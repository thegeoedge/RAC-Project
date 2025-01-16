package com.heavenscode.rac.service;

import com.heavenscode.rac.domain.Customervehicle;
import com.heavenscode.rac.repository.CustomervehicleRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.heavenscode.rac.domain.Customervehicle}.
 */
@Service
@Transactional
public class CustomervehicleService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomervehicleService.class);

    private final CustomervehicleRepository customervehicleRepository;

    public CustomervehicleService(CustomervehicleRepository customervehicleRepository) {
        this.customervehicleRepository = customervehicleRepository;
    }

    /**
     * Save a customervehicle.
     *
     * @param customervehicle the entity to save.
     * @return the persisted entity.
     */
    public Customervehicle save(Customervehicle customervehicle) {
        LOG.debug("Request to save Customervehicle : {}", customervehicle);
        return customervehicleRepository.save(customervehicle);
    }

    /**
     * Update a customervehicle.
     *
     * @param customervehicle the entity to save.
     * @return the persisted entity.
     */
    public Customervehicle update(Customervehicle customervehicle) {
        LOG.debug("Request to update Customervehicle : {}", customervehicle);
        return customervehicleRepository.save(customervehicle);
    }

    /**
     * Partially update a customervehicle.
     *
     * @param customervehicle the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Customervehicle> partialUpdate(Customervehicle customervehicle) {
        LOG.debug("Request to partially update Customervehicle : {}", customervehicle);

        return customervehicleRepository
            .findById(customervehicle.getId())
            .map(existingCustomervehicle -> {
                if (customervehicle.getCustomerid() != null) {
                    existingCustomervehicle.setCustomerid(customervehicle.getCustomerid());
                }
                if (customervehicle.getVehiclenumber() != null) {
                    existingCustomervehicle.setVehiclenumber(customervehicle.getVehiclenumber());
                }
                if (customervehicle.getCategoryid() != null) {
                    existingCustomervehicle.setCategoryid(customervehicle.getCategoryid());
                }
                if (customervehicle.getCategoryname() != null) {
                    existingCustomervehicle.setCategoryname(customervehicle.getCategoryname());
                }
                if (customervehicle.getTypeid() != null) {
                    existingCustomervehicle.setTypeid(customervehicle.getTypeid());
                }
                if (customervehicle.getTypename() != null) {
                    existingCustomervehicle.setTypename(customervehicle.getTypename());
                }
                if (customervehicle.getMakeid() != null) {
                    existingCustomervehicle.setMakeid(customervehicle.getMakeid());
                }
                if (customervehicle.getMakename() != null) {
                    existingCustomervehicle.setMakename(customervehicle.getMakename());
                }
                if (customervehicle.getModel() != null) {
                    existingCustomervehicle.setModel(customervehicle.getModel());
                }
                if (customervehicle.getYom() != null) {
                    existingCustomervehicle.setYom(customervehicle.getYom());
                }
                if (customervehicle.getCustomercode() != null) {
                    existingCustomervehicle.setCustomercode(customervehicle.getCustomercode());
                }
                if (customervehicle.getRemarks() != null) {
                    existingCustomervehicle.setRemarks(customervehicle.getRemarks());
                }
                if (customervehicle.getServicecount() != null) {
                    existingCustomervehicle.setServicecount(customervehicle.getServicecount());
                }
                if (customervehicle.getEngNo() != null) {
                    existingCustomervehicle.setEngNo(customervehicle.getEngNo());
                }
                if (customervehicle.getChaNo() != null) {
                    existingCustomervehicle.setChaNo(customervehicle.getChaNo());
                }
                if (customervehicle.getMilage() != null) {
                    existingCustomervehicle.setMilage(customervehicle.getMilage());
                }
                if (customervehicle.getLastservicedate() != null) {
                    existingCustomervehicle.setLastservicedate(customervehicle.getLastservicedate());
                }
                if (customervehicle.getNextservicedate() != null) {
                    existingCustomervehicle.setNextservicedate(customervehicle.getNextservicedate());
                }
                if (customervehicle.getLmu() != null) {
                    existingCustomervehicle.setLmu(customervehicle.getLmu());
                }
                if (customervehicle.getLmd() != null) {
                    existingCustomervehicle.setLmd(customervehicle.getLmd());
                }
                if (customervehicle.getNextgearoilmilage() != null) {
                    existingCustomervehicle.setNextgearoilmilage(customervehicle.getNextgearoilmilage());
                }
                if (customervehicle.getNextmilage() != null) {
                    existingCustomervehicle.setNextmilage(customervehicle.getNextmilage());
                }
                if (customervehicle.getServiceperiod() != null) {
                    existingCustomervehicle.setServiceperiod(customervehicle.getServiceperiod());
                }

                return existingCustomervehicle;
            })
            .map(customervehicleRepository::save);
    }

    /**
     * Get one customervehicle by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Customervehicle> findOne(Long id) {
        LOG.debug("Request to get Customervehicle : {}", id);
        return customervehicleRepository.findById(id);
    }

    /**
     * Delete the customervehicle by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Customervehicle : {}", id);
        customervehicleRepository.deleteById(id);
    }
}
