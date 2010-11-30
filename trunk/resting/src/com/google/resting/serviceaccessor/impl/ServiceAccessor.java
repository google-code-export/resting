package com.google.resting.serviceaccessor.impl;


import static com.google.resting.rest.util.oauth.SignatureUtil.getSignature;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.http.HttpStatus;

import com.google.resting.component.OperationType;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.NameValueEntity;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.rest.client.RESTClient;
import com.google.resting.serviceaccessor.Accessor;

/**
 * Service accessor layer. Service accessor 
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class ServiceAccessor implements Accessor{
	private static final String AMPERSAND_SEPARATED_STRING = "&%s=%s";
	private static final String SIGNATURE = "signature";
	protected String path=null;
	protected String targetDomain=null;
	private ServiceResponse serviceResponse=null;
	private boolean isSecureInvocation=false;
	private OperationType operationType=null; 
	private String contextPathElement=null;
	private List<NameValueEntity> inputParams=null;
	private int port=0;

	public ServiceAccessor(ServiceContext serviceContext){
		this.targetDomain=serviceContext.getTargetDomain();
		this.port=serviceContext.getPort();
		this.path=serviceContext.getPath();
		this.isSecureInvocation=serviceContext.isSecureInvocation();
		this.operationType=serviceContext.getRequestType();
		this.contextPathElement=serviceContext.getContextPathElement();
		this.inputParams=serviceContext.getInputParams();
	}//ServiceAccessor

	/**
	 * Executes REST request
	 */
	public void access(){
		if(isSecureInvocation)
			this.serviceResponse= RESTClient.secureInvoke(targetDomain, path, operationType, port );
		else
			this.serviceResponse= RESTClient.invoke(targetDomain, path, operationType, port );

		if(validate())
			System.out.println("Validation passed");
	}//access

	
	/**
	 * Validates REST response
	 * 
	 * @return boolean - true/false
	 */
	public boolean validate(){
		assert serviceResponse!=null:"Service response should not be null";
		
		//TODO Validation logic yet to be implemented. The implementation is incomplete here.
		if(serviceResponse.getStatusCode() == HttpStatus.SC_OK) 
			return true;

		return false;
	}//validate

	public ServiceResponse getServiceResponse(){
		assert serviceResponse!=null:"Service response should not be null";
		return serviceResponse;
	}
	
	public void signRequest(String keyString){
		try {
			
			this.path=path+String.format(AMPERSAND_SEPARATED_STRING,SIGNATURE,getSignature(keyString,targetDomain,operationType, isSecureInvocation, contextPathElement, inputParams));
			
		} catch (InvalidKeyException e) {
			//ErrorHandler.throwServiceException(e);
		} catch (NoSuchAlgorithmException e) {
			//ErrorHandler.throwServiceException(e);
		} catch (IllegalStateException e) {
			//ErrorHandler.throwServiceException(e);
		} catch (UnsupportedEncodingException e) {
			//ErrorHandler.throwServiceException(e);
		}
	}

}//ServiceAccessor
