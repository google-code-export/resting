package com.google.resting.component.content.contentdecorator;

import java.util.ArrayList;
import java.util.List;

import com.google.resting.component.content.ContentType;
import com.google.resting.component.content.IContentData;

public class ByteContentData implements IContentData {
	
	private byte[] content=null;
	private int contentLength=0;
	private String contentInString=null;
	private List<ContentType> contentTypes=null;
	

	public ByteContentData(byte[] bytes) {
		this.contentLength=bytes.length;
		this.content=bytes;
	}

	public byte[] getContent() {
		return content;
	}

	public List<ContentType> getContentTypes() {
		if(contentTypes==null){
			contentTypes=new ArrayList<ContentType>();
			contentTypes.add(ContentType.APPLICATION_OCTET_STREAM);
		}
		return contentTypes;
	}

	@Override
	public int getContentLength() {
		return contentLength;
	}

	@Override
	public String getContentInString() {
		if(contentInString==null)
			contentInString=new String(content);
		return contentInString;
	}
	
	@Override
	public String toString(){
		return getContentInString();
	}

}
