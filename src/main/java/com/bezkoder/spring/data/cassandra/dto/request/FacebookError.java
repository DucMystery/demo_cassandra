package com.bezkoder.spring.data.cassandra.dto.request;

import lombok.Data;

@Data
public class FacebookError {

   private String errorData;
   private String message;

}
