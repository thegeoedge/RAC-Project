package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Vehiclecategory;
import com.heavenscode.rac.repository.VehiclecategoryRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Vehiclecategory}.
 */
@RestController
@RequestMapping("/api/vehiclecategories")
@Transactional
public class VehiclecategoryResource {

    private final Logger log = LoggerFactory.getLogger(VehiclecategoryResource.class);

    private static final String ENTITY_NAME = "vehiclecategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VehiclecategoryRepository vehiclecategoryRepository;

    public VehiclecategoryResource(VehiclecategoryRepository vehiclecategoryRepository) {
        this.vehiclecategoryRepository = vehiclecategoryRepository;
    }

    /**
     * {@code POST  /vehiclecategories} : Create a new vehiclecategory.
     *
     * @param vehiclecategory the vehiclecategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehiclecategory, or with status {@code 400 (Bad Request)} if the vehiclecategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Vehiclecategory> createVehiclecategory(@RequestBody Vehiclecategory vehiclecategory) throws URISyntaxException {
        log.debug("REST request to save Vehiclecategory : {}", vehiclecategory);
        if (vehiclecategory.getId() != null) {
            throw new BadRequestAlertException("A new vehiclecategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vehiclecategory = vehiclecategoryRepository.save(vehiclecategory);
        return ResponseEntity.created(new URI("/api/vehiclecategories/" + vehiclecategory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, vehiclecategory.getId().toString()))
            .body(vehiclecategory);
    }

    /**
     * {@code PUT  /vehiclecategories/:id} : Updates an existing vehiclecategory.
     *
     * @param id the id of the vehiclecategory to save.
     * @param vehiclecategory the vehiclecategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiclecategory,
     * or with status {@code 400 (Bad Request)} if the vehiclecategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehiclecategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehiclecategory> updateVehiclecategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehiclecategory vehiclecategory
    ) throws URISyntaxException {
        log.debug("REST request to update Vehiclecategory : {}, {}", id, vehiclecategory);
        if (vehiclecategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiclecategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiclecategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        vehiclecategory = vehiclecategoryRepository.save(vehiclecategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehiclecategory.getId().toString()))
            .body(vehiclecategory);
    }

    /**
     * {@code PATCH  /vehiclecategories/:id} : Partial updates given fields of an existing vehiclecategory, field will ignore if it is null
     *
     * @param id the id of the vehiclecategory to save.
     * @param vehiclecategory the vehiclecategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiclecategory,
     * or with status {@code 400 (Bad Request)} if the vehiclecategory is not valid,
     * or with status {@code 404 (Not Found)} if the vehiclecategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the vehiclecategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Vehiclecategory> partialUpdateVehiclecategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehiclecategory vehiclecategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update Vehiclecategory partially : {}, {}", id, vehiclecategory);
        if (vehiclecategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiclecategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiclecategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Vehiclecategory> result = vehiclecategoryRepository
            .findById(vehiclecategory.getId())
            .map(existingVehiclecategory -> {
                if (vehiclecategory.getVehiclecategory() != null) {
                    existingVehiclecategory.setVehiclecategory(vehiclecategory.getVehiclecategory());
                }
                if (vehiclecategory.getVehiclecategorydiscription() != null) {
                    existingVehiclecategory.setVehiclecategorydiscription(vehiclecategory.getVehiclecategorydiscription());
                }
                if (vehiclecategory.getLmu() != null) {
                    existingVehiclecategory.setLmu(vehiclecategory.getLmu());
                }
                if (vehiclecategory.getLmd() != null) {
                    existingVehiclecategory.setLmd(vehiclecategory.getLmd());
                }

                return existingVehiclecategory;
            })
            .map(vehiclecategoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehiclecategory.getId().toString())
        );
    }

    /**
     * {@code GET  /vehiclecategories} : get all the vehiclecategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehiclecategories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Vehiclecategory>> getAllVehiclecategories(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Vehiclecategories");
        Page<Vehiclecategory> page = vehiclecategoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vehiclecategories/:id} : get the "id" vehiclecategory.
     *
     * @param id the id of the vehiclecategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehiclecategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehiclecategory> getVehiclecategory(@PathVariable("id") Long id) {
        log.debug("REST request to get Vehiclecategory : {}", id);
        Optional<Vehiclecategory> vehiclecategory = vehiclecategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vehiclecategory);
    }

    /**
     * {@code DELETE  /vehiclecategories/:id} : delete the "id" vehiclecategory.
     *
     * @param id the id of the vehiclecategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiclecategory(@PathVariable("id") Long id) {
        log.debug("REST request to delete Vehiclecategory : {}", id);
        vehiclecategoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
