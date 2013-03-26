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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import test.com.google.resting.vo.Assertion;
import test.com.google.resting.vo.Concept;
import test.com.google.resting.vo.Entry;
import test.com.google.resting.vo.Err;
import test.com.google.resting.vo.Facets;
import test.com.google.resting.vo.GeoNames;
import test.com.google.resting.vo.Product;
import test.com.google.resting.vo.Result;
import test.com.google.resting.vo.ResultSet;
import test.com.google.resting.vo.Standard;
import test.com.google.resting.vo.Standards;
import test.com.google.resting.vo.Status;
import test.com.google.resting.vo.StatusMessage;
import test.com.google.resting.vo.StatusMessageConverter;

import com.google.gson.Gson;
import com.google.resting.Resting;
import com.google.resting.RestingBuilder;
import com.google.resting.atom.AtomFeed;
import com.google.resting.component.Alias;
import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.Verb;
import com.google.resting.component.content.ContentType;
import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONAlias;
import com.google.resting.component.impl.json.JSONRequestParams;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.json.JSONException;
import com.google.resting.json.JSONObject;
import com.google.resting.transform.impl.JSONTransformer;
import com.google.resting.transform.impl.XMLTransformer;
import com.google.resting.util.ReflectionUtil;
import com.thoughtworks.xstream.XStream;
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
			ServiceResponse response=Resting.get("http://where.yahooapis.com/geocode?q=1600+Pennsylvania+Avenue,+Washington,+DC&appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--",80); 
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
		params.add("q", "1600+Pennsylvania+Avenue,+Washington,+DC");  
		try {
			ServiceResponse response=Resting.get("http://where.yahooapis.com/geocode",80,params);  
			System.out.println("[RestingTest::testGetWithRequestParams] Response is" +response);
			assertEquals(200, response.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetByJSON() {
		System.out.println("\ntestGetByJSON\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609");  
		try {
			List<Product> products=Resting.getByJSON("http://api.zappos.com/Product/7515478",80,jsonParams, Product.class, "product");
			System.out.println("[RestingTest::getByJSON] The product detail is "+products.get(0).toString());
			assertEquals(7515478,products.get(0).getProductId());
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
		params.add("q", "1600+Pennsylvania+Avenue,+Washington,+DC");  
		XMLAlias alias=new XMLAlias().add("Result", Result.class).add("ResultSet", ResultSet.class);   
		try {
			ResultSet results=Resting.getByXML("http://where.yahooapis.com/geocode", 80,params,ResultSet.class, alias);	
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
		String xml="<?xml version=\"1.0\" encoding=\"utf-8\"?><message>FFQW0141I Collection 005Collection was created successfully.</message>";
		XStream xstream=new XStream(new DomDriver());
		xstream.registerConverter(new StatusMessageConverter());
		xstream.alias("message", StatusMessage.class);
		StatusMessage message=(StatusMessage)xstream.fromXML(xml);
		System.out.println(message.toString());
	}

	public void testLongAndShortenedXML(){
		try {
			System.out.println("\ntestLongAndShortenedXML\n-----------------------------");
			String longxml="<geonames><status><message>the deaily limit of 30000 credits for demo has been exceeded.</message><value> 18 </value></status></geonames>";
			XStream xstream=new XStream(new DomDriver());
			xstream.alias("geonames", GeoNames.class);
			xstream.alias("status",Status.class);
			GeoNames geonames=(GeoNames)xstream.fromXML(longxml);
			System.out.println("Long XML representation: "+geonames);
			String shortXML="<geonames><status message=\"the deaily limit of 30000 credits for demo has been exceeded.\" value=\"18\"/></geonames>"; 
			XStream xstream2=new XStream(new DomDriver());
			xstream2.alias("geonames", GeoNames.class);
			xstream2.alias("status",Status.class);
			xstream2.useAttributeFor(Status.class, "message");
			xstream2.useAttributeFor(Status.class, "value");
			GeoNames geonames2=(GeoNames)xstream2.fromXML(shortXML);
			System.out.println("Short XML representation: "+geonames2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void testUseAttributeInXML(){
		System.out.println("\ntestUseAttributeInXML\n-----------------------------");
		XMLAlias alias=new XMLAlias().add("geonames", GeoNames.class).add("status", Status.class).addAttribute("message", Status.class).addAttribute("value", Status.class);
		GeoNames geonames=Resting.getByXML("http://localhost/testresting/rest/hello/get/shortxml", 8080, null, GeoNames.class, alias);
		System.out.println(geonames);
	}

	public void testRestByYAML1() {
		System.out.println("\ntestGetByYAML1\n-----------------------------");
		try {
			List<Concept> l = Resting
					.restByYAML(
							"http://openmind.media.mit.edu/api/en/concept/duck/query.yaml",
							80, null, Verb.GET, Concept.class,
							EncodingTypes.UTF8, null);
			assertNotNull(l);
			assertEquals(1, l.size());
			assertNotSame("Failed to create Concept object",
					Concept.class, l.get(0));
			System.out.println(ReflectionUtil.describe(l.get(0),
					Concept.class, new StringBuffer()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testRestByYAML2() {
		System.out.println("\ntestGetByYAML2\n-----------------------------");
		try {
			List<Assertion> l = Resting
					.restByYAML(
							"http://openmind.media.mit.edu/api/en/assertion/25/query.yaml",
							80, null, Verb.GET, Assertion.class,
							EncodingTypes.UTF8, null);
			assertNotNull(l);
			assertEquals(1, l.size());
			assertNotSame("Failed to create Assertion object",
					Assertion.class, l.get(0));
			System.out.println(ReflectionUtil.describe(l.get(0),
					Assertion.class, new StringBuffer()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testRestByATOM() {
		System.out.println("\ntestRestByATOM\n-----------------------------");
		try {
			List<AtomFeed> l = Resting.restByATOM(
					"http://books.google.com/books/feeds/volumes?q=php", 80, null,
					Verb.GET, AtomFeed.class,new XMLAlias(),EncodingTypes.UTF8 , null);
			assert l.size() == 0 : "Atom parser failed to parse the response. Check error logs";
			System.out.println(ReflectionUtil.describe(l.get(0), AtomFeed.class, new StringBuffer()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGetByATOM() {
		System.out.println("\ntestGetByATOM\n-----------------------------");
		try {
			List<AtomFeed> l = Resting.getByATOM(
					"http://books.google.com/books/feeds/volumes?q=php", 80, null,
					AtomFeed.class,new XMLAlias());
			assert l.size() == 0 : "Atom parser failed to parse the response. Check error logs";
			System.out.println(ReflectionUtil.describe(l.get(0), AtomFeed.class, new StringBuffer()));
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



	public void testLocal4(){
		System.out.println("\ntestLocal4\n-----------------------------");

		ServiceResponse serviceResponse=Resting.get("http://172.16.18.83/api/v10/search?collection=246246&query=readme&start=0&results=25&output=application/json", 8394,null, EncodingTypes.UTF8, null);
		String resultant=serviceResponse.getResponseString();
		/*		String resultant=StringUtils.remove(serviceResponse.getResponseString(),"es:");
	System.out.println("Index: "+StringUtils.indexOf(resultant, "#text"));
	//StringUtils.re

	resultant=StringUtils.remove(resultant, "ibmsc:");

	System.out.println(resultant);

	JSONTransformer<test.com.google.resting.vo.Header> transformer=new JSONTransformer<test.com.google.resting.vo.Header>();		
	List<test.com.google.resting.vo.Header> headers=transformer.getEntityList(resultant, test.com.google.resting.vo.Header.class, new JSONAlias("apiResponse"));
	System.out.println(" Parsing Header object: Total results: "+headers.get(0).getTotalResults());
		 */
		System.out.println(resultant);
		JSONTransformer<test.com.google.resting.vo.Entry> etransformer=new JSONTransformer<test.com.google.resting.vo.Entry>();
		List<test.com.google.resting.vo.Entry> entries=etransformer.getEntityList(resultant, test.com.google.resting.vo.Entry.class, new JSONAlias("es:apiResponse"));
		System.out.println("Parsing entries: No. of apiresponse items"+entries.size());
		Entry entry=entries.get(0);
		System.out.println(entry.getResults().get(0).getLink()[0].getHref());
	}

	public void testLocal5(){
		System.out.println("\ntestLocal5\n-----------------------------");
		ServiceResponse serviceResponse=Resting.get("http://172.16.18.83/api/v10/search?collection=246246&query=site&start=0&results=25&output=application/json", 8394,null, EncodingTypes.UTF8, null);
		JSONTransformer<test.com.google.resting.vo.Header> transformer=new JSONTransformer<test.com.google.resting.vo.Header>();		
		List<test.com.google.resting.vo.Header> headers=transformer.getEntityList(serviceResponse, test.com.google.resting.vo.Header.class, new JSONAlias("es:apiResponse"));
		System.out.println(" Parsing Header object: Total results: "+headers.get(0).getTotalResults());
		List<test.com.google.resting.vo.Entry> entries=headers.get(0).getEntries();
		//System.out.println(entries.get(0).getField().getText());

	}

	public void testPostTextFile(){
		System.out.println("\ntestPostTextFile\n-----------------------------");
		ServiceResponse response=Resting.post("http://localhost/testresting/rest/hello/post/file", 8080, null, new File("C:\\misc\\cityList.txt"), EncodingTypes.UTF8, null, ContentType.TEXT_PLAIN);
		System.out.println(response.getResponseString());

	}
	public void testPostImageFile(){
		System.out.println("\ntestImageTextFile\n-----------------------------");
		ServiceResponse response=Resting.post("http://localhost/testresting/rest/hello/post/imagefile", 8080, null, new File("C:\\misc\\Grills and Faces.PNG"), EncodingTypes.BINARY, null, ContentType.IMAGE_JPEG);
		System.out.println(response.getResponseLength());

	}	
	
	public void testBuilder(){
		System.out.println("\ntestBuilder\n-----------------------------");
		RequestParams jsonParams = new JSONRequestParams();   
		jsonParams.add("key", "fdb3c385a8d22d174cafeadc6d4c1405b08d5609"); 
		jsonParams.add("facets", "[\"brandNameFacet\"]");
		Alias alias=new JSONAlias("facets");
		try {
			RestingBuilder<Facets> builder=new RestingBuilder<Facets>("http://api.zappos.com/Search",Facets.class)
																	.setAlias(alias)
																	.setRequestParams(jsonParams);
			List<Facets> facets=builder.build();
			System.out.println("[RestingTest::testBuilderDefaults] The length of values in facets is "+facets.get(0).getValues().size());
			assertNotNull(facets);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void testNoAliasWithJSONTransformer(){
		System.out.println("\ntestNoAliasWithJSONTransformer\n-----------------------------");
		String json="{\"err\":{\"err\":\"824\"}}";
		JSONTransformer<Err> t=new JSONTransformer<Err>();
		List<Err>err=t.getEntityList(json, Err.class, new JSONAlias("err"));
		
		System.out.println(err);

		String json2="{\"err\":\"824\"}";
		List<Err>err2=t.getEntityList(json, Err.class, new JSONAlias("err"));
		System.out.println(err2);
		
	}
	
	public void testNoAlias(){
		System.out.println("\ntestNoAlias\n-----------------------------");
		//System.out.println(Resting.get("http://localhost/testresting/rest/hello/get/noalias", 8080));
		List<Err>errs=Resting.getByJSON("http://localhost/testresting/rest/hello/get/noalias", 8080, null, Err.class,"err");
		System.out.println(errs.get(0));
	}
	
	public void testNullAlias(){
		System.out.println("\ntestNullAlias\n-----------------------------");
		//System.out.println(Resting.get("http://localhost/testresting/rest/hello/get/noalias", 8080));
		List<Err>errs=Resting.getByJSON("http://localhost/testresting/rest/hello/get/noalias", 8080, null, Err.class,null);
		System.out.println(errs.get(0));
	}
}
