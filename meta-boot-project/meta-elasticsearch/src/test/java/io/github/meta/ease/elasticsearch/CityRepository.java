package io.github.meta.ease.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ES 操作类
 * <p>
 * Created by bysocket on 20/06/2017.
 */
public interface CityRepository extends ElasticsearchRepository<City, Long> {

}
