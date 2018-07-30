package com.gofluent.elasticsearch.elasticsearchsyncdb.repository;

import com.gofluent.elasticsearch.model.SearchObject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SearchObjectRepository extends ElasticsearchRepository<SearchObject, String> {
    SearchObject findById(String id);
}
