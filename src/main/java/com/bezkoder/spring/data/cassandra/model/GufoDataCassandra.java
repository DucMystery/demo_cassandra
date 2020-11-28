package com.bezkoder.spring.data.cassandra.model;

import com.bezkoder.spring.data.cassandra.enums.DataType;
import com.bezkoder.spring.data.cassandra.model.opt.GufoDataFieldName;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.List;

@Data
@Table(value = "gufo_data_adv")
public class GufoDataCassandra {


    @PrimaryKey
    private String id;

    @Column(value = GufoDataFieldName.BEAN_TYPE)
    @CassandraType(type = com.datastax.driver.core.DataType.Name.TEXT)
    private DataType beanType;

    @Column(value = GufoDataFieldName.DOWNLOAD_DATE)
    private Date downloadDate;

    @Column(value = GufoDataFieldName.TITLE)
    private String title;

    @Column(value = GufoDataFieldName.FULL_CONTENTS)
    private List<String> fullContents;

    @Column(value = GufoDataFieldName.DESCRIPTION)
    private String description;

    @Column(value = GufoDataFieldName.CREATED_DATE)
    private Date createdDate;

    @Column(value = GufoDataFieldName.PUBLISH_DATE)
    private Date publishDate;

    @Column(value = GufoDataFieldName.URL)
    private String url;

    @Column(value = GufoDataFieldName.ATTACHMENT_URLS)
    private List<String> attachmentUrls;

    @Column(value = GufoDataFieldName.DOMAIN)
    private String domain;

    @Column(value = GufoDataFieldName.SHORT_DATE)
    private String shortDate;

    @Column(value = GufoDataFieldName.CLASSIFICATION)
    private String classification;

    @Column(value = GufoDataFieldName.PROBABILITY)
    private double probability;

    @Column(value = GufoDataFieldName.TRUSTED)
    private boolean trusted =false;

    @Column(value = GufoDataFieldName.SPECIAL_FIELDS)
    private String specialFields;
}
