package com.bezkoder.spring.data.cassandra.mapper;

import com.bezkoder.spring.data.cassandra.dto.request.DataCrawlDTO;
import com.bezkoder.spring.data.cassandra.model.DataCrawl;
import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;
import com.bezkoder.spring.data.cassandra.model.opt.AttachmentUrl;
import com.bezkoder.spring.data.cassandra.model.opt.FullContent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DataMapper {

    @Named("convertImage")
    default List<String> convertString(String image){

        if (image==null){
            image="https://i1-vnexpress.vnecdn.net/2020/11/24/bden-chon-noi-cac-2938-1606179483.jpg?w=1020&h=0&q=100&dpr=1&fit=crop&s=nnzkBv_OeiGkJ2jZWJw1zQ";
        }

        List<String> stringList = new ArrayList<>();

        AttachmentUrl attachmentUrl = new AttachmentUrl();
        attachmentUrl.setId("01");
        attachmentUrl.setType("img");
        attachmentUrl.setUrl(image);

        try {
            String save = new ObjectMapper().writeValueAsString(attachmentUrl);
            stringList.add(save);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return stringList;
    }

    @Named("convertContent")
    default List<String> convertContent(String content){

        if (content==null){
            content="Tổng thống đắc cử Biden lựa chọn 6 người vào nội các mới, lãnh đạo các cơ quan ngoại giao và an ninh.\n" +
                    "Trong danh sách những lựa chọn nội các đầu tiên được Tổng thống đắc cử Joe Biden công bố hôm 23/11, cựu thứ trưởng ngoại giao Antony Blinken, đồng thời là trợ lý lâu năm của Biden, được lựa chọn làm Ngoại trưởng. Blinken, 58 tuổi, tốt nghiệp Đại học Harvard và Trường Luật Columbia, từng hỗ trợ để Biden giành được đề cử ứng viên tổng thống của đảng Dân chủ năm 2008.";
        }

        ObjectMapper mapper = new ObjectMapper();

        String[] contents = content.split("\n");
        List<String> data = new ArrayList<>();
        for (String s: contents){

            FullContent fullContent = new FullContent();
            fullContent.setId(Integer.toString(data.size()));
            fullContent.setContent(s);
            fullContent.setType("text");
            try {
                String save = mapper.writeValueAsString(fullContent);
                data.add(save);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
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
