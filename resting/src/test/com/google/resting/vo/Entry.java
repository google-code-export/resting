package test.com.google.resting.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Entry implements Serializable {

	private String title;
	private Link[] link;
	private float relevance;
	private Date updated;
	private String id;
	private Thumbnail thumbnail;
	private String documentSource;
	private boolean firstOfASite;
	private Author author;
	
	@SerializedName("ibmsc:field")
	private Collection<Field> field;
	private String summary;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Link[] getLink() {
		return link;
	}
	public void setLink(Link[] link) {
		this.link = link;
	}
	public float getRelevance() {
		return relevance;
	}
	public void setRelevance(float relevance) {
		this.relevance = relevance;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Thumbnail getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getDocumentSource() {
		return documentSource;
	}
	public void setDocumentSource(String documentSource) {
		this.documentSource = documentSource;
	}
	public boolean isFirstOfASite() {
		return firstOfASite;
	}
	public void setFirstOfASite(boolean firstOfASite) {
		this.firstOfASite = firstOfASite;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
/*	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}*/
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	


}
