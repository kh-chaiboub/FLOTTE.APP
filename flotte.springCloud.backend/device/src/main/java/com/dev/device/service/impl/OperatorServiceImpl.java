package com.dev.device.service.impl;

import com.dev.device.domain.Operator;
import com.dev.device.repository.OperatorRepository;
import com.dev.device.service.OperatorService;
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
public class OperatorServiceImpl implements OperatorService {
    private final Logger log = LoggerFactory.getLogger(OperatorServiceImpl.class);
    private final OperatorRepository operatorRepository;

    public OperatorServiceImpl(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }


    @Override
    public Operator save(Operator operator) {
        return operatorRepository.save(operator);
    }

    @Override
    public Optional<Operator> partialUpdate(Operator operator) {
        return operatorRepository
                .findById(operator.getId())
                .map(
                        existingOperation -> {
                            if (operator.getOperatorName() != null) {
                                existingOperation.setOperatorName(operator.getOperatorName());
                            }

                            return existingOperation;
                        }

                )
                .map(operatorRepository::save);
    }

    @Override
    public List<Operator> findAll() {
        return operatorRepository.findAll();
    }

    @Override
    public Page<Operator> findAllWithEagerRelationships(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Operator> findOne(String id) {
        return operatorRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        operatorRepository.deleteById(id);

    }
}
