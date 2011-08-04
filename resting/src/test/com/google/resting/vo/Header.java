package test.com.google.resting.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Header implements Serializable {

	private static final long serialVersionUID = -9155484309537178003L;
	
	@SerializedName("es:title")
	private String title;
	@SerializedName("es:link")
	private String link;
	@SerializedName("es:collectionId")
	private String collectionId;
	@SerializedName("es:publishDate")
	private Date publishDate;
	@SerializedName("es:itemsPerPage")
	private int itemsPerPage;
	@SerializedName("es:startIndex")
	private int startIndex;
	@SerializedName("es:totalResults")
	private int totalResults;
	@SerializedName("es:availableResults")
	private int availableResults;
	@SerializedName("es:queryEvaluationTime")
	private long queryEvaluationTime;
	@SerializedName("es:result")
	private List<Entry> entries;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getAvailableResults() {
		return availableResults;
	}

	public void setAvailableResults(int availableResults) {
		this.availableResults = availableResults;
	}

	public long getQueryEvaluationTime() {
		return queryEvaluationTime;
	}

	public void setQueryEvaluationTime(long queryEvaluationTime) {
		this.queryEvaluationTime = queryEvaluationTime;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public List<Entry> getEntries() {
		return entries;
	}

}
