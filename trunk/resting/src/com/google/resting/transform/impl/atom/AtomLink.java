package com.google.resting.transform.impl.atom;

/**
 * @author lakshmipriya-p
 */
@XmlInfo(tag="link")
public class AtomLink extends AtomElement {

	private String rel;
	private String href;
	
	public AtomLink(String rel, String href) {
		super("link");
		this.rel = rel;
		this.href = href;
	}

	public String getRel() {
		return rel;
	}

	public String getHref() {
		return href;
	}
	
}
