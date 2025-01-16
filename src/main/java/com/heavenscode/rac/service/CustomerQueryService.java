package com.heavenscode.rac.service;

import com.heavenscode.rac.domain.*; // for static metamodels
import com.heavenscode.rac.domain.Customer;
import com.heavenscode.rac.repository.CustomerRepository;
import com.heavenscode.rac.service.criteria.CustomerCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Customer} entities in the database.
 * The main input is a {@link CustomerCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link Customer} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CustomerQueryService extends QueryService<Customer> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerQueryService.class);

    private final CustomerRepository customerRepository;

    public CustomerQueryService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Return a {@link Page} of {@link Customer} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Customer> findByCriteria(CustomerCriteria criteria, Pageable page) {
        LOG.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CustomerCriteria criteria) {
        LOG.debug("count by criteria : {}", criteria);
        final Specification<Customer> specification = createSpecification(criteria);
        return customerRepository.count(specification);
    }

    /**
     * Function to convert {@link CustomerCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Customer> createSpecification(CustomerCriteria criteria) {
        Specification<Customer> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Customer_.id));
            }
            if (criteria.getCustomertype() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCustomertype(), Customer_.customertype));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Customer_.code));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Customer_.title));
            }
            if (criteria.getNamewithinitials() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNamewithinitials(), Customer_.namewithinitials));
            }
            if (criteria.getFullname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFullname(), Customer_.fullname));
            }
            if (criteria.getCallingname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCallingname(), Customer_.callingname));
            }
            if (criteria.getNicno() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNicno(), Customer_.nicno));
            }
            if (criteria.getNicissueddate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNicissueddate(), Customer_.nicissueddate));
            }
            if (criteria.getDateofbirth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateofbirth(), Customer_.dateofbirth));
            }
            if (criteria.getBloodgroup() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBloodgroup(), Customer_.bloodgroup));
            }
            if (criteria.getGender() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGender(), Customer_.gender));
            }
            if (criteria.getMaritalstatus() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMaritalstatus(), Customer_.maritalstatus));
            }
            if (criteria.getMarrieddate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMarrieddate(), Customer_.marrieddate));
            }
            if (criteria.getNationality() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNationality(), Customer_.nationality));
            }
            if (criteria.getTerritory() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTerritory(), Customer_.territory));
            }
            if (criteria.getReligion() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReligion(), Customer_.religion));
            }
            if (criteria.getTeam() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeam(), Customer_.team));
            }
            if (criteria.getBusinessname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessname(), Customer_.businessname));
            }
            if (criteria.getBusinessregdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBusinessregdate(), Customer_.businessregdate));
            }
            if (criteria.getBusinessregno() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessregno(), Customer_.businessregno));
            }
            if (criteria.getProfilepicturepath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProfilepicturepath(), Customer_.profilepicturepath));
            }
            if (criteria.getResidencehouseno() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidencehouseno(), Customer_.residencehouseno));
            }
            if (criteria.getResidenceaddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidenceaddress(), Customer_.residenceaddress));
            }
            if (criteria.getResidencecity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidencecity(), Customer_.residencecity));
            }
            if (criteria.getResidencephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getResidencephone(), Customer_.residencephone));
            }
            if (criteria.getBusinesslocationno() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinesslocationno(), Customer_.businesslocationno));
            }
            if (criteria.getBusinessaddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessaddress(), Customer_.businessaddress));
            }
            if (criteria.getBusinesscity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinesscity(), Customer_.businesscity));
            }
            if (criteria.getBusinessphone1() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessphone1(), Customer_.businessphone1));
            }
            if (criteria.getBusinessphone2() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessphone2(), Customer_.businessphone2));
            }
            if (criteria.getBusinessmobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessmobile(), Customer_.businessmobile));
            }
            if (criteria.getBusinessfax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessfax(), Customer_.businessfax));
            }
            if (criteria.getBusinessemail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBusinessemail(), Customer_.businessemail));
            }
            if (criteria.getBusinessprovinceid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBusinessprovinceid(), Customer_.businessprovinceid));
            }
            if (criteria.getBusinessdistrictid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBusinessdistrictid(), Customer_.businessdistrictid));
            }
            if (criteria.getContactpersonname() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactpersonname(), Customer_.contactpersonname));
            }
            if (criteria.getContactpersonphone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactpersonphone(), Customer_.contactpersonphone));
            }
            if (criteria.getContactpersonmobile() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getContactpersonmobile(), Customer_.contactpersonmobile)
                );
            }
            if (criteria.getContactpersonemail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactpersonemail(), Customer_.contactpersonemail));
            }
            if (criteria.getRootmappath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRootmappath(), Customer_.rootmappath));
            }
            if (criteria.getWebsite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWebsite(), Customer_.website));
            }
            if (criteria.getRegistrationdate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRegistrationdate(), Customer_.registrationdate));
            }
            if (criteria.getIsactive() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsactive(), Customer_.isactive));
            }
            if (criteria.getIsinternalcustomer() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIsinternalcustomer(), Customer_.isinternalcustomer));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Customer_.description));
            }
            if (criteria.getLmu() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLmu(), Customer_.lmu));
            }
            if (criteria.getLmd() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLmd(), Customer_.lmd));
            }
            if (criteria.getMaximumdiscount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMaximumdiscount(), Customer_.maximumdiscount));
            }
            if (criteria.getCreditlimit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreditlimit(), Customer_.creditlimit));
            }
            if (criteria.getHassecuritydeposit() != null) {
                specification = specification.and(buildSpecification(criteria.getHassecuritydeposit(), Customer_.hassecuritydeposit));
            }
            if (criteria.getSecuritydepositamount() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getSecuritydepositamount(), Customer_.securitydepositamount)
                );
            }
            if (criteria.getPaybycash() != null) {
                specification = specification.and(buildSpecification(criteria.getPaybycash(), Customer_.paybycash));
            }
            if (criteria.getCashpaymentbeforeload() != null) {
                specification = specification.and(buildSpecification(criteria.getCashpaymentbeforeload(), Customer_.cashpaymentbeforeload));
            }
            if (criteria.getCashlastinvoicebeforeload() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getCashlastinvoicebeforeload(), Customer_.cashlastinvoicebeforeload)
                );
            }
            if (criteria.getPaybycredit() != null) {
                specification = specification.and(buildSpecification(criteria.getPaybycredit(), Customer_.paybycredit));
            }
            if (criteria.getCreditoneweekcheck() != null) {
                specification = specification.and(buildSpecification(criteria.getCreditoneweekcheck(), Customer_.creditoneweekcheck));
            }
            if (criteria.getCreditpaymentbydays() != null) {
                specification = specification.and(
                    buildRangeSpecification(criteria.getCreditpaymentbydays(), Customer_.creditpaymentbydays)
                );
            }
            if (criteria.getHaspurchasingdeposit() != null) {
                specification = specification.and(buildSpecification(criteria.getHaspurchasingdeposit(), Customer_.haspurchasingdeposit));
            }
            if (criteria.getHassecuritydepositbond() != null) {
                specification = specification.and(
                    buildSpecification(criteria.getHassecuritydepositbond(), Customer_.hassecuritydepositbond)
                );
            }
            if (criteria.getHasassestsdeposit() != null) {
                specification = specification.and(buildSpecification(criteria.getHasassestsdeposit(), Customer_.hasassestsdeposit));
            }
            if (criteria.getCustomerrootmappath() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getCustomerrootmappath(), Customer_.customerrootmappath)
                );
            }
            if (criteria.getEmployername() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployername(), Customer_.employername));
            }
            if (criteria.getEmployeraddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployeraddress(), Customer_.employeraddress));
            }
            if (criteria.getEmployerphone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmployerphone(), Customer_.employerphone));
            }
            if (criteria.getEmployerdesignation() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getEmployerdesignation(), Customer_.employerdesignation)
                );
            }
            if (criteria.getPreviousemployername() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPreviousemployername(), Customer_.previousemployername)
                );
            }
            if (criteria.getPreviousemployeraddress() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPreviousemployeraddress(), Customer_.previousemployeraddress)
                );
            }
            if (criteria.getPreviousindustry() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPreviousindustry(), Customer_.previousindustry));
            }
            if (criteria.getPreviousperiod() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPreviousperiod(), Customer_.previousperiod));
            }
            if (criteria.getPreviouspositions() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPreviouspositions(), Customer_.previouspositions));
            }
            if (criteria.getPreviousresionforleaving() != null) {
                specification = specification.and(
                    buildStringSpecification(criteria.getPreviousresionforleaving(), Customer_.previousresionforleaving)
                );
            }
            if (criteria.getHascreaditlimit() != null) {
                specification = specification.and(buildSpecification(criteria.getHascreaditlimit(), Customer_.hascreaditlimit));
            }
            if (criteria.getAccountid() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAccountid(), Customer_.accountid));
            }
            if (criteria.getAccountcode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountcode(), Customer_.accountcode));
            }
            if (criteria.getIsregistered() != null) {
                specification = specification.and(buildSpecification(criteria.getIsregistered(), Customer_.isregistered));
            }
            if (criteria.getVatregnumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVatregnumber(), Customer_.vatregnumber));
            }
            if (criteria.getTinnumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTinnumber(), Customer_.tinnumber));
            }
            if (criteria.getLat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLat(), Customer_.lat));
            }
            if (criteria.getLon() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLon(), Customer_.lon));
            }
            if (criteria.getCreditperiod() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreditperiod(), Customer_.creditperiod));
            }
        }
        return specification;
    }
}
