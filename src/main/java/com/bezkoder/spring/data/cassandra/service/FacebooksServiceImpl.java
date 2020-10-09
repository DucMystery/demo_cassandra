package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import com.bezkoder.spring.data.cassandra.model.Facebooks;
import com.bezkoder.spring.data.cassandra.repository.FacebooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FacebooksServiceImpl implements FacebooksService {

    @Autowired
    private FacebooksRepository facebooksRepository;

    @Override
    public List<Facebooks> getAll() {

        List<Facebooks> facebooksList = facebooksRepository.findAll();

        return facebooksList;
    }
}
