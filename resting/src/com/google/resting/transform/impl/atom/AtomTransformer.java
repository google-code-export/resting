/*
 * Copyright (C) 2011 Google Code.
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
package com.google.resting.transform.impl.atom;

import java.util.ArrayList;
import java.util.List;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.Transformer;
import com.google.resting.transform.impl.JdomXMLTransformer;

/**
 * A transformer implementation to parse ATOM feeds
 * 
 * @author lakshmipriya-p
 *
 */
public class AtomTransformer<T> implements Transformer<T, ServiceResponse> {
	
	public AtomTransformer() {}
	
	@Override
	public T createEntity(String singleEntityStream, Class<T> targetType) {
		return null;
	}

	@Override
	public List<T> getEntityList(ServiceResponse source, Class<T> targetType,
			Alias alias) {
		return this
				.getEntityList(source.getResponseString(), targetType, alias);
	}

	@Override
	public List<T> getEntityList(String responseString, Class<T> targetType,
			Alias alias) {
		List<T> l = new ArrayList<T>(0);
		if (alias instanceof XMLAlias) {
			XMLAlias xmlAlias = (XMLAlias) alias;
			handleDefaultNS(xmlAlias);
			T entity = (T) new JdomXMLTransformer<T>().createEntity(
					responseString, targetType, xmlAlias);
			l.add(entity);
		}
		return l;
	}
	
	private void handleDefaultNS(XMLAlias xmlAlias) {
		xmlAlias.add("author", AtomAuthor.class).add("category",
				AtomCategory.class).add("link", AtomLink.class).add("entry",
				AtomEntry.class).add("source", AtomFeed.class).add("generator",
				AtomGenerator.class).add("contributor", AtomAuthor.class);
	}

	@Override
	public T createEntity(String singleEntityStream, Class<T> targetType,
			Alias alias) {
		if (alias instanceof XMLAlias) {
			XMLAlias xmlAlias = (XMLAlias) alias;
			handleDefaultNS(xmlAlias);
			return (T) new JdomXMLTransformer<T>().createEntity(
					singleEntityStream, targetType, xmlAlias);
		}
		return null;
	}
}
