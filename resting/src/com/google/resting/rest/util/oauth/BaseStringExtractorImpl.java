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


import java.util.List;

import org.apache.http.NameValuePair;



/**
 * Default implementation of BaseStringExtractor. Conforms to OAuth 1.0a
 * 
 * 
 */
public class BaseStringExtractorImpl {

	private static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";

	public static String extract(String sourceVerb, String sourceUrl,List<NameValuePair> inputParams, String messageEncoding) {
		String verb = URLUtil.percentEncode(sourceVerb,messageEncoding);
		String url = URLUtil.percentEncode(getSanitizedUrl(sourceUrl),messageEncoding);
		String params = getSortedAndEncodedParams(inputParams,messageEncoding);
		return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
	}

	private static String getSortedAndEncodedParams(List<NameValuePair> sourceParams, String messageEncoding) {
		return URLUtil.getFormURLEncodeParamList(sourceParams,messageEncoding);
	}

	private static String getSanitizedUrl(String url) {
		return url.replaceAll("\\?.*", "").replace("\\:\\d{4}", "");
	}

}
