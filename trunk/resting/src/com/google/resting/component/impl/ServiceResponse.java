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

package com.google.resting.component.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.CharArrayBuffer;

import com.google.resting.Resting;
import com.google.resting.util.IOUtils;
/**
 * Wrapper object for REST response. This entity encapsulates the HTTP status code, the HTTP response body as a String and 
 * the HTTP headers of the REST response. {@link Resting} APIs like {@link Resting.get()}, {@link Resting.put()}, 
 * {@link Resting.post()} and {@link Resting.delete()} return an instance of this object. 
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public class ServiceResponse {
	
	private int statusCode = 500;
	private String responseString=null;
	private Header[] responseHeaders=null;

	public ServiceResponse(HttpResponse response, String charset) {
		assert response!=null:"HttpResponse should not be null";
		InputStream inputStream=null;	
		try {
			if (response ==null){} 
			HttpEntity entity = response.getEntity();
			this.statusCode = response.getStatusLine().getStatusCode();
			this.responseHeaders=response.getAllHeaders();
			inputStream=entity.getContent();
			this.responseString=IOUtils.writeToString(inputStream, charset);
			//this.responseString=IOUtils.toString(inputStream, charset);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(inputStream);
		}//try
		
	}//ServiceResponse
	
	public int getStatusCode() {
		return statusCode;
	}//getStatusCode

	public String getResponseString() {
		return responseString;
	}//getResponseString
	
	public Header[] getResponseHeaders(){
		return responseHeaders;
	}//getResponseHeaders

	
	public String toString(){
		int length=responseString.length()+3+96;
		 for(Header header:responseHeaders){
			 length+=header.getName().length()+header.getValue().length()+3;
		 }
		CharArrayBuffer buffer= new CharArrayBuffer(length);
		buffer.append("\nServiceResponse\n---------------\nHTTP Status: ");
		buffer.append(statusCode);
		buffer.append("\nHeaders: \n");
		 for(Header header:responseHeaders){
			 buffer.append(header.getName());
			 buffer.append(" : ");
			 buffer.append(header.getValue());
			 buffer.append("\n");
		 }		
		buffer.append("Response body: \n");
		buffer.append(responseString);
		buffer.append("----------------\n");

		return buffer.toString();
	}//toString

}//ServiceResponse
