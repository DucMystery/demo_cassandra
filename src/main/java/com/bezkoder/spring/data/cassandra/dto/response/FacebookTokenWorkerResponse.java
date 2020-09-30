package com.bezkoder.spring.data.cassandra.dto.response;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookError;
import lombok.Data;

@Data
public class FacebookTokenWorkerResponse {

    private String token;

    private FacebookError facebookError;


}
