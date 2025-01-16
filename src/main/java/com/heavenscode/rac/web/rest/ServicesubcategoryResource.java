package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Servicesubcategory;
import com.heavenscode.rac.repository.ServicesubcategoryRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Servicesubcategory}.
 */
@RestController
@RequestMapping("/api/servicesubcategories")
@Transactional
public class ServicesubcategoryResource {

    private final Logger log = LoggerFactory.getLogger(ServicesubcategoryResource.class);

    private static final String ENTITY_NAME = "servicesubcategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServicesubcategoryRepository servicesubcategoryRepository;

    public ServicesubcategoryResource(ServicesubcategoryRepository servicesubcategoryRepository) {
        this.servicesubcategoryRepository = servicesubcategoryRepository;
    }

    /**
     * {@code POST  /servicesubcategories} : Create a new servicesubcategory.
     *
     * @param servicesubcategory the servicesubcategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new servicesubcategory, or with status {@code 400 (Bad Request)} if the servicesubcategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Servicesubcategory> createServicesubcategory(@RequestBody Servicesubcategory servicesubcategory)
        throws URISyntaxException {
        log.debug("REST request to save Servicesubcategory : {}", servicesubcategory);
        if (servicesubcategory.getId() != null) {
            throw new BadRequestAlertException("A new servicesubcategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        servicesubcategory = servicesubcategoryRepository.save(servicesubcategory);
        return ResponseEntity.created(new URI("/api/servicesubcategories/" + servicesubcategory.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, servicesubcategory.getId().toString()))
            .body(servicesubcategory);
    }

    /**
     * {@code PUT  /servicesubcategories/:id} : Updates an existing servicesubcategory.
     *
     * @param id the id of the servicesubcategory to save.
     * @param servicesubcategory the servicesubcategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicesubcategory,
     * or with status {@code 400 (Bad Request)} if the servicesubcategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the servicesubcategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Servicesubcategory> updateServicesubcategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Servicesubcategory servicesubcategory
    ) throws URISyntaxException {
        log.debug("REST request to update Servicesubcategory : {}, {}", id, servicesubcategory);
        if (servicesubcategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servicesubcategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servicesubcategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        servicesubcategory = servicesubcategoryRepository.save(servicesubcategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servicesubcategory.getId().toString()))
            .body(servicesubcategory);
    }

    /**
     * {@code PATCH  /servicesubcategories/:id} : Partial updates given fields of an existing servicesubcategory, field will ignore if it is null
     *
     * @param id the id of the servicesubcategory to save.
     * @param servicesubcategory the servicesubcategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated servicesubcategory,
     * or with status {@code 400 (Bad Request)} if the servicesubcategory is not valid,
     * or with status {@code 404 (Not Found)} if the servicesubcategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the servicesubcategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Servicesubcategory> partialUpdateServicesubcategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Servicesubcategory servicesubcategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update Servicesubcategory partially : {}, {}", id, servicesubcategory);
        if (servicesubcategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, servicesubcategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!servicesubcategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Servicesubcategory> result = servicesubcategoryRepository
            .findById(servicesubcategory.getId())
            .map(existingServicesubcategory -> {
                if (servicesubcategory.getName() != null) {
                    existingServicesubcategory.setName(servicesubcategory.getName());
                }
                if (servicesubcategory.getDescription() != null) {
                    existingServicesubcategory.setDescription(servicesubcategory.getDescription());
                }
                if (servicesubcategory.getMainid() != null) {
                    existingServicesubcategory.setMainid(servicesubcategory.getMainid());
                }
                if (servicesubcategory.getMainname() != null) {
                    existingServicesubcategory.setMainname(servicesubcategory.getMainname());
                }
                if (servicesubcategory.getLmu() != null) {
                    existingServicesubcategory.setLmu(servicesubcategory.getLmu());
                }
                if (servicesubcategory.getLmd() != null) {
                    existingServicesubcategory.setLmd(servicesubcategory.getLmd());
                }
                if (servicesubcategory.getIsactive() != null) {
                    existingServicesubcategory.setIsactive(servicesubcategory.getIsactive());
                }

                return existingServicesubcategory;
            })
            .map(servicesubcategoryRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, servicesubcategory.getId().toString())
        );
    }

    /**
     * {@code GET  /servicesubcategories} : get all the servicesubcategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of servicesubcategories in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Servicesubcategory>> getAllServicesubcategories(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Servicesubcategories");
        Page<Servicesubcategory> page = servicesubcategoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /servicesubcategories/:id} : get the "id" servicesubcategory.
     *
     * @param id the id of the servicesubcategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the servicesubcategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Servicesubcategory> getServicesubcategory(@PathVariable("id") Long id) {
        log.debug("REST request to get Servicesubcategory : {}", id);
        Optional<Servicesubcategory> servicesubcategory = servicesubcategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(servicesubcategory);
    }

    /**
     * {@code DELETE  /servicesubcategories/:id} : delete the "id" servicesubcategory.
     *
     * @param id the id of the servicesubcategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServicesubcategory(@PathVariable("id") Long id) {
        log.debug("REST request to delete Servicesubcategory : {}", id);
        servicesubcategoryRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
