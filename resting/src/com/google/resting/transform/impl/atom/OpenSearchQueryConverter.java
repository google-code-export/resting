package com.google.resting.transform.impl.atom;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class OpenSearchQueryConverter implements Converter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		OpenSearchQuery q = new OpenSearchQuery();         
		q.setRole(reader.getAttribute("role"));         
		q.setSearchTerms(reader.getAttribute("searchTerms"));
		return q; 
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(OpenSearchQuery.class);
	}
	
}
