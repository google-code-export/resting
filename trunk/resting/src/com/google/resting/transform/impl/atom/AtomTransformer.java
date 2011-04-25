package com.google.resting.transform.impl.atom;

import java.util.List;

import com.google.resting.component.Alias;
import com.google.resting.component.impl.xml.XMLAlias;
import com.google.resting.transform.Transformer;
import com.google.resting.transform.impl.XMLTransformer;

/**
 * A transformer implementation to parse ATOM feeds
 * @author lakshmipriya-p
 *
 */
public class AtomTransformer implements Transformer {
	
	@Override
	public AtomFeed createEntity(String singleEntityStream, Class targetType) {
		XMLAlias alias = new XMLAlias();
		alias.add("feed", AtomFeed.class);
		alias.add("link", AtomLink.class);
		alias.add("author", AtomAuthor.class);
		alias.add("entry", AtomEntry.class);
		List<AtomFeed> l = new XMLTransformer<AtomFeed>().getEntityList(
				singleEntityStream, AtomFeed.class, alias);
		assert (l.size() == 0) : "Atom feed could not be parsed. Check the log for errors";
		return l.get(0);
	}

	@Override
	//TODO
	public List getEntityList(Object source, Class targetType, Alias alias) {
		return null;
	}

	@Override
	//TODO
	public List getEntityList(String responseString, Class targetType,
			Alias alias) {
		return null;
	}
}
