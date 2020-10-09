package com.bezkoder.spring.data.cassandra.model;

import com.bezkoder.spring.data.cassandra.enums.FacebookType;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "facebooks")
public class Facebooks {

    @BsonProperty("_id")
    private ObjectId id;

    @Field(name = "facebook_id")
    private String facebookId;

    @Field(name = "facebook_url")
    private String facebookUrl;

    @Field(name = "facebook_type")
    private String facebookType;

    @Field(name = "facebook_name")
    private String facebookName;
}
