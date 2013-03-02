/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright
 * ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.giwi.camel.kafka.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Giwi Softwares
 * 
 */
public class BinaryHelper {

	private final static BinaryHelper instance = new BinaryHelper();

	private BinaryHelper() {
		// Emptiness is killing me ...
	}

	/**
	 * @return an instance of my fabulous binary helper
	 */
	public static BinaryHelper getInstance() {
		return instance;
	}

	/**
	 * @param obj
	 *            my object
	 * @return a serialized object
	 * @throws java.io.IOException
	 *             a bullshit
	 */
	public byte[] getBytes(Object obj) throws java.io.IOException {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		byte[] data;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			data = bos.toByteArray();
		} finally {
			if (oos != null) {
				oos.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		return data;
	}

	/**
	 * @param data
	 *            a byteArray to deserialize
	 * @return an Object (if known)
	 * @throws IOException
	 *             a mad dog booze
	 * @throws ClassNotFoundException
	 *             oh wait ...
	 */
	public Object getObject(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			bis = new ByteArrayInputStream(data);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			return obj;
		} finally {
			if (ois != null) {
				ois.close();
			}
			if (bis != null) {
				bis.close();
			}
		}
	}
}
