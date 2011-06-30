package test.com.google.resting.vo;

import java.util.List;

public class APIResponse {
	
	private List<OFCollection> collectionConfig;

	public List<OFCollection> getEntries() {
		return collectionConfig;
	}

	public void setEntries(List<OFCollection> entries) {
		this.collectionConfig = entries;
	}

}
