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
 * Priority for converters
 * 
 * @author sujata.de
 * @since resting 0.5
 *
 */

public enum Priority {
	LOW(XStream.PRIORITY_LOW),
	NORMAL(XStream.PRIORITY_NORMAL),
	VERY_HIGH(XStream.PRIORITY_VERY_HIGH),
	VERY_LOW(XStream.PRIORITY_VERY_LOW);
	
	private int xstreamPriority;
	public int getXStreamPriority(){
		return xstreamPriority;
	}
	private Priority(int xstreamPriority){
		this.xstreamPriority=xstreamPriority;
	}
}
