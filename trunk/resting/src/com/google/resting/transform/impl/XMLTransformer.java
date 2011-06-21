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

import javax.xml.namespace.QName;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.xml.Priority;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.Transformer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.QNameMap;
import com.thoughtworks.xstream.io.xml.StaxDriver;
/**
 * Base transformer for transforming XML response
 * 
 * @author sujata.de
 * @since resting 0.1
 * 
 * @param <T> Target type
 */
public class XMLTransformer<T> implements Transformer<T, ServiceResponse> {
	private XStream xstream = new XStream(new DomDriver());
	private boolean namespaceAware = false;
	
	public XMLTransformer() {}
	
	public XMLTransformer(boolean flag) {
		this.namespaceAware = flag;
	}

	public T createEntity(String source, Class<T> targetType) {
		T dest = null;
		try {
			dest = (T)xstream.fromXML(source);
		} catch (ConversionException e) {
			System.out
					.println("The target type class attributes do not match the service response. Check class definition");
			e.printStackTrace();
		} catch (XStreamException e) {
			System.out
					.println("Tranformer failed to parse the response. Check error logs");
			e.printStackTrace();
		}
		return dest;
	}//createEntity
	
	public List<T> getEntityList(ServiceResponse serviceResponse, Class<T> targetType, Alias alias){
		return this.getEntityList(serviceResponse.getResponseString(), targetType, alias);
	}
	
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
		// Enable StAX driver if transformer is namespace aware
		Map<QName, Class> map = xmlAlias.getQNameMap();
		if (namespaceAware && map != null) {
			QNameMap qnameMap = new QNameMap();
			Set<QName> qnameSet = map.keySet();
			for (QName aQname : qnameSet) {
				qnameMap.registerMapping(aQname, map.get(aQname));
			}
			xstream = new XStream(new StaxDriver(qnameMap));
		}
		
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
