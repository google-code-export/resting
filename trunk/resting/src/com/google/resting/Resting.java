package com.google.resting;

import java.util.List;
import java.util.Map;

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
 * This is the main class for using resting. 
 * 
 *<p> Here is an example of how Resting can be used for a simple class: </p>
 *
 * Suppose your rest URL is http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA 
 *
 *<p>The resting code can be in both of these ways: </p>
 *
 *<p> Option 1: 
 *<pre>
  * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA");
 *</pre>
 *</p>
 *
 *<p> Option 2:
 *<pre>
 * RequestParams params=new BasicRequestParams();
 * params.add("appid","YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");
 * params.add("state","CA");
 * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",params);
 * 
 * </pre>
 * </p>
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public final class Resting {
	
	/**
	 * Executes GET method and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers.
	 * 
	 * @param url Base uri of the REST endpoint
	 * @param requestParams Collection of parameters for REST request
	 * 
	 * @return ServiceResponse object containing the entire REST request as a String, the HTTP status code and the response headers.
	 */
	public static ServiceResponse get(String url, RequestParams requestParams){
		URLContext urlContext=new URLContext(url);
		ServiceContext serviceContext= new GenericServiceContext(urlContext,requestParams,OperationType.GET);
		ServiceAccessor serviceAccessor=new ServiceAccessor(serviceContext);
		serviceAccessor.access();
		ServiceResponse serviceResponse=serviceAccessor.getServiceResponse();
		return serviceResponse;
	}//get
	
	public static ServiceResponse get(String url){
		return get(url,null);
	}//get
	
	public static<T> List<T> get(String url, RequestParams requestParams, TransformationType transformationType, Class<T> targetType, Alias alias){
		ServiceResponse serviceResponse=get(url, requestParams);
		List<T> results=null;
		if(transformationType==TransformationType.JSON){
			JSONTransformer<T> transformer=new JSONTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
		}
		if(transformationType==TransformationType.XML){
			XMLTransformer<T> transformer=new XMLTransformer<T>();
			results=transformer.getEntityList(serviceResponse, targetType, alias);
			
		}
		
		return results;
	}//get

}//Resting
