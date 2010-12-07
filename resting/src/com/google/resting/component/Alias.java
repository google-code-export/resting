package com.google.resting.component;

import java.util.Map;
/**
 * Base interface for Alias object for parsing various types of responses.
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public interface Alias {
	
	public void setAliasTypeMap(Map<String, Class> aliasTypeMap);
	public Map<String, Class> getAliasTypeMap();

}//Alias
