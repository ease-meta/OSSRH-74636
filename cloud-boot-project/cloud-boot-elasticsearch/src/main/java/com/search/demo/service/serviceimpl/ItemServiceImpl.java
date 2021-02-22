package com.search.demo.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.search.demo.bean.Item;
import com.search.demo.dao.ItemDao;
import com.search.demo.service.ItemService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.completion.CompletionSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * ItemServiceImpl
 *
 * @author sk
 * @date 2020/3/30 11:26
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemDao, Item> implements ItemService {
    @Resource
    private RestHighLevelClient client;
    @Autowired
    private ItemDao mapper;
    public static final String INDEX = "item";

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteOne(Long id) {
        //构建delete请求
        DeleteRequest request = new DeleteRequest(INDEX).id(id + "");
        DeleteResponse response = null;
        try {
            response = client.delete(request, RequestOptions.DEFAULT);
            System.out.println(response);
            if (response == null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Boolean addItem(Item item, String id) {
        //单条插入
        IndexRequest request = new IndexRequest(INDEX).id(id).source(beanToMap(item));
        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            if (response == null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Object deleteAll() {
        //构建批量删除请求
        DeleteByQueryRequest request = new DeleteByQueryRequest(INDEX);
        //匹配所有
        request.setQuery(new MatchAllQueryBuilder());
        BulkByScrollResponse response = null;
        try {
            response = client.deleteByQuery(request, RequestOptions.DEFAULT);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.toJSON(response);
    }

    /**
     * 查询
     *
     * @param
     * @return
     */
    @Override
    public Object selectByKey(String key, String value, Integer current, Integer size) {
        //分页返回结果
        Page<Item> page = new Page<>();
        //构建搜索请求
        SearchRequest request = new SearchRequest(INDEX);
        //搜索条件构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //设置高亮显示
        HighlightBuilder hiBuilder = new HighlightBuilder();
        //设置高亮标签
        hiBuilder.preTags("<a style='color: #e4393c'>");
        hiBuilder.postTags("</a>");
        //高亮字段
        hiBuilder.field(key);
        /**
         * 使用QueryBuilder
         * termQuery("key", obj) 完全匹配
         * termsQuery("key", obj1, obj2..)   一次匹配多个值
         * matchQuery("key", Obj) 单个匹配, field不支持通配符, 前缀具高级特性
         * multiMatchQuery("text", "field1", "field2"..);  匹配多个字段, field有通配符忒行
         */
        //搜索条件 matchQuery
        searchSourceBuilder.query(QueryBuilders.matchQuery(key, value));
        //搜索结果分页 当前页
        searchSourceBuilder.from(current);
        //每页显示条数
        searchSourceBuilder.size(size);
        //高亮显示添加到构造器
        searchSourceBuilder.highlighter(hiBuilder);
        //构造器添加到搜索请求
        request.source(searchSourceBuilder);
        //客户端返回信息
        SearchResponse response = null;
        try {
            //请求返回
            response = client.search(request, RequestOptions.DEFAULT);
            if (response == null) {
                return page;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //搜索结果
        SearchHit[] hits = response.getHits().getHits();
        //分页封装
        page.setSize(size);
        page.setCurrent(current);
        page.setTotal(hits.length);
        //所有结果封装
        List<Item> list = new ArrayList<>();
        //高亮字段封装
        for (SearchHit hit : hits) {
            //设置高亮字段
            Item item = JSONObject.parseObject(hit.getSourceAsString(), Item.class);
            String title = hit.getHighlightFields().get(key).getFragments()[0].toString();
            item.setTitle(title);
            //返回结果
            list.add(item);
        }
        page.setRecords(list);
        return page;

    }

    @Override
    public Page<Item> selectAll() {
        List<Item> result = new ArrayList<>();
        Page<Item> page = new Page<>();
        //构造请求
        SearchRequest request = new SearchRequest(INDEX);
        //搜索条件构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //matchAllQuery查询所有
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //分页(查询所有可不要)
        searchSourceBuilder.from(1);
        searchSourceBuilder.size(1000);
        request.source(searchSourceBuilder);
        try {
            //返回结果封装
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            page.setSize(1000);
            page.setCurrent(1);
            page.setTotal(hits.length);
            for (SearchHit hit : hits) {
                Item item = JSONObject.parseObject(hit.getSourceAsString(), Item.class);
                result.add(item);
            }
            page.setRecords(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return page;
    }

    @Override
    public Object insertByBulk() {
        //批量插入请求
        BulkRequest bulkRequest = new BulkRequest();
        //从数据库中查询所有数据
        List<Item> list = mapper.selectList(null);
        //遍历构造bulk条件
        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            //这里必须每次都使用new IndexRequest(index,type),不然只会插入最后一条记录(这样插入不会覆盖已经存在的Id，也就是不能更新)
            bulkRequest.add(new IndexRequest(INDEX).id(item.getId().toString())
                    .source(BeanMap.create(item)));
        }
        BulkResponse responses = null;
        try {
            //客户端返回
            responses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.toJSON(responses);
    }

    @Override
    public Object selectSuggest(String value) {
        List<String> result = new ArrayList<>();
        //搜索条件
        SearchRequest request = new SearchRequest(INDEX);
        //搜索条件构造器
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        /**
         * PhraseSuggestionBuilder (org.elasticsearch.search.suggest.phrase)
         * CompletionSuggestionBuilder (org.elasticsearch.search.suggest.completion)
         * TermSuggestionBuilder (org.elasticsearch.search.suggest.term)
         */
        //提示搜索构造器, 使用PhraseSuggestionBuilder 构造器参数为搜索字段
        //PhraseSuggestionBuilder suggestionBuilder = new PhraseSuggestionBuilder("title");
        //搜索内容
        //suggestionBuilder.text(value);
        //提示搜索构造器, 使用TermSuggestionBuilder 构造器参数为搜索字段
        //TermSuggestionBuilder suggestionBuilder = new TermSuggestionBuilder("title");
        //suggestionBuilder.text(value);
        //提示搜索构造器, 使用CompletionSuggestionBuilder 构造器参数为搜索字段
        //如果使用使用CompletionSuggestionBuilder,需要在构建mapping时添加相对应的suggest
        CompletionSuggestionBuilder suggestionBuilder = new CompletionSuggestionBuilder("title.suggest");
        suggestionBuilder.text(value);
        SuggestBuilder suggestBuilder = new SuggestBuilder();
        //添加Suggestion 第一个参数为搜索名称，可以随便打，与下面的搜索结果名称匹配即可 第二个参数为提示搜索构造器
        suggestBuilder.addSuggestion("my-suggest", suggestionBuilder);
        searchSourceBuilder.suggest(suggestBuilder);
        request.source(searchSourceBuilder);
        try {
            //返回内容
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            //保存es返回结果 my-suggest需要与上面的搜索结果名称相同
            List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> list = response
                    .getSuggest().getSuggestion("my-suggest").getEntries();
            //返回内容格式可自行打断点查看，这里直接封装使用
            if (list == null) {
                return result;
            } else {
                //转为list保存结果字符串
                for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> e : list) {
                    for (Suggest.Suggestion.Entry.Option option : e) {
                        result.add(option.getText().toString());
                        System.out.println(option.getText().toString());
                    }
                }
            }
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 实体类转map
     *
     * @param bean
     * @param <T>
     * @return
     */
    private static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = new HashMap<>();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                if (beanMap.get(key) != null) {
                    map.put(key + "", beanMap.get(key));
                }
            }
        }
        return map;
    }

    @Override
    public Object createMapping() {
        PutMappingRequest putMappingRequest = new PutMappingRequest(INDEX);
        try {
            //XContentBuilder构造器
            XContentBuilder xContentBuilder = setMapping(new Item());
            putMappingRequest.source(xContentBuilder);
            xContentBuilder.getOutputStream().toString();
            IndicesClient indicesClient = client.indices();
            AcknowledgedResponse acknowledgedResponse = indicesClient.putMapping(putMappingRequest, RequestOptions.DEFAULT);
            if (acknowledgedResponse.isAcknowledged()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object createIndex() {
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest(INDEX);
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            if (createIndexResponse.isAcknowledged()) {
                return true;
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static XContentBuilder setMapping(Object obj) {
        List<Field> fieldList = getFields(obj);
        XContentBuilder mapping = null;
        try {
            mapping = jsonBuilder().startObject().startObject("properties");
            for (Field field : fieldList) {
                //修饰符是static的字段不处理
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                String name = field.getName();
                if (Item.list().contains(name)) {
                    //需要分词的字段
                    mapping.startObject(name)
                            .field("type", getElasticSearchMappingType(field.getType().getSimpleName().toLowerCase()))
                            .field("index", "true")
                            //使用IK分词器
                            .field("analyzer", "ik_max_word")
                            .field("search_analyzer", "ik_max_word")
                            .startObject("fields")
                            .startObject("suggest")
                            .field("type", "completion")
                            .field("analyzer", "ik_max_word")
                            .endObject()
                            .endObject()
                            .endObject();
                } else {
                    //不需要分词的字段
                    mapping.startObject(name)
                            .field("type", getElasticSearchMappingType(field.getType().getSimpleName().toLowerCase()))
                            .field("index", "true")
                            .endObject();
                }
            }
            mapping.endObject().endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapping;
    }

    /**
     * java基础类型转el类型
     *
     * @param varType
     * @return
     */
    private static String getElasticSearchMappingType(String varType) {
        String es;
        switch (varType) {
            case "date":
                es = "date";
                break;
            case "double":
                es = "double";
                break;
            case "long":
                es = "long";
                break;
            case "int":
                es = "long";
                break;
            default:
                es = "text";
                break;
        }
        return es;
    }

    /**
     * 获取字段名
     *
     * @param obj
     * @return
     */
    private static List<Field> getFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        List<Field> fieldList = new ArrayList<>();
        fieldList.addAll(Arrays.asList(fields));
        return fieldList;
    }
}
