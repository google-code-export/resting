package com.google.resting.transform;

import java.util.List;

import com.google.resting.component.impl.Alias;

/**
 * Base interface for transformer
 * 
 * @author sujata.de
 *
 * @param <T> source type
 * @param <U> destination type
 */

public interface Transformer<T,U> {
	public List<T> getEntityList(U source, Class<T> targetType, Alias alias);
	public T createEntity(String singleEntityStream, Class<T> targetType);
}
