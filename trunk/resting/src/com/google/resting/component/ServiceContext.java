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

package com.google.resting.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;

import com.google.resting.component.impl.URLContext;
import com.google.resting.rest.client.HttpContext;
/**
 * Abstract class for encapsulating the entire context of the REST endpoint service invocation. This includes base URI, 
 * MIME type and the type of operations. 
 * MIME types: Only JSON and XML are being supported in the current release.
 * Types of operations: Only GET and POST are being supported in the current release. 
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public abstract class ServiceContext {
	
	private String targetDomain=null;
	private int port=0;
	private boolean isSecureInvocation=false;
	private Verb verb=null;
	private EncodingTypes charset=null;
	private List<Header> headers;
	private HttpContext httpContext=null;

	protected ServiceContext(URLContext urlContext, RequestParams queryParams, Verb verb, EncodingTypes charset,List<Header> inputHeaders, HttpContext httpContext ){
		this.targetDomain=urlContext.getTargetDomain();
		this.port=urlContext.getPort();
		this.isSecureInvocation=urlContext.isSecureInvocation();
		this.verb=verb;
		this.charset=charset;
		this.httpContext=httpContext;
		
		if(inputHeaders !=null){
			this.headers=new ArrayList<Header>();
			this.headers.addAll(inputHeaders);
		}
	}//ServiceContext
	
	public abstract String getPath();
	
	public abstract String getContextPathElement();
	
	public abstract List<NameValuePair> getInputParams();
	
	public List<Header> getHeaders(){
		return headers;
	}//getHeaders
	
	public Verb getVerb(){
		assert verb!=null:"HTTP operation type should not be null";
		return verb;
	}//getVerb
	
	public boolean isSecureInvocation(){
		return isSecureInvocation;
	}//isSecureInvocation
	
	public String getTargetDomain(){
		assert targetDomain!=null:"Base url should not be null";
		return targetDomain;
	}//getTargetDomain
	
	public int getPort(){
		assert port!=0:"Port is not set";
		return port;
	}//getPort
	
	public HttpEntity getHttpEntity(){
		return null;
	}//getHttpEntity
	
	public EncodingTypes getCharset(){
		return charset;
	}//getCharset
	
	public HttpContext getHttpContext(){
		return httpContext;
	}//getHttpContext


}//ServiceContext
