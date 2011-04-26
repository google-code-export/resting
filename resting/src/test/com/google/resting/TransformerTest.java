package test.com.google.resting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import junit.framework.TestCase;

import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.impl.atom.AtomAuthor;
import com.google.resting.transform.impl.atom.AtomEntry;
import com.google.resting.transform.impl.atom.AtomFeed;
import com.google.resting.transform.impl.atom.AtomLink;
import com.google.resting.transform.impl.atom.AtomTransformer;

public class TransformerTest extends TestCase {

	public void testGetEntityList1() {
		try {
			FileInputStream fis = new FileInputStream(new File(
					"C:\\apps\\atomfeed-edited.txt"));
			byte[] contentData = new byte[fis.available()];
			fis.read(contentData);
			String atomfeed = new String(contentData);
			XMLAlias alias = new XMLAlias();
			System.out.println(new AtomTransformer(true).getEntityList(
					atomfeed, AtomFeed.class, alias));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
