package com.google.resting.rest.util.oauth;
/**
 * This class will contain all the logic of creating timestamps for query params
 * Oauth 1.0.
 * @author sujata.de
 *
 */
public class TimeStampUtil {
	
	protected static String getTimeStamp(){
		return Long.toString(System.currentTimeMillis()/1000);
	}


}
