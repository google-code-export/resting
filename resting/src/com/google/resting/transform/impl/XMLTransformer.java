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
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.JSONAlias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.transform.Transformer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * Base transformer for transforming XML response
 * 
 * @author sujata.de
 * @since resting 0.1
 * 
 * @param <T> Target type
 */
public class XMLTransformer<T> implements Transformer<T, ServiceResponse> {
	private XStream xstream=new XStream(new DomDriver());

	@Override
	public T createEntity(String source, Class<T> targetType) {
		
		T dest=(T)xstream.fromXML(source);
		return dest;
	}//createEntity
	
	@Override
	public List<T> getEntityList(ServiceResponse serviceResponse, Class<T> targetType, Alias alias){
		String responseString=serviceResponse.getResponseString();
		Set<Entry<String, Class>> aliasSet=alias.getAliasTypeMap().entrySet();
		for(Map.Entry<String, Class> aliasEntry: aliasSet){
			xstream.alias(aliasEntry.getKey(), aliasEntry.getValue());
		}
		List<T> dests=new ArrayList<T>();
		dests.add(createEntity(responseString, targetType));
		//TODO XSD is not yet handled. Need to bring in XMLBeans.
		return dests;
	}//getEntityList

}//XMLTransformer
