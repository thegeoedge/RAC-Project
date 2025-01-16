package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Nextserviceinstructions;
import com.heavenscode.rac.repository.NextserviceinstructionsRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Nextserviceinstructions}.
 */
@RestController
@RequestMapping("/api/nextserviceinstructions")
@Transactional
public class NextserviceinstructionsResource {

    private final Logger log = LoggerFactory.getLogger(NextserviceinstructionsResource.class);

    private static final String ENTITY_NAME = "nextserviceinstructions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NextserviceinstructionsRepository nextserviceinstructionsRepository;

    public NextserviceinstructionsResource(NextserviceinstructionsRepository nextserviceinstructionsRepository) {
        this.nextserviceinstructionsRepository = nextserviceinstructionsRepository;
    }

    /**
     * {@code POST  /nextserviceinstructions} : Create a new nextserviceinstructions.
     *
     * @param nextserviceinstructions the nextserviceinstructions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nextserviceinstructions, or with status {@code 400 (Bad Request)} if the nextserviceinstructions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Nextserviceinstructions> createNextserviceinstructions(
        @RequestBody Nextserviceinstructions nextserviceinstructions
    ) throws URISyntaxException {
        log.debug("REST request to save Nextserviceinstructions : {}", nextserviceinstructions);
        if (nextserviceinstructions.getId() != null) {
            throw new BadRequestAlertException("A new nextserviceinstructions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        nextserviceinstructions = nextserviceinstructionsRepository.save(nextserviceinstructions);
        return ResponseEntity.created(new URI("/api/nextserviceinstructions/" + nextserviceinstructions.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, nextserviceinstructions.getId().toString()))
            .body(nextserviceinstructions);
    }

    /**
     * {@code PUT  /nextserviceinstructions/:id} : Updates an existing nextserviceinstructions.
     *
     * @param id the id of the nextserviceinstructions to save.
     * @param nextserviceinstructions the nextserviceinstructions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nextserviceinstructions,
     * or with status {@code 400 (Bad Request)} if the nextserviceinstructions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nextserviceinstructions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Nextserviceinstructions> updateNextserviceinstructions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Nextserviceinstructions nextserviceinstructions
    ) throws URISyntaxException {
        log.debug("REST request to update Nextserviceinstructions : {}, {}", id, nextserviceinstructions);
        if (nextserviceinstructions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nextserviceinstructions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nextserviceinstructionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        nextserviceinstructions = nextserviceinstructionsRepository.save(nextserviceinstructions);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nextserviceinstructions.getId().toString()))
            .body(nextserviceinstructions);
    }

    /**
     * {@code PATCH  /nextserviceinstructions/:id} : Partial updates given fields of an existing nextserviceinstructions, field will ignore if it is null
     *
     * @param id the id of the nextserviceinstructions to save.
     * @param nextserviceinstructions the nextserviceinstructions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nextserviceinstructions,
     * or with status {@code 400 (Bad Request)} if the nextserviceinstructions is not valid,
     * or with status {@code 404 (Not Found)} if the nextserviceinstructions is not found,
     * or with status {@code 500 (Internal Server Error)} if the nextserviceinstructions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Nextserviceinstructions> partialUpdateNextserviceinstructions(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Nextserviceinstructions nextserviceinstructions
    ) throws URISyntaxException {
        log.debug("REST request to partial update Nextserviceinstructions partially : {}, {}", id, nextserviceinstructions);
        if (nextserviceinstructions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nextserviceinstructions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nextserviceinstructionsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Nextserviceinstructions> result = nextserviceinstructionsRepository
            .findById(nextserviceinstructions.getId())
            .map(existingNextserviceinstructions -> {
                if (nextserviceinstructions.getJobid() != null) {
                    existingNextserviceinstructions.setJobid(nextserviceinstructions.getJobid());
                }
                if (nextserviceinstructions.getInstruction() != null) {
                    existingNextserviceinstructions.setInstruction(nextserviceinstructions.getInstruction());
                }
                if (nextserviceinstructions.getIsactive() != null) {
                    existingNextserviceinstructions.setIsactive(nextserviceinstructions.getIsactive());
                }
                if (nextserviceinstructions.getLmu() != null) {
                    existingNextserviceinstructions.setLmu(nextserviceinstructions.getLmu());
                }
                if (nextserviceinstructions.getLmd() != null) {
                    existingNextserviceinstructions.setLmd(nextserviceinstructions.getLmd());
                }
                if (nextserviceinstructions.getIgnore() != null) {
                    existingNextserviceinstructions.setIgnore(nextserviceinstructions.getIgnore());
                }
                if (nextserviceinstructions.getVehicleid() != null) {
                    existingNextserviceinstructions.setVehicleid(nextserviceinstructions.getVehicleid());
                }
                if (nextserviceinstructions.getVehicleno() != null) {
                    existingNextserviceinstructions.setVehicleno(nextserviceinstructions.getVehicleno());
                }

                return existingNextserviceinstructions;
            })
            .map(nextserviceinstructionsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, nextserviceinstructions.getId().toString())
        );
    }

    /**
     * {@code GET  /nextserviceinstructions} : get all the nextserviceinstructions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nextserviceinstructions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Nextserviceinstructions>> getAllNextserviceinstructions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Nextserviceinstructions");
        Page<Nextserviceinstructions> page = nextserviceinstructionsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /nextserviceinstructions/:id} : get the "id" nextserviceinstructions.
     *
     * @param id the id of the nextserviceinstructions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nextserviceinstructions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Nextserviceinstructions> getNextserviceinstructions(@PathVariable("id") Long id) {
        log.debug("REST request to get Nextserviceinstructions : {}", id);
        Optional<Nextserviceinstructions> nextserviceinstructions = nextserviceinstructionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(nextserviceinstructions);
    }

    /**
     * {@code DELETE  /nextserviceinstructions/:id} : delete the "id" nextserviceinstructions.
     *
     * @param id the id of the nextserviceinstructions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNextserviceinstructions(@PathVariable("id") Long id) {
        log.debug("REST request to delete Nextserviceinstructions : {}", id);
        nextserviceinstructionsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
