package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autojobempallocation;
import com.heavenscode.rac.repository.AutojobempallocationRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autojobempallocation}.
 */
@RestController
@RequestMapping("/api/autojobempallocations")
@Transactional
public class AutojobempallocationResource {

    private final Logger log = LoggerFactory.getLogger(AutojobempallocationResource.class);

    private static final String ENTITY_NAME = "autojobempallocation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutojobempallocationRepository autojobempallocationRepository;

    public AutojobempallocationResource(AutojobempallocationRepository autojobempallocationRepository) {
        this.autojobempallocationRepository = autojobempallocationRepository;
    }

    /**
     * {@code POST  /autojobempallocations} : Create a new autojobempallocation.
     *
     * @param autojobempallocation the autojobempallocation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autojobempallocation, or with status {@code 400 (Bad Request)} if the autojobempallocation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autojobempallocation> createAutojobempallocation(@RequestBody Autojobempallocation autojobempallocation)
        throws URISyntaxException {
        log.debug("REST request to save Autojobempallocation : {}", autojobempallocation);
        if (autojobempallocation.getId() != null) {
            throw new BadRequestAlertException("A new autojobempallocation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autojobempallocation = autojobempallocationRepository.save(autojobempallocation);
        return ResponseEntity.created(new URI("/api/autojobempallocations/" + autojobempallocation.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autojobempallocation.getId().toString()))
            .body(autojobempallocation);
    }

    /**
     * {@code PUT  /autojobempallocations/:id} : Updates an existing autojobempallocation.
     *
     * @param id the id of the autojobempallocation to save.
     * @param autojobempallocation the autojobempallocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autojobempallocation,
     * or with status {@code 400 (Bad Request)} if the autojobempallocation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autojobempallocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autojobempallocation> updateAutojobempallocation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autojobempallocation autojobempallocation
    ) throws URISyntaxException {
        log.debug("REST request to update Autojobempallocation : {}, {}", id, autojobempallocation);
        if (autojobempallocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autojobempallocation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autojobempallocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autojobempallocation = autojobempallocationRepository.save(autojobempallocation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autojobempallocation.getId().toString()))
            .body(autojobempallocation);
    }

    /**
     * {@code PATCH  /autojobempallocations/:id} : Partial updates given fields of an existing autojobempallocation, field will ignore if it is null
     *
     * @param id the id of the autojobempallocation to save.
     * @param autojobempallocation the autojobempallocation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autojobempallocation,
     * or with status {@code 400 (Bad Request)} if the autojobempallocation is not valid,
     * or with status {@code 404 (Not Found)} if the autojobempallocation is not found,
     * or with status {@code 500 (Internal Server Error)} if the autojobempallocation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autojobempallocation> partialUpdateAutojobempallocation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autojobempallocation autojobempallocation
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autojobempallocation partially : {}, {}", id, autojobempallocation);
        if (autojobempallocation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autojobempallocation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autojobempallocationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autojobempallocation> result = autojobempallocationRepository
            .findById(autojobempallocation.getId())
            .map(existingAutojobempallocation -> {
                if (autojobempallocation.getJobid() != null) {
                    existingAutojobempallocation.setJobid(autojobempallocation.getJobid());
                }
                if (autojobempallocation.getCategoryid() != null) {
                    existingAutojobempallocation.setCategoryid(autojobempallocation.getCategoryid());
                }
                if (autojobempallocation.getEmpposition() != null) {
                    existingAutojobempallocation.setEmpposition(autojobempallocation.getEmpposition());
                }
                if (autojobempallocation.getEmpid() != null) {
                    existingAutojobempallocation.setEmpid(autojobempallocation.getEmpid());
                }
                if (autojobempallocation.getEmpname() != null) {
                    existingAutojobempallocation.setEmpname(autojobempallocation.getEmpname());
                }
                if (autojobempallocation.getAllocatetime() != null) {
                    existingAutojobempallocation.setAllocatetime(autojobempallocation.getAllocatetime());
                }
                if (autojobempallocation.getLmu() != null) {
                    existingAutojobempallocation.setLmu(autojobempallocation.getLmu());
                }
                if (autojobempallocation.getLmd() != null) {
                    existingAutojobempallocation.setLmd(autojobempallocation.getLmd());
                }
                if (autojobempallocation.getJobdate() != null) {
                    existingAutojobempallocation.setJobdate(autojobempallocation.getJobdate());
                }
                if (autojobempallocation.getCommission() != null) {
                    existingAutojobempallocation.setCommission(autojobempallocation.getCommission());
                }
                if (autojobempallocation.getIscommissionpaid() != null) {
                    existingAutojobempallocation.setIscommissionpaid(autojobempallocation.getIscommissionpaid());
                }
                if (autojobempallocation.getStarttime() != null) {
                    existingAutojobempallocation.setStarttime(autojobempallocation.getStarttime());
                }
                if (autojobempallocation.getEndtime() != null) {
                    existingAutojobempallocation.setEndtime(autojobempallocation.getEndtime());
                }

                return existingAutojobempallocation;
            })
            .map(autojobempallocationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autojobempallocation.getId().toString())
        );
    }

    /**
     * {@code GET  /autojobempallocations} : get all the autojobempallocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autojobempallocations in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autojobempallocation>> getAllAutojobempallocations(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autojobempallocations");
        Page<Autojobempallocation> page = autojobempallocationRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autojobempallocations/:id} : get the "id" autojobempallocation.
     *
     * @param id the id of the autojobempallocation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autojobempallocation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autojobempallocation> getAutojobempallocation(@PathVariable("id") Long id) {
        log.debug("REST request to get Autojobempallocation : {}", id);
        Optional<Autojobempallocation> autojobempallocation = autojobempallocationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autojobempallocation);
    }

    /**
     * {@code DELETE  /autojobempallocations/:id} : delete the "id" autojobempallocation.
     *
     * @param id the id of the autojobempallocation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutojobempallocation(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autojobempallocation : {}", id);
        autojobempallocationRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
