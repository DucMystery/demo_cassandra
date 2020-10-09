package com.bezkoder.spring.data.cassandra.dto.request;

import lombok.Data;
import org.json.JSONObject;

import java.util.Map;

@Data
public class FacebookUsersDTO {
    private String uid;
    private String name;
    private String link;
    private String phoneNumber;
    private Map<String ,Object> details;
}
