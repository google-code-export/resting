package com.google.resting.transform.impl.atom;

/**
 * @author lakshmipriya-p
 *
 */
@XmlInfo(tag="entry")
public class AtomAuthor extends AtomElement {
	
	  private String name;
	  private String emailAddress;
	  private String uri;
	  
	  public AtomAuthor(String name, String emailAddress, String uri) {
		  super("author");
		  this.name = name;
		  this.emailAddress = emailAddress;
		  this.uri = uri;
	  }

	public String getName() {
		return name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getUri() {
		return uri;
	}
}
