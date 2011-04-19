package test.com.google.resting.vo;

public class StatusMessage {
	private String message;

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	public String toString(){
		return "Message: "+message;
	}

}
