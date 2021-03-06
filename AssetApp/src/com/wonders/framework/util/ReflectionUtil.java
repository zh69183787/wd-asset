package com.wonders.framework.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;

/**
 * 反射工具类
 * 
 * @author cheney
 * 
 */
public class ReflectionUtil {

	/**
	 * 获取指定对象的属性值
	 * 
	 * @param source
	 * @param property
	 * @return 属性值
	 */
	public static Object getProperty(Object source, String property) {
		BeanWrapper sourceBw = new BeanWrapperImpl(source);
		return sourceBw.getPropertyValue(property);
	}

	/**
	 * 设置指定对象的属性
	 * 
	 * @param source
	 *            对象
	 * @param property
	 *            属性
	 * @param value
	 *            新的属性值
	 */
	public static void setProperty(Object source, String property, Object value) {
		BeanWrapper sourceBw = new BeanWrapperImpl(source);
		sourceBw.setPropertyValue(property, value);
	}

	/**
	 * 把src中的内容Copy到指定target
	 * 
	 * @param src
	 * @param target
	 */
	public static void copyProperties(Object src, Object target) {
		copyProperties(src, target, null);
	}

	/**
	 * 把src中的内容Copy到指定target，但是忽略指定的属性
	 * 
	 * @param src
	 * @param target
	 * @param ignoreProps
	 *            指定忽略的属性
	 */
	public static void copyProperties(Object source, Object target,
			String[] ignoreProperties) {
		if (source == null || target == null) {
			throw new IllegalArgumentException(
					"Source and target must not be null");
		}
		List<String> ignoreList = (ignoreProperties != null) ? Arrays
				.asList(ignoreProperties) : null;
		BeanWrapper sourceBw = new BeanWrapperImpl(source);
		BeanWrapper targetBw = new BeanWrapperImpl(target);
		MutablePropertyValues values = new MutablePropertyValues();
		for (int i = 0; i < sourceBw.getPropertyDescriptors().length; i++) {
			PropertyDescriptor sourceDesc = sourceBw.getPropertyDescriptors()[i];
			String name = sourceDesc.getName();
			try {
				PropertyDescriptor targetDesc = targetBw
						.getPropertyDescriptor(name);
				if (targetDesc.getWriteMethod() != null
						&& sourceDesc.getReadMethod() != null
						&& (ignoreProperties == null || (!ignoreList
								.contains(name)))) {
					values.addPropertyValue(new PropertyValue(name, sourceBw
							.getPropertyValue(name)));
				}
			} catch (BeansException e) {
				// ignore loop error
			}
		}
		targetBw.setPropertyValues(values);
		// BeanUtils.copyProperties(src, target, ignoreProps);
	}

	/**
	 * Check if the given class represents a "simple" property, i.e. a
	 * primitive, a String, a Class, or a corresponding array.
	 * 
	 */
	public static boolean isSimpleProperty(Class<?> clazz) {
		return clazz.isPrimitive() || isPrimitiveArray(clazz)
				|| isPrimitiveWrapperArray(clazz) || clazz.equals(String.class)
				|| clazz.equals(String[].class) || clazz.equals(Class.class)
				|| clazz.equals(Class[].class);
	}

	/**
	 * Check if the given class represents a primitive array, i.e. boolean,
	 * byte, char, short, int, long, float, or double.
	 */
	public static boolean isPrimitiveArray(Class<?> clazz) {
		return boolean[].class.equals(clazz) || byte[].class.equals(clazz)
				|| char[].class.equals(clazz) || short[].class.equals(clazz)
				|| int[].class.equals(clazz) || long[].class.equals(clazz)
				|| float[].class.equals(clazz) || double[].class.equals(clazz);
	}

	/**
	 * Check if the given class represents an array of primitive wrappers, i.e.
	 * Boolean, Byte, Character, Short, Integer, Long, Float, or Double.
	 */
	public static boolean isPrimitiveWrapperArray(Class<?> clazz) {
		return Boolean[].class.equals(clazz) || Byte[].class.equals(clazz)
				|| Character[].class.equals(clazz)
				|| Short[].class.equals(clazz) || Integer[].class.equals(clazz)
				|| Long[].class.equals(clazz) || Float[].class.equals(clazz)
				|| Double[].class.equals(clazz);
	}

}
