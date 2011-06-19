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

@XmlInfo(tag = "feed", namespace = "http://www.w3.org/2005/Atom")
public class AtomFeed {

	// atom meta data
	protected String title;
	protected String id;
	protected AtomCategory category;
	protected AtomAuthor author;
	protected String updated;

	// link
	protected AtomLink link;

	// Atom Entries
	protected List<AtomEntry> entries;
	
	protected String queryEvaluationTime;
	protected String totalResults;
	protected OpenSearchQuery Query;
	
	/**
	 * Recommended meta data per 1.0 Spec
	 * 
	 * @param author
	 * @return
	 */
	public AtomFeed addAuthor(AtomAuthor author) {
		this.author = author;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}
	
	public AtomCategory getCategory() {
		return category;
	}

	public AtomAuthor getAuthor() {
		return author;
	}

	public String getUpdated() {
		return updated;
	}

	public AtomLink getLink() {
		return link;
	}
	
	public void setEntries(List<AtomEntry> l) {
		this.entries = l;
	}

	public List<AtomEntry> getEntries() {
		return entries;
	}
	
	public String getQueryEvaluationTime() {
		return queryEvaluationTime;
	}

	public void setQueryEvaluationTime(String queryEvaluationTime) {
		this.queryEvaluationTime = queryEvaluationTime;
	}

	public String getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}

	public OpenSearchQuery getSearchQuery() {
		return Query;
	}

	public void setSearchQuery(OpenSearchQuery searchQuery) {
		this.Query = searchQuery;
	}
	
	public String toString() {
		StringBuffer out = new StringBuffer();
		out.append("title=").append(title).append(",id=").append(id).append(
				",category=").append(category.toString()).append(",updated=")
				.append(updated).append(",link=").append(link.toString())
				.append(",query evaluation time=").append(queryEvaluationTime)
				.append(",total results=").append(totalResults).append(
						",query=").append(Query.toString());
		return out.toString();
	}

}
