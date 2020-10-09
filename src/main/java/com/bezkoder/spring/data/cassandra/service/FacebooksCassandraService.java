package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.dto.response.FacebooksResponseDTO;
import com.bezkoder.spring.data.cassandra.model.FacebooksCS;

import java.util.List;

public interface FacebooksCassandraService {

    List<FacebooksCS> createFacebooksCassandra(List<FacebooksResponseDTO> facebooksResponseDTOList);

    FacebooksCS create(FacebooksCS facebooksCS);
}
