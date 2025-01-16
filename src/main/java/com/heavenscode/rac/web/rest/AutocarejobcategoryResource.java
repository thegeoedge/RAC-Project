package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocarejobcategory;
import com.heavenscode.rac.repository.AutocarejobcategoryRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocarejobcategory}.
 */
@RestController
@RequestMapping("/api/autocarejobcategories")
@Transactional
public class AutocarejobcategoryResource {

    private final Logger log = LoggerFactory.getLogger(AutocarejobcategoryResource.class);

    private static final String ENTITY_NAME = "autocarejobcategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocarejobcategoryRepository autocarejobcategoryRepository;

    public AutocarejobcategoryResource(AutocarejobcategoryRepository autocarejobcategoryRepository) {
        this.autocarejobcategoryRepository = autocarejobcategoryRepository;
    }

    /**
     * {@code POST  /autocarejobcategories} : Create a new autocarejobcategory.
     *
     * @param autocarejobcategory the autocarejobcategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocarejobcategory, or with status {@code 400 (Bad Request)} if the autocarejobcategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocarejobcategory> createAutocarejobcategory(@RequestBody Autocarejobcategory autocarejobcategory)
        throws URISyntaxException {
        log.debug("REST request to save Autocarejobcategory : {}", autocarejobcategory);
        if (autocarejobcategory.getId() != null) {
            throw new BadRequestAlertException("A new autocarejobcategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocarejobcategory = autocarejobcategoryRepository.save(autocarejobcategory);
        return ResponseEntity.created(new URI("/api/autocarejobcategories/" + autocarejobcategory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocarejobcategory.getId().toString()))
            .body(autocarejobcategory);
    }

    /**
     * {@code PUT  /autocarejobcategories/:id} : Updates an existing autocarejobcategory.
     *
     * @param id the id of the autocarejobcategory to save.
     * @param autocarejobcategory the autocarejobcategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarejobcategory,
     * or with status {@code 400 (Bad Request)} if the autocarejobcategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocarejobcategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocarejobcategory> updateAutocarejobcategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarejobcategory autocarejobcategory
    ) throws URISyntaxException {
        log.debug("REST request to update Autocarejobcategory : {}, {}", id, autocarejobcategory);
        if (autocarejobcategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarejobcategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarejobcategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocarejobcategory = autocarejobcategoryRepository.save(autocarejobcategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarejobcategory.getId().toString()))
            .body(autocarejobcategory);
    }

    /**
     * {@code PATCH  /autocarejobcategories/:id} : Partial updates given fields of an existing autocarejobcategory, field will ignore if it is null
     *
     * @param id the id of the autocarejobcategory to save.
     * @param autocarejobcategory the autocarejobcategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarejobcategory,
     * or with status {@code 400 (Bad Request)} if the autocarejobcategory is not valid,
     * or with status {@code 404 (Not Found)} if the autocarejobcategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocarejobcategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocarejobcategory> partialUpdateAutocarejobcategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarejobcategory autocarejobcategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocarejobcategory partially : {}, {}", id, autocarejobcategory);
        if (autocarejobcategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarejobcategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarejobcategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocarejobcategory> result = autocarejobcategoryRepository
            .findById(autocarejobcategory.getId())
            .map(existingAutocarejobcategory -> {
                if (autocarejobcategory.getCode() != null) {
                    existingAutocarejobcategory.setCode(autocarejobcategory.getCode());
                }
                if (autocarejobcategory.getName() != null) {
                    existingAutocarejobcategory.setName(autocarejobcategory.getName());
                }
                if (autocarejobcategory.getDescription() != null) {
                    existingAutocarejobcategory.setDescription(autocarejobcategory.getDescription());
                }
                if (autocarejobcategory.getLmu() != null) {
                    existingAutocarejobcategory.setLmu(autocarejobcategory.getLmu());
                }
                if (autocarejobcategory.getLmd() != null) {
                    existingAutocarejobcategory.setLmd(autocarejobcategory.getLmd());
                }
                if (autocarejobcategory.getDisplayorder() != null) {
                    existingAutocarejobcategory.setDisplayorder(autocarejobcategory.getDisplayorder());
                }

                return existingAutocarejobcategory;
            })
            .map(autocarejobcategoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarejobcategory.getId().toString())
        );
    }

    /**
     * {@code GET  /autocarejobcategories} : get all the autocarejobcategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocarejobcategories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocarejobcategory>> getAllAutocarejobcategories(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocarejobcategories");
        Page<Autocarejobcategory> page = autocarejobcategoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocarejobcategories/:id} : get the "id" autocarejobcategory.
     *
     * @param id the id of the autocarejobcategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocarejobcategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocarejobcategory> getAutocarejobcategory(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocarejobcategory : {}", id);
        Optional<Autocarejobcategory> autocarejobcategory = autocarejobcategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocarejobcategory);
    }

    /**
     * {@code DELETE  /autocarejobcategories/:id} : delete the "id" autocarejobcategory.
     *
     * @param id the id of the autocarejobcategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocarejobcategory(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocarejobcategory : {}", id);
        autocarejobcategoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
