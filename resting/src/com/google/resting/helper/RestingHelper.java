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

package com.google.resting.helper;

import static com.google.resting.method.delete.DeleteHelper.delete;
import static com.google.resting.method.get.GetHelper.get;
import static com.google.resting.method.post.PostHelper.post;
import static com.google.resting.method.put.PutHelper.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;

import test.com.google.resting.vo.SampleFeed;

import com.google.resting.component.Alias;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.Verb;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONAlias;
import com.google.resting.transform.TransformationType;
import com.google.resting.transform.impl.JSONTransformer;
import com.google.resting.transform.impl.XMLTransformer;
import com.google.resting.transform.impl.YAMLTransformer;
import com.google.resting.transform.impl.atom.AtomTransformer;
/**
 * Helper class for Resting.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public final class RestingHelper {

	public final static<T> List<T> executeAndTransform(String url, int port, RequestParams requestParams, Verb verb, TransformationType transformationType, Class<T> targetType, Alias alias, EncodingTypes encoding, List<Header> additionalHeaders){
		ServiceResponse serviceResponse=getServiceResponse(url, port, requestParams, verb, encoding, additionalHeaders);
		
		List<T> results=new ArrayList<T>();
		final long startTime=System.currentTimeMillis();
		if(transformationType==TransformationType.JSON){
			JSONTransformer<T> transformer=new JSONTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
		}//JSON
		
		if(transformationType==TransformationType.XML){
			XMLTransformer<T> transformer=new XMLTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
			
		}//XML
		if (transformationType == TransformationType.YAML) {
			YAMLTransformer<T> transformer = new YAMLTransformer<T>();
			results = transformer.getEntityList(serviceResponse, targetType,
					alias);
		}// YAML
		
		if (transformationType == TransformationType.ATOM) {
			AtomTransformer<T> transformer = new AtomTransformer<T>();
			results = transformer.getEntityList(serviceResponse.getResponseString(), targetType,
					alias);
		}// ATOM	
		final long endTime=System.currentTimeMillis();
		System.out.println( "Time taken in transformation : "+ (endTime - startTime) + " ms.");
		
		return results;
	}//executeAndTransform
	
	public final static Map<String, List> executeAndTransform(String url, int port, RequestParams requestParams, Verb verb, TransformationType transformationType, JSONAlias alias, EncodingTypes encoding, List<Header> additionalHeaders){
		ServiceResponse serviceResponse=getServiceResponse(url, port, requestParams, verb, encoding, additionalHeaders);
		
		Map<String, List> results=null;
		
		if(transformationType==TransformationType.JSON){
			JSONTransformer<Object> transformer=new JSONTransformer<Object>();
			final long startTime=System.currentTimeMillis();
			results=transformer.getEntityLists(serviceResponse,  alias);
			final long endTime=System.currentTimeMillis();
			System.out.println( "Time taken in transformation : "+ (endTime - startTime) + " ms.");
		}//JSON
		
		//Not needed for XML
		
		
		return results;
	}//executeAndTransform
	
	private static ServiceResponse getServiceResponse(String url, int port, RequestParams requestParams, Verb verb, EncodingTypes encoding, List<Header> additionalHeaders){
		ServiceResponse serviceResponse=null;
		if(verb==Verb.GET)
			serviceResponse=get(url, port,requestParams, encoding, additionalHeaders);
		else if (verb == Verb.DELETE)
			serviceResponse=delete(url, port,requestParams, encoding, additionalHeaders);
		else if (verb==Verb.POST)
			serviceResponse=post(url, port, encoding, requestParams, additionalHeaders);
		else if (verb==Verb.PUT)
			serviceResponse=put(url, encoding, port,requestParams, additionalHeaders);
		return serviceResponse;
	}//getServiceResponse
}
