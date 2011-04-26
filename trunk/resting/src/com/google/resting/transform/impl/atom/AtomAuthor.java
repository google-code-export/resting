package com.google.resting.transform.impl.atom;

/**
 * @author lakshmipriya-p
 *
 */
@XmlInfo(tag="entry")
public class AtomAuthor extends AtomElement {
	
	  private String name;
	  private String email;
	  private String uri;
	  
	  public AtomAuthor(String name, String email, String uri) {
		  super("author");
		  this.name = name;
		  this.email = email;
		  this.uri = uri;
	  }

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUri() {
		return uri;
	}
}
