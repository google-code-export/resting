package com.google.resting.transform.impl.atom;

public class AtomCategory {

	private String term;
	private String label;
	
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
	
	public String toString() {
		return "term=" + term + "label=" + label;
	}
}
