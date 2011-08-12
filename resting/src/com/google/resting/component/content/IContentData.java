 /*
 * Copyright (C) 2011 Google Code.
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

package com.google.resting.component.content;

/**
 * Interface for the content of REST response
 * 
 * @author sujata.de
 *
 */

public interface IContentData {
	
	public Object getContent();
	public int getContentLength();
	public String getContentInString();

}
