package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Receipt;
import com.heavenscode.rac.repository.ReceiptRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Receipt}.
 */
@RestController
@RequestMapping("/api/receipts")
@Transactional
public class ReceiptResource {

    private final Logger log = LoggerFactory.getLogger(ReceiptResource.class);

    private static final String ENTITY_NAME = "receipt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceiptRepository receiptRepository;

    public ReceiptResource(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    /**
     * {@code POST  /receipts} : Create a new receipt.
     *
     * @param receipt the receipt to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receipt, or with status {@code 400 (Bad Request)} if the receipt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Receipt> createReceipt(@RequestBody Receipt receipt) throws URISyntaxException {
        log.debug("REST request to save Receipt : {}", receipt);
        if (receipt.getId() != null) {
            throw new BadRequestAlertException("A new receipt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        receipt = receiptRepository.save(receipt);
        return ResponseEntity.created(new URI("/api/receipts/" + receipt.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, receipt.getId().toString()))
            .body(receipt);
    }

    /**
     * {@code PUT  /receipts/:id} : Updates an existing receipt.
     *
     * @param id the id of the receipt to save.
     * @param receipt the receipt to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receipt,
     * or with status {@code 400 (Bad Request)} if the receipt is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receipt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Receipt> updateReceipt(@PathVariable(value = "id", required = false) final Long id, @RequestBody Receipt receipt)
        throws URISyntaxException {
        log.debug("REST request to update Receipt : {}, {}", id, receipt);
        if (receipt.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, receipt.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!receiptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        receipt = receiptRepository.save(receipt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, receipt.getId().toString()))
            .body(receipt);
    }

    /**
     * {@code PATCH  /receipts/:id} : Partial updates given fields of an existing receipt, field will ignore if it is null
     *
     * @param id the id of the receipt to save.
     * @param receipt the receipt to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receipt,
     * or with status {@code 400 (Bad Request)} if the receipt is not valid,
     * or with status {@code 404 (Not Found)} if the receipt is not found,
     * or with status {@code 500 (Internal Server Error)} if the receipt couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Receipt> partialUpdateReceipt(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Receipt receipt
    ) throws URISyntaxException {
        log.debug("REST request to partial update Receipt partially : {}, {}", id, receipt);
        if (receipt.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, receipt.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!receiptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Receipt> result = receiptRepository
            .findById(receipt.getId())
            .map(existingReceipt -> {
                if (receipt.getCode() != null) {
                    existingReceipt.setCode(receipt.getCode());
                }
                if (receipt.getReceiptdate() != null) {
                    existingReceipt.setReceiptdate(receipt.getReceiptdate());
                }
                if (receipt.getCustomername() != null) {
                    existingReceipt.setCustomername(receipt.getCustomername());
                }
                if (receipt.getCustomeraddress() != null) {
                    existingReceipt.setCustomeraddress(receipt.getCustomeraddress());
                }
                if (receipt.getTotalamount() != null) {
                    existingReceipt.setTotalamount(receipt.getTotalamount());
                }
                if (receipt.getTotalamountinword() != null) {
                    existingReceipt.setTotalamountinword(receipt.getTotalamountinword());
                }
                if (receipt.getComments() != null) {
                    existingReceipt.setComments(receipt.getComments());
                }
                if (receipt.getLmu() != null) {
                    existingReceipt.setLmu(receipt.getLmu());
                }
                if (receipt.getLmd() != null) {
                    existingReceipt.setLmd(receipt.getLmd());
                }
                if (receipt.getTermid() != null) {
                    existingReceipt.setTermid(receipt.getTermid());
                }
                if (receipt.getTerm() != null) {
                    existingReceipt.setTerm(receipt.getTerm());
                }
                if (receipt.getDate() != null) {
                    existingReceipt.setDate(receipt.getDate());
                }
                if (receipt.getAmount() != null) {
                    existingReceipt.setAmount(receipt.getAmount());
                }
                if (receipt.getCheckdate() != null) {
                    existingReceipt.setCheckdate(receipt.getCheckdate());
                }
                if (receipt.getCheckno() != null) {
                    existingReceipt.setCheckno(receipt.getCheckno());
                }
                if (receipt.getBank() != null) {
                    existingReceipt.setBank(receipt.getBank());
                }
                if (receipt.getCustomerid() != null) {
                    existingReceipt.setCustomerid(receipt.getCustomerid());
                }
                if (receipt.getIsactive() != null) {
                    existingReceipt.setIsactive(receipt.getIsactive());
                }
                if (receipt.getDeposited() != null) {
                    existingReceipt.setDeposited(receipt.getDeposited());
                }
                if (receipt.getCreatedby() != null) {
                    existingReceipt.setCreatedby(receipt.getCreatedby());
                }

                return existingReceipt;
            })
            .map(receiptRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, receipt.getId().toString())
        );
    }

    /**
     * {@code GET  /receipts} : get all the receipts.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receipts in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Receipt>> getAllReceipts(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Receipts");
        Page<Receipt> page = receiptRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /receipts/:id} : get the "id" receipt.
     *
     * @param id the id of the receipt to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receipt, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Receipt> getReceipt(@PathVariable("id") Long id) {
        log.debug("REST request to get Receipt : {}", id);
        Optional<Receipt> receipt = receiptRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(receipt);
    }

    /**
     * {@code DELETE  /receipts/:id} : delete the "id" receipt.
     *
     * @param id the id of the receipt to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceipt(@PathVariable("id") Long id) {
        log.debug("REST request to delete Receipt : {}", id);
        receiptRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
