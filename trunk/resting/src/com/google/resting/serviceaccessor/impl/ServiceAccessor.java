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

package com.google.resting.serviceaccessor.impl;


import static com.google.resting.rest.util.oauth.SignatureUtil.getSignature;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;

import com.google.resting.component.ServiceContext;
import com.google.resting.component.Verb;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.rest.client.impl.RESTClient;
import com.google.resting.serviceaccessor.Accessor;

/**
 * Implementation of the service accessor layer. 
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public final class ServiceAccessor implements Accessor{
	private static final String AMPERSAND_SEPARATED_STRING = "&%s=%s";
	private static final String SIGNATURE = "signature";

	/**
	 * Executes REST request
	 */
	public static ServiceResponse access(ServiceContext serviceContext){
		boolean isSecureInvocation=serviceContext.isSecureInvocation();
		ServiceResponse serviceResponse=null;
		if(isSecureInvocation)
			serviceResponse= RESTClient.secureInvoke(serviceContext );
		else
			serviceResponse= RESTClient.invoke(serviceContext );

		//if(validate(serviceResponse))
		//Handle validation properly
		//	System.out.println("[Resting::ServiceAccessor] Service response validation passed");
		return serviceResponse;

	}//access

	
	/**
	 * Validates REST response
	 * 
	 * @return boolean - true/false
	 */
	private static boolean validate(ServiceResponse serviceResponse){
		assert serviceResponse!=null:"Service response should not be null";
		
		//TODO Validation logic yet to be added. The implementation is incomplete here.
		if(serviceResponse.getStatusCode() == HttpStatus.SC_OK) 
			return true;

		return false;
	}//validate

	
	public static void signRequest(String keyString, ServiceContext serviceContext){
		boolean isSecureInvocation=serviceContext.isSecureInvocation();
		String targetDomain=serviceContext.getTargetDomain();
		String path=serviceContext.getPath();
		Verb verb=serviceContext.getVerb();
		String contextPathElement=serviceContext.getContextPathElement();
		String encoding=serviceContext.getCharset().getName();
		List<NameValuePair> inputParams=serviceContext.getInputParams();
		try {
			path=path+String.format(AMPERSAND_SEPARATED_STRING,SIGNATURE,getSignature(keyString,targetDomain,verb, isSecureInvocation, contextPathElement, inputParams, encoding));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}//signRequest

}//ServiceAccessor
