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

package com.google.resting.component.impl.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import com.google.resting.component.Alias;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.SingleValueConverter;
/**
 * To encapsulate all the tools required for marshalling xml using XStream API. The various components are: 
 * 
 * 1. aliasTypeMap - A map containing information about all the aliasing classes
 * 2. implicitCollectionMap - A map containing information about all the implicit collections
 * 3. referenceMode - Modes to signify reference in XML. Support for object graph.
 * 4. converters - A map of custom {@link Converter} instances, along with their priorities.
 * 5. singleValueConverters - A map of custom {@link SingleValueConverter} instances
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public class XMLAlias<T> implements Alias{
	
	private Map<String,Class> aliasTypeMap=null;
	private Map<String, Class> implicitCollectionMap=null;
	private ReferenceMode referenceMode=ReferenceMode.XPATH_RELATIVE_REFERENCES;
	private Map<Converter,Priority> converters=null;
	private Map<SingleValueConverter,Priority> singleValueConverters=null;
	private List<Class> annotatedTypeList=null;
	private Map<QName, Class> qualifiedNameMap = null;
	
	/**
	 * Constructor
	 */
	public XMLAlias(){
		aliasTypeMap= new HashMap<String, Class>();
	}//XMLAlias
	
	/**
	 * Constructor 
	 * @param aliasTypeMap - Map of aliasing classes. Key - alias, value - alias class.
	 */
	public XMLAlias(Map<String,Class> aliasTypeMap){
		this.aliasTypeMap= aliasTypeMap;
	}//XMLAlias 	
	
	/**
	 * Adds an alias to the XMLAlias
	 * 
	 * @param alias Name of the alias
	 * @param aliasClass	Aliasing class
	 * 
	 * @return this
	 */
	public XMLAlias add(String alias, Class aliasClass){
		aliasTypeMap.put(alias, aliasClass);
		return this;
	}//add
	
	/**
	 * Adds implicit collection information to XMLAlias
	 * 
	 * @param alias	Name of the implicit collection
	 * @param clazz Class in which implicit collection is contained
	 * 
	 * @return	this
	 */
	public XMLAlias addImplicitCollection(String alias, Class clazz){
		if(implicitCollectionMap==null)
			implicitCollectionMap= new HashMap<String, Class>();
		implicitCollectionMap.put(alias, clazz);
		return this;
	}//addImplicitCollection
		
	/**
	 * Adds custom {@link Converter} to XMLAlias
	 * 
	 * @param converter Custom {@link Converter} instance for XML unmarshalling
	 * 
	 * @return this
	 */
	public XMLAlias addConverter(Converter converter){
		if(converters==null)
			converters=new HashMap<Converter, Priority>();
		converters.put(converter, Priority.NORMAL);
		return this;
	}//addConverter
	
	/**
	 * Adds a {@link SingleValueConverter} to XMLAlias
	 * 
	 * @param singleValueConverter Custom @link SingleValueConverter} for xml unmarshalling
	 * 
	 * @return this
	 */
	
	public XMLAlias addConverter(SingleValueConverter singleValueConverter){
		if(singleValueConverters==null)
			singleValueConverters=new HashMap<SingleValueConverter, Priority>();
		singleValueConverters.put(singleValueConverter, Priority.NORMAL);
		return this;
	}//addConverter
	
	/**
	 * Adds a {@link Converter} with {@link Priority}
	 * 
	 * @param converter Custom {@link Converter} instance for XML unmarshalling
	 * @param priority {@link Priority} of the converter
	 * 
	 * @return this
	 */

	public XMLAlias addConverter(Converter converter, Priority priority){
		if(converters==null)
			converters=new HashMap<Converter, Priority>();
		converters.put(converter, priority);
		return this;
	}//addConverter
	
	/**
	 * Adds a {@link SingleValueConverter} with {@link Priority}
	 * 
	 * @param converter Custom {@link SingleValueConverter} instance for XML unmarshalling
	 * @param priority {@link Priority} of the converter
	 * 
	 * @return this
	 */	
	public XMLAlias addConverter(SingleValueConverter converter, Priority priority){
		if(singleValueConverters==null)
			singleValueConverters=new HashMap<SingleValueConverter, Priority>();
		singleValueConverters.put(converter, priority);
		return this;	
	}//addConverter
	
	public XMLAlias processAnnotation(Class clazz){
		if(annotatedTypeList == null)
			annotatedTypeList=new ArrayList<Class>();
		annotatedTypeList.add(clazz);
		return this;
	}
	
	/**
	 * Get the map of aliasing information 
	 * 
	 * @return Map of aliasing information
	 */
	@Override
	public Map<String,Class> getAliasTypeMap(){
		assert aliasTypeMap!=null:"The key-value map of aliases is null.";
		return aliasTypeMap;
	}//getAliasTypeMap
	
	
	/**
	 * Set the map of aliasing information
	 * 
	 * @param aliasTypeMap Map containing alias name as key and alias class as value
	 */

	@Override
	public void setAliasTypeMap(Map<String, Class> aliasTypeMap) {
		this.aliasTypeMap=aliasTypeMap;
	}//setAliasTypeMap

	/**
	 * Get the map of information for implicit collections
	 * 
	 * @return Map containing implicit collection name as key and class name as value
	 */
	public Map<String, Class> getImplicitCollectionMap() {
		return implicitCollectionMap;
	}//getImplicitCollectionAlias
	
	/**
	 *  Set the map of information for implicit collection
	 *  
	 * @param implicitCollectionMap Map containing implicit collection name as key and class name as value
	 */

	public void setImplicitCollectionMap(	Map<String, Class> implicitCollectionMap) {
		this.implicitCollectionMap = implicitCollectionMap;
	}//setImplicitCollectionAlias

	/**
	 * Set the {@link ReferenceMode} for the object graph in the xml.
	 * 
	 * @param referenceMode {@link ReferenceMode} of the object graph in the xml
	 */
	public void setReferenceMode(ReferenceMode referenceMode) {
		this.referenceMode = referenceMode;
		
	}//setReferenceMode

	/**
	 * Get the {@link ReferenceMode} for the object graph in the xml.
	 * 
	 * @return referenceMode {@link ReferenceMode} of the object graph in the xml
	 */

	public ReferenceMode getReferenceMode() {
		return referenceMode;
	}//getReferenceMode

	/**
	 * Set the map of custom {@link Converter} to XMLAlias
	 * 
	 * @param converters Map containing {@link Converter} and it's {@link Priority}
	 */
	public void setConverters(Map<Converter,Priority> converters) {
		this.converters = converters;
	}//setConverters

	/**
	 * Get the map of custom {@link Converter} to XMLAlias
	 * 
	 * @return converters Map containing {@link Converter} and it's {@link Priority}
	 */
	public Map<Converter,Priority> getConverters() {
		return converters;
	}//getConverters

	/**
	 * Get the map of custom {@link SingleValueConverter} to XMLAlias
	 * 
	 * @return singleValueConverters Map containing {@link SingleValueConverter} and it's {@link Priority}
	 */
	public Map<SingleValueConverter, Priority> getSingleValueConverters() {
		return singleValueConverters;
	}//getSingleValueConverters

	/**
	 * Set the map of custom {@link SingleValueConverter} to XMLAlias
	 * 
	 * @param singleValueConverters Map containing {@link SingleValueConverter} and it's {@link Priority}
	 */
	public void setSingleValueConverters(Map<SingleValueConverter, Priority> singleValueConverters) {
		this.singleValueConverters = singleValueConverters;
	}//setSingleValueConverters
	
	/**
	 * Get the list of annotated classes for aliasing annotations
	 * 
	 * @return List of annotated types
	 */

	public List<Class> getAnnotatedTypeList() {
		return annotatedTypeList;
	}

	/**
	 * Set the list of annotated classes for aliasing annotations
	 * 
	 * @param annotatedTypeList List of annotated classes
	 */
	public void setAnnotatedTypeList(List<Class> annotatedTypeList) {
		this.annotatedTypeList = annotatedTypeList;
	}
	
	public void setQNameMap(Map m) {
		this.qualifiedNameMap = m;
	}
	
	public XMLAlias addQName(QName name, Class z) {
		if(qualifiedNameMap == null) {
			qualifiedNameMap = new HashMap<QName, Class>();
		}
		this.qualifiedNameMap.put(name, z);
		return this;
	}
	
	public Map<QName, Class> getQNameMap() {
		return this.qualifiedNameMap;
	}
	
	/**
	 * Return class name for specified alias name
	 * @param aliasKey
	 * @return
	 */
	public Class getClassForAlias(String aliasKey) {
		return aliasTypeMap.get(aliasKey);
	}

}//XMLAlias
