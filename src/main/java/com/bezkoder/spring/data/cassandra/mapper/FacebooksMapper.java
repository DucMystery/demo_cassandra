package com.bezkoder.spring.data.cassandra.mapper;

import com.bezkoder.spring.data.cassandra.dto.response.FacebooksResponseDTO;
import com.bezkoder.spring.data.cassandra.model.Facebooks;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FacebooksMapper {

    @Named("toFacebooksDTO")
    FacebooksResponseDTO toFacebooksDTO(Facebooks facebooks);

    @Named("toFacebooksDTOS")
    List<FacebooksResponseDTO> toFacebooksDTOS(List<Facebooks> facebooksList);


}
