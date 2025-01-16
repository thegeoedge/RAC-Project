package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocareappointment;
import com.heavenscode.rac.repository.AutocareappointmentRepository;
import com.heavenscode.rac.service.AutocareappointmentQueryService;
import com.heavenscode.rac.service.AutocareappointmentService;
import com.heavenscode.rac.service.criteria.AutocareappointmentCriteria;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocareappointment}.
 */
@RestController
@RequestMapping("/api/autocareappointments")
public class AutocareappointmentResource {

    private static final Logger LOG = LoggerFactory.getLogger(AutocareappointmentResource.class);

    private static final String ENTITY_NAME = "autocareappointment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocareappointmentService autocareappointmentService;

    private final AutocareappointmentRepository autocareappointmentRepository;

    private final AutocareappointmentQueryService autocareappointmentQueryService;

    public AutocareappointmentResource(
        AutocareappointmentService autocareappointmentService,
        AutocareappointmentRepository autocareappointmentRepository,
        AutocareappointmentQueryService autocareappointmentQueryService
    ) {
        this.autocareappointmentService = autocareappointmentService;
        this.autocareappointmentRepository = autocareappointmentRepository;
        this.autocareappointmentQueryService = autocareappointmentQueryService;
    }

    /**
     * {@code POST  /autocareappointments} : Create a new autocareappointment.
     *
     * @param autocareappointment the autocareappointment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocareappointment, or with status {@code 400 (Bad Request)} if the autocareappointment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocareappointment> createAutocareappointment(@RequestBody Autocareappointment autocareappointment)
        throws URISyntaxException {
        LOG.debug("REST request to save Autocareappointment : {}", autocareappointment);
        if (autocareappointment.getId() != null) {
            throw new BadRequestAlertException("A new autocareappointment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocareappointment = autocareappointmentService.save(autocareappointment);
        return ResponseEntity.created(new URI("/api/autocareappointments/" + autocareappointment.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocareappointment.getId().toString()))
            .body(autocareappointment);
    }

    /**
     * {@code PUT  /autocareappointments/:id} : Updates an existing autocareappointment.
     *
     * @param id the id of the autocareappointment to save.
     * @param autocareappointment the autocareappointment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocareappointment,
     * or with status {@code 400 (Bad Request)} if the autocareappointment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocareappointment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocareappointment> updateAutocareappointment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocareappointment autocareappointment
    ) throws URISyntaxException {
        LOG.debug("REST request to update Autocareappointment : {}, {}", id, autocareappointment);
        if (autocareappointment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocareappointment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocareappointmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocareappointment = autocareappointmentService.update(autocareappointment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocareappointment.getId().toString()))
            .body(autocareappointment);
    }

    /**
     * {@code PATCH  /autocareappointments/:id} : Partial updates given fields of an existing autocareappointment, field will ignore if it is null
     *
     * @param id the id of the autocareappointment to save.
     * @param autocareappointment the autocareappointment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocareappointment,
     * or with status {@code 400 (Bad Request)} if the autocareappointment is not valid,
     * or with status {@code 404 (Not Found)} if the autocareappointment is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocareappointment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocareappointment> partialUpdateAutocareappointment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocareappointment autocareappointment
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Autocareappointment partially : {}, {}", id, autocareappointment);
        if (autocareappointment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocareappointment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocareappointmentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocareappointment> result = autocareappointmentService.partialUpdate(autocareappointment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocareappointment.getId().toString())
        );
    }

    /**
     * {@code GET  /autocareappointments} : get all the autocareappointments.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocareappointments in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocareappointment>> getAllAutocareappointments(
        AutocareappointmentCriteria criteria,
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        LOG.debug("REST request to get Autocareappointments by criteria: {}", criteria);

        Page<Autocareappointment> page = autocareappointmentQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocareappointments/count} : count all the autocareappointments.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countAutocareappointments(AutocareappointmentCriteria criteria) {
        LOG.debug("REST request to count Autocareappointments by criteria: {}", criteria);
        return ResponseEntity.ok().body(autocareappointmentQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /autocareappointments/:id} : get the "id" autocareappointment.
     *
     * @param id the id of the autocareappointment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocareappointment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocareappointment> getAutocareappointment(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Autocareappointment : {}", id);
        Optional<Autocareappointment> autocareappointment = autocareappointmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(autocareappointment);
    }

    /**
     * {@code DELETE  /autocareappointments/:id} : delete the "id" autocareappointment.
     *
     * @param id the id of the autocareappointment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocareappointment(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Autocareappointment : {}", id);
        autocareappointmentService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
