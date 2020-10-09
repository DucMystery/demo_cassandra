package com.bezkoder.spring.data.cassandra.controller;

import com.bezkoder.spring.data.cassandra.dto.response.FacebooksResponseDTO;
import com.bezkoder.spring.data.cassandra.mapper.FacebooksMapper;
import com.bezkoder.spring.data.cassandra.model.Facebooks;
import com.bezkoder.spring.data.cassandra.model.FacebooksCS;
import com.bezkoder.spring.data.cassandra.service.FacebooksCassandraService;
import com.bezkoder.spring.data.cassandra.service.FacebooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacebooksController {

    @Autowired
    private FacebooksService facebooksService;

    @Autowired
    private FacebooksMapper facebooksMapper;

    @Autowired
    private FacebooksCassandraService facebooksCassandraService;

    @RequestMapping(value = "getAll")
    public ResponseEntity<List<FacebooksResponseDTO>> getAll(){


        List<Facebooks> facebooksList = facebooksService.getAll();
        List<FacebooksResponseDTO> facebooksResponseDTOList = facebooksMapper.toFacebooksDTOS(facebooksList);
        return new ResponseEntity<>(facebooksResponseDTOList, HttpStatus.OK);

    }

    @RequestMapping(value = "create-all",method = RequestMethod.GET)
    public ResponseEntity<List<FacebooksCS>> createAll(){
        List<FacebooksResponseDTO> facebooksResponseDTOList = facebooksMapper.toFacebooksDTOS(facebooksService.getAll());
        List<FacebooksCS> facebooksCSList = facebooksCassandraService.createFacebooksCassandra(facebooksResponseDTOList);
        return new ResponseEntity<>(facebooksCSList,HttpStatus.OK);
    }

    @RequestMapping(value = "create",method = RequestMethod.POST)
    public ResponseEntity<FacebooksCS> create(@RequestBody FacebooksCS facebooksCS){
        FacebooksCS current = facebooksCassandraService.create(facebooksCS);
        return new ResponseEntity<>(current,HttpStatus.OK);
    }
}
