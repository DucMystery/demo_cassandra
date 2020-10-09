package com.bezkoder.spring.data.cassandra.repository;

import com.bezkoder.spring.data.cassandra.model.FacebookUsersCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacebookUsersCassandraRepository extends CassandraRepository<FacebookUsersCassandra,String> {
}
