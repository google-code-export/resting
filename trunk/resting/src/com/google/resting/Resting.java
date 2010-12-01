package com.google.resting;

import java.util.List;

import com.google.resting.component.OperationType;
import com.google.resting.component.RequestParams;
import com.google.resting.component.impl.Alias;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.helper.RestingHelper;
import com.google.resting.transform.TransformationType;
/**
 * This is the main class for using resting. 
 * 
 *<p> Here is an example of how Resting can be used for a simple class: </p>
 *
 * Suppose your rest URL is http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA 
 *
 *<p>The resting code can be used in these three ways: </p>
 *
 *<p> Option 1 (Easy and fast): Read the entire HTTP response as a String
 *<pre>
 * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA");
 * System.out.println(response.toString());
 *</pre>
 *</p>
 *<p> Option 2: Create your custom JAVA objects from REST response.
 *<pre>
 * Alias alias=new Alias().add("Result", Result.class).add("ResultSet", ResultSet.class); //Create an alias object which will help transforming each embedded object from XML to Java.
 * List<ResultSet> resultset=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA", ResultSet.class, alias);
 * System.out.println(resultset.toString());
 *</pre>
 *</p>
 *<p> Option 3 (Recommended for JSON/complex encoding issue): Create a RequestParams object. Do the rest in either way (Option #1 or Option #2). You can even implement your own RequestParams. 
 *<pre>
 * RequestParams params = new BasicRequestParams(); 
 * params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");
 * params.add("state", "CA");
 * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",params);
 * System.out.println(response.toString());
 *</pre>
 *</p>
 *

 *
 *For more examples, please refer the upcoming Examples section.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public final class Resting {
	
	/**
	 * Executes HTTP GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param url URI of the REST endpoint
	 * @param requestParams Collection of parameters for REST request
	 * 
	 * @return ServiceResponse object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public static ServiceResponse get(String uri){
		return RestingHelper.execute(uri, OperationType.GET);
	}
	/**
	 * Executes HTTP POST request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param url URI of the REST endpoint
	 * @param requestParams Collection of parameters for REST request
	 * 
	 * @return ServiceResponse object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public static ServiceResponse post(String uri){
		return RestingHelper.execute(uri, OperationType.POST);
	}
	/**
	 * Executes HTTP PUT request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param url URI of the REST endpoint
	 * @param requestParams Collection of parameters for REST request
	 * 
	 * @return ServiceResponse object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	public static ServiceResponse put(String uri){
		return RestingHelper.execute(uri, OperationType.PUT);
	}
	/**
	 * Executes HTTP DELETE request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code.
	 * 
	 * @param url URI of the REST endpoint
	 * @param requestParams Collection of parameters for REST request
	 * 
	 * @return ServiceResponse object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public static ServiceResponse delete(String uri){
		return RestingHelper.execute(uri, OperationType.DELETE);
	}
	/**
	 * Executes HTTP GET request and transforms the JSON response into list of target entity.
	 * 
	 * @param <T> Target entity type
	 * @param url URI of the REST endpoint
	 * @param targetType Class of the target type T
	 * @param alias JSON alias for reading the entity from JSON response.
	 * 
	 * @return List of entities of target type T
	 */
	
	public static <T> List<T> getByJSON(String uri, RequestParams requestParams, Class<T> targetType, String alias){
		Alias jsonAlias=new Alias(alias);
		return RestingHelper.executeAndTransform(uri, requestParams, OperationType.GET, TransformationType.JSON, targetType, jsonAlias);
	}//getByJSON
	
	/**
	 * Executes HTTP GET request and transforms the XML response into list of target entity.
	 * 
	 * @param <T> Target entity type
	 * @param url URI of the REST endpoint
	 * @param targetType Class of the target type T
	 * @param alias Alias for reading the entity from XML response.
	 * 
	 * @return List of entities of target type T
	 */

	public static <T> List<T> getByXML(String url, RequestParams requestParams, Class<T> targetType, Alias alias){
		return RestingHelper.executeAndTransform(url, requestParams, OperationType.GET, TransformationType.XML, targetType, alias);
	}//getByXML
	


}//Resting
