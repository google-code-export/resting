package com.google.resting.component.impl;

import java.util.HashMap;
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
	
	public Alias(){
		aliasMap= new HashMap<String, Class>();
	}//AliasMap
	
	public Alias(String singleAlias){
		this.singleAlias=singleAlias;
		//The aliasMap is not needed to be initialized since
		//a single alias is enough for transformers for JSON.
	}//AliasMap
	
	public Alias add(String alias, Class aliasClass){
		aliasMap.put(alias, aliasClass);
		return this;
	}//add
	
	public Map<String,Class> getAliasMap(){
		return aliasMap;
	}//getAliasMaps
	
	public String getSingleAlias(){
		return singleAlias;
	}//getSingleAlias

}//AliasMap
