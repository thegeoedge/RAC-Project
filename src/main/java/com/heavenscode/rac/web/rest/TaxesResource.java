package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Taxes;
import com.heavenscode.rac.repository.TaxesRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Taxes}.
 */
@RestController
@RequestMapping("/api/taxes")
@Transactional
public class TaxesResource {

    private final Logger log = LoggerFactory.getLogger(TaxesResource.class);

    private static final String ENTITY_NAME = "taxes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxesRepository taxesRepository;

    public TaxesResource(TaxesRepository taxesRepository) {
        this.taxesRepository = taxesRepository;
    }

    /**
     * {@code POST  /taxes} : Create a new taxes.
     *
     * @param taxes the taxes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxes, or with status {@code 400 (Bad Request)} if the taxes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Taxes> createTaxes(@RequestBody Taxes taxes) throws URISyntaxException {
        log.debug("REST request to save Taxes : {}", taxes);
        if (taxes.getId() != null) {
            throw new BadRequestAlertException("A new taxes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        taxes = taxesRepository.save(taxes);
        return ResponseEntity.created(new URI("/api/taxes/" + taxes.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, taxes.getId().toString()))
            .body(taxes);
    }

    /**
     * {@code PUT  /taxes/:id} : Updates an existing taxes.
     *
     * @param id the id of the taxes to save.
     * @param taxes the taxes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxes,
     * or with status {@code 400 (Bad Request)} if the taxes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Taxes> updateTaxes(@PathVariable(value = "id", required = false) final Long id, @RequestBody Taxes taxes)
        throws URISyntaxException {
        log.debug("REST request to update Taxes : {}, {}", id, taxes);
        if (taxes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taxes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        taxes = taxesRepository.save(taxes);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxes.getId().toString()))
            .body(taxes);
    }

    /**
     * {@code PATCH  /taxes/:id} : Partial updates given fields of an existing taxes, field will ignore if it is null
     *
     * @param id the id of the taxes to save.
     * @param taxes the taxes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxes,
     * or with status {@code 400 (Bad Request)} if the taxes is not valid,
     * or with status {@code 404 (Not Found)} if the taxes is not found,
     * or with status {@code 500 (Internal Server Error)} if the taxes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Taxes> partialUpdateTaxes(@PathVariable(value = "id", required = false) final Long id, @RequestBody Taxes taxes)
        throws URISyntaxException {
        log.debug("REST request to partial update Taxes partially : {}, {}", id, taxes);
        if (taxes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, taxes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!taxesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Taxes> result = taxesRepository
            .findById(taxes.getId())
            .map(existingTaxes -> {
                if (taxes.getCode() != null) {
                    existingTaxes.setCode(taxes.getCode());
                }
                if (taxes.getDescription() != null) {
                    existingTaxes.setDescription(taxes.getDescription());
                }
                if (taxes.getEffectivefrom() != null) {
                    existingTaxes.setEffectivefrom(taxes.getEffectivefrom());
                }
                if (taxes.getEffectiveto() != null) {
                    existingTaxes.setEffectiveto(taxes.getEffectiveto());
                }
                if (taxes.getPercentage() != null) {
                    existingTaxes.setPercentage(taxes.getPercentage());
                }
                if (taxes.getFixedamount() != null) {
                    existingTaxes.setFixedamount(taxes.getFixedamount());
                }
                if (taxes.getIsmanual() != null) {
                    existingTaxes.setIsmanual(taxes.getIsmanual());
                }
                if (taxes.getIsactive() != null) {
                    existingTaxes.setIsactive(taxes.getIsactive());
                }
                if (taxes.getLmu() != null) {
                    existingTaxes.setLmu(taxes.getLmu());
                }
                if (taxes.getLmd() != null) {
                    existingTaxes.setLmd(taxes.getLmd());
                }

                return existingTaxes;
            })
            .map(taxesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, taxes.getId().toString())
        );
    }

    /**
     * {@code GET  /taxes} : get all the taxes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxes in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Taxes>> getAllTaxes(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Taxes");
        Page<Taxes> page = taxesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /taxes/:id} : get the "id" taxes.
     *
     * @param id the id of the taxes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Taxes> getTaxes(@PathVariable("id") Long id) {
        log.debug("REST request to get Taxes : {}", id);
        Optional<Taxes> taxes = taxesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(taxes);
    }

    /**
     * {@code DELETE  /taxes/:id} : delete the "id" taxes.
     *
     * @param id the id of the taxes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaxes(@PathVariable("id") Long id) {
        log.debug("REST request to delete Taxes : {}", id);
        taxesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
