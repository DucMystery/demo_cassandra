package com.bezkoder.spring.data.cassandra.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "facebooks_cs")
@Data
//@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class FacebooksCS {

    @PrimaryKey
    private String facebookId;

    private String facebookUrl;

    private String facebookType;

    private String facebookName;
}
