package test.com.google.resting.vo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Entry implements Serializable {

	@SerializedName("es:itemsPerPage")
	private int itemsPerPage;
	
	@SerializedName("es:startIndex")
	private int startIndex;
	
	@SerializedName("es:totalResults")
	private int totalResults;
	
	@SerializedName("es:queryEvaluationTime")
	private long queryEvaluationTime;
	
	@SerializedName("es:result")
	private List<MyResult> results;
	

	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public int getTotalResults() {
		return totalResults;
	}

	public long getQueryEvaluationTime() {
		return queryEvaluationTime;
	}
	public List<MyResult> getResults() {
		return results;
	}


	


}
