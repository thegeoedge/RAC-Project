package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Locationbasedstock;
import com.heavenscode.rac.repository.LocationbasedstockRepository;
import com.heavenscode.rac.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Locationbasedstock}.
 */
@RestController
@RequestMapping("/api/locationbasedstocks")
@Transactional
public class LocationbasedstockResource {

    private final Logger log = LoggerFactory.getLogger(LocationbasedstockResource.class);

    private static final String ENTITY_NAME = "locationbasedstock";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocationbasedstockRepository locationbasedstockRepository;

    public LocationbasedstockResource(LocationbasedstockRepository locationbasedstockRepository) {
        this.locationbasedstockRepository = locationbasedstockRepository;
    }

    /**
     * {@code POST  /locationbasedstocks} : Create a new locationbasedstock.
     *
     * @param locationbasedstock the locationbasedstock to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new locationbasedstock, or with status {@code 400 (Bad Request)} if the locationbasedstock has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Locationbasedstock> createLocationbasedstock(@Valid @RequestBody Locationbasedstock locationbasedstock)
        throws URISyntaxException {
        log.debug("REST request to save Locationbasedstock : {}", locationbasedstock);
        if (locationbasedstock.getId() != null) {
            throw new BadRequestAlertException("A new locationbasedstock cannot already have an ID", ENTITY_NAME, "idexists");
        }
        locationbasedstock = locationbasedstockRepository.save(locationbasedstock);
        return ResponseEntity.created(new URI("/api/locationbasedstocks/" + locationbasedstock.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, locationbasedstock.getId().toString()))
            .body(locationbasedstock);
    }

    /**
     * {@code PUT  /locationbasedstocks/:id} : Updates an existing locationbasedstock.
     *
     * @param id the id of the locationbasedstock to save.
     * @param locationbasedstock the locationbasedstock to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated locationbasedstock,
     * or with status {@code 400 (Bad Request)} if the locationbasedstock is not valid,
     * or with status {@code 500 (Internal Server Error)} if the locationbasedstock couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Locationbasedstock> updateLocationbasedstock(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Locationbasedstock locationbasedstock
    ) throws URISyntaxException {
        log.debug("REST request to update Locationbasedstock : {}, {}", id, locationbasedstock);
        if (locationbasedstock.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, locationbasedstock.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!locationbasedstockRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        locationbasedstock = locationbasedstockRepository.save(locationbasedstock);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, locationbasedstock.getId().toString()))
            .body(locationbasedstock);
    }

    /**
     * {@code PATCH  /locationbasedstocks/:id} : Partial updates given fields of an existing locationbasedstock, field will ignore if it is null
     *
     * @param id the id of the locationbasedstock to save.
     * @param locationbasedstock the locationbasedstock to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated locationbasedstock,
     * or with status {@code 400 (Bad Request)} if the locationbasedstock is not valid,
     * or with status {@code 404 (Not Found)} if the locationbasedstock is not found,
     * or with status {@code 500 (Internal Server Error)} if the locationbasedstock couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Locationbasedstock> partialUpdateLocationbasedstock(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Locationbasedstock locationbasedstock
    ) throws URISyntaxException {
        log.debug("REST request to partial update Locationbasedstock partially : {}, {}", id, locationbasedstock);
        if (locationbasedstock.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, locationbasedstock.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!locationbasedstockRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Locationbasedstock> result = locationbasedstockRepository
            .findById(locationbasedstock.getId())
            .map(existingLocationbasedstock -> {
                if (locationbasedstock.getItemid() != null) {
                    existingLocationbasedstock.setItemid(locationbasedstock.getItemid());
                }
                if (locationbasedstock.getCode() != null) {
                    existingLocationbasedstock.setCode(locationbasedstock.getCode());
                }
                if (locationbasedstock.getName() != null) {
                    existingLocationbasedstock.setName(locationbasedstock.getName());
                }
                if (locationbasedstock.getLocationid() != null) {
                    existingLocationbasedstock.setLocationid(locationbasedstock.getLocationid());
                }
                if (locationbasedstock.getLocationcode() != null) {
                    existingLocationbasedstock.setLocationcode(locationbasedstock.getLocationcode());
                }
                if (locationbasedstock.getAvailablequantity() != null) {
                    existingLocationbasedstock.setAvailablequantity(locationbasedstock.getAvailablequantity());
                }
                if (locationbasedstock.getHasbatches() != null) {
                    existingLocationbasedstock.setHasbatches(locationbasedstock.getHasbatches());
                }
                if (locationbasedstock.getLmu() != null) {
                    existingLocationbasedstock.setLmu(locationbasedstock.getLmu());
                }
                if (locationbasedstock.getLmd() != null) {
                    existingLocationbasedstock.setLmd(locationbasedstock.getLmd());
                }

                return existingLocationbasedstock;
            })
            .map(locationbasedstockRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, locationbasedstock.getId().toString())
        );
    }

    /**
     * {@code GET  /locationbasedstocks} : get all the locationbasedstocks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of locationbasedstocks in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Locationbasedstock>> getAllLocationbasedstocks(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Locationbasedstocks");
        Page<Locationbasedstock> page = locationbasedstockRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /locationbasedstocks/:id} : get the "id" locationbasedstock.
     *
     * @param id the id of the locationbasedstock to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the locationbasedstock, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Locationbasedstock> getLocationbasedstock(@PathVariable("id") Long id) {
        log.debug("REST request to get Locationbasedstock : {}", id);
        Optional<Locationbasedstock> locationbasedstock = locationbasedstockRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(locationbasedstock);
    }

    /**
     * {@code DELETE  /locationbasedstocks/:id} : delete the "id" locationbasedstock.
     *
     * @param id the id of the locationbasedstock to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocationbasedstock(@PathVariable("id") Long id) {
        log.debug("REST request to delete Locationbasedstock : {}", id);
        locationbasedstockRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
