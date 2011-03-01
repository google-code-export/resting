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

import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.http.message.BasicNameValuePair;

import com.google.resting.component.RequestParams;
import com.google.resting.json.JSONArray;
import com.google.resting.json.JSONException;
import com.google.resting.json.JSONObject;

/**
 * Implementation of the collection of JSON request parameters in the REST request.
 * 
 * @author sujata.de
 * @since resting 0.1
 * 
 */

public class JSONRequestParams extends RequestParams {

	public JSONRequestParams() {
		super();
	}// JSONRequestParams

	/**
	 * To add input params in the format
	 * &valueArrayKey={valueArray[0],valueArray[1]...}
	 * 
	 * @param valueArrayKey
	 * @param valueArray
	 */
	public void add(String valueArrayKey, String[] valueArray) {
		JSONArray jsonArray = new JSONArray();
		for (String value : valueArray) {
			jsonArray.put(value);
		}
		queryParams
				.add(new BasicNameValuePair(valueArrayKey, jsonArray.toString()));
	}// add

	/**
	 * To add input params in the format
	 * &key=[valueArrayKey={valueArray[0],valueArray[1]...}]
	 * 
	 * @param key
	 * @param valueArrayKey
	 * @param valueArray
	 */
	public void add(String key, String valueArrayKey, String[] valueArray) {

		JSONObject jsonObject = null;
		JSONArray jsonArray = new JSONArray();
		if (valueArray.length == 1) {
			jsonObject = new JSONObject();
			try {
				jsonObject.put(valueArrayKey, valueArray[0]);
			} catch (JSONException e) {
				// ErrorHandler.throwServiceException(e);
			}
		} else {

			for (String value : valueArray) {
				jsonArray.put(value);
			}
			try {
				jsonObject = new JSONObject();
				jsonObject.put(valueArrayKey, jsonArray);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}// if

		queryParams.add(new BasicNameValuePair(key, jsonObject.toString()));
	}// add

	/**
	 * To add multidimensional arrays of input params in the format:
	 * &key={values(1).key:[values(1).value[0],values(1).value[1],
	 * values(2).key:[values(2).value[0],values(2).value[1]} Ex.
	 * &filters={"colorFacet":["Blue", "Black"],"size":["13", "12"]}
	 * 
	 * @param key
	 * @param values
	 */
	public void add(String key, Map<String, String[]> values) {
		String[] valueElements;
		JSONArray jsonArray;
		JSONObject jsonObject = null;
		int size = values.size();
		StringBuilder result = new StringBuilder("");
		int i = 1;
        Set<Entry<String, String[]>> entrySet=values.entrySet();
		for (Map.Entry<String, String[]> value : entrySet) {
			valueElements = value.getValue();
			jsonArray = new JSONArray();
			for (String valueElement : valueElements) {
				jsonArray.put(valueElement);
			}// for
			try {
				jsonObject = new JSONObject().put(value.getKey(), jsonArray
						.toString());
			} catch (JSONException e) {
				// ErrorHandler.throwServiceException(e);
			}
			String str = jsonObject.toString();
			str = str.substring(1, str.length() - 1)
							.replaceAll("\"\\[", "[")
							.replaceAll("\\]\"", "]")
							.replaceAll("\\\\", "");

			if (i < size)
				result.append(str).append(",");
			else
				result.append(str).append("}");

			i++;

		}// for

	//	System.out.println("The final string is " + result);
		queryParams.add(new BasicNameValuePair(key, result.toString()));
	}// add

	@Override
	public void add(String key, String value) {
		queryParams.add(new BasicNameValuePair(key, value));

	}//add

}// JSONRequestParams
