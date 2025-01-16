package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.EmpSectiontbl;
import com.heavenscode.rac.repository.EmpSectiontblRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.EmpSectiontbl}.
 */
@RestController
@RequestMapping("/api/emp-sectiontbls")
@Transactional
public class EmpSectiontblResource {

    private final Logger log = LoggerFactory.getLogger(EmpSectiontblResource.class);

    private static final String ENTITY_NAME = "empSectiontbl";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpSectiontblRepository empSectiontblRepository;

    public EmpSectiontblResource(EmpSectiontblRepository empSectiontblRepository) {
        this.empSectiontblRepository = empSectiontblRepository;
    }

    /**
     * {@code POST  /emp-sectiontbls} : Create a new empSectiontbl.
     *
     * @param empSectiontbl the empSectiontbl to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empSectiontbl, or with status {@code 400 (Bad Request)} if the empSectiontbl has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EmpSectiontbl> createEmpSectiontbl(@RequestBody EmpSectiontbl empSectiontbl) throws URISyntaxException {
        log.debug("REST request to save EmpSectiontbl : {}", empSectiontbl);
        if (empSectiontbl.getEmpid() != null) {
            throw new BadRequestAlertException("A new empSectiontbl cannot already have an ID", ENTITY_NAME, "idexists");
        }
        empSectiontbl = empSectiontblRepository.save(empSectiontbl);
        return ResponseEntity.created(new URI("/api/emp-sectiontbls/" + empSectiontbl.getEmpid()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, empSectiontbl.getEmpid().toString()))
            .body(empSectiontbl);
    }

    /**
     * {@code PUT  /emp-sectiontbls/:id} : Updates an existing empSectiontbl.
     *
     * @param id the id of the empSectiontbl to save.
     * @param empSectiontbl the empSectiontbl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empSectiontbl,
     * or with status {@code 400 (Bad Request)} if the empSectiontbl is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empSectiontbl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpSectiontbl> updateEmpSectiontbl(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmpSectiontbl empSectiontbl
    ) throws URISyntaxException {
        log.debug("REST request to update EmpSectiontbl : {}, {}", id, empSectiontbl);
        if (empSectiontbl.getEmpid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empSectiontbl.getEmpid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empSectiontblRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        empSectiontbl = empSectiontblRepository.save(empSectiontbl);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, empSectiontbl.getEmpid().toString()))
            .body(empSectiontbl);
    }

    /**
     * {@code PATCH  /emp-sectiontbls/:id} : Partial updates given fields of an existing empSectiontbl, field will ignore if it is null
     *
     * @param id the id of the empSectiontbl to save.
     * @param empSectiontbl the empSectiontbl to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empSectiontbl,
     * or with status {@code 400 (Bad Request)} if the empSectiontbl is not valid,
     * or with status {@code 404 (Not Found)} if the empSectiontbl is not found,
     * or with status {@code 500 (Internal Server Error)} if the empSectiontbl couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmpSectiontbl> partialUpdateEmpSectiontbl(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EmpSectiontbl empSectiontbl
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmpSectiontbl partially : {}, {}", id, empSectiontbl);
        if (empSectiontbl.getEmpid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empSectiontbl.getEmpid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empSectiontblRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmpSectiontbl> result = empSectiontblRepository
            .findById(empSectiontbl.getEmpid())
            .map(existingEmpSectiontbl -> {
                if (empSectiontbl.getEmpid() != null) {
                    existingEmpSectiontbl.setEmpid(empSectiontbl.getEmpid());
                }
                if (empSectiontbl.getSectionid() != null) {
                    existingEmpSectiontbl.setSectionid(empSectiontbl.getSectionid());
                }
                if (empSectiontbl.getLmd() != null) {
                    existingEmpSectiontbl.setLmd(empSectiontbl.getLmd());
                }
                if (empSectiontbl.getLmu() != null) {
                    existingEmpSectiontbl.setLmu(empSectiontbl.getLmu());
                }

                return existingEmpSectiontbl;
            })
            .map(empSectiontblRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, empSectiontbl.getEmpid().toString())
        );
    }

    /**
     * {@code GET  /emp-sectiontbls} : get all the empSectiontbls.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empSectiontbls in body.
     */
    @GetMapping("")
    public ResponseEntity<List<EmpSectiontbl>> getAllEmpSectiontbls(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of EmpSectiontbls");
        Page<EmpSectiontbl> page = empSectiontblRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /emp-sectiontbls/:id} : get the "id" empSectiontbl.
     *
     * @param id the id of the empSectiontbl to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empSectiontbl, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpSectiontbl> getEmpSectiontbl(@PathVariable("id") Long id) {
        log.debug("REST request to get EmpSectiontbl : {}", id);
        Optional<EmpSectiontbl> empSectiontbl = empSectiontblRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(empSectiontbl);
    }

    /**
     * {@code DELETE  /emp-sectiontbls/:id} : delete the "id" empSectiontbl.
     *
     * @param id the id of the empSectiontbl to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpSectiontbl(@PathVariable("id") Long id) {
        log.debug("REST request to delete EmpSectiontbl : {}", id);
        empSectiontblRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
