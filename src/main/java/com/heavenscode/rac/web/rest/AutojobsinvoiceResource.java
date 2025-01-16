package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autojobsinvoice;
import com.heavenscode.rac.repository.AutojobsinvoiceRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autojobsinvoice}.
 */
@RestController
@RequestMapping("/api/autojobsinvoices")
@Transactional
public class AutojobsinvoiceResource {

    private final Logger log = LoggerFactory.getLogger(AutojobsinvoiceResource.class);

    private static final String ENTITY_NAME = "autojobsinvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutojobsinvoiceRepository autojobsinvoiceRepository;

    public AutojobsinvoiceResource(AutojobsinvoiceRepository autojobsinvoiceRepository) {
        this.autojobsinvoiceRepository = autojobsinvoiceRepository;
    }

    /**
     * {@code POST  /autojobsinvoices} : Create a new autojobsinvoice.
     *
     * @param autojobsinvoice the autojobsinvoice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autojobsinvoice, or with status {@code 400 (Bad Request)} if the autojobsinvoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autojobsinvoice> createAutojobsinvoice(@RequestBody Autojobsinvoice autojobsinvoice) throws URISyntaxException {
        log.debug("REST request to save Autojobsinvoice : {}", autojobsinvoice);
        if (autojobsinvoice.getId() != null) {
            throw new BadRequestAlertException("A new autojobsinvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autojobsinvoice = autojobsinvoiceRepository.save(autojobsinvoice);
        return ResponseEntity.created(new URI("/api/autojobsinvoices/" + autojobsinvoice.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autojobsinvoice.getId().toString()))
            .body(autojobsinvoice);
    }

    /**
     * {@code PUT  /autojobsinvoices/:id} : Updates an existing autojobsinvoice.
     *
     * @param id the id of the autojobsinvoice to save.
     * @param autojobsinvoice the autojobsinvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autojobsinvoice,
     * or with status {@code 400 (Bad Request)} if the autojobsinvoice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autojobsinvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autojobsinvoice> updateAutojobsinvoice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autojobsinvoice autojobsinvoice
    ) throws URISyntaxException {
        log.debug("REST request to update Autojobsinvoice : {}, {}", id, autojobsinvoice);
        if (autojobsinvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autojobsinvoice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autojobsinvoiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autojobsinvoice = autojobsinvoiceRepository.save(autojobsinvoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autojobsinvoice.getId().toString()))
            .body(autojobsinvoice);
    }

    /**
     * {@code PATCH  /autojobsinvoices/:id} : Partial updates given fields of an existing autojobsinvoice, field will ignore if it is null
     *
     * @param id the id of the autojobsinvoice to save.
     * @param autojobsinvoice the autojobsinvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autojobsinvoice,
     * or with status {@code 400 (Bad Request)} if the autojobsinvoice is not valid,
     * or with status {@code 404 (Not Found)} if the autojobsinvoice is not found,
     * or with status {@code 500 (Internal Server Error)} if the autojobsinvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autojobsinvoice> partialUpdateAutojobsinvoice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autojobsinvoice autojobsinvoice
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autojobsinvoice partially : {}, {}", id, autojobsinvoice);
        if (autojobsinvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autojobsinvoice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autojobsinvoiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autojobsinvoice> result = autojobsinvoiceRepository
            .findById(autojobsinvoice.getId())
            .map(existingAutojobsinvoice -> {
                if (autojobsinvoice.getCode() != null) {
                    existingAutojobsinvoice.setCode(autojobsinvoice.getCode());
                }
                if (autojobsinvoice.getInvoicedate() != null) {
                    existingAutojobsinvoice.setInvoicedate(autojobsinvoice.getInvoicedate());
                }
                if (autojobsinvoice.getCreateddate() != null) {
                    existingAutojobsinvoice.setCreateddate(autojobsinvoice.getCreateddate());
                }
                if (autojobsinvoice.getJobid() != null) {
                    existingAutojobsinvoice.setJobid(autojobsinvoice.getJobid());
                }
                if (autojobsinvoice.getQuoteid() != null) {
                    existingAutojobsinvoice.setQuoteid(autojobsinvoice.getQuoteid());
                }
                if (autojobsinvoice.getOrderid() != null) {
                    existingAutojobsinvoice.setOrderid(autojobsinvoice.getOrderid());
                }
                if (autojobsinvoice.getDelieverydate() != null) {
                    existingAutojobsinvoice.setDelieverydate(autojobsinvoice.getDelieverydate());
                }
                if (autojobsinvoice.getAutojobsrepid() != null) {
                    existingAutojobsinvoice.setAutojobsrepid(autojobsinvoice.getAutojobsrepid());
                }
                if (autojobsinvoice.getAutojobsrepname() != null) {
                    existingAutojobsinvoice.setAutojobsrepname(autojobsinvoice.getAutojobsrepname());
                }
                if (autojobsinvoice.getDelieverfrom() != null) {
                    existingAutojobsinvoice.setDelieverfrom(autojobsinvoice.getDelieverfrom());
                }
                if (autojobsinvoice.getCustomerid() != null) {
                    existingAutojobsinvoice.setCustomerid(autojobsinvoice.getCustomerid());
                }
                if (autojobsinvoice.getCustomername() != null) {
                    existingAutojobsinvoice.setCustomername(autojobsinvoice.getCustomername());
                }
                if (autojobsinvoice.getCustomeraddress() != null) {
                    existingAutojobsinvoice.setCustomeraddress(autojobsinvoice.getCustomeraddress());
                }
                if (autojobsinvoice.getDeliveryaddress() != null) {
                    existingAutojobsinvoice.setDeliveryaddress(autojobsinvoice.getDeliveryaddress());
                }
                if (autojobsinvoice.getSubtotal() != null) {
                    existingAutojobsinvoice.setSubtotal(autojobsinvoice.getSubtotal());
                }
                if (autojobsinvoice.getTotaltax() != null) {
                    existingAutojobsinvoice.setTotaltax(autojobsinvoice.getTotaltax());
                }
                if (autojobsinvoice.getTotaldiscount() != null) {
                    existingAutojobsinvoice.setTotaldiscount(autojobsinvoice.getTotaldiscount());
                }
                if (autojobsinvoice.getNettotal() != null) {
                    existingAutojobsinvoice.setNettotal(autojobsinvoice.getNettotal());
                }
                if (autojobsinvoice.getMessage() != null) {
                    existingAutojobsinvoice.setMessage(autojobsinvoice.getMessage());
                }
                if (autojobsinvoice.getLmu() != null) {
                    existingAutojobsinvoice.setLmu(autojobsinvoice.getLmu());
                }
                if (autojobsinvoice.getLmd() != null) {
                    existingAutojobsinvoice.setLmd(autojobsinvoice.getLmd());
                }
                if (autojobsinvoice.getPaidamount() != null) {
                    existingAutojobsinvoice.setPaidamount(autojobsinvoice.getPaidamount());
                }
                if (autojobsinvoice.getAmountowing() != null) {
                    existingAutojobsinvoice.setAmountowing(autojobsinvoice.getAmountowing());
                }
                if (autojobsinvoice.getIsactive() != null) {
                    existingAutojobsinvoice.setIsactive(autojobsinvoice.getIsactive());
                }
                if (autojobsinvoice.getLocationid() != null) {
                    existingAutojobsinvoice.setLocationid(autojobsinvoice.getLocationid());
                }
                if (autojobsinvoice.getLocationcode() != null) {
                    existingAutojobsinvoice.setLocationcode(autojobsinvoice.getLocationcode());
                }
                if (autojobsinvoice.getReferencecode() != null) {
                    existingAutojobsinvoice.setReferencecode(autojobsinvoice.getReferencecode());
                }
                if (autojobsinvoice.getCreatedbyid() != null) {
                    existingAutojobsinvoice.setCreatedbyid(autojobsinvoice.getCreatedbyid());
                }
                if (autojobsinvoice.getCreatedbyname() != null) {
                    existingAutojobsinvoice.setCreatedbyname(autojobsinvoice.getCreatedbyname());
                }
                if (autojobsinvoice.getAutocarecompanyid() != null) {
                    existingAutojobsinvoice.setAutocarecompanyid(autojobsinvoice.getAutocarecompanyid());
                }

                return existingAutojobsinvoice;
            })
            .map(autojobsinvoiceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autojobsinvoice.getId().toString())
        );
    }

    /**
     * {@code GET  /autojobsinvoices} : get all the autojobsinvoices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autojobsinvoices in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autojobsinvoice>> getAllAutojobsinvoices(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Autojobsinvoices");
        Page<Autojobsinvoice> page = autojobsinvoiceRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autojobsinvoices/:id} : get the "id" autojobsinvoice.
     *
     * @param id the id of the autojobsinvoice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autojobsinvoice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autojobsinvoice> getAutojobsinvoice(@PathVariable("id") Long id) {
        log.debug("REST request to get Autojobsinvoice : {}", id);
        Optional<Autojobsinvoice> autojobsinvoice = autojobsinvoiceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autojobsinvoice);
    }

    /**
     * {@code DELETE  /autojobsinvoices/:id} : delete the "id" autojobsinvoice.
     *
     * @param id the id of the autojobsinvoice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutojobsinvoice(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autojobsinvoice : {}", id);
        autojobsinvoiceRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
