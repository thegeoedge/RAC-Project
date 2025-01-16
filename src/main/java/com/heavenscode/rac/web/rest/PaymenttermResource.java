package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Paymentterm;
import com.heavenscode.rac.repository.PaymenttermRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Paymentterm}.
 */
@RestController
@RequestMapping("/api/paymentterms")
@Transactional
public class PaymenttermResource {

    private final Logger log = LoggerFactory.getLogger(PaymenttermResource.class);

    private static final String ENTITY_NAME = "paymentterm";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymenttermRepository paymenttermRepository;

    public PaymenttermResource(PaymenttermRepository paymenttermRepository) {
        this.paymenttermRepository = paymenttermRepository;
    }

    /**
     * {@code POST  /paymentterms} : Create a new paymentterm.
     *
     * @param paymentterm the paymentterm to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentterm, or with status {@code 400 (Bad Request)} if the paymentterm has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Paymentterm> createPaymentterm(@RequestBody Paymentterm paymentterm) throws URISyntaxException {
        log.debug("REST request to save Paymentterm : {}", paymentterm);
        if (paymentterm.getId() != null) {
            throw new BadRequestAlertException("A new paymentterm cannot already have an ID", ENTITY_NAME, "idexists");
        }
        paymentterm = paymenttermRepository.save(paymentterm);
        return ResponseEntity.created(new URI("/api/paymentterms/" + paymentterm.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, paymentterm.getId().toString()))
            .body(paymentterm);
    }

    /**
     * {@code PUT  /paymentterms/:id} : Updates an existing paymentterm.
     *
     * @param id the id of the paymentterm to save.
     * @param paymentterm the paymentterm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentterm,
     * or with status {@code 400 (Bad Request)} if the paymentterm is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentterm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Paymentterm> updatePaymentterm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Paymentterm paymentterm
    ) throws URISyntaxException {
        log.debug("REST request to update Paymentterm : {}, {}", id, paymentterm);
        if (paymentterm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentterm.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymenttermRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        paymentterm = paymenttermRepository.save(paymentterm);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentterm.getId().toString()))
            .body(paymentterm);
    }

    /**
     * {@code PATCH  /paymentterms/:id} : Partial updates given fields of an existing paymentterm, field will ignore if it is null
     *
     * @param id the id of the paymentterm to save.
     * @param paymentterm the paymentterm to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentterm,
     * or with status {@code 400 (Bad Request)} if the paymentterm is not valid,
     * or with status {@code 404 (Not Found)} if the paymentterm is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentterm couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Paymentterm> partialUpdatePaymentterm(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Paymentterm paymentterm
    ) throws URISyntaxException {
        log.debug("REST request to partial update Paymentterm partially : {}, {}", id, paymentterm);
        if (paymentterm.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentterm.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!paymenttermRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Paymentterm> result = paymenttermRepository
            .findById(paymentterm.getId())
            .map(existingPaymentterm -> {
                if (paymentterm.getPaymenttype() != null) {
                    existingPaymentterm.setPaymenttype(paymentterm.getPaymenttype());
                }
                if (paymentterm.getForvoucher() != null) {
                    existingPaymentterm.setForvoucher(paymentterm.getForvoucher());
                }

                return existingPaymentterm;
            })
            .map(paymenttermRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentterm.getId().toString())
        );
    }

    /**
     * {@code GET  /paymentterms} : get all the paymentterms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentterms in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Paymentterm>> getAllPaymentterms(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Paymentterms");
        Page<Paymentterm> page = paymenttermRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /paymentterms/:id} : get the "id" paymentterm.
     *
     * @param id the id of the paymentterm to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentterm, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paymentterm> getPaymentterm(@PathVariable("id") Long id) {
        log.debug("REST request to get Paymentterm : {}", id);
        Optional<Paymentterm> paymentterm = paymenttermRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paymentterm);
    }

    /**
     * {@code DELETE  /paymentterms/:id} : delete the "id" paymentterm.
     *
     * @param id the id of the paymentterm to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentterm(@PathVariable("id") Long id) {
        log.debug("REST request to delete Paymentterm : {}", id);
        paymenttermRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
