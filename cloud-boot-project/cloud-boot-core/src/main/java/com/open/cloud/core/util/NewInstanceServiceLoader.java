package com.open.cloud.core.util;

import java.util.*;

/**
 * The type New instance service loader.
 *
 * @author Leijian
 * @date 2020 /3/26
 */
public final class NewInstanceServiceLoader {

    private static final Map<Class, Collection<Class<?>>> SERVICE_MAP = new HashMap<>();

    private NewInstanceServiceLoader() {

    }

    /**
     * Register.
     *
     * @param <T>     the type parameter
     * @param service the service
     */
    public static <T> void register(final Class<T> service) {
        for (T each : ServiceLoader.load(service)) {
            registerServiceClass(service, each);
        }
    }

    private static <T> void registerServiceClass(final Class<T> service, final T instance) {
        Collection<Class<?>> serviceClasses = SERVICE_MAP.get(service);
        if (null == serviceClasses) {
            serviceClasses = new LinkedHashSet<>();
        }
        serviceClasses.add(instance.getClass());
        SERVICE_MAP.put(service, serviceClasses);
    }


    /**
     * New service instances collection.
     *
     * @param <T>     the type parameter
     * @param service the service
     * @return the collection
     */
    @SuppressWarnings("unchecked")
    public static <T> Collection<T> newServiceInstances(final Class<T> service) {
        Collection<T> result = new LinkedList<>();
        if (null == SERVICE_MAP.get(service)) {
            return result;
        }
        for (Class<?> each : SERVICE_MAP.get(service)) {
            try {
                result.add((T) each.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // ignore
            }
        }
        return result;
    }
}

