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
