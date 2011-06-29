package test.com.google.resting.vo;

public class SampleFeedWithObjectRef extends SampleFeed {

	private OpenSearchQuery query;

	public OpenSearchQuery getQuery() {
		return query;
	}

	public void setQuery(OpenSearchQuery query) {
		this.query = query;
	}
}
