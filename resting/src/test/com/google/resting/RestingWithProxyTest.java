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
package test.com.google.resting;

import java.util.List;

import junit.framework.TestCase;
import test.com.google.resting.vo.Facets;

import com.google.resting.Resting;
import com.google.resting.RestingBuilder;
import com.google.resting.component.Alias;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONAlias;
import com.google.resting.component.impl.json.JSONRequestParams;
import com.google.resting.rest.client.HttpContext;
/**
 * Test case for Resting API with proxy
 * 
 * @author sujata.de
 * @since resting 0.8
 *
 */
public class RestingWithProxyTest extends TestCase {
	
	String proxyHost="proxy.abcd.ac.in";
	int proxyPort=3128;
	String proxyUser="user";
	String proxyPassword="password";
	
	
	public void testBuilderWithProxy(){
		System.out.println("\ntestBuilderWithProxy\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609"); 
		jsonParams.add("facets", "[\"brandNameFacet\"]");
		Alias alias=new JSONAlias("facets");
		try {
			RestingBuilder<Facets> builder=new RestingBuilder<Facets>("http://api.zappos.com/Search",Facets.class)
																	.setAlias(alias)
																	.setRequestParams(jsonParams)
																	.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
			List<Facets> facets=builder.build();
			System.out.println("[RestingTest::testBuilderWithProxy] The length of values in facets is "+facets.get(0).getValues().size());
			assertNotNull(facets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void testRestingWithProxy(){
		System.out.println("\ntestRestingWithProxy\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609"); 
		jsonParams.add("facets", "[\"brandNameFacet\"]");
		try {
			ServiceResponse response=Resting.get("http://api.zappos.com/Search", 80,jsonParams, EncodingTypes.UTF8, null, proxyHost, proxyPort, proxyUser, proxyPassword);
			System.out.println("[RestingTest::testRestingWithProxy] The length of values in facets is "+response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testBuilderWithProxyForServiceResponse(){
		System.out.println("\ntestBuilderWithProxyForServiceResponse\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609"); 
		jsonParams.add("facets", "[\"brandNameFacet\"]");
		Alias alias=new JSONAlias("facets");
		try {
			ServiceResponse response=new RestingBuilder("http://api.zappos.com/Search")
																	.setAlias(alias)
																	.setRequestParams(jsonParams)
																	.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword)
																	.invoke();
			System.out.println("[RestingTest::testBuilderWithProxyForServiceResponse] The length of response content is "+response.getResponseLength());
			System.out.println("[RestingTest::testBuilderWithProxyForServiceResponse] Response content "+response.getResponseString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testRestingWithHttpContext(){
		System.out.println("\ntestRestingWithHttpContext\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609"); 
		jsonParams.add("facets", "[\"brandNameFacet\"]");
		HttpContext httpContext=new HttpContext();
		httpContext.setProxy(proxyHost, proxyPort, proxyUser, proxyPassword);
		List<Facets> facets=Resting.getByJSON("http://api.zappos.com/Search", 80, jsonParams, Facets.class, "facets", httpContext);
		System.out.println("[RestingTest::testBuilderWithProxy] The length of values in facets is "+facets.get(0).getValues().size());
	}

}
