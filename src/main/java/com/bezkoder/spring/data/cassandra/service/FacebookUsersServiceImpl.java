package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.mapper.FacebookUsersMapper;
import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import com.bezkoder.spring.data.cassandra.repository.FacebookUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FacebookUsersServiceImpl implements FacebookUsersService {

    @Autowired
    private FacebookUsersRepository facebookUsersRepository;
    @Autowired
    private FacebookUsersMapper facebookUsersMapper;
    @Override
    public Page<FacebookUsers> getAll(Pageable pageable) {
        Page<FacebookUsers> facebookUsersPage = facebookUsersRepository.findAll(pageable);

        int limit = 10 ;
        return facebookUsersPage;
    }

    @Override
    public List<FacebookUsers> getAllByLimitAndSkip(Pageable pageable) {
        Page<FacebookUsers> facebookUsersPage = facebookUsersRepository.findAll(pageable);
        List<FacebookUsers> facebookUsersList = StreamSupport.stream(facebookUsersPage.spliterator(),false).collect(Collectors.toList());
        return facebookUsersList;
    }

}
