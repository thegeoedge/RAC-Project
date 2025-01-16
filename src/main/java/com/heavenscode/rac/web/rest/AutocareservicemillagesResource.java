package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocareservicemillages;
import com.heavenscode.rac.repository.AutocareservicemillagesRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocareservicemillages}.
 */
@RestController
@RequestMapping("/api/autocareservicemillages")
@Transactional
public class AutocareservicemillagesResource {

    private final Logger log = LoggerFactory.getLogger(AutocareservicemillagesResource.class);

    private static final String ENTITY_NAME = "autocareservicemillages";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocareservicemillagesRepository autocareservicemillagesRepository;

    public AutocareservicemillagesResource(AutocareservicemillagesRepository autocareservicemillagesRepository) {
        this.autocareservicemillagesRepository = autocareservicemillagesRepository;
    }

    /**
     * {@code POST  /autocareservicemillages} : Create a new autocareservicemillages.
     *
     * @param autocareservicemillages the autocareservicemillages to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocareservicemillages, or with status {@code 400 (Bad Request)} if the autocareservicemillages has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocareservicemillages> createAutocareservicemillages(
        @RequestBody Autocareservicemillages autocareservicemillages
    ) throws URISyntaxException {
        log.debug("REST request to save Autocareservicemillages : {}", autocareservicemillages);
        if (autocareservicemillages.getId() != null) {
            throw new BadRequestAlertException("A new autocareservicemillages cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocareservicemillages = autocareservicemillagesRepository.save(autocareservicemillages);
        return ResponseEntity.created(new URI("/api/autocareservicemillages/" + autocareservicemillages.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocareservicemillages.getId().toString()))
            .body(autocareservicemillages);
    }

    /**
     * {@code PUT  /autocareservicemillages/:id} : Updates an existing autocareservicemillages.
     *
     * @param id the id of the autocareservicemillages to save.
     * @param autocareservicemillages the autocareservicemillages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocareservicemillages,
     * or with status {@code 400 (Bad Request)} if the autocareservicemillages is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocareservicemillages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocareservicemillages> updateAutocareservicemillages(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocareservicemillages autocareservicemillages
    ) throws URISyntaxException {
        log.debug("REST request to update Autocareservicemillages : {}, {}", id, autocareservicemillages);
        if (autocareservicemillages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocareservicemillages.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocareservicemillagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocareservicemillages = autocareservicemillagesRepository.save(autocareservicemillages);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocareservicemillages.getId().toString()))
            .body(autocareservicemillages);
    }

    /**
     * {@code PATCH  /autocareservicemillages/:id} : Partial updates given fields of an existing autocareservicemillages, field will ignore if it is null
     *
     * @param id the id of the autocareservicemillages to save.
     * @param autocareservicemillages the autocareservicemillages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocareservicemillages,
     * or with status {@code 400 (Bad Request)} if the autocareservicemillages is not valid,
     * or with status {@code 404 (Not Found)} if the autocareservicemillages is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocareservicemillages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocareservicemillages> partialUpdateAutocareservicemillages(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocareservicemillages autocareservicemillages
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocareservicemillages partially : {}, {}", id, autocareservicemillages);
        if (autocareservicemillages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocareservicemillages.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocareservicemillagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocareservicemillages> result = autocareservicemillagesRepository
            .findById(autocareservicemillages.getId())
            .map(existingAutocareservicemillages -> {
                if (autocareservicemillages.getMillage() != null) {
                    existingAutocareservicemillages.setMillage(autocareservicemillages.getMillage());
                }

                return existingAutocareservicemillages;
            })
            .map(autocareservicemillagesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocareservicemillages.getId().toString())
        );
    }

    /**
     * {@code GET  /autocareservicemillages} : get all the autocareservicemillages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocareservicemillages in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocareservicemillages>> getAllAutocareservicemillages(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocareservicemillages");
        Page<Autocareservicemillages> page = autocareservicemillagesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocareservicemillages/:id} : get the "id" autocareservicemillages.
     *
     * @param id the id of the autocareservicemillages to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocareservicemillages, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocareservicemillages> getAutocareservicemillages(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocareservicemillages : {}", id);
        Optional<Autocareservicemillages> autocareservicemillages = autocareservicemillagesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocareservicemillages);
    }

    /**
     * {@code DELETE  /autocareservicemillages/:id} : delete the "id" autocareservicemillages.
     *
     * @param id the id of the autocareservicemillages to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocareservicemillages(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocareservicemillages : {}", id);
        autocareservicemillagesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
