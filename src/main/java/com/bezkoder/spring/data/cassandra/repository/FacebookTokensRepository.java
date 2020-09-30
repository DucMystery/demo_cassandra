package com.bezkoder.spring.data.cassandra.repository;

import com.bezkoder.spring.data.cassandra.model.FacebookTokens;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacebookTokensRepository extends CassandraRepository<FacebookTokens,String> {

    @AllowFiltering
    List<FacebookTokens> findAllByStatusUseAndAndValid(boolean statusUse, boolean valid);
}
