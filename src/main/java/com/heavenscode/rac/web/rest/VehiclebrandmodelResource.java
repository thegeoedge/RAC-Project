package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Vehiclebrandmodel;
import com.heavenscode.rac.repository.VehiclebrandmodelRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Vehiclebrandmodel}.
 */
@RestController
@RequestMapping("/api/vehiclebrandmodels")
@Transactional
public class VehiclebrandmodelResource {

    private final Logger log = LoggerFactory.getLogger(VehiclebrandmodelResource.class);

    private static final String ENTITY_NAME = "vehiclebrandmodel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VehiclebrandmodelRepository vehiclebrandmodelRepository;

    public VehiclebrandmodelResource(VehiclebrandmodelRepository vehiclebrandmodelRepository) {
        this.vehiclebrandmodelRepository = vehiclebrandmodelRepository;
    }

    /**
     * {@code POST  /vehiclebrandmodels} : Create a new vehiclebrandmodel.
     *
     * @param vehiclebrandmodel the vehiclebrandmodel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehiclebrandmodel, or with status {@code 400 (Bad Request)} if the vehiclebrandmodel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Vehiclebrandmodel> createVehiclebrandmodel(@RequestBody Vehiclebrandmodel vehiclebrandmodel)
        throws URISyntaxException {
        log.debug("REST request to save Vehiclebrandmodel : {}", vehiclebrandmodel);
        if (vehiclebrandmodel.getId() != null) {
            throw new BadRequestAlertException("A new vehiclebrandmodel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vehiclebrandmodel = vehiclebrandmodelRepository.save(vehiclebrandmodel);
        return ResponseEntity.created(new URI("/api/vehiclebrandmodels/" + vehiclebrandmodel.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, vehiclebrandmodel.getId().toString()))
            .body(vehiclebrandmodel);
    }

    /**
     * {@code PUT  /vehiclebrandmodels/:id} : Updates an existing vehiclebrandmodel.
     *
     * @param id the id of the vehiclebrandmodel to save.
     * @param vehiclebrandmodel the vehiclebrandmodel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiclebrandmodel,
     * or with status {@code 400 (Bad Request)} if the vehiclebrandmodel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehiclebrandmodel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehiclebrandmodel> updateVehiclebrandmodel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehiclebrandmodel vehiclebrandmodel
    ) throws URISyntaxException {
        log.debug("REST request to update Vehiclebrandmodel : {}, {}", id, vehiclebrandmodel);
        if (vehiclebrandmodel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiclebrandmodel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiclebrandmodelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        vehiclebrandmodel = vehiclebrandmodelRepository.save(vehiclebrandmodel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehiclebrandmodel.getId().toString()))
            .body(vehiclebrandmodel);
    }

    /**
     * {@code PATCH  /vehiclebrandmodels/:id} : Partial updates given fields of an existing vehiclebrandmodel, field will ignore if it is null
     *
     * @param id the id of the vehiclebrandmodel to save.
     * @param vehiclebrandmodel the vehiclebrandmodel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehiclebrandmodel,
     * or with status {@code 400 (Bad Request)} if the vehiclebrandmodel is not valid,
     * or with status {@code 404 (Not Found)} if the vehiclebrandmodel is not found,
     * or with status {@code 500 (Internal Server Error)} if the vehiclebrandmodel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Vehiclebrandmodel> partialUpdateVehiclebrandmodel(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehiclebrandmodel vehiclebrandmodel
    ) throws URISyntaxException {
        log.debug("REST request to partial update Vehiclebrandmodel partially : {}, {}", id, vehiclebrandmodel);
        if (vehiclebrandmodel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehiclebrandmodel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehiclebrandmodelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Vehiclebrandmodel> result = vehiclebrandmodelRepository
            .findById(vehiclebrandmodel.getId())
            .map(existingVehiclebrandmodel -> {
                if (vehiclebrandmodel.getBrandid() != null) {
                    existingVehiclebrandmodel.setBrandid(vehiclebrandmodel.getBrandid());
                }
                if (vehiclebrandmodel.getBrandname() != null) {
                    existingVehiclebrandmodel.setBrandname(vehiclebrandmodel.getBrandname());
                }
                if (vehiclebrandmodel.getModel() != null) {
                    existingVehiclebrandmodel.setModel(vehiclebrandmodel.getModel());
                }
                if (vehiclebrandmodel.getLmu() != null) {
                    existingVehiclebrandmodel.setLmu(vehiclebrandmodel.getLmu());
                }
                if (vehiclebrandmodel.getLmd() != null) {
                    existingVehiclebrandmodel.setLmd(vehiclebrandmodel.getLmd());
                }

                return existingVehiclebrandmodel;
            })
            .map(vehiclebrandmodelRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehiclebrandmodel.getId().toString())
        );
    }

    /**
     * {@code GET  /vehiclebrandmodels} : get all the vehiclebrandmodels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehiclebrandmodels in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Vehiclebrandmodel>> getAllVehiclebrandmodels(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Vehiclebrandmodels");
        Page<Vehiclebrandmodel> page = vehiclebrandmodelRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vehiclebrandmodels/:id} : get the "id" vehiclebrandmodel.
     *
     * @param id the id of the vehiclebrandmodel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehiclebrandmodel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehiclebrandmodel> getVehiclebrandmodel(@PathVariable("id") Long id) {
        log.debug("REST request to get Vehiclebrandmodel : {}", id);
        Optional<Vehiclebrandmodel> vehiclebrandmodel = vehiclebrandmodelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vehiclebrandmodel);
    }

    /**
     * {@code DELETE  /vehiclebrandmodels/:id} : delete the "id" vehiclebrandmodel.
     *
     * @param id the id of the vehiclebrandmodel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehiclebrandmodel(@PathVariable("id") Long id) {
        log.debug("REST request to delete Vehiclebrandmodel : {}", id);
        vehiclebrandmodelRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
