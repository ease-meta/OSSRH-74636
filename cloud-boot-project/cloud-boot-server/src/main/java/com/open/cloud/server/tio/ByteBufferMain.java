/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.open.cloud.server.tio;

import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

/**
 * @author Leijian
 * @date 2020/4/12
 */
public class ByteBufferMain {

	public static void main(String args[]) throws FileNotFoundException {

		System.out.println("----------Test allocate--------");
		System.out.println("before alocate:"
				+ Runtime.getRuntime().freeMemory());

		// 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
		// 要超过多少内存大小JVM才能感觉到？
		ByteBuffer buffer = ByteBuffer.allocate(102400);
		System.out.println("buffer = " + buffer);

		System.out.println("after alocate:"
				+ Runtime.getRuntime().freeMemory());

		// 这部分直接用的系统内存，所以对JVM的内存没有影响
		ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
		System.out.println("directBuffer = " + directBuffer);
		System.out.println("after direct alocate:"
				+ Runtime.getRuntime().freeMemory());

		System.out.println("----------Test wrap--------");
		byte[] bytes = new byte[32];
		buffer = ByteBuffer.wrap(bytes);
		System.out.println(buffer);

		buffer = ByteBuffer.wrap(bytes, 10, 10);
		System.out.println(buffer);
	}
}
