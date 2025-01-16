package com.heavenscode.rac.web.rest;

import com.heavenscode.rac.domain.Autocarejob;
import com.heavenscode.rac.repository.AutocarejobRepository;
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
 * REST controller for managing {@link com.heavenscode.rac.domain.Autocarejob}.
 */
@RestController
@RequestMapping("/api/autocarejobs")
@Transactional
public class AutocarejobResource {

    private final Logger log = LoggerFactory.getLogger(AutocarejobResource.class);

    private static final String ENTITY_NAME = "autocarejob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutocarejobRepository autocarejobRepository;

    public AutocarejobResource(AutocarejobRepository autocarejobRepository) {
        this.autocarejobRepository = autocarejobRepository;
    }

    /**
     * {@code POST  /autocarejobs} : Create a new autocarejob.
     *
     * @param autocarejob the autocarejob to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new autocarejob, or with status {@code 400 (Bad Request)} if the autocarejob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Autocarejob> createAutocarejob(@RequestBody Autocarejob autocarejob) throws URISyntaxException {
        log.debug("REST request to save Autocarejob : {}", autocarejob);
        if (autocarejob.getId() != null) {
            throw new BadRequestAlertException("A new autocarejob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        autocarejob = autocarejobRepository.save(autocarejob);
        return ResponseEntity.created(new URI("/api/autocarejobs/" + autocarejob.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, autocarejob.getId().toString()))
            .body(autocarejob);
    }

    /**
     * {@code PUT  /autocarejobs/:id} : Updates an existing autocarejob.
     *
     * @param id the id of the autocarejob to save.
     * @param autocarejob the autocarejob to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarejob,
     * or with status {@code 400 (Bad Request)} if the autocarejob is not valid,
     * or with status {@code 500 (Internal Server Error)} if the autocarejob couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Autocarejob> updateAutocarejob(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarejob autocarejob
    ) throws URISyntaxException {
        log.debug("REST request to update Autocarejob : {}, {}", id, autocarejob);
        if (autocarejob.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarejob.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarejobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        autocarejob = autocarejobRepository.save(autocarejob);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarejob.getId().toString()))
            .body(autocarejob);
    }

    /**
     * {@code PATCH  /autocarejobs/:id} : Partial updates given fields of an existing autocarejob, field will ignore if it is null
     *
     * @param id the id of the autocarejob to save.
     * @param autocarejob the autocarejob to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated autocarejob,
     * or with status {@code 400 (Bad Request)} if the autocarejob is not valid,
     * or with status {@code 404 (Not Found)} if the autocarejob is not found,
     * or with status {@code 500 (Internal Server Error)} if the autocarejob couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Autocarejob> partialUpdateAutocarejob(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Autocarejob autocarejob
    ) throws URISyntaxException {
        log.debug("REST request to partial update Autocarejob partially : {}, {}", id, autocarejob);
        if (autocarejob.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, autocarejob.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!autocarejobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Autocarejob> result = autocarejobRepository
            .findById(autocarejob.getId())
            .map(existingAutocarejob -> {
                if (autocarejob.getJobnumber() != null) {
                    existingAutocarejob.setJobnumber(autocarejob.getJobnumber());
                }
                if (autocarejob.getVehicleid() != null) {
                    existingAutocarejob.setVehicleid(autocarejob.getVehicleid());
                }
                if (autocarejob.getVehiclenumber() != null) {
                    existingAutocarejob.setVehiclenumber(autocarejob.getVehiclenumber());
                }
                if (autocarejob.getMillage() != null) {
                    existingAutocarejob.setMillage(autocarejob.getMillage());
                }
                if (autocarejob.getNextmillage() != null) {
                    existingAutocarejob.setNextmillage(autocarejob.getNextmillage());
                }
                if (autocarejob.getNextservicedate() != null) {
                    existingAutocarejob.setNextservicedate(autocarejob.getNextservicedate());
                }
                if (autocarejob.getVehicletypeid() != null) {
                    existingAutocarejob.setVehicletypeid(autocarejob.getVehicletypeid());
                }
                if (autocarejob.getJobtypeid() != null) {
                    existingAutocarejob.setJobtypeid(autocarejob.getJobtypeid());
                }
                if (autocarejob.getJobtypename() != null) {
                    existingAutocarejob.setJobtypename(autocarejob.getJobtypename());
                }
                if (autocarejob.getJobopenby() != null) {
                    existingAutocarejob.setJobopenby(autocarejob.getJobopenby());
                }
                if (autocarejob.getJobopentime() != null) {
                    existingAutocarejob.setJobopentime(autocarejob.getJobopentime());
                }
                if (autocarejob.getLmu() != null) {
                    existingAutocarejob.setLmu(autocarejob.getLmu());
                }
                if (autocarejob.getLmd() != null) {
                    existingAutocarejob.setLmd(autocarejob.getLmd());
                }
                if (autocarejob.getSpecialrquirments() != null) {
                    existingAutocarejob.setSpecialrquirments(autocarejob.getSpecialrquirments());
                }
                if (autocarejob.getSpecialinstructions() != null) {
                    existingAutocarejob.setSpecialinstructions(autocarejob.getSpecialinstructions());
                }
                if (autocarejob.getRemarks() != null) {
                    existingAutocarejob.setRemarks(autocarejob.getRemarks());
                }
                if (autocarejob.getNextserviceinstructions() != null) {
                    existingAutocarejob.setNextserviceinstructions(autocarejob.getNextserviceinstructions());
                }
                if (autocarejob.getLastserviceinstructions() != null) {
                    existingAutocarejob.setLastserviceinstructions(autocarejob.getLastserviceinstructions());
                }
                if (autocarejob.getIsadvisorchecked() != null) {
                    existingAutocarejob.setIsadvisorchecked(autocarejob.getIsadvisorchecked());
                }
                if (autocarejob.getIsempallocated() != null) {
                    existingAutocarejob.setIsempallocated(autocarejob.getIsempallocated());
                }
                if (autocarejob.getJobclosetime() != null) {
                    existingAutocarejob.setJobclosetime(autocarejob.getJobclosetime());
                }
                if (autocarejob.getIsjobclose() != null) {
                    existingAutocarejob.setIsjobclose(autocarejob.getIsjobclose());
                }
                if (autocarejob.getIsfeedback() != null) {
                    existingAutocarejob.setIsfeedback(autocarejob.getIsfeedback());
                }
                if (autocarejob.getFeedbackstatusid() != null) {
                    existingAutocarejob.setFeedbackstatusid(autocarejob.getFeedbackstatusid());
                }
                if (autocarejob.getCustomername() != null) {
                    existingAutocarejob.setCustomername(autocarejob.getCustomername());
                }
                if (autocarejob.getCustomertel() != null) {
                    existingAutocarejob.setCustomertel(autocarejob.getCustomertel());
                }
                if (autocarejob.getCustomerid() != null) {
                    existingAutocarejob.setCustomerid(autocarejob.getCustomerid());
                }
                if (autocarejob.getAdvisorfinalcheck() != null) {
                    existingAutocarejob.setAdvisorfinalcheck(autocarejob.getAdvisorfinalcheck());
                }
                if (autocarejob.getJobdate() != null) {
                    existingAutocarejob.setJobdate(autocarejob.getJobdate());
                }
                if (autocarejob.getIscompanyservice() != null) {
                    existingAutocarejob.setIscompanyservice(autocarejob.getIscompanyservice());
                }
                if (autocarejob.getFreeservicenumber() != null) {
                    existingAutocarejob.setFreeservicenumber(autocarejob.getFreeservicenumber());
                }
                if (autocarejob.getCompanyid() != null) {
                    existingAutocarejob.setCompanyid(autocarejob.getCompanyid());
                }
                if (autocarejob.getUpdatetocustomer() != null) {
                    existingAutocarejob.setUpdatetocustomer(autocarejob.getUpdatetocustomer());
                }
                if (autocarejob.getNextgearoilmilage() != null) {
                    existingAutocarejob.setNextgearoilmilage(autocarejob.getNextgearoilmilage());
                }
                if (autocarejob.getIsjobinvoiced() != null) {
                    existingAutocarejob.setIsjobinvoiced(autocarejob.getIsjobinvoiced());
                }
                if (autocarejob.getIswaiting() != null) {
                    existingAutocarejob.setIswaiting(autocarejob.getIswaiting());
                }
                if (autocarejob.getIscustomercomment() != null) {
                    existingAutocarejob.setIscustomercomment(autocarejob.getIscustomercomment());
                }
                if (autocarejob.getImagefolder() != null) {
                    existingAutocarejob.setImagefolder(autocarejob.getImagefolder());
                }
                if (autocarejob.getFrontimage() != null) {
                    existingAutocarejob.setFrontimage(autocarejob.getFrontimage());
                }
                if (autocarejob.getLeftimage() != null) {
                    existingAutocarejob.setLeftimage(autocarejob.getLeftimage());
                }
                if (autocarejob.getRightimage() != null) {
                    existingAutocarejob.setRightimage(autocarejob.getRightimage());
                }
                if (autocarejob.getBackimage() != null) {
                    existingAutocarejob.setBackimage(autocarejob.getBackimage());
                }
                if (autocarejob.getDashboardimage() != null) {
                    existingAutocarejob.setDashboardimage(autocarejob.getDashboardimage());
                }

                return existingAutocarejob;
            })
            .map(autocarejobRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, autocarejob.getId().toString())
        );
    }

    /**
     * {@code GET  /autocarejobs} : get all the autocarejobs.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autocarejobs in body.
     */
    @GetMapping("")
    public ResponseEntity<List<Autocarejob>> getAllAutocarejobs(@org.springdoc.core.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Autocarejobs");
        Page<Autocarejob> page = autocarejobRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /autocarejobs/:id} : get the "id" autocarejob.
     *
     * @param id the id of the autocarejob to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the autocarejob, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Autocarejob> getAutocarejob(@PathVariable("id") Long id) {
        log.debug("REST request to get Autocarejob : {}", id);
        Optional<Autocarejob> autocarejob = autocarejobRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(autocarejob);
    }

    /**
     * {@code DELETE  /autocarejobs/:id} : delete the "id" autocarejob.
     *
     * @param id the id of the autocarejob to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutocarejob(@PathVariable("id") Long id) {
        log.debug("REST request to delete Autocarejob : {}", id);
        autocarejobRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
