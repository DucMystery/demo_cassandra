package com.bezkoder.spring.data.cassandra.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class FacebookTokenResponseDTO {

    private String token;
    private boolean valid;
    private boolean statusUse;
    private Date lastTimeUsed;
    private int countUse;
    private Date createdTime;
}
