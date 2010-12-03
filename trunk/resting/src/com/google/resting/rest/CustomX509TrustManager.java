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

package com.google.resting.rest;



import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;
/**
 * Custom Trust Manager. Esp. suitable for Android.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public class CustomX509TrustManager implements X509TrustManager {

	/**
	 * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[],String authType)
	 */
	public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
		
	}//checkClientTrusted

	/**
	 * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[],String authType)
	 */
	public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {

	}//checkServerTrusted

	/**
	 * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
	 */
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}//getAcceptedIssuers

}//CustomX509TrustManager
