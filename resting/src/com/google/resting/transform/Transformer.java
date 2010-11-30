package com.google.resting.transform;

/**
 * Base interface for transformer
 * 
 * @author sujata.de
 *
 * @param <T> source type
 * @param <U> destination type
 */

public interface Transformer<T,U> {
	public T createFrom(U source, Class<T> targetType);
}
