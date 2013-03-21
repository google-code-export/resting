package test.com.google.resting.vo;

public class Status {
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String message;
	private String value;
	
	public String toString(){
		return "message: "+message+", value: "+value;
	}

}
