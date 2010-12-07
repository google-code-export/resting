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

package com.google.resting.component.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * To encapsulate all the aliases used for marshalling HTTP response streams into objects.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class Alias {
	private Map<String,Class> aliasMap=null;
	//In many transformation, only a single alias is necessary. For ex. JSON.
	private String singleAlias=null;
	private List<Class> aliasList=null;
	
	public Alias(){
		aliasMap= new HashMap<String, Class>();
	}//Alias - aliasMap
	
	public Alias(String singleAlias){
		this.singleAlias=singleAlias;
		//The aliasMap or the alias list is not needed to be initialized since
		//a single alias is enough for transformers for JSON.
	}//Alias - singleAlias
	
	public Alias(List<Class> aliasList){
		this.aliasMap= new HashMap<String, Class>();
		this.aliasList=aliasList;
	}//Alias - aliasList	
	
	public Alias(Map<String,Class> aliasMap, List<Class> aliasList){
		this.aliasMap= aliasMap;
		this.aliasList=aliasList;
	}//Alias - aliasList	
	
	public Alias add(String alias, Class aliasClass){
		if(aliasMap==null)
			aliasMap= new HashMap<String, Class>();
		aliasMap.put(alias, aliasClass);
		return this;
	}//add
	
	public Alias add(Class aliasClass){
		if(aliasList == null)
			aliasList=new ArrayList<Class>();
		aliasList.add(aliasClass);
		return this;
	}
	
	public Map<String,Class> getAliasMap(){
		assert aliasMap!=null:"The key-value map of aliases is null.";
		return aliasMap;
	}//getAliasMaps
	
	public String getSingleAlias(){
		assert singleAlias!=null:"The alias is null";
		return singleAlias;
	}//getSingleAlias

	public List<Class> getAliasList() {
		assert aliasList!=null:"The alias list is null";
		return aliasList;
	}//getAliasList

}//AliasMap
