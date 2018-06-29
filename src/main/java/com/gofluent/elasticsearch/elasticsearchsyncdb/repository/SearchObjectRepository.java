package com.gofluent.elasticsearch.elasticsearchsyncdb.repository;

import com.gofluent.elasticsearch.elasticsearchsyncdb.model.SearchObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SearchObjectRepository extends MongoRepository<SearchObject, String> {
}
