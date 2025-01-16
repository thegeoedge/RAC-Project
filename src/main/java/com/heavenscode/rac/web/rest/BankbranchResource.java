package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Bankbranch;
import com.heavenscode.rac.repository.BankbranchRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Bankbranch}.
 */
@RestController
@RequestMapping("/api/bankbranches")
@Transactional
public class BankbranchResource {

    private final Logger log = LoggerFactory.getLogger(BankbranchResource.class);

    private static final String ENTITY_NAME = "bankbranch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankbranchRepository bankbranchRepository;

    public BankbranchResource(BankbranchRepository bankbranchRepository) {
        this.bankbranchRepository = bankbranchRepository;
    }

    /**
     * {@code POST  /bankbranches} : Create a new bankbranch.
     *
     * @param bankbranch the bankbranch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bankbranch, or with status {@code 400 (Bad Request)} if the bankbranch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Bankbranch> createBankbranch(@RequestBody Bankbranch bankbranch) throws URISyntaxException {
        log.debug("REST request to save Bankbranch : {}", bankbranch);
        if (bankbranch.getId() != null) {
            throw new BadRequestAlertException("A new bankbranch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        bankbranch = bankbranchRepository.save(bankbranch);
        return ResponseEntity.created(new URI("/api/bankbranches/" + bankbranch.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, bankbranch.getId().toString()))
            .body(bankbranch);
    }

    /**
     * {@code PUT  /bankbranches/:id} : Updates an existing bankbranch.
     *
     * @param id the id of the bankbranch to save.
     * @param bankbranch the bankbranch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankbranch,
     * or with status {@code 400 (Bad Request)} if the bankbranch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bankbranch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Bankbranch> updateBankbranch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Bankbranch bankbranch
    ) throws URISyntaxException {
        log.debug("REST request to update Bankbranch : {}, {}", id, bankbranch);
        if (bankbranch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankbranch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankbranchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        bankbranch = bankbranchRepository.save(bankbranch);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankbranch.getId().toString()))
            .body(bankbranch);
    }

    /**
     * {@code PATCH  /bankbranches/:id} : Partial updates given fields of an existing bankbranch, field will ignore if it is null
     *
     * @param id the id of the bankbranch to save.
     * @param bankbranch the bankbranch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bankbranch,
     * or with status {@code 400 (Bad Request)} if the bankbranch is not valid,
     * or with status {@code 404 (Not Found)} if the bankbranch is not found,
     * or with status {@code 500 (Internal Server Error)} if the bankbranch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Bankbranch> partialUpdateBankbranch(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Bankbranch bankbranch
    ) throws URISyntaxException {
        log.debug("REST request to partial update Bankbranch partially : {}, {}", id, bankbranch);
        if (bankbranch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankbranch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankbranchRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Bankbranch> result = bankbranchRepository
            .findById(bankbranch.getId())
            .map(existingBankbranch -> {
                if (bankbranch.getBankcode() != null) {
                    existingBankbranch.setBankcode(bankbranch.getBankcode());
                }
                if (bankbranch.getBranchcode() != null) {
                    existingBankbranch.setBranchcode(bankbranch.getBranchcode());
                }
                if (bankbranch.getBranchname() != null) {
                    existingBankbranch.setBranchname(bankbranch.getBranchname());
                }

                return existingBankbranch;
            })
            .map(bankbranchRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankbranch.getId().toString())
        );
    }

    /**
     * {@code GET  /bankbranches} : get all the bankbranches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bankbranches in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Bankbranch>> getAllBankbranches(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Bankbranches");
        Page<Bankbranch> page = bankbranchRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bankbranches/:id} : get the "id" bankbranch.
     *
     * @param id the id of the bankbranch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankbranch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Bankbranch> getBankbranch(@PathVariable("id") Long id) {
        log.debug("REST request to get Bankbranch : {}", id);
        Optional<Bankbranch> bankbranch = bankbranchRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bankbranch);
    }

    /**
     * {@code DELETE  /bankbranches/:id} : delete the "id" bankbranch.
     *
     * @param id the id of the bankbranch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBankbranch(@PathVariable("id") Long id) {
        log.debug("REST request to delete Bankbranch : {}", id);
        bankbranchRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
