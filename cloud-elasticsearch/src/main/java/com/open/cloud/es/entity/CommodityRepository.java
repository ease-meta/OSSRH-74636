package com.open.cloud.es.entity;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, String> {

}