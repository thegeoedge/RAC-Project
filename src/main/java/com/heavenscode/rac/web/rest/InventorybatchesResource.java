package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Inventorybatches;
import com.heavenscode.rac.repository.InventorybatchesRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Inventorybatches}.
 */
@RestController
@RequestMapping("/api/inventorybatches")
@Transactional
public class InventorybatchesResource {

    private final Logger log = LoggerFactory.getLogger(InventorybatchesResource.class);

    private static final String ENTITY_NAME = "inventorybatches";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InventorybatchesRepository inventorybatchesRepository;

    public InventorybatchesResource(InventorybatchesRepository inventorybatchesRepository) {
        this.inventorybatchesRepository = inventorybatchesRepository;
    }

    /**
     * {@code POST  /inventorybatches} : Create a new inventorybatches.
     *
     * @param inventorybatches the inventorybatches to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inventorybatches, or with status {@code 400 (Bad Request)} if the inventorybatches has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Inventorybatches> createInventorybatches(@RequestBody Inventorybatches inventorybatches)
        throws URISyntaxException {
        log.debug("REST request to save Inventorybatches : {}", inventorybatches);
        if (inventorybatches.getId() != null) {
            throw new BadRequestAlertException("A new inventorybatches cannot already have an ID", ENTITY_NAME, "idexists");
        }
        inventorybatches = inventorybatchesRepository.save(inventorybatches);
        return ResponseEntity.created(new URI("/api/inventorybatches/" + inventorybatches.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, inventorybatches.getId().toString()))
            .body(inventorybatches);
    }

    /**
     * {@code PUT  /inventorybatches/:id} : Updates an existing inventorybatches.
     *
     * @param id the id of the inventorybatches to save.
     * @param inventorybatches the inventorybatches to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventorybatches,
     * or with status {@code 400 (Bad Request)} if the inventorybatches is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inventorybatches couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventorybatches> updateInventorybatches(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Inventorybatches inventorybatches
    ) throws URISyntaxException {
        log.debug("REST request to update Inventorybatches : {}, {}", id, inventorybatches);
        if (inventorybatches.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventorybatches.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventorybatchesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        inventorybatches = inventorybatchesRepository.save(inventorybatches);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inventorybatches.getId().toString()))
            .body(inventorybatches);
    }

    /**
     * {@code PATCH  /inventorybatches/:id} : Partial updates given fields of an existing inventorybatches, field will ignore if it is null
     *
     * @param id the id of the inventorybatches to save.
     * @param inventorybatches the inventorybatches to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inventorybatches,
     * or with status {@code 400 (Bad Request)} if the inventorybatches is not valid,
     * or with status {@code 404 (Not Found)} if the inventorybatches is not found,
     * or with status {@code 500 (Internal Server Error)} if the inventorybatches couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Inventorybatches> partialUpdateInventorybatches(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Inventorybatches inventorybatches
    ) throws URISyntaxException {
        log.debug("REST request to partial update Inventorybatches partially : {}, {}", id, inventorybatches);
        if (inventorybatches.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, inventorybatches.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!inventorybatchesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Inventorybatches> result = inventorybatchesRepository
            .findById(inventorybatches.getId())
            .map(existingInventorybatches -> {
                if (inventorybatches.getItemid() != null) {
                    existingInventorybatches.setItemid(inventorybatches.getItemid());
                }
                if (inventorybatches.getCode() != null) {
                    existingInventorybatches.setCode(inventorybatches.getCode());
                }
                if (inventorybatches.getTxdate() != null) {
                    existingInventorybatches.setTxdate(inventorybatches.getTxdate());
                }
                if (inventorybatches.getCost() != null) {
                    existingInventorybatches.setCost(inventorybatches.getCost());
                }
                if (inventorybatches.getPrice() != null) {
                    existingInventorybatches.setPrice(inventorybatches.getPrice());
                }
                if (inventorybatches.getCostwithoutvat() != null) {
                    existingInventorybatches.setCostwithoutvat(inventorybatches.getCostwithoutvat());
                }
                if (inventorybatches.getPricewithoutvat() != null) {
                    existingInventorybatches.setPricewithoutvat(inventorybatches.getPricewithoutvat());
                }
                if (inventorybatches.getNotes() != null) {
                    existingInventorybatches.setNotes(inventorybatches.getNotes());
                }
                if (inventorybatches.getLmu() != null) {
                    existingInventorybatches.setLmu(inventorybatches.getLmu());
                }
                if (inventorybatches.getLmd() != null) {
                    existingInventorybatches.setLmd(inventorybatches.getLmd());
                }
                if (inventorybatches.getLineid() != null) {
                    existingInventorybatches.setLineid(inventorybatches.getLineid());
                }
                if (inventorybatches.getManufacturedate() != null) {
                    existingInventorybatches.setManufacturedate(inventorybatches.getManufacturedate());
                }
                if (inventorybatches.getExpiredate() != null) {
                    existingInventorybatches.setExpiredate(inventorybatches.getExpiredate());
                }
                if (inventorybatches.getQuantity() != null) {
                    existingInventorybatches.setQuantity(inventorybatches.getQuantity());
                }
                if (inventorybatches.getAddeddate() != null) {
                    existingInventorybatches.setAddeddate(inventorybatches.getAddeddate());
                }
                if (inventorybatches.getCosttotal() != null) {
                    existingInventorybatches.setCosttotal(inventorybatches.getCosttotal());
                }
                if (inventorybatches.getReturnprice() != null) {
                    existingInventorybatches.setReturnprice(inventorybatches.getReturnprice());
                }

                return existingInventorybatches;
            })
            .map(inventorybatchesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inventorybatches.getId().toString())
        );
    }

    /**
     * {@code GET  /inventorybatches} : get all the inventorybatches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inventorybatches in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Inventorybatches>> getAllInventorybatches(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Inventorybatches");
        Page<Inventorybatches> page = inventorybatchesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inventorybatches/:id} : get the "id" inventorybatches.
     *
     * @param id the id of the inventorybatches to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inventorybatches, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventorybatches> getInventorybatches(@PathVariable("id") Long id) {
        log.debug("REST request to get Inventorybatches : {}", id);
        Optional<Inventorybatches> inventorybatches = inventorybatchesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inventorybatches);
    }

    /**
     * {@code DELETE  /inventorybatches/:id} : delete the "id" inventorybatches.
     *
     * @param id the id of the inventorybatches to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventorybatches(@PathVariable("id") Long id) {
        log.debug("REST request to delete Inventorybatches : {}", id);
        inventorybatchesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
