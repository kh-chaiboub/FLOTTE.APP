package com.dev.device.resource;
import com.dev.device.domain.Brand;
import com.dev.device.domain.BrandCount;
import com.dev.device.domain.Device;
import com.dev.device.model.UserClient;
import com.dev.device.service.DeviceProjectionListMap;
import com.dev.device.service.DeviceService;
import com.dev.device.util.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/devices")
public class DeviceResource {
    Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    private static final String ENTITY_NAME = "device";

    private static DeviceService deviceService;
    @Autowired
    private UserClient userClient;

    public DeviceResource(UserClient userClient, DeviceService deviceService) {
        this.deviceService = deviceService;
        this.userClient = userClient;

    }

    @GetMapping("/devices")
    @PreAuthorize("hasAuthority('USER')")
    public List<Device> getAllDevices() {
        LOGGER.info("REST request to get all Categories");
        return deviceService.findAll();
    }

    @GetMapping("/projection/devices")
    @PreAuthorize("hasAuthority('USER')")
    public List<DeviceProjectionListMap> getAllDevicesProjection(
            @RequestParam(value="page",defaultValue = "0") int page,
            @RequestParam(value="size",defaultValue = "50") int size,
            @RequestHeader Map<String, String> headers) {

        LOGGER.info("REST request to get all Vehicles");

        Pageable secondPageWithFiveElements = PageRequest.of(page, size);
        List<DeviceProjectionListMap> dvs = deviceService.findAllProjection(secondPageWithFiveElements,null);
        return dvs;

    }

    @GetMapping("/devicesPage")
    @PreAuthorize("hasAuthority('USER')")
    public Page<Device> getAllDevicesPage(Pageable pageable) {
        LOGGER.debug("REST request to get all Devices");
        LOGGER.info("REST request to All Devices : {}");
        return deviceService.findAllWithEagerRelationships(pageable);
    }


    @GetMapping("/devices/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Device> findByID(@PathVariable String id) {
        LOGGER.debug("REST request to get Device : {}", id);
        Optional<Device> device = null;
            device = deviceService.findOne(id);
        return device;

    }


    @GetMapping("/devices/deviceID/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Device> findByDeviceID(@RequestHeader Map<String, String> headers, @PathVariable String id) {
        System.out.println(headers);
        LOGGER.debug("REST request to get Device : {}", id);
        Optional<Device> device = null;
            device = deviceService.findByDeviceID(id);
        return device;

    }

    @GetMapping("/devices/imei/{imei}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Device> findByImei(@RequestHeader Map<String, String> headers, @PathVariable String imei) {
        System.out.println(headers);
        LOGGER.debug("REST request to get imei : {}", imei);
        Optional<Device> device = null;
        device = deviceService.findByImei(imei);

        return device;


    }

    @GetMapping("/devices/TotaldeviceByBrand")
    @PreAuthorize("hasAuthority('USER')")
    public List<Brand> DeviceByBrand(){
      return deviceService.TotalDeviceByBrand();
    }

    @GetMapping("/devices/countByBrand")
    @PreAuthorize("hasAuthority('USER')")
    public List<BrandCount> countDevicesByBrand() {
        return deviceService.countDevicesByBrand();
    }


    @PostMapping("/devices")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Device> createAutoService(@RequestHeader Map<String, String> headers, @RequestBody Device device) throws URISyntaxException {
        LOGGER.debug("REST request to save Device : {}", device);
        System.out.println("REST request to save Device : {}" + device);
        Device result = null;
            LOGGER.info("A new device cannot already have an ID", ENTITY_NAME, "idexists ");
            result = deviceService.save(device);
            LOGGER.info("A new device cannot already have an ID" + headers);
            return new ResponseEntity(result, OK);

    }

    @PutMapping("/devices/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Device> updateDevice(@Valid @RequestBody Device device,@RequestHeader Map<String, String> headers) throws URISyntaxException {
        Optional<Device> result =null;
        LOGGER.info("REST request to update Device : {}", device);
        result = deviceService.partialUpdate(device);
        LOGGER.info("REST request to update Device : by", headers);
        return ResponseEntity.ok()
                .body(result.get());
    }

    @DeleteMapping("/devices/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<HttpResponse> deleteDevice(@PathVariable String id,@RequestHeader Map<String, String> headers) {
        LOGGER.debug("REST request to delete Device : {}", id);
            deviceService.delete(id);
            return response(OK, "Device_DELETED_SUCCESSFULLY by "+ headers);

    }


    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(),
                message), httpStatus);
    }


}
