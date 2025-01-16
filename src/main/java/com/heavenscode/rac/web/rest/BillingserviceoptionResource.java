package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Billingserviceoption;
import com.heavenscode.rac.repository.BillingserviceoptionRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Billingserviceoption}.
 */
@RestController
@RequestMapping("/api/billingserviceoptions")
@Transactional
public class BillingserviceoptionResource {

    private final Logger log = LoggerFactory.getLogger(BillingserviceoptionResource.class);

    private static final String ENTITY_NAME = "billingserviceoption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingserviceoptionRepository billingserviceoptionRepository;

    public BillingserviceoptionResource(BillingserviceoptionRepository billingserviceoptionRepository) {
        this.billingserviceoptionRepository = billingserviceoptionRepository;
    }

    /**
     * {@code POST  /billingserviceoptions} : Create a new billingserviceoption.
     *
     * @param billingserviceoption the billingserviceoption to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingserviceoption, or with status {@code 400 (Bad Request)} if the billingserviceoption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Billingserviceoption> createBillingserviceoption(@RequestBody Billingserviceoption billingserviceoption)
        throws URISyntaxException {
        log.debug("REST request to save Billingserviceoption : {}", billingserviceoption);
        if (billingserviceoption.getId() != null) {
            throw new BadRequestAlertException("A new billingserviceoption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        billingserviceoption = billingserviceoptionRepository.save(billingserviceoption);
        return ResponseEntity.created(new URI("/api/billingserviceoptions/" + billingserviceoption.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, billingserviceoption.getId().toString()))
            .body(billingserviceoption);
    }

    /**
     * {@code PUT  /billingserviceoptions/:id} : Updates an existing billingserviceoption.
     *
     * @param id the id of the billingserviceoption to save.
     * @param billingserviceoption the billingserviceoption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingserviceoption,
     * or with status {@code 400 (Bad Request)} if the billingserviceoption is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingserviceoption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Billingserviceoption> updateBillingserviceoption(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Billingserviceoption billingserviceoption
    ) throws URISyntaxException {
        log.debug("REST request to update Billingserviceoption : {}, {}", id, billingserviceoption);
        if (billingserviceoption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billingserviceoption.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billingserviceoptionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        billingserviceoption = billingserviceoptionRepository.save(billingserviceoption);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billingserviceoption.getId().toString()))
            .body(billingserviceoption);
    }

    /**
     * {@code PATCH  /billingserviceoptions/:id} : Partial updates given fields of an existing billingserviceoption, field will ignore if it is null
     *
     * @param id the id of the billingserviceoption to save.
     * @param billingserviceoption the billingserviceoption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingserviceoption,
     * or with status {@code 400 (Bad Request)} if the billingserviceoption is not valid,
     * or with status {@code 404 (Not Found)} if the billingserviceoption is not found,
     * or with status {@code 500 (Internal Server Error)} if the billingserviceoption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Billingserviceoption> partialUpdateBillingserviceoption(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Billingserviceoption billingserviceoption
    ) throws URISyntaxException {
        log.debug("REST request to partial update Billingserviceoption partially : {}, {}", id, billingserviceoption);
        if (billingserviceoption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billingserviceoption.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billingserviceoptionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Billingserviceoption> result = billingserviceoptionRepository
            .findById(billingserviceoption.getId())
            .map(existingBillingserviceoption -> {
                if (billingserviceoption.getServicename() != null) {
                    existingBillingserviceoption.setServicename(billingserviceoption.getServicename());
                }
                if (billingserviceoption.getServicediscription() != null) {
                    existingBillingserviceoption.setServicediscription(billingserviceoption.getServicediscription());
                }
                if (billingserviceoption.getIsactive() != null) {
                    existingBillingserviceoption.setIsactive(billingserviceoption.getIsactive());
                }
                if (billingserviceoption.getLmd() != null) {
                    existingBillingserviceoption.setLmd(billingserviceoption.getLmd());
                }
                if (billingserviceoption.getLmu() != null) {
                    existingBillingserviceoption.setLmu(billingserviceoption.getLmu());
                }
                if (billingserviceoption.getOrderby() != null) {
                    existingBillingserviceoption.setOrderby(billingserviceoption.getOrderby());
                }
                if (billingserviceoption.getBilltocustomer() != null) {
                    existingBillingserviceoption.setBilltocustomer(billingserviceoption.getBilltocustomer());
                }

                return existingBillingserviceoption;
            })
            .map(billingserviceoptionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, billingserviceoption.getId().toString())
        );
    }

    /**
     * {@code GET  /billingserviceoptions} : get all the billingserviceoptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingserviceoptions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Billingserviceoption>> getAllBillingserviceoptions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Billingserviceoptions");
        Page<Billingserviceoption> page = billingserviceoptionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billingserviceoptions/:id} : get the "id" billingserviceoption.
     *
     * @param id the id of the billingserviceoption to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingserviceoption, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Billingserviceoption> getBillingserviceoption(@PathVariable("id") Long id) {
        log.debug("REST request to get Billingserviceoption : {}", id);
        Optional<Billingserviceoption> billingserviceoption = billingserviceoptionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(billingserviceoption);
    }

    /**
     * {@code DELETE  /billingserviceoptions/:id} : delete the "id" billingserviceoption.
     *
     * @param id the id of the billingserviceoption to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillingserviceoption(@PathVariable("id") Long id) {
        log.debug("REST request to delete Billingserviceoption : {}", id);
        billingserviceoptionRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
