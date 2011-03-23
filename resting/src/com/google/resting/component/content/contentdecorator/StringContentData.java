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
	private List<ContentType> contentTypes=null;
	

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

	public List<ContentType> getContentTypes() {
		if(contentTypes==null){
			contentTypes=new ArrayList<ContentType>();
			contentTypes.add(ContentType.TEXT_PLAIN);
			contentTypes.add(ContentType.TEXT_HTML);
			contentTypes.add(ContentType.TEXT_XML);
		}
		return contentTypes;
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
