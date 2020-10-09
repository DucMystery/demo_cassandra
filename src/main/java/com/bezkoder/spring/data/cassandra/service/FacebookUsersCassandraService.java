package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookUsersDTO;
import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import com.bezkoder.spring.data.cassandra.model.FacebookUsersCassandra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import java.util.List;

public interface FacebookUsersCassandraService {

    Slice<FacebookUsersCassandra> findAll(Pageable pageable);
    List<FacebookUsersCassandra> createUser(List<FacebookUsersCassandra> facebookUsersCassandraList);

    void create(FacebookUsersDTO facebookUsersDTO);

    void  delete();
}
