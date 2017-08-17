package com.swift.jrmt.common.utils;

import java.util.Collection;
import java.util.Map;

import com.swift.jrmt.common.constants.StatusCode;
import com.swift.jrmt.common.exception.JrmtRuntimeException;



/**
 * 说明:断言工具类，请结合KuaiCheRuntimeException类使用
 * 
 * @see KuaiCheRuntimeException
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * 
 * 2015年5月6日 下午10:03:03
 */
public  class AssertUtil {

	static public void fail(StatusCode statusCode, String defaultMessage) {
		throw new JrmtRuntimeException(statusCode, defaultMessage);
	}
	
	
	public static void hasLength(String text, StatusCode statusCode) {
		hasLength(text, statusCode, null);
	}
	
	public static void hasLength(String text, StatusCode statusCode, String defaultMessage) {
		if (!StringUtil.hasLength(text)) {
			fail(statusCode ,defaultMessage);
		}
	}
	
	
	public static void hasLengthByTrim(String text, StatusCode statusCode) {
		if (text == null || text.trim().length() == 0) {
			fail(statusCode, null);
		}
	}
	
	public static void hasLengthByTrim(String text, StatusCode statusCode, String defaultMessage) {
		if (text == null || text.trim().length() == 0) {
			fail(statusCode, defaultMessage);
		}
	}
	
	public static void hasErrors(boolean expression, StatusCode statusCode) {
		if(expression){
			fail(statusCode, null);
		}
	}
	
	public static void hasErrors(boolean expression, StatusCode statusCode, String defaultMessage) {
		if(expression){
			fail(statusCode, defaultMessage);
		}
	}
	
	/**
	 * 当判断表达式不为真时，报错
	 * @param expression 判断表达式
	 * @param statusCode 错误码
	 */
	public static void isTrue(boolean expression, StatusCode statusCode) {
		if (!expression) {
			fail(statusCode, null);
		}
	}
	
	public static void isTrue(boolean expression, StatusCode statusCode, String defaultMessage) {
		if (!expression) {
			fail(statusCode, defaultMessage);
		}
	}

	/**
	 * 当判断表达式不为假时，报错
	 * @param expression 判断表达式
	 * @param statusCode 错误码
	 */
	public static void isFalse(boolean expression, StatusCode statusCode) {
		isTrue(!expression, statusCode);
	}
	
	public static void isFalse(boolean expression, StatusCode statusCode, String defaultMessage) {
		isTrue(!expression, statusCode, defaultMessage);
	}

	/**
	 * 当对象不为空时，报错
	 *
	 * @param object
	 * @param statusCode
	 */
	public static void isNull(Object object, StatusCode statusCode) {
		if (object != null) {
			fail(statusCode, null);
		}
	}
	
	public static void isNull(Object object, StatusCode statusCode, String defaultMessage) {
		if (object != null) {
			fail(statusCode, defaultMessage);
		}
	}

	/**
	 * 当对象为空时，报错
	 *
	 * @param object
	 * @param statusCode
	 */
	public static void notNull(Object object, StatusCode statusCode) {
		if (object == null) {
			fail(statusCode, null);
		}
	}
	
	public static void notNull(Object object, StatusCode statusCode, String defaultMessage) {
		if (object == null) {
			fail(statusCode, defaultMessage);
		}
	}
	
	public static void notEmpty(Object[] array, StatusCode statusCode) {
		if (array == null || array.length == 0) {
			fail(statusCode, null);
		}
	}
	
	public static void notEmpty(Collection<?> collection, StatusCode statusCode) {
		if (CollectionUtil.isEmpty(collection)) {
			fail(statusCode, null);
		}
	}
	
	public static void notEmpty(Collection<?> collection, StatusCode statusCode, String defaultMessage) {
		if (CollectionUtil.isEmpty(collection)) {
			fail(statusCode, defaultMessage);
		}
	}
	
	public static void notEmpty(Map<?,?> map, StatusCode statusCode) {
		if (CollectionUtil.isEmpty(map)) {
			fail(statusCode, null);
		}
	}
	
	public static void notEmpty(Map<?,?> map, StatusCode statusCode, String defaultMessage) {
		if (CollectionUtil.isEmpty(map)) {
			fail(statusCode, defaultMessage);
		}
	}
	
	public static void validate(boolean expression, StatusCode statusCode){
		if (!expression) {
			fail(statusCode, null);
		}
	}
	
	public static void validate(boolean expression, StatusCode statusCode, String defaultMessage){
		if (!expression) {
			fail(statusCode, defaultMessage);
		}
	}
	
	public static void regex(String text, String regex, StatusCode statusCode){
		boolean result = text.matches(regex);
		isTrue(result, statusCode);
	}
	
	public static void regex(String text, String regex, StatusCode statusCode, String defaultMessage){
		boolean result = text.matches(regex);
		isTrue(result, statusCode, defaultMessage);
	}
	
	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz);</pre>
	 * @param object the object to check
	 * @throws AssertException if the object is <code>null</code>
	 */
//	public static void notNull(Object object) {
//		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
//	}
	
	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws AssertException if the object is <code>null</code>
	 */
//	public static void notNull(Object object, String message) {
//		if (object == null) {
//			throw new AssertException(message);
//		}
//	}
	
}
