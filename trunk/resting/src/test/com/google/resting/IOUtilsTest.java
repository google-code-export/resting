package test.com.google.resting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.resting.component.EncodingTypes;
import com.google.resting.util.IOUtils;

import junit.framework.TestCase;

public class IOUtilsTest extends TestCase {
	
	public void testToString(){
		File file=new File("/domain_driven_design.pdf");
		String result1="";
		String result2="";
		FileInputStream inputStream=null;
		FileInputStream inputStream2=null;
		try {
			long startTime1=System.currentTimeMillis();
			for(int i=0;i<100;i++){
			inputStream=new FileInputStream(file);
			
			result1=IOUtils.toString(inputStream, EncodingTypes.UTF8);
			
			inputStream.close();
			}
			long endTime1=System.currentTimeMillis();
			System.out.println("Time taken in toString: "+(endTime1-startTime1));
			
			long startTime2=System.currentTimeMillis();
			for(int i=0;i<100;i++){
			inputStream2=new FileInputStream(file);
			result2=IOUtils.writeToString(inputStream2, EncodingTypes.UTF8);
			inputStream2.close();
			}
			long endTime2=System.currentTimeMillis();
			System.out.println("Time taken in writeToString: "+(endTime2-startTime2));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(result1, result2);
		
	}

}
