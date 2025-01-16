package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Workshopworklist;
import com.heavenscode.rac.repository.WorkshopworklistRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Workshopworklist}.
 */
@RestController
@RequestMapping("/api/workshopworklists")
@Transactional
public class WorkshopworklistResource {

    private final Logger log = LoggerFactory.getLogger(WorkshopworklistResource.class);

    private static final String ENTITY_NAME = "workshopworklist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WorkshopworklistRepository workshopworklistRepository;

    public WorkshopworklistResource(WorkshopworklistRepository workshopworklistRepository) {
        this.workshopworklistRepository = workshopworklistRepository;
    }

    /**
     * {@code POST  /workshopworklists} : Create a new workshopworklist.
     *
     * @param workshopworklist the workshopworklist to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new workshopworklist, or with status {@code 400 (Bad Request)} if the workshopworklist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Workshopworklist> createWorkshopworklist(@RequestBody Workshopworklist workshopworklist)
        throws URISyntaxException {
        log.debug("REST request to save Workshopworklist : {}", workshopworklist);
        if (workshopworklist.getId() != null) {
            throw new BadRequestAlertException("A new workshopworklist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        workshopworklist = workshopworklistRepository.save(workshopworklist);
        return ResponseEntity.created(new URI("/api/workshopworklists/" + workshopworklist.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, workshopworklist.getId().toString()))
            .body(workshopworklist);
    }

    /**
     * {@code PUT  /workshopworklists/:id} : Updates an existing workshopworklist.
     *
     * @param id the id of the workshopworklist to save.
     * @param workshopworklist the workshopworklist to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workshopworklist,
     * or with status {@code 400 (Bad Request)} if the workshopworklist is not valid,
     * or with status {@code 500 (Internal Server Error)} if the workshopworklist couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Workshopworklist> updateWorkshopworklist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Workshopworklist workshopworklist
    ) throws URISyntaxException {
        log.debug("REST request to update Workshopworklist : {}, {}", id, workshopworklist);
        if (workshopworklist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workshopworklist.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workshopworklistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        workshopworklist = workshopworklistRepository.save(workshopworklist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workshopworklist.getId().toString()))
            .body(workshopworklist);
    }

    /**
     * {@code PATCH  /workshopworklists/:id} : Partial updates given fields of an existing workshopworklist, field will ignore if it is null
     *
     * @param id the id of the workshopworklist to save.
     * @param workshopworklist the workshopworklist to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated workshopworklist,
     * or with status {@code 400 (Bad Request)} if the workshopworklist is not valid,
     * or with status {@code 404 (Not Found)} if the workshopworklist is not found,
     * or with status {@code 500 (Internal Server Error)} if the workshopworklist couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Workshopworklist> partialUpdateWorkshopworklist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Workshopworklist workshopworklist
    ) throws URISyntaxException {
        log.debug("REST request to partial update Workshopworklist partially : {}, {}", id, workshopworklist);
        if (workshopworklist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, workshopworklist.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!workshopworklistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Workshopworklist> result = workshopworklistRepository
            .findById(workshopworklist.getId())
            .map(existingWorkshopworklist -> {
                if (workshopworklist.getWorkshopwork() != null) {
                    existingWorkshopworklist.setWorkshopwork(workshopworklist.getWorkshopwork());
                }
                if (workshopworklist.getWorkshopworkdescription() != null) {
                    existingWorkshopworklist.setWorkshopworkdescription(workshopworklist.getWorkshopworkdescription());
                }
                if (workshopworklist.getIsactive() != null) {
                    existingWorkshopworklist.setIsactive(workshopworklist.getIsactive());
                }
                if (workshopworklist.getLmd() != null) {
                    existingWorkshopworklist.setLmd(workshopworklist.getLmd());
                }
                if (workshopworklist.getLmu() != null) {
                    existingWorkshopworklist.setLmu(workshopworklist.getLmu());
                }

                return existingWorkshopworklist;
            })
            .map(workshopworklistRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, workshopworklist.getId().toString())
        );
    }

    /**
     * {@code GET  /workshopworklists} : get all the workshopworklists.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of workshopworklists in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Workshopworklist>> getAllWorkshopworklists(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Workshopworklists");
        Page<Workshopworklist> page = workshopworklistRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /workshopworklists/:id} : get the "id" workshopworklist.
     *
     * @param id the id of the workshopworklist to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the workshopworklist, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Workshopworklist> getWorkshopworklist(@PathVariable("id") Long id) {
        log.debug("REST request to get Workshopworklist : {}", id);
        Optional<Workshopworklist> workshopworklist = workshopworklistRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(workshopworklist);
    }

    /**
     * {@code DELETE  /workshopworklists/:id} : delete the "id" workshopworklist.
     *
     * @param id the id of the workshopworklist to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshopworklist(@PathVariable("id") Long id) {
        log.debug("REST request to delete Workshopworklist : {}", id);
        workshopworklistRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
