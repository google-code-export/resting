package com.google.resting.test;

import java.util.List;

public class Facets {
	private String facetField;
	private String facetFieldDisplayName;
	private List<Values> values;
	public String getFacetField() {
		return facetField;
	}
	public void setFacetField(String facetField) {
		this.facetField = facetField;
	}
	public String getFacetFieldDisplayName() {
		return facetFieldDisplayName;
	}
	public void setFacetFieldDisplayName(String facetFieldDisplayName) {
		this.facetFieldDisplayName = facetFieldDisplayName;
	}
	public List<Values> getValues() {
		return values;
	}
	public void setValues(List<Values> values) {
		this.values = values;
	}
	public String toString(){
		return facetField+"|"+facetFieldDisplayName+"|";
	}

}
