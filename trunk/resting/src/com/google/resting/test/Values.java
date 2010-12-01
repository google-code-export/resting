package com.google.resting.test;

public class Values {
	private String name;
	private String count;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	public String toString(){
		return name+"="+count+"\n";
	}
	
}
