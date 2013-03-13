 /*
 * Copyright (C) 2010 Google Code.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.com.google.resting.vo;

public class Result {
	
	private int quality;
	private float latitude  ;
	private float longitude  ;
	private float offsetlat ;
	private float offsetlon ;
	private float radius ;
	
	private String  name;
	private String  line1;
	private String line2;
	
	private String  line3 ;
	private String  line4;
	private String  house;
	private String  street;
	
	private String  xstreet ;
	
	private String unittype  ;
	private String  unit ;
	private String  postal;
	private String  neighborhood ;
	private String city ;
	private String county;
	private String state;
	private String country;
	private String countrycode;
	private String statecode;
	private String countycode;
	private String hash ;
	private int woeid;
	private int woetype;
	private int uzip;
	
	
	public String toString(){
		return "Latitude: "+latitude+", Longitude: "+longitude+", Postal code: "+postal;
	}
	
	
	

}
