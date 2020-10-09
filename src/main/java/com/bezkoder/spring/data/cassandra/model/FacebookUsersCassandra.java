package com.bezkoder.spring.data.cassandra.model;

import com.bezkoder.spring.data.cassandra.convert.MapToStringConverter;
import com.bezkoder.spring.data.cassandra.convert.StringToMapConverter;
import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FacebookUsersCassandra {

    @PrimaryKey
    private String uid;
    private String name;
    private String link;
    private String phoneNumber;

    @CassandraType(type = DataType.Name.TEXT)
    @JsonSerialize(using = CustomeSerializer.class)
    private String details;
}
