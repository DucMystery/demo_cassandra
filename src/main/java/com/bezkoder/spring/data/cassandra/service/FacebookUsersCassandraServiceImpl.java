package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookUsersDTO;
import com.bezkoder.spring.data.cassandra.exception.FacebookUsersAlreadyExistException;
import com.bezkoder.spring.data.cassandra.mapper.FacebookUsersMapper;
import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import com.bezkoder.spring.data.cassandra.model.FacebookUsersCassandra;
import com.bezkoder.spring.data.cassandra.repository.FacebookUsersCassandraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacebookUsersCassandraServiceImpl implements FacebookUsersCassandraService {
    @Autowired
    private FacebookUsersCassandraRepository facebookUsersCassandraRepository;

    @Autowired
    private FacebookUsersMapper facebookUsersMapper;
    @Override
    public Slice<FacebookUsersCassandra> findAll(Pageable pageable) {
        return facebookUsersCassandraRepository.findAll(pageable);
    }

    @Override
    public List<FacebookUsersCassandra> createUser(List<FacebookUsersCassandra> facebookUsersCassandraList) {
        for (FacebookUsersCassandra f : facebookUsersCassandraList){
            facebookUsersCassandraRepository.save(f);
        }

        return facebookUsersCassandraList;
    }

    @Override
    public void create(FacebookUsersDTO facebookUsersDTO) {

        Optional<FacebookUsersCassandra> facebookUsersCassandra = facebookUsersCassandraRepository.findById(facebookUsersDTO.getUid());
        if (facebookUsersCassandra.isPresent()){
            throw new FacebookUsersAlreadyExistException("User already exist");
        }
//        FacebookUsersCassandra currentUser = facebookUsersMapper.toFacebookUsersCassandra(facebookUsersDTO);
//        facebookUsersCassandraRepository.save(currentUser);
//        return currentUser;
    }

    @Override
    public void delete() {
        facebookUsersCassandraRepository.deleteAll();
    }


}
