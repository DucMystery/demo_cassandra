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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DataCrawlServiceImpl implements DataCrawlService {

    @Autowired
    private DataCrawlRepository dataCrawlRepository;

    @Autowired
    private GufoDataCassandraRepository gufoDataCassandraRepository;

    @Autowired
    private DataMapper dataMapper;

    int pageNumber =0;
    int pageSize =10000;
    int count =0;
    @Override
    public void findAllData(Pageable pageable) {

        Pageable currentPage = new Pageable() {
            @Override
            public int getPageNumber() {
                return pageable.getPageNumber();
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public long getOffset() {
                return pageNumber;
            }

            @Override
            public Sort getSort() {
                return pageable.getSort();
            }

            @Override
            public Pageable next() {
                return pageable.next();
            }

            @Override
            public Pageable previousOrFirst() {
                return pageable.previousOrFirst();
            }

            @Override
            public Pageable first() {
                return pageable.first();
            }

            @Override
            public boolean hasPrevious() {
                return pageable.hasPrevious();
            }
        };

        Page<DataCrawl> dataCrawlPage = dataCrawlRepository.findAll(currentPage);

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
            gufoDataCassandra.setCreatedDate(new Date());
            gufoDataCassandra.setDownloadDate(new Date());
            gufoDataCassandra.setPublishDate(new Date());

            gufoDataCassandraRepository.save(gufoDataCassandra);
            count+=1;
            dataCassandraList.add(gufoDataCassandra);
        }

        pageNumber+=10000;
        count+=10000;
    }
}
