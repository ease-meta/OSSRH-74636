package com.open.cloud.common.utils.spring;

import org.springframework.beans.factory.ListableBeanFactory;

import java.util.ArrayList;
import java.util.List;

import static com.open.cloud.common.utils.StringUtils.hasText;
import static com.open.cloud.common.utils.StringUtils.isEmpty;
import static com.open.cloud.common.utils.ObjectUtils.of;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static org.springframework.beans.factory.BeanFactoryUtils.beanNamesForTypeIncludingAncestors;
import static org.springframework.util.ObjectUtils.containsElement;

/**
 * @author Leijian
 */
public abstract class BeanFactoryUtils {

	public static <T> T getOptionalBean(ListableBeanFactory beanFactory, String beanName, Class<T> beanType) {

		if (!hasText(beanName)) {
			return null;
		}

		String[] beanNames = of(beanName);

		List<T> beans = getBeans(beanFactory, beanNames, beanType);

		return isEmpty(beans) ? null : beans.get(0);
	}


	/**
	 * Gets name-matched Beans from {@link ListableBeanFactory BeanFactory}
	 *
	 * @param beanFactory {@link ListableBeanFactory BeanFactory}
	 * @param beanNames   the names of Bean
	 * @param beanType    the {@link Class type} of Bean
	 * @param <T>         the {@link Class type} of Bean
	 * @return the read-only and non-null {@link List} of Bean names
	 */
	public static <T> List<T> getBeans(ListableBeanFactory beanFactory, String[] beanNames, Class<T> beanType) {

		if (isEmpty(beanNames)) {
			return emptyList();
		}

		String[] allBeanNames = beanNamesForTypeIncludingAncestors(beanFactory, beanType);

		List<T> beans = new ArrayList<T>(beanNames.length);

		for (String beanName : beanNames) {
			if (containsElement(allBeanNames, beanName)) {
				beans.add(beanFactory.getBean(beanName, beanType));
			}
		}

		return unmodifiableList(beans);
	}
}
