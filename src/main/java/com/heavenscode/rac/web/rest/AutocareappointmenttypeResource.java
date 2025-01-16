package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocareappointmenttype;
import com.heavenscode.rac.repository.AutocareappointmenttypeRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocareappointmenttype}.
 */
@RestController
@RequestMapping("/api/autocareappointmenttypes")
@Transactional
public class AutocareappointmenttypeResource {

    private final Logger log = LoggerFactory.getLogger(AutocareappointmenttypeResource.class);

    private static final String ENTITY_NAME = "autocareappointmenttype";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocareappointmenttypeRepository autocareappointmenttypeRepository;

    public AutocareappointmenttypeResource(AutocareappointmenttypeRepository autocareappointmenttypeRepository) {
        this.autocareappointmenttypeRepository = autocareappointmenttypeRepository;
    }

    /**
     * {@code POST  /autocareappointmenttypes} : Create a new autocareappointmenttype.
     *
     * @param autocareappointmenttype the autocareappointmenttype to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocareappointmenttype, or with status {@code 400 (Bad Request)} if the autocareappointmenttype has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocareappointmenttype> createAutocareappointmenttype(
        @RequestBody Autocareappointmenttype autocareappointmenttype
    ) throws URISyntaxException {
        log.debug("REST request to save Autocareappointmenttype : {}", autocareappointmenttype);
        if (autocareappointmenttype.getId() != null) {
            throw new BadRequestAlertException("A new autocareappointmenttype cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocareappointmenttype = autocareappointmenttypeRepository.save(autocareappointmenttype);
        return ResponseEntity.created(new URI("/api/autocareappointmenttypes/" + autocareappointmenttype.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocareappointmenttype.getId().toString()))
            .body(autocareappointmenttype);
    }

    /**
     * {@code PUT  /autocareappointmenttypes/:id} : Updates an existing autocareappointmenttype.
     *
     * @param id the id of the autocareappointmenttype to save.
     * @param autocareappointmenttype the autocareappointmenttype to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocareappointmenttype,
     * or with status {@code 400 (Bad Request)} if the autocareappointmenttype is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocareappointmenttype couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocareappointmenttype> updateAutocareappointmenttype(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocareappointmenttype autocareappointmenttype
    ) throws URISyntaxException {
        log.debug("REST request to update Autocareappointmenttype : {}, {}", id, autocareappointmenttype);
        if (autocareappointmenttype.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocareappointmenttype.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocareappointmenttypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocareappointmenttype = autocareappointmenttypeRepository.save(autocareappointmenttype);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocareappointmenttype.getId().toString()))
            .body(autocareappointmenttype);
    }

    /**
     * {@code PATCH  /autocareappointmenttypes/:id} : Partial updates given fields of an existing autocareappointmenttype, field will ignore if it is null
     *
     * @param id the id of the autocareappointmenttype to save.
     * @param autocareappointmenttype the autocareappointmenttype to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocareappointmenttype,
     * or with status {@code 400 (Bad Request)} if the autocareappointmenttype is not valid,
     * or with status {@code 404 (Not Found)} if the autocareappointmenttype is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocareappointmenttype couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocareappointmenttype> partialUpdateAutocareappointmenttype(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocareappointmenttype autocareappointmenttype
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocareappointmenttype partially : {}, {}", id, autocareappointmenttype);
        if (autocareappointmenttype.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocareappointmenttype.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocareappointmenttypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocareappointmenttype> result = autocareappointmenttypeRepository
            .findById(autocareappointmenttype.getId())
            .map(existingAutocareappointmenttype -> {
                if (autocareappointmenttype.getTypename() != null) {
                    existingAutocareappointmenttype.setTypename(autocareappointmenttype.getTypename());
                }

                return existingAutocareappointmenttype;
            })
            .map(autocareappointmenttypeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocareappointmenttype.getId().toString())
        );
    }

    /**
     * {@code GET  /autocareappointmenttypes} : get all the autocareappointmenttypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocareappointmenttypes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocareappointmenttype>> getAllAutocareappointmenttypes(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocareappointmenttypes");
        Page<Autocareappointmenttype> page = autocareappointmenttypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocareappointmenttypes/:id} : get the "id" autocareappointmenttype.
     *
     * @param id the id of the autocareappointmenttype to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocareappointmenttype, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocareappointmenttype> getAutocareappointmenttype(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocareappointmenttype : {}", id);
        Optional<Autocareappointmenttype> autocareappointmenttype = autocareappointmenttypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocareappointmenttype);
    }

    /**
     * {@code DELETE  /autocareappointmenttypes/:id} : delete the "id" autocareappointmenttype.
     *
     * @param id the id of the autocareappointmenttype to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocareappointmenttype(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocareappointmenttype : {}", id);
        autocareappointmenttypeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
