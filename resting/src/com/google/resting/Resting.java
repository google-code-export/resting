package com.google.resting;

import java.util.List;

import com.google.resting.component.OperationType;
import com.google.resting.component.AbstractRequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.GenericServiceContext;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.URLContext;
import com.google.resting.serviceaccessor.impl.ServiceAccessor;
/**
 * This is the main class for using resting. 
 * 
 *<p> Here is an example of how Resting can be used for a simple class:
 *
 *Suppose your rest URL is http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA
 *The resting code will be as follows:
 *
 *<pre>
 * RequestParams params=new RequestParams();
 * params.add("appid","YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");
 * params.add("state","CA");
 * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",params);
 * 
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class Resting {
	
	/**
	 * Executes GET method.
	 * 
	 * @param url Base uri of the REST endpoint
	 * @param requestParams Collection of parameters for REST request
	 * 
	 * @return ServiceResponse object containing the entire REST request as a String.
	 */
	public static ServiceResponse get(String url, AbstractRequestParams requestParams){
		URLContext urlContext=new URLContext(url);
		ServiceContext serviceContext= new GenericServiceContext(urlContext,requestParams,OperationType.GET);
		ServiceAccessor serviceAccessor=new ServiceAccessor(serviceContext);
		serviceAccessor.access();
		ServiceResponse serviceResponse=serviceAccessor.getServiceResponse();
		return serviceResponse;
	}
	
	public static ServiceResponse get(String url){
		return get(url,null);
	}
	
	public static<T> List<T> transformJSON(ServiceResponse serviceResponse){
		return null;
	}

	public static<T> List<T> transformXML(){
		return null;
	}	
}
