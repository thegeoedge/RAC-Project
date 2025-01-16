package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocarehoist;
import com.heavenscode.rac.repository.AutocarehoistRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocarehoist}.
 */
@RestController
@RequestMapping("/api/autocarehoists")
@Transactional
public class AutocarehoistResource {

    private final Logger log = LoggerFactory.getLogger(AutocarehoistResource.class);

    private static final String ENTITY_NAME = "autocarehoist";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocarehoistRepository autocarehoistRepository;

    public AutocarehoistResource(AutocarehoistRepository autocarehoistRepository) {
        this.autocarehoistRepository = autocarehoistRepository;
    }

    /**
     * {@code POST  /autocarehoists} : Create a new autocarehoist.
     *
     * @param autocarehoist the autocarehoist to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocarehoist, or with status {@code 400 (Bad Request)} if the autocarehoist has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocarehoist> createAutocarehoist(@RequestBody Autocarehoist autocarehoist) throws URISyntaxException {
        log.debug("REST request to save Autocarehoist : {}", autocarehoist);
        if (autocarehoist.getId() != null) {
            throw new BadRequestAlertException("A new autocarehoist cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocarehoist = autocarehoistRepository.save(autocarehoist);
        return ResponseEntity.created(new URI("/api/autocarehoists/" + autocarehoist.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocarehoist.getId().toString()))
            .body(autocarehoist);
    }

    /**
     * {@code PUT  /autocarehoists/:id} : Updates an existing autocarehoist.
     *
     * @param id the id of the autocarehoist to save.
     * @param autocarehoist the autocarehoist to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarehoist,
     * or with status {@code 400 (Bad Request)} if the autocarehoist is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocarehoist couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocarehoist> updateAutocarehoist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarehoist autocarehoist
    ) throws URISyntaxException {
        log.debug("REST request to update Autocarehoist : {}, {}", id, autocarehoist);
        if (autocarehoist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarehoist.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarehoistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocarehoist = autocarehoistRepository.save(autocarehoist);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarehoist.getId().toString()))
            .body(autocarehoist);
    }

    /**
     * {@code PATCH  /autocarehoists/:id} : Partial updates given fields of an existing autocarehoist, field will ignore if it is null
     *
     * @param id the id of the autocarehoist to save.
     * @param autocarehoist the autocarehoist to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarehoist,
     * or with status {@code 400 (Bad Request)} if the autocarehoist is not valid,
     * or with status {@code 404 (Not Found)} if the autocarehoist is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocarehoist couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocarehoist> partialUpdateAutocarehoist(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarehoist autocarehoist
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocarehoist partially : {}, {}", id, autocarehoist);
        if (autocarehoist.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarehoist.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarehoistRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocarehoist> result = autocarehoistRepository
            .findById(autocarehoist.getId())
            .map(existingAutocarehoist -> {
                if (autocarehoist.getHoistname() != null) {
                    existingAutocarehoist.setHoistname(autocarehoist.getHoistname());
                }
                if (autocarehoist.getHoistcode() != null) {
                    existingAutocarehoist.setHoistcode(autocarehoist.getHoistcode());
                }
                if (autocarehoist.getHoisttypeid() != null) {
                    existingAutocarehoist.setHoisttypeid(autocarehoist.getHoisttypeid());
                }
                if (autocarehoist.getHoisttypename() != null) {
                    existingAutocarehoist.setHoisttypename(autocarehoist.getHoisttypename());
                }
                if (autocarehoist.getLmu() != null) {
                    existingAutocarehoist.setLmu(autocarehoist.getLmu());
                }
                if (autocarehoist.getLmd() != null) {
                    existingAutocarehoist.setLmd(autocarehoist.getLmd());
                }

                return existingAutocarehoist;
            })
            .map(autocarehoistRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarehoist.getId().toString())
        );
    }

    /**
     * {@code GET  /autocarehoists} : get all the autocarehoists.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocarehoists in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocarehoist>> getAllAutocarehoists(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Autocarehoists");
        Page<Autocarehoist> page = autocarehoistRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocarehoists/:id} : get the "id" autocarehoist.
     *
     * @param id the id of the autocarehoist to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocarehoist, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocarehoist> getAutocarehoist(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocarehoist : {}", id);
        Optional<Autocarehoist> autocarehoist = autocarehoistRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocarehoist);
    }

    /**
     * {@code DELETE  /autocarehoists/:id} : delete the "id" autocarehoist.
     *
     * @param id the id of the autocarehoist to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocarehoist(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocarehoist : {}", id);
        autocarehoistRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
