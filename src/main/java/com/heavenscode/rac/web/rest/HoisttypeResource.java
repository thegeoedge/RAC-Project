package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Hoisttype;
import com.heavenscode.rac.repository.HoisttypeRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Hoisttype}.
 */
@RestController
@RequestMapping("/api/hoisttypes")
@Transactional
public class HoisttypeResource {

    private final Logger log = LoggerFactory.getLogger(HoisttypeResource.class);

    private static final String ENTITY_NAME = "hoisttype";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HoisttypeRepository hoisttypeRepository;

    public HoisttypeResource(HoisttypeRepository hoisttypeRepository) {
        this.hoisttypeRepository = hoisttypeRepository;
    }

    /**
     * {@code POST  /hoisttypes} : Create a new hoisttype.
     *
     * @param hoisttype the hoisttype to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hoisttype, or with status {@code 400 (Bad Request)} if the hoisttype has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Hoisttype> createHoisttype(@RequestBody Hoisttype hoisttype) throws URISyntaxException {
        log.debug("REST request to save Hoisttype : {}", hoisttype);
        if (hoisttype.getId() != null) {
            throw new BadRequestAlertException("A new hoisttype cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hoisttype = hoisttypeRepository.save(hoisttype);
        return ResponseEntity.created(new URI("/api/hoisttypes/" + hoisttype.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hoisttype.getId().toString()))
            .body(hoisttype);
    }

    /**
     * {@code PUT  /hoisttypes/:id} : Updates an existing hoisttype.
     *
     * @param id the id of the hoisttype to save.
     * @param hoisttype the hoisttype to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hoisttype,
     * or with status {@code 400 (Bad Request)} if the hoisttype is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hoisttype couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hoisttype> updateHoisttype(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Hoisttype hoisttype
    ) throws URISyntaxException {
        log.debug("REST request to update Hoisttype : {}, {}", id, hoisttype);
        if (hoisttype.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hoisttype.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hoisttypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hoisttype = hoisttypeRepository.save(hoisttype);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hoisttype.getId().toString()))
            .body(hoisttype);
    }

    /**
     * {@code PATCH  /hoisttypes/:id} : Partial updates given fields of an existing hoisttype, field will ignore if it is null
     *
     * @param id the id of the hoisttype to save.
     * @param hoisttype the hoisttype to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hoisttype,
     * or with status {@code 400 (Bad Request)} if the hoisttype is not valid,
     * or with status {@code 404 (Not Found)} if the hoisttype is not found,
     * or with status {@code 500 (Internal Server Error)} if the hoisttype couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Hoisttype> partialUpdateHoisttype(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Hoisttype hoisttype
    ) throws URISyntaxException {
        log.debug("REST request to partial update Hoisttype partially : {}, {}", id, hoisttype);
        if (hoisttype.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hoisttype.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hoisttypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Hoisttype> result = hoisttypeRepository
            .findById(hoisttype.getId())
            .map(existingHoisttype -> {
                if (hoisttype.getHoisttype() != null) {
                    existingHoisttype.setHoisttype(hoisttype.getHoisttype());
                }
                if (hoisttype.getLmu() != null) {
                    existingHoisttype.setLmu(hoisttype.getLmu());
                }
                if (hoisttype.getLmd() != null) {
                    existingHoisttype.setLmd(hoisttype.getLmd());
                }

                return existingHoisttype;
            })
            .map(hoisttypeRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hoisttype.getId().toString())
        );
    }

    /**
     * {@code GET  /hoisttypes} : get all the hoisttypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hoisttypes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Hoisttype>> getAllHoisttypes(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Hoisttypes");
        Page<Hoisttype> page = hoisttypeRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /hoisttypes/:id} : get the "id" hoisttype.
     *
     * @param id the id of the hoisttype to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hoisttype, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hoisttype> getHoisttype(@PathVariable("id") Long id) {
        log.debug("REST request to get Hoisttype : {}", id);
        Optional<Hoisttype> hoisttype = hoisttypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hoisttype);
    }

    /**
     * {@code DELETE  /hoisttypes/:id} : delete the "id" hoisttype.
     *
     * @param id the id of the hoisttype to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHoisttype(@PathVariable("id") Long id) {
        log.debug("REST request to delete Hoisttype : {}", id);
        hoisttypeRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
