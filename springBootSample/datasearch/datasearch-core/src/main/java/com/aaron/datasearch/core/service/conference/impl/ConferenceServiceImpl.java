package com.aaron.datasearch.core.service.conference.impl;

import com.aaron.datasearch.bean.Conference;
import com.aaron.datasearch.core.dao.conference.ConferenceRepository;
import com.aaron.datasearch.core.service.conference.IConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Administrator on 2018/6/2.
 */
@Service
public class ConferenceServiceImpl implements IConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Override
    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void delete(Conference conference) {
        conferenceRepository.delete(conference);
    }

    @Override
    public Conference findOne(String id) {
        return conferenceRepository.findOne(id);
    }

    @Override
    public Iterable<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    @Override
    public Page<Conference> findByName(String name, PageRequest pageRequest) {
        return conferenceRepository.findByName(name,pageRequest);
    }

    @Override
    public List<Conference> findByName(String name) {
        return conferenceRepository.findByName(name);
    }

    @PostConstruct
    public void insertDataSample() {

        // Remove all documents
        conferenceRepository.deleteAll();
        conferenceRepository.refresh();

        // Save data sample
//        conferenceRepository.save(Conference.builder().date("2014-11-06").name("Spring eXchange 2014 - London")
//                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(51.500152D, -0.126236D)).build());
//        conferenceRepository.save(Conference.builder().date("2014-12-07").name("Scala eXchange 2014 - London")
//                .keywords(Arrays.asList("scala", "play", "java")).location(new GeoPoint(51.500152D, -0.126236D)).build());
//        conferenceRepository.save(Conference.builder().date("2014-11-20").name("Elasticsearch 2014 - Berlin")
//                .keywords(Arrays.asList("java", "elasticsearch", "kibana")).location(new GeoPoint(52.5234051D, 13.4113999))
//                .build());
//        conferenceRepository.save(Conference.builder().date("2014-11-12").name("AWS London 2014")
//                .keywords(Arrays.asList("cloud", "aws")).location(new GeoPoint(51.500152D, -0.126236D)).build());
//        conferenceRepository.save(Conference.builder().date("2014-10-04").name("JDD14 - Cracow")
//                .keywords(Arrays.asList("java", "spring")).location(new GeoPoint(50.0646501D, 19.9449799)).build());
    }
}
