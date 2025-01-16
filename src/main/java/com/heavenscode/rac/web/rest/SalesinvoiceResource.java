package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Salesinvoice;
import com.heavenscode.rac.repository.SalesinvoiceRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Salesinvoice}.
 */
@RestController
@RequestMapping("/api/salesinvoices")
@Transactional
public class SalesinvoiceResource {

    private final Logger log = LoggerFactory.getLogger(SalesinvoiceResource.class);

    private static final String ENTITY_NAME = "salesinvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesinvoiceRepository salesinvoiceRepository;

    public SalesinvoiceResource(SalesinvoiceRepository salesinvoiceRepository) {
        this.salesinvoiceRepository = salesinvoiceRepository;
    }

    /**
     * {@code POST  /salesinvoices} : Create a new salesinvoice.
     *
     * @param salesinvoice the salesinvoice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesinvoice, or with status {@code 400 (Bad Request)} if the salesinvoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Salesinvoice> createSalesinvoice(@RequestBody Salesinvoice salesinvoice) throws URISyntaxException {
        log.debug("REST request to save Salesinvoice : {}", salesinvoice);
        if (salesinvoice.getId() != null) {
            throw new BadRequestAlertException("A new salesinvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        salesinvoice = salesinvoiceRepository.save(salesinvoice);
        return ResponseEntity.created(new URI("/api/salesinvoices/" + salesinvoice.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, salesinvoice.getId().toString()))
            .body(salesinvoice);
    }

    /**
     * {@code PUT  /salesinvoices/:id} : Updates an existing salesinvoice.
     *
     * @param id the id of the salesinvoice to save.
     * @param salesinvoice the salesinvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesinvoice,
     * or with status {@code 400 (Bad Request)} if the salesinvoice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesinvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Salesinvoice> updateSalesinvoice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Salesinvoice salesinvoice
    ) throws URISyntaxException {
        log.debug("REST request to update Salesinvoice : {}, {}", id, salesinvoice);
        if (salesinvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesinvoice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesinvoiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        salesinvoice = salesinvoiceRepository.save(salesinvoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesinvoice.getId().toString()))
            .body(salesinvoice);
    }

    /**
     * {@code PATCH  /salesinvoices/:id} : Partial updates given fields of an existing salesinvoice, field will ignore if it is null
     *
     * @param id the id of the salesinvoice to save.
     * @param salesinvoice the salesinvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesinvoice,
     * or with status {@code 400 (Bad Request)} if the salesinvoice is not valid,
     * or with status {@code 404 (Not Found)} if the salesinvoice is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesinvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Salesinvoice> partialUpdateSalesinvoice(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Salesinvoice salesinvoice
    ) throws URISyntaxException {
        log.debug("REST request to partial update Salesinvoice partially : {}, {}", id, salesinvoice);
        if (salesinvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesinvoice.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesinvoiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Salesinvoice> result = salesinvoiceRepository
            .findById(salesinvoice.getId())
            .map(existingSalesinvoice -> {
                if (salesinvoice.getCode() != null) {
                    existingSalesinvoice.setCode(salesinvoice.getCode());
                }
                if (salesinvoice.getInvoicedate() != null) {
                    existingSalesinvoice.setInvoicedate(salesinvoice.getInvoicedate());
                }
                if (salesinvoice.getCreateddate() != null) {
                    existingSalesinvoice.setCreateddate(salesinvoice.getCreateddate());
                }
                if (salesinvoice.getQuoteid() != null) {
                    existingSalesinvoice.setQuoteid(salesinvoice.getQuoteid());
                }
                if (salesinvoice.getOrderid() != null) {
                    existingSalesinvoice.setOrderid(salesinvoice.getOrderid());
                }
                if (salesinvoice.getDelieverydate() != null) {
                    existingSalesinvoice.setDelieverydate(salesinvoice.getDelieverydate());
                }
                if (salesinvoice.getSalesrepid() != null) {
                    existingSalesinvoice.setSalesrepid(salesinvoice.getSalesrepid());
                }
                if (salesinvoice.getSalesrepname() != null) {
                    existingSalesinvoice.setSalesrepname(salesinvoice.getSalesrepname());
                }
                if (salesinvoice.getDelieverfrom() != null) {
                    existingSalesinvoice.setDelieverfrom(salesinvoice.getDelieverfrom());
                }
                if (salesinvoice.getCustomerid() != null) {
                    existingSalesinvoice.setCustomerid(salesinvoice.getCustomerid());
                }
                if (salesinvoice.getCustomername() != null) {
                    existingSalesinvoice.setCustomername(salesinvoice.getCustomername());
                }
                if (salesinvoice.getCustomeraddress() != null) {
                    existingSalesinvoice.setCustomeraddress(salesinvoice.getCustomeraddress());
                }
                if (salesinvoice.getDeliveryaddress() != null) {
                    existingSalesinvoice.setDeliveryaddress(salesinvoice.getDeliveryaddress());
                }
                if (salesinvoice.getSubtotal() != null) {
                    existingSalesinvoice.setSubtotal(salesinvoice.getSubtotal());
                }
                if (salesinvoice.getTotaltax() != null) {
                    existingSalesinvoice.setTotaltax(salesinvoice.getTotaltax());
                }
                if (salesinvoice.getTotaldiscount() != null) {
                    existingSalesinvoice.setTotaldiscount(salesinvoice.getTotaldiscount());
                }
                if (salesinvoice.getNettotal() != null) {
                    existingSalesinvoice.setNettotal(salesinvoice.getNettotal());
                }
                if (salesinvoice.getMessage() != null) {
                    existingSalesinvoice.setMessage(salesinvoice.getMessage());
                }
                if (salesinvoice.getLmu() != null) {
                    existingSalesinvoice.setLmu(salesinvoice.getLmu());
                }
                if (salesinvoice.getLmd() != null) {
                    existingSalesinvoice.setLmd(salesinvoice.getLmd());
                }
                if (salesinvoice.getPaidamount() != null) {
                    existingSalesinvoice.setPaidamount(salesinvoice.getPaidamount());
                }
                if (salesinvoice.getAmountowing() != null) {
                    existingSalesinvoice.setAmountowing(salesinvoice.getAmountowing());
                }
                if (salesinvoice.getIsactive() != null) {
                    existingSalesinvoice.setIsactive(salesinvoice.getIsactive());
                }
                if (salesinvoice.getLocationid() != null) {
                    existingSalesinvoice.setLocationid(salesinvoice.getLocationid());
                }
                if (salesinvoice.getLocationcode() != null) {
                    existingSalesinvoice.setLocationcode(salesinvoice.getLocationcode());
                }
                if (salesinvoice.getReferencecode() != null) {
                    existingSalesinvoice.setReferencecode(salesinvoice.getReferencecode());
                }
                if (salesinvoice.getCreatedbyid() != null) {
                    existingSalesinvoice.setCreatedbyid(salesinvoice.getCreatedbyid());
                }
                if (salesinvoice.getCreatedbyname() != null) {
                    existingSalesinvoice.setCreatedbyname(salesinvoice.getCreatedbyname());
                }
                if (salesinvoice.getAutocarecharges() != null) {
                    existingSalesinvoice.setAutocarecharges(salesinvoice.getAutocarecharges());
                }
                if (salesinvoice.getAutocarejobid() != null) {
                    existingSalesinvoice.setAutocarejobid(salesinvoice.getAutocarejobid());
                }
                if (salesinvoice.getVehicleno() != null) {
                    existingSalesinvoice.setVehicleno(salesinvoice.getVehicleno());
                }
                if (salesinvoice.getNextmeter() != null) {
                    existingSalesinvoice.setNextmeter(salesinvoice.getNextmeter());
                }
                if (salesinvoice.getCurrentmeter() != null) {
                    existingSalesinvoice.setCurrentmeter(salesinvoice.getCurrentmeter());
                }
                if (salesinvoice.getRemarks() != null) {
                    existingSalesinvoice.setRemarks(salesinvoice.getRemarks());
                }
                if (salesinvoice.getHasdummybill() != null) {
                    existingSalesinvoice.setHasdummybill(salesinvoice.getHasdummybill());
                }
                if (salesinvoice.getDummybillid() != null) {
                    existingSalesinvoice.setDummybillid(salesinvoice.getDummybillid());
                }
                if (salesinvoice.getDummybillamount() != null) {
                    existingSalesinvoice.setDummybillamount(salesinvoice.getDummybillamount());
                }
                if (salesinvoice.getDummycommision() != null) {
                    existingSalesinvoice.setDummycommision(salesinvoice.getDummycommision());
                }
                if (salesinvoice.getIsserviceinvoice() != null) {
                    existingSalesinvoice.setIsserviceinvoice(salesinvoice.getIsserviceinvoice());
                }
                if (salesinvoice.getNbtamount() != null) {
                    existingSalesinvoice.setNbtamount(salesinvoice.getNbtamount());
                }
                if (salesinvoice.getVatamount() != null) {
                    existingSalesinvoice.setVatamount(salesinvoice.getVatamount());
                }
                if (salesinvoice.getAutocarecompanyid() != null) {
                    existingSalesinvoice.setAutocarecompanyid(salesinvoice.getAutocarecompanyid());
                }
                if (salesinvoice.getIscompanyinvoice() != null) {
                    existingSalesinvoice.setIscompanyinvoice(salesinvoice.getIscompanyinvoice());
                }
                if (salesinvoice.getInvcanceldate() != null) {
                    existingSalesinvoice.setInvcanceldate(salesinvoice.getInvcanceldate());
                }
                if (salesinvoice.getInvcancelby() != null) {
                    existingSalesinvoice.setInvcancelby(salesinvoice.getInvcancelby());
                }
                if (salesinvoice.getIsvatinvoice() != null) {
                    existingSalesinvoice.setIsvatinvoice(salesinvoice.getIsvatinvoice());
                }
                if (salesinvoice.getPaymenttype() != null) {
                    existingSalesinvoice.setPaymenttype(salesinvoice.getPaymenttype());
                }
                if (salesinvoice.getPendingamount() != null) {
                    existingSalesinvoice.setPendingamount(salesinvoice.getPendingamount());
                }
                if (salesinvoice.getAdvancepayment() != null) {
                    existingSalesinvoice.setAdvancepayment(salesinvoice.getAdvancepayment());
                }
                if (salesinvoice.getDiscountcode() != null) {
                    existingSalesinvoice.setDiscountcode(salesinvoice.getDiscountcode());
                }

                return existingSalesinvoice;
            })
            .map(salesinvoiceRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, salesinvoice.getId().toString())
        );
    }

    /**
     * {@code GET  /salesinvoices} : get all the salesinvoices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesinvoices in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Salesinvoice>> getAllSalesinvoices(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Salesinvoices");
        Page<Salesinvoice> page = salesinvoiceRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /salesinvoices/:id} : get the "id" salesinvoice.
     *
     * @param id the id of the salesinvoice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesinvoice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Salesinvoice> getSalesinvoice(@PathVariable("id") Long id) {
        log.debug("REST request to get Salesinvoice : {}", id);
        Optional<Salesinvoice> salesinvoice = salesinvoiceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(salesinvoice);
    }

    /**
     * {@code DELETE  /salesinvoices/:id} : delete the "id" salesinvoice.
     *
     * @param id the id of the salesinvoice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesinvoice(@PathVariable("id") Long id) {
        log.debug("REST request to delete Salesinvoice : {}", id);
        salesinvoiceRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
