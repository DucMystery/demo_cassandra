package com.bezkoder.spring.data.cassandra.controller;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookUsersDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookUsersResponseDTO;
import com.bezkoder.spring.data.cassandra.mapper.FacebookUsersMapper;
import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import com.bezkoder.spring.data.cassandra.model.FacebookUsersCassandra;
import com.bezkoder.spring.data.cassandra.service.FacebookUsersService;
import com.bezkoder.spring.data.cassandra.service.FacebookUsersCassandraService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FacebookUsersController {

    @Autowired
    private FacebookUsersService facebookUsersService;

    @Autowired
    private FacebookUsersMapper facebookUsersMapper;

    @Autowired
    private FacebookUsersCassandraService facebookUsersCassandraService;

    @RequestMapping(value = "get-all",method = RequestMethod.GET)
    public ResponseEntity<List<FacebookUsersResponseDTO>> getAll(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="get-limit",method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getAllByLimit(@PageableDefault Pageable pageable){
        Pageable currentPage = new Pageable() {
            @Override
            public int getPageNumber() {
                return pageable.getPageNumber();
            }

            @Override
            public int getPageSize() {
                return 25;
            }

            @Override
            public long getOffset() {
                return 89175;
            }

            @Override
            public Sort getSort() {
                return pageable.getSort();
            }

            @Override
            public Pageable next() {
                return pageable.next();
            }

            @Override
            public Pageable previousOrFirst() {
                return pageable.previousOrFirst();
            }

            @Override
            public Pageable first() {
                return pageable.first();
            }

            @Override
            public boolean hasPrevious() {
                return pageable.hasPrevious();
            }
        };
        List<FacebookUsers> facebookUsersPage = facebookUsersService.getAllByLimitAndSkip(currentPage);

        List<FacebookUsersCassandra> facebookUsersCassandraList = new ArrayList<>();
        for (FacebookUsers f: facebookUsersPage){
            try {
                JSONObject jsonObject = new JSONObject(f.getDetails());
                FacebookUsersCassandra facebookUsersCassandra = new FacebookUsersCassandra();
                facebookUsersCassandra.setUid(f.getUid());
                facebookUsersCassandra.setLink(f.getLink());
                facebookUsersCassandra.setName(f.getName());
                facebookUsersCassandra.setPhoneNumber(f.getPhoneNumber());
                facebookUsersCassandra.setDetails(jsonObject.toString());
                facebookUsersCassandraList.add(facebookUsersCassandra);
            }catch (Exception e){
                System.err.println(e);
            }

        }
        facebookUsersCassandraService.createUser(facebookUsersCassandraList);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public ResponseEntity<Void> deleteAll(){
        facebookUsersCassandraService.delete();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public ResponseEntity<Slice<FacebookUsersCassandra>> findAll(@PageableDefault(size = 13) Pageable pageable){
        Slice<FacebookUsersCassandra> facebookUsersCassandraList = facebookUsersCassandraService.findAll(pageable);
        return new ResponseEntity<>(facebookUsersCassandraList,HttpStatus.OK);
    }
}
