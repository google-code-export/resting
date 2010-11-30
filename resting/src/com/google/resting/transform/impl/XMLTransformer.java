package com.google.resting.transform.impl;

import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.transform.Transformer;
import com.thoughtworks.xstream.XStream;

public class XMLTransformer<T> implements Transformer<T, ServiceResponse> {

	@Override
	public T createFrom(ServiceResponse source, Class<T> targetType) {
		XStream xstream=new XStream();
		T dest=(T)xstream.fromXML(source.getResponseString());
		return dest;
	}

}
