package com.bezkoder.spring.data.cassandra.dto.request;

import jdk.nashorn.api.scripting.JSObject;
import lombok.Data;

@Data
public class ObjectMongo {

    private int limit;
    private int skip;
}
