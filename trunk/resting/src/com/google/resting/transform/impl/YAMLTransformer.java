/*
 * Copyright (C) 2010 Google Code.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	public List<T> getEntityList(String responseString, Class<T> targetType,
			Alias alias) {
		List<T> list = new ArrayList<T>();
		Yaml yaml = new Yaml(new Constructor(targetType));
		Iterable<Object> objects = yaml.loadAll(responseString);
		for (Object unknown : objects) {
			if (unknown != null && unknown.getClass().equals(targetType)) {
				list.add((T) unknown);
			} else {
				System.out.println("Some objects could not be parsed");
			}
		}
		return list;
	}

	public List<T> getEntityList(ServiceResponse response, Class<T> targetType,
			Alias alias) {
		return this.getEntityList(response.getResponseString(), targetType, alias);
	}

	@Override
	public T createEntity(String singleEntityStream, Class<T> targetType,
			Alias alias) {
		return null;
	}
}
