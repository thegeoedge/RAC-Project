package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Workshopvehiclework;
import com.heavenscode.rac.repository.WorkshopvehicleworkRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Workshopvehiclework}.
 */
@RestController
@RequestMapping("/api/workshopvehicleworks")
@Transactional
public class WorkshopvehicleworkResource {

    private final Logger log = LoggerFactory.getLogger(WorkshopvehicleworkResource.class);

    private static final String ENTITY_NAME = "workshopvehiclework";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkshopvehicleworkRepository workshopvehicleworkRepository;

    public WorkshopvehicleworkResource(WorkshopvehicleworkRepository workshopvehicleworkRepository) {
        this.workshopvehicleworkRepository = workshopvehicleworkRepository;
    }

    /**
     * {@code POST  /workshopvehicleworks} : Create a new workshopvehiclework.
     *
     * @param workshopvehiclework the workshopvehiclework to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workshopvehiclework, or with status {@code 400 (Bad Request)} if the workshopvehiclework has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Workshopvehiclework> createWorkshopvehiclework(@RequestBody Workshopvehiclework workshopvehiclework)
        throws URISyntaxException {
        log.debug("REST request to save Workshopvehiclework : {}", workshopvehiclework);
        if (workshopvehiclework.getId() != null) {
            throw new BadRequestAlertException("A new workshopvehiclework cannot already have an ID", ENTITY_NAME, "idexists");
        }
        workshopvehiclework = workshopvehicleworkRepository.save(workshopvehiclework);
        return ResponseEntity.created(new URI("/api/workshopvehicleworks/" + workshopvehiclework.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, workshopvehiclework.getId().toString()))
            .body(workshopvehiclework);
    }

    /**
     * {@code PUT  /workshopvehicleworks/:id} : Updates an existing workshopvehiclework.
     *
     * @param id the id of the workshopvehiclework to save.
     * @param workshopvehiclework the workshopvehiclework to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workshopvehiclework,
     * or with status {@code 400 (Bad Request)} if the workshopvehiclework is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workshopvehiclework couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Workshopvehiclework> updateWorkshopvehiclework(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Workshopvehiclework workshopvehiclework
    ) throws URISyntaxException {
        log.debug("REST request to update Workshopvehiclework : {}, {}", id, workshopvehiclework);
        if (workshopvehiclework.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workshopvehiclework.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workshopvehicleworkRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        workshopvehiclework = workshopvehicleworkRepository.save(workshopvehiclework);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workshopvehiclework.getId().toString()))
            .body(workshopvehiclework);
    }

    /**
     * {@code PATCH  /workshopvehicleworks/:id} : Partial updates given fields of an existing workshopvehiclework, field will ignore if it is null
     *
     * @param id the id of the workshopvehiclework to save.
     * @param workshopvehiclework the workshopvehiclework to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workshopvehiclework,
     * or with status {@code 400 (Bad Request)} if the workshopvehiclework is not valid,
     * or with status {@code 404 (Not Found)} if the workshopvehiclework is not found,
     * or with status {@code 500 (Internal Server Error)} if the workshopvehiclework couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Workshopvehiclework> partialUpdateWorkshopvehiclework(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Workshopvehiclework workshopvehiclework
    ) throws URISyntaxException {
        log.debug("REST request to partial update Workshopvehiclework partially : {}, {}", id, workshopvehiclework);
        if (workshopvehiclework.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workshopvehiclework.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workshopvehicleworkRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Workshopvehiclework> result = workshopvehicleworkRepository
            .findById(workshopvehiclework.getId())
            .map(existingWorkshopvehiclework -> {
                if (workshopvehiclework.getJobid() != null) {
                    existingWorkshopvehiclework.setJobid(workshopvehiclework.getJobid());
                }
                if (workshopvehiclework.getVehicleid() != null) {
                    existingWorkshopvehiclework.setVehicleid(workshopvehiclework.getVehicleid());
                }
                if (workshopvehiclework.getCustomerid() != null) {
                    existingWorkshopvehiclework.setCustomerid(workshopvehiclework.getCustomerid());
                }
                if (workshopvehiclework.getCustomername() != null) {
                    existingWorkshopvehiclework.setCustomername(workshopvehiclework.getCustomername());
                }
                if (workshopvehiclework.getContactno() != null) {
                    existingWorkshopvehiclework.setContactno(workshopvehiclework.getContactno());
                }
                if (workshopvehiclework.getVehicleno() != null) {
                    existingWorkshopvehiclework.setVehicleno(workshopvehiclework.getVehicleno());
                }
                if (workshopvehiclework.getVehiclebrand() != null) {
                    existingWorkshopvehiclework.setVehiclebrand(workshopvehiclework.getVehiclebrand());
                }
                if (workshopvehiclework.getVehiclemodel() != null) {
                    existingWorkshopvehiclework.setVehiclemodel(workshopvehiclework.getVehiclemodel());
                }
                if (workshopvehiclework.getMileage() != null) {
                    existingWorkshopvehiclework.setMileage(workshopvehiclework.getMileage());
                }
                if (workshopvehiclework.getAddeddate() != null) {
                    existingWorkshopvehiclework.setAddeddate(workshopvehiclework.getAddeddate());
                }
                if (workshopvehiclework.getIscalltocustomer() != null) {
                    existingWorkshopvehiclework.setIscalltocustomer(workshopvehiclework.getIscalltocustomer());
                }
                if (workshopvehiclework.getRemarks() != null) {
                    existingWorkshopvehiclework.setRemarks(workshopvehiclework.getRemarks());
                }
                if (workshopvehiclework.getCalldate() != null) {
                    existingWorkshopvehiclework.setCalldate(workshopvehiclework.getCalldate());
                }
                if (workshopvehiclework.getLmu() != null) {
                    existingWorkshopvehiclework.setLmu(workshopvehiclework.getLmu());
                }
                if (workshopvehiclework.getLmd() != null) {
                    existingWorkshopvehiclework.setLmd(workshopvehiclework.getLmd());
                }

                return existingWorkshopvehiclework;
            })
            .map(workshopvehicleworkRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workshopvehiclework.getId().toString())
        );
    }

    /**
     * {@code GET  /workshopvehicleworks} : get all the workshopvehicleworks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workshopvehicleworks in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Workshopvehiclework>> getAllWorkshopvehicleworks(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Workshopvehicleworks");
        Page<Workshopvehiclework> page = workshopvehicleworkRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workshopvehicleworks/:id} : get the "id" workshopvehiclework.
     *
     * @param id the id of the workshopvehiclework to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workshopvehiclework, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Workshopvehiclework> getWorkshopvehiclework(@PathVariable("id") Long id) {
        log.debug("REST request to get Workshopvehiclework : {}", id);
        Optional<Workshopvehiclework> workshopvehiclework = workshopvehicleworkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workshopvehiclework);
    }

    /**
     * {@code DELETE  /workshopvehicleworks/:id} : delete the "id" workshopvehiclework.
     *
     * @param id the id of the workshopvehiclework to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshopvehiclework(@PathVariable("id") Long id) {
        log.debug("REST request to delete Workshopvehiclework : {}", id);
        workshopvehicleworkRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
