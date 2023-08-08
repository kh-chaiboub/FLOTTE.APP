package com.reforgan.reforgan.service;

import com.reforgan.reforgan.domain.RefOrgan;
import com.reforgan.reforgan.model.User;


import java.util.List;
import java.util.Optional;

public interface RefOrganService {
    RefOrgan createRefOrgan( RefOrgan refOrgan);
    RefOrgan updateRefOrgan( RefOrgan refOrgan);
    List getAllRefOrgans();
    Optional<RefOrgan> getRefOrgan(String id);

    List<RefOrgan> findAllByRefOrgansByPreOrgan(List pRef);

    void deleteRefOrgan(String id);

    List<RefOrgan> getRefOrganByPrefOrgan(String prefOrgan);

    List<RefOrgan> findAllByRefOrgans(User userProfil);
}
