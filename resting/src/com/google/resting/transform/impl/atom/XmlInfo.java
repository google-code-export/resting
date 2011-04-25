package com.google.resting.transform.impl.atom;

/**
 * Custom annotation to read XML namespace and other attributes
 * @author lakshmipriya-p
 *
 */
public @interface XmlInfo {

	String tag() default "";
	String namespace() default "";
}
