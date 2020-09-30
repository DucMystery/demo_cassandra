package com.bezkoder.spring.data.cassandra.component.impl;

import com.bezkoder.spring.data.cassandra.component.HandlerExceptionFacebook;
import com.bezkoder.spring.data.cassandra.dto.request.FacebookError;
import org.springframework.stereotype.Component;

@Component
public class HandlerExceptionFacebookImpl implements HandlerExceptionFacebook {
    @Override
    public boolean valid(FacebookError error) {
        String check = "checkpoint";

        if (error.getMessage().contains(check)){
            return true;
        }
        return false;
    }
}
