 /*
 * Copyright (C) 2013 Google Code.
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

package com.google.resting.rest.client;

import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
//import org.apache.http.nio.client.HttpAsyncClient;
//import org.apache.http.nio.reactor.IOReactorException;
import org.apache.http.params.HttpParams;

import com.google.resting.component.ServiceContext;
import com.google.resting.component.Verb;
/**
 * Base class for REST client
 * 
 * @author sujata.de
 * @since resting 0.7
 *
 */
public abstract class BaseRESTClient {
	
	protected static HttpRequest buildHttpRequest(ServiceContext serviceContext) {
		
		String path=serviceContext.getPath();
		Verb verb=serviceContext.getVerb();
		HttpEntity httpEntity=serviceContext.getHttpEntity();
		List<Header> headers=serviceContext.getHeaders();
		
		if (verb == Verb.GET) {
			HttpGet httpGet = new HttpGet(path);
			 if(headers!=null){
				 for(Header header:headers)
					 httpGet.addHeader(header);
			 }
			return httpGet;
			
		} else if (verb == Verb.POST) {
			HttpPost httpPost = new HttpPost(path);
			 if (headers != null) {
				for (Header header : headers)
					httpPost.addHeader(header);
			}
			if(httpEntity!=null)
				httpPost.setEntity(httpEntity);
			return httpPost;

		} else if (verb == Verb.DELETE) {
			HttpDelete httpDelete = new HttpDelete(path);
			 if (headers != null) {
				for (Header header : headers)
					httpDelete.addHeader(header);
			}
			return httpDelete;

		} else {
			HttpPut httpPut = new HttpPut(path);
			 if (headers != null) {
				for (Header header : headers)
					httpPut.addHeader(header);
			}
			if(httpEntity!=null)
				httpPut.setEntity(httpEntity);
			return httpPut;
		}//if
	}//buildHttpRequest

	protected static DefaultHttpClient buildHttpClient(ServiceContext serviceContext) {
		DefaultHttpClient httpClient =null;
		HttpParams httpParams=null;
		HttpContext httpContext=serviceContext.getHttpContext();
		if(httpContext!=null)
				httpParams=httpContext.getHttpParams();
		if(httpParams!=null)
			httpClient = new DefaultHttpClient(httpParams);
		else
			httpClient=new DefaultHttpClient();
		
		return httpClient;
	}//buildHttpClient
	
/*	protected static HttpAsyncClient buildHttpAsyncClient(ServiceContext serviceContext) {
		DefaultHttpAsyncClient httpClient =null;
		HttpParams httpParams=null;
		HttpContext httpContext=serviceContext.getHttpContext();
		if(httpContext!=null)
				httpParams=httpContext.getHttpParams();
		try {
			httpClient =new DefaultHttpAsyncClient();
			if(httpParams!=null)
				httpClient.setDefaultHttpParams(httpParams);
		} catch (IOReactorException e) {
			e.printStackTrace();
		}
		return httpClient;
	}//buildHttpAsyncClient
*/
}//BaseRESTClient
