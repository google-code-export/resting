package com.google.resting.helper;

import java.util.List;

import com.google.resting.component.OperationType;
import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.Alias;
import com.google.resting.component.impl.GenericServiceContext;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.URLContext;
import com.google.resting.serviceaccessor.impl.ServiceAccessor;
import com.google.resting.transform.TransformationType;
import com.google.resting.transform.impl.JSONTransformer;
import com.google.resting.transform.impl.XMLTransformer;
/**
 * Helper class for Resting.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public final class RestingHelper {
	
	public final static ServiceResponse execute(String url, RequestParams requestParams, OperationType operationType){
		URLContext urlContext=new URLContext(url);
		ServiceContext serviceContext= new GenericServiceContext(urlContext,requestParams,operationType);
		ServiceAccessor serviceAccessor=new ServiceAccessor(serviceContext);
		serviceAccessor.access();
		ServiceResponse serviceResponse=serviceAccessor.getServiceResponse();
		return serviceResponse;
	}//execute
	
	public final static ServiceResponse execute(String url, OperationType operationType){
		return execute(url,null, operationType);
	}//execute
	
	public final static<T> List<T> executeAndTransform(String url, RequestParams requestParams, OperationType operationType, TransformationType transformationType, Class<T> targetType, Alias alias){
		ServiceResponse serviceResponse=execute(url, requestParams, operationType);
		List<T> results=null;
		if(transformationType==TransformationType.JSON){
			JSONTransformer<T> transformer=new JSONTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
		}//JSON
		
		if(transformationType==TransformationType.XML){
			XMLTransformer<T> transformer=new XMLTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
			
		}//XML
		
		return results;
	}//execute

}
