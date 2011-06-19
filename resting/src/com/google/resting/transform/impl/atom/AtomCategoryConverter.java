package com.google.resting.transform.impl.atom;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AtomCategoryConverter implements Converter {

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		AtomCategory category = new AtomCategory();         
		category.setTerm(reader.getAttribute("term"));         
		category.setLabel(reader.getAttribute("label"));         
		return category; 
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(AtomCategory.class);
	}

	
	
}
