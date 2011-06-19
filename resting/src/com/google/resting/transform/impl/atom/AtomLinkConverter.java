package com.google.resting.transform.impl.atom;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AtomLinkConverter implements Converter {

		@Override
		public void marshal(Object source, HierarchicalStreamWriter writer,
				MarshallingContext context) {
		}

		@Override
		public Object unmarshal(HierarchicalStreamReader reader,
				UnmarshallingContext context) {
			AtomLink link = new AtomLink();         
			link.setHref(reader.getAttribute("href"));         
			link.setRel(reader.getAttribute("rel"));
			link.setType(reader.getAttribute("type"));
			return link; 
		}

		@Override
		public boolean canConvert(Class type) {
			return type.equals(AtomLink.class);
		}
}

