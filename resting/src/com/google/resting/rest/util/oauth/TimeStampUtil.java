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

package com.google.resting.rest.util.oauth;
/**
 * This class will contain all the logic of creating timestamps for query params
 * Oauth 1.0.
 * @author sujata.de
 *
 */
public class TimeStampUtil {
	
	protected static String getTimeStamp(){
		return Long.toString(System.currentTimeMillis()/1000);
	}


}
