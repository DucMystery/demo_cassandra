package com.bezkoder.spring.data.cassandra.mapper;

import com.bezkoder.spring.data.cassandra.dto.request.DataCrawlDTO;
import com.bezkoder.spring.data.cassandra.model.DataCrawl;
import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DataMapper {

    @Named("convertImage")
    default List<String> convertString(String image){

        List<String> stringList = new ArrayList<>();
        stringList.add(image);
        return stringList;
    }

    @Named("convertContent")
    default List<String> convertContent(String fullContent){

        String[] contents = fullContent.split("\n");
        List<String> data = new ArrayList<>();
        for (String s: contents){

            data.add(s);
        }

        return data;
    }

    @Named("toDataCrawlDTO")
    DataCrawlDTO toDataCrawlDTO(DataCrawl dataCrawl);

    @Named("toDataCrawlDTOs")
    List<DataCrawlDTO> toDataCrawlDTOs(List<DataCrawl> dataCrawls);

    @Named("toGufoDataCassandra")
    @Mapping(target = "attachmentUrls",source = "imageUrl", qualifiedByName ="convertImage")
    @Mapping(target = "fullContents",source = "content", qualifiedByName ="convertContent")
    GufoDataCassandra toGufoDataCassandra(DataCrawlDTO dataCrawlDTO);
}
