package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Salesorder;
import com.heavenscode.rac.repository.SalesorderRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Salesorder}.
 */
@RestController
@RequestMapping("/api/salesorders")
@Transactional
public class SalesorderResource {

    private final Logger log = LoggerFactory.getLogger(SalesorderResource.class);

    private static final String ENTITY_NAME = "salesorder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesorderRepository salesorderRepository;

    public SalesorderResource(SalesorderRepository salesorderRepository) {
        this.salesorderRepository = salesorderRepository;
    }

    /**
     * {@code POST  /salesorders} : Create a new salesorder.
     *
     * @param salesorder the salesorder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesorder, or with status {@code 400 (Bad Request)} if the salesorder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Salesorder> createSalesorder(@RequestBody Salesorder salesorder) throws URISyntaxException {
        log.debug("REST request to save Salesorder : {}", salesorder);
        if (salesorder.getId() != null) {
            throw new BadRequestAlertException("A new salesorder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        salesorder = salesorderRepository.save(salesorder);
        return ResponseEntity.created(new URI("/api/salesorders/" + salesorder.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, salesorder.getId().toString()))
            .body(salesorder);
    }

    /**
     * {@code PUT  /salesorders/:id} : Updates an existing salesorder.
     *
     * @param id the id of the salesorder to save.
     * @param salesorder the salesorder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesorder,
     * or with status {@code 400 (Bad Request)} if the salesorder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesorder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Salesorder> updateSalesorder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Salesorder salesorder
    ) throws URISyntaxException {
        log.debug("REST request to update Salesorder : {}, {}", id, salesorder);
        if (salesorder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesorder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesorderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        salesorder = salesorderRepository.save(salesorder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesorder.getId().toString()))
            .body(salesorder);
    }

    /**
     * {@code PATCH  /salesorders/:id} : Partial updates given fields of an existing salesorder, field will ignore if it is null
     *
     * @param id the id of the salesorder to save.
     * @param salesorder the salesorder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesorder,
     * or with status {@code 400 (Bad Request)} if the salesorder is not valid,
     * or with status {@code 404 (Not Found)} if the salesorder is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesorder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Salesorder> partialUpdateSalesorder(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Salesorder salesorder
    ) throws URISyntaxException {
        log.debug("REST request to partial update Salesorder partially : {}, {}", id, salesorder);
        if (salesorder.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesorder.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesorderRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Salesorder> result = salesorderRepository
            .findById(salesorder.getId())
            .map(existingSalesorder -> {
                if (salesorder.getCode() != null) {
                    existingSalesorder.setCode(salesorder.getCode());
                }
                if (salesorder.getOrderdate() != null) {
                    existingSalesorder.setOrderdate(salesorder.getOrderdate());
                }
                if (salesorder.getCreateddate() != null) {
                    existingSalesorder.setCreateddate(salesorder.getCreateddate());
                }
                if (salesorder.getQuoteid() != null) {
                    existingSalesorder.setQuoteid(salesorder.getQuoteid());
                }
                if (salesorder.getSalesrepid() != null) {
                    existingSalesorder.setSalesrepid(salesorder.getSalesrepid());
                }
                if (salesorder.getSalesrepname() != null) {
                    existingSalesorder.setSalesrepname(salesorder.getSalesrepname());
                }
                if (salesorder.getDelieverfrom() != null) {
                    existingSalesorder.setDelieverfrom(salesorder.getDelieverfrom());
                }
                if (salesorder.getCustomerid() != null) {
                    existingSalesorder.setCustomerid(salesorder.getCustomerid());
                }
                if (salesorder.getCustomername() != null) {
                    existingSalesorder.setCustomername(salesorder.getCustomername());
                }
                if (salesorder.getCustomeraddress() != null) {
                    existingSalesorder.setCustomeraddress(salesorder.getCustomeraddress());
                }
                if (salesorder.getDeliveryaddress() != null) {
                    existingSalesorder.setDeliveryaddress(salesorder.getDeliveryaddress());
                }
                if (salesorder.getSubtotal() != null) {
                    existingSalesorder.setSubtotal(salesorder.getSubtotal());
                }
                if (salesorder.getTotaltax() != null) {
                    existingSalesorder.setTotaltax(salesorder.getTotaltax());
                }
                if (salesorder.getTotaldiscount() != null) {
                    existingSalesorder.setTotaldiscount(salesorder.getTotaldiscount());
                }
                if (salesorder.getNettotal() != null) {
                    existingSalesorder.setNettotal(salesorder.getNettotal());
                }
                if (salesorder.getMessage() != null) {
                    existingSalesorder.setMessage(salesorder.getMessage());
                }
                if (salesorder.getIsinvoiced() != null) {
                    existingSalesorder.setIsinvoiced(salesorder.getIsinvoiced());
                }
                if (salesorder.getRefinvoiceid() != null) {
                    existingSalesorder.setRefinvoiceid(salesorder.getRefinvoiceid());
                }
                if (salesorder.getLmu() != null) {
                    existingSalesorder.setLmu(salesorder.getLmu());
                }
                if (salesorder.getLmd() != null) {
                    existingSalesorder.setLmd(salesorder.getLmd());
                }
                if (salesorder.getIsfixed() != null) {
                    existingSalesorder.setIsfixed(salesorder.getIsfixed());
                }
                if (salesorder.getIsactive() != null) {
                    existingSalesorder.setIsactive(salesorder.getIsactive());
                }
                if (salesorder.getAdvancepayment() != null) {
                    existingSalesorder.setAdvancepayment(salesorder.getAdvancepayment());
                }
                if (salesorder.getAdvancepaymentreturnamount() != null) {
                    existingSalesorder.setAdvancepaymentreturnamount(salesorder.getAdvancepaymentreturnamount());
                }
                if (salesorder.getAdvancepaymentreturndate() != null) {
                    existingSalesorder.setAdvancepaymentreturndate(salesorder.getAdvancepaymentreturndate());
                }
                if (salesorder.getAdvancepaymentby() != null) {
                    existingSalesorder.setAdvancepaymentby(salesorder.getAdvancepaymentby());
                }

                return existingSalesorder;
            })
            .map(salesorderRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesorder.getId().toString())
        );
    }

    /**
     * {@code GET  /salesorders} : get all the salesorders.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesorders in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Salesorder>> getAllSalesorders(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Salesorders");
        Page<Salesorder> page = salesorderRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /salesorders/:id} : get the "id" salesorder.
     *
     * @param id the id of the salesorder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesorder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Salesorder> getSalesorder(@PathVariable("id") Long id) {
        log.debug("REST request to get Salesorder : {}", id);
        Optional<Salesorder> salesorder = salesorderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(salesorder);
    }

    /**
     * {@code DELETE  /salesorders/:id} : delete the "id" salesorder.
     *
     * @param id the id of the salesorder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesorder(@PathVariable("id") Long id) {
        log.debug("REST request to delete Salesorder : {}", id);
        salesorderRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
