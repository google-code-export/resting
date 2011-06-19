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

import java.util.List;

import javax.xml.namespace.QName;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.Transformer;
import com.google.resting.transform.impl.XMLTransformer;

/**
 * A transformer implementation to parse ATOM feeds
 * @author lakshmipriya-p
 *
 */
public class AtomTransformer<T> implements Transformer<T, ServiceResponse> {
	private boolean addDefaultNamespace = true;
	private static final String NAMESPACE = "http://www.w3.org/2005/Atom";
	private static final String PREFIX = "atom";
	
	public AtomTransformer() {}
	
	public AtomTransformer(boolean addDefaultNamespace) {
		this.addDefaultNamespace = addDefaultNamespace;
	}
	
	@Override
	public T createEntity(String singleEntityStream, Class<T> targetType) {
		return new XMLTransformer<T>().createEntity(
				singleEntityStream, targetType);
	}

	@Override
	public List<T> getEntityList(ServiceResponse source, Class<T> targetType, Alias alias) {
		if(addDefaultNamespace && alias instanceof XMLAlias) {
			handleDefaultNS((XMLAlias)alias); 
		}
		return new XMLTransformer<T>(true).getEntityList(
				source, targetType, alias);
	}

	@Override
	public List<T> getEntityList(String responseString, Class<T> targetType,
			Alias alias) {
		if(addDefaultNamespace && alias instanceof XMLAlias) {
			handleDefaultNS((XMLAlias)alias); 
		}
		return new XMLTransformer<T>(true).getEntityList(
				responseString, targetType, alias);
	}
	
	private void handleDefaultNS(XMLAlias xmlAlias) {
		xmlAlias.add("feed", AtomFeed.class).add("author", AtomAuthor.class)
				.add("category", AtomCategory.class)
				.add("link", AtomLink.class)
				.add("Query", OpenSearchQuery.class).add("entry",
						AtomEntry.class);
		xmlAlias.addQName(new QName(NAMESPACE, "feed", PREFIX), AtomFeed.class);
		xmlAlias.addConverter(new AtomCategoryConverter());
		xmlAlias.addConverter(new AtomLinkConverter());
		xmlAlias.addConverter(new OpenSearchQueryConverter());
		xmlAlias.addImplicitCollection("entries", AtomFeed.class);
		xmlAlias.addImplicitCollection("links", AtomEntry.class);
	}
}
