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

import org.apache.http.message.BasicNameValuePair;

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
		queryParams.add(new BasicNameValuePair(key, value));
	}//add

}//BasicRequestParams
