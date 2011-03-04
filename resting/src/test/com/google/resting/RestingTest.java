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

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import junit.framework.TestCase;
import test.com.google.resting.vo.Facets;
import test.com.google.resting.vo.Product;
import test.com.google.resting.vo.Result;
import test.com.google.resting.vo.ResultSet;

import com.google.resting.Resting;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONRequestParams;
import com.google.resting.component.impl.xml.XMLAlias;
/**
 * Test case for main Resting API
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class RestingTest extends TestCase {

	public void testGet() {
		System.out.println("\ntestGet\n-----------------------------");
		try {
			ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA",80); 
			System.out.println("[RestingTest::testGet] Response is" +response);
			assertEquals(200, response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetWithRequestParams() {
		System.out.println("\ntestGetWithRequestParams\n-----------------------------");
		RequestParams params = new BasicRequestParams();   
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");  
		params.add("state", "CA");  
		try {
			ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",80,params);  
			System.out.println("[RestingTest::testGetWithRequestParams] Response is" +response);
			assertEquals(200, response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testPost() {
	}

	public void testPostWithRequestParams() {
		fail("Not yet implemented");
	}

	public void testPut() {
		fail("Not yet implemented");
	}

	public void testPutWithRequestParams() {
		fail("Not yet implemented");
	}

	public void testDelete() {
		fail("Not yet implemented");
	}

	public void testDeleteWithRequestParams() {
		fail("Not yet implemented");
	}

	public void testGetByJSON() {
		System.out.println("\ntestGetByJSON\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609");  
		try {
			List<Product> products=Resting.getByJSON("http://api.zappos.com/Product/7564933",80,jsonParams, Product.class, "product");
			System.out.println("[RestingTest::getByJSON] The product detail is "+products.get(0).toString());
			assertEquals(7564933,products.get(0).getProductId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testGetByJSONLongResponse(){
		System.out.println("\ntestGetByJSONLongResponse\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609"); 
		jsonParams.add("facets", "[\"brandNameFacet\"]");
		try {
			List<Facets> facets=Resting.getByJSON("http://api.zappos.com/Search",80,jsonParams, Facets.class, "facets");
			System.out.println("[RestingTest::testGetByJSONLongResponse] The length of values in facets is "+facets.get(0).getValues().size());
			assertNotNull(facets);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetByXML() {
		System.out.println("\ntestGetByXML\n-----------------------------");
		RequestParams params = new BasicRequestParams();   
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");  
		params.add("state", "CA");  
		XMLAlias alias=new XMLAlias().add("Result", Result.class).add("ResultSet", ResultSet.class);   
		try {
			ResultSet resultset=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode", 80,params,ResultSet.class, alias);	
			System.out.println("[RestingTest::getByXML] The response detail is "+resultset.getResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void testGetByXML2() {
		System.out.println("\ntestGetByXML2\n-----------------------------");		
		RequestParams params = new BasicRequestParams();   
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");  
		params.add("state", "CA");  
		XMLAlias alias=new XMLAlias().add("Result", Result.class).add("ResultSet", ResultSet.class);   
	    try {
			ResultSet results=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode", 80,params,ResultSet.class, alias);	
			System.out.println("[RestingTest::getByXML2] The response detail is "+results.getResult().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testMime(){
		System.out.println("\ntestMime\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testMime] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
		
	}
	public void testMimeApplicationJSON(){
		System.out.println("\ntestMime2\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/vo",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testMime2] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
	}
	public void testMimeTextHtml(){
		System.out.println("\ntestMimeTextHtml\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/htmlhello",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testMimeTextHtml] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
	}
	public void testMimeAdditionalNeg(){
		System.out.println("\ntestMimeAdditionalNeg\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/octet",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testMimeAdditionalNeg] Response is" +response);
		assertEquals(406, response.getStatusCode());
	}
	public void testMimeAdditionalPos(){
		System.out.println("\ntestMimeAdditionalPos\n-----------------------------");		
		ServiceResponse response=null;
		List<Header> headers=new ArrayList<Header>();
		headers.add(new BasicHeader("Accept","application/octet-stream"));
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/octet",8080, null, EncodingTypes.BINARY, headers);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testMimeAdditionalPos] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
	}
	public void testMimeAdditionalPos2(){
		System.out.println("\ntestMimeAdditionalPos2\n-----------------------------");		
		ServiceResponse response=null;
		List<Header> headers=new ArrayList<Header>();
		headers.add(new BasicHeader("Accept","application/octet-stream"));
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/octet",8080, null, EncodingTypes.BINARY, headers);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testMimeAdditionalPos2] Length of response byte array is " +response.getResponseInBytes().length);
		assertEquals(200, response.getStatusCode());
		
	}	
}
