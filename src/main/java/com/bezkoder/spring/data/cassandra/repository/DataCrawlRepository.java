package com.bezkoder.spring.data.cassandra.repository;

import com.bezkoder.spring.data.cassandra.model.DataCrawl;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataCrawlRepository extends MongoRepository<DataCrawl, String> {
}
