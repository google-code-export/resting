package com.google.resting.component.content.contentdecorator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.content.ContentType;
import com.google.resting.component.content.IContentData;

public class StringContentData implements IContentData {
	
	private String content=null;
	private int contentLength=0;	

	public StringContentData(byte[] bytes, EncodingTypes charset) {
		try {
			this.contentLength=bytes.length;
			this.content=new String(bytes,charset.getName());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getContent() {
		return content;
	}

	public int getContentLength() {
		return contentLength;
	}

	@Override
	public String getContentInString() {
		return content;
	}
	
	@Override
	public String toString(){
		return content;
	}

}
