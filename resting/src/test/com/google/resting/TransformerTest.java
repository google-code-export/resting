package test.com.google.resting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import junit.framework.TestCase;
import test.com.google.resting.vo.OpenSearchQuery;
import test.com.google.resting.vo.SampleFeed;
import test.com.google.resting.vo.SampleFeedWithObjectRef;

import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.impl.atom.AtomFeed;
import com.google.resting.transform.impl.atom.AtomTransformer;
import com.google.resting.util.ReflectionUtil;

public class TransformerTest extends TestCase {

	public void testAtomCreateEntity1() {
		System.out.println("\ntestAtomCreateEntity1\n");
		try {
			URL dirURL = TransformerTest.class.getClassLoader().getResource(
					"atom");
			if (dirURL != null && dirURL.getProtocol().equals("file")) {
				String[] feeds = new File(dirURL.toURI()).list();
				System.out.println("Number of files " + feeds.length);
				for (String aFeed : feeds) {
					if (aFeed.endsWith(".xml")) {
						System.out.println(dirURL.getFile() + "\\" + aFeed);
						File file = new File(dirURL.getFile()
								+ System.getProperty("file.separator") + aFeed);
						byte[] buffer = new byte[(int) file.length()];
						BufferedInputStream f = null;
						try {
							f = new BufferedInputStream(new FileInputStream(
									file));
							f.read(buffer);
						} finally {
							if (f != null)
								try {
									f.close();
								} catch (IOException ignored) {
									ignored.printStackTrace();
								}
						}
						String feedContent = new String(buffer);
						XMLAlias xmlAlias = new XMLAlias();
						AtomFeed feedObject = new AtomTransformer<AtomFeed>()
								.createEntity(feedContent, AtomFeed.class,
										xmlAlias);
						System.out.println("Response Feed details "
								+ ReflectionUtil.describe(feedObject,
										AtomFeed.class, new StringBuffer())
										.toString());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testAtomCreateEntity2() {
		System.out.println("\ntestAtomCreateEntity2\n");
		String aLine = null;
		try {
			InputStream is = TransformerTest.class
					.getResourceAsStream("/atom/feed_custom_namespace.xml");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuffer contentData = new StringBuffer("");
			while ((aLine = reader.readLine()) != null) {
				contentData.append(aLine);
			}
			XMLAlias xmlAlias = new XMLAlias();
			SampleFeed feedObject = new AtomTransformer<SampleFeed>()
					.createEntity(contentData.toString(), SampleFeed.class, xmlAlias);
			System.out.println("Response Feed details "
					+ ReflectionUtil.describe(feedObject, SampleFeed.class,
							new StringBuffer()).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testAtomCreateEntity3() {
		System.out.println("\ntestAtomCreateEntity3\n");
		String aLine = null;
		try {
			InputStream is = TransformerTest.class
					.getResourceAsStream("/atom/feed_custom_namespace.xml");
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(is));
			StringBuffer contentData = new StringBuffer("");
			while ((aLine = reader.readLine()) != null) {
				contentData.append(aLine);
			}
			XMLAlias xmlAlias = new XMLAlias();
			xmlAlias.add("query", OpenSearchQuery.class);
			SampleFeedWithObjectRef feedObject = new AtomTransformer<SampleFeedWithObjectRef>()
					.createEntity(contentData.toString(),
							SampleFeedWithObjectRef.class, xmlAlias);
			System.out.println("Response Feed details "
					+ ReflectionUtil.describe(feedObject,
							SampleFeedWithObjectRef.class, new StringBuffer())
							.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
