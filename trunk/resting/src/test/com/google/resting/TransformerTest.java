package test.com.google.resting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import junit.framework.TestCase;

import test.com.google.resting.vo.APIResponse;
import test.com.google.resting.vo.OFCollection;
import test.com.google.resting.vo.OpenSearchQuery;
import test.com.google.resting.vo.SampleFeed;
import test.com.google.resting.vo.SampleFeedWithObjectRef;

import com.google.resting.atom.AtomFeed;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.impl.atom.AtomTransformer;
import com.google.resting.util.ReflectionUtil;

public class TransformerTest extends TestCase {
	
	private String getResource(String resourcePath) throws Exception {
		String aLine = null;
		StringBuffer contentData = new StringBuffer("");
		InputStream is = TransformerTest.class
				.getResourceAsStream(resourcePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		while ((aLine = reader.readLine()) != null) {
			contentData.append(aLine);
		}
		return contentData.toString();
	}
	
	public String getLocalResource(String theName) {
        try {
            InputStream input;
            input = TransformerTest.class.getResourceAsStream(theName);
            if (input == null) {
                throw new RuntimeException("Can not find " + theName);
            }
            BufferedInputStream is = new BufferedInputStream(input);
            StringBuilder buf = new StringBuilder(3000);
            int i;
            try {
                while ((i = is.read()) != -1) {
                    buf.append((char) i);
                }
            } finally {
                is.close();
            }
            String resource = buf.toString();
            // convert EOLs
            String[] lines = resource.split("\\r?\\n");
            StringBuilder buffer = new StringBuilder();
            for (int j = 0; j < lines.length; j++) {
                buffer.append(lines[j]);
                buffer.append("\n");
            }
            return buffer.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * Parse sample atom xml files from the resources/atom folder 
	 */
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

	/**
	 * Parse xml file with custom namespace and custom attributes  
	 */
	public void testAtomCreateEntity2() {
		System.out.println("\ntestAtomCreateEntity2\n");
		try {
			String contentData = this
					.getResource("/atom/feed_custom_namespace.xml");
			XMLAlias xmlAlias = new XMLAlias();
			SampleFeed feedObject = new AtomTransformer<SampleFeed>()
					.createEntity(contentData.toString(), SampleFeed.class, xmlAlias);
			System.out.println("Response Feed details "
					+ ReflectionUtil.describe(feedObject, SampleFeed.class,
							new StringBuffer()).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Parse xml with custom namespace, attributes and object reference
	 */
	public void testAtomCreateEntity3() {
		System.out.println("\ntestAtomCreateEntity3\n");
		String aLine = null;
		try {
			String contentData = this
					.getResource("/atom/feed_custom_namespace.xml");
			XMLAlias xmlAlias = new XMLAlias();
			xmlAlias.add("query", OpenSearchQuery.class);
			SampleFeedWithObjectRef feedObject = new AtomTransformer<SampleFeedWithObjectRef>()
					.createEntity(contentData.toString(),
							SampleFeedWithObjectRef.class, xmlAlias);
			System.out.println("Response Feed details "
					+ ReflectionUtil.describe(feedObject,
							SampleFeedWithObjectRef.class, new StringBuffer())
							.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Parse atom collection xml
	 */
	public void testAtomCreateEntity4() {
		System.out.println("\ntestAtomCreateEntity4\n");
		String aLine = null;
		try {
			String contentData = this.getResource("/atom/collection.xml");
			XMLAlias xmlAlias = new XMLAlias();
			xmlAlias.add("apiResponse", APIResponse.class);
			xmlAlias.add("collectionConfig", OFCollection.class);
			APIResponse feedObject = new AtomTransformer<APIResponse>()
					.createEntity(contentData.toString(),
							APIResponse.class, xmlAlias);
			System.out.println("Response Feed details "
					+ ReflectionUtil.describe(feedObject,
							APIResponse.class, new StringBuffer())
							.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
