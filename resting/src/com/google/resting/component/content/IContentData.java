package com.google.resting.component.content;

import com.google.resting.component.content.ContentType;

public interface IContentData {
	
	public Object getContent();
	public ContentType getContentType();
	public int getContentLength();
	public String getContentInString();

}
