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
package com.google.resting.transform.impl.atom;

/**
 * @author lakshmipriya-p
 */
@XmlInfo(tag="link")
public class AtomLink extends AtomElement {

	private String rel;
	private String href;
	private String type;
	
	public AtomLink(String rel, String href) {
		super("link");
		this.rel = rel;
		this.href = href;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRel() {
		return rel;
	}

	public String getHref() {
		return href;
	}
	
}
