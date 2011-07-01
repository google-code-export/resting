package com.google.resting.transform.impl.atom;

/**
 * @author lakshmipriya-p
 *
 */
public class AtomCategory extends AtomElement {

	private String term;
	private String label;
	private String scheme;
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
}
