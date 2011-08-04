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
 * Values of content type in request header.
 * 
 * @author sujata.de
 * @since resting 0.5
 *
 */
public enum ContentType {
	
	//text
	TEXT_XML("text/xml"),
	TEXT_HTML("text/html"),
	TEXT_PLAIN("text/plain"),
	TEXT_CSS("text/css"),
	TEXT_CMD("text/cmd"),
	TEXT_CSV("text/csv"),
	TEXT_JS("text/javascript"),

	
	//application
	APPLICATION_JSON("application/json"),
	APPLICATION_OCTET_STREAM("application/octet-stream"), 
	APPLICATION_ATOM_XML("application/atom+xml"),
	APPLICATION_PDF("application/pdf"),
	APPLICATION_PS("application/postscript"),
	APPLICATION_ZIP("application/zip"),
	APPLICATION_GZIP("application/x-gzip"),
	APPLICATION_SOAP_XML("application/soap+xml"),
	APPLICATION_XHTML("application/xhtml+xml"),
	APPLICATION_XML_DTD("application/xml-dtd"),
	APPLICATION_XOP("application/xop"),
	
	//image
	IMAGE_GIF("image/gif"),
	IMAGE_JPEG("image/jpeg"),
	IMAGE_PJPEG("image/pjpeg"),
	IMAGE_PNG("image/png"),
	IMAGE_SVG_XML("image/svg+xml"),
	IMAGE_TIFF("image/tiff"),
	
	//message
	MESSAGE_HTTP("message/http"),
	MESSAGE_IMDN_XML("message/imdn+xml"),
	MESSAGE_PARTIAL("message/partial"),
	MESSAGE_RFC822("message/rfc822"),
	
	//audio
	AUDIO_MP4("audio/mp4"),
	AUDIO_MPEG("audio/mpeg"),
	
	//video
	VIDEO_MPEG("video/mpeg")

	;
	
	private String name;
	
	private ContentType(String name){
		this.name=name;
	}
	
	public String getName(){
		return name;
	}
	
}//ContentType
