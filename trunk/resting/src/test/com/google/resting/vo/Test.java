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

package test.com.google.resting.vo;

import java.util.List;

import com.google.resting.Resting;
import com.google.resting.component.RequestParams;
import com.google.resting.component.impl.BasicRequestParams;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.json.JSONAlias;
import com.google.resting.transform.impl.XMLTransformer;
/**
 * Temporary test class.
 * 
 * @author sujata.de
 *
 */
public class Test {
	public static void main(String[] args) {
		RequestParams params = new BasicRequestParams();
		params.add("appid", "YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");
		params.add("state", "CA");
		ServiceResponse serviceResponse=Resting.get("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA",80);
		System.out.println("[Resting]The raw HTTP response from Yahoo Map REST API is\n--------- \n"+serviceResponse.getResponseString());
				

		XMLTransformer<ResultSet> transformer=new XMLTransformer<ResultSet>();
		JSONAlias alias=new JSONAlias().add("Result", Result.class).add("ResultSet", ResultSet.class);
/*
		List<ResultSet> results=transformer.getEntityList(serviceResponse, ResultSet.class,alias);
		System.out.println("\n-----------\n[Resting]The resultant object is \n ------\n"+results.toString());
*/		
		//List<ResultSet> resultset=Resting.getByXML("http://local.yahooapis.com/MapsService/V1/geocode?appid=YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--&state=CA", ResultSet.class, alias);
		//System.out.println(resultset.toString());
//		RequestParams params = new BasicRequestParams();
//		params.add("facets", "[\"brandNameFacet\"]");
//		params.add("key", "ff25d179c8b2b8c4f40c78a37b458fd0415ccb6d");
		
		//ServiceResponse serviceResponse=RestingHelper.execute("http://api.zappos.com/Search",params,OperationType.GET);
		//List<Facets> facets=Resting.getByJSON("http://api.zappos.com/Search",params, Facets.class, "facets");
		//System.out.println(facets.get(0).getValues().size());
		RequestParams jsonparams = new BasicRequestParams();
		jsonparams.add("key", "ff25d179c8b2b8c4f40c78a37b458fd0415ccb6d");
		List<Product> products=Resting.getByJSON("http://api.zappos.com/Product/7564933,7590514",80,jsonparams, Product.class, "product");
		System.out.println(products.get(0).getProductId());

	}

}//Test 
