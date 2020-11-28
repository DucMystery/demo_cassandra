package com.bezkoder.spring.data.cassandra.controller;

import com.bezkoder.spring.data.cassandra.dto.request.DataCrawlDTO;
import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;
import com.bezkoder.spring.data.cassandra.service.DataCrawlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/data-crawl")
public class DataCrawlController {

    @Autowired
    private DataCrawlService dataCrawlService;

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<Void> findAll(@PageableDefault Pageable pageable){

        dataCrawlService.findAllData(pageable);

        return new ResponseEntity<>( HttpStatus.OK);
    }
}
