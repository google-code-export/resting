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
package com.google.resting.method.put;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.entity.StringEntity;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.Verb;
import com.google.resting.component.impl.URLContext;

/**
 * Implementation of ServiceContext for HTTP PUT operation.
 * 
 * @author sujata.de
 * @since resting 0.2
 */
public class PutServiceContext extends ServiceContext {
	
	private List<NameValuePair> inputParams=null;
	private String path=null;
	private String contextPathElement=null;
	private HttpEntity httpEntity=null;
	
	public PutServiceContext(URLContext urlContext, RequestParams requestParams, EncodingTypes encoding, List<Header> inputHeaders ) {
		super(urlContext, requestParams, Verb.PUT, encoding, inputHeaders );
		this.contextPathElement=urlContext.getContextPath();
		this.path=this.contextPathElement;
		if(requestParams !=null){
			this.inputParams=requestParams.getRequestParams();
			this.httpEntity=setFormEntity(this.inputParams);
		}
	//	System.out.println( "The path is "+path);		
	}//PutServiceContext
	
	public PutServiceContext(URLContext urlContext, String message, EncodingTypes encoding, List<Header> inputHeaders ) {
		super(urlContext, null, Verb.PUT,encoding, inputHeaders);
		this.contextPathElement=urlContext.getContextPath();
		this.path=this.contextPathElement;
		this.httpEntity=setMessageEntity(message, encoding.getName());
	//	System.out.println( "The path is "+path);		
	}//PutServiceContext	

	public PutServiceContext(URLContext urlContext, File file, EncodingTypes encoding, boolean isBinary, List<Header> inputHeaders ) {
		super(urlContext, null, Verb.PUT,encoding, inputHeaders);
		this.contextPathElement=urlContext.getContextPath();
		this.path=this.contextPathElement;
		//this.httpEntity=setMessageEntity(message, encoding);
	//	System.out.println( "The path is "+path);		
	}//PutServiceContext		
	
	private HttpEntity setMessageEntity(String message, String encoding){
		StringEntity entity =null;
		try {
			entity = new StringEntity(message,  "text/plain; charset=\""+encoding+"\"");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entity;		
	}
	
	private HttpEntity setFormEntity(List<NameValuePair> inputParams){
		UrlEncodedFormEntity entity=null;
		try {
			entity = new UrlEncodedFormEntity(inputParams, getCharset().getName());
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return entity;
		
	}
	
	private HttpEntity setFileEntity(String filename, boolean isBinary){
		return null;
	}
	
	@Override
	public String getContextPathElement() {
		return contextPathElement;
	}//getContextPathElement

	@Override
	public HttpEntity getHttpEntity() {
		return httpEntity;
	}//getHttpEntity

	@Override
	public List<NameValuePair> getInputParams() {
		return inputParams;
	}//getInputParams

	@Override
	public String getPath() {
		return path;
	}//getPath

}//PutServiceContext
