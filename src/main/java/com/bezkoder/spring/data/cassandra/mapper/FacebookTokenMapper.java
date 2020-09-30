package com.bezkoder.spring.data.cassandra.mapper;

import com.bezkoder.spring.data.cassandra.dto.request.FacebookTokenDTO;
import com.bezkoder.spring.data.cassandra.dto.response.FacebookTokenResponseDTO;
import com.bezkoder.spring.data.cassandra.model.FacebookTokens;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface FacebookTokenMapper {

    @Named("toFacebookTokenDTO")
    FacebookTokenResponseDTO toFacebookTokenDTO(FacebookTokens facebookTokens);

    @Named("toFacebookToken")
    FacebookTokens toFacebookToken(FacebookTokenDTO facebookTokenDTO);

    @Named("toFacebookTokens")
    List<FacebookTokens> toFacebookTokens(List<FacebookTokenDTO> facebookTokenDTOList);

    @Named("toFacebookTokenDTOS")
    List<FacebookTokenResponseDTO> toFacebookTokenDTOS(List<FacebookTokens> facebookTokensList);
}
