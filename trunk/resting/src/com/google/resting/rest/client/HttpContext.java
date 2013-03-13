 /*
 * Copyright (C) 2013 Google Code.
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
package com.google.resting.rest.client;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/**
 * Encapsulates various contexts for REST invocation
 * 
 * @author sujata.de
 * @since resting 0.7
 *
 */
public class HttpContext {
	
	private HttpParams httpParams;
	private Credentials credentials;
	private AuthScope authScope;
	
	public HttpContext(){
		this.httpParams=new BasicHttpParams();
		this.authScope=null;
		this.credentials=null;
	}//HttpContext
	
	public HttpContext setTimeout(int timeout){
		HttpConnectionParams.setConnectionTimeout(httpParams, timeout);
		HttpConnectionParams.setSoTimeout(httpParams, timeout);
		return this;
	}//setTimeout
	
	public HttpContext setTimeout(int connectionTimeout, int socketTimeout){
		HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout);
		HttpConnectionParams.setSoTimeout(httpParams, socketTimeout);
		return this;
	}//setTimeout
	
	public HttpContext setConnectionTimeout(int connectionTimeout){
		HttpConnectionParams.setConnectionTimeout(httpParams, connectionTimeout);
		return this;
	}//setConnectionTimeout
	
	public HttpContext setSocketTimeout(int socketTimeout){
		HttpConnectionParams.setSoTimeout(httpParams, socketTimeout);
		return this;
	}//setSocketTimeout
	
	public HttpContext setPreemptiveAuthentication(boolean preemptiveAuthentication){
		httpParams.setParameter("http.authentication.preemptive", preemptiveAuthentication);
		return this;
	}//setPreemptiveAuthentication

	public HttpContext setCredentials(String user, String password) {
		this.credentials = new UsernamePasswordCredentials(user,password);
		return this;
	}//setCredentials

	public HttpContext setAuthScope(String host, int port, String realm) {
		authScope = new AuthScope(host,port,realm);
		return this;
	}//setAuthScope
	
	public HttpContext setAuthScope(AuthScope authScope){
		this.authScope=authScope;
		return this;
	}//setAuthScope
	
	public HttpContext setProxy(String proxyHost, int proxyPort){
		httpParams.setParameter(ConnRoutePNames.DEFAULT_PROXY, new HttpHost(proxyHost,proxyPort));
		return this;
	}//setProxy

	protected HttpParams getHttpParams() {
		return httpParams;
	}//getHttpParams

	protected Credentials getCredentials() {
		return credentials;
	}//getCredentials

	protected AuthScope getAuthScope() {
		return authScope;
	}//getAuthScope

}//HttpContext
