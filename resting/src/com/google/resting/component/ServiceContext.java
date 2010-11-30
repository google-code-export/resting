package com.google.resting.component;

import java.util.List;

import com.google.resting.component.impl.NameValueEntity;
import com.google.resting.component.impl.URLContext;
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
	private OperationType requestType=null;
	
	protected ServiceContext(URLContext urlContext, AbstractRequestParams queryParams, OperationType operationType){
		this.targetDomain=urlContext.getTargetDomain();
		this.port=urlContext.getPort();
		this.isSecureInvocation=urlContext.isSecureInvocation();
		this.requestType=operationType;
	}
	public abstract String getPath();
	
	public abstract String getContextPathElement();
	
	public abstract List<NameValueEntity> getInputParams();
	
	public OperationType getRequestType(){
		assert requestType!=null:"Request type should not be null";
		return requestType;
	}
	
	public boolean isSecureInvocation(){
		return isSecureInvocation;
	}
	
	public String getTargetDomain(){
		assert targetDomain!=null:"Base url should not be null";
		return targetDomain;
	}
	
	public int getPort(){
		assert port!=0:"Port is not set";
		return port;
	}
}
