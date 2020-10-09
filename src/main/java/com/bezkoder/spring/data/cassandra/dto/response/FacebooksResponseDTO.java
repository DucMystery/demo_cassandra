package com.bezkoder.spring.data.cassandra.dto.response;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;
@Data
public class FacebooksResponseDTO {

    private ObjectId id;
    private String facebookId;
    private String facebookUrl;
    private String facebookType;
    private String facebookName;
}
