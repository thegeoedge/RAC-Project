package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Lastserviceinstructions;
import com.heavenscode.rac.repository.LastserviceinstructionsRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Lastserviceinstructions}.
 */
@RestController
@RequestMapping("/api/lastserviceinstructions")
@Transactional
public class LastserviceinstructionsResource {

    private final Logger log = LoggerFactory.getLogger(LastserviceinstructionsResource.class);

    private static final String ENTITY_NAME = "lastserviceinstructions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LastserviceinstructionsRepository lastserviceinstructionsRepository;

    public LastserviceinstructionsResource(LastserviceinstructionsRepository lastserviceinstructionsRepository) {
        this.lastserviceinstructionsRepository = lastserviceinstructionsRepository;
    }

    /**
     * {@code POST  /lastserviceinstructions} : Create a new lastserviceinstructions.
     *
     * @param lastserviceinstructions the lastserviceinstructions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lastserviceinstructions, or with status {@code 400 (Bad Request)} if the lastserviceinstructions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Lastserviceinstructions> createLastserviceinstructions(
        @RequestBody Lastserviceinstructions lastserviceinstructions
    ) throws URISyntaxException {
        log.debug("REST request to save Lastserviceinstructions : {}", lastserviceinstructions);
        if (lastserviceinstructions.getId() != null) {
            throw new BadRequestAlertException("A new lastserviceinstructions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        lastserviceinstructions = lastserviceinstructionsRepository.save(lastserviceinstructions);
        return ResponseEntity.created(new URI("/api/lastserviceinstructions/" + lastserviceinstructions.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, lastserviceinstructions.getId().toString()))
            .body(lastserviceinstructions);
    }

    /**
     * {@code PUT  /lastserviceinstructions/:id} : Updates an existing lastserviceinstructions.
     *
     * @param id the id of the lastserviceinstructions to save.
     * @param lastserviceinstructions the lastserviceinstructions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lastserviceinstructions,
     * or with status {@code 400 (Bad Request)} if the lastserviceinstructions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lastserviceinstructions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Lastserviceinstructions> updateLastserviceinstructions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Lastserviceinstructions lastserviceinstructions
    ) throws URISyntaxException {
        log.debug("REST request to update Lastserviceinstructions : {}, {}", id, lastserviceinstructions);
        if (lastserviceinstructions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lastserviceinstructions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lastserviceinstructionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        lastserviceinstructions = lastserviceinstructionsRepository.save(lastserviceinstructions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lastserviceinstructions.getId().toString()))
            .body(lastserviceinstructions);
    }

    /**
     * {@code PATCH  /lastserviceinstructions/:id} : Partial updates given fields of an existing lastserviceinstructions, field will ignore if it is null
     *
     * @param id the id of the lastserviceinstructions to save.
     * @param lastserviceinstructions the lastserviceinstructions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lastserviceinstructions,
     * or with status {@code 400 (Bad Request)} if the lastserviceinstructions is not valid,
     * or with status {@code 404 (Not Found)} if the lastserviceinstructions is not found,
     * or with status {@code 500 (Internal Server Error)} if the lastserviceinstructions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Lastserviceinstructions> partialUpdateLastserviceinstructions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Lastserviceinstructions lastserviceinstructions
    ) throws URISyntaxException {
        log.debug("REST request to partial update Lastserviceinstructions partially : {}, {}", id, lastserviceinstructions);
        if (lastserviceinstructions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, lastserviceinstructions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!lastserviceinstructionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Lastserviceinstructions> result = lastserviceinstructionsRepository
            .findById(lastserviceinstructions.getId())
            .map(existingLastserviceinstructions -> {
                if (lastserviceinstructions.getJobid() != null) {
                    existingLastserviceinstructions.setJobid(lastserviceinstructions.getJobid());
                }
                if (lastserviceinstructions.getInstruction() != null) {
                    existingLastserviceinstructions.setInstruction(lastserviceinstructions.getInstruction());
                }
                if (lastserviceinstructions.getIsactive() != null) {
                    existingLastserviceinstructions.setIsactive(lastserviceinstructions.getIsactive());
                }
                if (lastserviceinstructions.getLmu() != null) {
                    existingLastserviceinstructions.setLmu(lastserviceinstructions.getLmu());
                }
                if (lastserviceinstructions.getLmd() != null) {
                    existingLastserviceinstructions.setLmd(lastserviceinstructions.getLmd());
                }
                if (lastserviceinstructions.getIgnore() != null) {
                    existingLastserviceinstructions.setIgnore(lastserviceinstructions.getIgnore());
                }
                if (lastserviceinstructions.getVehicleid() != null) {
                    existingLastserviceinstructions.setVehicleid(lastserviceinstructions.getVehicleid());
                }
                if (lastserviceinstructions.getVehicleno() != null) {
                    existingLastserviceinstructions.setVehicleno(lastserviceinstructions.getVehicleno());
                }

                return existingLastserviceinstructions;
            })
            .map(lastserviceinstructionsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, lastserviceinstructions.getId().toString())
        );
    }

    /**
     * {@code GET  /lastserviceinstructions} : get all the lastserviceinstructions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lastserviceinstructions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Lastserviceinstructions>> getAllLastserviceinstructions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Lastserviceinstructions");
        Page<Lastserviceinstructions> page = lastserviceinstructionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lastserviceinstructions/:id} : get the "id" lastserviceinstructions.
     *
     * @param id the id of the lastserviceinstructions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lastserviceinstructions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Lastserviceinstructions> getLastserviceinstructions(@PathVariable("id") Long id) {
        log.debug("REST request to get Lastserviceinstructions : {}", id);
        Optional<Lastserviceinstructions> lastserviceinstructions = lastserviceinstructionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(lastserviceinstructions);
    }

    /**
     * {@code DELETE  /lastserviceinstructions/:id} : delete the "id" lastserviceinstructions.
     *
     * @param id the id of the lastserviceinstructions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLastserviceinstructions(@PathVariable("id") Long id) {
        log.debug("REST request to delete Lastserviceinstructions : {}", id);
        lastserviceinstructionsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
