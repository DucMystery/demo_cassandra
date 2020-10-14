package com.bezkoder.spring.data.cassandra.controller;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookError;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenDTO;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenUpdateDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenResponseDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenWorkerResponse;
import com.bezkoder.spring.data.cassandra.mapper.FacebookTokenMapper;
import com.bezkoder.spring.data.cassandra.model.FacebookTokens;
import com.bezkoder.spring.data.cassandra.service.FacebookTokensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FacebookTokensController {

    @Autowired
    private FacebookTokensService facebookTokensService;

    @Autowired
    private FacebookTokenMapper facebookTokenMapper;

    @RequestMapping(value = "/facebook-tokens", method = RequestMethod.POST)
    public ResponseEntity<FacebookTokenResponseDTO> createFacebookToken(@Valid @RequestBody FacebookTokenDTO facebookTokenDTO){
        FacebookTokens facebookTokens = facebookTokensService.createToken(facebookTokenDTO);
        FacebookTokenResponseDTO facebookTokenResponseDTO = facebookTokenMapper.toFacebookTokenDTO(facebookTokens);

        return new ResponseEntity<>(facebookTokenResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/facebook-tokens", method = RequestMethod.GET)
    public ResponseEntity<List<FacebookTokenResponseDTO>> getAll(){
        List<FacebookTokens> facebookTokensList = facebookTokensService.findAllToken();
        List<FacebookTokenResponseDTO> facebookTokenResponseDTOS = facebookTokenMapper.toFacebookTokenDTOS(facebookTokensList);
        return new ResponseEntity<>(facebookTokenResponseDTOS,HttpStatus.OK);
    }

    @RequestMapping(value = "/facebook-tokens/update/{token}", method = RequestMethod.POST)
    public ResponseEntity<FacebookTokenResponseDTO> updateFacebookToken(@PathVariable String token, @Valid @RequestBody FacebookTokenUpdateDTO facebookTokenUpdateDTO){
        FacebookTokens facebookTokens = facebookTokensService.updateTokensByChainToken(token,facebookTokenUpdateDTO);
        FacebookTokenResponseDTO facebookTokenResponseDTO = facebookTokenMapper.toFacebookTokenDTO(facebookTokens);
        return new ResponseEntity<>(facebookTokenResponseDTO,HttpStatus.OK);
    }

    @RequestMapping(value = "/facebook-tokens/delete/{token}",method = RequestMethod.POST)
    public ResponseEntity<Void> deleteToken(@PathVariable String token){
        facebookTokensService.deleteByChainToken(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/facebook-tokens/get", method = RequestMethod.GET)
    public ResponseEntity<FacebookTokenResponseDTO> getChainToken(){
        FacebookTokens facebookTokens = facebookTokensService.getFacebookTokensByChainToken();
        FacebookTokenResponseDTO facebookTokenResponseDTO = facebookTokenMapper.toFacebookTokenDTO(facebookTokens);
        return new ResponseEntity<>(facebookTokenResponseDTO,HttpStatus.OK);
    }

    @RequestMapping(value = "/facebook-tokens/worker-update")
    public ResponseEntity<FacebookTokenResponseDTO> workerUpdateToken(@Valid @RequestBody FacebookTokenWorkerResponse facebookTokenWorkerResponse){
        FacebookTokenResponseDTO facebookTokenResponseDTO = facebookTokensService.executeResponseWorker(facebookTokenWorkerResponse);
        return new ResponseEntity<>(facebookTokenResponseDTO,HttpStatus.OK);
    }

    @RequestMapping(value = "/read-from-excel",method = RequestMethod.POST)
    public ResponseEntity<List<FacebookTokenResponseDTO>> readFileExcel(@RequestPart MultipartFile multipartFile){
        List<FacebookTokens> facebookTokensList = facebookTokensService.readFromExcel(multipartFile);
        List<FacebookTokenResponseDTO> facebookTokenResponseDTOList = facebookTokenMapper.toFacebookTokenDTOS(facebookTokensList);
        return new ResponseEntity<>(facebookTokenResponseDTOList,HttpStatus.OK);
    }
}
