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
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.xml.Priority;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.Transformer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
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
	
	public List<T> getEntityList(ServiceResponse serviceResponse, Class<T> targetType, Alias alias){
		return this.getEntityList(serviceResponse.getResponseString(), targetType, alias);
	}
	
	@Override
	public List<T> getEntityList(String responseString, Class<T> targetType, Alias alias){
		//String responseString=serviceResponse.getResponseString();
		XMLAlias xmlAlias=null;
		if (alias instanceof XMLAlias) {
			xmlAlias = (XMLAlias) alias;
			constructXStreamObject(xmlAlias);	
		}
		List<T> dests=new ArrayList<T>();
		dests.add(createEntity(responseString, targetType));
		//TODO XSD is not yet handled. Need to bring in XMLBeans.
		return dests;
	}//getEntityList
	
	private void constructXStreamObject(XMLAlias xmlAlias){
		
		//Set alias
		Map<String, Class> aliasTypeMap = xmlAlias.getAliasTypeMap();
		Set<Entry<String, Class>> aliasSet=null;
		if (aliasTypeMap != null) {
			aliasSet = aliasTypeMap.entrySet();
			for (Map.Entry<String, Class> aliasEntry : aliasSet) {
				xstream.alias(aliasEntry.getKey(), aliasEntry.getValue());
			}
		}
		
		//Set implicit collection
		Map<String, Class> implicitAliasMap = xmlAlias.getImplicitCollectionMap();
		Set<Entry<String, Class>> implicitAliasSet=null;
		if (implicitAliasMap != null) {
			implicitAliasSet = implicitAliasMap.entrySet();
			for (Map.Entry<String, Class> aliasEntry : implicitAliasSet) {
				xstream.addImplicitCollection(aliasEntry.getValue(),aliasEntry.getKey());
			}
		}
		
		//Set mode
		xstream.setMode(xmlAlias.getReferenceMode().getXStreamMode());
		
		//Set converters
		Map<Converter,Priority> converters = xmlAlias.getConverters();
		Set<Entry<Converter,Priority>> converterSet=null;
		if(converters !=null){
			converterSet=converters.entrySet();
			for(Entry<Converter, Priority> converterEntry : converterSet){
				xstream.registerConverter(converterEntry.getKey(), converterEntry.getValue().getXStreamPriority());
			}
		}

		Map<SingleValueConverter,Priority> singleValueConverters = xmlAlias.getSingleValueConverters();
		Set<Entry<SingleValueConverter,Priority>> sconverterSet=null;
		if(singleValueConverters !=null){
			sconverterSet=singleValueConverters.entrySet();
			for(Entry<SingleValueConverter, Priority> converterEntry : sconverterSet){
				xstream.registerConverter(converterEntry.getKey(), converterEntry.getValue().getXStreamPriority());
			}
		}		
		
		//Process annotations
		List<Class> annotatedClassList=xmlAlias.getAnnotatedTypeList();
		if(annotatedClassList!=null){
			for(Class type:annotatedClassList)
				xstream.processAnnotations(type);
		}
		
	}//constructXStreamObject


}//XMLTransformer
