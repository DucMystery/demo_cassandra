package com.bezkoder.spring.data.cassandra.dto.request;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class DataCrawlDTO {

    private String url;

    private String title;

    private String description;

    private String content;

    private String source;

    private String imageUrl;
}
