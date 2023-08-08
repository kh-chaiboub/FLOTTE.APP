package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.Mission;
import com.rh.vehicle.repository.MissionRepository;
import com.rh.vehicle.service.MissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class MissionServiceImpl implements MissionService {

    private final Logger log = LoggerFactory.getLogger(MissionServiceImpl.class);

    private final MissionRepository missionRepository;

    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public Mission save(Mission mission) {
        log.debug("Request to save Mission : {}", mission);
        return missionRepository.save(mission);
    }

    @Override
    public Optional<Mission> partialUpdate(Mission mission) {
        log.debug("Request to partially update Mission : {}", mission);

        return missionRepository
                .findById(mission.getId())
                .map(existingMission -> {
                    if (mission.getMission() != null) {
                        existingMission.setMission(mission.getMission());
                    }

                    return existingMission;
                })
                .map(missionRepository::save);
    }

    @Override
    public List<Mission> findAll() {
        log.debug("Request to get all Missions");
        return missionRepository.findAll();
    }

    @Override
    public Optional<Mission> findOne(String id) {
        log.debug("Request to get Mission : {}", id);
        return missionRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Mission : {}", id);
        missionRepository.deleteById(id);
    }
}
