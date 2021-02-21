package org.elasticsearch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.entity.Monitor;
import org.elasticsearch.service.impl.ChartsServiceImpl;
import org.elasticsearch.service.impl.MonitorServiceImpl;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElkClientApplicationTests {
    @Autowired
    RestHighLevelClient highLevelClient;
    @Autowired
    MonitorServiceImpl monitorService;
    @Autowired
    ChartsServiceImpl chartsService;
    @Test
    public void contextLoads() {
        SearchRequest searchRequest = new SearchRequest("fbshx-online-monitor*");
        searchRequest.types("log");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder  = QueryBuilders.termQuery("retCode","000000");
        BoolQueryBuilder termQueryBuilder = QueryBuilders.boolQuery().mustNot(queryBuilder);
        int from = 0;
        int size = 20;
        long total = 0;
        try {
            sourceBuilder.query(termQueryBuilder).from(0).size(size);
            sourceBuilder.sort("transDate");
            //sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            searchRequest.source(sourceBuilder);
            SearchResponse response = highLevelClient.search(searchRequest);
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits) {
                Monitor monitor =  JSONObject.parseObject(hit.getSourceAsString(), Monitor.class);
                monitor.setId(hit.getId()); monitor.setIndex(hit.getIndex());
                System.out.println(monitor);
            }
            total = response.getHits().totalHits;
            System.out.println("测试:[" + total + "][" + from + "-" + (from + hits.length) + ")");
            from += hits.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void count() {
       List list =  chartsService.groupByDate(QueryBuilders.matchAllQuery());
        System.out.println(list.size()+":"+JSONArray.toJSONString(list));
    }

    @Test
    public void update() {
        List list = new ArrayList();
        list.add("AWz_gyR2s0xZFqWb8aMB");
        Map<String,Object> map = new HashMap<>();
        map.put("handle","已处理");
            boolean f = monitorService.updateMonitor(list,map);
        System.out.println(f);
    }

    @Test
    public void groupBy() {
        List list = new ArrayList();
        SearchRequest searchRequest = new SearchRequest("fbshx-online-monitor*");
        searchRequest.types("log");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("by_date").field("transDate");
        try {
            sourceBuilder.query(QueryBuilders.matchAllQuery()).aggregation(aggregationBuilder).size(0);
            searchRequest.source(sourceBuilder);
            SearchResponse response = highLevelClient.search(searchRequest);
            Aggregations aggregations = response.getAggregations();
            Terms terms= aggregations.get("by_date");
            for (Bucket bucket:terms.getBuckets()){
                System.out.println(bucket.getKey()+":"+bucket.getDocCount());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
