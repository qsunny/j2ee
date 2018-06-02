package com.aaron.datasearch.core.service.conference;

import com.aaron.datasearch.bean.Conference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by Administrator on 2018/6/2.
 */
public interface IConferenceService {
    Conference save(Conference conference);

    void delete(Conference conference);

    Conference findOne(String id);

    Iterable<Conference> findAll();

    public Page<Conference> findByName(String name, PageRequest pageRequest);

    List<Conference> findByName(String name);
}
