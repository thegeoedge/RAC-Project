package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocarejobAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocarejob;
import com.heavenscode.rac.repository.AutocarejobRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AutocarejobResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocarejobResourceIT {

    private static final Integer DEFAULT_JOBNUMBER = 1;
    private static final Integer UPDATED_JOBNUMBER = 2;

    private static final Integer DEFAULT_VEHICLEID = 1;
    private static final Integer UPDATED_VEHICLEID = 2;

    private static final String DEFAULT_VEHICLENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLENUMBER = "BBBBBBBBBB";

    private static final Float DEFAULT_MILLAGE = 1F;
    private static final Float UPDATED_MILLAGE = 2F;

    private static final Float DEFAULT_NEXTMILLAGE = 1F;
    private static final Float UPDATED_NEXTMILLAGE = 2F;

    private static final LocalDate DEFAULT_NEXTSERVICEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEXTSERVICEDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_VEHICLETYPEID = 1;
    private static final Integer UPDATED_VEHICLETYPEID = 2;

    private static final Integer DEFAULT_JOBTYPEID = 1;
    private static final Integer UPDATED_JOBTYPEID = 2;

    private static final String DEFAULT_JOBTYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_JOBTYPENAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_JOBOPENBY = 1;
    private static final Integer UPDATED_JOBOPENBY = 2;

    private static final Instant DEFAULT_JOBOPENTIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOBOPENTIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SPECIALRQUIRMENTS = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALRQUIRMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALINSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALINSTRUCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_NEXTSERVICEINSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_NEXTSERVICEINSTRUCTIONS = "BBBBBBBBBB";

    private static final String DEFAULT_LASTSERVICEINSTRUCTIONS = "AAAAAAAAAA";
    private static final String UPDATED_LASTSERVICEINSTRUCTIONS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISADVISORCHECKED = false;
    private static final Boolean UPDATED_ISADVISORCHECKED = true;

    private static final Boolean DEFAULT_ISEMPALLOCATED = false;
    private static final Boolean UPDATED_ISEMPALLOCATED = true;

    private static final Instant DEFAULT_JOBCLOSETIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOBCLOSETIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISJOBCLOSE = false;
    private static final Boolean UPDATED_ISJOBCLOSE = true;

    private static final Boolean DEFAULT_ISFEEDBACK = false;
    private static final Boolean UPDATED_ISFEEDBACK = true;

    private static final Integer DEFAULT_FEEDBACKSTATUSID = 1;
    private static final Integer UPDATED_FEEDBACKSTATUSID = 2;

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERTEL = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERTEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;

    private static final Boolean DEFAULT_ADVISORFINALCHECK = false;
    private static final Boolean UPDATED_ADVISORFINALCHECK = true;

    private static final Instant DEFAULT_JOBDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOBDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISCOMPANYSERVICE = false;
    private static final Boolean UPDATED_ISCOMPANYSERVICE = true;

    private static final String DEFAULT_FREESERVICENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_FREESERVICENUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_COMPANYID = 1;
    private static final Integer UPDATED_COMPANYID = 2;

    private static final Boolean DEFAULT_UPDATETOCUSTOMER = false;
    private static final Boolean UPDATED_UPDATETOCUSTOMER = true;

    private static final String DEFAULT_NEXTGEAROILMILAGE = "AAAAAAAAAA";
    private static final String UPDATED_NEXTGEAROILMILAGE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISJOBINVOICED = false;
    private static final Boolean UPDATED_ISJOBINVOICED = true;

    private static final Boolean DEFAULT_ISWAITING = false;
    private static final Boolean UPDATED_ISWAITING = true;

    private static final Boolean DEFAULT_ISCUSTOMERCOMMENT = false;
    private static final Boolean UPDATED_ISCUSTOMERCOMMENT = true;

    private static final String DEFAULT_IMAGEFOLDER = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEFOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_FRONTIMAGE = "AAAAAAAAAA";
    private static final String UPDATED_FRONTIMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_LEFTIMAGE = "AAAAAAAAAA";
    private static final String UPDATED_LEFTIMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_RIGHTIMAGE = "AAAAAAAAAA";
    private static final String UPDATED_RIGHTIMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_BACKIMAGE = "AAAAAAAAAA";
    private static final String UPDATED_BACKIMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_DASHBOARDIMAGE = "AAAAAAAAAA";
    private static final String UPDATED_DASHBOARDIMAGE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/autocarejobs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocarejobRepository autocarejobRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocarejobMockMvc;

    private Autocarejob autocarejob;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarejob createEntity(EntityManager em) {
        Autocarejob autocarejob = new Autocarejob()
            .jobnumber(DEFAULT_JOBNUMBER)
            .vehicleid(DEFAULT_VEHICLEID)
            .vehiclenumber(DEFAULT_VEHICLENUMBER)
            .millage(DEFAULT_MILLAGE)
            .nextmillage(DEFAULT_NEXTMILLAGE)
            .nextservicedate(DEFAULT_NEXTSERVICEDATE)
            .vehicletypeid(DEFAULT_VEHICLETYPEID)
            .jobtypeid(DEFAULT_JOBTYPEID)
            .jobtypename(DEFAULT_JOBTYPENAME)
            .jobopenby(DEFAULT_JOBOPENBY)
            .jobopentime(DEFAULT_JOBOPENTIME)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .specialrquirments(DEFAULT_SPECIALRQUIRMENTS)
            .specialinstructions(DEFAULT_SPECIALINSTRUCTIONS)
            .remarks(DEFAULT_REMARKS)
            .nextserviceinstructions(DEFAULT_NEXTSERVICEINSTRUCTIONS)
            .lastserviceinstructions(DEFAULT_LASTSERVICEINSTRUCTIONS)
            .isadvisorchecked(DEFAULT_ISADVISORCHECKED)
            .isempallocated(DEFAULT_ISEMPALLOCATED)
            .jobclosetime(DEFAULT_JOBCLOSETIME)
            .isjobclose(DEFAULT_ISJOBCLOSE)
            .isfeedback(DEFAULT_ISFEEDBACK)
            .feedbackstatusid(DEFAULT_FEEDBACKSTATUSID)
            .customername(DEFAULT_CUSTOMERNAME)
            .customertel(DEFAULT_CUSTOMERTEL)
            .customerid(DEFAULT_CUSTOMERID)
            .advisorfinalcheck(DEFAULT_ADVISORFINALCHECK)
            .jobdate(DEFAULT_JOBDATE)
            .iscompanyservice(DEFAULT_ISCOMPANYSERVICE)
            .freeservicenumber(DEFAULT_FREESERVICENUMBER)
            .companyid(DEFAULT_COMPANYID)
            .updatetocustomer(DEFAULT_UPDATETOCUSTOMER)
            .nextgearoilmilage(DEFAULT_NEXTGEAROILMILAGE)
            .isjobinvoiced(DEFAULT_ISJOBINVOICED)
            .iswaiting(DEFAULT_ISWAITING)
            .iscustomercomment(DEFAULT_ISCUSTOMERCOMMENT)
            .imagefolder(DEFAULT_IMAGEFOLDER)
            .frontimage(DEFAULT_FRONTIMAGE)
            .leftimage(DEFAULT_LEFTIMAGE)
            .rightimage(DEFAULT_RIGHTIMAGE)
            .backimage(DEFAULT_BACKIMAGE)
            .dashboardimage(DEFAULT_DASHBOARDIMAGE);
        return autocarejob;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarejob createUpdatedEntity(EntityManager em) {
        Autocarejob autocarejob = new Autocarejob()
            .jobnumber(UPDATED_JOBNUMBER)
            .vehicleid(UPDATED_VEHICLEID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .millage(UPDATED_MILLAGE)
            .nextmillage(UPDATED_NEXTMILLAGE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .vehicletypeid(UPDATED_VEHICLETYPEID)
            .jobtypeid(UPDATED_JOBTYPEID)
            .jobtypename(UPDATED_JOBTYPENAME)
            .jobopenby(UPDATED_JOBOPENBY)
            .jobopentime(UPDATED_JOBOPENTIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .specialrquirments(UPDATED_SPECIALRQUIRMENTS)
            .specialinstructions(UPDATED_SPECIALINSTRUCTIONS)
            .remarks(UPDATED_REMARKS)
            .nextserviceinstructions(UPDATED_NEXTSERVICEINSTRUCTIONS)
            .lastserviceinstructions(UPDATED_LASTSERVICEINSTRUCTIONS)
            .isadvisorchecked(UPDATED_ISADVISORCHECKED)
            .isempallocated(UPDATED_ISEMPALLOCATED)
            .jobclosetime(UPDATED_JOBCLOSETIME)
            .isjobclose(UPDATED_ISJOBCLOSE)
            .isfeedback(UPDATED_ISFEEDBACK)
            .feedbackstatusid(UPDATED_FEEDBACKSTATUSID)
            .customername(UPDATED_CUSTOMERNAME)
            .customertel(UPDATED_CUSTOMERTEL)
            .customerid(UPDATED_CUSTOMERID)
            .advisorfinalcheck(UPDATED_ADVISORFINALCHECK)
            .jobdate(UPDATED_JOBDATE)
            .iscompanyservice(UPDATED_ISCOMPANYSERVICE)
            .freeservicenumber(UPDATED_FREESERVICENUMBER)
            .companyid(UPDATED_COMPANYID)
            .updatetocustomer(UPDATED_UPDATETOCUSTOMER)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .isjobinvoiced(UPDATED_ISJOBINVOICED)
            .iswaiting(UPDATED_ISWAITING)
            .iscustomercomment(UPDATED_ISCUSTOMERCOMMENT)
            .imagefolder(UPDATED_IMAGEFOLDER)
            .frontimage(UPDATED_FRONTIMAGE)
            .leftimage(UPDATED_LEFTIMAGE)
            .rightimage(UPDATED_RIGHTIMAGE)
            .backimage(UPDATED_BACKIMAGE)
            .dashboardimage(UPDATED_DASHBOARDIMAGE);
        return autocarejob;
    }

    @BeforeEach
    public void initTest() {
        autocarejob = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocarejob() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocarejob
        var returnedAutocarejob = om.readValue(
            restAutocarejobMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejob)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocarejob.class
        );

        // Validate the Autocarejob in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocarejobUpdatableFieldsEquals(returnedAutocarejob, getPersistedAutocarejob(returnedAutocarejob));
    }

    @Test
    @Transactional
    void createAutocarejobWithExistingId() throws Exception {
        // Create the Autocarejob with an existing ID
        autocarejob.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocarejobMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejob)))
            .andExpect(status().isBadRequest());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocarejobs() throws Exception {
        // Initialize the database
        autocarejobRepository.saveAndFlush(autocarejob);

        // Get all the autocarejobList
        restAutocarejobMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocarejob.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobnumber").value(hasItem(DEFAULT_JOBNUMBER)))
            .andExpect(jsonPath("$.[*].vehicleid").value(hasItem(DEFAULT_VEHICLEID)))
            .andExpect(jsonPath("$.[*].vehiclenumber").value(hasItem(DEFAULT_VEHICLENUMBER)))
            .andExpect(jsonPath("$.[*].millage").value(hasItem(DEFAULT_MILLAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].nextmillage").value(hasItem(DEFAULT_NEXTMILLAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].nextservicedate").value(hasItem(DEFAULT_NEXTSERVICEDATE.toString())))
            .andExpect(jsonPath("$.[*].vehicletypeid").value(hasItem(DEFAULT_VEHICLETYPEID)))
            .andExpect(jsonPath("$.[*].jobtypeid").value(hasItem(DEFAULT_JOBTYPEID)))
            .andExpect(jsonPath("$.[*].jobtypename").value(hasItem(DEFAULT_JOBTYPENAME)))
            .andExpect(jsonPath("$.[*].jobopenby").value(hasItem(DEFAULT_JOBOPENBY)))
            .andExpect(jsonPath("$.[*].jobopentime").value(hasItem(DEFAULT_JOBOPENTIME.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].specialrquirments").value(hasItem(DEFAULT_SPECIALRQUIRMENTS)))
            .andExpect(jsonPath("$.[*].specialinstructions").value(hasItem(DEFAULT_SPECIALINSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].nextserviceinstructions").value(hasItem(DEFAULT_NEXTSERVICEINSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].lastserviceinstructions").value(hasItem(DEFAULT_LASTSERVICEINSTRUCTIONS)))
            .andExpect(jsonPath("$.[*].isadvisorchecked").value(hasItem(DEFAULT_ISADVISORCHECKED.booleanValue())))
            .andExpect(jsonPath("$.[*].isempallocated").value(hasItem(DEFAULT_ISEMPALLOCATED.booleanValue())))
            .andExpect(jsonPath("$.[*].jobclosetime").value(hasItem(DEFAULT_JOBCLOSETIME.toString())))
            .andExpect(jsonPath("$.[*].isjobclose").value(hasItem(DEFAULT_ISJOBCLOSE.booleanValue())))
            .andExpect(jsonPath("$.[*].isfeedback").value(hasItem(DEFAULT_ISFEEDBACK.booleanValue())))
            .andExpect(jsonPath("$.[*].feedbackstatusid").value(hasItem(DEFAULT_FEEDBACKSTATUSID)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].customertel").value(hasItem(DEFAULT_CUSTOMERTEL)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].advisorfinalcheck").value(hasItem(DEFAULT_ADVISORFINALCHECK.booleanValue())))
            .andExpect(jsonPath("$.[*].jobdate").value(hasItem(DEFAULT_JOBDATE.toString())))
            .andExpect(jsonPath("$.[*].iscompanyservice").value(hasItem(DEFAULT_ISCOMPANYSERVICE.booleanValue())))
            .andExpect(jsonPath("$.[*].freeservicenumber").value(hasItem(DEFAULT_FREESERVICENUMBER)))
            .andExpect(jsonPath("$.[*].companyid").value(hasItem(DEFAULT_COMPANYID)))
            .andExpect(jsonPath("$.[*].updatetocustomer").value(hasItem(DEFAULT_UPDATETOCUSTOMER.booleanValue())))
            .andExpect(jsonPath("$.[*].nextgearoilmilage").value(hasItem(DEFAULT_NEXTGEAROILMILAGE)))
            .andExpect(jsonPath("$.[*].isjobinvoiced").value(hasItem(DEFAULT_ISJOBINVOICED.booleanValue())))
            .andExpect(jsonPath("$.[*].iswaiting").value(hasItem(DEFAULT_ISWAITING.booleanValue())))
            .andExpect(jsonPath("$.[*].iscustomercomment").value(hasItem(DEFAULT_ISCUSTOMERCOMMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].imagefolder").value(hasItem(DEFAULT_IMAGEFOLDER)))
            .andExpect(jsonPath("$.[*].frontimage").value(hasItem(DEFAULT_FRONTIMAGE)))
            .andExpect(jsonPath("$.[*].leftimage").value(hasItem(DEFAULT_LEFTIMAGE)))
            .andExpect(jsonPath("$.[*].rightimage").value(hasItem(DEFAULT_RIGHTIMAGE)))
            .andExpect(jsonPath("$.[*].backimage").value(hasItem(DEFAULT_BACKIMAGE)))
            .andExpect(jsonPath("$.[*].dashboardimage").value(hasItem(DEFAULT_DASHBOARDIMAGE)));
    }

    @Test
    @Transactional
    void getAutocarejob() throws Exception {
        // Initialize the database
        autocarejobRepository.saveAndFlush(autocarejob);

        // Get the autocarejob
        restAutocarejobMockMvc
            .perform(get(ENTITY_API_URL_ID, autocarejob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocarejob.getId().intValue()))
            .andExpect(jsonPath("$.jobnumber").value(DEFAULT_JOBNUMBER))
            .andExpect(jsonPath("$.vehicleid").value(DEFAULT_VEHICLEID))
            .andExpect(jsonPath("$.vehiclenumber").value(DEFAULT_VEHICLENUMBER))
            .andExpect(jsonPath("$.millage").value(DEFAULT_MILLAGE.doubleValue()))
            .andExpect(jsonPath("$.nextmillage").value(DEFAULT_NEXTMILLAGE.doubleValue()))
            .andExpect(jsonPath("$.nextservicedate").value(DEFAULT_NEXTSERVICEDATE.toString()))
            .andExpect(jsonPath("$.vehicletypeid").value(DEFAULT_VEHICLETYPEID))
            .andExpect(jsonPath("$.jobtypeid").value(DEFAULT_JOBTYPEID))
            .andExpect(jsonPath("$.jobtypename").value(DEFAULT_JOBTYPENAME))
            .andExpect(jsonPath("$.jobopenby").value(DEFAULT_JOBOPENBY))
            .andExpect(jsonPath("$.jobopentime").value(DEFAULT_JOBOPENTIME.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.specialrquirments").value(DEFAULT_SPECIALRQUIRMENTS))
            .andExpect(jsonPath("$.specialinstructions").value(DEFAULT_SPECIALINSTRUCTIONS))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.nextserviceinstructions").value(DEFAULT_NEXTSERVICEINSTRUCTIONS))
            .andExpect(jsonPath("$.lastserviceinstructions").value(DEFAULT_LASTSERVICEINSTRUCTIONS))
            .andExpect(jsonPath("$.isadvisorchecked").value(DEFAULT_ISADVISORCHECKED.booleanValue()))
            .andExpect(jsonPath("$.isempallocated").value(DEFAULT_ISEMPALLOCATED.booleanValue()))
            .andExpect(jsonPath("$.jobclosetime").value(DEFAULT_JOBCLOSETIME.toString()))
            .andExpect(jsonPath("$.isjobclose").value(DEFAULT_ISJOBCLOSE.booleanValue()))
            .andExpect(jsonPath("$.isfeedback").value(DEFAULT_ISFEEDBACK.booleanValue()))
            .andExpect(jsonPath("$.feedbackstatusid").value(DEFAULT_FEEDBACKSTATUSID))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.customertel").value(DEFAULT_CUSTOMERTEL))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.advisorfinalcheck").value(DEFAULT_ADVISORFINALCHECK.booleanValue()))
            .andExpect(jsonPath("$.jobdate").value(DEFAULT_JOBDATE.toString()))
            .andExpect(jsonPath("$.iscompanyservice").value(DEFAULT_ISCOMPANYSERVICE.booleanValue()))
            .andExpect(jsonPath("$.freeservicenumber").value(DEFAULT_FREESERVICENUMBER))
            .andExpect(jsonPath("$.companyid").value(DEFAULT_COMPANYID))
            .andExpect(jsonPath("$.updatetocustomer").value(DEFAULT_UPDATETOCUSTOMER.booleanValue()))
            .andExpect(jsonPath("$.nextgearoilmilage").value(DEFAULT_NEXTGEAROILMILAGE))
            .andExpect(jsonPath("$.isjobinvoiced").value(DEFAULT_ISJOBINVOICED.booleanValue()))
            .andExpect(jsonPath("$.iswaiting").value(DEFAULT_ISWAITING.booleanValue()))
            .andExpect(jsonPath("$.iscustomercomment").value(DEFAULT_ISCUSTOMERCOMMENT.booleanValue()))
            .andExpect(jsonPath("$.imagefolder").value(DEFAULT_IMAGEFOLDER))
            .andExpect(jsonPath("$.frontimage").value(DEFAULT_FRONTIMAGE))
            .andExpect(jsonPath("$.leftimage").value(DEFAULT_LEFTIMAGE))
            .andExpect(jsonPath("$.rightimage").value(DEFAULT_RIGHTIMAGE))
            .andExpect(jsonPath("$.backimage").value(DEFAULT_BACKIMAGE))
            .andExpect(jsonPath("$.dashboardimage").value(DEFAULT_DASHBOARDIMAGE));
    }

    @Test
    @Transactional
    void getNonExistingAutocarejob() throws Exception {
        // Get the autocarejob
        restAutocarejobMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocarejob() throws Exception {
        // Initialize the database
        autocarejobRepository.saveAndFlush(autocarejob);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejob
        Autocarejob updatedAutocarejob = autocarejobRepository.findById(autocarejob.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocarejob are not directly saved in db
        em.detach(updatedAutocarejob);
        updatedAutocarejob
            .jobnumber(UPDATED_JOBNUMBER)
            .vehicleid(UPDATED_VEHICLEID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .millage(UPDATED_MILLAGE)
            .nextmillage(UPDATED_NEXTMILLAGE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .vehicletypeid(UPDATED_VEHICLETYPEID)
            .jobtypeid(UPDATED_JOBTYPEID)
            .jobtypename(UPDATED_JOBTYPENAME)
            .jobopenby(UPDATED_JOBOPENBY)
            .jobopentime(UPDATED_JOBOPENTIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .specialrquirments(UPDATED_SPECIALRQUIRMENTS)
            .specialinstructions(UPDATED_SPECIALINSTRUCTIONS)
            .remarks(UPDATED_REMARKS)
            .nextserviceinstructions(UPDATED_NEXTSERVICEINSTRUCTIONS)
            .lastserviceinstructions(UPDATED_LASTSERVICEINSTRUCTIONS)
            .isadvisorchecked(UPDATED_ISADVISORCHECKED)
            .isempallocated(UPDATED_ISEMPALLOCATED)
            .jobclosetime(UPDATED_JOBCLOSETIME)
            .isjobclose(UPDATED_ISJOBCLOSE)
            .isfeedback(UPDATED_ISFEEDBACK)
            .feedbackstatusid(UPDATED_FEEDBACKSTATUSID)
            .customername(UPDATED_CUSTOMERNAME)
            .customertel(UPDATED_CUSTOMERTEL)
            .customerid(UPDATED_CUSTOMERID)
            .advisorfinalcheck(UPDATED_ADVISORFINALCHECK)
            .jobdate(UPDATED_JOBDATE)
            .iscompanyservice(UPDATED_ISCOMPANYSERVICE)
            .freeservicenumber(UPDATED_FREESERVICENUMBER)
            .companyid(UPDATED_COMPANYID)
            .updatetocustomer(UPDATED_UPDATETOCUSTOMER)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .isjobinvoiced(UPDATED_ISJOBINVOICED)
            .iswaiting(UPDATED_ISWAITING)
            .iscustomercomment(UPDATED_ISCUSTOMERCOMMENT)
            .imagefolder(UPDATED_IMAGEFOLDER)
            .frontimage(UPDATED_FRONTIMAGE)
            .leftimage(UPDATED_LEFTIMAGE)
            .rightimage(UPDATED_RIGHTIMAGE)
            .backimage(UPDATED_BACKIMAGE)
            .dashboardimage(UPDATED_DASHBOARDIMAGE);

        restAutocarejobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocarejob.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocarejob))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocarejobToMatchAllProperties(updatedAutocarejob);
    }

    @Test
    @Transactional
    void putNonExistingAutocarejob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejob.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarejobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocarejob.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarejob))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocarejob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejob.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarejob))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocarejob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejob.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejob)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocarejobWithPatch() throws Exception {
        // Initialize the database
        autocarejobRepository.saveAndFlush(autocarejob);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejob using partial update
        Autocarejob partialUpdatedAutocarejob = new Autocarejob();
        partialUpdatedAutocarejob.setId(autocarejob.getId());

        partialUpdatedAutocarejob
            .jobnumber(UPDATED_JOBNUMBER)
            .vehicleid(UPDATED_VEHICLEID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .jobtypeid(UPDATED_JOBTYPEID)
            .jobopenby(UPDATED_JOBOPENBY)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .specialinstructions(UPDATED_SPECIALINSTRUCTIONS)
            .remarks(UPDATED_REMARKS)
            .lastserviceinstructions(UPDATED_LASTSERVICEINSTRUCTIONS)
            .isempallocated(UPDATED_ISEMPALLOCATED)
            .isjobclose(UPDATED_ISJOBCLOSE)
            .isfeedback(UPDATED_ISFEEDBACK)
            .feedbackstatusid(UPDATED_FEEDBACKSTATUSID)
            .customername(UPDATED_CUSTOMERNAME)
            .customerid(UPDATED_CUSTOMERID)
            .advisorfinalcheck(UPDATED_ADVISORFINALCHECK)
            .jobdate(UPDATED_JOBDATE)
            .iscompanyservice(UPDATED_ISCOMPANYSERVICE)
            .freeservicenumber(UPDATED_FREESERVICENUMBER)
            .companyid(UPDATED_COMPANYID)
            .updatetocustomer(UPDATED_UPDATETOCUSTOMER)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .iscustomercomment(UPDATED_ISCUSTOMERCOMMENT)
            .frontimage(UPDATED_FRONTIMAGE)
            .dashboardimage(UPDATED_DASHBOARDIMAGE);

        restAutocarejobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarejob.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarejob))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejob in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarejobUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocarejob, autocarejob),
            getPersistedAutocarejob(autocarejob)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocarejobWithPatch() throws Exception {
        // Initialize the database
        autocarejobRepository.saveAndFlush(autocarejob);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejob using partial update
        Autocarejob partialUpdatedAutocarejob = new Autocarejob();
        partialUpdatedAutocarejob.setId(autocarejob.getId());

        partialUpdatedAutocarejob
            .jobnumber(UPDATED_JOBNUMBER)
            .vehicleid(UPDATED_VEHICLEID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .millage(UPDATED_MILLAGE)
            .nextmillage(UPDATED_NEXTMILLAGE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .vehicletypeid(UPDATED_VEHICLETYPEID)
            .jobtypeid(UPDATED_JOBTYPEID)
            .jobtypename(UPDATED_JOBTYPENAME)
            .jobopenby(UPDATED_JOBOPENBY)
            .jobopentime(UPDATED_JOBOPENTIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .specialrquirments(UPDATED_SPECIALRQUIRMENTS)
            .specialinstructions(UPDATED_SPECIALINSTRUCTIONS)
            .remarks(UPDATED_REMARKS)
            .nextserviceinstructions(UPDATED_NEXTSERVICEINSTRUCTIONS)
            .lastserviceinstructions(UPDATED_LASTSERVICEINSTRUCTIONS)
            .isadvisorchecked(UPDATED_ISADVISORCHECKED)
            .isempallocated(UPDATED_ISEMPALLOCATED)
            .jobclosetime(UPDATED_JOBCLOSETIME)
            .isjobclose(UPDATED_ISJOBCLOSE)
            .isfeedback(UPDATED_ISFEEDBACK)
            .feedbackstatusid(UPDATED_FEEDBACKSTATUSID)
            .customername(UPDATED_CUSTOMERNAME)
            .customertel(UPDATED_CUSTOMERTEL)
            .customerid(UPDATED_CUSTOMERID)
            .advisorfinalcheck(UPDATED_ADVISORFINALCHECK)
            .jobdate(UPDATED_JOBDATE)
            .iscompanyservice(UPDATED_ISCOMPANYSERVICE)
            .freeservicenumber(UPDATED_FREESERVICENUMBER)
            .companyid(UPDATED_COMPANYID)
            .updatetocustomer(UPDATED_UPDATETOCUSTOMER)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .isjobinvoiced(UPDATED_ISJOBINVOICED)
            .iswaiting(UPDATED_ISWAITING)
            .iscustomercomment(UPDATED_ISCUSTOMERCOMMENT)
            .imagefolder(UPDATED_IMAGEFOLDER)
            .frontimage(UPDATED_FRONTIMAGE)
            .leftimage(UPDATED_LEFTIMAGE)
            .rightimage(UPDATED_RIGHTIMAGE)
            .backimage(UPDATED_BACKIMAGE)
            .dashboardimage(UPDATED_DASHBOARDIMAGE);

        restAutocarejobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarejob.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarejob))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejob in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarejobUpdatableFieldsEquals(partialUpdatedAutocarejob, getPersistedAutocarejob(partialUpdatedAutocarejob));
    }

    @Test
    @Transactional
    void patchNonExistingAutocarejob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejob.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarejobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocarejob.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarejob))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocarejob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejob.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarejob))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocarejob() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejob.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocarejob)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarejob in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocarejob() throws Exception {
        // Initialize the database
        autocarejobRepository.saveAndFlush(autocarejob);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocarejob
        restAutocarejobMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocarejob.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocarejobRepository.count();
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

    protected Autocarejob getPersistedAutocarejob(Autocarejob autocarejob) {
        return autocarejobRepository.findById(autocarejob.getId()).orElseThrow();
    }

    protected void assertPersistedAutocarejobToMatchAllProperties(Autocarejob expectedAutocarejob) {
        assertAutocarejobAllPropertiesEquals(expectedAutocarejob, getPersistedAutocarejob(expectedAutocarejob));
    }

    protected void assertPersistedAutocarejobToMatchUpdatableProperties(Autocarejob expectedAutocarejob) {
        assertAutocarejobAllUpdatablePropertiesEquals(expectedAutocarejob, getPersistedAutocarejob(expectedAutocarejob));
    }
}
