package test.com.google.resting.vo;

import com.google.gson.annotations.SerializedName;

public class Field {

	private String id ;
	private String type;
	private boolean  contentSearchable;
	private boolean fieldSearchable;
	private boolean parametric;
	
	private boolean returnable;
	private boolean sortable;
	
	@SerializedName("#text") 
	private String text;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isContentSearchable() {
		return contentSearchable;
	}
	public void setContentSearchable(boolean contentSearchable) {
		this.contentSearchable = contentSearchable;
	}
	public boolean isFieldSearchable() {
		return fieldSearchable;
	}
	public void setFieldSearchable(boolean fieldSearchable) {
		this.fieldSearchable = fieldSearchable;
	}
	public boolean isParametric() {
		return parametric;
	}
	public void setParametric(boolean parametric) {
		this.parametric = parametric;
	}
	public boolean isReturnable() {
		return returnable;
	}
	public void setReturnable(boolean returnable) {
		this.returnable = returnable;
	}
	public boolean isSortable() {
		return sortable;
	}
	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
