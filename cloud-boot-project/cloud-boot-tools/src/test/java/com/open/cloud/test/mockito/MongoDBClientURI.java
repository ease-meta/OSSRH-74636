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
package com.open.cloud.test.mockito;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBClientURI {

	public static void main(String[] args) {
		String mongo_uri = "mongodb://admin:123456@122.51.108.224:27017/admin";

		MongoClientURI mongoClientUri = new MongoClientURI(mongo_uri);

		MongoClient mongoclient = new MongoClient(mongoClientUri);

		//连接到数据库
		MongoDatabase mongoDatabase = mongoclient.getDatabase("admin");
		System.out.println("Connect to database successfully");

		mongoDatabase.createCollection("test");
		System.out.println("集合创建成功");

		MongoCollection<Document> collection = mongoDatabase.getCollection("test");
		System.out.println("集合 test 选择成功");

	}
}
