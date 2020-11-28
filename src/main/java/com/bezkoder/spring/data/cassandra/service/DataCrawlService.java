package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DataCrawlService {

    void findAllData(Pageable pageable);
}
