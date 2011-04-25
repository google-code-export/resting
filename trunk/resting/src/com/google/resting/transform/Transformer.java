 /*
 * Copyright (C) 2010 Google Code.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.resting.transform;

import java.util.List;

import com.google.resting.component.Alias;

/**
 * Base interface for transformer
 * 
 * @author sujata.de
 *
 * @param <T> source type
 * @param <U> destination type
 */

public interface Transformer<T,U> {
	public T createEntity(String singleEntityStream, Class<T> targetType);
	public List<T> getEntityList(U source, Class<T> targetType, Alias alias);
	List<T> getEntityList(String responseString, Class<T> targetType,
			Alias alias);
	
}
