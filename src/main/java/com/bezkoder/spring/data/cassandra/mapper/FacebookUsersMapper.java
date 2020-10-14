package com.bezkoder.spring.data.cassandra.mapper;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookUsersDTO;
import com.bezkoder.spring.data.cassandra.dto.request.FacebooksIntermediateDTO;
import com.bezkoder.spring.data.cassandra.dto.request.ObjectMongo;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookUsersResponseDTO;
import com.bezkoder.spring.data.cassandra.model.FacebookUsers;
import com.bezkoder.spring.data.cassandra.model.FacebookUsersCassandra;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.api.scripting.JSObject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
@Component
public interface FacebookUsersMapper {
     static final String TO_FACEBOOK_USERS_DTO = "toFacebookUsersDTO";
     static final String TO_FACEBOOK_USERS_DTOS = "toFacebookUsersDTOS";
     static final String TO_FACEBOOK_USERS = "toFacebookUsers";
     static final String TO_FACEBOOK_USERSES = "toFacebookUserses";
     static final String TO_FACEBOOK_USERS_CASSANDRAS = "toFacebookUsersCassandras";






}
