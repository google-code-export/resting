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
package com.google.resting.method.post;

import java.io.File;
import java.util.List;

import org.apache.http.Header;

import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.URLContext;
import com.google.resting.serviceaccessor.impl.ServiceAccessor;
/**
 * Helper class for HTTP PUT operation
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public class PostHelper {
	public final static ServiceResponse post(String url, int port, String encoding, RequestParams requestParams, List<Header> additionalHeaders){
		URLContext urlContext=new URLContext(url,port);
		ServiceContext serviceContext= new PostServiceContext(urlContext,requestParams, encoding, additionalHeaders);
		return ServiceAccessor.access(serviceContext);
	}//post
	
	public final static ServiceResponse post(String messageToPost, String encoding, String url, int port, List<Header> additionalHeaders){
		URLContext urlContext=new URLContext(url,port);
		ServiceContext serviceContext= new PostServiceContext(urlContext,messageToPost,encoding, additionalHeaders);
		return ServiceAccessor.access(serviceContext);
	}//post

	public final static ServiceResponse post(String url, int port, File file, String encoding, boolean isBinaryFile, List<Header> additionalHeaders){
		URLContext urlContext=new URLContext(url,port);
		ServiceContext serviceContext= new PostServiceContext(urlContext,file, encoding, isBinaryFile, additionalHeaders);
		return ServiceAccessor.access(serviceContext);
	}//post
}//PostHelper
