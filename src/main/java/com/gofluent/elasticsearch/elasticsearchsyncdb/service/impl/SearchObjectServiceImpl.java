package com.gofluent.elasticsearch.elasticsearchsyncdb.service.impl;

import com.gofluent.elasticsearch.elasticsearchsyncdb.model.SearchObject;

import com.gofluent.elasticsearch.elasticsearchsyncdb.repository.SearchObjectRepository;
import com.gofluent.elasticsearch.elasticsearchsyncdb.service.SearchObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchObjectServiceImpl implements SearchObjectService {

    @Autowired
    private SearchObjectRepository searchObjectRepository;

    @Override
    public void save(SearchObject searchObject) {
        searchObjectRepository.save(searchObject);
    }
}
