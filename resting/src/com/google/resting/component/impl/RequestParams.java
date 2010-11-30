package com.google.resting.component.impl;

import com.google.resting.component.AbstractRequestParams;

public class RequestParams extends AbstractRequestParams {

		/**
		 * To add input params in the format &key=value
		 * 
		 * @param key
		 * @param value
		 */
		@Override
		public void add(String key, String value) {
			queryParams.add(new NameValueEntity(key, value));
		}

}
