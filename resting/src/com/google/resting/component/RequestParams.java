package com.google.resting.component;

import java.util.ArrayList;
import java.util.List;

import com.google.resting.component.impl.NameValueEntity;

/**
 * Abstract class for handling collection query parameters for REST invocation
 * 
 * @author sujata.de
 * @since resting 0.1
 * 
 */
public abstract class RequestParams {
	
	//
	protected List<NameValueEntity> queryParams;

	protected RequestParams(){
		queryParams= new ArrayList<NameValueEntity>();
	}

	/**
	 * To add input params in the format &key=value
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void add(String name, String value);
	

	public List<NameValueEntity> getKeyValueRequestParams() {
		return queryParams;
	}
	
}
