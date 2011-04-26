package com.google.resting.transform.impl.atom;

import java.util.List;


@XmlInfo(tag = "feed", namespace = "http://www.w3.org/2005/Atom")
public class AtomFeed extends AtomElement {

	// atom meta data
	private String title;
	private String id;
	private String category;
	private AtomAuthor author;
	private String updated;

	// link
	private List<AtomLink> links;

	// Atom Entries
	private List<AtomEntry> entries;

	/**
	 * Avoid the construction without mandatory meta data
	 */
	@SuppressWarnings("unused")
	private AtomFeed() {
		super(null);
	}

	/**
	 * Constructs an Atom Feed object 
	 * Id, title and timestamp are mandatory
	 * metadata as per Atom 1.0 specification
	 * 
	 * @param id
	 * @param title
	 * @param ts
	 */
	public AtomFeed(String id, String title, String ts) {
		super("feed", "http://www.w3.org/2005/Atom");
		this.id = id;
		this.title = title;
		this.updated = ts;
	}

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
	
	public String getCategory() {
		return category;
	}

	public AtomAuthor getAuthor() {
		return author;
	}

	public String getUpdated() {
		return updated;
	}

	public List<AtomLink> getLinks() {
		return links;
	}

	public List<AtomEntry> getEntries() {
		return entries;
	}
	
}
