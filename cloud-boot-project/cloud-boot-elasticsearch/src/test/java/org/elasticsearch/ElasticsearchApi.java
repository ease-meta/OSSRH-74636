package org.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author Leijian
 * @date 2021/2/22 21:30
 * @since
 */
public class ElasticsearchApi {
	private static final String INDEX = "item"; //索引名称

	public static void main(String[] args) {
		HttpHost hosts = new HttpHost("10.7.29.152", 9200, "HTTP");
		RestClientBuilder restClientBuilder = RestClient.builder(hosts);
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);
		//创建索引index,创建索引index，就相当于我们使用java创建数据库
		try {
			//构建创造索引请求 构造器参数为你想要创建的索引名称
			CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX);
			//发送请求
			CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
			//isAcknowledged()是否创建完成
			if (createIndexResponse.isAcknowledged()) {
				System.out.println("ok");
			} else {
				System.out.println("false");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("false");
		}
	}
}
