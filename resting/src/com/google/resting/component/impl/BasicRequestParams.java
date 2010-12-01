package com.google.resting.component.impl;

import com.google.resting.component.RequestParams;
/**
 * Basic implementation of RequestParams
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class BasicRequestParams extends RequestParams {

	/**
	 * To add input params in the format &key=value
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void add(String key, String value) {
		queryParams.add(new NameValueEntity(key, value));
	}//add

}//BasicRequestParams
