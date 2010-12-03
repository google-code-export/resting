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

package com.google.resting.rest.util.oauth;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import com.google.resting.component.impl.NameValueEntity;



/**
 * This class contains all the utilities required to create the encoded URL string
 * 
 * @author sujata.de
 * @since resting 0.1
 */

public class URLUtil {
	/**
	 * Turns a list of key value params into a form-url-encoded string (key=value&key2=value2)
	 * 
	 * @param inputParams List of input parameters
	 *           
	 * @return Form-url-encoded string
	 */
	protected static String getFormURLEncodeParamList(List<NameValueEntity> inputParams) {
		int length=inputParams.size();
		return (length <= 0) ? RequestConstants.EMPTY_STRING : formUrlEncode(inputParams, length);
	}

	private static String formUrlEncode(List<NameValueEntity> inputParams, int length) {
		StringBuffer encodedString = new StringBuffer(length * 20);
		for (NameValueEntity inputParam : inputParams) {
			if (encodedString.length() > 0) {
				encodedString.append(RequestConstants.PARAM_SEPARATOR);
			}
			encodedString.append(percentEncode(inputParam.getName())).append(RequestConstants.PAIR_SEPARATOR).append(percentEncode(inputParam.getValue()));
		}
		return encodedString.toString();
	}

	/**
	 * Percent encodes a string
	 * 
	 * @param Plain string
	 * @return Percent encoded string
	 */
	protected static String percentEncode(String plainString) {
		try {
			return URLEncoder.encode(plainString, RequestConstants.UTF8).replaceAll("\\+", "%20");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return null;
	}

	/**
	 * Percent decodes a string
	 * 
	 * @param Percent encoded string
	 * @return Plain string
	 */
	protected static String percentDecode(String encodedString) {
		try {
			return URLDecoder.decode(encodedString, RequestConstants.UTF8);
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}
		return null;
	}
}
