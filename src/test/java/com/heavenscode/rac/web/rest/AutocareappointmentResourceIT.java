package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocareappointmentAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocareappointment;
import com.heavenscode.rac.repository.AutocareappointmentRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AutocareappointmentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocareappointmentResourceIT {

    private static final Integer DEFAULT_APPOINTMENTTYPE = 1;
    private static final Integer UPDATED_APPOINTMENTTYPE = 2;
    private static final Integer SMALLER_APPOINTMENTTYPE = 1 - 1;

    private static final Instant DEFAULT_APPOINTMENTDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPOINTMENTDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ADDEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CONFORMDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CONFORMDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_APPOINTMENTNUMBER = 1;
    private static final Integer UPDATED_APPOINTMENTNUMBER = 2;
    private static final Integer SMALLER_APPOINTMENTNUMBER = 1 - 1;

    private static final String DEFAULT_VEHICLENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLENUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_APPOINTMENTTIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_APPOINTMENTTIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISCONFORMED = false;
    private static final Boolean UPDATED_ISCONFORMED = true;

    private static final Integer DEFAULT_CONFORMEDBY = 1;
    private static final Integer UPDATED_CONFORMEDBY = 2;
    private static final Integer SMALLER_CONFORMEDBY = 1 - 1;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;
    private static final Integer SMALLER_LMU = 1 - 1;

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;
    private static final Integer SMALLER_CUSTOMERID = 1 - 1;

    private static final String DEFAULT_CONTACTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISSUED = false;
    private static final Boolean UPDATED_ISSUED = true;

    private static final Integer DEFAULT_HOISTID = 1;
    private static final Integer UPDATED_HOISTID = 2;
    private static final Integer SMALLER_HOISTID = 1 - 1;

    private static final Boolean DEFAULT_ISARRIVED = false;
    private static final Boolean UPDATED_ISARRIVED = true;

    private static final Boolean DEFAULT_ISCANCEL = false;
    private static final Boolean UPDATED_ISCANCEL = true;

    private static final Boolean DEFAULT_ISNOANSWER = false;
    private static final Boolean UPDATED_ISNOANSWER = true;

    private static final String DEFAULT_MISSEDAPPOINTMENTCALL = "AAAAAAAAAA";
    private static final String UPDATED_MISSEDAPPOINTMENTCALL = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMERMOBILEID = 1;
    private static final Integer UPDATED_CUSTOMERMOBILEID = 2;
    private static final Integer SMALLER_CUSTOMERMOBILEID = 1 - 1;

    private static final Integer DEFAULT_CUSTOMERMOBILEVEHICLEID = 1;
    private static final Integer UPDATED_CUSTOMERMOBILEVEHICLEID = 2;
    private static final Integer SMALLER_CUSTOMERMOBILEVEHICLEID = 1 - 1;

    private static final Integer DEFAULT_VEHICLEID = 1;
    private static final Integer UPDATED_VEHICLEID = 2;
    private static final Integer SMALLER_VEHICLEID = 1 - 1;

    private static final Boolean DEFAULT_ISMOBILEAPPOINTMENT = false;
    private static final Boolean UPDATED_ISMOBILEAPPOINTMENT = true;

    private static final Float DEFAULT_ADVANCEPAYMENT = 1F;
    private static final Float UPDATED_ADVANCEPAYMENT = 2F;
    private static final Float SMALLER_ADVANCEPAYMENT = 1F - 1F;

    private static final Integer DEFAULT_JOBID = 1;
    private static final Integer UPDATED_JOBID = 2;
    private static final Integer SMALLER_JOBID = 1 - 1;

    private static final String ENTITY_API_URL = "/api/autocareappointments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocareappointmentRepository autocareappointmentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocareappointmentMockMvc;

    private Autocareappointment autocareappointment;

    private Autocareappointment insertedAutocareappointment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocareappointment createEntity() {
        return new Autocareappointment()
            .appointmenttype(DEFAULT_APPOINTMENTTYPE)
            .appointmentdate(DEFAULT_APPOINTMENTDATE)
            .addeddate(DEFAULT_ADDEDDATE)
            .conformdate(DEFAULT_CONFORMDATE)
            .appointmentnumber(DEFAULT_APPOINTMENTNUMBER)
            .vehiclenumber(DEFAULT_VEHICLENUMBER)
            .appointmenttime(DEFAULT_APPOINTMENTTIME)
            .isconformed(DEFAULT_ISCONFORMED)
            .conformedby(DEFAULT_CONFORMEDBY)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU)
            .customerid(DEFAULT_CUSTOMERID)
            .contactnumber(DEFAULT_CONTACTNUMBER)
            .customername(DEFAULT_CUSTOMERNAME)
            .issued(DEFAULT_ISSUED)
            .hoistid(DEFAULT_HOISTID)
            .isarrived(DEFAULT_ISARRIVED)
            .iscancel(DEFAULT_ISCANCEL)
            .isnoanswer(DEFAULT_ISNOANSWER)
            .missedappointmentcall(DEFAULT_MISSEDAPPOINTMENTCALL)
            .customermobileid(DEFAULT_CUSTOMERMOBILEID)
            .customermobilevehicleid(DEFAULT_CUSTOMERMOBILEVEHICLEID)
            .vehicleid(DEFAULT_VEHICLEID)
            .ismobileappointment(DEFAULT_ISMOBILEAPPOINTMENT)
            .advancepayment(DEFAULT_ADVANCEPAYMENT)
            .jobid(DEFAULT_JOBID);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocareappointment createUpdatedEntity() {
        return new Autocareappointment()
            .appointmenttype(UPDATED_APPOINTMENTTYPE)
            .appointmentdate(UPDATED_APPOINTMENTDATE)
            .addeddate(UPDATED_ADDEDDATE)
            .conformdate(UPDATED_CONFORMDATE)
            .appointmentnumber(UPDATED_APPOINTMENTNUMBER)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .appointmenttime(UPDATED_APPOINTMENTTIME)
            .isconformed(UPDATED_ISCONFORMED)
            .conformedby(UPDATED_CONFORMEDBY)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .customerid(UPDATED_CUSTOMERID)
            .contactnumber(UPDATED_CONTACTNUMBER)
            .customername(UPDATED_CUSTOMERNAME)
            .issued(UPDATED_ISSUED)
            .hoistid(UPDATED_HOISTID)
            .isarrived(UPDATED_ISARRIVED)
            .iscancel(UPDATED_ISCANCEL)
            .isnoanswer(UPDATED_ISNOANSWER)
            .missedappointmentcall(UPDATED_MISSEDAPPOINTMENTCALL)
            .customermobileid(UPDATED_CUSTOMERMOBILEID)
            .customermobilevehicleid(UPDATED_CUSTOMERMOBILEVEHICLEID)
            .vehicleid(UPDATED_VEHICLEID)
            .ismobileappointment(UPDATED_ISMOBILEAPPOINTMENT)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .jobid(UPDATED_JOBID);
    }

    @BeforeEach
    public void initTest() {
        autocareappointment = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAutocareappointment != null) {
            autocareappointmentRepository.delete(insertedAutocareappointment);
            insertedAutocareappointment = null;
        }
    }

    @Test
    @Transactional
    void createAutocareappointment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocareappointment
        var returnedAutocareappointment = om.readValue(
            restAutocareappointmentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareappointment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocareappointment.class
        );

        // Validate the Autocareappointment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocareappointmentUpdatableFieldsEquals(
            returnedAutocareappointment,
            getPersistedAutocareappointment(returnedAutocareappointment)
        );

        insertedAutocareappointment = returnedAutocareappointment;
    }

    @Test
    @Transactional
    void createAutocareappointmentWithExistingId() throws Exception {
        // Create the Autocareappointment with an existing ID
        autocareappointment.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocareappointmentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareappointment)))
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocareappointments() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList
        restAutocareappointmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocareappointment.getId().intValue())))
            .andExpect(jsonPath("$.[*].appointmenttype").value(hasItem(DEFAULT_APPOINTMENTTYPE)))
            .andExpect(jsonPath("$.[*].appointmentdate").value(hasItem(DEFAULT_APPOINTMENTDATE.toString())))
            .andExpect(jsonPath("$.[*].addeddate").value(hasItem(DEFAULT_ADDEDDATE.toString())))
            .andExpect(jsonPath("$.[*].conformdate").value(hasItem(DEFAULT_CONFORMDATE.toString())))
            .andExpect(jsonPath("$.[*].appointmentnumber").value(hasItem(DEFAULT_APPOINTMENTNUMBER)))
            .andExpect(jsonPath("$.[*].vehiclenumber").value(hasItem(DEFAULT_VEHICLENUMBER)))
            .andExpect(jsonPath("$.[*].appointmenttime").value(hasItem(DEFAULT_APPOINTMENTTIME.toString())))
            .andExpect(jsonPath("$.[*].isconformed").value(hasItem(DEFAULT_ISCONFORMED.booleanValue())))
            .andExpect(jsonPath("$.[*].conformedby").value(hasItem(DEFAULT_CONFORMEDBY)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].contactnumber").value(hasItem(DEFAULT_CONTACTNUMBER)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].issued").value(hasItem(DEFAULT_ISSUED.booleanValue())))
            .andExpect(jsonPath("$.[*].hoistid").value(hasItem(DEFAULT_HOISTID)))
            .andExpect(jsonPath("$.[*].isarrived").value(hasItem(DEFAULT_ISARRIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].iscancel").value(hasItem(DEFAULT_ISCANCEL.booleanValue())))
            .andExpect(jsonPath("$.[*].isnoanswer").value(hasItem(DEFAULT_ISNOANSWER.booleanValue())))
            .andExpect(jsonPath("$.[*].missedappointmentcall").value(hasItem(DEFAULT_MISSEDAPPOINTMENTCALL)))
            .andExpect(jsonPath("$.[*].customermobileid").value(hasItem(DEFAULT_CUSTOMERMOBILEID)))
            .andExpect(jsonPath("$.[*].customermobilevehicleid").value(hasItem(DEFAULT_CUSTOMERMOBILEVEHICLEID)))
            .andExpect(jsonPath("$.[*].vehicleid").value(hasItem(DEFAULT_VEHICLEID)))
            .andExpect(jsonPath("$.[*].ismobileappointment").value(hasItem(DEFAULT_ISMOBILEAPPOINTMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].advancepayment").value(hasItem(DEFAULT_ADVANCEPAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)));
    }

    @Test
    @Transactional
    void getAutocareappointment() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get the autocareappointment
        restAutocareappointmentMockMvc
            .perform(get(ENTITY_API_URL_ID, autocareappointment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocareappointment.getId().intValue()))
            .andExpect(jsonPath("$.appointmenttype").value(DEFAULT_APPOINTMENTTYPE))
            .andExpect(jsonPath("$.appointmentdate").value(DEFAULT_APPOINTMENTDATE.toString()))
            .andExpect(jsonPath("$.addeddate").value(DEFAULT_ADDEDDATE.toString()))
            .andExpect(jsonPath("$.conformdate").value(DEFAULT_CONFORMDATE.toString()))
            .andExpect(jsonPath("$.appointmentnumber").value(DEFAULT_APPOINTMENTNUMBER))
            .andExpect(jsonPath("$.vehiclenumber").value(DEFAULT_VEHICLENUMBER))
            .andExpect(jsonPath("$.appointmenttime").value(DEFAULT_APPOINTMENTTIME.toString()))
            .andExpect(jsonPath("$.isconformed").value(DEFAULT_ISCONFORMED.booleanValue()))
            .andExpect(jsonPath("$.conformedby").value(DEFAULT_CONFORMEDBY))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.contactnumber").value(DEFAULT_CONTACTNUMBER))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.issued").value(DEFAULT_ISSUED.booleanValue()))
            .andExpect(jsonPath("$.hoistid").value(DEFAULT_HOISTID))
            .andExpect(jsonPath("$.isarrived").value(DEFAULT_ISARRIVED.booleanValue()))
            .andExpect(jsonPath("$.iscancel").value(DEFAULT_ISCANCEL.booleanValue()))
            .andExpect(jsonPath("$.isnoanswer").value(DEFAULT_ISNOANSWER.booleanValue()))
            .andExpect(jsonPath("$.missedappointmentcall").value(DEFAULT_MISSEDAPPOINTMENTCALL))
            .andExpect(jsonPath("$.customermobileid").value(DEFAULT_CUSTOMERMOBILEID))
            .andExpect(jsonPath("$.customermobilevehicleid").value(DEFAULT_CUSTOMERMOBILEVEHICLEID))
            .andExpect(jsonPath("$.vehicleid").value(DEFAULT_VEHICLEID))
            .andExpect(jsonPath("$.ismobileappointment").value(DEFAULT_ISMOBILEAPPOINTMENT.booleanValue()))
            .andExpect(jsonPath("$.advancepayment").value(DEFAULT_ADVANCEPAYMENT.doubleValue()))
            .andExpect(jsonPath("$.jobid").value(DEFAULT_JOBID));
    }

    @Test
    @Transactional
    void getAutocareappointmentsByIdFiltering() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        Long id = autocareappointment.getId();

        defaultAutocareappointmentFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultAutocareappointmentFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultAutocareappointmentFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype equals to
        defaultAutocareappointmentFiltering(
            "appointmenttype.equals=" + DEFAULT_APPOINTMENTTYPE,
            "appointmenttype.equals=" + UPDATED_APPOINTMENTTYPE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype in
        defaultAutocareappointmentFiltering(
            "appointmenttype.in=" + DEFAULT_APPOINTMENTTYPE + "," + UPDATED_APPOINTMENTTYPE,
            "appointmenttype.in=" + UPDATED_APPOINTMENTTYPE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype is not null
        defaultAutocareappointmentFiltering("appointmenttype.specified=true", "appointmenttype.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype is greater than or equal to
        defaultAutocareappointmentFiltering(
            "appointmenttype.greaterThanOrEqual=" + DEFAULT_APPOINTMENTTYPE,
            "appointmenttype.greaterThanOrEqual=" + UPDATED_APPOINTMENTTYPE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype is less than or equal to
        defaultAutocareappointmentFiltering(
            "appointmenttype.lessThanOrEqual=" + DEFAULT_APPOINTMENTTYPE,
            "appointmenttype.lessThanOrEqual=" + SMALLER_APPOINTMENTTYPE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype is less than
        defaultAutocareappointmentFiltering(
            "appointmenttype.lessThan=" + UPDATED_APPOINTMENTTYPE,
            "appointmenttype.lessThan=" + DEFAULT_APPOINTMENTTYPE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttypeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttype is greater than
        defaultAutocareappointmentFiltering(
            "appointmenttype.greaterThan=" + SMALLER_APPOINTMENTTYPE,
            "appointmenttype.greaterThan=" + DEFAULT_APPOINTMENTTYPE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentdateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentdate equals to
        defaultAutocareappointmentFiltering(
            "appointmentdate.equals=" + DEFAULT_APPOINTMENTDATE,
            "appointmentdate.equals=" + UPDATED_APPOINTMENTDATE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentdateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentdate in
        defaultAutocareappointmentFiltering(
            "appointmentdate.in=" + DEFAULT_APPOINTMENTDATE + "," + UPDATED_APPOINTMENTDATE,
            "appointmentdate.in=" + UPDATED_APPOINTMENTDATE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentdate is not null
        defaultAutocareappointmentFiltering("appointmentdate.specified=true", "appointmentdate.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAddeddateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where addeddate equals to
        defaultAutocareappointmentFiltering("addeddate.equals=" + DEFAULT_ADDEDDATE, "addeddate.equals=" + UPDATED_ADDEDDATE);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAddeddateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where addeddate in
        defaultAutocareappointmentFiltering(
            "addeddate.in=" + DEFAULT_ADDEDDATE + "," + UPDATED_ADDEDDATE,
            "addeddate.in=" + UPDATED_ADDEDDATE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAddeddateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where addeddate is not null
        defaultAutocareappointmentFiltering("addeddate.specified=true", "addeddate.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformdateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformdate equals to
        defaultAutocareappointmentFiltering("conformdate.equals=" + DEFAULT_CONFORMDATE, "conformdate.equals=" + UPDATED_CONFORMDATE);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformdateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformdate in
        defaultAutocareappointmentFiltering(
            "conformdate.in=" + DEFAULT_CONFORMDATE + "," + UPDATED_CONFORMDATE,
            "conformdate.in=" + UPDATED_CONFORMDATE
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformdate is not null
        defaultAutocareappointmentFiltering("conformdate.specified=true", "conformdate.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber equals to
        defaultAutocareappointmentFiltering(
            "appointmentnumber.equals=" + DEFAULT_APPOINTMENTNUMBER,
            "appointmentnumber.equals=" + UPDATED_APPOINTMENTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber in
        defaultAutocareappointmentFiltering(
            "appointmentnumber.in=" + DEFAULT_APPOINTMENTNUMBER + "," + UPDATED_APPOINTMENTNUMBER,
            "appointmentnumber.in=" + UPDATED_APPOINTMENTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber is not null
        defaultAutocareappointmentFiltering("appointmentnumber.specified=true", "appointmentnumber.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber is greater than or equal to
        defaultAutocareappointmentFiltering(
            "appointmentnumber.greaterThanOrEqual=" + DEFAULT_APPOINTMENTNUMBER,
            "appointmentnumber.greaterThanOrEqual=" + UPDATED_APPOINTMENTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber is less than or equal to
        defaultAutocareappointmentFiltering(
            "appointmentnumber.lessThanOrEqual=" + DEFAULT_APPOINTMENTNUMBER,
            "appointmentnumber.lessThanOrEqual=" + SMALLER_APPOINTMENTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber is less than
        defaultAutocareappointmentFiltering(
            "appointmentnumber.lessThan=" + UPDATED_APPOINTMENTNUMBER,
            "appointmentnumber.lessThan=" + DEFAULT_APPOINTMENTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmentnumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmentnumber is greater than
        defaultAutocareappointmentFiltering(
            "appointmentnumber.greaterThan=" + SMALLER_APPOINTMENTNUMBER,
            "appointmentnumber.greaterThan=" + DEFAULT_APPOINTMENTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehiclenumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehiclenumber equals to
        defaultAutocareappointmentFiltering(
            "vehiclenumber.equals=" + DEFAULT_VEHICLENUMBER,
            "vehiclenumber.equals=" + UPDATED_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehiclenumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehiclenumber in
        defaultAutocareappointmentFiltering(
            "vehiclenumber.in=" + DEFAULT_VEHICLENUMBER + "," + UPDATED_VEHICLENUMBER,
            "vehiclenumber.in=" + UPDATED_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehiclenumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehiclenumber is not null
        defaultAutocareappointmentFiltering("vehiclenumber.specified=true", "vehiclenumber.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehiclenumberContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehiclenumber contains
        defaultAutocareappointmentFiltering(
            "vehiclenumber.contains=" + DEFAULT_VEHICLENUMBER,
            "vehiclenumber.contains=" + UPDATED_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehiclenumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehiclenumber does not contain
        defaultAutocareappointmentFiltering(
            "vehiclenumber.doesNotContain=" + UPDATED_VEHICLENUMBER,
            "vehiclenumber.doesNotContain=" + DEFAULT_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttimeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttime equals to
        defaultAutocareappointmentFiltering(
            "appointmenttime.equals=" + DEFAULT_APPOINTMENTTIME,
            "appointmenttime.equals=" + UPDATED_APPOINTMENTTIME
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttimeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttime in
        defaultAutocareappointmentFiltering(
            "appointmenttime.in=" + DEFAULT_APPOINTMENTTIME + "," + UPDATED_APPOINTMENTTIME,
            "appointmenttime.in=" + UPDATED_APPOINTMENTTIME
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAppointmenttimeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where appointmenttime is not null
        defaultAutocareappointmentFiltering("appointmenttime.specified=true", "appointmenttime.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsconformedIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isconformed equals to
        defaultAutocareappointmentFiltering("isconformed.equals=" + DEFAULT_ISCONFORMED, "isconformed.equals=" + UPDATED_ISCONFORMED);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsconformedIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isconformed in
        defaultAutocareappointmentFiltering(
            "isconformed.in=" + DEFAULT_ISCONFORMED + "," + UPDATED_ISCONFORMED,
            "isconformed.in=" + UPDATED_ISCONFORMED
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsconformedIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isconformed is not null
        defaultAutocareappointmentFiltering("isconformed.specified=true", "isconformed.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby equals to
        defaultAutocareappointmentFiltering("conformedby.equals=" + DEFAULT_CONFORMEDBY, "conformedby.equals=" + UPDATED_CONFORMEDBY);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby in
        defaultAutocareappointmentFiltering(
            "conformedby.in=" + DEFAULT_CONFORMEDBY + "," + UPDATED_CONFORMEDBY,
            "conformedby.in=" + UPDATED_CONFORMEDBY
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby is not null
        defaultAutocareappointmentFiltering("conformedby.specified=true", "conformedby.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby is greater than or equal to
        defaultAutocareappointmentFiltering(
            "conformedby.greaterThanOrEqual=" + DEFAULT_CONFORMEDBY,
            "conformedby.greaterThanOrEqual=" + UPDATED_CONFORMEDBY
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby is less than or equal to
        defaultAutocareappointmentFiltering(
            "conformedby.lessThanOrEqual=" + DEFAULT_CONFORMEDBY,
            "conformedby.lessThanOrEqual=" + SMALLER_CONFORMEDBY
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby is less than
        defaultAutocareappointmentFiltering("conformedby.lessThan=" + UPDATED_CONFORMEDBY, "conformedby.lessThan=" + DEFAULT_CONFORMEDBY);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByConformedbyIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where conformedby is greater than
        defaultAutocareappointmentFiltering(
            "conformedby.greaterThan=" + SMALLER_CONFORMEDBY,
            "conformedby.greaterThan=" + DEFAULT_CONFORMEDBY
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmd equals to
        defaultAutocareappointmentFiltering("lmd.equals=" + DEFAULT_LMD, "lmd.equals=" + UPDATED_LMD);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmd in
        defaultAutocareappointmentFiltering("lmd.in=" + DEFAULT_LMD + "," + UPDATED_LMD, "lmd.in=" + UPDATED_LMD);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmd is not null
        defaultAutocareappointmentFiltering("lmd.specified=true", "lmd.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu equals to
        defaultAutocareappointmentFiltering("lmu.equals=" + DEFAULT_LMU, "lmu.equals=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu in
        defaultAutocareappointmentFiltering("lmu.in=" + DEFAULT_LMU + "," + UPDATED_LMU, "lmu.in=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu is not null
        defaultAutocareappointmentFiltering("lmu.specified=true", "lmu.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu is greater than or equal to
        defaultAutocareappointmentFiltering("lmu.greaterThanOrEqual=" + DEFAULT_LMU, "lmu.greaterThanOrEqual=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu is less than or equal to
        defaultAutocareappointmentFiltering("lmu.lessThanOrEqual=" + DEFAULT_LMU, "lmu.lessThanOrEqual=" + SMALLER_LMU);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu is less than
        defaultAutocareappointmentFiltering("lmu.lessThan=" + UPDATED_LMU, "lmu.lessThan=" + DEFAULT_LMU);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByLmuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where lmu is greater than
        defaultAutocareappointmentFiltering("lmu.greaterThan=" + SMALLER_LMU, "lmu.greaterThan=" + DEFAULT_LMU);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid equals to
        defaultAutocareappointmentFiltering("customerid.equals=" + DEFAULT_CUSTOMERID, "customerid.equals=" + UPDATED_CUSTOMERID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid in
        defaultAutocareappointmentFiltering(
            "customerid.in=" + DEFAULT_CUSTOMERID + "," + UPDATED_CUSTOMERID,
            "customerid.in=" + UPDATED_CUSTOMERID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid is not null
        defaultAutocareappointmentFiltering("customerid.specified=true", "customerid.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid is greater than or equal to
        defaultAutocareappointmentFiltering(
            "customerid.greaterThanOrEqual=" + DEFAULT_CUSTOMERID,
            "customerid.greaterThanOrEqual=" + UPDATED_CUSTOMERID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid is less than or equal to
        defaultAutocareappointmentFiltering(
            "customerid.lessThanOrEqual=" + DEFAULT_CUSTOMERID,
            "customerid.lessThanOrEqual=" + SMALLER_CUSTOMERID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid is less than
        defaultAutocareappointmentFiltering("customerid.lessThan=" + UPDATED_CUSTOMERID, "customerid.lessThan=" + DEFAULT_CUSTOMERID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomeridIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customerid is greater than
        defaultAutocareappointmentFiltering("customerid.greaterThan=" + SMALLER_CUSTOMERID, "customerid.greaterThan=" + DEFAULT_CUSTOMERID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByContactnumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where contactnumber equals to
        defaultAutocareappointmentFiltering(
            "contactnumber.equals=" + DEFAULT_CONTACTNUMBER,
            "contactnumber.equals=" + UPDATED_CONTACTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByContactnumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where contactnumber in
        defaultAutocareappointmentFiltering(
            "contactnumber.in=" + DEFAULT_CONTACTNUMBER + "," + UPDATED_CONTACTNUMBER,
            "contactnumber.in=" + UPDATED_CONTACTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByContactnumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where contactnumber is not null
        defaultAutocareappointmentFiltering("contactnumber.specified=true", "contactnumber.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByContactnumberContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where contactnumber contains
        defaultAutocareappointmentFiltering(
            "contactnumber.contains=" + DEFAULT_CONTACTNUMBER,
            "contactnumber.contains=" + UPDATED_CONTACTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByContactnumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where contactnumber does not contain
        defaultAutocareappointmentFiltering(
            "contactnumber.doesNotContain=" + UPDATED_CONTACTNUMBER,
            "contactnumber.doesNotContain=" + DEFAULT_CONTACTNUMBER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomernameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customername equals to
        defaultAutocareappointmentFiltering("customername.equals=" + DEFAULT_CUSTOMERNAME, "customername.equals=" + UPDATED_CUSTOMERNAME);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomernameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customername in
        defaultAutocareappointmentFiltering(
            "customername.in=" + DEFAULT_CUSTOMERNAME + "," + UPDATED_CUSTOMERNAME,
            "customername.in=" + UPDATED_CUSTOMERNAME
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomernameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customername is not null
        defaultAutocareappointmentFiltering("customername.specified=true", "customername.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomernameContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customername contains
        defaultAutocareappointmentFiltering(
            "customername.contains=" + DEFAULT_CUSTOMERNAME,
            "customername.contains=" + UPDATED_CUSTOMERNAME
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomernameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customername does not contain
        defaultAutocareappointmentFiltering(
            "customername.doesNotContain=" + UPDATED_CUSTOMERNAME,
            "customername.doesNotContain=" + DEFAULT_CUSTOMERNAME
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIssuedIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where issued equals to
        defaultAutocareappointmentFiltering("issued.equals=" + DEFAULT_ISSUED, "issued.equals=" + UPDATED_ISSUED);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIssuedIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where issued in
        defaultAutocareappointmentFiltering("issued.in=" + DEFAULT_ISSUED + "," + UPDATED_ISSUED, "issued.in=" + UPDATED_ISSUED);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIssuedIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where issued is not null
        defaultAutocareappointmentFiltering("issued.specified=true", "issued.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid equals to
        defaultAutocareappointmentFiltering("hoistid.equals=" + DEFAULT_HOISTID, "hoistid.equals=" + UPDATED_HOISTID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid in
        defaultAutocareappointmentFiltering("hoistid.in=" + DEFAULT_HOISTID + "," + UPDATED_HOISTID, "hoistid.in=" + UPDATED_HOISTID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid is not null
        defaultAutocareappointmentFiltering("hoistid.specified=true", "hoistid.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid is greater than or equal to
        defaultAutocareappointmentFiltering(
            "hoistid.greaterThanOrEqual=" + DEFAULT_HOISTID,
            "hoistid.greaterThanOrEqual=" + UPDATED_HOISTID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid is less than or equal to
        defaultAutocareappointmentFiltering("hoistid.lessThanOrEqual=" + DEFAULT_HOISTID, "hoistid.lessThanOrEqual=" + SMALLER_HOISTID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid is less than
        defaultAutocareappointmentFiltering("hoistid.lessThan=" + UPDATED_HOISTID, "hoistid.lessThan=" + DEFAULT_HOISTID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByHoistidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where hoistid is greater than
        defaultAutocareappointmentFiltering("hoistid.greaterThan=" + SMALLER_HOISTID, "hoistid.greaterThan=" + DEFAULT_HOISTID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsarrivedIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isarrived equals to
        defaultAutocareappointmentFiltering("isarrived.equals=" + DEFAULT_ISARRIVED, "isarrived.equals=" + UPDATED_ISARRIVED);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsarrivedIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isarrived in
        defaultAutocareappointmentFiltering(
            "isarrived.in=" + DEFAULT_ISARRIVED + "," + UPDATED_ISARRIVED,
            "isarrived.in=" + UPDATED_ISARRIVED
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsarrivedIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isarrived is not null
        defaultAutocareappointmentFiltering("isarrived.specified=true", "isarrived.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIscancelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where iscancel equals to
        defaultAutocareappointmentFiltering("iscancel.equals=" + DEFAULT_ISCANCEL, "iscancel.equals=" + UPDATED_ISCANCEL);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIscancelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where iscancel in
        defaultAutocareappointmentFiltering("iscancel.in=" + DEFAULT_ISCANCEL + "," + UPDATED_ISCANCEL, "iscancel.in=" + UPDATED_ISCANCEL);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIscancelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where iscancel is not null
        defaultAutocareappointmentFiltering("iscancel.specified=true", "iscancel.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsnoanswerIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isnoanswer equals to
        defaultAutocareappointmentFiltering("isnoanswer.equals=" + DEFAULT_ISNOANSWER, "isnoanswer.equals=" + UPDATED_ISNOANSWER);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsnoanswerIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isnoanswer in
        defaultAutocareappointmentFiltering(
            "isnoanswer.in=" + DEFAULT_ISNOANSWER + "," + UPDATED_ISNOANSWER,
            "isnoanswer.in=" + UPDATED_ISNOANSWER
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsnoanswerIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where isnoanswer is not null
        defaultAutocareappointmentFiltering("isnoanswer.specified=true", "isnoanswer.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByMissedappointmentcallIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where missedappointmentcall equals to
        defaultAutocareappointmentFiltering(
            "missedappointmentcall.equals=" + DEFAULT_MISSEDAPPOINTMENTCALL,
            "missedappointmentcall.equals=" + UPDATED_MISSEDAPPOINTMENTCALL
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByMissedappointmentcallIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where missedappointmentcall in
        defaultAutocareappointmentFiltering(
            "missedappointmentcall.in=" + DEFAULT_MISSEDAPPOINTMENTCALL + "," + UPDATED_MISSEDAPPOINTMENTCALL,
            "missedappointmentcall.in=" + UPDATED_MISSEDAPPOINTMENTCALL
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByMissedappointmentcallIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where missedappointmentcall is not null
        defaultAutocareappointmentFiltering("missedappointmentcall.specified=true", "missedappointmentcall.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByMissedappointmentcallContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where missedappointmentcall contains
        defaultAutocareappointmentFiltering(
            "missedappointmentcall.contains=" + DEFAULT_MISSEDAPPOINTMENTCALL,
            "missedappointmentcall.contains=" + UPDATED_MISSEDAPPOINTMENTCALL
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByMissedappointmentcallNotContainsSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where missedappointmentcall does not contain
        defaultAutocareappointmentFiltering(
            "missedappointmentcall.doesNotContain=" + UPDATED_MISSEDAPPOINTMENTCALL,
            "missedappointmentcall.doesNotContain=" + DEFAULT_MISSEDAPPOINTMENTCALL
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid equals to
        defaultAutocareappointmentFiltering(
            "customermobileid.equals=" + DEFAULT_CUSTOMERMOBILEID,
            "customermobileid.equals=" + UPDATED_CUSTOMERMOBILEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid in
        defaultAutocareappointmentFiltering(
            "customermobileid.in=" + DEFAULT_CUSTOMERMOBILEID + "," + UPDATED_CUSTOMERMOBILEID,
            "customermobileid.in=" + UPDATED_CUSTOMERMOBILEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid is not null
        defaultAutocareappointmentFiltering("customermobileid.specified=true", "customermobileid.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid is greater than or equal to
        defaultAutocareappointmentFiltering(
            "customermobileid.greaterThanOrEqual=" + DEFAULT_CUSTOMERMOBILEID,
            "customermobileid.greaterThanOrEqual=" + UPDATED_CUSTOMERMOBILEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid is less than or equal to
        defaultAutocareappointmentFiltering(
            "customermobileid.lessThanOrEqual=" + DEFAULT_CUSTOMERMOBILEID,
            "customermobileid.lessThanOrEqual=" + SMALLER_CUSTOMERMOBILEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid is less than
        defaultAutocareappointmentFiltering(
            "customermobileid.lessThan=" + UPDATED_CUSTOMERMOBILEID,
            "customermobileid.lessThan=" + DEFAULT_CUSTOMERMOBILEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobileidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobileid is greater than
        defaultAutocareappointmentFiltering(
            "customermobileid.greaterThan=" + SMALLER_CUSTOMERMOBILEID,
            "customermobileid.greaterThan=" + DEFAULT_CUSTOMERMOBILEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid equals to
        defaultAutocareappointmentFiltering(
            "customermobilevehicleid.equals=" + DEFAULT_CUSTOMERMOBILEVEHICLEID,
            "customermobilevehicleid.equals=" + UPDATED_CUSTOMERMOBILEVEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid in
        defaultAutocareappointmentFiltering(
            "customermobilevehicleid.in=" + DEFAULT_CUSTOMERMOBILEVEHICLEID + "," + UPDATED_CUSTOMERMOBILEVEHICLEID,
            "customermobilevehicleid.in=" + UPDATED_CUSTOMERMOBILEVEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid is not null
        defaultAutocareappointmentFiltering("customermobilevehicleid.specified=true", "customermobilevehicleid.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid is greater than or equal to
        defaultAutocareappointmentFiltering(
            "customermobilevehicleid.greaterThanOrEqual=" + DEFAULT_CUSTOMERMOBILEVEHICLEID,
            "customermobilevehicleid.greaterThanOrEqual=" + UPDATED_CUSTOMERMOBILEVEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid is less than or equal to
        defaultAutocareappointmentFiltering(
            "customermobilevehicleid.lessThanOrEqual=" + DEFAULT_CUSTOMERMOBILEVEHICLEID,
            "customermobilevehicleid.lessThanOrEqual=" + SMALLER_CUSTOMERMOBILEVEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid is less than
        defaultAutocareappointmentFiltering(
            "customermobilevehicleid.lessThan=" + UPDATED_CUSTOMERMOBILEVEHICLEID,
            "customermobilevehicleid.lessThan=" + DEFAULT_CUSTOMERMOBILEVEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByCustomermobilevehicleidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where customermobilevehicleid is greater than
        defaultAutocareappointmentFiltering(
            "customermobilevehicleid.greaterThan=" + SMALLER_CUSTOMERMOBILEVEHICLEID,
            "customermobilevehicleid.greaterThan=" + DEFAULT_CUSTOMERMOBILEVEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid equals to
        defaultAutocareappointmentFiltering("vehicleid.equals=" + DEFAULT_VEHICLEID, "vehicleid.equals=" + UPDATED_VEHICLEID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid in
        defaultAutocareappointmentFiltering(
            "vehicleid.in=" + DEFAULT_VEHICLEID + "," + UPDATED_VEHICLEID,
            "vehicleid.in=" + UPDATED_VEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid is not null
        defaultAutocareappointmentFiltering("vehicleid.specified=true", "vehicleid.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid is greater than or equal to
        defaultAutocareappointmentFiltering(
            "vehicleid.greaterThanOrEqual=" + DEFAULT_VEHICLEID,
            "vehicleid.greaterThanOrEqual=" + UPDATED_VEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid is less than or equal to
        defaultAutocareappointmentFiltering(
            "vehicleid.lessThanOrEqual=" + DEFAULT_VEHICLEID,
            "vehicleid.lessThanOrEqual=" + SMALLER_VEHICLEID
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid is less than
        defaultAutocareappointmentFiltering("vehicleid.lessThan=" + UPDATED_VEHICLEID, "vehicleid.lessThan=" + DEFAULT_VEHICLEID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByVehicleidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where vehicleid is greater than
        defaultAutocareappointmentFiltering("vehicleid.greaterThan=" + SMALLER_VEHICLEID, "vehicleid.greaterThan=" + DEFAULT_VEHICLEID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsmobileappointmentIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where ismobileappointment equals to
        defaultAutocareappointmentFiltering(
            "ismobileappointment.equals=" + DEFAULT_ISMOBILEAPPOINTMENT,
            "ismobileappointment.equals=" + UPDATED_ISMOBILEAPPOINTMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsmobileappointmentIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where ismobileappointment in
        defaultAutocareappointmentFiltering(
            "ismobileappointment.in=" + DEFAULT_ISMOBILEAPPOINTMENT + "," + UPDATED_ISMOBILEAPPOINTMENT,
            "ismobileappointment.in=" + UPDATED_ISMOBILEAPPOINTMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByIsmobileappointmentIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where ismobileappointment is not null
        defaultAutocareappointmentFiltering("ismobileappointment.specified=true", "ismobileappointment.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment equals to
        defaultAutocareappointmentFiltering(
            "advancepayment.equals=" + DEFAULT_ADVANCEPAYMENT,
            "advancepayment.equals=" + UPDATED_ADVANCEPAYMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment in
        defaultAutocareappointmentFiltering(
            "advancepayment.in=" + DEFAULT_ADVANCEPAYMENT + "," + UPDATED_ADVANCEPAYMENT,
            "advancepayment.in=" + UPDATED_ADVANCEPAYMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment is not null
        defaultAutocareappointmentFiltering("advancepayment.specified=true", "advancepayment.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment is greater than or equal to
        defaultAutocareappointmentFiltering(
            "advancepayment.greaterThanOrEqual=" + DEFAULT_ADVANCEPAYMENT,
            "advancepayment.greaterThanOrEqual=" + UPDATED_ADVANCEPAYMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment is less than or equal to
        defaultAutocareappointmentFiltering(
            "advancepayment.lessThanOrEqual=" + DEFAULT_ADVANCEPAYMENT,
            "advancepayment.lessThanOrEqual=" + SMALLER_ADVANCEPAYMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment is less than
        defaultAutocareappointmentFiltering(
            "advancepayment.lessThan=" + UPDATED_ADVANCEPAYMENT,
            "advancepayment.lessThan=" + DEFAULT_ADVANCEPAYMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByAdvancepaymentIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where advancepayment is greater than
        defaultAutocareappointmentFiltering(
            "advancepayment.greaterThan=" + SMALLER_ADVANCEPAYMENT,
            "advancepayment.greaterThan=" + DEFAULT_ADVANCEPAYMENT
        );
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid equals to
        defaultAutocareappointmentFiltering("jobid.equals=" + DEFAULT_JOBID, "jobid.equals=" + UPDATED_JOBID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid in
        defaultAutocareappointmentFiltering("jobid.in=" + DEFAULT_JOBID + "," + UPDATED_JOBID, "jobid.in=" + UPDATED_JOBID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid is not null
        defaultAutocareappointmentFiltering("jobid.specified=true", "jobid.specified=false");
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid is greater than or equal to
        defaultAutocareappointmentFiltering("jobid.greaterThanOrEqual=" + DEFAULT_JOBID, "jobid.greaterThanOrEqual=" + UPDATED_JOBID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid is less than or equal to
        defaultAutocareappointmentFiltering("jobid.lessThanOrEqual=" + DEFAULT_JOBID, "jobid.lessThanOrEqual=" + SMALLER_JOBID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid is less than
        defaultAutocareappointmentFiltering("jobid.lessThan=" + UPDATED_JOBID, "jobid.lessThan=" + DEFAULT_JOBID);
    }

    @Test
    @Transactional
    void getAllAutocareappointmentsByJobidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        // Get all the autocareappointmentList where jobid is greater than
        defaultAutocareappointmentFiltering("jobid.greaterThan=" + SMALLER_JOBID, "jobid.greaterThan=" + DEFAULT_JOBID);
    }

    private void defaultAutocareappointmentFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultAutocareappointmentShouldBeFound(shouldBeFound);
        defaultAutocareappointmentShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultAutocareappointmentShouldBeFound(String filter) throws Exception {
        restAutocareappointmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocareappointment.getId().intValue())))
            .andExpect(jsonPath("$.[*].appointmenttype").value(hasItem(DEFAULT_APPOINTMENTTYPE)))
            .andExpect(jsonPath("$.[*].appointmentdate").value(hasItem(DEFAULT_APPOINTMENTDATE.toString())))
            .andExpect(jsonPath("$.[*].addeddate").value(hasItem(DEFAULT_ADDEDDATE.toString())))
            .andExpect(jsonPath("$.[*].conformdate").value(hasItem(DEFAULT_CONFORMDATE.toString())))
            .andExpect(jsonPath("$.[*].appointmentnumber").value(hasItem(DEFAULT_APPOINTMENTNUMBER)))
            .andExpect(jsonPath("$.[*].vehiclenumber").value(hasItem(DEFAULT_VEHICLENUMBER)))
            .andExpect(jsonPath("$.[*].appointmenttime").value(hasItem(DEFAULT_APPOINTMENTTIME.toString())))
            .andExpect(jsonPath("$.[*].isconformed").value(hasItem(DEFAULT_ISCONFORMED.booleanValue())))
            .andExpect(jsonPath("$.[*].conformedby").value(hasItem(DEFAULT_CONFORMEDBY)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].contactnumber").value(hasItem(DEFAULT_CONTACTNUMBER)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].issued").value(hasItem(DEFAULT_ISSUED.booleanValue())))
            .andExpect(jsonPath("$.[*].hoistid").value(hasItem(DEFAULT_HOISTID)))
            .andExpect(jsonPath("$.[*].isarrived").value(hasItem(DEFAULT_ISARRIVED.booleanValue())))
            .andExpect(jsonPath("$.[*].iscancel").value(hasItem(DEFAULT_ISCANCEL.booleanValue())))
            .andExpect(jsonPath("$.[*].isnoanswer").value(hasItem(DEFAULT_ISNOANSWER.booleanValue())))
            .andExpect(jsonPath("$.[*].missedappointmentcall").value(hasItem(DEFAULT_MISSEDAPPOINTMENTCALL)))
            .andExpect(jsonPath("$.[*].customermobileid").value(hasItem(DEFAULT_CUSTOMERMOBILEID)))
            .andExpect(jsonPath("$.[*].customermobilevehicleid").value(hasItem(DEFAULT_CUSTOMERMOBILEVEHICLEID)))
            .andExpect(jsonPath("$.[*].vehicleid").value(hasItem(DEFAULT_VEHICLEID)))
            .andExpect(jsonPath("$.[*].ismobileappointment").value(hasItem(DEFAULT_ISMOBILEAPPOINTMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].advancepayment").value(hasItem(DEFAULT_ADVANCEPAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)));

        // Check, that the count call also returns 1
        restAutocareappointmentMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultAutocareappointmentShouldNotBeFound(String filter) throws Exception {
        restAutocareappointmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restAutocareappointmentMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingAutocareappointment() throws Exception {
        // Get the autocareappointment
        restAutocareappointmentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocareappointment() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareappointment
        Autocareappointment updatedAutocareappointment = autocareappointmentRepository.findById(autocareappointment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocareappointment are not directly saved in db
        em.detach(updatedAutocareappointment);
        updatedAutocareappointment
            .appointmenttype(UPDATED_APPOINTMENTTYPE)
            .appointmentdate(UPDATED_APPOINTMENTDATE)
            .addeddate(UPDATED_ADDEDDATE)
            .conformdate(UPDATED_CONFORMDATE)
            .appointmentnumber(UPDATED_APPOINTMENTNUMBER)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .appointmenttime(UPDATED_APPOINTMENTTIME)
            .isconformed(UPDATED_ISCONFORMED)
            .conformedby(UPDATED_CONFORMEDBY)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .customerid(UPDATED_CUSTOMERID)
            .contactnumber(UPDATED_CONTACTNUMBER)
            .customername(UPDATED_CUSTOMERNAME)
            .issued(UPDATED_ISSUED)
            .hoistid(UPDATED_HOISTID)
            .isarrived(UPDATED_ISARRIVED)
            .iscancel(UPDATED_ISCANCEL)
            .isnoanswer(UPDATED_ISNOANSWER)
            .missedappointmentcall(UPDATED_MISSEDAPPOINTMENTCALL)
            .customermobileid(UPDATED_CUSTOMERMOBILEID)
            .customermobilevehicleid(UPDATED_CUSTOMERMOBILEVEHICLEID)
            .vehicleid(UPDATED_VEHICLEID)
            .ismobileappointment(UPDATED_ISMOBILEAPPOINTMENT)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .jobid(UPDATED_JOBID);

        restAutocareappointmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocareappointment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocareappointment))
            )
            .andExpect(status().isOk());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocareappointmentToMatchAllProperties(updatedAutocareappointment);
    }

    @Test
    @Transactional
    void putNonExistingAutocareappointment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocareappointmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocareappointment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocareappointment))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocareappointment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocareappointment))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocareappointment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareappointment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocareappointmentWithPatch() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareappointment using partial update
        Autocareappointment partialUpdatedAutocareappointment = new Autocareappointment();
        partialUpdatedAutocareappointment.setId(autocareappointment.getId());

        partialUpdatedAutocareappointment
            .appointmentdate(UPDATED_APPOINTMENTDATE)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .contactnumber(UPDATED_CONTACTNUMBER)
            .issued(UPDATED_ISSUED)
            .hoistid(UPDATED_HOISTID)
            .isarrived(UPDATED_ISARRIVED)
            .iscancel(UPDATED_ISCANCEL)
            .missedappointmentcall(UPDATED_MISSEDAPPOINTMENTCALL)
            .customermobilevehicleid(UPDATED_CUSTOMERMOBILEVEHICLEID)
            .vehicleid(UPDATED_VEHICLEID)
            .jobid(UPDATED_JOBID);

        restAutocareappointmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocareappointment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocareappointment))
            )
            .andExpect(status().isOk());

        // Validate the Autocareappointment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocareappointmentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocareappointment, autocareappointment),
            getPersistedAutocareappointment(autocareappointment)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocareappointmentWithPatch() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareappointment using partial update
        Autocareappointment partialUpdatedAutocareappointment = new Autocareappointment();
        partialUpdatedAutocareappointment.setId(autocareappointment.getId());

        partialUpdatedAutocareappointment
            .appointmenttype(UPDATED_APPOINTMENTTYPE)
            .appointmentdate(UPDATED_APPOINTMENTDATE)
            .addeddate(UPDATED_ADDEDDATE)
            .conformdate(UPDATED_CONFORMDATE)
            .appointmentnumber(UPDATED_APPOINTMENTNUMBER)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .appointmenttime(UPDATED_APPOINTMENTTIME)
            .isconformed(UPDATED_ISCONFORMED)
            .conformedby(UPDATED_CONFORMEDBY)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .customerid(UPDATED_CUSTOMERID)
            .contactnumber(UPDATED_CONTACTNUMBER)
            .customername(UPDATED_CUSTOMERNAME)
            .issued(UPDATED_ISSUED)
            .hoistid(UPDATED_HOISTID)
            .isarrived(UPDATED_ISARRIVED)
            .iscancel(UPDATED_ISCANCEL)
            .isnoanswer(UPDATED_ISNOANSWER)
            .missedappointmentcall(UPDATED_MISSEDAPPOINTMENTCALL)
            .customermobileid(UPDATED_CUSTOMERMOBILEID)
            .customermobilevehicleid(UPDATED_CUSTOMERMOBILEVEHICLEID)
            .vehicleid(UPDATED_VEHICLEID)
            .ismobileappointment(UPDATED_ISMOBILEAPPOINTMENT)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .jobid(UPDATED_JOBID);

        restAutocareappointmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocareappointment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocareappointment))
            )
            .andExpect(status().isOk());

        // Validate the Autocareappointment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocareappointmentUpdatableFieldsEquals(
            partialUpdatedAutocareappointment,
            getPersistedAutocareappointment(partialUpdatedAutocareappointment)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocareappointment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointment.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocareappointmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocareappointment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocareappointment))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocareappointment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocareappointment))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocareappointment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointment.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocareappointment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocareappointment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocareappointment() throws Exception {
        // Initialize the database
        insertedAutocareappointment = autocareappointmentRepository.saveAndFlush(autocareappointment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocareappointment
        restAutocareappointmentMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocareappointment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocareappointmentRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Autocareappointment getPersistedAutocareappointment(Autocareappointment autocareappointment) {
        return autocareappointmentRepository.findById(autocareappointment.getId()).orElseThrow();
    }

    protected void assertPersistedAutocareappointmentToMatchAllProperties(Autocareappointment expectedAutocareappointment) {
        assertAutocareappointmentAllPropertiesEquals(
            expectedAutocareappointment,
            getPersistedAutocareappointment(expectedAutocareappointment)
        );
    }

    protected void assertPersistedAutocareappointmentToMatchUpdatableProperties(Autocareappointment expectedAutocareappointment) {
        assertAutocareappointmentAllUpdatablePropertiesEquals(
            expectedAutocareappointment,
            getPersistedAutocareappointment(expectedAutocareappointment)
        );
    }
}
