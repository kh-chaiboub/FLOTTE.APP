package com.reforgan.reforgan.service.impl;

import com.reforgan.reforgan.domain.RefOrgan;
import com.reforgan.reforgan.model.User;
import com.reforgan.reforgan.repository.RefOrganRepository;
import com.reforgan.reforgan.service.RefOrganService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import java.util.List;

@Service
@Transactional
public class RefOrganServiceImpl implements RefOrganService {
    private final Logger log = LoggerFactory.getLogger(RefOrganServiceImpl.class);
    private final RefOrganRepository refOrganRepository;

    public RefOrganServiceImpl(RefOrganRepository refOrganRepository) {
        this.refOrganRepository = refOrganRepository;
    }


    @Override
    public RefOrgan createRefOrgan(RefOrgan refOrgan) {
        log.debug("service request to save RefOrgan : {}", refOrgan);
        return refOrganRepository.save(refOrgan);
    }

    @Override
    public RefOrgan updateRefOrgan(RefOrgan refOrgan) {
        log.debug("service request to update RefOrgan : {}", refOrgan);

        return refOrganRepository.save(refOrgan);
    }

    @Override
    public List getAllRefOrgans() {

        log.debug("service request to get all RefOrgans");
        List<RefOrgan> refOrgans =refOrganRepository.findAll();
        //List<Node> nodes = new ArrayList<>();
//       for(){
//
//       }

        return refOrgans;
    }

    @Override
    public Optional<RefOrgan> getRefOrgan(String id) {
        log.debug("service request to get RefOrgan : {}", id);
        Optional<RefOrgan> refOrgan =refOrganRepository.findById(id);
        return refOrgan;
    }


    @Override
    public List<RefOrgan> getRefOrganByPrefOrgan(String p_ref) {
        log.debug("service request to get RefOrgan : {}", p_ref);
        return refOrganRepository.findByPrefOrgan(p_ref);
    }

    @Override
    public List<RefOrgan> findAllByRefOrgans(User userProfil) {

        List<String> listRef = Arrays.asList(userProfil.getAuthorities());
       // System.out.println(listRef);
        List<RefOrgan> refOrgansChild =refOrganRepository.findByIdIn(listRef);
        List<RefOrgan> refOrgans =RefOrgansByChild(refOrgansChild);

        return refOrgans;
      }

    @Override
    public List<RefOrgan> findAllByRefOrgansByPreOrgan(List pRef) {

        List<RefOrgan> refOrgansChild =refOrganRepository.findByIdIn(pRef);
        List<RefOrgan> refOrgans =RefOrgansByChild(refOrgansChild);

        return refOrgans;
    }

    public List<RefOrgan> RefOrgansByChild(List<RefOrgan> refOrgansChild ) {
        List<RefOrgan> refOrgans = new ArrayList<>();
        refOrgans.addAll(refOrgansChild);
        for(RefOrgan ref :refOrgansChild)  {
            RefOrgan refOrgan =null;
            do{
                if(refOrgan==null){
                    refOrgan =refOrganRepository.findById(ref.getPrefOrgan()).get();

                }else {
                    refOrgan =refOrganRepository.findById(refOrgan.getPrefOrgan()).get();
                }


                if(!refOrgans.contains(refOrgan)){
                    refOrgans.add(refOrgan);
                }
            }while (refOrgan.getPrefOrgan().isEmpty() || !refOrgan.getPrefOrgan().contains("0") );
        }
return refOrgans;
    }
    @Override
    public void deleteRefOrgan(String id) {
        refOrganRepository.deleteById(id);
    }
}
