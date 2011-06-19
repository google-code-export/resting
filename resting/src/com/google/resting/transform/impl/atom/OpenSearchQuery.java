package com.google.resting.transform.impl.atom;

@XmlInfo(tag="Query", namespace = "http://a9.com/-/spec/opensearch/1.1/")
public class OpenSearchQuery {

	private String role;
	private String searchTerms;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSearchTerms() {
		return searchTerms;
	}
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	
	public String toString() {
		return "role=" + role + "search terms=" + searchTerms;
	}
}
