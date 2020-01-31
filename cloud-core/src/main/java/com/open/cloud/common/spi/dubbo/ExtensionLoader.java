package com.open.cloud.common.spi.dubbo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

import com.open.cloud.common.utils.ConcurrentHashSet;
import com.open.cloud.common.utils.Holder;
import com.open.cloud.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Leijian
 */
@Slf4j
public class ExtensionLoader<T> {

	private static final ConcurrentMap<Class<?>, ExtensionLoader<?>> EXTENSION_LOADERS = new ConcurrentHashMap<>();

	private static final Pattern NAME_SEPARATOR = Pattern.compile("\\s*[,]+\\s*");

	private static final String SERVICES_DIRECTORY = "META-INF/services/";

	private final Class<?> type;

	private String cachedDefaultName;

	private final ConcurrentMap<String, Holder<Object>> cachedInstances = new ConcurrentHashMap<>();

	private final Holder<Map<String, Class<?>>> cachedClasses = new Holder<>();

	private Set<Class<?>> cachedWrapperClasses;

	private Map<String, IllegalStateException> exceptions = new ConcurrentHashMap<>();

	private static final ConcurrentMap<Class<?>, Object> EXTENSION_INSTANCES = new ConcurrentHashMap<>();

	private ExtensionLoader(Class<?> type) {
		this.type = type;
		final SPI defaultAnnotation = this.type.getAnnotation(SPI.class);
		if (defaultAnnotation != null) {
			String value = defaultAnnotation.value();
			if (value.trim().length() > 0) {
				String[] names = NAME_SEPARATOR.split(value);
				if (names.length > 1) {
					throw new IllegalStateException("more than 1 default extension name on extension " + type.getName() + ": " + Arrays.toString(names));
				}
				if (names.length == 1) {
					cachedDefaultName = names[0];
				}
			}
		}
	}

	private static <T> boolean withExtensionAnnotation(Class<T> type) {
		return type.isAnnotationPresent(SPI.class);
	}

	private static <T> Annotation getAnnotationsByType(Class<T> type, Class<? extends Annotation> annotationClass) {
		return type.getAnnotation(annotationClass);
	}

	@SuppressWarnings("unchecked")
	public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
		if (type == null) {
			throw new IllegalArgumentException("Extension type == null");
		}
		if (!type.isInterface()) {
			throw new IllegalArgumentException("Extension type(" + type + ") is not interface!");
		}
		if (!withExtensionAnnotation(type)) {
			throw new IllegalArgumentException("Extension type(" + type + ") is not extension, because WITHOUT @" + SPI.class.getSimpleName() + " Annotation!");
		}

		ExtensionLoader<T> loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
		if (loader == null) {
			EXTENSION_LOADERS.putIfAbsent(type, new ExtensionLoader<T>(type));
			loader = (ExtensionLoader<T>) EXTENSION_LOADERS.get(type);
		}
		return loader;
	}

	@SuppressWarnings("unchecked")
	public T getLoadedExtension(String name) {
		if (!StringUtils.hasText(name)) {
			throw new IllegalArgumentException("Extension name == null");
		}
		Holder<Object> holder = cachedInstances.get(name);
		if (holder == null) {
			cachedInstances.putIfAbsent(name, new Holder<>());
			holder = cachedInstances.get(name);
		}
		return (T) holder.get();
	}

	public Set<String> getExtensionsKeys() {
		return Collections.unmodifiableSet(new TreeSet<>(getExtensionClasses().keySet()));
	}

	public Set<T> getExtensions() {
		Set<T> set = new HashSet<>();
		for (String key : getExtensionsKeys()) {
			set.add(getExtension(key));
		}
		return set;
	}

	public T getExtension() {
		if (cachedDefaultName == null || cachedDefaultName.isEmpty()) {
			throw findException("default");
		}
		return getExtension(cachedDefaultName);
	}

	@SuppressWarnings("unchecked")
	public T getExtension(String name) {
		if (name == null || name.length() == 0) {
			throw new IllegalArgumentException("Extension name == null");
		}
		Holder<Object> holder = cachedInstances.get(name);
		if (holder == null) {
			cachedInstances.putIfAbsent(name, new Holder<>());
			holder = cachedInstances.get(name);
		}
		Object instance = holder.get();
		if (instance == null) {
			synchronized (holder) {
				instance = holder.get();
				if (instance == null) {
					instance = createExtension(name);
					holder.set(instance);
				}
			}
		}
		return (T) instance;
	}

	private Map<String, Class<?>> getExtensionClasses() {
		Map<String, Class<?>> classes = cachedClasses.get();
		if (classes == null) {
			synchronized (cachedClasses) {
				classes = cachedClasses.get();
				if (classes == null) {
					classes = loadExtensionClasses();
					cachedClasses.set(classes);
				}
			}
		}
		return classes;
	}

	private Map<String, Class<?>> loadExtensionClasses() {
		Map<String, Class<?>> extensionClasses = new HashMap<>();
		loadFile(extensionClasses, SERVICES_DIRECTORY);
		return extensionClasses;
	}

	private ClassLoader findClassLoader() {
		return ExtensionLoader.class.getClassLoader();
	}

	@SuppressWarnings("all")
	private void loadFile(Map<String, Class<?>> extensionClasses, String dir) {
		String fileName = dir + type.getName();
		try {
			Enumeration<URL> urls;
			ClassLoader classLoader = findClassLoader();
			if (classLoader != null) {
				urls = classLoader.getResources(fileName);
			} else {
				urls = ClassLoader.getSystemResources(fileName);
			}
			if (urls != null) {
				while (urls.hasMoreElements()) {
					java.net.URL url = urls.nextElement();
					try {
						BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
						try {
							String line = null;
							while ((line = reader.readLine()) != null) {
								final int ci = line.indexOf('#');
								if (ci >= 0) {
									line = line.substring(0, ci);
								}
								line = line.trim();
								if (line.length() > 0) {
									try {
										String name = null;
										int i = line.indexOf('=');
										if (i > 0) {
											name = line.substring(0, i).trim();
											line = line.substring(i + 1).trim();
										}
										if (line.length() > 0) {
											Class<?> clazz = Class.forName(line, true, classLoader);

											if (!type.isAssignableFrom(clazz)) {
												throw new IllegalStateException("Error when load extension class(interface: " + type + ", class line: " + clazz.getName() + "), class " + clazz.getName()
														+ "is not subtype of interface.");
											}
											Activate activate = (Activate) getAnnotationsByType(clazz, Activate.class);
											if (Objects.isNull(activate) || activate.activate()) {
												if (cachedWrapperClasses == null) {
													cachedWrapperClasses = new ConcurrentHashSet<Class<?>>();
												}
												cachedWrapperClasses.add(clazz);
												SPI spi = (SPI) getAnnotationsByType(clazz, SPI.class);
												if (!StringUtils.hasText(name)) {
													name = spi.value();
												}
												if (StringUtils.isEmpty(name)) {
													throw new IllegalStateException();
												}
												extensionClasses.put(name, clazz);
											}
										}
									} catch (Throwable t) {
										IllegalStateException e =
												new IllegalStateException("Failed to load extension class(interface: " + type + ", class line: " + line + ") in " + url + ", cause: " + t.getMessage(), t);
										exceptions.put(line, e);
									}
								}
							} // end of while read lines
						} finally {
							reader.close();
						}
					} catch (Throwable t) {
						log.error("Exception when load extension class(interface: " + type + ", class file: " + url + ") in " + url, t);
					}
				} // end of while urls
			}
		} catch (Throwable t) {
			log.error("Exception when load extension class(interface: " + type + ", description file: " + fileName + ").", t);
		}
	}

	@SuppressWarnings("unchecked")
	private T createExtension(String name) {
		Class<?> clazz = getExtensionClasses().get(name);
		if (clazz == null) {
			throw findException(name);
		}
		try {
			T instance = (T) EXTENSION_INSTANCES.get(clazz);
			if (instance == null) {
				instance = (T) clazz.newInstance();
				EXTENSION_INSTANCES.putIfAbsent(clazz, instance);
			}
			return (T) EXTENSION_INSTANCES.get(clazz);
		} catch (Throwable t) {
			throw new IllegalStateException("Extension instance(name: " + name + ", class: " + type + ")  could not be instantiated: " + t.getMessage(), t);
		}
	}

	private IllegalStateException findException(String name) {
		for (Map.Entry<String, IllegalStateException> entry : exceptions.entrySet()) {
			if (entry.getKey().toLowerCase().contains(name.toLowerCase())) {
				return entry.getValue();
			}
		}
		StringBuilder buf = new StringBuilder("No such extension " + type.getName() + " by name " + name);
		int i = 1;
		for (Map.Entry<String, IllegalStateException> entry : exceptions.entrySet()) {
			if (i == 1) {
				buf.append(", possible causes: ");
			}
			buf.append("\r\n(");
			buf.append(i++);
			buf.append(") ");
			buf.append(entry.getKey());
			buf.append(":\r\n");
			buf.append(entry.getValue().getClass().getName() + ":" + entry.getValue().getMessage());
		}
		return new IllegalStateException(buf.toString());
	}
}
