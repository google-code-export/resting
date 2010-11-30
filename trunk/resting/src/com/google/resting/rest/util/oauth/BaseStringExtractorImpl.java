package com.google.resting.rest.util.oauth;


import java.util.List;


import com.google.resting.component.impl.NameValueEntity;



/**
 * Default implementation of BaseStringExtractor. Conforms to OAuth 1.0a
 * 
 * 
 */
public class BaseStringExtractorImpl {

	private static final String AMPERSAND_SEPARATED_STRING = "%s&%s&%s";

	public static String extract(String sourceVerb, String sourceUrl,List<NameValueEntity> inputParams) {
		String verb = URLUtil.percentEncode(sourceVerb);
		String url = URLUtil.percentEncode(getSanitizedUrl(sourceUrl));
		String params = getSortedAndEncodedParams(inputParams);
		return String.format(AMPERSAND_SEPARATED_STRING, verb, url, params);
	}

	private static String getSortedAndEncodedParams(List<NameValueEntity> sourceParams) {
		return URLUtil.getFormURLEncodeParamList(sourceParams);
	}

	private static String getSanitizedUrl(String url) {
		return url.replaceAll("\\?.*", "").replace("\\:\\d{4}", "");
	}

}
