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

import static com.google.resting.component.EncodingTypes.UTF8;
import static com.google.resting.helper.RestingHelper.executeAndTransform;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;

import com.google.gson.Gson;
import com.google.resting.atom.AtomFeed;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.Verb;
import com.google.resting.component.content.ContentType;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONAlias;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.helper.RestingHelper;
import com.google.resting.method.delete.DeleteHelper;
import com.google.resting.method.get.GetHelper;
import com.google.resting.method.post.PostHelper;
import com.google.resting.method.put.PutHelper;
import com.google.resting.rest.client.HttpContext;
import com.google.resting.transform.TransformationType;


/**
 * This is the main class for using resting. {@code Resting} exposes APIs for invoking REST services and transforming the response into value objects in one step.
 * {@code Resting} assumes many default configurations. For non-default configuration options and advanced operations, use {@code RestingBuilder}.
 * 
 *<p> Here is an example of how Resting can be used for a simple class: </p>
 *
 * Suppose your rest URL is http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true 
 *
 *<p>The resting code can be used in these three ways: </p>
 *
 *<p> Option 1 (Easy and fast): Pass the entire URI and read the entire HTTP response as a String
 *<pre>
 * <code>
 * ServiceResponse response=Resting.get("http://maps.googleapis.com/maps/api/geocode/json?address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=true",80);
 * System.out.println(response); //Print the entire response body.
 * </code>
 *</pre>
 *</p>
  *<p> Option 2 (Recommended for JSON/complex encoding issue): Create a {@link RequestParams} object(&key=value params). Pass it along with the base URI (http://.././ path). You can even implement your own  {@link RequestParams}. 
 *<pre>
 *<code>
 * RequestParams params = new BasicRequestParams(); 
 * params.add("address", "1600+Amphitheatre+Parkway,+Mountain+View,+CA");
 * params.add("sensor", "true");
 * ServiceResponse response=Resting.get("http://maps.googleapis.com/maps/api/geocode/json",80,params); 
 * System.out.println(response); //Print the entire response body.
 * </code>
 *</pre>
 *</p>
 *<p> Option 3: Create your custom JAVA objects from REST response. This can be done in multiple ways depending on your response type. 
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
 * <code>
 * RequestParams jsonParams = new JSONRequestParams(); //Create request params 
 * jsonParams.add("key", "your_key");
 * List<Product> products=Resting.getByJSON("http://api.zappos.com/Product/12345",80,jsonParams, Product.class, "product");//Get the list of Product objects by passing "product" as alias
 * </code>
 * </pr>
 * </p>
 *<p> B. For XML response:
 *
 *<pre>
 *<code>
 * XMLAlias alias=new XMLAlias().add("result", Result.class).add("address_component", AddressComponent.class); //Create an alias object which will help transforming each embedded object from XML to Java.
 * List<Result> result=Resting.getByXML("http://maps.googleapis.com/maps/api/geocode/xml", 80,params,Result.class, alias);
 *</code>
 *</pre>
 *</p>
 *<p> C. For YAML response:
 *
 *<pre>
 *<code>
 * List<Concept> entities = Resting.restByYAML("http://openmind.media.mit.edu/api/en/concept/duck/query.yaml",80, null, Verb.GET, Concept.class, EncodingTypes.UTF8, null);
 *</code>
 *</pre>
 *</p>
 *<p> D. For ATOM response:
 *
 *<pre>
 *<code>
 * List<AtomFeed> l = Resting.restByATOM("http://books.google.com/books/feeds/volumes?q=php", 80, null,Verb.GET, AtomFeed.class,new XMLAlias(),EncodingTypes.UTF8 , null);
 *</code>
 *</pre>
 *</p>
 *For more examples, please refer the upcoming Examples section.
 *For more advanced operations, use RestingBuilder.java
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
	 * @param url Entire URI of the REST endpoint. Port is 80.
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String uri){
		return GetHelper.get(uri, 80, null,UTF8,null);
	}//get
	
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
		return GetHelper.get(uri, port, null,UTF8,null);
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
		return GetHelper.get(baseURI, port,requestParams,UTF8,null);
	}//get
	
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Message encoding in response
	 * @param inputHeaders Additional response headers, as required by the client. 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String baseURI, int port,RequestParams requestParams, EncodingTypes encoding, List<Header> additionalHeaders){
		return GetHelper.get(baseURI, port,requestParams,encoding, additionalHeaders,null);
	}//get
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Message encoding in response
	 * @param inputHeaders Additional response headers, as required by the client. 
	 * @param connectionTimeout Connection timeout in milliseconds
	 * @param socketTimeout Default socket timeout in milliseconds
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String baseURI, int port,RequestParams requestParams, EncodingTypes encoding, List<Header> additionalHeaders, int connectionTimeout, int socketTimeout){
		return GetHelper.get(baseURI, port,requestParams,encoding, additionalHeaders,new HttpContext().setTimeout(connectionTimeout, socketTimeout));
	}//get
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint. Port is 80.
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Message encoding in response
	 * @param inputHeaders Additional response headers, as required by the client. 
	 * @param timeout Connection and socket timeout in milliseconds
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String baseURI, RequestParams requestParams, EncodingTypes encoding, List<Header> additionalHeaders, int timeout){
		return GetHelper.get(baseURI, 80,requestParams,encoding, additionalHeaders,new HttpContext().setTimeout(timeout));
	}//get
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param uri URI of the REST endpoint. 
	 * @param port Port of the REST endpoint.
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Message encoding in response
	 * @param inputHeaders Additional response headers, as required by the client. 
	 * @param proxyHost Host of the proxy server
	 * @param proxyPort Port of the proxy server
	 * @param proxyUser Proxy username. Default is null.
	 * @param proxyPassword. Proxy password. Default is null.
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String uri, int port, RequestParams requestParams, EncodingTypes encoding, List<Header> additionalHeaders,  String proxyHost, int proxyPort, String proxyUser, String proxyPassword){
		return GetHelper.get(uri, port,requestParams,encoding, additionalHeaders,new HttpContext().setProxy(proxyHost, proxyPort, proxyUser, proxyPassword));
	}//get
	/**
	 * Executes HTTP/HTTPS GET request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint. Port is 80.
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Message encoding in response
	 * @param inputHeaders Additional response headers, as required by the client. 
	 * @param httpContext {@code HttpContext} object containing HTTP parameters. To be used to set connection timeout, proxy,  authentication etc.
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse get(String uri, int port, RequestParams requestParams, EncodingTypes encoding, List<Header> additionalHeaders, HttpContext httpContext){
		return GetHelper.get(uri, port,requestParams,encoding, additionalHeaders,httpContext);
	}//get
	/**
	 * Executes HTTP/HTTPS POST request for a POST request with no content in the message body and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port){
		return PostHelper.post(baseURI, port, UTF8,null,null,null);
	}//post
	
	/**
	 * Executes HTTP/HTTPS POST request with HTML form data in the message body and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation. 
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair to be sent in the message body
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams){
		//Post
		return PostHelper.post(baseURI, port, UTF8,requestParams,null,null);
	}//post
	/**
	 * Executes HTTP/HTTPS POST request with message String in the message body and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation. A typical  
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param messageToPost String to be posted 
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port, String messageToPost, EncodingTypes messageEncoding){
		return PostHelper.post(messageToPost, messageEncoding, baseURI, port,null,null);
	}//post	
	/**
	 * Executes HTTP/HTTPS POST request with message String in the message body and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param messageToPost String to be posted 
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param acceptHeaders Additional response headers, as required by the client. 
	 * @param messageContentType ContentType of the message String. The default is "text/plain".
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams, String messageToPost, EncodingTypes messageEncoding,List<Header> acceptHeaders, ContentType messageContentType){
		return PostHelper.post(messageToPost, messageEncoding, baseURI, port,requestParams,acceptHeaders,messageContentType,null);
	}//post	
	/**
	 * Executes HTTP/HTTPS POST request with message String in the message body and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param messageToPost String to be posted 
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param acceptHeaders Additional response headers, as required by the client. 
	 * @param messageContentType ContentType of the message String. The default is "text/plain".
	 * @param proxyHost Host of the proxy server
	 * @param proxyPort Port of the proxy server
	 * @param proxyUser Proxy username. Default is null.
	 * @param proxyPassword. Proxy password. Default is null.
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams, String messageToPost, EncodingTypes messageEncoding,List<Header> acceptHeaders, ContentType messageContentType,String proxyHost, int proxyPort, String proxyUser, String proxyPassword){
		return PostHelper.post(messageToPost, messageEncoding, baseURI, port,requestParams,acceptHeaders,messageContentType,new HttpContext().setProxy(proxyHost, proxyPort, proxyUser, proxyPassword));
	}//post	  
	/**
	 * Executes HTTP/HTTPS POST request with message String in the message body and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param messageToPost String to be posted 
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param acceptHeaders Additional response headers, as required by the client. 
	 * @param messageContentType ContentType of the message String. The default is "text/plain".
	 * @param httpContext {@code HttpContext} object containing HTTP parameters. To be used to set connection timeout, proxy,  authentication etc.
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams, String messageToPost, EncodingTypes messageEncoding,List<Header> acceptHeaders, ContentType messageContentType,HttpContext httpContext){
		return PostHelper.post(messageToPost, messageEncoding, baseURI, port,requestParams,acceptHeaders,messageContentType,httpContext);
	}//post	 
	/**
	 * Executes HTTP/HTTPS POST request for file and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  

	 * @param baseURI	Base URI of the REST endpoint
	 * @param port	Port of the REST endpoint
	 * @param requestParams	{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param file	File to be posted
	 * @param fileEncoding	EncodingType of the file
	 * @param additionalHeaders	Additional response headers, as required by the client. 
	 * @param fileContentType	Content type of the file
	 * @return
	 */
	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams, File file, EncodingTypes fileEncoding,List<Header> additionalHeaders, ContentType fileContentType){
		return PostHelper.post(baseURI, port, file, requestParams,fileEncoding, additionalHeaders, fileContentType,null);
	}//post	
	/**
	 * Executes HTTP/HTTPS POST request for file and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  

	 * @param baseURI	Base URI of the REST endpoint
	 * @param port	Port of the REST endpoint
	 * @param requestParams	{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param file	File to be posted
	 * @param fileEncoding	EncodingType of the file
	 * @param additionalHeaders	Additional response headers, as required by the client. 
	 * @param fileContentType	Content type of the file
 	 * @param proxyHost Host of the proxy server
	 * @param proxyPort Port of the proxy server
	 * @param proxyUser Proxy username. Default is null.
	 * @param proxyPassword. Proxy password. Default is null.
	 * @return 
	 */
	/**
	 * Executes HTTP/HTTPS POST request for file and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  

	 * @param baseURI	Base URI of the REST endpoint
	 * @param port	Port of the REST endpoint
	 * @param requestParams	{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param file	File to be posted
	 * @param fileEncoding	EncodingType of the file
	 * @param additionalHeaders	Additional response headers, as required by the client. 
	 * @param fileContentType	Content type of the file
	 * @param proxyHost Host of the proxy server
	 * @param proxyPort Port of the proxy server
	 * @param proxyUser Proxy username. Default is null.
	 * @param proxyPassword. Proxy password. Default is null.
	 * @return
	 */
		
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams, File file, EncodingTypes fileEncoding,List<Header> additionalHeaders, ContentType fileContentType,String proxyHost, int proxyPort, String proxyUser, String proxyPassword){
		return PostHelper.post(baseURI, port, file, requestParams,fileEncoding, additionalHeaders, fileContentType,new HttpContext().setProxy(proxyHost, proxyPort, proxyUser, proxyPassword));
	}//post	
	/**
	 * Executes HTTP/HTTPS POST request for file and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. This is the most commonly used form of POST method invocation.  

	 * @param baseURI	Base URI of the REST endpoint
	 * @param port	Port of the REST endpoint
	 * @param requestParams	{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param file	File to be posted
	 * @param fileEncoding	EncodingType of the file
	 * @param additionalHeaders	Additional response headers, as required by the client. 
	 * @param fileContentType	Content type of the file
	 * @param httpContext {@code HttpContext} object containing HTTP parameters. To be used to set connection timeout, proxy,  authentication etc.
	 * @return
	 */	
	public final static ServiceResponse post(String baseURI, int port, RequestParams requestParams, File file, EncodingTypes fileEncoding,List<Header> additionalHeaders, ContentType fileContentType,HttpContext httpContext){
		return PostHelper.post(baseURI, port, file, requestParams,fileEncoding, additionalHeaders, fileContentType,httpContext);
	}//post
	
	/**
	 * Executes HTTP/HTTPS POST request for a Java object (which is converted into JSON) and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code. 
	 * 
	 * Note: This API can be used to post objects directly. 
     *
	 * @param baseURI	Base URI of the REST endpoint
	 * @param port	Port of the REST endpoint
	 * @param requestParams	{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param objectToBePosted	Java object to be posted as JSON
	 * @param additionalHeaders	Additional response headers, as required by the client. 
	 * @param httpContext {@code HttpContext} object containing HTTP parameters. To be used to set connection timeout, proxy,  authentication etc.
	 * @return
	 */	
	public final static ServiceResponse postAsJSON(String baseURI, int port, RequestParams requestParams, Object objectToBePosted, List<Header> additionalHeaders, HttpContext httpContext){
		return PostHelper.post(new Gson().toJson(objectToBePosted), null, baseURI, port,requestParams,additionalHeaders,null,httpContext);
	}
	/**
	 * Executes HTTP/HTTPS PUT request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	public final static ServiceResponse put(String baseURI, int port){
		return PutHelper.put(baseURI,port, null, UTF8,null,null);
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
		return PutHelper.put(baseURI,UTF8,port, requestParams,null,null);
	}//put
	/**
	 * Executes HTTP/HTTPS PUT request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param proxyHost Host of the proxy server
	 * @param proxyPort Port of the proxy server
	 * @param proxyUser Proxy username. Default is null.
	 * @param proxyPassword. Proxy password. Default is null.
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	public final static ServiceResponse put(String baseURI, int port, RequestParams requestParams,String proxyHost, int proxyPort, String proxyUser, String proxyPassword){
		return PutHelper.put(baseURI,UTF8,port, requestParams,null,new HttpContext().setProxy(proxyHost, proxyPort, proxyUser, proxyPassword));
	}//put	
	/**
	 * Executes HTTP/HTTPS PUT request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Encoding type of response
	 * @param additionalHeaders List of headers
	 * @param httpContext HttpContext object containing http parameters
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	public final static ServiceResponse put(String baseURI, int port, RequestParams requestParams,EncodingTypes encoding,List<Header> additionalHeaders, HttpContext httpContext){
		return PutHelper.put(baseURI,encoding,port, requestParams,additionalHeaders,httpContext);
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
		return DeleteHelper.delete(uri, port,null,UTF8,null);
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
		return DeleteHelper.delete(baseURI, port, requestParams,UTF8,null);
	}//delete
	
	/**
	 * Executes HTTP/HTTPS DELETE request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code.
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param proxyHost Host of the proxy server
	 * @param proxyPort Port of the proxy server
	 * @param proxyUser Proxy username. Default is null.
	 * @param proxyPassword. Proxy password. Default is null.
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse delete(String baseURI, int port, RequestParams requestParams,String proxyHost, int proxyPort, String proxyUser, String proxyPassword){
		return DeleteHelper.delete(baseURI, port, requestParams,UTF8,new HttpContext().setProxy(proxyHost, proxyPort, proxyUser, proxyPassword));
	}//delete	
	/**
	 * Executes HTTP/HTTPS DELETE request and returns ServiceResponse object which encapsulates the entire HTTP response as a String as well 
	 * as the response headers and the HTTP status code.
	 * 
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param encoding Encoding type of response
	 * @headers Additional headers
	 * 
	 * @return {@link ServiceResponse} object containing the entire REST response as a String, the HTTP status code and the response headers.
	 */
	
	public final static ServiceResponse delete(String baseURI, int port, RequestParams requestParams,EncodingTypes encoding, List<Header> headers,HttpContext httpContext){
		return DeleteHelper.delete(baseURI, port, requestParams,encoding,headers,httpContext);
	}//delete
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into list of target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
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
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, Verb.GET, TransformationType.JSON, targetType, jsonAlias,UTF8, null,null);
	}//getByJSON
	
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into list of target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * 
	 * @param <T> Target entity type
	 * @param url Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param targetType Class of the target type T
	 * @param alias JSON alias for reading the entity from JSON response.
	 * @param encoding Encoding type of the response message
	 * @param additionalHeaders Additional response headers, as required by the client. 
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> getByJSON(String baseURI, int port, RequestParams requestParams, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, Verb.GET, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,null);
	}//getByJSON
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into list of target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * 
	 * @param <T> Target entity type
	 * @param url Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param targetType Class of the target type T
	 * @param alias JSON alias for reading the entity from JSON response.
	 * @param encoding Encoding type of the response message
	 * @param additionalHeaders Additional response headers, as required by the client. 
	 * @param int connectionTimeout Timeout in milliseconds till the connection is established
	 * @param int socketTimeout Default socket timeout (SO_TIMEOUT), the socket timeout waiting for data 
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> getByJSON(String baseURI, int port, RequestParams requestParams, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders, int connectionTimeout, int socketTimeout){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, Verb.GET, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,new HttpContext().setTimeout(connectionTimeout,socketTimeout));
	}//getByJSON
		
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into list of target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * 
	 * @param <T> Target entity type
	 * @param url Base URI of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param targetType Class of the target type T
	 * @param alias JSON alias for reading the entity from JSON response.
	 * @param encoding Encoding type of the response message
	 * @param additionalHeaders Additional response headers, as required by the client. 
	 * @param int connectionTimeout Timeout in milliseconds till the connection is established
	 * @param int socketTimeout Default socket timeout (SO_TIMEOUT) in milliseconds, the socket timeout waiting for data 
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> getByJSON(String baseURI,RequestParams requestParams, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders, int connectionTimeout, int socketTimeout){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, 80,requestParams, Verb.GET, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,new HttpContext().setTimeout(connectionTimeout, socketTimeout));
	}//getByJSON	
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into list of target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * 
	 * @param <T> Target entity type
	 * @param url Base URI of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param targetType Class of the target type T
	 * @param alias JSON alias for reading the entity from JSON response.
	 * @param encoding Encoding type of the response message
	 * @param additionalHeaders Additional response headers, as required by the client. 
	 * @param int timeout Connection timeout and socket timeout in milliseconds
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> getByJSON(String baseURI,RequestParams requestParams, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders, int timeout){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, 80,requestParams, Verb.GET, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,new HttpContext().setTimeout(timeout));
	}//getByJSON		
	/**
	 * Executes HTTP/HTTPS REST request and transforms the JSON response into list of target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * 
	 * @param <T> 
	 * 			Target entity type
	 * @param url 
	 * 			Base URI of the REST endpoint
	 * @param requestParams 
	 * 			{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param verb	
	 * 			REST method
	 * @param targetType 
	 * 			Class of the target type T
	 * @param alias 
	 * 			JSON alias for reading the entity from JSON response.
	 * @param encoding 
	 * 			Encoding type of the response message
	 * @param additionalHeaders 
	 * 			Additional response headers, as required by the client. 
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> restByJSON(String baseURI, RequestParams requestParams, Verb verb, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, 80,requestParams, verb, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,null);
	}//getByJSON	
	/**
	 * Executes HTTP/HTTPS REST request and transforms the JSON response into list of target entity.
	 * 
	 * @param <T> 
	 * 			Target entity type
	 * @param url 
	 * 			Base URI of the REST endpoint
	 * @param port 
	 * 			Port of the REST endpoint
	 * @param requestParams 
	 * 			{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param verb	
	 * 			REST method
	 * @param targetType 
	 * 			Class of the target type T
	 * @param alias 
	 * 			JSON alias for reading the entity from JSON response.
	 * @param encoding 
	 * 			Encoding type of the response message
	 * @param additionalHeaders 
	 * 			Additional response headers, as required by the client. 
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> restByJSON(String baseURI, int port, RequestParams requestParams, Verb verb, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, verb, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,null);
	}//getByJSON	
	/**
	 * Executes HTTP/HTTPS REST request and transforms the JSON response into list of target entity.
	 * 
	 * @param <T> 
	 * 			Target entity type
	 * @param url 
	 * 			Base URI of the REST endpoint
	 * @param requestParams 
	 * 			{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param verb	
	 * 			REST method
	 * @param targetType 
	 * 			Class of the target type T
	 * @param alias 
	 * 			JSON alias for reading the entity from JSON response.
	 * @param encoding 
	 * 			Encoding type of the response message
	 * @param additionalHeaders 
	 * 			Additional response headers, as required by the client. 
	 * @param connectionTimeout
	 * 			Connection timeout in milliseconds
	 * @param socketTimeout
	 * 			Default socket timeout (SO_TIMEOUT) in milliseconds, the timeout for waiting for data
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> restByJSON(String baseURI, RequestParams requestParams, Verb verb, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders, int connectionTimeout, int socketTimeout){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, 80,requestParams, verb, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,new HttpContext().setTimeout(connectionTimeout, socketTimeout));
	}//getByJSON	
	/**
	 * Executes HTTP/HTTPS REST request and transforms the JSON response into list of target entity.
	 * 
	 * @param <T> 
	 * 			Target entity type
	 * @param url 
	 * 			Base URI of the REST endpoint. Port is 80.
	 * @param requestParams 
	 * 			{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param verb	
	 * 			REST method
	 * @param targetType 
	 * 			Class of the target type T
	 * @param alias 
	 * 			JSON alias for reading the entity from JSON response.
	 * @param encoding 
	 * 			Encoding type of the response message
	 * @param additionalHeaders 
	 * 			Additional response headers, as required by the client. 
	 * @param timeout
	 * 			Connection and socket timeout in milliseconds
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> restByJSON(String baseURI, RequestParams requestParams, Verb verb, Class<T> targetType, String alias, EncodingTypes encoding, List<Header> additionalHeaders, int timeout){
		JSONAlias jsonAlias=new JSONAlias(alias);
		return RestingHelper.executeAndTransform(baseURI, 80,requestParams, verb, TransformationType.JSON, targetType, jsonAlias,encoding, additionalHeaders,new HttpContext().setTimeout(timeout));
	}//getByJSON		
	/**
	 * Executes HTTP/HTTPS GET request and transforms the JSON response into lists of target entities.
	 * 
	 * @param url Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param aliasTypeMap Map of aliases and corresponding types for marshalling data from JSON response. Only the aliases/types for which the data
	 * should be retrieved are to be added in this map.
	 * 
	 * @return Map containing alias (@link String) and (@ List)s of objects corresponding to that token
	 */
	
	public final static  Map<String, List> getByJSON(String baseURI, int port, RequestParams requestParams, Map<String, Class> aliasTypeMap){	
		JSONAlias alias=new JSONAlias(aliasTypeMap);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, Verb.GET, TransformationType.JSON, alias,UTF8,null,null);
	}//getByJSON	
	
	/**
	 * Executes HTTP/HTTPS REST request and transforms the JSON response into lists of target entities.
	 * 
	 * @param url 
	 * 			Base URI of the REST endpoint
	 * @param port 
	 * 			Port of the REST endpoint
	 * @param requestParams 
	 * 			{@link RequestParams} object containing collection of parameters in key/ value pair for REST request
	 * @param verb	
	 * 			REST method
	 * @param aliasTypeMap 
	 * 			Map of aliases and corresponding types for marshalling data from JSON response. Only the aliases/types for which the data
	 * 			should be retrieved are to be added in this map.
	 * @param encoding 
	 * 			Encoding type of the response message
	 * @param additionalHeaders 
	 * 			Additional response headers, as required by the client. 
	 *
	 * @return Map containing alias (@link String) and (@ List)s of objects corresponding to that token
	 */
	
	public final static  Map<String, List> restByJSON(String baseURI, int port, RequestParams requestParams, Verb verb, Map<String, Class> aliasTypeMap, EncodingTypes encoding, List<Header> additionalHeaders){	
		JSONAlias alias=new JSONAlias(aliasTypeMap);
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, verb, TransformationType.JSON, alias,encoding,additionalHeaders,null);
	}//getByJSON	
	
	/**
	 * Executes HTTP/HTTPS GET request and transforms the XML response into target entity.
	 * 
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * 
	 * @param <T> Root entity type of the XML
	 * @param baseURI Base URI of the REST endpoint
	 * @param port Port of the REST endpoint
	 * @param requestParams {@link RequestParams} object containing collection of request parameters in key/ value pair
	 * @param rootType Class of the root entity
	 * @param alias XMLAlias object 
	 * 
	 * @return Root entity
	 */

	public final static <T> T getByXML(String baseURI, int port, RequestParams requestParams, Class<T> rootType, XMLAlias alias){
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, Verb.GET, TransformationType.XML, rootType, alias,UTF8, null,null).get(0);
	}//getByXML
	/**
	 * Executes HTTP/HTTPS REST request and transforms the XML response into target entity.
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * @param <T> 
	 * 			Root entity type of the XML
	 * @param baseURI 
	 * 			Base URI of the REST endpoint
	 * @param port 
	 * 			Port of the REST endpoint
	 * @param requestParams 
	 * 			{@link RequestParams} object containing collection of request parameters in key/ value pair
	 * @param verb	
	 * 			REST method
	 * @param rootType 
	 * 			Class of the root entity
	 * @param alias 
	 * 			XMLAlias object 
	 * @param encoding 
	 * 			Encoding type of the response message
	 * @param additionalHeaders 
	 * 			Additional response headers, as required by the client. 
	 *
	 * @return Root entity
	 */

	public final static <T> T restByXML(String baseURI, int port, RequestParams requestParams, Verb verb, Class<T> rootType, XMLAlias alias, EncodingTypes encoding, List<Header> additionalHeaders){
		return RestingHelper.executeAndTransform(baseURI, port,requestParams, verb, TransformationType.XML, rootType, alias,encoding, additionalHeaders,null).get(0);
	}//getByXML	
		/**
	 * Executes HTTP/HTTPS GET request and transforms the YAML response into
	 * target entity.
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * @param <T>
	 *            Target type of the YAML
	 * @param baseURI
	 *            Base URI of the REST endpoint
	 * @param port
	 *            Port of the REST endpoint
	 * @param requestParams
	 *            {@link RequestParams} object containing collection of request
	 *            parameters in key/ value pair
	 * @param targetType
	 *            Class of the target type
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T> List<T> getByYAML(String baseURI, int port,	RequestParams requestParams, Class<T> targetType) {
		return RestingHelper.executeAndTransform(baseURI, port, requestParams, Verb.GET,TransformationType.YAML, targetType, null, UTF8, null,null);
	}// getByYAML

	/**
	 * Executes HTTP/HTTPS REST request and transforms the YAML response into
	 * target entity.
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * @param <T>
	 *            Target type of the YAML
	 * @param baseURI
	 *            Base URI of the REST endpoint
	 * @param port
	 *            Port of the REST endpoint
	 * @param requestParams
	 *            {@link RequestParams} object containing collection of request
	 *            parameters in key/ value pair
	 * @param verb	
	 * 				REST method
	 * @param targetType
	 *            Class of the target type
	 * @param encoding 
	 * 				Encoding type of the response message
	 * @param additionalHeaders 
	 * 				Additional response headers, as required by the client. 
	 * 
	 * @return List of entities of target type T
	 */
	public final static <T> List<T> restByYAML(String baseURI, int port, RequestParams requestParams, Verb verb, Class<T> targetType, EncodingTypes encodingType, List<Header> additionalHeaders) {
		return RestingHelper.executeAndTransform(baseURI, port, requestParams, verb,TransformationType.YAML, targetType, null, encodingType, additionalHeaders,null);
	}// restByYAML
	
	/**
	 * Executes HTTP/HTTPS GET request and transforms the ATOM response into
	 * target entity.
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * @param <T>
	 *            Target type of the ATOM response 
	 * @param baseURI
	 *            Base URI of the REST endpoint
	 * @param port
	 *            Port of the REST endpoint
	 * @param requestParams
	 *            {@link RequestParams} object containing collection of request
	 *            parameters in key/ value pair
	 * @param targetType
	 *            Class of the target type
	 * 
	 * @return List of entities of target type T
	 */

	public final static <T extends AtomFeed> List<T> getByATOM(String baseURI, int port,	RequestParams requestParams, Class<T> targetType, XMLAlias alias) {
		return executeAndTransform(baseURI, port, requestParams, Verb.GET,TransformationType.ATOM, targetType, alias, UTF8, null,null);
	}// getByATOM
	
	/**
	 * 
	 * Executes HTTP/HTTPS REST request and transforms the ATOM response into
	 * target entity.
	 * Note: To use advanced features like proxies and authentication, please use {@code RestingBuilder}
	 * @param <T>
	 *            Target type of the ATOM response 
	 * @param baseURI
	 *            Base URI of the REST endpoint
	 * @param port
	 *            Port of the REST endpoint
	 * @param requestParams
	 *            {@link RequestParams} object containing collection of request
	 *            parameters in key/ value pair
	 * @param verb	
	 * 			REST method
	 * @param targetType
	 *            Class of the target type
	 * @param encoding 
	 * 				Encoding type of the response message
	 * @param additionalHeaders 
	 * 				Additional response headers, as required by the client. 

	 * @return List of entities of target type T
	 */
	public final static <T extends AtomFeed> List<T> restByATOM(String baseURI, int port, RequestParams requestParams, Verb verb, Class<T> targetType, XMLAlias alias, EncodingTypes encodingType, List<Header> additionalHeaders) {
		return executeAndTransform(baseURI, port, requestParams, verb,TransformationType.ATOM, targetType, alias, encodingType, additionalHeaders,null);
	}// restByATOM
}//Resting
