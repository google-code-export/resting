package test.com.google.resting.vo;

import java.io.Serializable;

public class OFCollection implements Serializable {

	private String collectionId;
	private String configFile;
	private String description;
	private String displayId;
	private String displayName;
	private boolean externalCollection;
	private int flags;
	private String collectionDataDir;
	private String indexDataDir;
	private int indexType;
	private boolean partitioned;
	private int numPartitions;
	private boolean security;

	public OFCollection() {
		
	}

	public OFCollection(String collectionId, String configFile,
			String description, String displayId, String displayName,
			boolean externalCollection, int flags, String collectionDataDir,
			String indexDataDir, int indexType, boolean partitioned,
			int numPartitions, boolean security) {
		this.collectionId = collectionId;
		this.configFile = configFile;
		this.description = description;
		this.displayId = displayId;
		this.displayName = displayName;
		this.externalCollection = externalCollection;
		this.flags = flags;
		this.collectionDataDir = collectionDataDir;
		this.indexDataDir = indexDataDir;
		this.indexType = indexType;
		this.partitioned = partitioned;
		this.numPartitions = numPartitions;
		this.security = security;
	}

	public String getCollectionId() {
		return collectionId;
	}


	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}


	public String getConfigFile() {
		return configFile;
	}


	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDisplayId() {
		return displayId;
	}


	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}


	public String getDisplayName() {
		return displayName;
	}


	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public boolean isExternalCollection() {
		return externalCollection;
	}


	public void setExternalCollection(boolean externalCollection) {
		this.externalCollection = externalCollection;
	}


	public int getFlags() {
		return flags;
	}


	public void setFlags(int flags) {
		this.flags = flags;
	}


	public String getCollectionDataDir() {
		return collectionDataDir;
	}


	public void setCollectionDataDir(String collectionDataDir) {
		this.collectionDataDir = collectionDataDir;
	}


	public String getIndexDataDir() {
		return indexDataDir;
	}


	public void setIndexDataDir(String indexDataDir) {
		this.indexDataDir = indexDataDir;
	}


	public int getIndexType() {
		return indexType;
	}


	public void setIndexType(int indexType) {
		this.indexType = indexType;
	}


	public boolean isPartitioned() {
		return partitioned;
	}


	public void setPartitioned(boolean partitioned) {
		this.partitioned = partitioned;
	}


	public int getNumPartitions() {
		return numPartitions;
	}


	public void setNumPartitions(int numPartitions) {
		this.numPartitions = numPartitions;
	}


	public boolean isSecurity() {
		return security;
	}


	public void setSecurity(boolean security) {
		this.security = security;
	}


	public String toString() {
		StringBuffer sb = new StringBuffer(3);

		sb.append("collectionId = " + collectionId + ", ");
		sb.append("configFile = " + configFile + ", ");
		sb.append("description = " + description + ", ");
		sb.append("displayId = " + displayId + ", ");
		sb.append("displayName = " + displayName + ", ");
		sb.append("externalCollection = " + externalCollection + ", ");
		sb.append("flags = " + flags + ", ");
		sb.append("collectionDataDir = " + collectionDataDir + ", ");
		sb.append("indexDataDir = " + indexDataDir + ", ");
		sb.append("indexType = " + indexType + ", ");
		sb.append("partitioned = " + partitioned + ", ");
		sb.append("numPartitions = " + numPartitions + ", ");
		sb.append("security = " + security + ", ");

		return sb.toString();
	}
}
