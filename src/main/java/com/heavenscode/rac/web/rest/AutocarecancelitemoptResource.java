package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocarecancelitemopt;
import com.heavenscode.rac.repository.AutocarecancelitemoptRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocarecancelitemopt}.
 */
@RestController
@RequestMapping("/api/autocarecancelitemopts")
@Transactional
public class AutocarecancelitemoptResource {

    private final Logger log = LoggerFactory.getLogger(AutocarecancelitemoptResource.class);

    private static final String ENTITY_NAME = "autocarecancelitemopt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocarecancelitemoptRepository autocarecancelitemoptRepository;

    public AutocarecancelitemoptResource(AutocarecancelitemoptRepository autocarecancelitemoptRepository) {
        this.autocarecancelitemoptRepository = autocarecancelitemoptRepository;
    }

    /**
     * {@code POST  /autocarecancelitemopts} : Create a new autocarecancelitemopt.
     *
     * @param autocarecancelitemopt the autocarecancelitemopt to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocarecancelitemopt, or with status {@code 400 (Bad Request)} if the autocarecancelitemopt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocarecancelitemopt> createAutocarecancelitemopt(@RequestBody Autocarecancelitemopt autocarecancelitemopt)
        throws URISyntaxException {
        log.debug("REST request to save Autocarecancelitemopt : {}", autocarecancelitemopt);
        if (autocarecancelitemopt.getId() != null) {
            throw new BadRequestAlertException("A new autocarecancelitemopt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocarecancelitemopt = autocarecancelitemoptRepository.save(autocarecancelitemopt);
        return ResponseEntity.created(new URI("/api/autocarecancelitemopts/" + autocarecancelitemopt.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocarecancelitemopt.getId().toString()))
            .body(autocarecancelitemopt);
    }

    /**
     * {@code PUT  /autocarecancelitemopts/:id} : Updates an existing autocarecancelitemopt.
     *
     * @param id the id of the autocarecancelitemopt to save.
     * @param autocarecancelitemopt the autocarecancelitemopt to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarecancelitemopt,
     * or with status {@code 400 (Bad Request)} if the autocarecancelitemopt is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocarecancelitemopt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocarecancelitemopt> updateAutocarecancelitemopt(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarecancelitemopt autocarecancelitemopt
    ) throws URISyntaxException {
        log.debug("REST request to update Autocarecancelitemopt : {}, {}", id, autocarecancelitemopt);
        if (autocarecancelitemopt.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarecancelitemopt.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarecancelitemoptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocarecancelitemopt = autocarecancelitemoptRepository.save(autocarecancelitemopt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarecancelitemopt.getId().toString()))
            .body(autocarecancelitemopt);
    }

    /**
     * {@code PATCH  /autocarecancelitemopts/:id} : Partial updates given fields of an existing autocarecancelitemopt, field will ignore if it is null
     *
     * @param id the id of the autocarecancelitemopt to save.
     * @param autocarecancelitemopt the autocarecancelitemopt to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarecancelitemopt,
     * or with status {@code 400 (Bad Request)} if the autocarecancelitemopt is not valid,
     * or with status {@code 404 (Not Found)} if the autocarecancelitemopt is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocarecancelitemopt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocarecancelitemopt> partialUpdateAutocarecancelitemopt(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarecancelitemopt autocarecancelitemopt
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocarecancelitemopt partially : {}, {}", id, autocarecancelitemopt);
        if (autocarecancelitemopt.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarecancelitemopt.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarecancelitemoptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocarecancelitemopt> result = autocarecancelitemoptRepository
            .findById(autocarecancelitemopt.getId())
            .map(existingAutocarecancelitemopt -> {
                if (autocarecancelitemopt.getCanceloption() != null) {
                    existingAutocarecancelitemopt.setCanceloption(autocarecancelitemopt.getCanceloption());
                }

                return existingAutocarecancelitemopt;
            })
            .map(autocarecancelitemoptRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarecancelitemopt.getId().toString())
        );
    }

    /**
     * {@code GET  /autocarecancelitemopts} : get all the autocarecancelitemopts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocarecancelitemopts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocarecancelitemopt>> getAllAutocarecancelitemopts(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocarecancelitemopts");
        Page<Autocarecancelitemopt> page = autocarecancelitemoptRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocarecancelitemopts/:id} : get the "id" autocarecancelitemopt.
     *
     * @param id the id of the autocarecancelitemopt to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocarecancelitemopt, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocarecancelitemopt> getAutocarecancelitemopt(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocarecancelitemopt : {}", id);
        Optional<Autocarecancelitemopt> autocarecancelitemopt = autocarecancelitemoptRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocarecancelitemopt);
    }

    /**
     * {@code DELETE  /autocarecancelitemopts/:id} : delete the "id" autocarecancelitemopt.
     *
     * @param id the id of the autocarecancelitemopt to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocarecancelitemopt(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocarecancelitemopt : {}", id);
        autocarecancelitemoptRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
