package com.open.cloud.test.mockito;

import com.mongodb.MongoClient;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;

public class MongodbJava {

	public static void main(String[] args) {
		try {
			// 连接到 mongodb 服务
			MongoClient mongoClient = new MongoClient("122.51.108.224", 27017);
			// 连接到数据库
			// 连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
			System.out.println("Connect to database successfully");
			mongoDatabase.createCollection("test");
			System.out.println("集合创建成功");

			MongoCollection<Document> collection = mongoDatabase.getCollection("test");
			System.out.println("集合 test 选择成功");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
}
