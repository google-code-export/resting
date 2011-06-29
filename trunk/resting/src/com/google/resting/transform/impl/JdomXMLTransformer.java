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
	
	private static Object parseElement(Element element, Class target,
			XMLAlias alias) throws Exception {
		List<Element> entries = element.getChildren();
		Object targetInstance = target.newInstance();
		for (Element e : entries) {
			String name = e.getName();
			String value = e.getValue();
			try {
				Field f = ReflectionUtil.getField(target, name);
				f.setAccessible(true);
				Class definedClass = alias.getClassForAlias(name);
				if (f.getType().equals(List.class)) {
					Object c = f.get(targetInstance);

					if (c == null) {
						c = new ArrayList();
					}
					if (definedClass != null) {
						((List) c).add(parseElement(e, definedClass, alias));
					} else {
						((List) c).add(value);
					}
					f.set(targetInstance, c);
				} else if (definedClass != null) {
					f.set(targetInstance, parseElement(e, definedClass, alias));
				} else {
					f.set(targetInstance, value);
				}
			} catch (NoSuchFieldException ne) {
				//ne.printStackTrace();
				//TODO, handle ne
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			parseAttributes(e, target, targetInstance);
		}

		parseAttributes(element, target, targetInstance);
		return targetInstance;
	}
	
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
