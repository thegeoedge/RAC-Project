package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.EmpJobcommission;
import com.heavenscode.rac.repository.EmpJobcommissionRepository;
import com.heavenscode.rac.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.EmpJobcommission}.
 */
@RestController
@RequestMapping("/api/emp-jobcommissions")
@Transactional
public class EmpJobcommissionResource {

    private final Logger log = LoggerFactory.getLogger(EmpJobcommissionResource.class);

    private static final String ENTITY_NAME = "empJobcommission";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmpJobcommissionRepository empJobcommissionRepository;

    public EmpJobcommissionResource(EmpJobcommissionRepository empJobcommissionRepository) {
        this.empJobcommissionRepository = empJobcommissionRepository;
    }

    /**
     * {@code POST  /emp-jobcommissions} : Create a new empJobcommission.
     *
     * @param empJobcommission the empJobcommission to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new empJobcommission, or with status {@code 400 (Bad Request)} if the empJobcommission has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<EmpJobcommission> createEmpJobcommission(@Valid @RequestBody EmpJobcommission empJobcommission)
        throws URISyntaxException {
        log.debug("REST request to save EmpJobcommission : {}", empJobcommission);
        if (empJobcommission.getVehcatid() != null) {
            throw new BadRequestAlertException("A new empJobcommission cannot already have an ID", ENTITY_NAME, "idexists");
        }
        empJobcommission = empJobcommissionRepository.save(empJobcommission);
        return ResponseEntity.created(new URI("/api/emp-jobcommissions/" + empJobcommission.getVehcatid()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, empJobcommission.getVehcatid().toString()))
            .body(empJobcommission);
    }

    /**
     * {@code PUT  /emp-jobcommissions/:id} : Updates an existing empJobcommission.
     *
     * @param id the id of the empJobcommission to save.
     * @param empJobcommission the empJobcommission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empJobcommission,
     * or with status {@code 400 (Bad Request)} if the empJobcommission is not valid,
     * or with status {@code 500 (Internal Server Error)} if the empJobcommission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmpJobcommission> updateEmpJobcommission(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EmpJobcommission empJobcommission
    ) throws URISyntaxException {
        log.debug("REST request to update EmpJobcommission : {}, {}", id, empJobcommission);
        if (empJobcommission.getVehcatid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empJobcommission.getVehcatid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empJobcommissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        empJobcommission = empJobcommissionRepository.save(empJobcommission);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, empJobcommission.getVehcatid().toString()))
            .body(empJobcommission);
    }

    /**
     * {@code PATCH  /emp-jobcommissions/:id} : Partial updates given fields of an existing empJobcommission, field will ignore if it is null
     *
     * @param id the id of the empJobcommission to save.
     * @param empJobcommission the empJobcommission to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated empJobcommission,
     * or with status {@code 400 (Bad Request)} if the empJobcommission is not valid,
     * or with status {@code 404 (Not Found)} if the empJobcommission is not found,
     * or with status {@code 500 (Internal Server Error)} if the empJobcommission couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EmpJobcommission> partialUpdateEmpJobcommission(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EmpJobcommission empJobcommission
    ) throws URISyntaxException {
        log.debug("REST request to partial update EmpJobcommission partially : {}, {}", id, empJobcommission);
        if (empJobcommission.getVehcatid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, empJobcommission.getVehcatid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!empJobcommissionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EmpJobcommission> result = empJobcommissionRepository
            .findById(empJobcommission.getVehcatid())
            .map(existingEmpJobcommission -> {
                if (empJobcommission.getVehcatid() != null) {
                    existingEmpJobcommission.setVehcatid(empJobcommission.getVehcatid());
                }
                if (empJobcommission.getAutojobcatid() != null) {
                    existingEmpJobcommission.setAutojobcatid(empJobcommission.getAutojobcatid());
                }
                if (empJobcommission.getFirstcom() != null) {
                    existingEmpJobcommission.setFirstcom(empJobcommission.getFirstcom());
                }
                if (empJobcommission.getSecondcom() != null) {
                    existingEmpJobcommission.setSecondcom(empJobcommission.getSecondcom());
                }
                if (empJobcommission.getThirdcom() != null) {
                    existingEmpJobcommission.setThirdcom(empJobcommission.getThirdcom());
                }
                if (empJobcommission.getLmd() != null) {
                    existingEmpJobcommission.setLmd(empJobcommission.getLmd());
                }
                if (empJobcommission.getLmu() != null) {
                    existingEmpJobcommission.setLmu(empJobcommission.getLmu());
                }

                return existingEmpJobcommission;
            })
            .map(empJobcommissionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, empJobcommission.getVehcatid().toString())
        );
    }

    /**
     * {@code GET  /emp-jobcommissions} : get all the empJobcommissions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of empJobcommissions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<EmpJobcommission>> getAllEmpJobcommissions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of EmpJobcommissions");
        Page<EmpJobcommission> page = empJobcommissionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /emp-jobcommissions/:id} : get the "id" empJobcommission.
     *
     * @param id the id of the empJobcommission to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the empJobcommission, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmpJobcommission> getEmpJobcommission(@PathVariable("id") Long id) {
        log.debug("REST request to get EmpJobcommission : {}", id);
        Optional<EmpJobcommission> empJobcommission = empJobcommissionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(empJobcommission);
    }

    /**
     * {@code DELETE  /emp-jobcommissions/:id} : delete the "id" empJobcommission.
     *
     * @param id the id of the empJobcommission to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpJobcommission(@PathVariable("id") Long id) {
        log.debug("REST request to delete EmpJobcommission : {}", id);
        empJobcommissionRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
