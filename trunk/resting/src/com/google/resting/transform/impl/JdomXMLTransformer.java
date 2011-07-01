package com.google.resting.transform.impl;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.Transformer;
import com.google.resting.util.ReflectionUtil;

/**
 * A JDOM based implementation to parse XML content
 *
 * @author lakshmipriya-p
 */
public class JdomXMLTransformer<T> implements Transformer<T, ServiceResponse> {

	@Override
	public T createEntity(String singleEntityStream, Class<T> targetType) {
		return null;
	}

	@Override
	public List<T> getEntityList(ServiceResponse source, Class<T> targetType,
			Alias alias) {
		return null;
	}

	@Override
	public List<T> getEntityList(String responseString, Class<T> targetType,
			Alias alias) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.resting.transform.Transformer#createEntity(java.lang.String,
	 * java.lang.Class, com.google.resting.component.Alias)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T createEntity(String singleEntityStream, Class<T> targetType,
			Alias alias) {
		try {
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(new StringReader(singleEntityStream));
			Element root = doc.getRootElement();
			return (T)parseElement(root, targetType, (XMLAlias)alias);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * A recursive function to parse and map xml content into a Java object. The
	 * function handles object references, List<Object>, Boxed object types,
	 * String and primitive types. The method automatically skips fields that
	 * are not defined in the Java object
	 * 
	 * @param element
	 *            The root element of XML to be parsed
	 * @param target
	 *            The class of the Java object
	 * @param alias
	 *            XMLAlias
	 * @return
	 * @throws Exception
	 */
	private static Object parseElement(Element element, Class target,
			XMLAlias alias) throws Exception {
		List<Element> entries = element.getChildren();
		Object targetInstance = target.newInstance();
		//iterate over children
		for (Element e : entries) {
			String name = e.getName();
			String value = e.getValue();
			try {
				// check if class has defined variable for this field
				Field f = ReflectionUtil.getField(target, name);
				// skip access checks
				f.setAccessible(true);
				// the field could be a custom Java object
				Class definedClass = alias.getClassForAlias(name);
				Class type = f.getType();
				// check if the type is a collection
				//TODO, handle other collection types as well
				if (type.equals(List.class)) {
					Object c = f.get(targetInstance);
					// if field type is collection, the collection will be null
					// while adding first element. so instantiate the collection
					if (c == null) {
						c = new ArrayList();
					}
					// check if type is List<?> or <List<Boxed types>
					if (definedClass != null) {
						// if type is List<?>, invoke parseElement with the new class type
						((List) c).add(parseElement(e, definedClass, alias));
					} else {
						((List) c).add(value);
					}
					f.set(targetInstance, c);
				} else if (definedClass != null) {
					// if field is simple boxed type, straight away set the
					// parsed value
					f.set(targetInstance, parseElement(e, definedClass, alias));
				} else {
					// if type is primitive, parse the value into primitive
					if (type.isPrimitive()) {
						if (type.equals(int.class)) {
							f.set(targetInstance, Integer.valueOf(value)
									.intValue());
						} else if (type.equals(boolean.class)) {
							f.set(targetInstance, Boolean.valueOf(value)
									.booleanValue());
						} else if (type.equals(short.class)) {
							f.set(targetInstance, Short.valueOf(value)
									.shortValue());
						} else if (type.equals(long.class)) {
							f.set(targetInstance, Long.valueOf(value)
									.longValue());
						} else if (type.equals(char.class)) {
							if (value.length() > 1)
								throw new IllegalArgumentException(
										"Can not convert " + value
												+ " into character");
							f.set(targetInstance, value.indexOf(0));
						}
					} else {
						f.set(targetInstance, value);
					}
				}
			} catch (NoSuchFieldException ne) {
				//ne.printStackTrace();
				//TODO, the behavior here should be customizable by users
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// parse attributes of element
			parseAttributes(e, target, targetInstance);
		}
		parseAttributes(element, target, targetInstance);
		return targetInstance;
	}
	
	/**
	 * Parse attributes of XML element
	 * @param e
	 * @param target
	 * @param targetInstance
	 */
	private static void parseAttributes(Element e, Class target,
			Object targetInstance) {
		List<Attribute> attributes = e.getAttributes();
		for (Attribute attr : attributes) {
			String attrname = attr.getName();
			String attrvalue = attr.getValue();
			try {
				Field f = target.getDeclaredField(attrname);
				f.setAccessible(true);
				f.set(targetInstance, attrvalue);
			} catch (NoSuchFieldException ne) {
				//TODO, behavior should be customizable by users
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
