package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.model.Facebooks;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FacebooksService {

    List<Facebooks> getAll();

}
