package test.com.google.resting.vo;



import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import com.thoughtworks.xstream.converters.basic.StringConverter;

public class MyStringConverter extends StringConverter  {
	@Override
	public boolean canConvert(Class clazz) {
		return clazz.equals(Standard.class);
	}

	@Override
	public Object fromString(String message) {
		Standard standard=new Standard();
		//standard.setId(null);
		
		
		return standard;
		
		
	}
	

}
