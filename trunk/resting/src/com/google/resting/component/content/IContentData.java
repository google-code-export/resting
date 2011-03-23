package com.google.resting.component.content;

import java.util.List;


public interface IContentData {
	
	public Object getContent();
	public List<ContentType> getContentTypes();
	public int getContentLength();
	public String getContentInString();

}
