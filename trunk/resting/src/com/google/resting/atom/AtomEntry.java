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
package com.google.resting.atom;

import java.util.List;

/**
 * @author lakshmipriya-p
 *
 */
public class AtomEntry extends AtomElement {

	private List<AtomAuthor> author;
	private List<AtomCategory> category;
	private String content;
	private AtomAuthor contributor;
	private String id;
	private List<AtomLink> link;
	private String published;
	private String rights;
	private AtomFeed source;
	private String summary;
	private String title;
	private String updated;
	
	public List<AtomAuthor> getAuthor() {
		return author;
	}
	public void setAuthor(List<AtomAuthor> l) {
		this.author = l;
	}
	public List<AtomCategory> getCategory() {
		return category;
	}
	public void setCategory(List<AtomCategory> l) {
		this.category = l;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public AtomAuthor getContributor() {
		return contributor;
	}
	public void setContributor(AtomAuthor contributor) {
		this.contributor = contributor;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<AtomLink> getLink() {
		return link;
	}
	public void setLink(List<AtomLink> l) {
		this.link = l;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public AtomFeed getSource() {
		return source;
	}
	public void setSource(AtomFeed source) {
		this.source = source;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	
}