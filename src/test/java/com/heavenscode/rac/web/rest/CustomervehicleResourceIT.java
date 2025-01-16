package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.CustomervehicleAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Customervehicle;
import com.heavenscode.rac.repository.CustomervehicleRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link CustomervehicleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomervehicleResourceIT {

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;
    private static final Integer SMALLER_CUSTOMERID = 1 - 1;

    private static final String DEFAULT_VEHICLENUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLENUMBER = "BBBBBBBBBB";

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;
    private static final Integer SMALLER_CATEGORYID = 1 - 1;

    private static final String DEFAULT_CATEGORYNAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORYNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPEID = 1;
    private static final Integer UPDATED_TYPEID = 2;
    private static final Integer SMALLER_TYPEID = 1 - 1;

    private static final String DEFAULT_TYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPENAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAKEID = 1;
    private static final Integer UPDATED_MAKEID = 2;
    private static final Integer SMALLER_MAKEID = 1 - 1;

    private static final String DEFAULT_MAKENAME = "AAAAAAAAAA";
    private static final String UPDATED_MAKENAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_YOM = "AAAAAAAAAA";
    private static final String UPDATED_YOM = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERCODE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERCODE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_SERVICECOUNT = 1;
    private static final Integer UPDATED_SERVICECOUNT = 2;
    private static final Integer SMALLER_SERVICECOUNT = 1 - 1;

    private static final String DEFAULT_ENG_NO = "AAAAAAAAAA";
    private static final String UPDATED_ENG_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CHA_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHA_NO = "BBBBBBBBBB";

    private static final String DEFAULT_MILAGE = "AAAAAAAAAA";
    private static final String UPDATED_MILAGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LASTSERVICEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LASTSERVICEDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_LASTSERVICEDATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_NEXTSERVICEDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NEXTSERVICEDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NEXTSERVICEDATE = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;
    private static final Integer SMALLER_LMU = 1 - 1;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NEXTGEAROILMILAGE = "AAAAAAAAAA";
    private static final String UPDATED_NEXTGEAROILMILAGE = "BBBBBBBBBB";

    private static final String DEFAULT_NEXTMILAGE = "AAAAAAAAAA";
    private static final String UPDATED_NEXTMILAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SERVICEPERIOD = 1;
    private static final Integer UPDATED_SERVICEPERIOD = 2;
    private static final Integer SMALLER_SERVICEPERIOD = 1 - 1;

    private static final String ENTITY_API_URL = "/api/customervehicles";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomervehicleRepository customervehicleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomervehicleMockMvc;

    private Customervehicle customervehicle;

    private Customervehicle insertedCustomervehicle;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customervehicle createEntity() {
        return new Customervehicle()
            .customerid(DEFAULT_CUSTOMERID)
            .vehiclenumber(DEFAULT_VEHICLENUMBER)
            .categoryid(DEFAULT_CATEGORYID)
            .categoryname(DEFAULT_CATEGORYNAME)
            .typeid(DEFAULT_TYPEID)
            .typename(DEFAULT_TYPENAME)
            .makeid(DEFAULT_MAKEID)
            .makename(DEFAULT_MAKENAME)
            .model(DEFAULT_MODEL)
            .yom(DEFAULT_YOM)
            .customercode(DEFAULT_CUSTOMERCODE)
            .remarks(DEFAULT_REMARKS)
            .servicecount(DEFAULT_SERVICECOUNT)
            .engNo(DEFAULT_ENG_NO)
            .chaNo(DEFAULT_CHA_NO)
            .milage(DEFAULT_MILAGE)
            .lastservicedate(DEFAULT_LASTSERVICEDATE)
            .nextservicedate(DEFAULT_NEXTSERVICEDATE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .nextgearoilmilage(DEFAULT_NEXTGEAROILMILAGE)
            .nextmilage(DEFAULT_NEXTMILAGE)
            .serviceperiod(DEFAULT_SERVICEPERIOD);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customervehicle createUpdatedEntity() {
        return new Customervehicle()
            .customerid(UPDATED_CUSTOMERID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .categoryid(UPDATED_CATEGORYID)
            .categoryname(UPDATED_CATEGORYNAME)
            .typeid(UPDATED_TYPEID)
            .typename(UPDATED_TYPENAME)
            .makeid(UPDATED_MAKEID)
            .makename(UPDATED_MAKENAME)
            .model(UPDATED_MODEL)
            .yom(UPDATED_YOM)
            .customercode(UPDATED_CUSTOMERCODE)
            .remarks(UPDATED_REMARKS)
            .servicecount(UPDATED_SERVICECOUNT)
            .engNo(UPDATED_ENG_NO)
            .chaNo(UPDATED_CHA_NO)
            .milage(UPDATED_MILAGE)
            .lastservicedate(UPDATED_LASTSERVICEDATE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .nextmilage(UPDATED_NEXTMILAGE)
            .serviceperiod(UPDATED_SERVICEPERIOD);
    }

    @BeforeEach
    public void initTest() {
        customervehicle = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomervehicle != null) {
            customervehicleRepository.delete(insertedCustomervehicle);
            insertedCustomervehicle = null;
        }
    }

    @Test
    @Transactional
    void createCustomervehicle() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Customervehicle
        var returnedCustomervehicle = om.readValue(
            restCustomervehicleMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customervehicle)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Customervehicle.class
        );

        // Validate the Customervehicle in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCustomervehicleUpdatableFieldsEquals(returnedCustomervehicle, getPersistedCustomervehicle(returnedCustomervehicle));

        insertedCustomervehicle = returnedCustomervehicle;
    }

    @Test
    @Transactional
    void createCustomervehicleWithExistingId() throws Exception {
        // Create the Customervehicle with an existing ID
        customervehicle.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomervehicleMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customervehicle)))
            .andExpect(status().isBadRequest());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomervehicles() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList
        restCustomervehicleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customervehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].vehiclenumber").value(hasItem(DEFAULT_VEHICLENUMBER)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].categoryname").value(hasItem(DEFAULT_CATEGORYNAME)))
            .andExpect(jsonPath("$.[*].typeid").value(hasItem(DEFAULT_TYPEID)))
            .andExpect(jsonPath("$.[*].typename").value(hasItem(DEFAULT_TYPENAME)))
            .andExpect(jsonPath("$.[*].makeid").value(hasItem(DEFAULT_MAKEID)))
            .andExpect(jsonPath("$.[*].makename").value(hasItem(DEFAULT_MAKENAME)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].yom").value(hasItem(DEFAULT_YOM)))
            .andExpect(jsonPath("$.[*].customercode").value(hasItem(DEFAULT_CUSTOMERCODE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].servicecount").value(hasItem(DEFAULT_SERVICECOUNT)))
            .andExpect(jsonPath("$.[*].engNo").value(hasItem(DEFAULT_ENG_NO)))
            .andExpect(jsonPath("$.[*].chaNo").value(hasItem(DEFAULT_CHA_NO)))
            .andExpect(jsonPath("$.[*].milage").value(hasItem(DEFAULT_MILAGE)))
            .andExpect(jsonPath("$.[*].lastservicedate").value(hasItem(DEFAULT_LASTSERVICEDATE.toString())))
            .andExpect(jsonPath("$.[*].nextservicedate").value(hasItem(DEFAULT_NEXTSERVICEDATE.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].nextgearoilmilage").value(hasItem(DEFAULT_NEXTGEAROILMILAGE)))
            .andExpect(jsonPath("$.[*].nextmilage").value(hasItem(DEFAULT_NEXTMILAGE)))
            .andExpect(jsonPath("$.[*].serviceperiod").value(hasItem(DEFAULT_SERVICEPERIOD)));
    }

    @Test
    @Transactional
    void getCustomervehicle() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get the customervehicle
        restCustomervehicleMockMvc
            .perform(get(ENTITY_API_URL_ID, customervehicle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customervehicle.getId().intValue()))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.vehiclenumber").value(DEFAULT_VEHICLENUMBER))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.categoryname").value(DEFAULT_CATEGORYNAME))
            .andExpect(jsonPath("$.typeid").value(DEFAULT_TYPEID))
            .andExpect(jsonPath("$.typename").value(DEFAULT_TYPENAME))
            .andExpect(jsonPath("$.makeid").value(DEFAULT_MAKEID))
            .andExpect(jsonPath("$.makename").value(DEFAULT_MAKENAME))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.yom").value(DEFAULT_YOM))
            .andExpect(jsonPath("$.customercode").value(DEFAULT_CUSTOMERCODE))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.servicecount").value(DEFAULT_SERVICECOUNT))
            .andExpect(jsonPath("$.engNo").value(DEFAULT_ENG_NO))
            .andExpect(jsonPath("$.chaNo").value(DEFAULT_CHA_NO))
            .andExpect(jsonPath("$.milage").value(DEFAULT_MILAGE))
            .andExpect(jsonPath("$.lastservicedate").value(DEFAULT_LASTSERVICEDATE.toString()))
            .andExpect(jsonPath("$.nextservicedate").value(DEFAULT_NEXTSERVICEDATE.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.nextgearoilmilage").value(DEFAULT_NEXTGEAROILMILAGE))
            .andExpect(jsonPath("$.nextmilage").value(DEFAULT_NEXTMILAGE))
            .andExpect(jsonPath("$.serviceperiod").value(DEFAULT_SERVICEPERIOD));
    }

    @Test
    @Transactional
    void getCustomervehiclesByIdFiltering() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        Long id = customervehicle.getId();

        defaultCustomervehicleFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultCustomervehicleFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultCustomervehicleFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid equals to
        defaultCustomervehicleFiltering("customerid.equals=" + DEFAULT_CUSTOMERID, "customerid.equals=" + UPDATED_CUSTOMERID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid in
        defaultCustomervehicleFiltering(
            "customerid.in=" + DEFAULT_CUSTOMERID + "," + UPDATED_CUSTOMERID,
            "customerid.in=" + UPDATED_CUSTOMERID
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid is not null
        defaultCustomervehicleFiltering("customerid.specified=true", "customerid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid is greater than or equal to
        defaultCustomervehicleFiltering(
            "customerid.greaterThanOrEqual=" + DEFAULT_CUSTOMERID,
            "customerid.greaterThanOrEqual=" + UPDATED_CUSTOMERID
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid is less than or equal to
        defaultCustomervehicleFiltering(
            "customerid.lessThanOrEqual=" + DEFAULT_CUSTOMERID,
            "customerid.lessThanOrEqual=" + SMALLER_CUSTOMERID
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid is less than
        defaultCustomervehicleFiltering("customerid.lessThan=" + UPDATED_CUSTOMERID, "customerid.lessThan=" + DEFAULT_CUSTOMERID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomeridIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customerid is greater than
        defaultCustomervehicleFiltering("customerid.greaterThan=" + SMALLER_CUSTOMERID, "customerid.greaterThan=" + DEFAULT_CUSTOMERID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByVehiclenumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where vehiclenumber equals to
        defaultCustomervehicleFiltering("vehiclenumber.equals=" + DEFAULT_VEHICLENUMBER, "vehiclenumber.equals=" + UPDATED_VEHICLENUMBER);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByVehiclenumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where vehiclenumber in
        defaultCustomervehicleFiltering(
            "vehiclenumber.in=" + DEFAULT_VEHICLENUMBER + "," + UPDATED_VEHICLENUMBER,
            "vehiclenumber.in=" + UPDATED_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByVehiclenumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where vehiclenumber is not null
        defaultCustomervehicleFiltering("vehiclenumber.specified=true", "vehiclenumber.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByVehiclenumberContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where vehiclenumber contains
        defaultCustomervehicleFiltering(
            "vehiclenumber.contains=" + DEFAULT_VEHICLENUMBER,
            "vehiclenumber.contains=" + UPDATED_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByVehiclenumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where vehiclenumber does not contain
        defaultCustomervehicleFiltering(
            "vehiclenumber.doesNotContain=" + UPDATED_VEHICLENUMBER,
            "vehiclenumber.doesNotContain=" + DEFAULT_VEHICLENUMBER
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid equals to
        defaultCustomervehicleFiltering("categoryid.equals=" + DEFAULT_CATEGORYID, "categoryid.equals=" + UPDATED_CATEGORYID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid in
        defaultCustomervehicleFiltering(
            "categoryid.in=" + DEFAULT_CATEGORYID + "," + UPDATED_CATEGORYID,
            "categoryid.in=" + UPDATED_CATEGORYID
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid is not null
        defaultCustomervehicleFiltering("categoryid.specified=true", "categoryid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid is greater than or equal to
        defaultCustomervehicleFiltering(
            "categoryid.greaterThanOrEqual=" + DEFAULT_CATEGORYID,
            "categoryid.greaterThanOrEqual=" + UPDATED_CATEGORYID
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid is less than or equal to
        defaultCustomervehicleFiltering(
            "categoryid.lessThanOrEqual=" + DEFAULT_CATEGORYID,
            "categoryid.lessThanOrEqual=" + SMALLER_CATEGORYID
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid is less than
        defaultCustomervehicleFiltering("categoryid.lessThan=" + UPDATED_CATEGORYID, "categoryid.lessThan=" + DEFAULT_CATEGORYID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategoryidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryid is greater than
        defaultCustomervehicleFiltering("categoryid.greaterThan=" + SMALLER_CATEGORYID, "categoryid.greaterThan=" + DEFAULT_CATEGORYID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategorynameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryname equals to
        defaultCustomervehicleFiltering("categoryname.equals=" + DEFAULT_CATEGORYNAME, "categoryname.equals=" + UPDATED_CATEGORYNAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategorynameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryname in
        defaultCustomervehicleFiltering(
            "categoryname.in=" + DEFAULT_CATEGORYNAME + "," + UPDATED_CATEGORYNAME,
            "categoryname.in=" + UPDATED_CATEGORYNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategorynameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryname is not null
        defaultCustomervehicleFiltering("categoryname.specified=true", "categoryname.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategorynameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryname contains
        defaultCustomervehicleFiltering("categoryname.contains=" + DEFAULT_CATEGORYNAME, "categoryname.contains=" + UPDATED_CATEGORYNAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCategorynameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where categoryname does not contain
        defaultCustomervehicleFiltering(
            "categoryname.doesNotContain=" + UPDATED_CATEGORYNAME,
            "categoryname.doesNotContain=" + DEFAULT_CATEGORYNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid equals to
        defaultCustomervehicleFiltering("typeid.equals=" + DEFAULT_TYPEID, "typeid.equals=" + UPDATED_TYPEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid in
        defaultCustomervehicleFiltering("typeid.in=" + DEFAULT_TYPEID + "," + UPDATED_TYPEID, "typeid.in=" + UPDATED_TYPEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid is not null
        defaultCustomervehicleFiltering("typeid.specified=true", "typeid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid is greater than or equal to
        defaultCustomervehicleFiltering("typeid.greaterThanOrEqual=" + DEFAULT_TYPEID, "typeid.greaterThanOrEqual=" + UPDATED_TYPEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid is less than or equal to
        defaultCustomervehicleFiltering("typeid.lessThanOrEqual=" + DEFAULT_TYPEID, "typeid.lessThanOrEqual=" + SMALLER_TYPEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid is less than
        defaultCustomervehicleFiltering("typeid.lessThan=" + UPDATED_TYPEID, "typeid.lessThan=" + DEFAULT_TYPEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypeidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typeid is greater than
        defaultCustomervehicleFiltering("typeid.greaterThan=" + SMALLER_TYPEID, "typeid.greaterThan=" + DEFAULT_TYPEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypenameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typename equals to
        defaultCustomervehicleFiltering("typename.equals=" + DEFAULT_TYPENAME, "typename.equals=" + UPDATED_TYPENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypenameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typename in
        defaultCustomervehicleFiltering("typename.in=" + DEFAULT_TYPENAME + "," + UPDATED_TYPENAME, "typename.in=" + UPDATED_TYPENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypenameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typename is not null
        defaultCustomervehicleFiltering("typename.specified=true", "typename.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypenameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typename contains
        defaultCustomervehicleFiltering("typename.contains=" + DEFAULT_TYPENAME, "typename.contains=" + UPDATED_TYPENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByTypenameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where typename does not contain
        defaultCustomervehicleFiltering("typename.doesNotContain=" + UPDATED_TYPENAME, "typename.doesNotContain=" + DEFAULT_TYPENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid equals to
        defaultCustomervehicleFiltering("makeid.equals=" + DEFAULT_MAKEID, "makeid.equals=" + UPDATED_MAKEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid in
        defaultCustomervehicleFiltering("makeid.in=" + DEFAULT_MAKEID + "," + UPDATED_MAKEID, "makeid.in=" + UPDATED_MAKEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid is not null
        defaultCustomervehicleFiltering("makeid.specified=true", "makeid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid is greater than or equal to
        defaultCustomervehicleFiltering("makeid.greaterThanOrEqual=" + DEFAULT_MAKEID, "makeid.greaterThanOrEqual=" + UPDATED_MAKEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid is less than or equal to
        defaultCustomervehicleFiltering("makeid.lessThanOrEqual=" + DEFAULT_MAKEID, "makeid.lessThanOrEqual=" + SMALLER_MAKEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid is less than
        defaultCustomervehicleFiltering("makeid.lessThan=" + UPDATED_MAKEID, "makeid.lessThan=" + DEFAULT_MAKEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakeidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makeid is greater than
        defaultCustomervehicleFiltering("makeid.greaterThan=" + SMALLER_MAKEID, "makeid.greaterThan=" + DEFAULT_MAKEID);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakenameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makename equals to
        defaultCustomervehicleFiltering("makename.equals=" + DEFAULT_MAKENAME, "makename.equals=" + UPDATED_MAKENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakenameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makename in
        defaultCustomervehicleFiltering("makename.in=" + DEFAULT_MAKENAME + "," + UPDATED_MAKENAME, "makename.in=" + UPDATED_MAKENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakenameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makename is not null
        defaultCustomervehicleFiltering("makename.specified=true", "makename.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakenameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makename contains
        defaultCustomervehicleFiltering("makename.contains=" + DEFAULT_MAKENAME, "makename.contains=" + UPDATED_MAKENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMakenameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where makename does not contain
        defaultCustomervehicleFiltering("makename.doesNotContain=" + UPDATED_MAKENAME, "makename.doesNotContain=" + DEFAULT_MAKENAME);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByModelIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where model equals to
        defaultCustomervehicleFiltering("model.equals=" + DEFAULT_MODEL, "model.equals=" + UPDATED_MODEL);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByModelIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where model in
        defaultCustomervehicleFiltering("model.in=" + DEFAULT_MODEL + "," + UPDATED_MODEL, "model.in=" + UPDATED_MODEL);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByModelIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where model is not null
        defaultCustomervehicleFiltering("model.specified=true", "model.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByModelContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where model contains
        defaultCustomervehicleFiltering("model.contains=" + DEFAULT_MODEL, "model.contains=" + UPDATED_MODEL);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByModelNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where model does not contain
        defaultCustomervehicleFiltering("model.doesNotContain=" + UPDATED_MODEL, "model.doesNotContain=" + DEFAULT_MODEL);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByYomIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where yom equals to
        defaultCustomervehicleFiltering("yom.equals=" + DEFAULT_YOM, "yom.equals=" + UPDATED_YOM);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByYomIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where yom in
        defaultCustomervehicleFiltering("yom.in=" + DEFAULT_YOM + "," + UPDATED_YOM, "yom.in=" + UPDATED_YOM);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByYomIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where yom is not null
        defaultCustomervehicleFiltering("yom.specified=true", "yom.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByYomContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where yom contains
        defaultCustomervehicleFiltering("yom.contains=" + DEFAULT_YOM, "yom.contains=" + UPDATED_YOM);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByYomNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where yom does not contain
        defaultCustomervehicleFiltering("yom.doesNotContain=" + UPDATED_YOM, "yom.doesNotContain=" + DEFAULT_YOM);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomercodeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customercode equals to
        defaultCustomervehicleFiltering("customercode.equals=" + DEFAULT_CUSTOMERCODE, "customercode.equals=" + UPDATED_CUSTOMERCODE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomercodeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customercode in
        defaultCustomervehicleFiltering(
            "customercode.in=" + DEFAULT_CUSTOMERCODE + "," + UPDATED_CUSTOMERCODE,
            "customercode.in=" + UPDATED_CUSTOMERCODE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomercodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customercode is not null
        defaultCustomervehicleFiltering("customercode.specified=true", "customercode.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomercodeContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customercode contains
        defaultCustomervehicleFiltering("customercode.contains=" + DEFAULT_CUSTOMERCODE, "customercode.contains=" + UPDATED_CUSTOMERCODE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByCustomercodeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where customercode does not contain
        defaultCustomervehicleFiltering(
            "customercode.doesNotContain=" + UPDATED_CUSTOMERCODE,
            "customercode.doesNotContain=" + DEFAULT_CUSTOMERCODE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByRemarksIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where remarks equals to
        defaultCustomervehicleFiltering("remarks.equals=" + DEFAULT_REMARKS, "remarks.equals=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByRemarksIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where remarks in
        defaultCustomervehicleFiltering("remarks.in=" + DEFAULT_REMARKS + "," + UPDATED_REMARKS, "remarks.in=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByRemarksIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where remarks is not null
        defaultCustomervehicleFiltering("remarks.specified=true", "remarks.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByRemarksContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where remarks contains
        defaultCustomervehicleFiltering("remarks.contains=" + DEFAULT_REMARKS, "remarks.contains=" + UPDATED_REMARKS);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByRemarksNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where remarks does not contain
        defaultCustomervehicleFiltering("remarks.doesNotContain=" + UPDATED_REMARKS, "remarks.doesNotContain=" + DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount equals to
        defaultCustomervehicleFiltering("servicecount.equals=" + DEFAULT_SERVICECOUNT, "servicecount.equals=" + UPDATED_SERVICECOUNT);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount in
        defaultCustomervehicleFiltering(
            "servicecount.in=" + DEFAULT_SERVICECOUNT + "," + UPDATED_SERVICECOUNT,
            "servicecount.in=" + UPDATED_SERVICECOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount is not null
        defaultCustomervehicleFiltering("servicecount.specified=true", "servicecount.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount is greater than or equal to
        defaultCustomervehicleFiltering(
            "servicecount.greaterThanOrEqual=" + DEFAULT_SERVICECOUNT,
            "servicecount.greaterThanOrEqual=" + UPDATED_SERVICECOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount is less than or equal to
        defaultCustomervehicleFiltering(
            "servicecount.lessThanOrEqual=" + DEFAULT_SERVICECOUNT,
            "servicecount.lessThanOrEqual=" + SMALLER_SERVICECOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount is less than
        defaultCustomervehicleFiltering("servicecount.lessThan=" + UPDATED_SERVICECOUNT, "servicecount.lessThan=" + DEFAULT_SERVICECOUNT);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServicecountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where servicecount is greater than
        defaultCustomervehicleFiltering(
            "servicecount.greaterThan=" + SMALLER_SERVICECOUNT,
            "servicecount.greaterThan=" + DEFAULT_SERVICECOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByEngNoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where engNo equals to
        defaultCustomervehicleFiltering("engNo.equals=" + DEFAULT_ENG_NO, "engNo.equals=" + UPDATED_ENG_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByEngNoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where engNo in
        defaultCustomervehicleFiltering("engNo.in=" + DEFAULT_ENG_NO + "," + UPDATED_ENG_NO, "engNo.in=" + UPDATED_ENG_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByEngNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where engNo is not null
        defaultCustomervehicleFiltering("engNo.specified=true", "engNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByEngNoContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where engNo contains
        defaultCustomervehicleFiltering("engNo.contains=" + DEFAULT_ENG_NO, "engNo.contains=" + UPDATED_ENG_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByEngNoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where engNo does not contain
        defaultCustomervehicleFiltering("engNo.doesNotContain=" + UPDATED_ENG_NO, "engNo.doesNotContain=" + DEFAULT_ENG_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByChaNoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where chaNo equals to
        defaultCustomervehicleFiltering("chaNo.equals=" + DEFAULT_CHA_NO, "chaNo.equals=" + UPDATED_CHA_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByChaNoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where chaNo in
        defaultCustomervehicleFiltering("chaNo.in=" + DEFAULT_CHA_NO + "," + UPDATED_CHA_NO, "chaNo.in=" + UPDATED_CHA_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByChaNoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where chaNo is not null
        defaultCustomervehicleFiltering("chaNo.specified=true", "chaNo.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByChaNoContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where chaNo contains
        defaultCustomervehicleFiltering("chaNo.contains=" + DEFAULT_CHA_NO, "chaNo.contains=" + UPDATED_CHA_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByChaNoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where chaNo does not contain
        defaultCustomervehicleFiltering("chaNo.doesNotContain=" + UPDATED_CHA_NO, "chaNo.doesNotContain=" + DEFAULT_CHA_NO);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMilageIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where milage equals to
        defaultCustomervehicleFiltering("milage.equals=" + DEFAULT_MILAGE, "milage.equals=" + UPDATED_MILAGE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMilageIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where milage in
        defaultCustomervehicleFiltering("milage.in=" + DEFAULT_MILAGE + "," + UPDATED_MILAGE, "milage.in=" + UPDATED_MILAGE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMilageIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where milage is not null
        defaultCustomervehicleFiltering("milage.specified=true", "milage.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMilageContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where milage contains
        defaultCustomervehicleFiltering("milage.contains=" + DEFAULT_MILAGE, "milage.contains=" + UPDATED_MILAGE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByMilageNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where milage does not contain
        defaultCustomervehicleFiltering("milage.doesNotContain=" + UPDATED_MILAGE, "milage.doesNotContain=" + DEFAULT_MILAGE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate equals to
        defaultCustomervehicleFiltering(
            "lastservicedate.equals=" + DEFAULT_LASTSERVICEDATE,
            "lastservicedate.equals=" + UPDATED_LASTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate in
        defaultCustomervehicleFiltering(
            "lastservicedate.in=" + DEFAULT_LASTSERVICEDATE + "," + UPDATED_LASTSERVICEDATE,
            "lastservicedate.in=" + UPDATED_LASTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate is not null
        defaultCustomervehicleFiltering("lastservicedate.specified=true", "lastservicedate.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate is greater than or equal to
        defaultCustomervehicleFiltering(
            "lastservicedate.greaterThanOrEqual=" + DEFAULT_LASTSERVICEDATE,
            "lastservicedate.greaterThanOrEqual=" + UPDATED_LASTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate is less than or equal to
        defaultCustomervehicleFiltering(
            "lastservicedate.lessThanOrEqual=" + DEFAULT_LASTSERVICEDATE,
            "lastservicedate.lessThanOrEqual=" + SMALLER_LASTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate is less than
        defaultCustomervehicleFiltering(
            "lastservicedate.lessThan=" + UPDATED_LASTSERVICEDATE,
            "lastservicedate.lessThan=" + DEFAULT_LASTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLastservicedateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lastservicedate is greater than
        defaultCustomervehicleFiltering(
            "lastservicedate.greaterThan=" + SMALLER_LASTSERVICEDATE,
            "lastservicedate.greaterThan=" + DEFAULT_LASTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate equals to
        defaultCustomervehicleFiltering(
            "nextservicedate.equals=" + DEFAULT_NEXTSERVICEDATE,
            "nextservicedate.equals=" + UPDATED_NEXTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate in
        defaultCustomervehicleFiltering(
            "nextservicedate.in=" + DEFAULT_NEXTSERVICEDATE + "," + UPDATED_NEXTSERVICEDATE,
            "nextservicedate.in=" + UPDATED_NEXTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate is not null
        defaultCustomervehicleFiltering("nextservicedate.specified=true", "nextservicedate.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate is greater than or equal to
        defaultCustomervehicleFiltering(
            "nextservicedate.greaterThanOrEqual=" + DEFAULT_NEXTSERVICEDATE,
            "nextservicedate.greaterThanOrEqual=" + UPDATED_NEXTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate is less than or equal to
        defaultCustomervehicleFiltering(
            "nextservicedate.lessThanOrEqual=" + DEFAULT_NEXTSERVICEDATE,
            "nextservicedate.lessThanOrEqual=" + SMALLER_NEXTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate is less than
        defaultCustomervehicleFiltering(
            "nextservicedate.lessThan=" + UPDATED_NEXTSERVICEDATE,
            "nextservicedate.lessThan=" + DEFAULT_NEXTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextservicedateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextservicedate is greater than
        defaultCustomervehicleFiltering(
            "nextservicedate.greaterThan=" + SMALLER_NEXTSERVICEDATE,
            "nextservicedate.greaterThan=" + DEFAULT_NEXTSERVICEDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu equals to
        defaultCustomervehicleFiltering("lmu.equals=" + DEFAULT_LMU, "lmu.equals=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu in
        defaultCustomervehicleFiltering("lmu.in=" + DEFAULT_LMU + "," + UPDATED_LMU, "lmu.in=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu is not null
        defaultCustomervehicleFiltering("lmu.specified=true", "lmu.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu is greater than or equal to
        defaultCustomervehicleFiltering("lmu.greaterThanOrEqual=" + DEFAULT_LMU, "lmu.greaterThanOrEqual=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu is less than or equal to
        defaultCustomervehicleFiltering("lmu.lessThanOrEqual=" + DEFAULT_LMU, "lmu.lessThanOrEqual=" + SMALLER_LMU);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu is less than
        defaultCustomervehicleFiltering("lmu.lessThan=" + UPDATED_LMU, "lmu.lessThan=" + DEFAULT_LMU);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmu is greater than
        defaultCustomervehicleFiltering("lmu.greaterThan=" + SMALLER_LMU, "lmu.greaterThan=" + DEFAULT_LMU);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmd equals to
        defaultCustomervehicleFiltering("lmd.equals=" + DEFAULT_LMD, "lmd.equals=" + UPDATED_LMD);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmd in
        defaultCustomervehicleFiltering("lmd.in=" + DEFAULT_LMD + "," + UPDATED_LMD, "lmd.in=" + UPDATED_LMD);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByLmdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where lmd is not null
        defaultCustomervehicleFiltering("lmd.specified=true", "lmd.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextgearoilmilageIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextgearoilmilage equals to
        defaultCustomervehicleFiltering(
            "nextgearoilmilage.equals=" + DEFAULT_NEXTGEAROILMILAGE,
            "nextgearoilmilage.equals=" + UPDATED_NEXTGEAROILMILAGE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextgearoilmilageIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextgearoilmilage in
        defaultCustomervehicleFiltering(
            "nextgearoilmilage.in=" + DEFAULT_NEXTGEAROILMILAGE + "," + UPDATED_NEXTGEAROILMILAGE,
            "nextgearoilmilage.in=" + UPDATED_NEXTGEAROILMILAGE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextgearoilmilageIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextgearoilmilage is not null
        defaultCustomervehicleFiltering("nextgearoilmilage.specified=true", "nextgearoilmilage.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextgearoilmilageContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextgearoilmilage contains
        defaultCustomervehicleFiltering(
            "nextgearoilmilage.contains=" + DEFAULT_NEXTGEAROILMILAGE,
            "nextgearoilmilage.contains=" + UPDATED_NEXTGEAROILMILAGE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextgearoilmilageNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextgearoilmilage does not contain
        defaultCustomervehicleFiltering(
            "nextgearoilmilage.doesNotContain=" + UPDATED_NEXTGEAROILMILAGE,
            "nextgearoilmilage.doesNotContain=" + DEFAULT_NEXTGEAROILMILAGE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextmilageIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextmilage equals to
        defaultCustomervehicleFiltering("nextmilage.equals=" + DEFAULT_NEXTMILAGE, "nextmilage.equals=" + UPDATED_NEXTMILAGE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextmilageIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextmilage in
        defaultCustomervehicleFiltering(
            "nextmilage.in=" + DEFAULT_NEXTMILAGE + "," + UPDATED_NEXTMILAGE,
            "nextmilage.in=" + UPDATED_NEXTMILAGE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextmilageIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextmilage is not null
        defaultCustomervehicleFiltering("nextmilage.specified=true", "nextmilage.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextmilageContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextmilage contains
        defaultCustomervehicleFiltering("nextmilage.contains=" + DEFAULT_NEXTMILAGE, "nextmilage.contains=" + UPDATED_NEXTMILAGE);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByNextmilageNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where nextmilage does not contain
        defaultCustomervehicleFiltering(
            "nextmilage.doesNotContain=" + UPDATED_NEXTMILAGE,
            "nextmilage.doesNotContain=" + DEFAULT_NEXTMILAGE
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod equals to
        defaultCustomervehicleFiltering("serviceperiod.equals=" + DEFAULT_SERVICEPERIOD, "serviceperiod.equals=" + UPDATED_SERVICEPERIOD);
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod in
        defaultCustomervehicleFiltering(
            "serviceperiod.in=" + DEFAULT_SERVICEPERIOD + "," + UPDATED_SERVICEPERIOD,
            "serviceperiod.in=" + UPDATED_SERVICEPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod is not null
        defaultCustomervehicleFiltering("serviceperiod.specified=true", "serviceperiod.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod is greater than or equal to
        defaultCustomervehicleFiltering(
            "serviceperiod.greaterThanOrEqual=" + DEFAULT_SERVICEPERIOD,
            "serviceperiod.greaterThanOrEqual=" + UPDATED_SERVICEPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod is less than or equal to
        defaultCustomervehicleFiltering(
            "serviceperiod.lessThanOrEqual=" + DEFAULT_SERVICEPERIOD,
            "serviceperiod.lessThanOrEqual=" + SMALLER_SERVICEPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod is less than
        defaultCustomervehicleFiltering(
            "serviceperiod.lessThan=" + UPDATED_SERVICEPERIOD,
            "serviceperiod.lessThan=" + DEFAULT_SERVICEPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomervehiclesByServiceperiodIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        // Get all the customervehicleList where serviceperiod is greater than
        defaultCustomervehicleFiltering(
            "serviceperiod.greaterThan=" + SMALLER_SERVICEPERIOD,
            "serviceperiod.greaterThan=" + DEFAULT_SERVICEPERIOD
        );
    }

    private void defaultCustomervehicleFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCustomervehicleShouldBeFound(shouldBeFound);
        defaultCustomervehicleShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCustomervehicleShouldBeFound(String filter) throws Exception {
        restCustomervehicleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customervehicle.getId().intValue())))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].vehiclenumber").value(hasItem(DEFAULT_VEHICLENUMBER)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].categoryname").value(hasItem(DEFAULT_CATEGORYNAME)))
            .andExpect(jsonPath("$.[*].typeid").value(hasItem(DEFAULT_TYPEID)))
            .andExpect(jsonPath("$.[*].typename").value(hasItem(DEFAULT_TYPENAME)))
            .andExpect(jsonPath("$.[*].makeid").value(hasItem(DEFAULT_MAKEID)))
            .andExpect(jsonPath("$.[*].makename").value(hasItem(DEFAULT_MAKENAME)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].yom").value(hasItem(DEFAULT_YOM)))
            .andExpect(jsonPath("$.[*].customercode").value(hasItem(DEFAULT_CUSTOMERCODE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].servicecount").value(hasItem(DEFAULT_SERVICECOUNT)))
            .andExpect(jsonPath("$.[*].engNo").value(hasItem(DEFAULT_ENG_NO)))
            .andExpect(jsonPath("$.[*].chaNo").value(hasItem(DEFAULT_CHA_NO)))
            .andExpect(jsonPath("$.[*].milage").value(hasItem(DEFAULT_MILAGE)))
            .andExpect(jsonPath("$.[*].lastservicedate").value(hasItem(DEFAULT_LASTSERVICEDATE.toString())))
            .andExpect(jsonPath("$.[*].nextservicedate").value(hasItem(DEFAULT_NEXTSERVICEDATE.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].nextgearoilmilage").value(hasItem(DEFAULT_NEXTGEAROILMILAGE)))
            .andExpect(jsonPath("$.[*].nextmilage").value(hasItem(DEFAULT_NEXTMILAGE)))
            .andExpect(jsonPath("$.[*].serviceperiod").value(hasItem(DEFAULT_SERVICEPERIOD)));

        // Check, that the count call also returns 1
        restCustomervehicleMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCustomervehicleShouldNotBeFound(String filter) throws Exception {
        restCustomervehicleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCustomervehicleMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCustomervehicle() throws Exception {
        // Get the customervehicle
        restCustomervehicleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomervehicle() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customervehicle
        Customervehicle updatedCustomervehicle = customervehicleRepository.findById(customervehicle.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomervehicle are not directly saved in db
        em.detach(updatedCustomervehicle);
        updatedCustomervehicle
            .customerid(UPDATED_CUSTOMERID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .categoryid(UPDATED_CATEGORYID)
            .categoryname(UPDATED_CATEGORYNAME)
            .typeid(UPDATED_TYPEID)
            .typename(UPDATED_TYPENAME)
            .makeid(UPDATED_MAKEID)
            .makename(UPDATED_MAKENAME)
            .model(UPDATED_MODEL)
            .yom(UPDATED_YOM)
            .customercode(UPDATED_CUSTOMERCODE)
            .remarks(UPDATED_REMARKS)
            .servicecount(UPDATED_SERVICECOUNT)
            .engNo(UPDATED_ENG_NO)
            .chaNo(UPDATED_CHA_NO)
            .milage(UPDATED_MILAGE)
            .lastservicedate(UPDATED_LASTSERVICEDATE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .nextmilage(UPDATED_NEXTMILAGE)
            .serviceperiod(UPDATED_SERVICEPERIOD);

        restCustomervehicleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomervehicle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCustomervehicle))
            )
            .andExpect(status().isOk());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomervehicleToMatchAllProperties(updatedCustomervehicle);
    }

    @Test
    @Transactional
    void putNonExistingCustomervehicle() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customervehicle.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomervehicleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customervehicle.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customervehicle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomervehicle() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customervehicle.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomervehicleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customervehicle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomervehicle() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customervehicle.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomervehicleMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customervehicle)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomervehicleWithPatch() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customervehicle using partial update
        Customervehicle partialUpdatedCustomervehicle = new Customervehicle();
        partialUpdatedCustomervehicle.setId(customervehicle.getId());

        partialUpdatedCustomervehicle
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .categoryname(UPDATED_CATEGORYNAME)
            .typename(UPDATED_TYPENAME)
            .customercode(UPDATED_CUSTOMERCODE)
            .remarks(UPDATED_REMARKS)
            .servicecount(UPDATED_SERVICECOUNT)
            .engNo(UPDATED_ENG_NO)
            .milage(UPDATED_MILAGE)
            .lastservicedate(UPDATED_LASTSERVICEDATE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .nextmilage(UPDATED_NEXTMILAGE);

        restCustomervehicleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomervehicle.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomervehicle))
            )
            .andExpect(status().isOk());

        // Validate the Customervehicle in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomervehicleUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCustomervehicle, customervehicle),
            getPersistedCustomervehicle(customervehicle)
        );
    }

    @Test
    @Transactional
    void fullUpdateCustomervehicleWithPatch() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customervehicle using partial update
        Customervehicle partialUpdatedCustomervehicle = new Customervehicle();
        partialUpdatedCustomervehicle.setId(customervehicle.getId());

        partialUpdatedCustomervehicle
            .customerid(UPDATED_CUSTOMERID)
            .vehiclenumber(UPDATED_VEHICLENUMBER)
            .categoryid(UPDATED_CATEGORYID)
            .categoryname(UPDATED_CATEGORYNAME)
            .typeid(UPDATED_TYPEID)
            .typename(UPDATED_TYPENAME)
            .makeid(UPDATED_MAKEID)
            .makename(UPDATED_MAKENAME)
            .model(UPDATED_MODEL)
            .yom(UPDATED_YOM)
            .customercode(UPDATED_CUSTOMERCODE)
            .remarks(UPDATED_REMARKS)
            .servicecount(UPDATED_SERVICECOUNT)
            .engNo(UPDATED_ENG_NO)
            .chaNo(UPDATED_CHA_NO)
            .milage(UPDATED_MILAGE)
            .lastservicedate(UPDATED_LASTSERVICEDATE)
            .nextservicedate(UPDATED_NEXTSERVICEDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .nextgearoilmilage(UPDATED_NEXTGEAROILMILAGE)
            .nextmilage(UPDATED_NEXTMILAGE)
            .serviceperiod(UPDATED_SERVICEPERIOD);

        restCustomervehicleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomervehicle.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomervehicle))
            )
            .andExpect(status().isOk());

        // Validate the Customervehicle in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomervehicleUpdatableFieldsEquals(
            partialUpdatedCustomervehicle,
            getPersistedCustomervehicle(partialUpdatedCustomervehicle)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCustomervehicle() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customervehicle.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomervehicleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customervehicle.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customervehicle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomervehicle() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customervehicle.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomervehicleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customervehicle))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomervehicle() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customervehicle.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomervehicleMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customervehicle)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customervehicle in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomervehicle() throws Exception {
        // Initialize the database
        insertedCustomervehicle = customervehicleRepository.saveAndFlush(customervehicle);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customervehicle
        restCustomervehicleMockMvc
            .perform(delete(ENTITY_API_URL_ID, customervehicle.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customervehicleRepository.count();
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

    protected Customervehicle getPersistedCustomervehicle(Customervehicle customervehicle) {
        return customervehicleRepository.findById(customervehicle.getId()).orElseThrow();
    }

    protected void assertPersistedCustomervehicleToMatchAllProperties(Customervehicle expectedCustomervehicle) {
        assertCustomervehicleAllPropertiesEquals(expectedCustomervehicle, getPersistedCustomervehicle(expectedCustomervehicle));
    }

    protected void assertPersistedCustomervehicleToMatchUpdatableProperties(Customervehicle expectedCustomervehicle) {
        assertCustomervehicleAllUpdatablePropertiesEquals(expectedCustomervehicle, getPersistedCustomervehicle(expectedCustomervehicle));
    }
}
