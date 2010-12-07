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

import java.util.ArrayList;
import java.util.List;

import com.google.resting.component.Verb;
import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.Alias;
import com.google.resting.component.impl.GenericServiceContext;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.URLContext;
import com.google.resting.serviceaccessor.impl.ServiceAccessor;
import com.google.resting.transform.TransformationType;
import com.google.resting.transform.impl.JSONTransformer;
import com.google.resting.transform.impl.XMLTransformer;
/**
 * Helper class for Resting.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public final class RestingHelper {
	
	public final static ServiceResponse execute(String url, int port, RequestParams requestParams, Verb verb){
		URLContext urlContext=new URLContext(url,port);
		ServiceContext serviceContext= new GenericServiceContext(urlContext,requestParams,verb);
		return ServiceAccessor.access(serviceContext);
	}//execute	
	
	public final static ServiceResponse execute(String url,int port, Verb operationType){
		return execute(url,port,null, operationType);
	}//execute
	
	public final static<T> List<T> executeAndTransform(String url, int port, RequestParams requestParams, Verb verb, TransformationType transformationType, Class<T> targetType, Alias alias){
		ServiceResponse serviceResponse=execute(url, port,requestParams, verb);
		List<T> results=new ArrayList<T>();
		if(transformationType==TransformationType.JSON){
			JSONTransformer<T> transformer=new JSONTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
		}//JSON
		
		if(transformationType==TransformationType.XML){
			XMLTransformer<T> transformer=new XMLTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
			
		}//XML
		
		return results;
	}//execute

}
