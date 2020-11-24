package com.bezkoder.spring.data.cassandra.repository;

import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GufoDataCassandraRepository extends CassandraRepository<GufoDataCassandra, String> {
}
