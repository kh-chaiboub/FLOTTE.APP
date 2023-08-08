package com.dev.device.service.impl;

import com.dev.device.domain.SimCard;
import com.dev.device.repository.SimCardRepository;
import com.dev.device.service.SimCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SimCardServiceImpl implements SimCardService {

    private final Logger log = LoggerFactory.getLogger(SimCardServiceImpl.class);
    private final SimCardRepository simCardRepository;

    public SimCardServiceImpl(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }


    @Override
    public SimCard save(SimCard simCard) {
        return simCardRepository.save(simCard);
    }

    @Override
    public Optional<SimCard> partialUpdate(SimCard simCard) {
        return simCardRepository
                .findById(simCard.getId())
                .map(
                        existingSimCard -> {
                            if (simCard.getPhoneNumber() != null) {
                                existingSimCard.setPhoneNumber(simCard.getPhoneNumber());
                            }
                            if (simCard.getSerialNumber() != null) {
                                existingSimCard.setSerialNumber(simCard.getSerialNumber());

                            }
                            if (simCard.getOperator() != null) {
                                existingSimCard.setOperator(simCard.getOperator());
                            }

                            return existingSimCard;
                        }

                )
                .map(simCardRepository::save);
    }

    @Override
    public List<SimCard> findAll() {
        return simCardRepository.findAll();
    }

    @Override
    public Page<SimCard> findAllWithEagerRelationships(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<SimCard> findOne(String id) {
        return simCardRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        simCardRepository.deleteById(id);
    }
}
