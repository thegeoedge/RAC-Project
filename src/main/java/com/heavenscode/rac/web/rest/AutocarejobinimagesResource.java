package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocarejobinimages;
import com.heavenscode.rac.repository.AutocarejobinimagesRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocarejobinimages}.
 */
@RestController
@RequestMapping("/api/autocarejobinimages")
@Transactional
public class AutocarejobinimagesResource {

    private final Logger log = LoggerFactory.getLogger(AutocarejobinimagesResource.class);

    private static final String ENTITY_NAME = "autocarejobinimages";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocarejobinimagesRepository autocarejobinimagesRepository;

    public AutocarejobinimagesResource(AutocarejobinimagesRepository autocarejobinimagesRepository) {
        this.autocarejobinimagesRepository = autocarejobinimagesRepository;
    }

    /**
     * {@code POST  /autocarejobinimages} : Create a new autocarejobinimages.
     *
     * @param autocarejobinimages the autocarejobinimages to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocarejobinimages, or with status {@code 400 (Bad Request)} if the autocarejobinimages has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocarejobinimages> createAutocarejobinimages(@RequestBody Autocarejobinimages autocarejobinimages)
        throws URISyntaxException {
        log.debug("REST request to save Autocarejobinimages : {}", autocarejobinimages);
        if (autocarejobinimages.getId() != null) {
            throw new BadRequestAlertException("A new autocarejobinimages cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocarejobinimages = autocarejobinimagesRepository.save(autocarejobinimages);
        return ResponseEntity.created(new URI("/api/autocarejobinimages/" + autocarejobinimages.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocarejobinimages.getId().toString()))
            .body(autocarejobinimages);
    }

    /**
     * {@code PUT  /autocarejobinimages/:id} : Updates an existing autocarejobinimages.
     *
     * @param id the id of the autocarejobinimages to save.
     * @param autocarejobinimages the autocarejobinimages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarejobinimages,
     * or with status {@code 400 (Bad Request)} if the autocarejobinimages is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocarejobinimages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocarejobinimages> updateAutocarejobinimages(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarejobinimages autocarejobinimages
    ) throws URISyntaxException {
        log.debug("REST request to update Autocarejobinimages : {}, {}", id, autocarejobinimages);
        if (autocarejobinimages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarejobinimages.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarejobinimagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocarejobinimages = autocarejobinimagesRepository.save(autocarejobinimages);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarejobinimages.getId().toString()))
            .body(autocarejobinimages);
    }

    /**
     * {@code PATCH  /autocarejobinimages/:id} : Partial updates given fields of an existing autocarejobinimages, field will ignore if it is null
     *
     * @param id the id of the autocarejobinimages to save.
     * @param autocarejobinimages the autocarejobinimages to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarejobinimages,
     * or with status {@code 400 (Bad Request)} if the autocarejobinimages is not valid,
     * or with status {@code 404 (Not Found)} if the autocarejobinimages is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocarejobinimages couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocarejobinimages> partialUpdateAutocarejobinimages(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarejobinimages autocarejobinimages
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocarejobinimages partially : {}, {}", id, autocarejobinimages);
        if (autocarejobinimages.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarejobinimages.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarejobinimagesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocarejobinimages> result = autocarejobinimagesRepository
            .findById(autocarejobinimages.getId())
            .map(existingAutocarejobinimages -> {
                if (autocarejobinimages.getJobid() != null) {
                    existingAutocarejobinimages.setJobid(autocarejobinimages.getJobid());
                }
                if (autocarejobinimages.getImagefolder() != null) {
                    existingAutocarejobinimages.setImagefolder(autocarejobinimages.getImagefolder());
                }
                if (autocarejobinimages.getImagename() != null) {
                    existingAutocarejobinimages.setImagename(autocarejobinimages.getImagename());
                }

                return existingAutocarejobinimages;
            })
            .map(autocarejobinimagesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarejobinimages.getId().toString())
        );
    }

    /**
     * {@code GET  /autocarejobinimages} : get all the autocarejobinimages.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocarejobinimages in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocarejobinimages>> getAllAutocarejobinimages(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocarejobinimages");
        Page<Autocarejobinimages> page = autocarejobinimagesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocarejobinimages/:id} : get the "id" autocarejobinimages.
     *
     * @param id the id of the autocarejobinimages to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocarejobinimages, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocarejobinimages> getAutocarejobinimages(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocarejobinimages : {}", id);
        Optional<Autocarejobinimages> autocarejobinimages = autocarejobinimagesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocarejobinimages);
    }

    /**
     * {@code DELETE  /autocarejobinimages/:id} : delete the "id" autocarejobinimages.
     *
     * @param id the id of the autocarejobinimages to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocarejobinimages(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocarejobinimages : {}", id);
        autocarejobinimagesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
