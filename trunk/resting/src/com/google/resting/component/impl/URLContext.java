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
		constructContextPath(restUrl);
		this.port=80;
	}	
	public URLContext(String restUrl, int port){
		constructContextPath(restUrl);
		this.port=port;
	}
	
	private void constructContextPath(String restUrl){
		this.targetDomain=StringUtils.substringBetween(restUrl, URL_SEPARATOR, SEPARATOR);
		this.contextPath=StringUtils.substringAfter(restUrl,targetDomain );
		if(restUrl.startsWith(RequestConstants.HTTPS))
			this.isSecureInvocation=true;
		removeEnd();
		System.out.println("url is "+targetDomain);
		System.out.println("context path is "+contextPath);		
	}
	
	private void removeEnd(){
		this.contextPath=StringUtils.stripEnd(contextPath, ALL_SEPARATORS);
	}


	public URLContext addContextPathElement(String contextPathElement){
		this.contextPath=this.contextPath+SEPARATOR+contextPathElement;
		return this;
	}

	public String getContextPath(){
		assert this.contextPath!=null:"REST context path should not be null";
		return this.contextPath;
	}
	
	public String getTargetDomain() {
		return targetDomain;
	}

	public int getPort() {
		return port;
	}
	
	public boolean isSecureInvocation(){
		return isSecureInvocation;
	}

}