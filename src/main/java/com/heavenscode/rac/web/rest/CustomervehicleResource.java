package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Customervehicle;
import com.heavenscode.rac.repository.CustomervehicleRepository;
import com.heavenscode.rac.service.CustomervehicleQueryService;
import com.heavenscode.rac.service.CustomervehicleService;
import com.heavenscode.rac.service.criteria.CustomervehicleCriteria;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.heavenscode.rac.domain.Customervehicle}.
 */
@RestController
@RequestMapping("/api/customervehicles")
public class CustomervehicleResource {

    private static final Logger LOG = LoggerFactory.getLogger(CustomervehicleResource.class);

    private static final String ENTITY_NAME = "customervehicle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomervehicleService customervehicleService;

    private final CustomervehicleRepository customervehicleRepository;

    private final CustomervehicleQueryService customervehicleQueryService;

    public CustomervehicleResource(
        CustomervehicleService customervehicleService,
        CustomervehicleRepository customervehicleRepository,
        CustomervehicleQueryService customervehicleQueryService
    ) {
        this.customervehicleService = customervehicleService;
        this.customervehicleRepository = customervehicleRepository;
        this.customervehicleQueryService = customervehicleQueryService;
    }

    /**
     * {@code POST  /customervehicles} : Create a new customervehicle.
     *
     * @param customervehicle the customervehicle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customervehicle, or with status {@code 400 (Bad Request)} if the customervehicle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Customervehicle> createCustomervehicle(@RequestBody Customervehicle customervehicle) throws URISyntaxException {
        LOG.debug("REST request to save Customervehicle : {}", customervehicle);
        if (customervehicle.getId() != null) {
            throw new BadRequestAlertException("A new customervehicle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customervehicle = customervehicleService.save(customervehicle);
        return ResponseEntity.created(new URI("/api/customervehicles/" + customervehicle.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, customervehicle.getId().toString()))
            .body(customervehicle);
    }

    /**
     * {@code PUT  /customervehicles/:id} : Updates an existing customervehicle.
     *
     * @param id the id of the customervehicle to save.
     * @param customervehicle the customervehicle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customervehicle,
     * or with status {@code 400 (Bad Request)} if the customervehicle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customervehicle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Customervehicle> updateCustomervehicle(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Customervehicle customervehicle
    ) throws URISyntaxException {
        LOG.debug("REST request to update Customervehicle : {}, {}", id, customervehicle);
        if (customervehicle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customervehicle.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customervehicleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customervehicle = customervehicleService.update(customervehicle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customervehicle.getId().toString()))
            .body(customervehicle);
    }

    /**
     * {@code PATCH  /customervehicles/:id} : Partial updates given fields of an existing customervehicle, field will ignore if it is null
     *
     * @param id the id of the customervehicle to save.
     * @param customervehicle the customervehicle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customervehicle,
     * or with status {@code 400 (Bad Request)} if the customervehicle is not valid,
     * or with status {@code 404 (Not Found)} if the customervehicle is not found,
     * or with status {@code 500 (Internal Server Error)} if the customervehicle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Customervehicle> partialUpdateCustomervehicle(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Customervehicle customervehicle
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Customervehicle partially : {}, {}", id, customervehicle);
        if (customervehicle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customervehicle.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customervehicleRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Customervehicle> result = customervehicleService.partialUpdate(customervehicle);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, customervehicle.getId().toString())
        );
    }

    /**
     * {@code GET  /customervehicles} : get all the customervehicles.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customervehicles in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Customervehicle>> getAllCustomervehicles(
        CustomervehicleCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get Customervehicles by criteria: {}", criteria);

        Page<Customervehicle> page = customervehicleQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /customervehicles/count} : count all the customervehicles.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countCustomervehicles(CustomervehicleCriteria criteria) {
        LOG.debug("REST request to count Customervehicles by criteria: {}", criteria);
        return ResponseEntity.ok().body(customervehicleQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /customervehicles/:id} : get the "id" customervehicle.
     *
     * @param id the id of the customervehicle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customervehicle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customervehicle> getCustomervehicle(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Customervehicle : {}", id);
        Optional<Customervehicle> customervehicle = customervehicleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customervehicle);
    }

    /**
     * {@code DELETE  /customervehicles/:id} : delete the "id" customervehicle.
     *
     * @param id the id of the customervehicle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomervehicle(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Customervehicle : {}", id);
        customervehicleService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
