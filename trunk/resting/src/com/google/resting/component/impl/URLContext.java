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

package com.google.resting.component.impl;

import org.apache.commons.lang.StringUtils;

import com.google.resting.rest.util.oauth.RequestConstants;

/**
 * This class encapsulates the base URI information for REST end point.
 * 
 * @author sujata.de
 * @since resting 0.1
 */
public class URLContext{
	private String contextPath=null;
	private String targetDomain=null;
	private int port=0;
	private static final String SEPARATOR="/";
	private static final String URL_SEPARATOR="//";
	private static final String ALL_SEPARATORS="/?:*";
	private boolean isSecureInvocation=false;

	public URLContext(String restUrl){
		new URLContext(restUrl,80);
	}//URLContext
	
	public URLContext(String restUrl, int port){
		constructContextPath(restUrl);
		this.port=port;
	}//URLContext
	
	private void constructContextPath(String restUrl){
		this.targetDomain=StringUtils.substringBetween(restUrl, URL_SEPARATOR, SEPARATOR);
		this.contextPath=StringUtils.substringAfter(restUrl,targetDomain );
		if(restUrl.startsWith(RequestConstants.HTTPS))
			this.isSecureInvocation=true;
		removeEnd();
	//	System.out.println("url is "+targetDomain);
	//	System.out.println("context path is "+contextPath);		
	}//constructContextPath
	
	private void removeEnd(){
		this.contextPath=StringUtils.stripEnd(contextPath, ALL_SEPARATORS);
	}//removeEnd

	/**
	 * Add context path element to URLContext.
	 * 
	 * @param contextPathElement
	 * @return this
	 */
	public URLContext addContextPathElement(String contextPathElement){
		this.contextPath=this.contextPath+SEPARATOR+contextPathElement;
		return this;
	}//addContextPathElement
	
	/**
	 * Get the context path
	 * 
	 * @return Context path
	 */

	public String getContextPath(){
		assert this.contextPath!=null:"REST context path should not be null";
		return this.contextPath;
	}//getContextPath
	
	/**
	 * Get domain of the REST end point
	 * 
	 * @return target domain
	 */
	public String getTargetDomain() {
		return targetDomain;
	}//getTargetDomain
	
	/**
	 * Get the port of the REST endpoint
	 * @return
	 */

	public int getPort() {
		return port;
	}//getPort
	
	/**
	 * Returns if this is https invocation.
	 * 
	 * @return true/false
	 */
	
	public boolean isSecureInvocation(){
		return isSecureInvocation;
	}//isSecureInvocation

}//URLContext