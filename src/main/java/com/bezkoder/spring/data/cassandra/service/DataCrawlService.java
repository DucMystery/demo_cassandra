package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.dto.request.DataCrawlDTO;
import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;

import java.util.List;

public interface DataCrawlService {

    List<GufoDataCassandra> findAllData();
}
