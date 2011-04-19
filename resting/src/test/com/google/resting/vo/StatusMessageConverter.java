package test.com.google.resting.vo;


import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

public class StatusMessageConverter extends AbstractSingleValueConverter  {

	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(StatusMessage.class);
	}

	@Override
	public Object fromString(String message) {
		StatusMessage statusMessage=new StatusMessage();
		statusMessage.setMessage(message);
		return statusMessage;
	}

}
