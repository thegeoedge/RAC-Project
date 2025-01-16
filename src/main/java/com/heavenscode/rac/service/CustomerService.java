package com.heavenscode.rac.service;

import com.heavenscode.rac.domain.Customer;
import com.heavenscode.rac.repository.CustomerRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.heavenscode.rac.domain.Customer}.
 */
@Service
@Transactional
public class CustomerService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Save a customer.
     *
     * @param customer the entity to save.
     * @return the persisted entity.
     */
    public Customer save(Customer customer) {
        LOG.debug("Request to save Customer : {}", customer);
        return customerRepository.save(customer);
    }

    /**
     * Update a customer.
     *
     * @param customer the entity to save.
     * @return the persisted entity.
     */
    public Customer update(Customer customer) {
        LOG.debug("Request to update Customer : {}", customer);
        return customerRepository.save(customer);
    }

    /**
     * Partially update a customer.
     *
     * @param customer the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Customer> partialUpdate(Customer customer) {
        LOG.debug("Request to partially update Customer : {}", customer);

        return customerRepository
            .findById(customer.getId())
            .map(existingCustomer -> {
                if (customer.getCustomertype() != null) {
                    existingCustomer.setCustomertype(customer.getCustomertype());
                }
                if (customer.getCode() != null) {
                    existingCustomer.setCode(customer.getCode());
                }
                if (customer.getTitle() != null) {
                    existingCustomer.setTitle(customer.getTitle());
                }
                if (customer.getNamewithinitials() != null) {
                    existingCustomer.setNamewithinitials(customer.getNamewithinitials());
                }
                if (customer.getFullname() != null) {
                    existingCustomer.setFullname(customer.getFullname());
                }
                if (customer.getCallingname() != null) {
                    existingCustomer.setCallingname(customer.getCallingname());
                }
                if (customer.getNicno() != null) {
                    existingCustomer.setNicno(customer.getNicno());
                }
                if (customer.getNicissueddate() != null) {
                    existingCustomer.setNicissueddate(customer.getNicissueddate());
                }
                if (customer.getDateofbirth() != null) {
                    existingCustomer.setDateofbirth(customer.getDateofbirth());
                }
                if (customer.getBloodgroup() != null) {
                    existingCustomer.setBloodgroup(customer.getBloodgroup());
                }
                if (customer.getGender() != null) {
                    existingCustomer.setGender(customer.getGender());
                }
                if (customer.getMaritalstatus() != null) {
                    existingCustomer.setMaritalstatus(customer.getMaritalstatus());
                }
                if (customer.getMarrieddate() != null) {
                    existingCustomer.setMarrieddate(customer.getMarrieddate());
                }
                if (customer.getNationality() != null) {
                    existingCustomer.setNationality(customer.getNationality());
                }
                if (customer.getTerritory() != null) {
                    existingCustomer.setTerritory(customer.getTerritory());
                }
                if (customer.getReligion() != null) {
                    existingCustomer.setReligion(customer.getReligion());
                }
                if (customer.getTeam() != null) {
                    existingCustomer.setTeam(customer.getTeam());
                }
                if (customer.getBusinessname() != null) {
                    existingCustomer.setBusinessname(customer.getBusinessname());
                }
                if (customer.getBusinessregdate() != null) {
                    existingCustomer.setBusinessregdate(customer.getBusinessregdate());
                }
                if (customer.getBusinessregno() != null) {
                    existingCustomer.setBusinessregno(customer.getBusinessregno());
                }
                if (customer.getProfilepicturepath() != null) {
                    existingCustomer.setProfilepicturepath(customer.getProfilepicturepath());
                }
                if (customer.getResidencehouseno() != null) {
                    existingCustomer.setResidencehouseno(customer.getResidencehouseno());
                }
                if (customer.getResidenceaddress() != null) {
                    existingCustomer.setResidenceaddress(customer.getResidenceaddress());
                }
                if (customer.getResidencecity() != null) {
                    existingCustomer.setResidencecity(customer.getResidencecity());
                }
                if (customer.getResidencephone() != null) {
                    existingCustomer.setResidencephone(customer.getResidencephone());
                }
                if (customer.getBusinesslocationno() != null) {
                    existingCustomer.setBusinesslocationno(customer.getBusinesslocationno());
                }
                if (customer.getBusinessaddress() != null) {
                    existingCustomer.setBusinessaddress(customer.getBusinessaddress());
                }
                if (customer.getBusinesscity() != null) {
                    existingCustomer.setBusinesscity(customer.getBusinesscity());
                }
                if (customer.getBusinessphone1() != null) {
                    existingCustomer.setBusinessphone1(customer.getBusinessphone1());
                }
                if (customer.getBusinessphone2() != null) {
                    existingCustomer.setBusinessphone2(customer.getBusinessphone2());
                }
                if (customer.getBusinessmobile() != null) {
                    existingCustomer.setBusinessmobile(customer.getBusinessmobile());
                }
                if (customer.getBusinessfax() != null) {
                    existingCustomer.setBusinessfax(customer.getBusinessfax());
                }
                if (customer.getBusinessemail() != null) {
                    existingCustomer.setBusinessemail(customer.getBusinessemail());
                }
                if (customer.getBusinessprovinceid() != null) {
                    existingCustomer.setBusinessprovinceid(customer.getBusinessprovinceid());
                }
                if (customer.getBusinessdistrictid() != null) {
                    existingCustomer.setBusinessdistrictid(customer.getBusinessdistrictid());
                }
                if (customer.getContactpersonname() != null) {
                    existingCustomer.setContactpersonname(customer.getContactpersonname());
                }
                if (customer.getContactpersonphone() != null) {
                    existingCustomer.setContactpersonphone(customer.getContactpersonphone());
                }
                if (customer.getContactpersonmobile() != null) {
                    existingCustomer.setContactpersonmobile(customer.getContactpersonmobile());
                }
                if (customer.getContactpersonemail() != null) {
                    existingCustomer.setContactpersonemail(customer.getContactpersonemail());
                }
                if (customer.getRootmappath() != null) {
                    existingCustomer.setRootmappath(customer.getRootmappath());
                }
                if (customer.getWebsite() != null) {
                    existingCustomer.setWebsite(customer.getWebsite());
                }
                if (customer.getRegistrationdate() != null) {
                    existingCustomer.setRegistrationdate(customer.getRegistrationdate());
                }
                if (customer.getIsactive() != null) {
                    existingCustomer.setIsactive(customer.getIsactive());
                }
                if (customer.getIsinternalcustomer() != null) {
                    existingCustomer.setIsinternalcustomer(customer.getIsinternalcustomer());
                }
                if (customer.getDescription() != null) {
                    existingCustomer.setDescription(customer.getDescription());
                }
                if (customer.getLmu() != null) {
                    existingCustomer.setLmu(customer.getLmu());
                }
                if (customer.getLmd() != null) {
                    existingCustomer.setLmd(customer.getLmd());
                }
                if (customer.getMaximumdiscount() != null) {
                    existingCustomer.setMaximumdiscount(customer.getMaximumdiscount());
                }
                if (customer.getCreditlimit() != null) {
                    existingCustomer.setCreditlimit(customer.getCreditlimit());
                }
                if (customer.getHassecuritydeposit() != null) {
                    existingCustomer.setHassecuritydeposit(customer.getHassecuritydeposit());
                }
                if (customer.getSecuritydepositamount() != null) {
                    existingCustomer.setSecuritydepositamount(customer.getSecuritydepositamount());
                }
                if (customer.getPaybycash() != null) {
                    existingCustomer.setPaybycash(customer.getPaybycash());
                }
                if (customer.getCashpaymentbeforeload() != null) {
                    existingCustomer.setCashpaymentbeforeload(customer.getCashpaymentbeforeload());
                }
                if (customer.getCashlastinvoicebeforeload() != null) {
                    existingCustomer.setCashlastinvoicebeforeload(customer.getCashlastinvoicebeforeload());
                }
                if (customer.getPaybycredit() != null) {
                    existingCustomer.setPaybycredit(customer.getPaybycredit());
                }
                if (customer.getCreditoneweekcheck() != null) {
                    existingCustomer.setCreditoneweekcheck(customer.getCreditoneweekcheck());
                }
                if (customer.getCreditpaymentbydays() != null) {
                    existingCustomer.setCreditpaymentbydays(customer.getCreditpaymentbydays());
                }
                if (customer.getHaspurchasingdeposit() != null) {
                    existingCustomer.setHaspurchasingdeposit(customer.getHaspurchasingdeposit());
                }
                if (customer.getHassecuritydepositbond() != null) {
                    existingCustomer.setHassecuritydepositbond(customer.getHassecuritydepositbond());
                }
                if (customer.getHasassestsdeposit() != null) {
                    existingCustomer.setHasassestsdeposit(customer.getHasassestsdeposit());
                }
                if (customer.getCustomerrootmappath() != null) {
                    existingCustomer.setCustomerrootmappath(customer.getCustomerrootmappath());
                }
                if (customer.getEmployername() != null) {
                    existingCustomer.setEmployername(customer.getEmployername());
                }
                if (customer.getEmployeraddress() != null) {
                    existingCustomer.setEmployeraddress(customer.getEmployeraddress());
                }
                if (customer.getEmployerphone() != null) {
                    existingCustomer.setEmployerphone(customer.getEmployerphone());
                }
                if (customer.getEmployerdesignation() != null) {
                    existingCustomer.setEmployerdesignation(customer.getEmployerdesignation());
                }
                if (customer.getPreviousemployername() != null) {
                    existingCustomer.setPreviousemployername(customer.getPreviousemployername());
                }
                if (customer.getPreviousemployeraddress() != null) {
                    existingCustomer.setPreviousemployeraddress(customer.getPreviousemployeraddress());
                }
                if (customer.getPreviousindustry() != null) {
                    existingCustomer.setPreviousindustry(customer.getPreviousindustry());
                }
                if (customer.getPreviousperiod() != null) {
                    existingCustomer.setPreviousperiod(customer.getPreviousperiod());
                }
                if (customer.getPreviouspositions() != null) {
                    existingCustomer.setPreviouspositions(customer.getPreviouspositions());
                }
                if (customer.getPreviousresionforleaving() != null) {
                    existingCustomer.setPreviousresionforleaving(customer.getPreviousresionforleaving());
                }
                if (customer.getHascreaditlimit() != null) {
                    existingCustomer.setHascreaditlimit(customer.getHascreaditlimit());
                }
                if (customer.getAccountid() != null) {
                    existingCustomer.setAccountid(customer.getAccountid());
                }
                if (customer.getAccountcode() != null) {
                    existingCustomer.setAccountcode(customer.getAccountcode());
                }
                if (customer.getIsregistered() != null) {
                    existingCustomer.setIsregistered(customer.getIsregistered());
                }
                if (customer.getVatregnumber() != null) {
                    existingCustomer.setVatregnumber(customer.getVatregnumber());
                }
                if (customer.getTinnumber() != null) {
                    existingCustomer.setTinnumber(customer.getTinnumber());
                }
                if (customer.getLat() != null) {
                    existingCustomer.setLat(customer.getLat());
                }
                if (customer.getLon() != null) {
                    existingCustomer.setLon(customer.getLon());
                }
                if (customer.getCreditperiod() != null) {
                    existingCustomer.setCreditperiod(customer.getCreditperiod());
                }

                return existingCustomer;
            })
            .map(customerRepository::save);
    }

    /**
     * Get one customer by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Customer> findOne(Long id) {
        LOG.debug("Request to get Customer : {}", id);
        return customerRepository.findById(id);
    }

    /**
     * Delete the customer by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Customer : {}", id);
        customerRepository.deleteById(id);
    }
}
