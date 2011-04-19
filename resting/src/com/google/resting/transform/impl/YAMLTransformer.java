package com.google.resting.transform.impl;

import java.util.ArrayList;
import java.util.List;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.transform.Transformer;

/**
 * Base transformer for transforming YAML response.
 * 
 * @author priya
 * @since resting 0.5
 * 
 * @param <T>
 *            Target type
 */
public class YAMLTransformer<T> implements Transformer<T, ServiceResponse> {

	@SuppressWarnings("unchecked")
	@Override
	public T createEntity(String source, Class<T> targetType) {
		Object unknown = null;
		try {
			Yaml yaml = new Yaml();
			unknown = yaml.load(source);
			assert unknown != null : "Parsed object is null";
			assert unknown.getClass().equals(targetType) : "Cant construct an object of type "
					+ targetType + " from response stream";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (T) unknown;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getEntityList(ServiceResponse source, Class<T> targetType,
			Alias alias) {
		List<T> list = new ArrayList<T>();
		Yaml yaml = new Yaml(new Constructor(targetType));
		Iterable<Object> objects = yaml.loadAll(source.getResponseString());
		for (Object unknown : objects) {
			if (unknown != null && unknown.getClass().equals(targetType)) {
				list.add((T) unknown);
			} else {
				System.out.println("Some objects could not be parsed");
			}
		}
		return list;
	}
}
