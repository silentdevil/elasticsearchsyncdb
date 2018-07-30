package com.gofluent.elasticsearch.elasticsearchsyncdb.service;

import com.gofluent.elasticsearch.model.SearchObject;

public interface SearchObjectService {
    void save(SearchObject searchObject);
    SearchObject findById(String id);
}
