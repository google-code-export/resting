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


import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/**
 * Custom socket factory to handle SSL authentication. This class is essential because the default self signed 
 * SSL certificates have problems in Android.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

@SuppressWarnings("deprecation")
public class CustomSSLSocketFactory implements SocketFactory, LayeredSocketFactory {

	private SSLContext sslcontext = null;

	private static SSLContext createEasySSLContext() throws IOException {
		try {
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new TrustManager[] { new CustomX509TrustManager() }, new java.security.SecureRandom());
			return context;
		} catch (Exception e) {
			throw new IOException(e.getMessage());
		}
	}//createEasySSLContext

	private SSLContext getSSLContext() throws IOException {
		if (this.sslcontext == null) {
			this.sslcontext = createEasySSLContext();
		}
		return this.sslcontext;
	}//getSSLContext

	/**
	 * @see org.apache.http.conn.scheme.SocketFactory#connectSocket(java.net.Socket, java.lang.String, int,
	 *      java.net.InetAddress, int, org.apache.http.params.HttpParams)
	 */
	public Socket connectSocket(Socket sock, String host, int port, InetAddress localAddress, int localPort,
			HttpParams params) throws IOException, UnknownHostException, ConnectTimeoutException {
		int connTimeout = HttpConnectionParams.getConnectionTimeout(params);
		int soTimeout = HttpConnectionParams.getSoTimeout(params);
		InetSocketAddress remoteAddress = new InetSocketAddress(host, port);
		SSLSocket sslsock = (SSLSocket) ((sock != null) ? sock : createSocket());

		if ((localAddress != null) || (localPort > 0)) {
			// we need to bind explicitly
			if (localPort < 0) {
				localPort = 0; // indicates "any"
			}
			InetSocketAddress isa = new InetSocketAddress(localAddress, localPort);
			sslsock.bind(isa);
		}

		sslsock.connect(remoteAddress, connTimeout);
		sslsock.setSoTimeout(soTimeout);
		return sslsock;

	}//connectSocket

	/**
	 * @see org.apache.http.conn.scheme.SocketFactory#createSocket()
	 */
	public Socket createSocket() throws IOException {
		return getSSLContext().getSocketFactory().createSocket();
	}//createSocket

	/**
	 * @see org.apache.http.conn.scheme.SocketFactory#isSecure(java.net.Socket)
	 */
	public boolean isSecure(Socket socket) throws IllegalArgumentException {
		return true;
	}//isSecure

	/**
	 * @see org.apache.http.conn.scheme.LayeredSocketFactory#createSocket(java.net.Socket, java.lang.String, int,
	 *      boolean)
	 */
	public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException,
			UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(socket, host, port, autoClose);
	}//createSocket

	// -------------------------------------------------------------------
	// javadoc in org.apache.http.conn.scheme.SocketFactory says :
	// Both Object.equals() and Object.hashCode() must be overridden
	// for the correct operation of some connection managers
	// -------------------------------------------------------------------

	@Override
	public boolean equals(Object obj) {
		
		//check for null
		if (obj == null)
			return false;
		
		//check for reference to this
		if (obj == this)
			return true;
		
		//check for type 
		if (obj instanceof CustomSSLSocketFactory ){
			
			//Casting will not throw ClassCastException since type is already checked for
			//do all the field level checks here:
			//TBD::
			if(this.sslcontext==((CustomSSLSocketFactory) obj).sslcontext)
				return true;
		}//instanceof
		
		return false;
		
	}//equals

	@Override
	public int hashCode() {
		//TBD:: hashcode logic to be updated
		return CustomSSLSocketFactory.class.hashCode();
	}//hashCode

}//CustomSSLSocketFactory
