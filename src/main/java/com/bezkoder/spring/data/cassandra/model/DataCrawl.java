package com.bezkoder.spring.data.cassandra.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(value = "datacrawl")
@Data
public class DataCrawl {

    private String url;

    private String title;

    private String description;

    private int priority;

    @Field(value = "url_title")
    private String urlTitle;

    private String content;

    private String source;

    @Field(value = "image_url")
    private String imageUrl;
}
