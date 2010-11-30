package com.google.resting.rest.util.oauth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.resting.component.OperationType;
import com.google.resting.component.impl.NameValueEntity;
/**
 * Utility for signing request as per Oauth 1.0a.
 * 
 * @author sujata.de
 * @since resting 0.1
 *
 */

public class SignatureUtil {
	private static final String SEPARATOR="://";

	
	private static String getBaseString(String targetDomain, String requestType, boolean isSecureInvocation, String contextPathElement, List<NameValueEntity> inputParams){
		String sourceVerb=requestType;
		String sourceUrl=SEPARATOR+targetDomain+contextPathElement;
		if(isSecureInvocation){
			sourceUrl=RequestConstants.HTTPS+sourceUrl;
		}else{
			sourceUrl=RequestConstants.HTTP+sourceUrl;
		}
		System.out.println("Source url is "+sourceUrl);
		return BaseStringExtractorImpl.extract(sourceVerb, sourceUrl,inputParams );
	}
	
	/**
	 * Sign request.
	 * 
	 * @param keyString Consumer key for request signing.
	 * @param targetDomain Domain of the REST endpoint (Ex. login.yahoo.com)
	 * @param operationType Type of REST operation (GET/POST/PUT/DELETE)
	 * @param isSecureInvocation HTTP/HTTPS
	 * @param contextPathElement Path element in the base REST uri (Ex. /weather/india)
	 * @param inputParams Collection of request params for REST request (Ex. city=calcutta )
	 * @return
	 * @throws NoSuchAlgorithmException The exception is thrown if the encryption algorithm is not supported.
	 * @throws InvalidKeyException The exception is thrown if the consumer key is invalid
	 * @throws IllegalStateException 
	 * @throws UnsupportedEncodingException The exception is thrown if the URL encoding is incorrect.
	 */
	public static String getSignature(String keyString, String targetDomain, OperationType operationType, boolean isSecureInvocation, String contextPathElement, List<NameValueEntity> inputParams) throws NoSuchAlgorithmException,InvalidKeyException, IllegalStateException, UnsupportedEncodingException{
		
		String baseString=getBaseString(targetDomain,operationType.toString(), isSecureInvocation, contextPathElement, inputParams)
							.replace("+", "%20")
							.replace("*", "%2A")
							.replace("%7E", "~");
		
		System.out.println("Base string is "+baseString);
		Mac mac = Mac.getInstance("HmacSHA1");    
		SecretKeySpec secret = new SecretKeySpec(keyString.getBytes(RequestConstants.UTF8), mac.getAlgorithm());    
		mac.init(secret);     
		byte[] digest = mac.doFinal(baseString.getBytes(RequestConstants.UTF8));     
		return new String(Base64.encodeBase64(digest)).replace(RequestConstants.CARRIAGE_RETURN, RequestConstants.EMPTY_STRING); 
	}
}
