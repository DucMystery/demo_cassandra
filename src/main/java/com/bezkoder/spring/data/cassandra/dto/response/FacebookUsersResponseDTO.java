package com.bezkoder.spring.data.cassandra.dto.response;

import com.google.gson.JsonObject;
import lombok.Data;


@Data
public class FacebookUsersResponseDTO {

    private String uid;
    private String name;
    private String link;
    private String phoneNumber;

    private JsonObject details;
}
