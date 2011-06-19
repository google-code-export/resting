package test.com.google.resting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import junit.framework.TestCase;

import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.impl.atom.AtomFeed;
import com.google.resting.transform.impl.atom.AtomTransformer;

public class TransformerTest extends TestCase {

	public void testAtomGetEntityList() {
		System.out.println("\ntestAtomGetEntityList\n");
		String aLine = null;
		try {
			InputStream is = TransformerTest.class
					.getResourceAsStream("/feed.txt");
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuffer contentData = new StringBuffer("");
			while ((aLine = reader.readLine()) != null) {
				contentData.append(aLine);
			}
			String atomfeed = contentData.toString();
			XMLAlias xmlAlias = new XMLAlias();
			List<AtomFeed> l = new AtomTransformer<AtomFeed>(true).getEntityList(
					atomfeed, AtomFeed.class, xmlAlias);
			assert l.size() == 0 : "Service response parsing failed. Check exception logs";
			System.out.println("Response Feed details " + l.get(0).toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
