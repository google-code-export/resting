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
package com.google.resting.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import com.google.resting.component.EncodingTypes;
import com.google.resting.component.content.IContentData;
import com.google.resting.component.content.contentdecorator.ByteContentData;
import com.google.resting.component.content.contentdecorator.StringContentData;

import static com.google.resting.component.EncodingTypes.BINARY;
/**
 * IO utilities for resting
 * 
 * @author sujata.de
 * @since resting 0.4
 *
 */
public class IOUtils {
	
	   /**
     * The default buffer size to use for 
     * {@link #copyLarge(InputStream, OutputStream)}
     * and
     * {@link #copyLarge(Reader, Writer)}
     */
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
    /**
     * Get the contents of an <code>InputStream</code> as a String
     * using the specified character encoding.
     * <p>
     * Character encoding names can be found at
     * <a href="http://www.iana.org/assignments/character-sets">IANA</a>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedInputStream</code>.
     * 
     * @param input  the <code>InputStream</code> to read from
     * @param encoding  the encoding to use, null means platform default
     * @return the requested String
     * @throws NullPointerException if the input is null
     * @throws IOException if an I/O error occurs
     */
    public static String toString(InputStream input, EncodingTypes charset) throws IOException {
        StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw,charset);
        return sw.toString();
    }//toString
    /**
     * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * <p>
     * Large streams (over 2GB) will return a chars copied value of
     * <code>-1</code> after the copy has completed since the correct
     * number of chars cannot be returned as an int. For large streams
     * use the <code>copyLarge(Reader, Writer)</code> method.
     *
     * @param input  the <code>Reader</code> to read from
     * @param output  the <code>Writer</code> to write to
     * @return the number of characters copied, or -1 if &gt; Integer.MAX_VALUE
     * @throws NullPointerException if the input or output is null
     * @throws IOException if an I/O error occurs
     */
    public static void copy(InputStream input, Writer output, EncodingTypes charset)
    throws IOException {
    //	BufferedReader in = new BufferedReader(new InputStreamReader(input, "UTF-8"), DEFAULT_BUFFER_SIZE);
    	InputStreamReader in=new InputStreamReader(input, charset.getName());
    	copy(in, output);
    }//copy
    /**
     * Copy chars from a <code>Reader</code> to a <code>Writer</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     * <p>
     * Large streams (over 2GB) will return a chars copied value of
     * <code>-1</code> after the copy has completed since the correct
     * number of chars cannot be returned as an int. For large streams
     * use the <code>copyLarge(Reader, Writer)</code> method.
     *
     * @param input  the <code>Reader</code> to read from
     * @param output  the <code>Writer</code> to write to
     * @return the number of characters copied, or -1 if &gt; Integer.MAX_VALUE
     * @throws NullPointerException if the input or output is null
     * @throws IOException if an I/O error occurs
     */
    private static int copy(Reader input, Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }//copy
    /**
     * Copy chars from a large (over 2GB) <code>Reader</code> to a <code>Writer</code>.
     * <p>
     * This method buffers the input internally, so there is no need to use a
     * <code>BufferedReader</code>.
     *
     * @param input  the <code>Reader</code> to read from
     * @param output  the <code>Writer</code> to write to
     * @return the number of characters copied
     * @throws NullPointerException if the input or output is null
     * @throws IOException if an I/O error occurs
     */
    private static long copyLarge(Reader input, Writer output) throws IOException {
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        long count = 0;
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }  //copyLarge  
    
    public static void closeQuietly(Closeable closeable){
    	if(closeable!=null)
			try {
				closeable.close();
			} catch (IOException e) {
				//ignore
			}
    	
    }//closeQuietly
    /**
     * Copies a <code>ReadableByteChannel</code> into a <code>WritableByteChannel</code> byte channel. Uses a <code>ByteBuffer</code>
     * to write to the channel. The buffer is drained before each write.
     * 
     * @param src the <code>ReadableByteChannel</code> to read from
     * @param dest the <code>ReadableByteChannel</code> to write into
     * @throws <code>IOException</code> if there is an I/O error
     */
    private static void fastChannelCopy(final ReadableByteChannel src, final WritableByteChannel dest){
    	final ByteBuffer buffer=ByteBuffer.allocateDirect(DEFAULT_BUFFER_SIZE);
    	try {
			while(src.read(buffer)!=-1){
				//prepare the buffer to be drained.
				buffer.flip();
				//write to the channel. may block.
				dest.write(buffer);
				//if partial transfer, shift remainder down.
				//if buffer is empty, same as compact
				buffer.compact();
			}
			//EOF will leave buffer in fill state
			buffer.flip();
			
			//make sure buffer is fully drained.
			while(buffer.hasRemaining()){
				dest.write(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }//fastChannelCopy
    /**
     * Writes an <code>InputStream</code> into a String using a charset given in <code>EncodingTypes</code> in a fast and smooth manner.
     * 
     * @param inputStream the <code>InputStream</code> to read from
     * @param charset the charset among the ones defined in <code>EncodingTypes</code> for encoding 
     * @return Encoded string representation of the <code>InputStream</code>
     * @throws <code>UnsupportedEncodingException</code> if the charset is not supported
     * @throws <code>Exception</code> for any issue
     */
    public static String writeToString(InputStream inputStream, EncodingTypes charset){
    	String outputString=null;
    	ByteArrayOutputStream baos=new ByteArrayOutputStream();
    	final ReadableByteChannel inputChannel=Channels.newChannel(inputStream);
    	final WritableByteChannel outputChannel=Channels.newChannel(baos);
    	//copy the channels
    	fastChannelCopy(inputChannel, outputChannel);
    	try {
			outputString=new String(baos.toByteArray(), charset.getName());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally{
			closeQuietly(inputChannel);
			closeQuietly(outputChannel);
			closeQuietly(inputStream);
			closeQuietly(baos);
		}
    	return outputString;
    }//writeToString
    
   /**
     * Writes an <code>InputStream</code> into a <code>ContentData</code> using a charset given in <code>EncodingTypes</code> in 
     * a fast and smooth manner.
     * 
     * @param inputStream the <code>InputStream</code> to read from
     * @param charset the charset among the ones defined in <code>EncodingTypes</code> for encoding 
     * @return <code>ContentData</code> object 
     * @throws <code>UnsupportedEncodingException</code> if the charset is not supported
     * @throws <code>Exception</code> for any issue
     */
    public static IContentData writeToContentData(InputStream inputStream, EncodingTypes charset){
    	IContentData output=null;
    	byte[] outputBytes=null;
    	ByteArrayOutputStream baos=new ByteArrayOutputStream();
    	final ReadableByteChannel inputChannel=Channels.newChannel(inputStream);
    	final WritableByteChannel outputChannel=Channels.newChannel(baos);
    	//copy the channels
    	fastChannelCopy(inputChannel, outputChannel);
    	try {
				outputBytes=baos.toByteArray();
				switch(charset){
					case BINARY:
					      		output=new ByteContentData(outputBytes);
					      		break;
					default: 
				      			output=new StringContentData(outputBytes,charset);
				      			break;
				}
			
		}  catch(Exception e){
			e.printStackTrace();
		}finally{
			closeQuietly(inputChannel);
			closeQuietly(outputChannel);
			closeQuietly(inputStream);
			closeQuietly(baos);
		}
    	return output;
    }//writeToContentData
    
    public static String writeToString(byte[] bytes, EncodingTypes charset){
		String output=null;
		try {
			if(charset!=BINARY)
				output=new String(bytes,charset.getName());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return output;
    }
}//IOUtils
