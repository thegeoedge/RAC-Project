package com.heavenscode.rac.service;

import com.heavenscode.rac.domain.Autocareappointment;
import com.heavenscode.rac.repository.AutocareappointmentRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.heavenscode.rac.domain.Autocareappointment}.
 */
@Service
@Transactional
public class AutocareappointmentService {

    private static final Logger LOG = LoggerFactory.getLogger(AutocareappointmentService.class);

    private final AutocareappointmentRepository autocareappointmentRepository;

    public AutocareappointmentService(AutocareappointmentRepository autocareappointmentRepository) {
        this.autocareappointmentRepository = autocareappointmentRepository;
    }

    /**
     * Save a autocareappointment.
     *
     * @param autocareappointment the entity to save.
     * @return the persisted entity.
     */
    public Autocareappointment save(Autocareappointment autocareappointment) {
        LOG.debug("Request to save Autocareappointment : {}", autocareappointment);
        return autocareappointmentRepository.save(autocareappointment);
    }

    /**
     * Update a autocareappointment.
     *
     * @param autocareappointment the entity to save.
     * @return the persisted entity.
     */
    public Autocareappointment update(Autocareappointment autocareappointment) {
        LOG.debug("Request to update Autocareappointment : {}", autocareappointment);
        return autocareappointmentRepository.save(autocareappointment);
    }

    /**
     * Partially update a autocareappointment.
     *
     * @param autocareappointment the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Autocareappointment> partialUpdate(Autocareappointment autocareappointment) {
        LOG.debug("Request to partially update Autocareappointment : {}", autocareappointment);

        return autocareappointmentRepository
            .findById(autocareappointment.getId())
            .map(existingAutocareappointment -> {
                if (autocareappointment.getAppointmenttype() != null) {
                    existingAutocareappointment.setAppointmenttype(autocareappointment.getAppointmenttype());
                }
                if (autocareappointment.getAppointmentdate() != null) {
                    existingAutocareappointment.setAppointmentdate(autocareappointment.getAppointmentdate());
                }
                if (autocareappointment.getAddeddate() != null) {
                    existingAutocareappointment.setAddeddate(autocareappointment.getAddeddate());
                }
                if (autocareappointment.getConformdate() != null) {
                    existingAutocareappointment.setConformdate(autocareappointment.getConformdate());
                }
                if (autocareappointment.getAppointmentnumber() != null) {
                    existingAutocareappointment.setAppointmentnumber(autocareappointment.getAppointmentnumber());
                }
                if (autocareappointment.getVehiclenumber() != null) {
                    existingAutocareappointment.setVehiclenumber(autocareappointment.getVehiclenumber());
                }
                if (autocareappointment.getAppointmenttime() != null) {
                    existingAutocareappointment.setAppointmenttime(autocareappointment.getAppointmenttime());
                }
                if (autocareappointment.getIsconformed() != null) {
                    existingAutocareappointment.setIsconformed(autocareappointment.getIsconformed());
                }
                if (autocareappointment.getConformedby() != null) {
                    existingAutocareappointment.setConformedby(autocareappointment.getConformedby());
                }
                if (autocareappointment.getLmd() != null) {
                    existingAutocareappointment.setLmd(autocareappointment.getLmd());
                }
                if (autocareappointment.getLmu() != null) {
                    existingAutocareappointment.setLmu(autocareappointment.getLmu());
                }
                if (autocareappointment.getCustomerid() != null) {
                    existingAutocareappointment.setCustomerid(autocareappointment.getCustomerid());
                }
                if (autocareappointment.getContactnumber() != null) {
                    existingAutocareappointment.setContactnumber(autocareappointment.getContactnumber());
                }
                if (autocareappointment.getCustomername() != null) {
                    existingAutocareappointment.setCustomername(autocareappointment.getCustomername());
                }
                if (autocareappointment.getIssued() != null) {
                    existingAutocareappointment.setIssued(autocareappointment.getIssued());
                }
                if (autocareappointment.getHoistid() != null) {
                    existingAutocareappointment.setHoistid(autocareappointment.getHoistid());
                }
                if (autocareappointment.getIsarrived() != null) {
                    existingAutocareappointment.setIsarrived(autocareappointment.getIsarrived());
                }
                if (autocareappointment.getIscancel() != null) {
                    existingAutocareappointment.setIscancel(autocareappointment.getIscancel());
                }
                if (autocareappointment.getIsnoanswer() != null) {
                    existingAutocareappointment.setIsnoanswer(autocareappointment.getIsnoanswer());
                }
                if (autocareappointment.getMissedappointmentcall() != null) {
                    existingAutocareappointment.setMissedappointmentcall(autocareappointment.getMissedappointmentcall());
                }
                if (autocareappointment.getCustomermobileid() != null) {
                    existingAutocareappointment.setCustomermobileid(autocareappointment.getCustomermobileid());
                }
                if (autocareappointment.getCustomermobilevehicleid() != null) {
                    existingAutocareappointment.setCustomermobilevehicleid(autocareappointment.getCustomermobilevehicleid());
                }
                if (autocareappointment.getVehicleid() != null) {
                    existingAutocareappointment.setVehicleid(autocareappointment.getVehicleid());
                }
                if (autocareappointment.getIsmobileappointment() != null) {
                    existingAutocareappointment.setIsmobileappointment(autocareappointment.getIsmobileappointment());
                }
                if (autocareappointment.getAdvancepayment() != null) {
                    existingAutocareappointment.setAdvancepayment(autocareappointment.getAdvancepayment());
                }
                if (autocareappointment.getJobid() != null) {
                    existingAutocareappointment.setJobid(autocareappointment.getJobid());
                }

                return existingAutocareappointment;
            })
            .map(autocareappointmentRepository::save);
    }

    /**
     * Get one autocareappointment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Autocareappointment> findOne(Long id) {
        LOG.debug("Request to get Autocareappointment : {}", id);
        return autocareappointmentRepository.findById(id);
    }

    /**
     * Delete the autocareappointment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        LOG.debug("Request to delete Autocareappointment : {}", id);
        autocareappointmentRepository.deleteById(id);
    }
}
