package com.bezkoder.spring.data.cassandra.service;


import com.bezkoder.spring.data.cassandra.dto.response.FacebooksResponseDTO;
import com.bezkoder.spring.data.cassandra.mapper.FacebooksMapper;
import com.bezkoder.spring.data.cassandra.model.FacebooksCS;
import com.bezkoder.spring.data.cassandra.repository.FacebooksCassandraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class FacebooksCassandraServiceImpl implements FacebooksCassandraService{

    @Autowired
    private FacebooksCassandraRepository facebooksCassandraRepository;

    @Autowired
    private FacebooksMapper facebooksMapper;

    @Override
    public List<FacebooksCS> createFacebooksCassandra(List<FacebooksResponseDTO> facebooksResponseDTOList) {

        List<FacebooksCS> facebooksCSList = new ArrayList<>();

        for (FacebooksResponseDTO facebooksResponseDTO : facebooksResponseDTOList){
            FacebooksCS facebooksCS = new FacebooksCS();
            facebooksCS.setFacebookId(facebooksResponseDTO.getFacebookId());
            facebooksCS.setFacebookName(facebooksResponseDTO.getFacebookName());
            facebooksCS.setFacebookType(facebooksResponseDTO.getFacebookType());
            facebooksCS.setFacebookUrl(facebooksResponseDTO.getFacebookUrl());
            facebooksCassandraRepository.save(facebooksCS);
            facebooksCSList.add(facebooksCS);
        }
        return facebooksCSList;
    }

    @Override
    public FacebooksCS create(FacebooksCS facebooksCS) {
        facebooksCassandraRepository.save(facebooksCS);
        return facebooksCS;
    }
}
