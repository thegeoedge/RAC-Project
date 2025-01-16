package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocaretimetable;
import com.heavenscode.rac.repository.AutocaretimetableRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocaretimetable}.
 */
@RestController
@RequestMapping("/api/autocaretimetables")
@Transactional
public class AutocaretimetableResource {

    private final Logger log = LoggerFactory.getLogger(AutocaretimetableResource.class);

    private static final String ENTITY_NAME = "autocaretimetable";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocaretimetableRepository autocaretimetableRepository;

    public AutocaretimetableResource(AutocaretimetableRepository autocaretimetableRepository) {
        this.autocaretimetableRepository = autocaretimetableRepository;
    }

    /**
     * {@code POST  /autocaretimetables} : Create a new autocaretimetable.
     *
     * @param autocaretimetable the autocaretimetable to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocaretimetable, or with status {@code 400 (Bad Request)} if the autocaretimetable has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocaretimetable> createAutocaretimetable(@RequestBody Autocaretimetable autocaretimetable)
        throws URISyntaxException {
        log.debug("REST request to save Autocaretimetable : {}", autocaretimetable);
        if (autocaretimetable.getId() != null) {
            throw new BadRequestAlertException("A new autocaretimetable cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocaretimetable = autocaretimetableRepository.save(autocaretimetable);
        return ResponseEntity.created(new URI("/api/autocaretimetables/" + autocaretimetable.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocaretimetable.getId().toString()))
            .body(autocaretimetable);
    }

    /**
     * {@code PUT  /autocaretimetables/:id} : Updates an existing autocaretimetable.
     *
     * @param id the id of the autocaretimetable to save.
     * @param autocaretimetable the autocaretimetable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocaretimetable,
     * or with status {@code 400 (Bad Request)} if the autocaretimetable is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocaretimetable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocaretimetable> updateAutocaretimetable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocaretimetable autocaretimetable
    ) throws URISyntaxException {
        log.debug("REST request to update Autocaretimetable : {}, {}", id, autocaretimetable);
        if (autocaretimetable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocaretimetable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocaretimetableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocaretimetable = autocaretimetableRepository.save(autocaretimetable);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocaretimetable.getId().toString()))
            .body(autocaretimetable);
    }

    /**
     * {@code PATCH  /autocaretimetables/:id} : Partial updates given fields of an existing autocaretimetable, field will ignore if it is null
     *
     * @param id the id of the autocaretimetable to save.
     * @param autocaretimetable the autocaretimetable to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocaretimetable,
     * or with status {@code 400 (Bad Request)} if the autocaretimetable is not valid,
     * or with status {@code 404 (Not Found)} if the autocaretimetable is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocaretimetable couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocaretimetable> partialUpdateAutocaretimetable(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocaretimetable autocaretimetable
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocaretimetable partially : {}, {}", id, autocaretimetable);
        if (autocaretimetable.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocaretimetable.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocaretimetableRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocaretimetable> result = autocaretimetableRepository
            .findById(autocaretimetable.getId())
            .map(existingAutocaretimetable -> {
                if (autocaretimetable.getHoisttypeid() != null) {
                    existingAutocaretimetable.setHoisttypeid(autocaretimetable.getHoisttypeid());
                }
                if (autocaretimetable.getHoisttypename() != null) {
                    existingAutocaretimetable.setHoisttypename(autocaretimetable.getHoisttypename());
                }
                if (autocaretimetable.getHoisttime() != null) {
                    existingAutocaretimetable.setHoisttime(autocaretimetable.getHoisttime());
                }

                return existingAutocaretimetable;
            })
            .map(autocaretimetableRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocaretimetable.getId().toString())
        );
    }

    /**
     * {@code GET  /autocaretimetables} : get all the autocaretimetables.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocaretimetables in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocaretimetable>> getAllAutocaretimetables(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocaretimetables");
        Page<Autocaretimetable> page = autocaretimetableRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocaretimetables/:id} : get the "id" autocaretimetable.
     *
     * @param id the id of the autocaretimetable to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocaretimetable, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocaretimetable> getAutocaretimetable(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocaretimetable : {}", id);
        Optional<Autocaretimetable> autocaretimetable = autocaretimetableRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocaretimetable);
    }

    /**
     * {@code DELETE  /autocaretimetables/:id} : delete the "id" autocaretimetable.
     *
     * @param id the id of the autocaretimetable to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocaretimetable(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocaretimetable : {}", id);
        autocaretimetableRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
