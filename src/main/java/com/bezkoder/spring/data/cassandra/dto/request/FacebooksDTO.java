package com.bezkoder.spring.data.cassandra.dto.request;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
public class FacebooksDTO {


    private String facebookId;

    private String facebookUrl;

    private String facebookType;

    private String facebookName;
}
