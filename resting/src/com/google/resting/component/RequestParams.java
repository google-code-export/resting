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

package com.google.resting.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;

import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.json.JSONRequestParams;

/**
 * Abstract class for handling collection query parameters for REST invocation. This class should be extended 
 * for application/technology specific collections. The directly known subclasses available are {@link BasicRequestParams}
 * and {@link JSONRequestParams}.  
 * 
 * @author sujata.de
 * @since resting 0.1
 * 
 */
public abstract class RequestParams {
	
	//
	protected List<NameValuePair> queryParams=null;

	protected RequestParams(){
		queryParams= new ArrayList<NameValuePair>();
	}

	/**
	 * To add input params in the format &key=value
	 * 
	 * @param key
	 * @param value
	 */
	public abstract void add(String name, String value);
	

	public List<NameValuePair> getRequestParams() {
		return queryParams;
	}
	
}
