package com.google.resting.test;

public class Result {
	private String precision;
	private String Latitude;
	private String Longitude;
	private String Address;
	private String City;
	private String State;
	private String Zip;
	private String Country;
	
	public Result(String precision){
		this.precision=precision;
	}
	
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}

	public String toString(){
		return "precision="+precision+", latitude="+Latitude+", " +
				"Longitude="+Longitude+", Address="+Address+", "+
				"City= "+City+", ZIP= "+Zip+", State="+State+
				", Country="+Country;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZip() {
		return Zip;
	}
	public void setZip(String zip) {
		Zip = zip;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
	}
}
