package com.google.resting.component.impl;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.CharArrayBuffer;
/**
 * Wrapper object for REST response
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public class ServiceResponse {
	
	private int statusCode = 500;
	private String responseString=null;
	private Header[] responseHeaders=null;
	
	//Added for local testing. To be removed
	public ServiceResponse(String responseString){
		this.responseString= responseString;
	}

	public ServiceResponse(HttpResponse response) {
		assert response!=null:"HttpResponse should not be null";
		long ioStartTime=System.currentTimeMillis();
		
		InputStream inputStream=null;
		
		try {
			if (response ==null){} 
			HttpEntity entity = response.getEntity();
			this.statusCode = response.getStatusLine().getStatusCode();
			this.responseHeaders=response.getAllHeaders();
			inputStream=entity.getContent();
			this.responseString=IOUtils.toString(inputStream);
			long ioEndTime=System.currentTimeMillis();
			System.out.println("Time taken in converting IOStream: "+(ioEndTime-ioStartTime));
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
		CharArrayBuffer buffer= new CharArrayBuffer(responseString.length()+3+70);
		buffer.append("REST Response\n---------------\n HTTP Status: ");
		buffer.append(statusCode);
		buffer.append("\n Complete response body: ");
		buffer.append(responseString);
		return buffer.toString();
	}//toString

}//ServiceResponse
