package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.dto.request.DataCrawlDTO;
import com.bezkoder.spring.data.cassandra.enums.DataType;
import com.bezkoder.spring.data.cassandra.mapper.DataMapper;
import com.bezkoder.spring.data.cassandra.model.DataCrawl;
import com.bezkoder.spring.data.cassandra.model.GufoDataCassandra;
import com.bezkoder.spring.data.cassandra.repository.DataCrawlRepository;
import com.bezkoder.spring.data.cassandra.repository.GufoDataCassandraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataCrawlServiceImpl implements DataCrawlService {

    @Autowired
    private DataCrawlRepository dataCrawlRepository;

    @Autowired
    private GufoDataCassandraRepository gufoDataCassandraRepository;

    @Autowired
    private DataMapper dataMapper;
    @Override
    public List<GufoDataCassandra> findAllData() {

        Pageable pageable = PageRequest.of(0,100);

        int count = 0;

        Page<DataCrawl> dataCrawlPage = dataCrawlRepository.findAll(pageable);

        List<DataCrawl> dataCrawlList = dataCrawlPage.getContent();

        List<DataCrawlDTO> dataCrawlDTOList = dataMapper.toDataCrawlDTOs(dataCrawlList);

        List<GufoDataCassandra> dataCassandraList = new ArrayList<>();

        for (DataCrawlDTO dataCrawlDTO: dataCrawlDTOList){

            GufoDataCassandra gufoDataCassandra = dataMapper.toGufoDataCassandra(dataCrawlDTO);
            gufoDataCassandra.setDomain("https://www.facebook.com/");
            gufoDataCassandra.setBeanType(DataType.bean_content_news);
            gufoDataCassandra.setId("m"+Integer.toString(count));
            gufoDataCassandra.setSpecialFields("\"parentCategory\":\"the-thao\",\n" +
                    "    \"author\":\"Mystery1309\",\n" +
                    "    \"commentCount\":10,\n" +
                    "    \"subCategory\":\"the-thao_bong-da\"");
            gufoDataCassandraRepository.save(gufoDataCassandra);
            count+=1;
            dataCassandraList.add(gufoDataCassandra);
        }
        return dataCassandraList;
    }
}
