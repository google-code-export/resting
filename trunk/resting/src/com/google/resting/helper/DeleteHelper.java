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
package com.google.resting.helper;

import com.google.resting.component.RequestParams;
import com.google.resting.component.ServiceContext;
import com.google.resting.component.impl.DeleteServiceContext;
import com.google.resting.component.impl.ServiceResponse;
import com.google.resting.component.impl.URLContext;
import com.google.resting.serviceaccessor.impl.ServiceAccessor;
/**
 * Helper class for HTTP DELETE operation
 * 
 * @author sujata.de
 * @since resting 0.2
 *
 */
public class DeleteHelper {
	public final static ServiceResponse delete(String url, int port, RequestParams requestParams){
		URLContext urlContext=new URLContext(url,port);
		ServiceContext serviceContext= new DeleteServiceContext(urlContext,requestParams);
		return ServiceAccessor.access(serviceContext);	
	}//delete
	
}//DeleteHelper
