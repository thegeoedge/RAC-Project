package com.heavenscode.rac.service;

import com.heavenscode.rac.domain.*; // for static metamodels
import com.heavenscode.rac.domain.Customervehicle;
import com.heavenscode.rac.repository.CustomervehicleRepository;
import com.heavenscode.rac.service.criteria.CustomervehicleCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Customervehicle} entities in the database.
 * The main input is a {@link CustomervehicleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Customervehicle} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomervehicleQueryService extends QueryService<Customervehicle> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomervehicleQueryService.class);

    private final CustomervehicleRepository customervehicleRepository;

    public CustomervehicleQueryService(CustomervehicleRepository customervehicleRepository) {
        this.customervehicleRepository = customervehicleRepository;
    }

    /**
     * Return a {@link Page} of {@link Customervehicle} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Customervehicle> findByCriteria(CustomervehicleCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Customervehicle> specification = createSpecification(criteria);
        return customervehicleRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CustomervehicleCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Customervehicle> specification = createSpecification(criteria);
        return customervehicleRepository.count(specification);
    }

    /**
     * Function to convert {@link CustomervehicleCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Customervehicle> createSpecification(CustomervehicleCriteria criteria) {
        Specification<Customervehicle> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Customervehicle_.id));
            }
            if (criteria.getCustomerid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCustomerid(), Customervehicle_.customerid));
            }
            if (criteria.getVehiclenumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVehiclenumber(), Customervehicle_.vehiclenumber));
            }
            if (criteria.getCategoryid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCategoryid(), Customervehicle_.categoryid));
            }
            if (criteria.getCategoryname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCategoryname(), Customervehicle_.categoryname));
            }
            if (criteria.getTypeid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTypeid(), Customervehicle_.typeid));
            }
            if (criteria.getTypename() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypename(), Customervehicle_.typename));
            }
            if (criteria.getMakeid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMakeid(), Customervehicle_.makeid));
            }
            if (criteria.getMakename() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMakename(), Customervehicle_.makename));
            }
            if (criteria.getModel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModel(), Customervehicle_.model));
            }
            if (criteria.getYom() != null) {
                specification = specification.and(buildStringSpecification(criteria.getYom(), Customervehicle_.yom));
            }
            if (criteria.getCustomercode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCustomercode(), Customervehicle_.customercode));
            }
            if (criteria.getRemarks() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemarks(), Customervehicle_.remarks));
            }
            if (criteria.getServicecount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getServicecount(), Customervehicle_.servicecount));
            }
            if (criteria.getEngNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEngNo(), Customervehicle_.engNo));
            }
            if (criteria.getChaNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChaNo(), Customervehicle_.chaNo));
            }
            if (criteria.getMilage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMilage(), Customervehicle_.milage));
            }
            if (criteria.getLastservicedate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastservicedate(), Customervehicle_.lastservicedate));
            }
            if (criteria.getNextservicedate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNextservicedate(), Customervehicle_.nextservicedate));
            }
            if (criteria.getLmu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLmu(), Customervehicle_.lmu));
            }
            if (criteria.getLmd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLmd(), Customervehicle_.lmd));
            }
            if (criteria.getNextgearoilmilage() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getNextgearoilmilage(), Customervehicle_.nextgearoilmilage)
                );
            }
            if (criteria.getNextmilage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNextmilage(), Customervehicle_.nextmilage));
            }
            if (criteria.getServiceperiod() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getServiceperiod(), Customervehicle_.serviceperiod));
            }
        }
        return specification;
    }
}
