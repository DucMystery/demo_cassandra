package com.bezkoder.spring.data.cassandra.service;

import com.bezkoder.spring.data.cassandra.model.Facebooks;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface FacebooksService {

    List<Facebooks> getAll();

    Slice<Facebooks> findAll(Pageable pageable);

}
