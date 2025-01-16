package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Vehicletype;
import com.heavenscode.rac.repository.VehicletypeRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Vehicletype}.
 */
@RestController
@RequestMapping("/api/vehicletypes")
@Transactional
public class VehicletypeResource {

    private final Logger log = LoggerFactory.getLogger(VehicletypeResource.class);

    private static final String ENTITY_NAME = "vehicletype";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VehicletypeRepository vehicletypeRepository;

    public VehicletypeResource(VehicletypeRepository vehicletypeRepository) {
        this.vehicletypeRepository = vehicletypeRepository;
    }

    /**
     * {@code POST  /vehicletypes} : Create a new vehicletype.
     *
     * @param vehicletype the vehicletype to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vehicletype, or with status {@code 400 (Bad Request)} if the vehicletype has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Vehicletype> createVehicletype(@RequestBody Vehicletype vehicletype) throws URISyntaxException {
        log.debug("REST request to save Vehicletype : {}", vehicletype);
        if (vehicletype.getId() != null) {
            throw new BadRequestAlertException("A new vehicletype cannot already have an ID", ENTITY_NAME, "idexists");
        }
        vehicletype = vehicletypeRepository.save(vehicletype);
        return ResponseEntity.created(new URI("/api/vehicletypes/" + vehicletype.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, vehicletype.getId().toString()))
            .body(vehicletype);
    }

    /**
     * {@code PUT  /vehicletypes/:id} : Updates an existing vehicletype.
     *
     * @param id the id of the vehicletype to save.
     * @param vehicletype the vehicletype to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehicletype,
     * or with status {@code 400 (Bad Request)} if the vehicletype is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vehicletype couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Vehicletype> updateVehicletype(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehicletype vehicletype
    ) throws URISyntaxException {
        log.debug("REST request to update Vehicletype : {}, {}", id, vehicletype);
        if (vehicletype.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicletype.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicletypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        vehicletype = vehicletypeRepository.save(vehicletype);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehicletype.getId().toString()))
            .body(vehicletype);
    }

    /**
     * {@code PATCH  /vehicletypes/:id} : Partial updates given fields of an existing vehicletype, field will ignore if it is null
     *
     * @param id the id of the vehicletype to save.
     * @param vehicletype the vehicletype to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vehicletype,
     * or with status {@code 400 (Bad Request)} if the vehicletype is not valid,
     * or with status {@code 404 (Not Found)} if the vehicletype is not found,
     * or with status {@code 500 (Internal Server Error)} if the vehicletype couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Vehicletype> partialUpdateVehicletype(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Vehicletype vehicletype
    ) throws URISyntaxException {
        log.debug("REST request to partial update Vehicletype partially : {}, {}", id, vehicletype);
        if (vehicletype.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, vehicletype.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!vehicletypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Vehicletype> result = vehicletypeRepository
            .findById(vehicletype.getId())
            .map(existingVehicletype -> {
                if (vehicletype.getVehicletype() != null) {
                    existingVehicletype.setVehicletype(vehicletype.getVehicletype());
                }
                if (vehicletype.getVehicletypediscription() != null) {
                    existingVehicletype.setVehicletypediscription(vehicletype.getVehicletypediscription());
                }
                if (vehicletype.getLmu() != null) {
                    existingVehicletype.setLmu(vehicletype.getLmu());
                }
                if (vehicletype.getLmd() != null) {
                    existingVehicletype.setLmd(vehicletype.getLmd());
                }
                if (vehicletype.getCatid() != null) {
                    existingVehicletype.setCatid(vehicletype.getCatid());
                }
                if (vehicletype.getCatname() != null) {
                    existingVehicletype.setCatname(vehicletype.getCatname());
                }

                return existingVehicletype;
            })
            .map(vehicletypeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vehicletype.getId().toString())
        );
    }

    /**
     * {@code GET  /vehicletypes} : get all the vehicletypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vehicletypes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Vehicletype>> getAllVehicletypes(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Vehicletypes");
        Page<Vehicletype> page = vehicletypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vehicletypes/:id} : get the "id" vehicletype.
     *
     * @param id the id of the vehicletype to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vehicletype, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Vehicletype> getVehicletype(@PathVariable("id") Long id) {
        log.debug("REST request to get Vehicletype : {}", id);
        Optional<Vehicletype> vehicletype = vehicletypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vehicletype);
    }

    /**
     * {@code DELETE  /vehicletypes/:id} : delete the "id" vehicletype.
     *
     * @param id the id of the vehicletype to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicletype(@PathVariable("id") Long id) {
        log.debug("REST request to delete Vehicletype : {}", id);
        vehicletypeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
