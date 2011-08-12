 /*
 * Copyright (C) 2011 Google Code.
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
package com.google.resting.component.content.contentdecorator;

import java.io.UnsupportedEncodingException;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.content.IContentData;

/**
 * Byte content data from REST response
 * 
 * @author sujata.de
 * @since resting0.6
 *
 */
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
