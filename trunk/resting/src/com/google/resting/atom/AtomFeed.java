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

public class AtomFeed extends AtomElement {

	// atom meta data
	protected List<AtomCategory> category;
	protected List<AtomAuthor> author;
	protected List<AtomAuthor> contributor;
	protected AtomGenerator generator;
	protected String icon;
	protected String id;
	protected List<AtomLink> link;
	protected String logo;
	protected String published;
	protected String rights;
	protected AtomFeed source;
	protected String subTitle;
	protected String summary;
	protected String title;
	protected String updated;

	// Atom Entries
	protected List<AtomEntry> entry;

	/*protected String queryEvaluationTime;
	protected String totalResults;
	protected OpenSearchQuery Query;*/

	public List<AtomCategory> getCategory() {
		return category;
	}

	public void setCategories(List<AtomCategory> l) {
		this.category = l;
	}

	public List<AtomAuthor> getAuthor() {
		return author;
	}

	public void setAuthors(List<AtomAuthor> l) {
		this.author = l;
	}

	public List<AtomAuthor> getContributor() {
		return contributor;
	}

	public void setContributor(List<AtomAuthor> l) {
		this.contributor = l;
	}

	public AtomGenerator getGenerator() {
		return generator;
	}

	public void setGenerator(AtomGenerator generator) {
		this.generator = generator;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
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

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
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

	public List<AtomEntry> getEntries() {
		return entry;
	}

	public void setEntries(List<AtomEntry> l) {
		this.entry = l;
	}
}
