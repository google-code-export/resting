package test.com.google.resting.vo;

import com.google.resting.atom.AtomFeed;

public class SampleFeed extends AtomFeed {
	private String queryEvaluationTime;
	private String totalResults;
	
	public String getQueryEvaluationTime() {
		return queryEvaluationTime;
	}
	public void setQueryEvaluationTime(String queryEvaluationTime) {
		this.queryEvaluationTime = queryEvaluationTime;
	}
	public String getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
}
