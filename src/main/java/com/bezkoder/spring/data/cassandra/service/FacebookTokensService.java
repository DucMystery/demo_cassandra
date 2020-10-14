package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookError;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenDTO;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenUpdateDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenResponseDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenWorkerResponse;
import com.bezkoder.spring.data.cassandra.model.FacebookTokens;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FacebookTokensService {

    List<FacebookTokens> findAllToken();

    FacebookTokens createToken(FacebookTokenDTO facebookTokenDTO);

    void deleteByChainToken(String chainToken);

    FacebookTokens updateTokensByChainToken(String chainToken, FacebookTokenUpdateDTO facebookTokenUpdateDTO);

    FacebookTokens getFacebookTokensByChainToken();

    Optional<FacebookTokens> findByToken(String token);

    FacebookTokens workerUpdateToken(String token);

    FacebookTokens  workerResponseError(FacebookTokenWorkerResponse facebookTokenWorkerResponse);

    FacebookTokenResponseDTO executeResponseWorker(FacebookTokenWorkerResponse facebookTokenWorkerResponse);
    List<FacebookTokens> readFromExcel(MultipartFile multipartFile);


}
