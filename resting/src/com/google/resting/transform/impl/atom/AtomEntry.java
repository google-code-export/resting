package com.google.resting.transform.impl.atom;

@XmlInfo(tag="entry")
public class AtomEntry extends AtomElement {
	
	private String title;
	private AtomLink link;
	private String id;
	private String updated;
	private String summary;
	
	public AtomEntry(String title, AtomLink link, String id, String updated,
			String summary) {
		super("entry");
		this.title = title;
		this.link = link;
		this.id = id;
		this.updated = updated;
		this.summary = summary;
	}
	public String getTitle() {
		return title;
	}
	public AtomLink getLink() {
		return link;
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
