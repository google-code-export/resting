package com.google.resting.component.impl;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.google.resting.component.OperationType;
import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.URLContext;
import com.google.resting.rest.util.oauth.RequestConstants;
/**
 * Generic implementation of ServiceContext
 * 
 * @author sujata.de
 * @since resting 0.1
 */

public class GenericServiceContext extends ServiceContext {
	
	private List<NameValueEntity> inputParams=null;
	private String path=null;
	private String contextPathElement=null;

	public GenericServiceContext(URLContext urlContext, RequestParams requestParams, OperationType operationType){
		super(urlContext,requestParams, operationType);
		this.contextPathElement=urlContext.getContextPath();
		if(requestParams !=null)	this.inputParams=requestParams.getRequestParams();
		this.path=this.contextPathElement+getParamPathElement();
		System.out.println( "The path is "+path);
	}//GenericServiceContext
	
	public String getContextPathElement(){
		return contextPathElement;
	}//getContextPathElement
	
	public List<NameValueEntity> getInputParams(){
		return inputParams;
	}//getInputParams
	
	public String getPath(){
		assert path!=null:"Path in service context should not be null normally.";
		return path;
	}//getPath

	private String getParamPathElement(){
		if(inputParams == null) return "";
		StringBuffer combinedParams = new StringBuffer("");
		
			//sort the list here - specific to zappos, hence will be moved into provider specific implementation
/*			Collections.sort(inputParams, new Comparator<NameValuePair>(){

				@Override
				public int compare(NameValuePair object1, NameValuePair object2) {
					return object1.getName().compareTo(object2.getName());
				}
				
			});*/
		
		int i=0;
			for (NameValueEntity inputParam : inputParams){
				
				try {
					if (i > 0)
						combinedParams.append("&").append(inputParam.getName()).append("=").append(URLEncoder.encode(inputParam.getValue(), RequestConstants.UTF8));
					else
						combinedParams.append(inputParam.getName()).append("=").append(URLEncoder.encode(inputParam.getValue(), RequestConstants.UTF8));
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}


				i=1;
			}//for
		if(i==1) 
			return "?"+combinedParams.toString();
		else
			return combinedParams.toString();
	}//getParamPathElement

}//GenericServiceContext
