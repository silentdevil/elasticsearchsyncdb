package com.gofluent.elasticsearch.elasticsearchsyncdb.controller;

import com.gofluent.elasticsearch.elasticsearchsyncdb.model.SearchObject;
import com.gofluent.elasticsearch.elasticsearchsyncdb.service.SearchObjectService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/rest/")
public class SearchController {

    @Autowired
    private SearchObjectService searchObjectService;

    @GetMapping(value = "/save")
    public ResponseEntity<String> save(@RequestParam Map<String, Object> jsonString) throws IOException {
        System.out.println("CALLED");
        SearchObject searchObject = new SearchObject();
        searchObject.setId(jsonString.get("id") + "");
        searchObject.setType(String.valueOf(jsonString.get("type")));
        searchObject.setDisplay(String.valueOf(jsonString.get("display")));
        searchObject.setKeyword(String.valueOf(jsonString.get("keyword")));
        searchObject.setReturnId(String.valueOf(jsonString.get("returnid")));
        searchObjectService.save(searchObject);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/findbyid/{id}")
    public String findById(@PathVariable String id) {
        SearchObject searchObject = searchObjectService.findById(id);
        return searchObject.getKeyword();
    }
}
