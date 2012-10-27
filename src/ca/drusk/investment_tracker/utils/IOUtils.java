/******************************************************************************
 * Copyright (C) 2012 David Rusk
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *****************************************************************************/
package ca.drusk.investment_tracker.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Utilites for working with files.
 * 
 * @author drusk
 * 
 */
public final class IOUtils {

	/**
	 * Reads a file's contents into a string.
	 * 
	 * @param path
	 *            the path of the file to read
	 * @return a string with the file's contents
	 * @throws IOException
	 */
	public static String readFileToString(String path) throws IOException {
		FileInputStream stream = new FileInputStream(new File(path));
		try {
			FileChannel fileChannel = stream.getChannel();
			MappedByteBuffer buffer = fileChannel.map(
					FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());
			/* Instead of using default, pass in a decoder. */
			return Charset.defaultCharset().decode(buffer).toString();
		} finally {
			stream.close();
		}
	}

	/**
	 * Reads an InputStream into a string.
	 * 
	 * @param stream
	 *            the InputStream to read
	 * @return the contents of the InputStream as a string.
	 * @throws IOException
	 */
	public static String readStreamToString(InputStream stream)
			throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		try {
			StringBuffer sb = new StringBuffer(1024);
			char[] buffer = new char[1024];
			int numberOfBytesRead = 0;
			while ((numberOfBytesRead = reader.read(buffer)) != -1) {
				sb.append(String.valueOf(buffer, 0, numberOfBytesRead));
			}
			return sb.toString();
		} finally {
			reader.close();
		}
	}
	
	private IOUtils(){
	}
	
}
