package com.open.cloud.es.service.impl;

import com.open.cloud.es.entity.ChartsModel;
import com.open.cloud.es.service.ChartsService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class ChartsServiceImpl implements ChartsService {
    public final static String _INDEX = "fbshx-online-monitor-*";
    public final static String _TYPE = "log";
    @Autowired
    RestHighLevelClient highLevelClient;
    @Override
    public Map dateCountGroupBy() {
        Map map = new HashMap();
        List<ChartsModel> list = groupByDate(QueryBuilders.matchAllQuery());

        BoolQueryBuilder queryBuilder =  QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("handle","已处理"));
        List<ChartsModel> list1 = groupByDate(queryBuilder);

        BoolQueryBuilder queryBuilder1 =  QueryBuilders.boolQuery()
                .must(QueryBuilders.prefixQuery("retCode","9"));
        List<ChartsModel> list2 = groupByDate(queryBuilder1);

        map.put("ALL",list);
        map.put("HANDLE",list1);
        map.put("NOHANDLE",list2);
        return  map;
    }

    public List<ChartsModel> groupByDate(QueryBuilder queryBuilder){
        List list = new ArrayList();
        SearchRequest searchRequest = new SearchRequest("fbshx-online-monitor*");
        searchRequest.types("log");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        TermsAggregationBuilder aggregationBuilder = AggregationBuilders.terms("by_date").field("transDate");
        try {
            //每日总交易量分组
            sourceBuilder.query(queryBuilder).aggregation(aggregationBuilder.size(360)).size(0);
            searchRequest.source(sourceBuilder);
            SearchResponse response = highLevelClient.search(searchRequest);
            Aggregations aggregations = response.getAggregations();
            Terms terms= aggregations.get("by_date");
            for (Terms.Bucket bucket:terms.getBuckets()){
                ChartsModel chartsModel= new ChartsModel();
                chartsModel.setKey(bucket.getKeyAsString());
                chartsModel.setValue(bucket.getDocCount());
                list.add(chartsModel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        list.sort(new Comparator<ChartsModel>() {
            @Override
            public int compare(ChartsModel o1, ChartsModel o2){
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        return  list;
    }
}
