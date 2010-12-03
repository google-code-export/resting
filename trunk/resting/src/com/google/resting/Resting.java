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
 *<p> Option 1 (Easy and fast): Pass the entire URI and read the entire HTTP response as a String
 *<pre>
 * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA",80);
 * System.out.println(response); //Print the entire response body.
 *</pre>
 *</p>
  *<p> Option 2 (Recommended for JSON/complex encoding issue): Create a {@link RequestParams} object(&key=value params). Pass it along with the base URI (http://.././ path). You can even implement your own  {@link RequestParams}. 
 *<pre>
 * RequestParams params = new BasicRequestParams(); 
 * params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");
 * params.add("state", "CA");
 * ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",80,params); 
 * System.out.println(response); //Print the entire response body.
 *</pre>
 *</p>
 *<p> Option 3: Create your custom JAVA objects from REST response. This can be done in two ways. 
 *
 *<p> A. For JSON response: 
 *
 * Suppose your JSON response is of the format given below. 
 * 
 * <pre>
 * {
 * "type" : "stationary"
 * ,"product":
 * [
 * {
 * "productId":"12345","productName":"pen"
 * }
 * ,
 * {
 * "productId":"56789","productName":"pencil"
 * }
 * ]
 * }
 * 
 * You wish to retrieve the list of "product" objects. Here, "product" is the JSON alias: 
 * 
 * RequestParams jsonParams = new JSONRequestParams(); //Create request params 
 * jsonParams.add("key", "your_key");
 * List<Product> products=Resting.getByJSON("http://api.zappos.com/Product/12345",80,jsonParams, Product.class, "product");//Get the list of Product objects by passing "product" as alias
 * </pr>
 * </p>
 *<p> B. For XML response:
 *
 *<pre>
 * Alias alias=new Alias().add("Result", Result.class).add("ResultSet", ResultSet.class); //Create an alias object which will help transforming each embedded object from XML to Java.
 * List<ResultSet> resultset=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode", 80,params,ResultSet.class, alias);
 *</pre>
 *</p>
 *
 *For more examples, please refer the upcoming Examples section.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public final class Resting {
	
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param url Entire URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String uri, int port){
		return RestingHelper.execute(uri, port,OperationType.GET);
	}//get
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String baseURI, int port,RequestParams requestParams){
		return RestingHelper.execute(baseURI, port,requestParams,OperationType.GET);
	}//get
	
	/**
	 * Executes HTTP/HTTPS POST request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param url URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String uri, int port){
		return RestingHelper.execute(uri, port,OperationType.POST);
	}//post
	
	/**
	 * Executes HTTP/HTTPS POST request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams){
		return RestingHelper.execute(baseURI, port, requestParams,OperationType.POST);
	}//post
	
	/**
	 * Executes HTTP/HTTPS PUT request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param url URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	public final static ServiceResponse put(String uri, int port){
		return RestingHelper.execute(uri,port, OperationType.PUT);
	}//put
	
	/**
	 * Executes HTTP/HTTPS PUT request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	public final static ServiceResponse put(String baseURI, int port, RequestParams requestParams){
		return RestingHelper.execute(baseURI,port, requestParams, OperationType.PUT);
	}//put
	
	/**
	 * Executes HTTP/HTTPS DELETE request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code.
	 * 
	 * @param uri URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse delete(String uri, int port){
		return RestingHelper.execute(uri, port,OperationType.DELETE);
	}//delete
	
	/**
	 * Executes HTTP/HTTPS DELETE request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code.
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse delete(String baseURI, int port, RequestParams requestParams){
		return RestingHelper.execute(baseURI, port, requestParams, OperationType.DELETE);
	}//delete
	
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into list of target entity.
	 * 
	 * @param <T> Target entity type
	 * @param url Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param targetType Class of the target type T
	 * @param alias JSON alias for reading the entity from JSON response.
	 * 
	 * @return List of entities of target type T
	 */
	
	public final static <T> List<T> getByJSON(String baseURI, int port, RequestParams requestParams, Class<T> targetType, String alias){
		Alias jsonAlias=new Alias(alias);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, OperationType.GET, TransformationType.JSON, targetType, jsonAlias);
	}//getByJSON
	
	/**
	 * Executes HTTP/HTTPS GET request and transforms the XML response into list of target entity.
	 * 
	 * @param <T> Target entity type
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param targetType Class of the target type T
	 * @param alias Alias for reading the entity from XML response.
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> getByXML(String baseURI, int port, RequestParams requestParams, Class<T> targetType, Alias alias){
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, OperationType.GET, TransformationType.XML, targetType, alias);
	}//getByXML
	


}//Resting
