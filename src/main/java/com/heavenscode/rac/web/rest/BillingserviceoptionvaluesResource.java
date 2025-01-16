package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Billingserviceoptionvalues;
import com.heavenscode.rac.repository.BillingserviceoptionvaluesRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Billingserviceoptionvalues}.
 */
@RestController
@RequestMapping("/api/billingserviceoptionvalues")
@Transactional
public class BillingserviceoptionvaluesResource {

    private final Logger log = LoggerFactory.getLogger(BillingserviceoptionvaluesResource.class);

    private static final String ENTITY_NAME = "billingserviceoptionvalues";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillingserviceoptionvaluesRepository billingserviceoptionvaluesRepository;

    public BillingserviceoptionvaluesResource(BillingserviceoptionvaluesRepository billingserviceoptionvaluesRepository) {
        this.billingserviceoptionvaluesRepository = billingserviceoptionvaluesRepository;
    }

    /**
     * {@code POST  /billingserviceoptionvalues} : Create a new billingserviceoptionvalues.
     *
     * @param billingserviceoptionvalues the billingserviceoptionvalues to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billingserviceoptionvalues, or with status {@code 400 (Bad Request)} if the billingserviceoptionvalues has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Billingserviceoptionvalues> createBillingserviceoptionvalues(
        @RequestBody Billingserviceoptionvalues billingserviceoptionvalues
    ) throws URISyntaxException {
        log.debug("REST request to save Billingserviceoptionvalues : {}", billingserviceoptionvalues);
        if (billingserviceoptionvalues.getVehicletypeid() != null) {
            throw new BadRequestAlertException("A new billingserviceoptionvalues cannot already have an ID", ENTITY_NAME, "idexists");
        }
        billingserviceoptionvalues = billingserviceoptionvaluesRepository.save(billingserviceoptionvalues);
        return ResponseEntity.created(new URI("/api/billingserviceoptionvalues/" + billingserviceoptionvalues.getVehicletypeid()))
            .headers(
                HeaderUtil.createEntityCreationAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    billingserviceoptionvalues.getVehicletypeid().toString()
                )
            )
            .body(billingserviceoptionvalues);
    }

    /**
     * {@code PUT  /billingserviceoptionvalues/:id} : Updates an existing billingserviceoptionvalues.
     *
     * @param id the id of the billingserviceoptionvalues to save.
     * @param billingserviceoptionvalues the billingserviceoptionvalues to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingserviceoptionvalues,
     * or with status {@code 400 (Bad Request)} if the billingserviceoptionvalues is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billingserviceoptionvalues couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Billingserviceoptionvalues> updateBillingserviceoptionvalues(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Billingserviceoptionvalues billingserviceoptionvalues
    ) throws URISyntaxException {
        log.debug("REST request to update Billingserviceoptionvalues : {}, {}", id, billingserviceoptionvalues);
        if (billingserviceoptionvalues.getVehicletypeid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billingserviceoptionvalues.getVehicletypeid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billingserviceoptionvaluesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        billingserviceoptionvalues = billingserviceoptionvaluesRepository.save(billingserviceoptionvalues);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(
                    applicationName,
                    false,
                    ENTITY_NAME,
                    billingserviceoptionvalues.getVehicletypeid().toString()
                )
            )
            .body(billingserviceoptionvalues);
    }

    /**
     * {@code PATCH  /billingserviceoptionvalues/:id} : Partial updates given fields of an existing billingserviceoptionvalues, field will ignore if it is null
     *
     * @param id the id of the billingserviceoptionvalues to save.
     * @param billingserviceoptionvalues the billingserviceoptionvalues to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billingserviceoptionvalues,
     * or with status {@code 400 (Bad Request)} if the billingserviceoptionvalues is not valid,
     * or with status {@code 404 (Not Found)} if the billingserviceoptionvalues is not found,
     * or with status {@code 500 (Internal Server Error)} if the billingserviceoptionvalues couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Billingserviceoptionvalues> partialUpdateBillingserviceoptionvalues(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Billingserviceoptionvalues billingserviceoptionvalues
    ) throws URISyntaxException {
        log.debug("REST request to partial update Billingserviceoptionvalues partially : {}, {}", id, billingserviceoptionvalues);
        if (billingserviceoptionvalues.getVehicletypeid() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billingserviceoptionvalues.getVehicletypeid())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!billingserviceoptionvaluesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Billingserviceoptionvalues> result = billingserviceoptionvaluesRepository
            .findById(billingserviceoptionvalues.getVehicletypeid())
            .map(existingBillingserviceoptionvalues -> {
                if (billingserviceoptionvalues.getVehicletypeid() != null) {
                    existingBillingserviceoptionvalues.setVehicletypeid(billingserviceoptionvalues.getVehicletypeid());
                }
                if (billingserviceoptionvalues.getBillingserviceoptionid() != null) {
                    existingBillingserviceoptionvalues.setBillingserviceoptionid(billingserviceoptionvalues.getBillingserviceoptionid());
                }
                if (billingserviceoptionvalues.getValue() != null) {
                    existingBillingserviceoptionvalues.setValue(billingserviceoptionvalues.getValue());
                }
                if (billingserviceoptionvalues.getLmd() != null) {
                    existingBillingserviceoptionvalues.setLmd(billingserviceoptionvalues.getLmd());
                }
                if (billingserviceoptionvalues.getLmu() != null) {
                    existingBillingserviceoptionvalues.setLmu(billingserviceoptionvalues.getLmu());
                }

                return existingBillingserviceoptionvalues;
            })
            .map(billingserviceoptionvaluesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(
                applicationName,
                false,
                ENTITY_NAME,
                billingserviceoptionvalues.getVehicletypeid().toString()
            )
        );
    }

    /**
     * {@code GET  /billingserviceoptionvalues} : get all the billingserviceoptionvalues.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billingserviceoptionvalues in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Billingserviceoptionvalues>> getAllBillingserviceoptionvalues(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Billingserviceoptionvalues");
        Page<Billingserviceoptionvalues> page = billingserviceoptionvaluesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /billingserviceoptionvalues/:id} : get the "id" billingserviceoptionvalues.
     *
     * @param id the id of the billingserviceoptionvalues to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billingserviceoptionvalues, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Billingserviceoptionvalues> getBillingserviceoptionvalues(@PathVariable("id") Long id) {
        log.debug("REST request to get Billingserviceoptionvalues : {}", id);
        Optional<Billingserviceoptionvalues> billingserviceoptionvalues = billingserviceoptionvaluesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(billingserviceoptionvalues);
    }

    /**
     * {@code DELETE  /billingserviceoptionvalues/:id} : delete the "id" billingserviceoptionvalues.
     *
     * @param id the id of the billingserviceoptionvalues to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBillingserviceoptionvalues(@PathVariable("id") Long id) {
        log.debug("REST request to delete Billingserviceoptionvalues : {}", id);
        billingserviceoptionvaluesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
