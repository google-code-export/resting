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

package com.google.resting.method.get;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.Verb;
import com.google.resting.component.impl.URLContext;
/**
 * Implementation of ServiceContext for HTTP GET operation
 * 
 * @author sujata.de
 * @since resting 0.2
 */

public class GetServiceContext extends ServiceContext {
	
	private List<NameValuePair> inputParams=null;
	private String path=null;
	private String contextPathElement=null;

	public GetServiceContext(URLContext urlContext, RequestParams requestParams, EncodingTypes encoding, List<Header> inputHeaders ){
		super(urlContext,requestParams, Verb.GET, encoding, inputHeaders);
		this.contextPathElement=urlContext.getContextPath();
		if(requestParams !=null)	this.inputParams=requestParams.getRequestParams();
		this.path=this.contextPathElement+getParamPathElement();
	//	System.out.println( "The path is "+path);
	}//GetServiceContext
	
	public String getContextPathElement(){
		return contextPathElement;
	}//getContextPathElement
	
	public List<NameValuePair> getInputParams(){
		return inputParams;
	}//getInputParams
	
	public String getPath(){
		assert path!=null:"Path in service context should not be null normally.";
		return path;
	}//getPath

	private String getParamPathElement(){
		if(inputParams == null) return "";
		StringBuilder combinedParams = new StringBuilder("");
		
		int i=0;
			for (NameValuePair inputParam : inputParams){
				
				try {
					if (i > 0)
						combinedParams.append("&").append(inputParam.getName()).append("=").append(URLEncoder.encode(inputParam.getValue(), getCharset().getName()));
					else
						combinedParams.append(inputParam.getName()).append("=").append(URLEncoder.encode(inputParam.getValue(), getCharset().getName()));
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}


				i=1;
			}//for
		if(i==1) 
			return "?"+combinedParams.toString();
		else
			return combinedParams.toString();
	}//getParamPathElement

}//GetServiceContext
