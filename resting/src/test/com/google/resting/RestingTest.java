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

import junit.framework.TestCase;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import test.com.google.resting.vo.Facets;
import test.com.google.resting.vo.House;
import test.com.google.resting.vo.MyStringConverter;
import test.com.google.resting.vo.OFCollection;
import test.com.google.resting.vo.OFCollections;
import test.com.google.resting.vo.Product;
import test.com.google.resting.vo.Result;
import test.com.google.resting.vo.ResultSet;
import test.com.google.resting.vo.Standard;
import test.com.google.resting.vo.Standards;
import test.com.google.resting.vo.StatusMessage;
import test.com.google.resting.vo.StatusMessageConverter;

import com.google.resting.Resting;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.Verb;
import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONRequestParams;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.impl.XMLTransformer;
import com.google.resting.transform.impl.atom.AtomFeed;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.converters.basic.NullConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;
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
			ResultSet results=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode", 80,params,ResultSet.class, alias);	
			System.out.println("[RestingTest::getByXML] The response detail is "+results.getResult().toString());
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
	public void testAcceptApplicationJSON(){
		System.out.println("\ntestAcceptApplicationJSON\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/vo",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testAcceptApplicationJSON] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
	}
	public void testAcceptTextHtml(){
		System.out.println("\ntestAcceptTextHtml\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/htmlhello",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testAcceptTextHtml] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
	}
	public void testAcceptOctectNeg(){
		System.out.println("\ntestAcceptOctectNeg\n-----------------------------");		
		ServiceResponse response=null;
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/octet",8080);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testAcceptOctectNeg] Response is" +response);
		assertEquals(406, response.getStatusCode());
	}
	public void testConvertOctetStream(){
		System.out.println("\ntestConvertOctetStream\n-----------------------------");		
		ServiceResponse response=null;
		List<Header> headers=new ArrayList<Header>();
		headers.add(new BasicHeader("Accept","application/octet-stream"));
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/octet",8080, null, EncodingTypes.BINARY, headers);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testConvertOctetStream] Response is" +response);
		assertEquals(200, response.getStatusCode());
		
	}
	public void testAcceptOctetStream(){
		System.out.println("\ntestAcceptOctetStream\n-----------------------------");		
		ServiceResponse response=null;
		List<Header> headers=new ArrayList<Header>();
		headers.add(new BasicHeader("Accept","application/octet-stream"));
		try {
			response = Resting.get("http://localhost/testresting/rest/hello/octet",8080, null, EncodingTypes.BINARY, headers);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println("[RestingTest::testAcceptOctetStream] Length of response byte array is " +response.getResponseLength());
		assertEquals(200, response.getStatusCode());
		
	}	
	
	public void testAddCustomConverter(){
		System.out.println("\ntestAddCustomConverter\n-----------------------------");				
		XMLAlias alias=new XMLAlias().add("message", StatusMessage.class);
		alias.addConverter(new StatusMessageConverter());
        String entireResponseString= Resting.get("http://172.16.21.134/Mediator18042011/ssbt/api/collections?method=create&name=013Collection&output=xml&api_username=esadmin&api_password=Ssbt123", 8088).getResponseString();
        System.out.println("entireResponseString : "+entireResponseString);
        StatusMessage message=Resting.getByXML("http://172.16.21.134/Mediator18042011/ssbt/api/collections?method=create&name=014Collection&output=xml&api_username=esadmin&api_password=Ssbt123", 8088,null,StatusMessage.class, alias);	
        System.out.println(message.toString());

	}	
	
	public void testLocal3(){
		System.out.println("\ntestLocal3\n-----------------------------");				
		String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><message>FFQW0141I Collection 005Collection was created successfully.</message>";
		XStream xstream=new XStream(new DomDriver());
		xstream.registerConverter(new StatusMessageConverter());
		xstream.alias("message", StatusMessage.class);
		StatusMessage message=(StatusMessage)xstream.fromXML(xml);
		System.out.println(message.toString());
	}
	
	public void testRestByYAML() {
		System.out.println("\ntestGetByYAML\n-----------------------------");
		List<Header> headers=new ArrayList<Header>();
		headers.add(new BasicHeader("Accept","application/octet-stream"));
		try {
			List<House> l = Resting.restByYAML(
					"http://localhost/testresting/rest/hello/yaml", 8080, null,Verb.GET,
					House.class, EncodingTypes.UTF8,headers);
			assertNotNull(l);
			assertEquals(1, l.size());
			System.out.println("The house details are: "+l.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void testRestByATOM() {
		System.out.println("\ntestGetByATOM\n-----------------------------");
		List<Header> headers=new ArrayList<Header>();
		headers.add(new BasicHeader("Accept","application/octet-stream"));
		try {
			List<AtomFeed> l = Resting.restByATOM(
					"http://localhost/testresting/rest/hello/atom", 8080, null,Verb.GET,
					AtomFeed.class,EncodingTypes.UTF8,headers );
			System.out.println("The length of AtomFeed list is "+l.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testTrans(){
		System.out.println("\ntestTrans\n-----------------------------");				
		String text="<?xml version=\"1.0\" encoding=\"UTF-8\"?><standards> <standard>  <id>1</id>  <title>Safety</title> <parentId></parentId>  <parentTitle></parentTitle>  <level>0</level> </standard></standards>";
		XMLAlias alias=new XMLAlias().add("standard", Standard.class).add("standards", Standards.class); 
		//alias.addConverter(new MyStringConverter());
		XMLTransformer trans=new XMLTransformer();
		List<Standards> list=trans.getEntityList(text, Standards.class, alias);
		System.out.println(list.toString());
		
	}
	
}
