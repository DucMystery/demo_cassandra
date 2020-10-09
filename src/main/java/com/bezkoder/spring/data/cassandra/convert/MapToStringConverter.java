package com.bezkoder.spring.data.cassandra.convert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@WritingConverter
public class MapToStringConverter implements Converter<Map<String ,Object>,String> {
    private ObjectMapper objectMapper;

    public MapToStringConverter(ObjectMapper objectMapper) {
        this.objectMapper =objectMapper;
    }

    @SneakyThrows
    @Override
    public String convert( Map<String, Object> source)  {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
