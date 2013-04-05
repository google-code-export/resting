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
 * String content data from REST response. 
 * 
 * @author sujata.de
 * @since resting 0.6
 */

public class StringContentData implements IContentData {
	
	private String content=null;
	private int contentLength=0;	

	public StringContentData(byte[] bytes, EncodingTypes charset) {
		try {
			this.contentLength=bytes.length; 
			if(charset!=null)
				this.content=new String(bytes,charset.getName());
			else 
				this.content=new String(bytes);
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
