package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Vehiclebrandname;
import com.heavenscode.rac.repository.VehiclebrandnameRepository;
import com.heavenscode.rac.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.heavenscode.rac.domain.Vehiclebrandname}.
 */
@RestController
@RequestMapping("/api/vehiclebrandnames")
@Transactional
public class VehiclebrandnameResource {

    private final Logger log = LoggerFactory.getLogger(VehiclebrandnameResource.class);

    private static final String ENTITY_NAME = "vehiclebrandname";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VehiclebrandnameRepository vehiclebrandnameRepository;

    public VehiclebrandnameResource(VehiclebrandnameRepository vehiclebrandnameRepository) {
        this.vehiclebrandnameRepository = vehiclebrandnameRepository;
    }

    /**
     * {@code POST  /vehiclebrandnames} : Create a new vehiclebrandname.
     *
     * @param vehiclebrandname the vehiclebrandname to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehiclebrandname, or with status {@code 400 (Bad Request)} if the vehiclebrandname has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Vehiclebrandname> createVehiclebrandname(@RequestBody Vehiclebrandname vehiclebrandname)
        throws URISyntaxException {
        log.debug("REST request to save Vehiclebrandname : {}", vehiclebrandname);
        if (vehiclebrandname.getId() != null) {
            throw new BadRequestAlertException("A new vehiclebrandname cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vehiclebrandname = vehiclebrandnameRepository.save(vehiclebrandname);
        return ResponseEntity.created(new URI("/api/vehiclebrandnames/" + vehiclebrandname.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, vehiclebrandname.getId().toString()))
            .body(vehiclebrandname);
    }

    /**
     * {@code PUT  /vehiclebrandnames/:id} : Updates an existing vehiclebrandname.
     *
     * @param id the id of the vehiclebrandname to save.
     * @param vehiclebrandname the vehiclebrandname to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiclebrandname,
     * or with status {@code 400 (Bad Request)} if the vehiclebrandname is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehiclebrandname couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehiclebrandname> updateVehiclebrandname(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehiclebrandname vehiclebrandname
    ) throws URISyntaxException {
        log.debug("REST request to update Vehiclebrandname : {}, {}", id, vehiclebrandname);
        if (vehiclebrandname.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiclebrandname.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiclebrandnameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        vehiclebrandname = vehiclebrandnameRepository.save(vehiclebrandname);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehiclebrandname.getId().toString()))
            .body(vehiclebrandname);
    }

    /**
     * {@code PATCH  /vehiclebrandnames/:id} : Partial updates given fields of an existing vehiclebrandname, field will ignore if it is null
     *
     * @param id the id of the vehiclebrandname to save.
     * @param vehiclebrandname the vehiclebrandname to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiclebrandname,
     * or with status {@code 400 (Bad Request)} if the vehiclebrandname is not valid,
     * or with status {@code 404 (Not Found)} if the vehiclebrandname is not found,
     * or with status {@code 500 (Internal Server Error)} if the vehiclebrandname couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Vehiclebrandname> partialUpdateVehiclebrandname(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehiclebrandname vehiclebrandname
    ) throws URISyntaxException {
        log.debug("REST request to partial update Vehiclebrandname partially : {}, {}", id, vehiclebrandname);
        if (vehiclebrandname.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiclebrandname.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiclebrandnameRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Vehiclebrandname> result = vehiclebrandnameRepository
            .findById(vehiclebrandname.getId())
            .map(existingVehiclebrandname -> {
                if (vehiclebrandname.getBrandname() != null) {
                    existingVehiclebrandname.setBrandname(vehiclebrandname.getBrandname());
                }
                if (vehiclebrandname.getDescription() != null) {
                    existingVehiclebrandname.setDescription(vehiclebrandname.getDescription());
                }
                if (vehiclebrandname.getLmu() != null) {
                    existingVehiclebrandname.setLmu(vehiclebrandname.getLmu());
                }
                if (vehiclebrandname.getLmd() != null) {
                    existingVehiclebrandname.setLmd(vehiclebrandname.getLmd());
                }
                if (vehiclebrandname.getCompanyid() != null) {
                    existingVehiclebrandname.setCompanyid(vehiclebrandname.getCompanyid());
                }

                return existingVehiclebrandname;
            })
            .map(vehiclebrandnameRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehiclebrandname.getId().toString())
        );
    }

    /**
     * {@code GET  /vehiclebrandnames} : get all the vehiclebrandnames.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehiclebrandnames in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Vehiclebrandname>> getAllVehiclebrandnames(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Vehiclebrandnames");
        Page<Vehiclebrandname> page = vehiclebrandnameRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vehiclebrandnames/:id} : get the "id" vehiclebrandname.
     *
     * @param id the id of the vehiclebrandname to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehiclebrandname, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehiclebrandname> getVehiclebrandname(@PathVariable("id") Long id) {
        log.debug("REST request to get Vehiclebrandname : {}", id);
        Optional<Vehiclebrandname> vehiclebrandname = vehiclebrandnameRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vehiclebrandname);
    }

    /**
     * {@code DELETE  /vehiclebrandnames/:id} : delete the "id" vehiclebrandname.
     *
     * @param id the id of the vehiclebrandname to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiclebrandname(@PathVariable("id") Long id) {
        log.debug("REST request to delete Vehiclebrandname : {}", id);
        vehiclebrandnameRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
