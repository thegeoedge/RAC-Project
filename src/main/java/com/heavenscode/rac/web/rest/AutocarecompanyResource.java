package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocarecompany;
import com.heavenscode.rac.repository.AutocarecompanyRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocarecompany}.
 */
@RestController
@RequestMapping("/api/autocarecompanies")
@Transactional
public class AutocarecompanyResource {

    private final Logger log = LoggerFactory.getLogger(AutocarecompanyResource.class);

    private static final String ENTITY_NAME = "autocarecompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocarecompanyRepository autocarecompanyRepository;

    public AutocarecompanyResource(AutocarecompanyRepository autocarecompanyRepository) {
        this.autocarecompanyRepository = autocarecompanyRepository;
    }

    /**
     * {@code POST  /autocarecompanies} : Create a new autocarecompany.
     *
     * @param autocarecompany the autocarecompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocarecompany, or with status {@code 400 (Bad Request)} if the autocarecompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocarecompany> createAutocarecompany(@RequestBody Autocarecompany autocarecompany) throws URISyntaxException {
        log.debug("REST request to save Autocarecompany : {}", autocarecompany);
        if (autocarecompany.getId() != null) {
            throw new BadRequestAlertException("A new autocarecompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocarecompany = autocarecompanyRepository.save(autocarecompany);
        return ResponseEntity.created(new URI("/api/autocarecompanies/" + autocarecompany.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocarecompany.getId().toString()))
            .body(autocarecompany);
    }

    /**
     * {@code PUT  /autocarecompanies/:id} : Updates an existing autocarecompany.
     *
     * @param id the id of the autocarecompany to save.
     * @param autocarecompany the autocarecompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarecompany,
     * or with status {@code 400 (Bad Request)} if the autocarecompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocarecompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocarecompany> updateAutocarecompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarecompany autocarecompany
    ) throws URISyntaxException {
        log.debug("REST request to update Autocarecompany : {}, {}", id, autocarecompany);
        if (autocarecompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarecompany.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarecompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocarecompany = autocarecompanyRepository.save(autocarecompany);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarecompany.getId().toString()))
            .body(autocarecompany);
    }

    /**
     * {@code PATCH  /autocarecompanies/:id} : Partial updates given fields of an existing autocarecompany, field will ignore if it is null
     *
     * @param id the id of the autocarecompany to save.
     * @param autocarecompany the autocarecompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarecompany,
     * or with status {@code 400 (Bad Request)} if the autocarecompany is not valid,
     * or with status {@code 404 (Not Found)} if the autocarecompany is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocarecompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocarecompany> partialUpdateAutocarecompany(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarecompany autocarecompany
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocarecompany partially : {}, {}", id, autocarecompany);
        if (autocarecompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarecompany.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarecompanyRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocarecompany> result = autocarecompanyRepository
            .findById(autocarecompany.getId())
            .map(existingAutocarecompany -> {
                if (autocarecompany.getName() != null) {
                    existingAutocarecompany.setName(autocarecompany.getName());
                }
                if (autocarecompany.getAddress() != null) {
                    existingAutocarecompany.setAddress(autocarecompany.getAddress());
                }
                if (autocarecompany.getServicephone() != null) {
                    existingAutocarecompany.setServicephone(autocarecompany.getServicephone());
                }
                if (autocarecompany.getSparepartphone() != null) {
                    existingAutocarecompany.setSparepartphone(autocarecompany.getSparepartphone());
                }
                if (autocarecompany.getBodypaint() != null) {
                    existingAutocarecompany.setBodypaint(autocarecompany.getBodypaint());
                }
                if (autocarecompany.getGeneralphone() != null) {
                    existingAutocarecompany.setGeneralphone(autocarecompany.getGeneralphone());
                }
                if (autocarecompany.getFax() != null) {
                    existingAutocarecompany.setFax(autocarecompany.getFax());
                }
                if (autocarecompany.getEmail() != null) {
                    existingAutocarecompany.setEmail(autocarecompany.getEmail());
                }
                if (autocarecompany.getDescription() != null) {
                    existingAutocarecompany.setDescription(autocarecompany.getDescription());
                }
                if (autocarecompany.getLmu() != null) {
                    existingAutocarecompany.setLmu(autocarecompany.getLmu());
                }
                if (autocarecompany.getLmd() != null) {
                    existingAutocarecompany.setLmd(autocarecompany.getLmd());
                }
                if (autocarecompany.getVatregnumber() != null) {
                    existingAutocarecompany.setVatregnumber(autocarecompany.getVatregnumber());
                }
                if (autocarecompany.getTinnumber() != null) {
                    existingAutocarecompany.setTinnumber(autocarecompany.getTinnumber());
                }
                if (autocarecompany.getAccountcode() != null) {
                    existingAutocarecompany.setAccountcode(autocarecompany.getAccountcode());
                }
                if (autocarecompany.getAccountid() != null) {
                    existingAutocarecompany.setAccountid(autocarecompany.getAccountid());
                }

                return existingAutocarecompany;
            })
            .map(autocarecompanyRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarecompany.getId().toString())
        );
    }

    /**
     * {@code GET  /autocarecompanies} : get all the autocarecompanies.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocarecompanies in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocarecompany>> getAllAutocarecompanies(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Autocarecompanies");
        Page<Autocarecompany> page = autocarecompanyRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocarecompanies/:id} : get the "id" autocarecompany.
     *
     * @param id the id of the autocarecompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocarecompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocarecompany> getAutocarecompany(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocarecompany : {}", id);
        Optional<Autocarecompany> autocarecompany = autocarecompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocarecompany);
    }

    /**
     * {@code DELETE  /autocarecompanies/:id} : delete the "id" autocarecompany.
     *
     * @param id the id of the autocarecompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocarecompany(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocarecompany : {}", id);
        autocarecompanyRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
