package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Commonserviceoption;
import com.heavenscode.rac.repository.CommonserviceoptionRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Commonserviceoption}.
 */
@RestController
@RequestMapping("/api/commonserviceoptions")
@Transactional
public class CommonserviceoptionResource {

    private final Logger log = LoggerFactory.getLogger(CommonserviceoptionResource.class);

    private static final String ENTITY_NAME = "commonserviceoption";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommonserviceoptionRepository commonserviceoptionRepository;

    public CommonserviceoptionResource(CommonserviceoptionRepository commonserviceoptionRepository) {
        this.commonserviceoptionRepository = commonserviceoptionRepository;
    }

    /**
     * {@code POST  /commonserviceoptions} : Create a new commonserviceoption.
     *
     * @param commonserviceoption the commonserviceoption to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new commonserviceoption, or with status {@code 400 (Bad Request)} if the commonserviceoption has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Commonserviceoption> createCommonserviceoption(@RequestBody Commonserviceoption commonserviceoption)
        throws URISyntaxException {
        log.debug("REST request to save Commonserviceoption : {}", commonserviceoption);
        if (commonserviceoption.getId() != null) {
            throw new BadRequestAlertException("A new commonserviceoption cannot already have an ID", ENTITY_NAME, "idexists");
        }
        commonserviceoption = commonserviceoptionRepository.save(commonserviceoption);
        return ResponseEntity.created(new URI("/api/commonserviceoptions/" + commonserviceoption.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, commonserviceoption.getId().toString()))
            .body(commonserviceoption);
    }

    /**
     * {@code PUT  /commonserviceoptions/:id} : Updates an existing commonserviceoption.
     *
     * @param id the id of the commonserviceoption to save.
     * @param commonserviceoption the commonserviceoption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commonserviceoption,
     * or with status {@code 400 (Bad Request)} if the commonserviceoption is not valid,
     * or with status {@code 500 (Internal Server Error)} if the commonserviceoption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Commonserviceoption> updateCommonserviceoption(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Commonserviceoption commonserviceoption
    ) throws URISyntaxException {
        log.debug("REST request to update Commonserviceoption : {}, {}", id, commonserviceoption);
        if (commonserviceoption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commonserviceoption.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commonserviceoptionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        commonserviceoption = commonserviceoptionRepository.save(commonserviceoption);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commonserviceoption.getId().toString()))
            .body(commonserviceoption);
    }

    /**
     * {@code PATCH  /commonserviceoptions/:id} : Partial updates given fields of an existing commonserviceoption, field will ignore if it is null
     *
     * @param id the id of the commonserviceoption to save.
     * @param commonserviceoption the commonserviceoption to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated commonserviceoption,
     * or with status {@code 400 (Bad Request)} if the commonserviceoption is not valid,
     * or with status {@code 404 (Not Found)} if the commonserviceoption is not found,
     * or with status {@code 500 (Internal Server Error)} if the commonserviceoption couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Commonserviceoption> partialUpdateCommonserviceoption(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Commonserviceoption commonserviceoption
    ) throws URISyntaxException {
        log.debug("REST request to partial update Commonserviceoption partially : {}, {}", id, commonserviceoption);
        if (commonserviceoption.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, commonserviceoption.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commonserviceoptionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Commonserviceoption> result = commonserviceoptionRepository
            .findById(commonserviceoption.getId())
            .map(existingCommonserviceoption -> {
                if (commonserviceoption.getMainid() != null) {
                    existingCommonserviceoption.setMainid(commonserviceoption.getMainid());
                }
                if (commonserviceoption.getCode() != null) {
                    existingCommonserviceoption.setCode(commonserviceoption.getCode());
                }
                if (commonserviceoption.getName() != null) {
                    existingCommonserviceoption.setName(commonserviceoption.getName());
                }
                if (commonserviceoption.getDescription() != null) {
                    existingCommonserviceoption.setDescription(commonserviceoption.getDescription());
                }
                if (commonserviceoption.getValue() != null) {
                    existingCommonserviceoption.setValue(commonserviceoption.getValue());
                }
                if (commonserviceoption.getIsactive() != null) {
                    existingCommonserviceoption.setIsactive(commonserviceoption.getIsactive());
                }
                if (commonserviceoption.getLmd() != null) {
                    existingCommonserviceoption.setLmd(commonserviceoption.getLmd());
                }
                if (commonserviceoption.getLmu() != null) {
                    existingCommonserviceoption.setLmu(commonserviceoption.getLmu());
                }

                return existingCommonserviceoption;
            })
            .map(commonserviceoptionRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, commonserviceoption.getId().toString())
        );
    }

    /**
     * {@code GET  /commonserviceoptions} : get all the commonserviceoptions.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of commonserviceoptions in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Commonserviceoption>> getAllCommonserviceoptions(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of Commonserviceoptions");
        Page<Commonserviceoption> page = commonserviceoptionRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /commonserviceoptions/:id} : get the "id" commonserviceoption.
     *
     * @param id the id of the commonserviceoption to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the commonserviceoption, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Commonserviceoption> getCommonserviceoption(@PathVariable("id") Long id) {
        log.debug("REST request to get Commonserviceoption : {}", id);
        Optional<Commonserviceoption> commonserviceoption = commonserviceoptionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(commonserviceoption);
    }

    /**
     * {@code DELETE  /commonserviceoptions/:id} : delete the "id" commonserviceoption.
     *
     * @param id the id of the commonserviceoption to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommonserviceoption(@PathVariable("id") Long id) {
        log.debug("REST request to delete Commonserviceoption : {}", id);
        commonserviceoptionRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
