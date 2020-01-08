package com.open.cloud.test.mockito;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBJDBC {
	public static void main(String[] args) {
		try {
			//连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
			//ServerAddress()两个参数分别为 服务器地址 和 端口
			ServerAddress serverAddress = new ServerAddress("122.51.108.224", 27017);
			List<ServerAddress> addrs = new ArrayList<ServerAddress>();
			addrs.add(serverAddress);

			//MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
			MongoCredential credential = MongoCredential.createScramSha1Credential("admin", "databaseName", "123456".toCharArray());
			List<MongoCredential> credentials = new ArrayList<MongoCredential>();
			credentials.add(credential);

			//通过连接认证获取MongoDB连接
			MongoClient mongoClient = new MongoClient(addrs, credentials);

			//连接到数据库
			MongoDatabase mongoDatabase = mongoClient.getDatabase("databaseName");
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