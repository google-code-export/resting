package com.google.resting.component.content.contentdecorator;

import java.io.InputStream;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.content.ContentType;
import com.google.resting.component.content.IContentData;

public class ImageContentData implements IContentData{

	public ImageContentData(InputStream inputStream, EncodingTypes charset) {
	}

	public Object getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	public ContentType getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getContentLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContentInString() {
		// TODO Auto-generated method stub
		return null;
	}

}
