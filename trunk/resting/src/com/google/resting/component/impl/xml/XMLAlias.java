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

import java.util.HashMap;
import java.util.Map;

import com.google.resting.component.Alias;
/**
 * To encapsulate all the aliases used for marshalling XML response into objects.
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public class XMLAlias<T> implements Alias{
	private Map<String,Class> aliasTypeMap=null;
	private Map<String, Class> implicitCollectionAlias=null;
	
	public XMLAlias(){
		aliasTypeMap= new HashMap<String, Class>();
	}//XMLAlias
	

	public XMLAlias(Map<String,Class> aliasTypeMap){
		this.aliasTypeMap= aliasTypeMap;
	}//XMLAlias 	
	
	public XMLAlias add(String alias, Class aliasClass){
		if(aliasTypeMap==null)
			aliasTypeMap= new HashMap<String, Class>();
		aliasTypeMap.put(alias, aliasClass);
		return this;
	}//add
	
	public XMLAlias addImplicitCollection(String alias, Class aliasClass){
		if(implicitCollectionAlias==null)
			implicitCollectionAlias= new HashMap<String, Class>();
		implicitCollectionAlias.put(alias, aliasClass);
		return this;
	}//add
		
	
	
	@Override
	public Map<String,Class> getAliasTypeMap(){
		assert aliasTypeMap!=null:"The key-value map of aliases is null.";
		return aliasTypeMap;
	}//getAliasTypeMap


	@Override
	public void setAliasTypeMap(Map<String, Class> aliasTypeMap) {
		this.aliasTypeMap=aliasTypeMap;
	}//setAliasTypeMap


	public Map<String, Class> getImplicitCollectionAlias() {
		return implicitCollectionAlias;
	}


	public void setImplicitCollectionAlias(
			Map<String, Class> implicitCollectionAlias) {
		this.implicitCollectionAlias = implicitCollectionAlias;
	}
	

}//XMLAlias
