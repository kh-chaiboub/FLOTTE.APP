package com.rh.vehicle.service.impl;

import com.rh.vehicle.domain.Vehicle;
import com.rh.vehicle.domain.VehicleNew;
import com.rh.vehicle.model.*;
import com.rh.vehicle.repository.VehicleRepository;
import com.rh.vehicle.repository.VehicleRepositoryItemstreeview;
import com.rh.vehicle.service.VehicleService;

import java.util.*;

import com.rh.vehicle.service.VehiculeProjectionListMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import reactor.core.publisher.Flux;

/**
 * Service Implementation for managing {@link Vehicle}.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;
    private final VehicleRepositoryItemstreeview vehicleRepositoryItemstreeview;

    private DeviceClient deviceClient;
    private LastPositionClient lastPositionClient;
    private RefOrganClient refOrganClient;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleRepositoryItemstreeview vehicleRepositoryItemstreeview, DeviceClient deviceClient, LastPositionClient lastPositionClient, RefOrganClient refOrganClient) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleRepositoryItemstreeview = vehicleRepositoryItemstreeview;

        this.deviceClient = deviceClient;
        this.lastPositionClient = lastPositionClient;
        this.refOrganClient = refOrganClient;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        log.debug("Request to save Vehicle : {}", vehicle);
        System.out.println("save" + vehicle);
        Vehicle result;
//        if (vehicle.getId() != null) {
            //throw new BadRequestAlertException("A new vl cannot already have an ID", ENTITY_NAME, "idexists");
       result = vehicleRepository.save(vehicle);
//       }
        return result;
    }

    @Override
    public Optional<Vehicle> partialUpdate(Vehicle vehicle) {
        log.debug("Request to partially update Vehicle : {}", vehicle);

        return vehicleRepository
                .findById(vehicle.getId())
                .map(existingVehicle -> {
                    if (vehicle.getRegistrationNumber() != null) {
                        existingVehicle.setRegistrationNumber(vehicle.getRegistrationNumber());
                    }
//                    if (vehicle.getAvailable() != null) {
//                        existingVehicle.setAvailable(vehicle.getAvailable());
//
//
//                    }
                    if (vehicle.getFrequence() != null) {
                        existingVehicle.setFrequence(vehicle.getFrequence());
                    }
                    if (vehicle.getIndicatif() != null) {
                        existingVehicle.setIndicatif(vehicle.getIndicatif());
                    }
                    if (vehicle.getVehiculeDesc() != null) {
                        existingVehicle.setVehiculeDesc(vehicle.getVehiculeDesc());
                    }
                    if (vehicle.getVehiculeMaxVitesse() != 0) {
                        existingVehicle.setVehiculeMaxVitesse(vehicle.getVehiculeMaxVitesse());
                    }
                    if (vehicle.getVehiculeKmEstime() != 0) {
                        existingVehicle.setVehiculeKmEstime(vehicle.getVehiculeKmEstime());
                    }
//                    if (vehicle.getVehiculeKmDate() != null) {
//                        existingVehicle.setVehiculeKmDate(vehicle.getVehiculeKmDate());
//                    }
                    if (vehicle.getVehiculeKmEstime() != 0) {
                        existingVehicle.setVehiculeKmEstime(vehicle.getVehiculeKmEstime());
                    }

                    return existingVehicle;
                })
                .map(vehicleRepository::save);
    }

    @Override
    public List<Vehicle> findAll(String authorization) {

        log.debug("Request to get all Vehicles");
        List<Vehicle> vehicles = vehicleRepository.findAll();
//                vehicles.forEach(vl -> {
//            if(vl.getDevice()!= null){
//                String idDevice;
//                if(vl.getDevice().getImei()!=null){
//                    idDevice = vl.getDevice().getImei();
//                LastPosition ls = lastPositionClient.getLastPositionByDeviceID(idDevice);
//                if(ls.getLongitude()!= null && ls.getLatitude() != null){
//                    vl.setLastPosition(ls);
//                }
//
//                }
//            }
//
//        });
        return vehicles;
    }

    @Override
    public List<VehiculeProjectionListMap> findAllProjection(Pageable pageable, String authorization) {
        return vehicleRepository.findAllByVehicle(pageable);
    }

    @Override
    public List<VehiculeProjectionListMap> findByRegistrationNumberContains(String imm, Pageable pageable, String authorization) {
        return vehicleRepository.findByRegistrationNumberContains(imm,pageable);
    }

//    @Override
//    public List<VehiculeProjectionListMap> findAllProjection(String authorization) {
//        return vehicleRepository.findAllProjectedBy();
//    }

    @Override
    public List<VehicleNew> findAllItemstreeview(String authorization) {
        List<VehicleNew> vehicles =vehicleRepositoryItemstreeview.findAll();
        return vehicles ;
    }


    @Override
    public List<Vehicle> findAllByRefOrgans(String authorization,User userProfil) {
        List<String> listRef = Arrays.asList(userProfil.getAuthorities());

        List<Vehicle> vehicles =  vehicleRepository.findByPrefOrganIn(listRef);

//        vehicles.forEach(vl -> {
//
//            if(vl.getDevice()!= null){
//                String idDevice;
//                if(vl.getDevice().getImei()!=null){
//                    idDevice = vl.getDevice().getImei();
//
//                    //Device d = deviceClient.getDeviceByDeviceID(authorization,idDevice);
//                    LastPosition ls = lastPositionClient.getLastPositionByDeviceID(idDevice);
//                    //vl.setDevice(d);
//                    vl.setLastPosition(ls);
//                }
//            }
//
//
//            //  RefOrgan ref = refOrganClient.findByRefOrganId(vl.getRefOrganID());
//
//            //  vl.setRefOrgan(ref);
//        });
        return vehicles;
    }

    @Override
    public Optional<Vehicle> findOne(String authorization,String id) {
        log.debug("Request to get Vehicle : {}", id);
        Optional<Vehicle> vehicle = vehicleRepository.findById(id);
        System.out.println("----------------------------");
//        if(vehicle.get().getDevice()!= null){
//            String idDevice;
//            if(vehicle.get().getDevice().getImei()!=null){
//                idDevice = vehicle.get().getDevice().getImei();
//                LastPosition ls = lastPositionClient.getLastPositionByDeviceID(idDevice);
//               vehicle.get().setLastPosition(ls);
//
//            }
//        }

        return vehicle;
    }


    @Override
    public void delete(String id) {
        log.debug("Request to delete Vehicle : {}", id);
        vehicleRepository.deleteById(id);
    }

    @Override
    public int countVehicleByNotDoteDevice() {

            return vehicleRepository.vehiculeNotDote() == null ? 0 : vehicleRepository.vehiculeNotDote();

//        return vehicleRepository.vehiculeNotDote();

    }
//
//
//    @Override
//    public List<Vehicle> ListVehicleByNotDoteDevice() {
//        List<Vehicle> vls = vehicleRepository.vehiculeNotDoteList();
//        return vls;
//
////        return vehicleRepository.vehiculeNotDote();
//
//    }
//
//    @Override
//    public int countVehiclesWithDeviceTimeAfter24Hours() {
//        Date currentDate = new Date();
//        Date twentyFourHoursAgo = new Date(currentDate.getTime() - (48 * 60 * 60 * 1000));
//        Integer count= vehicleRepository.countVehiclesWithDeviceTimeAfter(twentyFourHoursAgo);
//
//        return count == null ? 0 : count;
//
//    }

    @Override
    public List<Vehicle> findByDeviceStatusFalse() {
        return vehicleRepository.findByDeviceStatusFalse();
    }

    @Override
    public int countByDeviceStatusFalse() {
        return vehicleRepository.countByDeviceStatusFalse();
    }

    @Override
    public List<Vehicle> findByDeviceStatusTrue() {
        return vehicleRepository.findByDeviceStatusTrue();
    }

    @Override
    public int countByDeviceStatusTrue() {
        return vehicleRepository.countByDeviceStatusTrue();
    }

    @Override
    public int countVehicleByNotDoteDeviceByRefOrgan(User user) {
        List<String> listRef = Arrays.asList(user.getAuthorities());
       return vehicleRepository.vehiculeNotDoteByRefOrgan(listRef) == null ? 0 :vehicleRepository.vehiculeNotDoteByRefOrgan(listRef);


    }
//    @Override
//    public int countVehicleByDevice(boolean b) {
//
//        return vehicleRepository.DeviceStatuss(b)== null ? 0 :vehicleRepository.DeviceStatuss(b);
//    }
//    @Override
//    public int countVehicleByDeviceByRefOrgan(User user, boolean b) {
//        List<String> listRef = Arrays.asList(user.getAuthorities());
//        return vehicleRepository.DeviceStatussByPrefOrganIn(listRef,b)== null ? 0 :vehicleRepository.DeviceStatussByPrefOrganIn(listRef,b);
//    }

    @Override
    public List<VehiculeProjectionListMap> findAllProjectionStatusTrue(Pageable pageable, Object o) {
        return vehicleRepository.findByDeviceStatusTrue(pageable);
    }
    @Override
    public List<VehiculeProjectionListMap> findAllProjectionStatusFalse(Pageable pageable, Object o) {
        return vehicleRepository.findByDeviceStatusFalse(pageable);
    }
}
