package com.memin.optics.repository.search;

import com.memin.optics.domain.Sale;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data ElasticSearch repository for the Sale entity.
 */
public interface SaleSearchRepository extends ElasticsearchRepository<Sale, Long> {
}
