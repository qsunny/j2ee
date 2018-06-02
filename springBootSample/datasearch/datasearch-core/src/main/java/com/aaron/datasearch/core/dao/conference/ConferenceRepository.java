package com.aaron.datasearch.core.dao.conference;

import com.aaron.datasearch.bean.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by Aaron.qiu on 2018/6/2.
 */
public interface ConferenceRepository extends ElasticsearchRepository<Conference, String> {

    Conference findOne(String id);

    Iterable<Conference> findAll();

    Page<Conference> findByName(String name, PageRequest pageRequest);

    List<Conference> findByName(String name);

}
