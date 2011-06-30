package com.google.resting.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ReflectionUtil {

	public static Field getField(Class c, String fieldName)
			throws NoSuchFieldException {
		try {
			return c.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class superClass = c.getSuperclass();
			if (superClass == null) {
				throw e;
			} else {
				return getField(superClass, fieldName);
			}
		}
	}
	
	public static List<Field> getAllFields(Class type, List<Field> toFill) {
		toFill.addAll(Arrays.asList(type.getDeclaredFields()));
		Class superCls = type.getSuperclass();
		if (superCls != null) {
			getAllFields(superCls, toFill);
		}
		return toFill;
	}
	
	public static StringBuffer describe(Object o, Class type, StringBuffer desc) {
		List<Field> fields = new ArrayList<Field>();
		getAllFields(type, fields);
		for (Field aField : fields) {
			aField.setAccessible(true);
			try {
				Object value = aField.get(o);
				Class cls = aField.getType();
				if (value != null) {
					if (Collection.class.isAssignableFrom(cls)) {
						Collection i = (Collection) value;
						for (Object aObject : i) {
							describe(aObject, aObject.getClass(), desc.append(
									aField.getName()).append(" attributes: "));
						}
					} else if (cls.equals(String.class) || cls.isPrimitive()) {
						desc.append(aField.getName()).append("=").append(value)
								.append(",");
					} else {
						describe(value, cls, desc);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return desc;
	}

}
