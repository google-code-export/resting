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

package com.google.resting.component.impl.json;

import java.util.HashMap;
import java.util.Map;

import com.google.resting.component.Alias;
/**
 * To encapsulate all the aliases used for marshalling JSON response into objects.
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public class JSONAlias implements Alias{
	
	private Map<String,Class> aliasTypeMap=null;
	
	private String singleAlias=null;
	
	public JSONAlias(){
		aliasTypeMap= new HashMap<String, Class>();
	}//JSONAlias 
	
	public JSONAlias(String singleAlias){
		this.singleAlias=singleAlias;
		//The aliasMap or the alias list is not needed to be initialized since
		//a single alias is enough for transformers for JSON.
	}//JSONAlias 

	public JSONAlias(Map<String,Class> aliasTypeMap){
		this.aliasTypeMap= aliasTypeMap;
	}//JSONAlias 	
	
	public JSONAlias add(String alias, Class aliasClass){
		if(aliasTypeMap==null)
			aliasTypeMap= new HashMap<String, Class>();
		aliasTypeMap.put(alias, aliasClass);
		return this;
	}//add
	
	
	@Override
	public Map<String,Class> getAliasTypeMap(){
		assert aliasTypeMap!=null:"The key-value map of aliases is null.";
		return aliasTypeMap;
	}//getAliasTypeMap
	
	public String getSingleAlias(){
		assert singleAlias!=null:"The alias is null";
		return singleAlias;
	}//getSingleAlias

	@Override
	public void setAliasTypeMap(Map<String, Class> aliasTypeMap) {
		this.aliasTypeMap=aliasTypeMap;		
	}//setAliasTypeMap

}//JSONAlias
