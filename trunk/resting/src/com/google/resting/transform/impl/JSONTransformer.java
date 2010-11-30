package com.google.resting.transform.impl;

import com.google.gson.Gson;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.transform.Transformer;

public class JSONTransformer<T> implements Transformer<T, ServiceResponse> {
	private static Gson gson=new Gson();

	@Override
	public T createFrom(ServiceResponse source, Class<T> targetType) {
		T dest=gson.fromJson(source.getResponseString(),targetType );
		return dest;
	}

}
