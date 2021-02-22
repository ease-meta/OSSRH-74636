package org.elasticsearch.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.entity.Monitor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: MonitorServiceImpl
 * @Description: java类作用描述
 * @Author: liyuq
 * @CreateDate: 2019/9/4 18:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Service
@Slf4j
public class MonitorServiceImpl implements MonitorService{
    public final static String _INDEX = "fbshx-online-monitor-*";
    public final static String _TYPE = "log";
    @Autowired
    RestHighLevelClient highLevelClient;

    /**
     * 获取retCode不等于“000000”的所有结果集
     * @return
     */
    @Override
    public List<Monitor> getMonitorAll(int from , int size) {
        List<Monitor> monitors = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest(_INDEX);
        searchRequest.types(_TYPE);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder  = QueryBuilders.prefixQuery("retCode","9");
        BoolQueryBuilder termQueryBuilder = QueryBuilders.boolQuery().must(queryBuilder);
        try {
            sourceBuilder.query(termQueryBuilder).from(from).size(size);
            sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
            searchRequest.source(sourceBuilder);
            SearchResponse response = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits) {
                Monitor monitor =  JSONObject.parseObject(hit.getSourceAsString(), Monitor.class);
                monitor.setId(hit.getId());
                monitor.setIndex(hit.getIndex());
                monitors.add(monitor);
            }
            return monitors;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获取retCode不等于“000000”的count
     * @return
     */
    @Override
    public long getMonitorCount() {
        SearchRequest searchRequest = new SearchRequest(_INDEX);
        try {
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(0);
            searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.prefixQuery("retCode","9")));
            searchRequest.source(searchSourceBuilder);
            SearchResponse response = highLevelClient.search(searchRequest,RequestOptions.DEFAULT);
            return response.getHits().getTotalHits().value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 根据ids 删除对应信息
     * @return
     */
    @Override
    public Boolean deleteMonitors(List<String> ids) {
        for(String _id: ids){
            Monitor monitor = getMonitor(_id);
            DeleteRequest deleteRequest = new DeleteRequest(monitor.getIndex(),_TYPE,_id);
            try {
                highLevelClient.delete(deleteRequest,RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  true;
    }
    /**
     * 根据id 查找对应信息
     * @return
     */
    @Override
    public Monitor getMonitor(String id) {
        Monitor monitor = null;
        SearchRequest searchRequest = new SearchRequest(_INDEX);
        searchRequest.types(_TYPE);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder  = QueryBuilders.termQuery("_id",id);
        try {
            sourceBuilder.query(queryBuilder);
            searchRequest.source(sourceBuilder);
            SearchResponse response = highLevelClient.search(searchRequest,RequestOptions.DEFAULT);
            response.getHits().getHits();
            SearchHit[] hits = response.getHits().getHits();
            for (SearchHit hit : hits) {
                monitor =  JSONObject.parseObject(hit.getSourceAsString(), Monitor.class);
                monitor.setId(hit.getId());
                monitor.setIndex(hit.getIndex());
            }
            return monitor;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据ids 更新对应信息
     * @return
     */
    @Override
    public Boolean updateMonitor(List<String> ids, Map map) {
        for(String _id: ids){
            Monitor monitor = getMonitor(_id);
            UpdateRequest updateRequest = new UpdateRequest(monitor.getIndex(),_TYPE,_id)
                    .doc(map);
            try {
                highLevelClient.update(updateRequest,RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
