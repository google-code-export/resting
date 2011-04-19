package com.google.resting.component.impl.xml;
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
import com.thoughtworks.xstream.XStream;
/**
 * Reference mode for object graph. A deserialized graph will keep objects intact, including circular references. 
 * 
 * @author sujata.de
 * @since resting 0.5
 *
 */
public enum ReferenceMode {
	ID_REFERENCES(XStream.ID_REFERENCES),
	XPATH_RELATIVE_REFERENCES(XStream.XPATH_RELATIVE_REFERENCES),
	XPATH_ABSOLUTE_REFERENCES(XStream.XPATH_ABSOLUTE_REFERENCES),
	NO_REFERENCES(XStream.NO_REFERENCES);
	
	private int xstreamMode;
	
	public int getXStreamMode(){
		return xstreamMode;
	}//getXStreamMode
	
	private ReferenceMode(int xstreamMode){
		this.xstreamMode=xstreamMode;
	}//constructor
}//ReferenceMode
