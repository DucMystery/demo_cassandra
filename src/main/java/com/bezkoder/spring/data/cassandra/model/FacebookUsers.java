package com.bezkoder.spring.data.cassandra.model;

import jdk.nashorn.api.scripting.JSObject;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Map;

@Document(collection = "facebook_users")
@Data
public class FacebookUsers {

    @BsonProperty("_id")
    private ObjectId id;

    private String uid;

    @Field(name = "name")
    private String name;

    @Field(name = "link")
    private String link;

    @Field(name = "phone_number")
    private String phoneNumber;

    @Field(name = "details")
    private Map<String,Object> details;
}
