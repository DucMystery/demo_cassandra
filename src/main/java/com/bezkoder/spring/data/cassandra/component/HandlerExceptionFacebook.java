package com.bezkoder.spring.data.cassandra.component;


import com.bezkoder.spring.data.cassandra.dto.request.FacebookError;

public interface HandlerExceptionFacebook {
    boolean valid(FacebookError error);
}
