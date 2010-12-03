 /*
 * Copyright (C) 2010 Google Code.
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

package com.google.resting.component.impl;

import org.apache.http.util.CharArrayBuffer;

/**
 * Name - value pair entity
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */
public class NameValueEntity {
	
	private final String name;
	private final String value;
	
	public NameValueEntity(String name, String value){
		this.name=name;
		this.value=value;
	}
	public String getName() {
		return name;
	}
	public String getValue() {
		return value;
	}
	@Override
	public boolean equals(Object obj){
		//check if null
		if(obj == null)
			return false;
		//check if the current instance
		if (obj == this)
			return true;
		//check for type
		if (obj instanceof NameValueEntity){
			NameValueEntity entity=(NameValueEntity)obj;
			return entity.name.equals(this.name)&& entity.value.equals(this.value);			
		}
		//else return false
		return false;
	}//equals
	
	@Override
	public int hashCode(){
		//TODO To be implemented
		return 420;
	}//hashCode
	
	public String toString(){
		if(value==null)
			return name;
		else{
			int length=name.length()+1+value.length();
			CharArrayBuffer buffer=new CharArrayBuffer(length);
			buffer.append(name);
			buffer.append("=");
			buffer.append(value);
			return buffer.toString();
		}
		
	}//toString

}//NameValueEntity
