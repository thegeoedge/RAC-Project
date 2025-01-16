package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Companybankaccount;
import com.heavenscode.rac.repository.CompanybankaccountRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Companybankaccount}.
 */
@RestController
@RequestMapping("/api/companybankaccounts")
@Transactional
public class CompanybankaccountResource {

    private final Logger log = LoggerFactory.getLogger(CompanybankaccountResource.class);

    private static final String ENTITY_NAME = "companybankaccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CompanybankaccountRepository companybankaccountRepository;

    public CompanybankaccountResource(CompanybankaccountRepository companybankaccountRepository) {
        this.companybankaccountRepository = companybankaccountRepository;
    }

    /**
     * {@code POST  /companybankaccounts} : Create a new companybankaccount.
     *
     * @param companybankaccount the companybankaccount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new companybankaccount, or with status {@code 400 (Bad Request)} if the companybankaccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Companybankaccount> createCompanybankaccount(@RequestBody Companybankaccount companybankaccount)
        throws URISyntaxException {
        log.debug("REST request to save Companybankaccount : {}", companybankaccount);
        if (companybankaccount.getId() != null) {
            throw new BadRequestAlertException("A new companybankaccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        companybankaccount = companybankaccountRepository.save(companybankaccount);
        return ResponseEntity.created(new URI("/api/companybankaccounts/" + companybankaccount.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, companybankaccount.getId().toString()))
            .body(companybankaccount);
    }

    /**
     * {@code PUT  /companybankaccounts/:id} : Updates an existing companybankaccount.
     *
     * @param id the id of the companybankaccount to save.
     * @param companybankaccount the companybankaccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated companybankaccount,
     * or with status {@code 400 (Bad Request)} if the companybankaccount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the companybankaccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Companybankaccount> updateCompanybankaccount(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Companybankaccount companybankaccount
    ) throws URISyntaxException {
        log.debug("REST request to update Companybankaccount : {}, {}", id, companybankaccount);
        if (companybankaccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, companybankaccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!companybankaccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        companybankaccount = companybankaccountRepository.save(companybankaccount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, companybankaccount.getId().toString()))
            .body(companybankaccount);
    }

    /**
     * {@code PATCH  /companybankaccounts/:id} : Partial updates given fields of an existing companybankaccount, field will ignore if it is null
     *
     * @param id the id of the companybankaccount to save.
     * @param companybankaccount the companybankaccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated companybankaccount,
     * or with status {@code 400 (Bad Request)} if the companybankaccount is not valid,
     * or with status {@code 404 (Not Found)} if the companybankaccount is not found,
     * or with status {@code 500 (Internal Server Error)} if the companybankaccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Companybankaccount> partialUpdateCompanybankaccount(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Companybankaccount companybankaccount
    ) throws URISyntaxException {
        log.debug("REST request to partial update Companybankaccount partially : {}, {}", id, companybankaccount);
        if (companybankaccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, companybankaccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!companybankaccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Companybankaccount> result = companybankaccountRepository
            .findById(companybankaccount.getId())
            .map(existingCompanybankaccount -> {
                if (companybankaccount.getCompanyid() != null) {
                    existingCompanybankaccount.setCompanyid(companybankaccount.getCompanyid());
                }
                if (companybankaccount.getAccountnumber() != null) {
                    existingCompanybankaccount.setAccountnumber(companybankaccount.getAccountnumber());
                }
                if (companybankaccount.getAccountname() != null) {
                    existingCompanybankaccount.setAccountname(companybankaccount.getAccountname());
                }
                if (companybankaccount.getBankname() != null) {
                    existingCompanybankaccount.setBankname(companybankaccount.getBankname());
                }
                if (companybankaccount.getBankid() != null) {
                    existingCompanybankaccount.setBankid(companybankaccount.getBankid());
                }
                if (companybankaccount.getBranchname() != null) {
                    existingCompanybankaccount.setBranchname(companybankaccount.getBranchname());
                }
                if (companybankaccount.getBranchid() != null) {
                    existingCompanybankaccount.setBranchid(companybankaccount.getBranchid());
                }
                if (companybankaccount.getAmount() != null) {
                    existingCompanybankaccount.setAmount(companybankaccount.getAmount());
                }
                if (companybankaccount.getAccountcode() != null) {
                    existingCompanybankaccount.setAccountcode(companybankaccount.getAccountcode());
                }
                if (companybankaccount.getAccountid() != null) {
                    existingCompanybankaccount.setAccountid(companybankaccount.getAccountid());
                }
                if (companybankaccount.getLmd() != null) {
                    existingCompanybankaccount.setLmd(companybankaccount.getLmd());
                }
                if (companybankaccount.getLmu() != null) {
                    existingCompanybankaccount.setLmu(companybankaccount.getLmu());
                }
                if (companybankaccount.getIsactive() != null) {
                    existingCompanybankaccount.setIsactive(companybankaccount.getIsactive());
                }
                if (companybankaccount.getAccounttypeid() != null) {
                    existingCompanybankaccount.setAccounttypeid(companybankaccount.getAccounttypeid());
                }

                return existingCompanybankaccount;
            })
            .map(companybankaccountRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, companybankaccount.getId().toString())
        );
    }

    /**
     * {@code GET  /companybankaccounts} : get all the companybankaccounts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of companybankaccounts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Companybankaccount>> getAllCompanybankaccounts(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Companybankaccounts");
        Page<Companybankaccount> page = companybankaccountRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /companybankaccounts/:id} : get the "id" companybankaccount.
     *
     * @param id the id of the companybankaccount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the companybankaccount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Companybankaccount> getCompanybankaccount(@PathVariable("id") Long id) {
        log.debug("REST request to get Companybankaccount : {}", id);
        Optional<Companybankaccount> companybankaccount = companybankaccountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(companybankaccount);
    }

    /**
     * {@code DELETE  /companybankaccounts/:id} : delete the "id" companybankaccount.
     *
     * @param id the id of the companybankaccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanybankaccount(@PathVariable("id") Long id) {
        log.debug("REST request to delete Companybankaccount : {}", id);
        companybankaccountRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
