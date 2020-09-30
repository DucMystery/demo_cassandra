package com.bezkoder.spring.data.cassandra.dto.request;

import lombok.Data;

import java.util.Date;
@Data
public class FacebookTokenUpdateDTO {

    private boolean valid;
    private boolean statusUse;
    private Date lastTimeUsed;
    private int countUse;
}
