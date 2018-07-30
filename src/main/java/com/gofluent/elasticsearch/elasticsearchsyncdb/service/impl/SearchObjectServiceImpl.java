package com.gofluent.elasticsearch.elasticsearchsyncdb.service.impl;


import com.gofluent.elasticsearch.elasticsearchsyncdb.repository.SearchObjectRepository;
import com.gofluent.elasticsearch.elasticsearchsyncdb.service.SearchObjectService;
import com.gofluent.elasticsearch.model.SearchObject;
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

    @Override
    public SearchObject findById(String id) {
        return searchObjectRepository.findById(id);
    }
}
