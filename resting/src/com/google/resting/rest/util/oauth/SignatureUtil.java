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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.NameValuePair;

import com.google.resting.component.Verb;
/**
 * Utility for signing request as per Oauth 1.0a.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public class SignatureUtil {
	private static final String SEPARATOR="://";

	
	private static String getBaseString(String targetDomain, String requestType, boolean isSecureInvocation, String contextPathElement, List<NameValuePair> inputParams, String messageEncoding){
		String sourceVerb=requestType;
		String sourceUrl=SEPARATOR+targetDomain+contextPathElement;
		if(isSecureInvocation){
			sourceUrl=RequestConstants.HTTPS+sourceUrl;
		}else{
			sourceUrl=RequestConstants.HTTP+sourceUrl;
		}
		System.out.println("Source url is "+sourceUrl);
		return BaseStringExtractorImpl.extract(sourceVerb, sourceUrl,inputParams, messageEncoding );
	}
	
	/**
	 * Sign request.
	 * 
	 * @param keyString Consumer key for request signing.
	 * @param targetDomain Domain of the REST endpoint (Ex. login.yahoo.com)
	 * @param verb Type of REST operation (GET/POST/PUT/DELETE)
	 * @param isSecureInvocation HTTP/HTTPS
	 * @param contextPathElement Path element in the base REST uri (Ex. /weather/india)
	 * @param inputParams Collection of request params for REST request (Ex. city=calcutta )
	 * @return
	 * @throws NoSuchAlgorithmException The exception is thrown if the encryption algorithm is not supported.
	 * @throws InvalidKeyException The exception is thrown if the consumer key is invalid
	 * @throws IllegalStateException 
	 * @throws UnsupportedEncodingException The exception is thrown if the URL encoding is incorrect.
	 */
	public static String getSignature(String keyString, String targetDomain, Verb verb, boolean isSecureInvocation, String contextPathElement, List<NameValuePair> inputParams, String messageEncoding) throws NoSuchAlgorithmException,InvalidKeyException, IllegalStateException, UnsupportedEncodingException{
		
		String baseString=getBaseString(targetDomain,verb.toString(), isSecureInvocation, contextPathElement, inputParams, messageEncoding)
							.replace("+", "%20")
							.replace("*", "%2A")
							.replace("%7E", "~");
		
		System.out.println("Base string is "+baseString);
		Mac mac = Mac.getInstance("HmacSHA1");    
		SecretKeySpec secret = new SecretKeySpec(keyString.getBytes(messageEncoding), mac.getAlgorithm());    
		mac.init(secret);     
		byte[] digest = mac.doFinal(baseString.getBytes(messageEncoding));     
		return new String(Base64.encodeBase64(digest)).replace(RequestConstants.CARRIAGE_RETURN, RequestConstants.EMPTY_STRING); 
	}
}
