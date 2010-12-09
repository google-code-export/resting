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
import test.com.google.resting.vo.Product;
import test.com.google.resting.vo.Result;
import test.com.google.resting.vo.ResultSet;

import com.google.resting.Resting;
import com.google.resting.component.Alias;
import com.google.resting.component.RequestParams;
import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.JSONRequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.XMLAlias;
/**
 * Test case for main Resting API
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class RestingTest extends TestCase {

	public void testGet() {
		ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA",80); 
		System.out.println("[RestingTest::testGet] Response is" +response);
		assertEquals(200, response.getStatusCode());
	}

	public void testGetWithRequestParams() {
		RequestParams params = new BasicRequestParams();   
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");  
		params.add("state", "CA");  
		ServiceResponse response=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",80,params);  
		System.out.println("[RestingTest::testGetWithRequestParams] Response is" +response);
		assertEquals(200, response.getStatusCode());
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
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609");  
		List<Product> products=Resting.getByJSON("http://api.zappos.com/Product/7564933",80,jsonParams, Product.class, "product");
		System.out.println("[RestingTest::getByJSON] The product detail is "+products.get(0).toString());
		assertEquals(7564933,products.get(0).getProductId());
	}

	public void testGetByXML() {
		RequestParams params = new BasicRequestParams();   
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");  
		params.add("state", "CA");  
		XMLAlias alias=new XMLAlias().add("Result", Result.class).add("ResultSet", ResultSet.class);   
		ResultSet resultset=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode", 80,params,ResultSet.class, alias);	
		System.out.println("[RestingTest::getByXML] The response detail is "+resultset.getResult().toString());
		assertNotNull(resultset);
	}
	public void testGetByXML2() {
		RequestParams params = new BasicRequestParams();   
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");  
		params.add("state", "CA");  
		XMLAlias alias=new XMLAlias().add("Result", Result.class).add("ResultSet", ResultSet.class);   
	     ResultSet results=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode", 80,params,ResultSet.class, alias);	
		System.out.println("[RestingTest::getByXML2] The response detail is "+results.getResult().toString());
		assertNotNull(results);
	}
}
