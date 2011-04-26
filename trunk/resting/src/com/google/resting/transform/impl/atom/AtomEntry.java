package com.google.resting.transform.impl.atom;

import java.util.List;

@XmlInfo(tag="entry")
public class AtomEntry extends AtomElement {
	
	private String title;
	private List<AtomLink> links;
	private List<AtomAuthor> authors;
	private String id;
	private String updated;
	private String summary;
	
	public AtomEntry(String title, List<AtomLink> links, List<AtomAuthor> authors, String id, String updated,
			String summary) {
		super("entry");
		this.title = title;
		this.links = links;
		this.authors = authors;
		this.id = id;
		this.updated = updated;
		this.summary = summary;
	}
	
	public List<AtomLink> getLinks() {
		return links;
	}
	
	public List<AtomAuthor> getAuthors() {
		return authors;
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
	
}