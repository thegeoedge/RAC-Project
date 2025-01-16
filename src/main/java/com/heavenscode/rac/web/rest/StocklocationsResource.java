package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Stocklocations;
import com.heavenscode.rac.repository.StocklocationsRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Stocklocations}.
 */
@RestController
@RequestMapping("/api/stocklocations")
@Transactional
public class StocklocationsResource {

    private final Logger log = LoggerFactory.getLogger(StocklocationsResource.class);

    private static final String ENTITY_NAME = "stocklocations";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StocklocationsRepository stocklocationsRepository;

    public StocklocationsResource(StocklocationsRepository stocklocationsRepository) {
        this.stocklocationsRepository = stocklocationsRepository;
    }

    /**
     * {@code POST  /stocklocations} : Create a new stocklocations.
     *
     * @param stocklocations the stocklocations to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stocklocations, or with status {@code 400 (Bad Request)} if the stocklocations has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Stocklocations> createStocklocations(@RequestBody Stocklocations stocklocations) throws URISyntaxException {
        log.debug("REST request to save Stocklocations : {}", stocklocations);
        if (stocklocations.getId() != null) {
            throw new BadRequestAlertException("A new stocklocations cannot already have an ID", ENTITY_NAME, "idexists");
        }
        stocklocations = stocklocationsRepository.save(stocklocations);
        return ResponseEntity.created(new URI("/api/stocklocations/" + stocklocations.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, stocklocations.getId().toString()))
            .body(stocklocations);
    }

    /**
     * {@code PUT  /stocklocations/:id} : Updates an existing stocklocations.
     *
     * @param id the id of the stocklocations to save.
     * @param stocklocations the stocklocations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stocklocations,
     * or with status {@code 400 (Bad Request)} if the stocklocations is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stocklocations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Stocklocations> updateStocklocations(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Stocklocations stocklocations
    ) throws URISyntaxException {
        log.debug("REST request to update Stocklocations : {}, {}", id, stocklocations);
        if (stocklocations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stocklocations.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stocklocationsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        stocklocations = stocklocationsRepository.save(stocklocations);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stocklocations.getId().toString()))
            .body(stocklocations);
    }

    /**
     * {@code PATCH  /stocklocations/:id} : Partial updates given fields of an existing stocklocations, field will ignore if it is null
     *
     * @param id the id of the stocklocations to save.
     * @param stocklocations the stocklocations to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stocklocations,
     * or with status {@code 400 (Bad Request)} if the stocklocations is not valid,
     * or with status {@code 404 (Not Found)} if the stocklocations is not found,
     * or with status {@code 500 (Internal Server Error)} if the stocklocations couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Stocklocations> partialUpdateStocklocations(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Stocklocations stocklocations
    ) throws URISyntaxException {
        log.debug("REST request to partial update Stocklocations partially : {}, {}", id, stocklocations);
        if (stocklocations.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stocklocations.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stocklocationsRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Stocklocations> result = stocklocationsRepository
            .findById(stocklocations.getId())
            .map(existingStocklocations -> {
                if (stocklocations.getLocationname() != null) {
                    existingStocklocations.setLocationname(stocklocations.getLocationname());
                }
                if (stocklocations.getLocationcode() != null) {
                    existingStocklocations.setLocationcode(stocklocations.getLocationcode());
                }
                if (stocklocations.getDescription() != null) {
                    existingStocklocations.setDescription(stocklocations.getDescription());
                }

                return existingStocklocations;
            })
            .map(stocklocationsRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, stocklocations.getId().toString())
        );
    }

    /**
     * {@code GET  /stocklocations} : get all the stocklocations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stocklocations in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Stocklocations>> getAllStocklocations(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Stocklocations");
        Page<Stocklocations> page = stocklocationsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /stocklocations/:id} : get the "id" stocklocations.
     *
     * @param id the id of the stocklocations to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stocklocations, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Stocklocations> getStocklocations(@PathVariable("id") Long id) {
        log.debug("REST request to get Stocklocations : {}", id);
        Optional<Stocklocations> stocklocations = stocklocationsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(stocklocations);
    }

    /**
     * {@code DELETE  /stocklocations/:id} : delete the "id" stocklocations.
     *
     * @param id the id of the stocklocations to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStocklocations(@PathVariable("id") Long id) {
        log.debug("REST request to delete Stocklocations : {}", id);
        stocklocationsRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
