package com.google.resting.component.content.contentdecorator;

import java.io.UnsupportedEncodingException;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.content.IContentData;

public class ByteContentData implements IContentData {
	
	private byte[] content=null;
	private int contentLength=0;
	private String contentInString=null;
	

	public ByteContentData(byte[] bytes) {
		this.contentLength=bytes.length;
		this.content=bytes;
	}

	public byte[] getContent() {
		return content;
	}

	@Override
	public int getContentLength() {
		return contentLength;
	}

	@Override
	public String getContentInString() {
		if(contentInString==null)
			try {
				contentInString=new String(content, EncodingTypes.UTF8.getName());
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return contentInString;
	}
	
	@Override
	public String toString(){
		return getContentInString();
	}

}
