package com.reforgan.reforgan.resource;

import com.reforgan.reforgan.domain.RefOrgan;
import com.reforgan.reforgan.model.User;
import com.reforgan.reforgan.model.UserClient;
import com.reforgan.reforgan.resource.errors.BadRequestAlertException;
import com.reforgan.reforgan.service.RefOrganService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/ref-organs")
public class RefOrganResource {

    private final Logger log = LoggerFactory.getLogger(RefOrganResource.class);

    private static final String ENTITY_NAME = "refOrgan";

    private String applicationName;

    private final RefOrganService refOrganService;

    @Autowired
    private UserClient userClient;

    public RefOrganResource(UserClient userClient,RefOrganService refOrganService) {
        this.refOrganService = refOrganService;
        this.userClient = userClient;
    }

    public static boolean userHasAuthority(String authority) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            if (authority.equals(grantedAuthority.getAuthority())) {
                return true;
            }
        }

        return false;
    }

    public Optional<User> getUserProfil() {
        String idUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<User> user = userClient.findUserById(idUser);
        System.out.println(user);
        return user;
    }

    @PostMapping("/ref-organs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RefOrgan> createRefOrgan(@RequestBody RefOrgan refOrgan) throws URISyntaxException {
        log.info("REST request to save RefOrgan : {}", refOrgan);
        if (refOrgan.getId() != null) {
            throw new BadRequestAlertException("A new refOrgan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RefOrgan result = refOrganService.createRefOrgan(refOrgan);
        return ResponseEntity.created(new URI("/api/ref-organs/ref-organs" + result.getId()))
                .body(result);
    }


    @PutMapping("/ref-organs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RefOrgan> updateRefOrgan(@RequestBody RefOrgan refOrgan) throws URISyntaxException {
        log.info("REST request to update RefOrgan : {}", refOrgan);
        if (refOrgan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RefOrgan result = refOrganService.updateRefOrgan(refOrgan);
        return ResponseEntity.ok()
                .body(result);
    }

    @GetMapping("/ref-organs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RefOrgan> getAllRefOrgans() {
        log.info("REST request to get all RefOrgans");
        return refOrganService.getAllRefOrgans();

    }

    @GetMapping("/ref-organs/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<RefOrgan> getRefOrgan(@PathVariable String id) {
        log.info("REST request to get RefOrgan : {}", id);
         Optional<RefOrgan> refOrgan = refOrganService.getRefOrgan(id);
        return new ResponseEntity<RefOrgan>(refOrgan.get(), HttpStatus.FOUND);
    }

    @GetMapping("/ref-organ/p_refOrgan/{prefOrgan}")
    @PreAuthorize("hasAuthority('USER')")
    public List<RefOrgan> getRefOrganByPrefOrgan(@PathVariable String prefOrgan) {
        log.info("REST request to get RefOrgan by prefOrgan: {}", prefOrgan);
        System.out.println("REST request to get RefOrgan by prefOrgan: {}"+ prefOrgan);
        return refOrganService.getRefOrganByPrefOrgan(prefOrgan);
    }

    @GetMapping("ref-organs/refVehicule/{pRef}")
    @PreAuthorize("hasAuthority('USER')")
    public List<RefOrgan> getRefOrganByPrefOrgan(@PathVariable List prefOrgans) {
        log.info("REST request to get RefOrgan by prefOrgan: {}", prefOrgans);
        return refOrganService.findAllByRefOrgansByPreOrgan(prefOrgans);
    }

    @DeleteMapping("/ref-organs/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteRefOrgan(@PathVariable String id) {
        log.info("REST request to delete RefOrgan : {}", id);
        refOrganService.deleteRefOrgan(id);
        return new ResponseEntity<Void>(HttpStatus.GONE);
    }
}
