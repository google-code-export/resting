package com.google.resting.test;

import java.util.List;

public class ResultSet {
	private Result Result;

	public void setResult(Result Result) {
		this.Result = Result;
	}

	public Result getResult() {
		return Result;
	}
	
	public String toString(){
		return Result.toString();
	}


}
