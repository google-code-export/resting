package test.com.google.resting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.google.resting.transform.impl.atom.AtomFeed;
import com.google.resting.transform.impl.atom.AtomTransformer;

import junit.framework.TestCase;

public class TransformerTest extends TestCase {

	public void testGetEntityList() {
		try {
			FileInputStream fis = new FileInputStream(new File(
			"C:\\apps\\samplefeed.txt"));
			byte[] contentData = new byte[fis.available()];
			fis.read(contentData);
			String atomfeed = new String(contentData);
			System.out.println(new AtomTransformer().createEntity(atomfeed, AtomFeed.class));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
	
}
