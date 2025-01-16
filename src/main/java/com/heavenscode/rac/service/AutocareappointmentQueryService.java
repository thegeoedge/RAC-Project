package com.heavenscode.rac.service;

import com.heavenscode.rac.domain.*; // for static metamodels
import com.heavenscode.rac.domain.Autocareappointment;
import com.heavenscode.rac.repository.AutocareappointmentRepository;
import com.heavenscode.rac.service.criteria.AutocareappointmentCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Autocareappointment} entities in the database.
 * The main input is a {@link AutocareappointmentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Autocareappointment} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AutocareappointmentQueryService extends QueryService<Autocareappointment> {

    private static final Logger LOG = LoggerFactory.getLogger(AutocareappointmentQueryService.class);

    private final AutocareappointmentRepository autocareappointmentRepository;

    public AutocareappointmentQueryService(AutocareappointmentRepository autocareappointmentRepository) {
        this.autocareappointmentRepository = autocareappointmentRepository;
    }

    /**
     * Return a {@link Page} of {@link Autocareappointment} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Autocareappointment> findByCriteria(AutocareappointmentCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Autocareappointment> specification = createSpecification(criteria);
        return autocareappointmentRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AutocareappointmentCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Autocareappointment> specification = createSpecification(criteria);
        return autocareappointmentRepository.count(specification);
    }

    /**
     * Function to convert {@link AutocareappointmentCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Autocareappointment> createSpecification(AutocareappointmentCriteria criteria) {
        Specification<Autocareappointment> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Autocareappointment_.id));
            }
            if (criteria.getAppointmenttype() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getAppointmenttype(), Autocareappointment_.appointmenttype)
                );
            }
            if (criteria.getAppointmentdate() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getAppointmentdate(), Autocareappointment_.appointmentdate)
                );
            }
            if (criteria.getAddeddate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAddeddate(), Autocareappointment_.addeddate));
            }
            if (criteria.getConformdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConformdate(), Autocareappointment_.conformdate));
            }
            if (criteria.getAppointmentnumber() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getAppointmentnumber(), Autocareappointment_.appointmentnumber)
                );
            }
            if (criteria.getVehiclenumber() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getVehiclenumber(), Autocareappointment_.vehiclenumber)
                );
            }
            if (criteria.getAppointmenttime() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getAppointmenttime(), Autocareappointment_.appointmenttime)
                );
            }
            if (criteria.getIsconformed() != null) {
                specification = specification.and(buildSpecification(criteria.getIsconformed(), Autocareappointment_.isconformed));
            }
            if (criteria.getConformedby() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getConformedby(), Autocareappointment_.conformedby));
            }
            if (criteria.getLmd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLmd(), Autocareappointment_.lmd));
            }
            if (criteria.getLmu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLmu(), Autocareappointment_.lmu));
            }
            if (criteria.getCustomerid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCustomerid(), Autocareappointment_.customerid));
            }
            if (criteria.getContactnumber() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getContactnumber(), Autocareappointment_.contactnumber)
                );
            }
            if (criteria.getCustomername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomername(), Autocareappointment_.customername));
            }
            if (criteria.getIssued() != null) {
                specification = specification.and(buildSpecification(criteria.getIssued(), Autocareappointment_.issued));
            }
            if (criteria.getHoistid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHoistid(), Autocareappointment_.hoistid));
            }
            if (criteria.getIsarrived() != null) {
                specification = specification.and(buildSpecification(criteria.getIsarrived(), Autocareappointment_.isarrived));
            }
            if (criteria.getIscancel() != null) {
                specification = specification.and(buildSpecification(criteria.getIscancel(), Autocareappointment_.iscancel));
            }
            if (criteria.getIsnoanswer() != null) {
                specification = specification.and(buildSpecification(criteria.getIsnoanswer(), Autocareappointment_.isnoanswer));
            }
            if (criteria.getMissedappointmentcall() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getMissedappointmentcall(), Autocareappointment_.missedappointmentcall)
                );
            }
            if (criteria.getCustomermobileid() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getCustomermobileid(), Autocareappointment_.customermobileid)
                );
            }
            if (criteria.getCustomermobilevehicleid() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getCustomermobilevehicleid(), Autocareappointment_.customermobilevehicleid)
                );
            }
            if (criteria.getVehicleid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getVehicleid(), Autocareappointment_.vehicleid));
            }
            if (criteria.getIsmobileappointment() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getIsmobileappointment(), Autocareappointment_.ismobileappointment)
                );
            }
            if (criteria.getAdvancepayment() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getAdvancepayment(), Autocareappointment_.advancepayment)
                );
            }
            if (criteria.getJobid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getJobid(), Autocareappointment_.jobid));
            }
        }
        return specification;
    }
}
