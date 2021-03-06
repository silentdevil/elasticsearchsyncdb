package com.gofluent.elasticsearch.elasticsearchsyncdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gofluent"})
public class ElasticsearchsyncdbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticsearchsyncdbApplication.class, args);
	}
}
