package com.bezkoder.spring.data.cassandra.service;


import com.bezkoder.spring.data.cassandra.dto.request.FacebookUsersDTO;
import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FacebookUsersService {

    Page<FacebookUsers> getAll(Pageable pageable);
    List<FacebookUsers> getAllByLimitAndSkip(Pageable pageable);
}
