package com.google.resting.test;

import com.google.resting.Resting;
import com.google.resting.component.impl.RequestParams;

public class Test {
	public static void main(String[] args){
	
		 RequestParams params=new RequestParams();
		 params.add("appid","YD-9G7bey8_JXxQP6rxl.fBFGgCdNjoDMACQA--");
		 params.add("state","CA");
		System.out.println(Resting.get("http://local.yahooapis.com/MapsService/V1/geocode",  params).getResponseString());
		
	}

}
