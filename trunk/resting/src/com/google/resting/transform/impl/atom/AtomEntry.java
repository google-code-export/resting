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

import java.util.List;

@XmlInfo(tag="entry")
public class AtomEntry {
	
	private String title;
	private List<AtomLink> links;
	private AtomAuthor author;
	private String id;
	private String updated;
	private String summary;
	
	public List<AtomLink> getLinks() {
		return links;
	}
	
	public AtomAuthor getAuthor() {
		return author;
	}
	
	public String getId() {
		return id;
	}
	public String getUpdated() {
		return updated;
	}
	public String getSummary() {
		return summary;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLink(List<AtomLink> l) {
		this.links = l;
	}

	public void setAuthor(AtomAuthor author) {
		this.author = author;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
}