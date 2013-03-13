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
package com.google.resting.method.delete;

import java.util.List;

import org.apache.http.Header;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.URLContext;
import com.google.resting.rest.client.HttpContext;
import com.google.resting.serviceaccessor.impl.ServiceAccessor;
/**
 * Helper class for HTTP DELETE operation
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public class DeleteHelper {
	public final static ServiceResponse delete(String url, int port, RequestParams requestParams, EncodingTypes encoding, HttpContext httpContext){
		return delete(url,port,requestParams, encoding, null,httpContext);	
	}//delete

	public final static ServiceResponse delete(String url, int port, RequestParams requestParams, EncodingTypes encoding,  List<Header> inputHeaders, HttpContext httpContext){
		URLContext urlContext=new URLContext(url,port);
		ServiceContext serviceContext= new DeleteServiceContext(urlContext,requestParams, encoding, inputHeaders,httpContext);
		return ServiceAccessor.access(serviceContext);	
	}//delete	
}//DeleteHelper
