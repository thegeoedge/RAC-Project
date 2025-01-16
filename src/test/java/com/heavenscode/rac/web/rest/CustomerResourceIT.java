package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.CustomerAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Customer;
import com.heavenscode.rac.repository.CustomerRepository;
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
 * Integration tests for the {@link CustomerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CustomerResourceIT {

    private static final Integer DEFAULT_CUSTOMERTYPE = 1;
    private static final Integer UPDATED_CUSTOMERTYPE = 2;
    private static final Integer SMALLER_CUSTOMERTYPE = 1 - 1;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_NAMEWITHINITIALS = "AAAAAAAAAA";
    private static final String UPDATED_NAMEWITHINITIALS = "BBBBBBBBBB";

    private static final String DEFAULT_FULLNAME = "AAAAAAAAAA";
    private static final String UPDATED_FULLNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CALLINGNAME = "AAAAAAAAAA";
    private static final String UPDATED_CALLINGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICNO = "AAAAAAAAAA";
    private static final String UPDATED_NICNO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_NICISSUEDDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_NICISSUEDDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_NICISSUEDDATE = LocalDate.ofEpochDay(-1L);

    private static final LocalDate DEFAULT_DATEOFBIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATEOFBIRTH = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATEOFBIRTH = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_BLOODGROUP = "AAAAAAAAAA";
    private static final String UPDATED_BLOODGROUP = "BBBBBBBBBB";

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_MARITALSTATUS = "AAAAAAAAAA";
    private static final String UPDATED_MARITALSTATUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_MARRIEDDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MARRIEDDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_MARRIEDDATE = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_NATIONALITY = 1;
    private static final Integer UPDATED_NATIONALITY = 2;
    private static final Integer SMALLER_NATIONALITY = 1 - 1;

    private static final String DEFAULT_TERRITORY = "AAAAAAAAAA";
    private static final String UPDATED_TERRITORY = "BBBBBBBBBB";

    private static final Integer DEFAULT_RELIGION = 1;
    private static final Integer UPDATED_RELIGION = 2;
    private static final Integer SMALLER_RELIGION = 1 - 1;

    private static final String DEFAULT_TEAM = "AAAAAAAAAA";
    private static final String UPDATED_TEAM = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSNAME = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BUSINESSREGDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BUSINESSREGDATE = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_BUSINESSREGDATE = LocalDate.ofEpochDay(-1L);

    private static final String DEFAULT_BUSINESSREGNO = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSREGNO = "BBBBBBBBBB";

    private static final String DEFAULT_PROFILEPICTUREPATH = "AAAAAAAAAA";
    private static final String UPDATED_PROFILEPICTUREPATH = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCEHOUSENO = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCEHOUSENO = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCEADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCEADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCECITY = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCECITY = "BBBBBBBBBB";

    private static final String DEFAULT_RESIDENCEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_RESIDENCEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSLOCATIONNO = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSLOCATIONNO = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSCITY = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSCITY = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSPHONE_1 = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSPHONE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSPHONE_2 = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSPHONE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSMOBILE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSMOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSFAX = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSFAX = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESSEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESSEMAIL = "BBBBBBBBBB";

    private static final Integer DEFAULT_BUSINESSPROVINCEID = 1;
    private static final Integer UPDATED_BUSINESSPROVINCEID = 2;
    private static final Integer SMALLER_BUSINESSPROVINCEID = 1 - 1;

    private static final Integer DEFAULT_BUSINESSDISTRICTID = 1;
    private static final Integer UPDATED_BUSINESSDISTRICTID = 2;
    private static final Integer SMALLER_BUSINESSDISTRICTID = 1 - 1;

    private static final String DEFAULT_CONTACTPERSONNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTPERSONNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTPERSONPHONE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTPERSONPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTPERSONMOBILE = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTPERSONMOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTPERSONEMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTPERSONEMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ROOTMAPPATH = "AAAAAAAAAA";
    private static final String UPDATED_ROOTMAPPATH = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final Instant DEFAULT_REGISTRATIONDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REGISTRATIONDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ISACTIVE = 1;
    private static final Integer UPDATED_ISACTIVE = 2;
    private static final Integer SMALLER_ISACTIVE = 1 - 1;

    private static final Integer DEFAULT_ISINTERNALCUSTOMER = 1;
    private static final Integer UPDATED_ISINTERNALCUSTOMER = 2;
    private static final Integer SMALLER_ISINTERNALCUSTOMER = 1 - 1;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;
    private static final Integer SMALLER_LMU = 1 - 1;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_MAXIMUMDISCOUNT = 1F;
    private static final Float UPDATED_MAXIMUMDISCOUNT = 2F;
    private static final Float SMALLER_MAXIMUMDISCOUNT = 1F - 1F;

    private static final Float DEFAULT_CREDITLIMIT = 1F;
    private static final Float UPDATED_CREDITLIMIT = 2F;
    private static final Float SMALLER_CREDITLIMIT = 1F - 1F;

    private static final Boolean DEFAULT_HASSECURITYDEPOSIT = false;
    private static final Boolean UPDATED_HASSECURITYDEPOSIT = true;

    private static final Float DEFAULT_SECURITYDEPOSITAMOUNT = 1F;
    private static final Float UPDATED_SECURITYDEPOSITAMOUNT = 2F;
    private static final Float SMALLER_SECURITYDEPOSITAMOUNT = 1F - 1F;

    private static final Boolean DEFAULT_PAYBYCASH = false;
    private static final Boolean UPDATED_PAYBYCASH = true;

    private static final Boolean DEFAULT_CASHPAYMENTBEFORELOAD = false;
    private static final Boolean UPDATED_CASHPAYMENTBEFORELOAD = true;

    private static final Boolean DEFAULT_CASHLASTINVOICEBEFORELOAD = false;
    private static final Boolean UPDATED_CASHLASTINVOICEBEFORELOAD = true;

    private static final Boolean DEFAULT_PAYBYCREDIT = false;
    private static final Boolean UPDATED_PAYBYCREDIT = true;

    private static final Boolean DEFAULT_CREDITONEWEEKCHECK = false;
    private static final Boolean UPDATED_CREDITONEWEEKCHECK = true;

    private static final Integer DEFAULT_CREDITPAYMENTBYDAYS = 1;
    private static final Integer UPDATED_CREDITPAYMENTBYDAYS = 2;
    private static final Integer SMALLER_CREDITPAYMENTBYDAYS = 1 - 1;

    private static final Boolean DEFAULT_HASPURCHASINGDEPOSIT = false;
    private static final Boolean UPDATED_HASPURCHASINGDEPOSIT = true;

    private static final Boolean DEFAULT_HASSECURITYDEPOSITBOND = false;
    private static final Boolean UPDATED_HASSECURITYDEPOSITBOND = true;

    private static final Boolean DEFAULT_HASASSESTSDEPOSIT = false;
    private static final Boolean UPDATED_HASASSESTSDEPOSIT = true;

    private static final String DEFAULT_CUSTOMERROOTMAPPATH = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERROOTMAPPATH = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYERNAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYERADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYERADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYERPHONE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYERPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYERDESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYERDESIGNATION = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUSEMPLOYERNAME = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSEMPLOYERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUSEMPLOYERADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSEMPLOYERADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUSINDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSINDUSTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUSPERIOD = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSPERIOD = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUSPOSITIONS = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSPOSITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_PREVIOUSRESIONFORLEAVING = "AAAAAAAAAA";
    private static final String UPDATED_PREVIOUSRESIONFORLEAVING = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HASCREADITLIMIT = false;
    private static final Boolean UPDATED_HASCREADITLIMIT = true;

    private static final Integer DEFAULT_ACCOUNTID = 1;
    private static final Integer UPDATED_ACCOUNTID = 2;
    private static final Integer SMALLER_ACCOUNTID = 1 - 1;

    private static final String DEFAULT_ACCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISREGISTERED = false;
    private static final Boolean UPDATED_ISREGISTERED = true;

    private static final String DEFAULT_VATREGNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VATREGNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TINNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TINNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LAT = "AAAAAAAAAA";
    private static final String UPDATED_LAT = "BBBBBBBBBB";

    private static final String DEFAULT_LON = "AAAAAAAAAA";
    private static final String UPDATED_LON = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREDITPERIOD = 1;
    private static final Integer UPDATED_CREDITPERIOD = 2;
    private static final Integer SMALLER_CREDITPERIOD = 1 - 1;

    private static final String ENTITY_API_URL = "/api/customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCustomerMockMvc;

    private Customer customer;

    private Customer insertedCustomer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createEntity() {
        return new Customer()
            .customertype(DEFAULT_CUSTOMERTYPE)
            .code(DEFAULT_CODE)
            .title(DEFAULT_TITLE)
            .namewithinitials(DEFAULT_NAMEWITHINITIALS)
            .fullname(DEFAULT_FULLNAME)
            .callingname(DEFAULT_CALLINGNAME)
            .nicno(DEFAULT_NICNO)
            .nicissueddate(DEFAULT_NICISSUEDDATE)
            .dateofbirth(DEFAULT_DATEOFBIRTH)
            .bloodgroup(DEFAULT_BLOODGROUP)
            .gender(DEFAULT_GENDER)
            .maritalstatus(DEFAULT_MARITALSTATUS)
            .marrieddate(DEFAULT_MARRIEDDATE)
            .nationality(DEFAULT_NATIONALITY)
            .territory(DEFAULT_TERRITORY)
            .religion(DEFAULT_RELIGION)
            .team(DEFAULT_TEAM)
            .businessname(DEFAULT_BUSINESSNAME)
            .businessregdate(DEFAULT_BUSINESSREGDATE)
            .businessregno(DEFAULT_BUSINESSREGNO)
            .profilepicturepath(DEFAULT_PROFILEPICTUREPATH)
            .residencehouseno(DEFAULT_RESIDENCEHOUSENO)
            .residenceaddress(DEFAULT_RESIDENCEADDRESS)
            .residencecity(DEFAULT_RESIDENCECITY)
            .residencephone(DEFAULT_RESIDENCEPHONE)
            .businesslocationno(DEFAULT_BUSINESSLOCATIONNO)
            .businessaddress(DEFAULT_BUSINESSADDRESS)
            .businesscity(DEFAULT_BUSINESSCITY)
            .businessphone1(DEFAULT_BUSINESSPHONE_1)
            .businessphone2(DEFAULT_BUSINESSPHONE_2)
            .businessmobile(DEFAULT_BUSINESSMOBILE)
            .businessfax(DEFAULT_BUSINESSFAX)
            .businessemail(DEFAULT_BUSINESSEMAIL)
            .businessprovinceid(DEFAULT_BUSINESSPROVINCEID)
            .businessdistrictid(DEFAULT_BUSINESSDISTRICTID)
            .contactpersonname(DEFAULT_CONTACTPERSONNAME)
            .contactpersonphone(DEFAULT_CONTACTPERSONPHONE)
            .contactpersonmobile(DEFAULT_CONTACTPERSONMOBILE)
            .contactpersonemail(DEFAULT_CONTACTPERSONEMAIL)
            .rootmappath(DEFAULT_ROOTMAPPATH)
            .website(DEFAULT_WEBSITE)
            .registrationdate(DEFAULT_REGISTRATIONDATE)
            .isactive(DEFAULT_ISACTIVE)
            .isinternalcustomer(DEFAULT_ISINTERNALCUSTOMER)
            .description(DEFAULT_DESCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .maximumdiscount(DEFAULT_MAXIMUMDISCOUNT)
            .creditlimit(DEFAULT_CREDITLIMIT)
            .hassecuritydeposit(DEFAULT_HASSECURITYDEPOSIT)
            .securitydepositamount(DEFAULT_SECURITYDEPOSITAMOUNT)
            .paybycash(DEFAULT_PAYBYCASH)
            .cashpaymentbeforeload(DEFAULT_CASHPAYMENTBEFORELOAD)
            .cashlastinvoicebeforeload(DEFAULT_CASHLASTINVOICEBEFORELOAD)
            .paybycredit(DEFAULT_PAYBYCREDIT)
            .creditoneweekcheck(DEFAULT_CREDITONEWEEKCHECK)
            .creditpaymentbydays(DEFAULT_CREDITPAYMENTBYDAYS)
            .haspurchasingdeposit(DEFAULT_HASPURCHASINGDEPOSIT)
            .hassecuritydepositbond(DEFAULT_HASSECURITYDEPOSITBOND)
            .hasassestsdeposit(DEFAULT_HASASSESTSDEPOSIT)
            .customerrootmappath(DEFAULT_CUSTOMERROOTMAPPATH)
            .employername(DEFAULT_EMPLOYERNAME)
            .employeraddress(DEFAULT_EMPLOYERADDRESS)
            .employerphone(DEFAULT_EMPLOYERPHONE)
            .employerdesignation(DEFAULT_EMPLOYERDESIGNATION)
            .previousemployername(DEFAULT_PREVIOUSEMPLOYERNAME)
            .previousemployeraddress(DEFAULT_PREVIOUSEMPLOYERADDRESS)
            .previousindustry(DEFAULT_PREVIOUSINDUSTRY)
            .previousperiod(DEFAULT_PREVIOUSPERIOD)
            .previouspositions(DEFAULT_PREVIOUSPOSITIONS)
            .previousresionforleaving(DEFAULT_PREVIOUSRESIONFORLEAVING)
            .hascreaditlimit(DEFAULT_HASCREADITLIMIT)
            .accountid(DEFAULT_ACCOUNTID)
            .accountcode(DEFAULT_ACCOUNTCODE)
            .isregistered(DEFAULT_ISREGISTERED)
            .vatregnumber(DEFAULT_VATREGNUMBER)
            .tinnumber(DEFAULT_TINNUMBER)
            .lat(DEFAULT_LAT)
            .lon(DEFAULT_LON)
            .creditperiod(DEFAULT_CREDITPERIOD);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Customer createUpdatedEntity() {
        return new Customer()
            .customertype(UPDATED_CUSTOMERTYPE)
            .code(UPDATED_CODE)
            .title(UPDATED_TITLE)
            .namewithinitials(UPDATED_NAMEWITHINITIALS)
            .fullname(UPDATED_FULLNAME)
            .callingname(UPDATED_CALLINGNAME)
            .nicno(UPDATED_NICNO)
            .nicissueddate(UPDATED_NICISSUEDDATE)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .bloodgroup(UPDATED_BLOODGROUP)
            .gender(UPDATED_GENDER)
            .maritalstatus(UPDATED_MARITALSTATUS)
            .marrieddate(UPDATED_MARRIEDDATE)
            .nationality(UPDATED_NATIONALITY)
            .territory(UPDATED_TERRITORY)
            .religion(UPDATED_RELIGION)
            .team(UPDATED_TEAM)
            .businessname(UPDATED_BUSINESSNAME)
            .businessregdate(UPDATED_BUSINESSREGDATE)
            .businessregno(UPDATED_BUSINESSREGNO)
            .profilepicturepath(UPDATED_PROFILEPICTUREPATH)
            .residencehouseno(UPDATED_RESIDENCEHOUSENO)
            .residenceaddress(UPDATED_RESIDENCEADDRESS)
            .residencecity(UPDATED_RESIDENCECITY)
            .residencephone(UPDATED_RESIDENCEPHONE)
            .businesslocationno(UPDATED_BUSINESSLOCATIONNO)
            .businessaddress(UPDATED_BUSINESSADDRESS)
            .businesscity(UPDATED_BUSINESSCITY)
            .businessphone1(UPDATED_BUSINESSPHONE_1)
            .businessphone2(UPDATED_BUSINESSPHONE_2)
            .businessmobile(UPDATED_BUSINESSMOBILE)
            .businessfax(UPDATED_BUSINESSFAX)
            .businessemail(UPDATED_BUSINESSEMAIL)
            .businessprovinceid(UPDATED_BUSINESSPROVINCEID)
            .businessdistrictid(UPDATED_BUSINESSDISTRICTID)
            .contactpersonname(UPDATED_CONTACTPERSONNAME)
            .contactpersonphone(UPDATED_CONTACTPERSONPHONE)
            .contactpersonmobile(UPDATED_CONTACTPERSONMOBILE)
            .contactpersonemail(UPDATED_CONTACTPERSONEMAIL)
            .rootmappath(UPDATED_ROOTMAPPATH)
            .website(UPDATED_WEBSITE)
            .registrationdate(UPDATED_REGISTRATIONDATE)
            .isactive(UPDATED_ISACTIVE)
            .isinternalcustomer(UPDATED_ISINTERNALCUSTOMER)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .maximumdiscount(UPDATED_MAXIMUMDISCOUNT)
            .creditlimit(UPDATED_CREDITLIMIT)
            .hassecuritydeposit(UPDATED_HASSECURITYDEPOSIT)
            .securitydepositamount(UPDATED_SECURITYDEPOSITAMOUNT)
            .paybycash(UPDATED_PAYBYCASH)
            .cashpaymentbeforeload(UPDATED_CASHPAYMENTBEFORELOAD)
            .cashlastinvoicebeforeload(UPDATED_CASHLASTINVOICEBEFORELOAD)
            .paybycredit(UPDATED_PAYBYCREDIT)
            .creditoneweekcheck(UPDATED_CREDITONEWEEKCHECK)
            .creditpaymentbydays(UPDATED_CREDITPAYMENTBYDAYS)
            .haspurchasingdeposit(UPDATED_HASPURCHASINGDEPOSIT)
            .hassecuritydepositbond(UPDATED_HASSECURITYDEPOSITBOND)
            .hasassestsdeposit(UPDATED_HASASSESTSDEPOSIT)
            .customerrootmappath(UPDATED_CUSTOMERROOTMAPPATH)
            .employername(UPDATED_EMPLOYERNAME)
            .employeraddress(UPDATED_EMPLOYERADDRESS)
            .employerphone(UPDATED_EMPLOYERPHONE)
            .employerdesignation(UPDATED_EMPLOYERDESIGNATION)
            .previousemployername(UPDATED_PREVIOUSEMPLOYERNAME)
            .previousemployeraddress(UPDATED_PREVIOUSEMPLOYERADDRESS)
            .previousindustry(UPDATED_PREVIOUSINDUSTRY)
            .previousperiod(UPDATED_PREVIOUSPERIOD)
            .previouspositions(UPDATED_PREVIOUSPOSITIONS)
            .previousresionforleaving(UPDATED_PREVIOUSRESIONFORLEAVING)
            .hascreaditlimit(UPDATED_HASCREADITLIMIT)
            .accountid(UPDATED_ACCOUNTID)
            .accountcode(UPDATED_ACCOUNTCODE)
            .isregistered(UPDATED_ISREGISTERED)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .lat(UPDATED_LAT)
            .lon(UPDATED_LON)
            .creditperiod(UPDATED_CREDITPERIOD);
    }

    @BeforeEach
    public void initTest() {
        customer = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCustomer != null) {
            customerRepository.delete(insertedCustomer);
            insertedCustomer = null;
        }
    }

    @Test
    @Transactional
    void createCustomer() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Customer
        var returnedCustomer = om.readValue(
            restCustomerMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Customer.class
        );

        // Validate the Customer in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCustomerUpdatableFieldsEquals(returnedCustomer, getPersistedCustomer(returnedCustomer));

        insertedCustomer = returnedCustomer;
    }

    @Test
    @Transactional
    void createCustomerWithExistingId() throws Exception {
        // Create the Customer with an existing ID
        customer.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCustomers() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].customertype").value(hasItem(DEFAULT_CUSTOMERTYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].namewithinitials").value(hasItem(DEFAULT_NAMEWITHINITIALS)))
            .andExpect(jsonPath("$.[*].fullname").value(hasItem(DEFAULT_FULLNAME)))
            .andExpect(jsonPath("$.[*].callingname").value(hasItem(DEFAULT_CALLINGNAME)))
            .andExpect(jsonPath("$.[*].nicno").value(hasItem(DEFAULT_NICNO)))
            .andExpect(jsonPath("$.[*].nicissueddate").value(hasItem(DEFAULT_NICISSUEDDATE.toString())))
            .andExpect(jsonPath("$.[*].dateofbirth").value(hasItem(DEFAULT_DATEOFBIRTH.toString())))
            .andExpect(jsonPath("$.[*].bloodgroup").value(hasItem(DEFAULT_BLOODGROUP)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].maritalstatus").value(hasItem(DEFAULT_MARITALSTATUS)))
            .andExpect(jsonPath("$.[*].marrieddate").value(hasItem(DEFAULT_MARRIEDDATE.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].territory").value(hasItem(DEFAULT_TERRITORY)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].team").value(hasItem(DEFAULT_TEAM)))
            .andExpect(jsonPath("$.[*].businessname").value(hasItem(DEFAULT_BUSINESSNAME)))
            .andExpect(jsonPath("$.[*].businessregdate").value(hasItem(DEFAULT_BUSINESSREGDATE.toString())))
            .andExpect(jsonPath("$.[*].businessregno").value(hasItem(DEFAULT_BUSINESSREGNO)))
            .andExpect(jsonPath("$.[*].profilepicturepath").value(hasItem(DEFAULT_PROFILEPICTUREPATH)))
            .andExpect(jsonPath("$.[*].residencehouseno").value(hasItem(DEFAULT_RESIDENCEHOUSENO)))
            .andExpect(jsonPath("$.[*].residenceaddress").value(hasItem(DEFAULT_RESIDENCEADDRESS)))
            .andExpect(jsonPath("$.[*].residencecity").value(hasItem(DEFAULT_RESIDENCECITY)))
            .andExpect(jsonPath("$.[*].residencephone").value(hasItem(DEFAULT_RESIDENCEPHONE)))
            .andExpect(jsonPath("$.[*].businesslocationno").value(hasItem(DEFAULT_BUSINESSLOCATIONNO)))
            .andExpect(jsonPath("$.[*].businessaddress").value(hasItem(DEFAULT_BUSINESSADDRESS)))
            .andExpect(jsonPath("$.[*].businesscity").value(hasItem(DEFAULT_BUSINESSCITY)))
            .andExpect(jsonPath("$.[*].businessphone1").value(hasItem(DEFAULT_BUSINESSPHONE_1)))
            .andExpect(jsonPath("$.[*].businessphone2").value(hasItem(DEFAULT_BUSINESSPHONE_2)))
            .andExpect(jsonPath("$.[*].businessmobile").value(hasItem(DEFAULT_BUSINESSMOBILE)))
            .andExpect(jsonPath("$.[*].businessfax").value(hasItem(DEFAULT_BUSINESSFAX)))
            .andExpect(jsonPath("$.[*].businessemail").value(hasItem(DEFAULT_BUSINESSEMAIL)))
            .andExpect(jsonPath("$.[*].businessprovinceid").value(hasItem(DEFAULT_BUSINESSPROVINCEID)))
            .andExpect(jsonPath("$.[*].businessdistrictid").value(hasItem(DEFAULT_BUSINESSDISTRICTID)))
            .andExpect(jsonPath("$.[*].contactpersonname").value(hasItem(DEFAULT_CONTACTPERSONNAME)))
            .andExpect(jsonPath("$.[*].contactpersonphone").value(hasItem(DEFAULT_CONTACTPERSONPHONE)))
            .andExpect(jsonPath("$.[*].contactpersonmobile").value(hasItem(DEFAULT_CONTACTPERSONMOBILE)))
            .andExpect(jsonPath("$.[*].contactpersonemail").value(hasItem(DEFAULT_CONTACTPERSONEMAIL)))
            .andExpect(jsonPath("$.[*].rootmappath").value(hasItem(DEFAULT_ROOTMAPPATH)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].registrationdate").value(hasItem(DEFAULT_REGISTRATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE)))
            .andExpect(jsonPath("$.[*].isinternalcustomer").value(hasItem(DEFAULT_ISINTERNALCUSTOMER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].maximumdiscount").value(hasItem(DEFAULT_MAXIMUMDISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].creditlimit").value(hasItem(DEFAULT_CREDITLIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].hassecuritydeposit").value(hasItem(DEFAULT_HASSECURITYDEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].securitydepositamount").value(hasItem(DEFAULT_SECURITYDEPOSITAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paybycash").value(hasItem(DEFAULT_PAYBYCASH.booleanValue())))
            .andExpect(jsonPath("$.[*].cashpaymentbeforeload").value(hasItem(DEFAULT_CASHPAYMENTBEFORELOAD.booleanValue())))
            .andExpect(jsonPath("$.[*].cashlastinvoicebeforeload").value(hasItem(DEFAULT_CASHLASTINVOICEBEFORELOAD.booleanValue())))
            .andExpect(jsonPath("$.[*].paybycredit").value(hasItem(DEFAULT_PAYBYCREDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].creditoneweekcheck").value(hasItem(DEFAULT_CREDITONEWEEKCHECK.booleanValue())))
            .andExpect(jsonPath("$.[*].creditpaymentbydays").value(hasItem(DEFAULT_CREDITPAYMENTBYDAYS)))
            .andExpect(jsonPath("$.[*].haspurchasingdeposit").value(hasItem(DEFAULT_HASPURCHASINGDEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].hassecuritydepositbond").value(hasItem(DEFAULT_HASSECURITYDEPOSITBOND.booleanValue())))
            .andExpect(jsonPath("$.[*].hasassestsdeposit").value(hasItem(DEFAULT_HASASSESTSDEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].customerrootmappath").value(hasItem(DEFAULT_CUSTOMERROOTMAPPATH)))
            .andExpect(jsonPath("$.[*].employername").value(hasItem(DEFAULT_EMPLOYERNAME)))
            .andExpect(jsonPath("$.[*].employeraddress").value(hasItem(DEFAULT_EMPLOYERADDRESS)))
            .andExpect(jsonPath("$.[*].employerphone").value(hasItem(DEFAULT_EMPLOYERPHONE)))
            .andExpect(jsonPath("$.[*].employerdesignation").value(hasItem(DEFAULT_EMPLOYERDESIGNATION)))
            .andExpect(jsonPath("$.[*].previousemployername").value(hasItem(DEFAULT_PREVIOUSEMPLOYERNAME)))
            .andExpect(jsonPath("$.[*].previousemployeraddress").value(hasItem(DEFAULT_PREVIOUSEMPLOYERADDRESS)))
            .andExpect(jsonPath("$.[*].previousindustry").value(hasItem(DEFAULT_PREVIOUSINDUSTRY)))
            .andExpect(jsonPath("$.[*].previousperiod").value(hasItem(DEFAULT_PREVIOUSPERIOD)))
            .andExpect(jsonPath("$.[*].previouspositions").value(hasItem(DEFAULT_PREVIOUSPOSITIONS)))
            .andExpect(jsonPath("$.[*].previousresionforleaving").value(hasItem(DEFAULT_PREVIOUSRESIONFORLEAVING)))
            .andExpect(jsonPath("$.[*].hascreaditlimit").value(hasItem(DEFAULT_HASCREADITLIMIT.booleanValue())))
            .andExpect(jsonPath("$.[*].accountid").value(hasItem(DEFAULT_ACCOUNTID)))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE)))
            .andExpect(jsonPath("$.[*].isregistered").value(hasItem(DEFAULT_ISREGISTERED.booleanValue())))
            .andExpect(jsonPath("$.[*].vatregnumber").value(hasItem(DEFAULT_VATREGNUMBER)))
            .andExpect(jsonPath("$.[*].tinnumber").value(hasItem(DEFAULT_TINNUMBER)))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT)))
            .andExpect(jsonPath("$.[*].lon").value(hasItem(DEFAULT_LON)))
            .andExpect(jsonPath("$.[*].creditperiod").value(hasItem(DEFAULT_CREDITPERIOD)));
    }

    @Test
    @Transactional
    void getCustomer() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get the customer
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL_ID, customer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(customer.getId().intValue()))
            .andExpect(jsonPath("$.customertype").value(DEFAULT_CUSTOMERTYPE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.namewithinitials").value(DEFAULT_NAMEWITHINITIALS))
            .andExpect(jsonPath("$.fullname").value(DEFAULT_FULLNAME))
            .andExpect(jsonPath("$.callingname").value(DEFAULT_CALLINGNAME))
            .andExpect(jsonPath("$.nicno").value(DEFAULT_NICNO))
            .andExpect(jsonPath("$.nicissueddate").value(DEFAULT_NICISSUEDDATE.toString()))
            .andExpect(jsonPath("$.dateofbirth").value(DEFAULT_DATEOFBIRTH.toString()))
            .andExpect(jsonPath("$.bloodgroup").value(DEFAULT_BLOODGROUP))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.maritalstatus").value(DEFAULT_MARITALSTATUS))
            .andExpect(jsonPath("$.marrieddate").value(DEFAULT_MARRIEDDATE.toString()))
            .andExpect(jsonPath("$.nationality").value(DEFAULT_NATIONALITY))
            .andExpect(jsonPath("$.territory").value(DEFAULT_TERRITORY))
            .andExpect(jsonPath("$.religion").value(DEFAULT_RELIGION))
            .andExpect(jsonPath("$.team").value(DEFAULT_TEAM))
            .andExpect(jsonPath("$.businessname").value(DEFAULT_BUSINESSNAME))
            .andExpect(jsonPath("$.businessregdate").value(DEFAULT_BUSINESSREGDATE.toString()))
            .andExpect(jsonPath("$.businessregno").value(DEFAULT_BUSINESSREGNO))
            .andExpect(jsonPath("$.profilepicturepath").value(DEFAULT_PROFILEPICTUREPATH))
            .andExpect(jsonPath("$.residencehouseno").value(DEFAULT_RESIDENCEHOUSENO))
            .andExpect(jsonPath("$.residenceaddress").value(DEFAULT_RESIDENCEADDRESS))
            .andExpect(jsonPath("$.residencecity").value(DEFAULT_RESIDENCECITY))
            .andExpect(jsonPath("$.residencephone").value(DEFAULT_RESIDENCEPHONE))
            .andExpect(jsonPath("$.businesslocationno").value(DEFAULT_BUSINESSLOCATIONNO))
            .andExpect(jsonPath("$.businessaddress").value(DEFAULT_BUSINESSADDRESS))
            .andExpect(jsonPath("$.businesscity").value(DEFAULT_BUSINESSCITY))
            .andExpect(jsonPath("$.businessphone1").value(DEFAULT_BUSINESSPHONE_1))
            .andExpect(jsonPath("$.businessphone2").value(DEFAULT_BUSINESSPHONE_2))
            .andExpect(jsonPath("$.businessmobile").value(DEFAULT_BUSINESSMOBILE))
            .andExpect(jsonPath("$.businessfax").value(DEFAULT_BUSINESSFAX))
            .andExpect(jsonPath("$.businessemail").value(DEFAULT_BUSINESSEMAIL))
            .andExpect(jsonPath("$.businessprovinceid").value(DEFAULT_BUSINESSPROVINCEID))
            .andExpect(jsonPath("$.businessdistrictid").value(DEFAULT_BUSINESSDISTRICTID))
            .andExpect(jsonPath("$.contactpersonname").value(DEFAULT_CONTACTPERSONNAME))
            .andExpect(jsonPath("$.contactpersonphone").value(DEFAULT_CONTACTPERSONPHONE))
            .andExpect(jsonPath("$.contactpersonmobile").value(DEFAULT_CONTACTPERSONMOBILE))
            .andExpect(jsonPath("$.contactpersonemail").value(DEFAULT_CONTACTPERSONEMAIL))
            .andExpect(jsonPath("$.rootmappath").value(DEFAULT_ROOTMAPPATH))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE))
            .andExpect(jsonPath("$.registrationdate").value(DEFAULT_REGISTRATIONDATE.toString()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE))
            .andExpect(jsonPath("$.isinternalcustomer").value(DEFAULT_ISINTERNALCUSTOMER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.maximumdiscount").value(DEFAULT_MAXIMUMDISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.creditlimit").value(DEFAULT_CREDITLIMIT.doubleValue()))
            .andExpect(jsonPath("$.hassecuritydeposit").value(DEFAULT_HASSECURITYDEPOSIT.booleanValue()))
            .andExpect(jsonPath("$.securitydepositamount").value(DEFAULT_SECURITYDEPOSITAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.paybycash").value(DEFAULT_PAYBYCASH.booleanValue()))
            .andExpect(jsonPath("$.cashpaymentbeforeload").value(DEFAULT_CASHPAYMENTBEFORELOAD.booleanValue()))
            .andExpect(jsonPath("$.cashlastinvoicebeforeload").value(DEFAULT_CASHLASTINVOICEBEFORELOAD.booleanValue()))
            .andExpect(jsonPath("$.paybycredit").value(DEFAULT_PAYBYCREDIT.booleanValue()))
            .andExpect(jsonPath("$.creditoneweekcheck").value(DEFAULT_CREDITONEWEEKCHECK.booleanValue()))
            .andExpect(jsonPath("$.creditpaymentbydays").value(DEFAULT_CREDITPAYMENTBYDAYS))
            .andExpect(jsonPath("$.haspurchasingdeposit").value(DEFAULT_HASPURCHASINGDEPOSIT.booleanValue()))
            .andExpect(jsonPath("$.hassecuritydepositbond").value(DEFAULT_HASSECURITYDEPOSITBOND.booleanValue()))
            .andExpect(jsonPath("$.hasassestsdeposit").value(DEFAULT_HASASSESTSDEPOSIT.booleanValue()))
            .andExpect(jsonPath("$.customerrootmappath").value(DEFAULT_CUSTOMERROOTMAPPATH))
            .andExpect(jsonPath("$.employername").value(DEFAULT_EMPLOYERNAME))
            .andExpect(jsonPath("$.employeraddress").value(DEFAULT_EMPLOYERADDRESS))
            .andExpect(jsonPath("$.employerphone").value(DEFAULT_EMPLOYERPHONE))
            .andExpect(jsonPath("$.employerdesignation").value(DEFAULT_EMPLOYERDESIGNATION))
            .andExpect(jsonPath("$.previousemployername").value(DEFAULT_PREVIOUSEMPLOYERNAME))
            .andExpect(jsonPath("$.previousemployeraddress").value(DEFAULT_PREVIOUSEMPLOYERADDRESS))
            .andExpect(jsonPath("$.previousindustry").value(DEFAULT_PREVIOUSINDUSTRY))
            .andExpect(jsonPath("$.previousperiod").value(DEFAULT_PREVIOUSPERIOD))
            .andExpect(jsonPath("$.previouspositions").value(DEFAULT_PREVIOUSPOSITIONS))
            .andExpect(jsonPath("$.previousresionforleaving").value(DEFAULT_PREVIOUSRESIONFORLEAVING))
            .andExpect(jsonPath("$.hascreaditlimit").value(DEFAULT_HASCREADITLIMIT.booleanValue()))
            .andExpect(jsonPath("$.accountid").value(DEFAULT_ACCOUNTID))
            .andExpect(jsonPath("$.accountcode").value(DEFAULT_ACCOUNTCODE))
            .andExpect(jsonPath("$.isregistered").value(DEFAULT_ISREGISTERED.booleanValue()))
            .andExpect(jsonPath("$.vatregnumber").value(DEFAULT_VATREGNUMBER))
            .andExpect(jsonPath("$.tinnumber").value(DEFAULT_TINNUMBER))
            .andExpect(jsonPath("$.lat").value(DEFAULT_LAT))
            .andExpect(jsonPath("$.lon").value(DEFAULT_LON))
            .andExpect(jsonPath("$.creditperiod").value(DEFAULT_CREDITPERIOD));
    }

    @Test
    @Transactional
    void getCustomersByIdFiltering() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        Long id = customer.getId();

        defaultCustomerFiltering("id.equals=" + id, "id.notEquals=" + id);

        defaultCustomerFiltering("id.greaterThanOrEqual=" + id, "id.greaterThan=" + id);

        defaultCustomerFiltering("id.lessThanOrEqual=" + id, "id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype equals to
        defaultCustomerFiltering("customertype.equals=" + DEFAULT_CUSTOMERTYPE, "customertype.equals=" + UPDATED_CUSTOMERTYPE);
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype in
        defaultCustomerFiltering(
            "customertype.in=" + DEFAULT_CUSTOMERTYPE + "," + UPDATED_CUSTOMERTYPE,
            "customertype.in=" + UPDATED_CUSTOMERTYPE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype is not null
        defaultCustomerFiltering("customertype.specified=true", "customertype.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype is greater than or equal to
        defaultCustomerFiltering(
            "customertype.greaterThanOrEqual=" + DEFAULT_CUSTOMERTYPE,
            "customertype.greaterThanOrEqual=" + UPDATED_CUSTOMERTYPE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype is less than or equal to
        defaultCustomerFiltering(
            "customertype.lessThanOrEqual=" + DEFAULT_CUSTOMERTYPE,
            "customertype.lessThanOrEqual=" + SMALLER_CUSTOMERTYPE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype is less than
        defaultCustomerFiltering("customertype.lessThan=" + UPDATED_CUSTOMERTYPE, "customertype.lessThan=" + DEFAULT_CUSTOMERTYPE);
    }

    @Test
    @Transactional
    void getAllCustomersByCustomertypeIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customertype is greater than
        defaultCustomerFiltering("customertype.greaterThan=" + SMALLER_CUSTOMERTYPE, "customertype.greaterThan=" + DEFAULT_CUSTOMERTYPE);
    }

    @Test
    @Transactional
    void getAllCustomersByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where code equals to
        defaultCustomerFiltering("code.equals=" + DEFAULT_CODE, "code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllCustomersByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where code in
        defaultCustomerFiltering("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE, "code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllCustomersByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where code is not null
        defaultCustomerFiltering("code.specified=true", "code.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCodeContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where code contains
        defaultCustomerFiltering("code.contains=" + DEFAULT_CODE, "code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllCustomersByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where code does not contain
        defaultCustomerFiltering("code.doesNotContain=" + UPDATED_CODE, "code.doesNotContain=" + DEFAULT_CODE);
    }

    @Test
    @Transactional
    void getAllCustomersByTitleIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where title equals to
        defaultCustomerFiltering("title.equals=" + DEFAULT_TITLE, "title.equals=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllCustomersByTitleIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where title in
        defaultCustomerFiltering("title.in=" + DEFAULT_TITLE + "," + UPDATED_TITLE, "title.in=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllCustomersByTitleIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where title is not null
        defaultCustomerFiltering("title.specified=true", "title.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByTitleContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where title contains
        defaultCustomerFiltering("title.contains=" + DEFAULT_TITLE, "title.contains=" + UPDATED_TITLE);
    }

    @Test
    @Transactional
    void getAllCustomersByTitleNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where title does not contain
        defaultCustomerFiltering("title.doesNotContain=" + UPDATED_TITLE, "title.doesNotContain=" + DEFAULT_TITLE);
    }

    @Test
    @Transactional
    void getAllCustomersByNamewithinitialsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where namewithinitials equals to
        defaultCustomerFiltering(
            "namewithinitials.equals=" + DEFAULT_NAMEWITHINITIALS,
            "namewithinitials.equals=" + UPDATED_NAMEWITHINITIALS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNamewithinitialsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where namewithinitials in
        defaultCustomerFiltering(
            "namewithinitials.in=" + DEFAULT_NAMEWITHINITIALS + "," + UPDATED_NAMEWITHINITIALS,
            "namewithinitials.in=" + UPDATED_NAMEWITHINITIALS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNamewithinitialsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where namewithinitials is not null
        defaultCustomerFiltering("namewithinitials.specified=true", "namewithinitials.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByNamewithinitialsContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where namewithinitials contains
        defaultCustomerFiltering(
            "namewithinitials.contains=" + DEFAULT_NAMEWITHINITIALS,
            "namewithinitials.contains=" + UPDATED_NAMEWITHINITIALS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNamewithinitialsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where namewithinitials does not contain
        defaultCustomerFiltering(
            "namewithinitials.doesNotContain=" + UPDATED_NAMEWITHINITIALS,
            "namewithinitials.doesNotContain=" + DEFAULT_NAMEWITHINITIALS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByFullnameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where fullname equals to
        defaultCustomerFiltering("fullname.equals=" + DEFAULT_FULLNAME, "fullname.equals=" + UPDATED_FULLNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByFullnameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where fullname in
        defaultCustomerFiltering("fullname.in=" + DEFAULT_FULLNAME + "," + UPDATED_FULLNAME, "fullname.in=" + UPDATED_FULLNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByFullnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where fullname is not null
        defaultCustomerFiltering("fullname.specified=true", "fullname.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByFullnameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where fullname contains
        defaultCustomerFiltering("fullname.contains=" + DEFAULT_FULLNAME, "fullname.contains=" + UPDATED_FULLNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByFullnameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where fullname does not contain
        defaultCustomerFiltering("fullname.doesNotContain=" + UPDATED_FULLNAME, "fullname.doesNotContain=" + DEFAULT_FULLNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByCallingnameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where callingname equals to
        defaultCustomerFiltering("callingname.equals=" + DEFAULT_CALLINGNAME, "callingname.equals=" + UPDATED_CALLINGNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByCallingnameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where callingname in
        defaultCustomerFiltering(
            "callingname.in=" + DEFAULT_CALLINGNAME + "," + UPDATED_CALLINGNAME,
            "callingname.in=" + UPDATED_CALLINGNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCallingnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where callingname is not null
        defaultCustomerFiltering("callingname.specified=true", "callingname.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCallingnameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where callingname contains
        defaultCustomerFiltering("callingname.contains=" + DEFAULT_CALLINGNAME, "callingname.contains=" + UPDATED_CALLINGNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByCallingnameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where callingname does not contain
        defaultCustomerFiltering("callingname.doesNotContain=" + UPDATED_CALLINGNAME, "callingname.doesNotContain=" + DEFAULT_CALLINGNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByNicnoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicno equals to
        defaultCustomerFiltering("nicno.equals=" + DEFAULT_NICNO, "nicno.equals=" + UPDATED_NICNO);
    }

    @Test
    @Transactional
    void getAllCustomersByNicnoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicno in
        defaultCustomerFiltering("nicno.in=" + DEFAULT_NICNO + "," + UPDATED_NICNO, "nicno.in=" + UPDATED_NICNO);
    }

    @Test
    @Transactional
    void getAllCustomersByNicnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicno is not null
        defaultCustomerFiltering("nicno.specified=true", "nicno.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByNicnoContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicno contains
        defaultCustomerFiltering("nicno.contains=" + DEFAULT_NICNO, "nicno.contains=" + UPDATED_NICNO);
    }

    @Test
    @Transactional
    void getAllCustomersByNicnoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicno does not contain
        defaultCustomerFiltering("nicno.doesNotContain=" + UPDATED_NICNO, "nicno.doesNotContain=" + DEFAULT_NICNO);
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate equals to
        defaultCustomerFiltering("nicissueddate.equals=" + DEFAULT_NICISSUEDDATE, "nicissueddate.equals=" + UPDATED_NICISSUEDDATE);
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate in
        defaultCustomerFiltering(
            "nicissueddate.in=" + DEFAULT_NICISSUEDDATE + "," + UPDATED_NICISSUEDDATE,
            "nicissueddate.in=" + UPDATED_NICISSUEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate is not null
        defaultCustomerFiltering("nicissueddate.specified=true", "nicissueddate.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate is greater than or equal to
        defaultCustomerFiltering(
            "nicissueddate.greaterThanOrEqual=" + DEFAULT_NICISSUEDDATE,
            "nicissueddate.greaterThanOrEqual=" + UPDATED_NICISSUEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate is less than or equal to
        defaultCustomerFiltering(
            "nicissueddate.lessThanOrEqual=" + DEFAULT_NICISSUEDDATE,
            "nicissueddate.lessThanOrEqual=" + SMALLER_NICISSUEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate is less than
        defaultCustomerFiltering("nicissueddate.lessThan=" + UPDATED_NICISSUEDDATE, "nicissueddate.lessThan=" + DEFAULT_NICISSUEDDATE);
    }

    @Test
    @Transactional
    void getAllCustomersByNicissueddateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nicissueddate is greater than
        defaultCustomerFiltering(
            "nicissueddate.greaterThan=" + SMALLER_NICISSUEDDATE,
            "nicissueddate.greaterThan=" + DEFAULT_NICISSUEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth equals to
        defaultCustomerFiltering("dateofbirth.equals=" + DEFAULT_DATEOFBIRTH, "dateofbirth.equals=" + UPDATED_DATEOFBIRTH);
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth in
        defaultCustomerFiltering(
            "dateofbirth.in=" + DEFAULT_DATEOFBIRTH + "," + UPDATED_DATEOFBIRTH,
            "dateofbirth.in=" + UPDATED_DATEOFBIRTH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth is not null
        defaultCustomerFiltering("dateofbirth.specified=true", "dateofbirth.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth is greater than or equal to
        defaultCustomerFiltering(
            "dateofbirth.greaterThanOrEqual=" + DEFAULT_DATEOFBIRTH,
            "dateofbirth.greaterThanOrEqual=" + UPDATED_DATEOFBIRTH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth is less than or equal to
        defaultCustomerFiltering(
            "dateofbirth.lessThanOrEqual=" + DEFAULT_DATEOFBIRTH,
            "dateofbirth.lessThanOrEqual=" + SMALLER_DATEOFBIRTH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth is less than
        defaultCustomerFiltering("dateofbirth.lessThan=" + UPDATED_DATEOFBIRTH, "dateofbirth.lessThan=" + DEFAULT_DATEOFBIRTH);
    }

    @Test
    @Transactional
    void getAllCustomersByDateofbirthIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where dateofbirth is greater than
        defaultCustomerFiltering("dateofbirth.greaterThan=" + SMALLER_DATEOFBIRTH, "dateofbirth.greaterThan=" + DEFAULT_DATEOFBIRTH);
    }

    @Test
    @Transactional
    void getAllCustomersByBloodgroupIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where bloodgroup equals to
        defaultCustomerFiltering("bloodgroup.equals=" + DEFAULT_BLOODGROUP, "bloodgroup.equals=" + UPDATED_BLOODGROUP);
    }

    @Test
    @Transactional
    void getAllCustomersByBloodgroupIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where bloodgroup in
        defaultCustomerFiltering("bloodgroup.in=" + DEFAULT_BLOODGROUP + "," + UPDATED_BLOODGROUP, "bloodgroup.in=" + UPDATED_BLOODGROUP);
    }

    @Test
    @Transactional
    void getAllCustomersByBloodgroupIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where bloodgroup is not null
        defaultCustomerFiltering("bloodgroup.specified=true", "bloodgroup.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBloodgroupContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where bloodgroup contains
        defaultCustomerFiltering("bloodgroup.contains=" + DEFAULT_BLOODGROUP, "bloodgroup.contains=" + UPDATED_BLOODGROUP);
    }

    @Test
    @Transactional
    void getAllCustomersByBloodgroupNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where bloodgroup does not contain
        defaultCustomerFiltering("bloodgroup.doesNotContain=" + UPDATED_BLOODGROUP, "bloodgroup.doesNotContain=" + DEFAULT_BLOODGROUP);
    }

    @Test
    @Transactional
    void getAllCustomersByGenderIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where gender equals to
        defaultCustomerFiltering("gender.equals=" + DEFAULT_GENDER, "gender.equals=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllCustomersByGenderIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where gender in
        defaultCustomerFiltering("gender.in=" + DEFAULT_GENDER + "," + UPDATED_GENDER, "gender.in=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllCustomersByGenderIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where gender is not null
        defaultCustomerFiltering("gender.specified=true", "gender.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByGenderContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where gender contains
        defaultCustomerFiltering("gender.contains=" + DEFAULT_GENDER, "gender.contains=" + UPDATED_GENDER);
    }

    @Test
    @Transactional
    void getAllCustomersByGenderNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where gender does not contain
        defaultCustomerFiltering("gender.doesNotContain=" + UPDATED_GENDER, "gender.doesNotContain=" + DEFAULT_GENDER);
    }

    @Test
    @Transactional
    void getAllCustomersByMaritalstatusIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maritalstatus equals to
        defaultCustomerFiltering("maritalstatus.equals=" + DEFAULT_MARITALSTATUS, "maritalstatus.equals=" + UPDATED_MARITALSTATUS);
    }

    @Test
    @Transactional
    void getAllCustomersByMaritalstatusIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maritalstatus in
        defaultCustomerFiltering(
            "maritalstatus.in=" + DEFAULT_MARITALSTATUS + "," + UPDATED_MARITALSTATUS,
            "maritalstatus.in=" + UPDATED_MARITALSTATUS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMaritalstatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maritalstatus is not null
        defaultCustomerFiltering("maritalstatus.specified=true", "maritalstatus.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByMaritalstatusContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maritalstatus contains
        defaultCustomerFiltering("maritalstatus.contains=" + DEFAULT_MARITALSTATUS, "maritalstatus.contains=" + UPDATED_MARITALSTATUS);
    }

    @Test
    @Transactional
    void getAllCustomersByMaritalstatusNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maritalstatus does not contain
        defaultCustomerFiltering(
            "maritalstatus.doesNotContain=" + UPDATED_MARITALSTATUS,
            "maritalstatus.doesNotContain=" + DEFAULT_MARITALSTATUS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate equals to
        defaultCustomerFiltering("marrieddate.equals=" + DEFAULT_MARRIEDDATE, "marrieddate.equals=" + UPDATED_MARRIEDDATE);
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate in
        defaultCustomerFiltering(
            "marrieddate.in=" + DEFAULT_MARRIEDDATE + "," + UPDATED_MARRIEDDATE,
            "marrieddate.in=" + UPDATED_MARRIEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate is not null
        defaultCustomerFiltering("marrieddate.specified=true", "marrieddate.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate is greater than or equal to
        defaultCustomerFiltering(
            "marrieddate.greaterThanOrEqual=" + DEFAULT_MARRIEDDATE,
            "marrieddate.greaterThanOrEqual=" + UPDATED_MARRIEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate is less than or equal to
        defaultCustomerFiltering(
            "marrieddate.lessThanOrEqual=" + DEFAULT_MARRIEDDATE,
            "marrieddate.lessThanOrEqual=" + SMALLER_MARRIEDDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate is less than
        defaultCustomerFiltering("marrieddate.lessThan=" + UPDATED_MARRIEDDATE, "marrieddate.lessThan=" + DEFAULT_MARRIEDDATE);
    }

    @Test
    @Transactional
    void getAllCustomersByMarrieddateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where marrieddate is greater than
        defaultCustomerFiltering("marrieddate.greaterThan=" + SMALLER_MARRIEDDATE, "marrieddate.greaterThan=" + DEFAULT_MARRIEDDATE);
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality equals to
        defaultCustomerFiltering("nationality.equals=" + DEFAULT_NATIONALITY, "nationality.equals=" + UPDATED_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality in
        defaultCustomerFiltering(
            "nationality.in=" + DEFAULT_NATIONALITY + "," + UPDATED_NATIONALITY,
            "nationality.in=" + UPDATED_NATIONALITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality is not null
        defaultCustomerFiltering("nationality.specified=true", "nationality.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality is greater than or equal to
        defaultCustomerFiltering(
            "nationality.greaterThanOrEqual=" + DEFAULT_NATIONALITY,
            "nationality.greaterThanOrEqual=" + UPDATED_NATIONALITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality is less than or equal to
        defaultCustomerFiltering(
            "nationality.lessThanOrEqual=" + DEFAULT_NATIONALITY,
            "nationality.lessThanOrEqual=" + SMALLER_NATIONALITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality is less than
        defaultCustomerFiltering("nationality.lessThan=" + UPDATED_NATIONALITY, "nationality.lessThan=" + DEFAULT_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllCustomersByNationalityIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where nationality is greater than
        defaultCustomerFiltering("nationality.greaterThan=" + SMALLER_NATIONALITY, "nationality.greaterThan=" + DEFAULT_NATIONALITY);
    }

    @Test
    @Transactional
    void getAllCustomersByTerritoryIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where territory equals to
        defaultCustomerFiltering("territory.equals=" + DEFAULT_TERRITORY, "territory.equals=" + UPDATED_TERRITORY);
    }

    @Test
    @Transactional
    void getAllCustomersByTerritoryIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where territory in
        defaultCustomerFiltering("territory.in=" + DEFAULT_TERRITORY + "," + UPDATED_TERRITORY, "territory.in=" + UPDATED_TERRITORY);
    }

    @Test
    @Transactional
    void getAllCustomersByTerritoryIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where territory is not null
        defaultCustomerFiltering("territory.specified=true", "territory.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByTerritoryContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where territory contains
        defaultCustomerFiltering("territory.contains=" + DEFAULT_TERRITORY, "territory.contains=" + UPDATED_TERRITORY);
    }

    @Test
    @Transactional
    void getAllCustomersByTerritoryNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where territory does not contain
        defaultCustomerFiltering("territory.doesNotContain=" + UPDATED_TERRITORY, "territory.doesNotContain=" + DEFAULT_TERRITORY);
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion equals to
        defaultCustomerFiltering("religion.equals=" + DEFAULT_RELIGION, "religion.equals=" + UPDATED_RELIGION);
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion in
        defaultCustomerFiltering("religion.in=" + DEFAULT_RELIGION + "," + UPDATED_RELIGION, "religion.in=" + UPDATED_RELIGION);
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion is not null
        defaultCustomerFiltering("religion.specified=true", "religion.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion is greater than or equal to
        defaultCustomerFiltering("religion.greaterThanOrEqual=" + DEFAULT_RELIGION, "religion.greaterThanOrEqual=" + UPDATED_RELIGION);
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion is less than or equal to
        defaultCustomerFiltering("religion.lessThanOrEqual=" + DEFAULT_RELIGION, "religion.lessThanOrEqual=" + SMALLER_RELIGION);
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion is less than
        defaultCustomerFiltering("religion.lessThan=" + UPDATED_RELIGION, "religion.lessThan=" + DEFAULT_RELIGION);
    }

    @Test
    @Transactional
    void getAllCustomersByReligionIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where religion is greater than
        defaultCustomerFiltering("religion.greaterThan=" + SMALLER_RELIGION, "religion.greaterThan=" + DEFAULT_RELIGION);
    }

    @Test
    @Transactional
    void getAllCustomersByTeamIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where team equals to
        defaultCustomerFiltering("team.equals=" + DEFAULT_TEAM, "team.equals=" + UPDATED_TEAM);
    }

    @Test
    @Transactional
    void getAllCustomersByTeamIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where team in
        defaultCustomerFiltering("team.in=" + DEFAULT_TEAM + "," + UPDATED_TEAM, "team.in=" + UPDATED_TEAM);
    }

    @Test
    @Transactional
    void getAllCustomersByTeamIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where team is not null
        defaultCustomerFiltering("team.specified=true", "team.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByTeamContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where team contains
        defaultCustomerFiltering("team.contains=" + DEFAULT_TEAM, "team.contains=" + UPDATED_TEAM);
    }

    @Test
    @Transactional
    void getAllCustomersByTeamNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where team does not contain
        defaultCustomerFiltering("team.doesNotContain=" + UPDATED_TEAM, "team.doesNotContain=" + DEFAULT_TEAM);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessnameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessname equals to
        defaultCustomerFiltering("businessname.equals=" + DEFAULT_BUSINESSNAME, "businessname.equals=" + UPDATED_BUSINESSNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessnameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessname in
        defaultCustomerFiltering(
            "businessname.in=" + DEFAULT_BUSINESSNAME + "," + UPDATED_BUSINESSNAME,
            "businessname.in=" + UPDATED_BUSINESSNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessname is not null
        defaultCustomerFiltering("businessname.specified=true", "businessname.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessnameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessname contains
        defaultCustomerFiltering("businessname.contains=" + DEFAULT_BUSINESSNAME, "businessname.contains=" + UPDATED_BUSINESSNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessnameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessname does not contain
        defaultCustomerFiltering(
            "businessname.doesNotContain=" + UPDATED_BUSINESSNAME,
            "businessname.doesNotContain=" + DEFAULT_BUSINESSNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate equals to
        defaultCustomerFiltering("businessregdate.equals=" + DEFAULT_BUSINESSREGDATE, "businessregdate.equals=" + UPDATED_BUSINESSREGDATE);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate in
        defaultCustomerFiltering(
            "businessregdate.in=" + DEFAULT_BUSINESSREGDATE + "," + UPDATED_BUSINESSREGDATE,
            "businessregdate.in=" + UPDATED_BUSINESSREGDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate is not null
        defaultCustomerFiltering("businessregdate.specified=true", "businessregdate.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate is greater than or equal to
        defaultCustomerFiltering(
            "businessregdate.greaterThanOrEqual=" + DEFAULT_BUSINESSREGDATE,
            "businessregdate.greaterThanOrEqual=" + UPDATED_BUSINESSREGDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate is less than or equal to
        defaultCustomerFiltering(
            "businessregdate.lessThanOrEqual=" + DEFAULT_BUSINESSREGDATE,
            "businessregdate.lessThanOrEqual=" + SMALLER_BUSINESSREGDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate is less than
        defaultCustomerFiltering(
            "businessregdate.lessThan=" + UPDATED_BUSINESSREGDATE,
            "businessregdate.lessThan=" + DEFAULT_BUSINESSREGDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregdateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregdate is greater than
        defaultCustomerFiltering(
            "businessregdate.greaterThan=" + SMALLER_BUSINESSREGDATE,
            "businessregdate.greaterThan=" + DEFAULT_BUSINESSREGDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregnoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregno equals to
        defaultCustomerFiltering("businessregno.equals=" + DEFAULT_BUSINESSREGNO, "businessregno.equals=" + UPDATED_BUSINESSREGNO);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregnoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregno in
        defaultCustomerFiltering(
            "businessregno.in=" + DEFAULT_BUSINESSREGNO + "," + UPDATED_BUSINESSREGNO,
            "businessregno.in=" + UPDATED_BUSINESSREGNO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregno is not null
        defaultCustomerFiltering("businessregno.specified=true", "businessregno.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregnoContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregno contains
        defaultCustomerFiltering("businessregno.contains=" + DEFAULT_BUSINESSREGNO, "businessregno.contains=" + UPDATED_BUSINESSREGNO);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessregnoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessregno does not contain
        defaultCustomerFiltering(
            "businessregno.doesNotContain=" + UPDATED_BUSINESSREGNO,
            "businessregno.doesNotContain=" + DEFAULT_BUSINESSREGNO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByProfilepicturepathIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where profilepicturepath equals to
        defaultCustomerFiltering(
            "profilepicturepath.equals=" + DEFAULT_PROFILEPICTUREPATH,
            "profilepicturepath.equals=" + UPDATED_PROFILEPICTUREPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByProfilepicturepathIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where profilepicturepath in
        defaultCustomerFiltering(
            "profilepicturepath.in=" + DEFAULT_PROFILEPICTUREPATH + "," + UPDATED_PROFILEPICTUREPATH,
            "profilepicturepath.in=" + UPDATED_PROFILEPICTUREPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByProfilepicturepathIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where profilepicturepath is not null
        defaultCustomerFiltering("profilepicturepath.specified=true", "profilepicturepath.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByProfilepicturepathContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where profilepicturepath contains
        defaultCustomerFiltering(
            "profilepicturepath.contains=" + DEFAULT_PROFILEPICTUREPATH,
            "profilepicturepath.contains=" + UPDATED_PROFILEPICTUREPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByProfilepicturepathNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where profilepicturepath does not contain
        defaultCustomerFiltering(
            "profilepicturepath.doesNotContain=" + UPDATED_PROFILEPICTUREPATH,
            "profilepicturepath.doesNotContain=" + DEFAULT_PROFILEPICTUREPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencehousenoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencehouseno equals to
        defaultCustomerFiltering(
            "residencehouseno.equals=" + DEFAULT_RESIDENCEHOUSENO,
            "residencehouseno.equals=" + UPDATED_RESIDENCEHOUSENO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencehousenoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencehouseno in
        defaultCustomerFiltering(
            "residencehouseno.in=" + DEFAULT_RESIDENCEHOUSENO + "," + UPDATED_RESIDENCEHOUSENO,
            "residencehouseno.in=" + UPDATED_RESIDENCEHOUSENO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencehousenoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencehouseno is not null
        defaultCustomerFiltering("residencehouseno.specified=true", "residencehouseno.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByResidencehousenoContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencehouseno contains
        defaultCustomerFiltering(
            "residencehouseno.contains=" + DEFAULT_RESIDENCEHOUSENO,
            "residencehouseno.contains=" + UPDATED_RESIDENCEHOUSENO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencehousenoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencehouseno does not contain
        defaultCustomerFiltering(
            "residencehouseno.doesNotContain=" + UPDATED_RESIDENCEHOUSENO,
            "residencehouseno.doesNotContain=" + DEFAULT_RESIDENCEHOUSENO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidenceaddressIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residenceaddress equals to
        defaultCustomerFiltering(
            "residenceaddress.equals=" + DEFAULT_RESIDENCEADDRESS,
            "residenceaddress.equals=" + UPDATED_RESIDENCEADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidenceaddressIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residenceaddress in
        defaultCustomerFiltering(
            "residenceaddress.in=" + DEFAULT_RESIDENCEADDRESS + "," + UPDATED_RESIDENCEADDRESS,
            "residenceaddress.in=" + UPDATED_RESIDENCEADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidenceaddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residenceaddress is not null
        defaultCustomerFiltering("residenceaddress.specified=true", "residenceaddress.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByResidenceaddressContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residenceaddress contains
        defaultCustomerFiltering(
            "residenceaddress.contains=" + DEFAULT_RESIDENCEADDRESS,
            "residenceaddress.contains=" + UPDATED_RESIDENCEADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidenceaddressNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residenceaddress does not contain
        defaultCustomerFiltering(
            "residenceaddress.doesNotContain=" + UPDATED_RESIDENCEADDRESS,
            "residenceaddress.doesNotContain=" + DEFAULT_RESIDENCEADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencecityIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencecity equals to
        defaultCustomerFiltering("residencecity.equals=" + DEFAULT_RESIDENCECITY, "residencecity.equals=" + UPDATED_RESIDENCECITY);
    }

    @Test
    @Transactional
    void getAllCustomersByResidencecityIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencecity in
        defaultCustomerFiltering(
            "residencecity.in=" + DEFAULT_RESIDENCECITY + "," + UPDATED_RESIDENCECITY,
            "residencecity.in=" + UPDATED_RESIDENCECITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencecityIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencecity is not null
        defaultCustomerFiltering("residencecity.specified=true", "residencecity.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByResidencecityContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencecity contains
        defaultCustomerFiltering("residencecity.contains=" + DEFAULT_RESIDENCECITY, "residencecity.contains=" + UPDATED_RESIDENCECITY);
    }

    @Test
    @Transactional
    void getAllCustomersByResidencecityNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencecity does not contain
        defaultCustomerFiltering(
            "residencecity.doesNotContain=" + UPDATED_RESIDENCECITY,
            "residencecity.doesNotContain=" + DEFAULT_RESIDENCECITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencephoneIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencephone equals to
        defaultCustomerFiltering("residencephone.equals=" + DEFAULT_RESIDENCEPHONE, "residencephone.equals=" + UPDATED_RESIDENCEPHONE);
    }

    @Test
    @Transactional
    void getAllCustomersByResidencephoneIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencephone in
        defaultCustomerFiltering(
            "residencephone.in=" + DEFAULT_RESIDENCEPHONE + "," + UPDATED_RESIDENCEPHONE,
            "residencephone.in=" + UPDATED_RESIDENCEPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByResidencephoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencephone is not null
        defaultCustomerFiltering("residencephone.specified=true", "residencephone.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByResidencephoneContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencephone contains
        defaultCustomerFiltering("residencephone.contains=" + DEFAULT_RESIDENCEPHONE, "residencephone.contains=" + UPDATED_RESIDENCEPHONE);
    }

    @Test
    @Transactional
    void getAllCustomersByResidencephoneNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where residencephone does not contain
        defaultCustomerFiltering(
            "residencephone.doesNotContain=" + UPDATED_RESIDENCEPHONE,
            "residencephone.doesNotContain=" + DEFAULT_RESIDENCEPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesslocationnoIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesslocationno equals to
        defaultCustomerFiltering(
            "businesslocationno.equals=" + DEFAULT_BUSINESSLOCATIONNO,
            "businesslocationno.equals=" + UPDATED_BUSINESSLOCATIONNO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesslocationnoIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesslocationno in
        defaultCustomerFiltering(
            "businesslocationno.in=" + DEFAULT_BUSINESSLOCATIONNO + "," + UPDATED_BUSINESSLOCATIONNO,
            "businesslocationno.in=" + UPDATED_BUSINESSLOCATIONNO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesslocationnoIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesslocationno is not null
        defaultCustomerFiltering("businesslocationno.specified=true", "businesslocationno.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesslocationnoContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesslocationno contains
        defaultCustomerFiltering(
            "businesslocationno.contains=" + DEFAULT_BUSINESSLOCATIONNO,
            "businesslocationno.contains=" + UPDATED_BUSINESSLOCATIONNO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesslocationnoNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesslocationno does not contain
        defaultCustomerFiltering(
            "businesslocationno.doesNotContain=" + UPDATED_BUSINESSLOCATIONNO,
            "businesslocationno.doesNotContain=" + DEFAULT_BUSINESSLOCATIONNO
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessaddressIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessaddress equals to
        defaultCustomerFiltering("businessaddress.equals=" + DEFAULT_BUSINESSADDRESS, "businessaddress.equals=" + UPDATED_BUSINESSADDRESS);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessaddressIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessaddress in
        defaultCustomerFiltering(
            "businessaddress.in=" + DEFAULT_BUSINESSADDRESS + "," + UPDATED_BUSINESSADDRESS,
            "businessaddress.in=" + UPDATED_BUSINESSADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessaddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessaddress is not null
        defaultCustomerFiltering("businessaddress.specified=true", "businessaddress.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessaddressContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessaddress contains
        defaultCustomerFiltering(
            "businessaddress.contains=" + DEFAULT_BUSINESSADDRESS,
            "businessaddress.contains=" + UPDATED_BUSINESSADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessaddressNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessaddress does not contain
        defaultCustomerFiltering(
            "businessaddress.doesNotContain=" + UPDATED_BUSINESSADDRESS,
            "businessaddress.doesNotContain=" + DEFAULT_BUSINESSADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesscityIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesscity equals to
        defaultCustomerFiltering("businesscity.equals=" + DEFAULT_BUSINESSCITY, "businesscity.equals=" + UPDATED_BUSINESSCITY);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesscityIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesscity in
        defaultCustomerFiltering(
            "businesscity.in=" + DEFAULT_BUSINESSCITY + "," + UPDATED_BUSINESSCITY,
            "businesscity.in=" + UPDATED_BUSINESSCITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesscityIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesscity is not null
        defaultCustomerFiltering("businesscity.specified=true", "businesscity.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesscityContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesscity contains
        defaultCustomerFiltering("businesscity.contains=" + DEFAULT_BUSINESSCITY, "businesscity.contains=" + UPDATED_BUSINESSCITY);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinesscityNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businesscity does not contain
        defaultCustomerFiltering(
            "businesscity.doesNotContain=" + UPDATED_BUSINESSCITY,
            "businesscity.doesNotContain=" + DEFAULT_BUSINESSCITY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone1IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone1 equals to
        defaultCustomerFiltering("businessphone1.equals=" + DEFAULT_BUSINESSPHONE_1, "businessphone1.equals=" + UPDATED_BUSINESSPHONE_1);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone1IsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone1 in
        defaultCustomerFiltering(
            "businessphone1.in=" + DEFAULT_BUSINESSPHONE_1 + "," + UPDATED_BUSINESSPHONE_1,
            "businessphone1.in=" + UPDATED_BUSINESSPHONE_1
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone1IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone1 is not null
        defaultCustomerFiltering("businessphone1.specified=true", "businessphone1.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone1ContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone1 contains
        defaultCustomerFiltering(
            "businessphone1.contains=" + DEFAULT_BUSINESSPHONE_1,
            "businessphone1.contains=" + UPDATED_BUSINESSPHONE_1
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone1NotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone1 does not contain
        defaultCustomerFiltering(
            "businessphone1.doesNotContain=" + UPDATED_BUSINESSPHONE_1,
            "businessphone1.doesNotContain=" + DEFAULT_BUSINESSPHONE_1
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone2IsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone2 equals to
        defaultCustomerFiltering("businessphone2.equals=" + DEFAULT_BUSINESSPHONE_2, "businessphone2.equals=" + UPDATED_BUSINESSPHONE_2);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone2IsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone2 in
        defaultCustomerFiltering(
            "businessphone2.in=" + DEFAULT_BUSINESSPHONE_2 + "," + UPDATED_BUSINESSPHONE_2,
            "businessphone2.in=" + UPDATED_BUSINESSPHONE_2
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone2IsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone2 is not null
        defaultCustomerFiltering("businessphone2.specified=true", "businessphone2.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone2ContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone2 contains
        defaultCustomerFiltering(
            "businessphone2.contains=" + DEFAULT_BUSINESSPHONE_2,
            "businessphone2.contains=" + UPDATED_BUSINESSPHONE_2
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessphone2NotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessphone2 does not contain
        defaultCustomerFiltering(
            "businessphone2.doesNotContain=" + UPDATED_BUSINESSPHONE_2,
            "businessphone2.doesNotContain=" + DEFAULT_BUSINESSPHONE_2
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessmobileIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessmobile equals to
        defaultCustomerFiltering("businessmobile.equals=" + DEFAULT_BUSINESSMOBILE, "businessmobile.equals=" + UPDATED_BUSINESSMOBILE);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessmobileIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessmobile in
        defaultCustomerFiltering(
            "businessmobile.in=" + DEFAULT_BUSINESSMOBILE + "," + UPDATED_BUSINESSMOBILE,
            "businessmobile.in=" + UPDATED_BUSINESSMOBILE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessmobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessmobile is not null
        defaultCustomerFiltering("businessmobile.specified=true", "businessmobile.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessmobileContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessmobile contains
        defaultCustomerFiltering("businessmobile.contains=" + DEFAULT_BUSINESSMOBILE, "businessmobile.contains=" + UPDATED_BUSINESSMOBILE);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessmobileNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessmobile does not contain
        defaultCustomerFiltering(
            "businessmobile.doesNotContain=" + UPDATED_BUSINESSMOBILE,
            "businessmobile.doesNotContain=" + DEFAULT_BUSINESSMOBILE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessfaxIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessfax equals to
        defaultCustomerFiltering("businessfax.equals=" + DEFAULT_BUSINESSFAX, "businessfax.equals=" + UPDATED_BUSINESSFAX);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessfaxIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessfax in
        defaultCustomerFiltering(
            "businessfax.in=" + DEFAULT_BUSINESSFAX + "," + UPDATED_BUSINESSFAX,
            "businessfax.in=" + UPDATED_BUSINESSFAX
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessfaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessfax is not null
        defaultCustomerFiltering("businessfax.specified=true", "businessfax.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessfaxContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessfax contains
        defaultCustomerFiltering("businessfax.contains=" + DEFAULT_BUSINESSFAX, "businessfax.contains=" + UPDATED_BUSINESSFAX);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessfaxNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessfax does not contain
        defaultCustomerFiltering("businessfax.doesNotContain=" + UPDATED_BUSINESSFAX, "businessfax.doesNotContain=" + DEFAULT_BUSINESSFAX);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessemailIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessemail equals to
        defaultCustomerFiltering("businessemail.equals=" + DEFAULT_BUSINESSEMAIL, "businessemail.equals=" + UPDATED_BUSINESSEMAIL);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessemailIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessemail in
        defaultCustomerFiltering(
            "businessemail.in=" + DEFAULT_BUSINESSEMAIL + "," + UPDATED_BUSINESSEMAIL,
            "businessemail.in=" + UPDATED_BUSINESSEMAIL
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessemailIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessemail is not null
        defaultCustomerFiltering("businessemail.specified=true", "businessemail.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessemailContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessemail contains
        defaultCustomerFiltering("businessemail.contains=" + DEFAULT_BUSINESSEMAIL, "businessemail.contains=" + UPDATED_BUSINESSEMAIL);
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessemailNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessemail does not contain
        defaultCustomerFiltering(
            "businessemail.doesNotContain=" + UPDATED_BUSINESSEMAIL,
            "businessemail.doesNotContain=" + DEFAULT_BUSINESSEMAIL
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid equals to
        defaultCustomerFiltering(
            "businessprovinceid.equals=" + DEFAULT_BUSINESSPROVINCEID,
            "businessprovinceid.equals=" + UPDATED_BUSINESSPROVINCEID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid in
        defaultCustomerFiltering(
            "businessprovinceid.in=" + DEFAULT_BUSINESSPROVINCEID + "," + UPDATED_BUSINESSPROVINCEID,
            "businessprovinceid.in=" + UPDATED_BUSINESSPROVINCEID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid is not null
        defaultCustomerFiltering("businessprovinceid.specified=true", "businessprovinceid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid is greater than or equal to
        defaultCustomerFiltering(
            "businessprovinceid.greaterThanOrEqual=" + DEFAULT_BUSINESSPROVINCEID,
            "businessprovinceid.greaterThanOrEqual=" + UPDATED_BUSINESSPROVINCEID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid is less than or equal to
        defaultCustomerFiltering(
            "businessprovinceid.lessThanOrEqual=" + DEFAULT_BUSINESSPROVINCEID,
            "businessprovinceid.lessThanOrEqual=" + SMALLER_BUSINESSPROVINCEID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid is less than
        defaultCustomerFiltering(
            "businessprovinceid.lessThan=" + UPDATED_BUSINESSPROVINCEID,
            "businessprovinceid.lessThan=" + DEFAULT_BUSINESSPROVINCEID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessprovinceidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessprovinceid is greater than
        defaultCustomerFiltering(
            "businessprovinceid.greaterThan=" + SMALLER_BUSINESSPROVINCEID,
            "businessprovinceid.greaterThan=" + DEFAULT_BUSINESSPROVINCEID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid equals to
        defaultCustomerFiltering(
            "businessdistrictid.equals=" + DEFAULT_BUSINESSDISTRICTID,
            "businessdistrictid.equals=" + UPDATED_BUSINESSDISTRICTID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid in
        defaultCustomerFiltering(
            "businessdistrictid.in=" + DEFAULT_BUSINESSDISTRICTID + "," + UPDATED_BUSINESSDISTRICTID,
            "businessdistrictid.in=" + UPDATED_BUSINESSDISTRICTID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid is not null
        defaultCustomerFiltering("businessdistrictid.specified=true", "businessdistrictid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid is greater than or equal to
        defaultCustomerFiltering(
            "businessdistrictid.greaterThanOrEqual=" + DEFAULT_BUSINESSDISTRICTID,
            "businessdistrictid.greaterThanOrEqual=" + UPDATED_BUSINESSDISTRICTID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid is less than or equal to
        defaultCustomerFiltering(
            "businessdistrictid.lessThanOrEqual=" + DEFAULT_BUSINESSDISTRICTID,
            "businessdistrictid.lessThanOrEqual=" + SMALLER_BUSINESSDISTRICTID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid is less than
        defaultCustomerFiltering(
            "businessdistrictid.lessThan=" + UPDATED_BUSINESSDISTRICTID,
            "businessdistrictid.lessThan=" + DEFAULT_BUSINESSDISTRICTID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByBusinessdistrictidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where businessdistrictid is greater than
        defaultCustomerFiltering(
            "businessdistrictid.greaterThan=" + SMALLER_BUSINESSDISTRICTID,
            "businessdistrictid.greaterThan=" + DEFAULT_BUSINESSDISTRICTID
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonnameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonname equals to
        defaultCustomerFiltering(
            "contactpersonname.equals=" + DEFAULT_CONTACTPERSONNAME,
            "contactpersonname.equals=" + UPDATED_CONTACTPERSONNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonnameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonname in
        defaultCustomerFiltering(
            "contactpersonname.in=" + DEFAULT_CONTACTPERSONNAME + "," + UPDATED_CONTACTPERSONNAME,
            "contactpersonname.in=" + UPDATED_CONTACTPERSONNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonnameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonname is not null
        defaultCustomerFiltering("contactpersonname.specified=true", "contactpersonname.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonnameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonname contains
        defaultCustomerFiltering(
            "contactpersonname.contains=" + DEFAULT_CONTACTPERSONNAME,
            "contactpersonname.contains=" + UPDATED_CONTACTPERSONNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonnameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonname does not contain
        defaultCustomerFiltering(
            "contactpersonname.doesNotContain=" + UPDATED_CONTACTPERSONNAME,
            "contactpersonname.doesNotContain=" + DEFAULT_CONTACTPERSONNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonphoneIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonphone equals to
        defaultCustomerFiltering(
            "contactpersonphone.equals=" + DEFAULT_CONTACTPERSONPHONE,
            "contactpersonphone.equals=" + UPDATED_CONTACTPERSONPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonphoneIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonphone in
        defaultCustomerFiltering(
            "contactpersonphone.in=" + DEFAULT_CONTACTPERSONPHONE + "," + UPDATED_CONTACTPERSONPHONE,
            "contactpersonphone.in=" + UPDATED_CONTACTPERSONPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonphoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonphone is not null
        defaultCustomerFiltering("contactpersonphone.specified=true", "contactpersonphone.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonphoneContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonphone contains
        defaultCustomerFiltering(
            "contactpersonphone.contains=" + DEFAULT_CONTACTPERSONPHONE,
            "contactpersonphone.contains=" + UPDATED_CONTACTPERSONPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonphoneNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonphone does not contain
        defaultCustomerFiltering(
            "contactpersonphone.doesNotContain=" + UPDATED_CONTACTPERSONPHONE,
            "contactpersonphone.doesNotContain=" + DEFAULT_CONTACTPERSONPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonmobileIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonmobile equals to
        defaultCustomerFiltering(
            "contactpersonmobile.equals=" + DEFAULT_CONTACTPERSONMOBILE,
            "contactpersonmobile.equals=" + UPDATED_CONTACTPERSONMOBILE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonmobileIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonmobile in
        defaultCustomerFiltering(
            "contactpersonmobile.in=" + DEFAULT_CONTACTPERSONMOBILE + "," + UPDATED_CONTACTPERSONMOBILE,
            "contactpersonmobile.in=" + UPDATED_CONTACTPERSONMOBILE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonmobileIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonmobile is not null
        defaultCustomerFiltering("contactpersonmobile.specified=true", "contactpersonmobile.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonmobileContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonmobile contains
        defaultCustomerFiltering(
            "contactpersonmobile.contains=" + DEFAULT_CONTACTPERSONMOBILE,
            "contactpersonmobile.contains=" + UPDATED_CONTACTPERSONMOBILE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonmobileNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonmobile does not contain
        defaultCustomerFiltering(
            "contactpersonmobile.doesNotContain=" + UPDATED_CONTACTPERSONMOBILE,
            "contactpersonmobile.doesNotContain=" + DEFAULT_CONTACTPERSONMOBILE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonemailIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonemail equals to
        defaultCustomerFiltering(
            "contactpersonemail.equals=" + DEFAULT_CONTACTPERSONEMAIL,
            "contactpersonemail.equals=" + UPDATED_CONTACTPERSONEMAIL
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonemailIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonemail in
        defaultCustomerFiltering(
            "contactpersonemail.in=" + DEFAULT_CONTACTPERSONEMAIL + "," + UPDATED_CONTACTPERSONEMAIL,
            "contactpersonemail.in=" + UPDATED_CONTACTPERSONEMAIL
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonemailIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonemail is not null
        defaultCustomerFiltering("contactpersonemail.specified=true", "contactpersonemail.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonemailContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonemail contains
        defaultCustomerFiltering(
            "contactpersonemail.contains=" + DEFAULT_CONTACTPERSONEMAIL,
            "contactpersonemail.contains=" + UPDATED_CONTACTPERSONEMAIL
        );
    }

    @Test
    @Transactional
    void getAllCustomersByContactpersonemailNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where contactpersonemail does not contain
        defaultCustomerFiltering(
            "contactpersonemail.doesNotContain=" + UPDATED_CONTACTPERSONEMAIL,
            "contactpersonemail.doesNotContain=" + DEFAULT_CONTACTPERSONEMAIL
        );
    }

    @Test
    @Transactional
    void getAllCustomersByRootmappathIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where rootmappath equals to
        defaultCustomerFiltering("rootmappath.equals=" + DEFAULT_ROOTMAPPATH, "rootmappath.equals=" + UPDATED_ROOTMAPPATH);
    }

    @Test
    @Transactional
    void getAllCustomersByRootmappathIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where rootmappath in
        defaultCustomerFiltering(
            "rootmappath.in=" + DEFAULT_ROOTMAPPATH + "," + UPDATED_ROOTMAPPATH,
            "rootmappath.in=" + UPDATED_ROOTMAPPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByRootmappathIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where rootmappath is not null
        defaultCustomerFiltering("rootmappath.specified=true", "rootmappath.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByRootmappathContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where rootmappath contains
        defaultCustomerFiltering("rootmappath.contains=" + DEFAULT_ROOTMAPPATH, "rootmappath.contains=" + UPDATED_ROOTMAPPATH);
    }

    @Test
    @Transactional
    void getAllCustomersByRootmappathNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where rootmappath does not contain
        defaultCustomerFiltering("rootmappath.doesNotContain=" + UPDATED_ROOTMAPPATH, "rootmappath.doesNotContain=" + DEFAULT_ROOTMAPPATH);
    }

    @Test
    @Transactional
    void getAllCustomersByWebsiteIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where website equals to
        defaultCustomerFiltering("website.equals=" + DEFAULT_WEBSITE, "website.equals=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void getAllCustomersByWebsiteIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where website in
        defaultCustomerFiltering("website.in=" + DEFAULT_WEBSITE + "," + UPDATED_WEBSITE, "website.in=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void getAllCustomersByWebsiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where website is not null
        defaultCustomerFiltering("website.specified=true", "website.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByWebsiteContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where website contains
        defaultCustomerFiltering("website.contains=" + DEFAULT_WEBSITE, "website.contains=" + UPDATED_WEBSITE);
    }

    @Test
    @Transactional
    void getAllCustomersByWebsiteNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where website does not contain
        defaultCustomerFiltering("website.doesNotContain=" + UPDATED_WEBSITE, "website.doesNotContain=" + DEFAULT_WEBSITE);
    }

    @Test
    @Transactional
    void getAllCustomersByRegistrationdateIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where registrationdate equals to
        defaultCustomerFiltering(
            "registrationdate.equals=" + DEFAULT_REGISTRATIONDATE,
            "registrationdate.equals=" + UPDATED_REGISTRATIONDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByRegistrationdateIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where registrationdate in
        defaultCustomerFiltering(
            "registrationdate.in=" + DEFAULT_REGISTRATIONDATE + "," + UPDATED_REGISTRATIONDATE,
            "registrationdate.in=" + UPDATED_REGISTRATIONDATE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByRegistrationdateIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where registrationdate is not null
        defaultCustomerFiltering("registrationdate.specified=true", "registrationdate.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive equals to
        defaultCustomerFiltering("isactive.equals=" + DEFAULT_ISACTIVE, "isactive.equals=" + UPDATED_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive in
        defaultCustomerFiltering("isactive.in=" + DEFAULT_ISACTIVE + "," + UPDATED_ISACTIVE, "isactive.in=" + UPDATED_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive is not null
        defaultCustomerFiltering("isactive.specified=true", "isactive.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive is greater than or equal to
        defaultCustomerFiltering("isactive.greaterThanOrEqual=" + DEFAULT_ISACTIVE, "isactive.greaterThanOrEqual=" + UPDATED_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive is less than or equal to
        defaultCustomerFiltering("isactive.lessThanOrEqual=" + DEFAULT_ISACTIVE, "isactive.lessThanOrEqual=" + SMALLER_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive is less than
        defaultCustomerFiltering("isactive.lessThan=" + UPDATED_ISACTIVE, "isactive.lessThan=" + DEFAULT_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsactiveIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isactive is greater than
        defaultCustomerFiltering("isactive.greaterThan=" + SMALLER_ISACTIVE, "isactive.greaterThan=" + DEFAULT_ISACTIVE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer equals to
        defaultCustomerFiltering(
            "isinternalcustomer.equals=" + DEFAULT_ISINTERNALCUSTOMER,
            "isinternalcustomer.equals=" + UPDATED_ISINTERNALCUSTOMER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer in
        defaultCustomerFiltering(
            "isinternalcustomer.in=" + DEFAULT_ISINTERNALCUSTOMER + "," + UPDATED_ISINTERNALCUSTOMER,
            "isinternalcustomer.in=" + UPDATED_ISINTERNALCUSTOMER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer is not null
        defaultCustomerFiltering("isinternalcustomer.specified=true", "isinternalcustomer.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer is greater than or equal to
        defaultCustomerFiltering(
            "isinternalcustomer.greaterThanOrEqual=" + DEFAULT_ISINTERNALCUSTOMER,
            "isinternalcustomer.greaterThanOrEqual=" + UPDATED_ISINTERNALCUSTOMER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer is less than or equal to
        defaultCustomerFiltering(
            "isinternalcustomer.lessThanOrEqual=" + DEFAULT_ISINTERNALCUSTOMER,
            "isinternalcustomer.lessThanOrEqual=" + SMALLER_ISINTERNALCUSTOMER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer is less than
        defaultCustomerFiltering(
            "isinternalcustomer.lessThan=" + UPDATED_ISINTERNALCUSTOMER,
            "isinternalcustomer.lessThan=" + DEFAULT_ISINTERNALCUSTOMER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByIsinternalcustomerIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isinternalcustomer is greater than
        defaultCustomerFiltering(
            "isinternalcustomer.greaterThan=" + SMALLER_ISINTERNALCUSTOMER,
            "isinternalcustomer.greaterThan=" + DEFAULT_ISINTERNALCUSTOMER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where description equals to
        defaultCustomerFiltering("description.equals=" + DEFAULT_DESCRIPTION, "description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCustomersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where description in
        defaultCustomerFiltering(
            "description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION,
            "description.in=" + UPDATED_DESCRIPTION
        );
    }

    @Test
    @Transactional
    void getAllCustomersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where description is not null
        defaultCustomerFiltering("description.specified=true", "description.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where description contains
        defaultCustomerFiltering("description.contains=" + DEFAULT_DESCRIPTION, "description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCustomersByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where description does not contain
        defaultCustomerFiltering("description.doesNotContain=" + UPDATED_DESCRIPTION, "description.doesNotContain=" + DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu equals to
        defaultCustomerFiltering("lmu.equals=" + DEFAULT_LMU, "lmu.equals=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu in
        defaultCustomerFiltering("lmu.in=" + DEFAULT_LMU + "," + UPDATED_LMU, "lmu.in=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu is not null
        defaultCustomerFiltering("lmu.specified=true", "lmu.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu is greater than or equal to
        defaultCustomerFiltering("lmu.greaterThanOrEqual=" + DEFAULT_LMU, "lmu.greaterThanOrEqual=" + UPDATED_LMU);
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu is less than or equal to
        defaultCustomerFiltering("lmu.lessThanOrEqual=" + DEFAULT_LMU, "lmu.lessThanOrEqual=" + SMALLER_LMU);
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu is less than
        defaultCustomerFiltering("lmu.lessThan=" + UPDATED_LMU, "lmu.lessThan=" + DEFAULT_LMU);
    }

    @Test
    @Transactional
    void getAllCustomersByLmuIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmu is greater than
        defaultCustomerFiltering("lmu.greaterThan=" + SMALLER_LMU, "lmu.greaterThan=" + DEFAULT_LMU);
    }

    @Test
    @Transactional
    void getAllCustomersByLmdIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmd equals to
        defaultCustomerFiltering("lmd.equals=" + DEFAULT_LMD, "lmd.equals=" + UPDATED_LMD);
    }

    @Test
    @Transactional
    void getAllCustomersByLmdIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmd in
        defaultCustomerFiltering("lmd.in=" + DEFAULT_LMD + "," + UPDATED_LMD, "lmd.in=" + UPDATED_LMD);
    }

    @Test
    @Transactional
    void getAllCustomersByLmdIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lmd is not null
        defaultCustomerFiltering("lmd.specified=true", "lmd.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount equals to
        defaultCustomerFiltering("maximumdiscount.equals=" + DEFAULT_MAXIMUMDISCOUNT, "maximumdiscount.equals=" + UPDATED_MAXIMUMDISCOUNT);
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount in
        defaultCustomerFiltering(
            "maximumdiscount.in=" + DEFAULT_MAXIMUMDISCOUNT + "," + UPDATED_MAXIMUMDISCOUNT,
            "maximumdiscount.in=" + UPDATED_MAXIMUMDISCOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount is not null
        defaultCustomerFiltering("maximumdiscount.specified=true", "maximumdiscount.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount is greater than or equal to
        defaultCustomerFiltering(
            "maximumdiscount.greaterThanOrEqual=" + DEFAULT_MAXIMUMDISCOUNT,
            "maximumdiscount.greaterThanOrEqual=" + UPDATED_MAXIMUMDISCOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount is less than or equal to
        defaultCustomerFiltering(
            "maximumdiscount.lessThanOrEqual=" + DEFAULT_MAXIMUMDISCOUNT,
            "maximumdiscount.lessThanOrEqual=" + SMALLER_MAXIMUMDISCOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount is less than
        defaultCustomerFiltering(
            "maximumdiscount.lessThan=" + UPDATED_MAXIMUMDISCOUNT,
            "maximumdiscount.lessThan=" + DEFAULT_MAXIMUMDISCOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByMaximumdiscountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where maximumdiscount is greater than
        defaultCustomerFiltering(
            "maximumdiscount.greaterThan=" + SMALLER_MAXIMUMDISCOUNT,
            "maximumdiscount.greaterThan=" + DEFAULT_MAXIMUMDISCOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit equals to
        defaultCustomerFiltering("creditlimit.equals=" + DEFAULT_CREDITLIMIT, "creditlimit.equals=" + UPDATED_CREDITLIMIT);
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit in
        defaultCustomerFiltering(
            "creditlimit.in=" + DEFAULT_CREDITLIMIT + "," + UPDATED_CREDITLIMIT,
            "creditlimit.in=" + UPDATED_CREDITLIMIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit is not null
        defaultCustomerFiltering("creditlimit.specified=true", "creditlimit.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit is greater than or equal to
        defaultCustomerFiltering(
            "creditlimit.greaterThanOrEqual=" + DEFAULT_CREDITLIMIT,
            "creditlimit.greaterThanOrEqual=" + UPDATED_CREDITLIMIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit is less than or equal to
        defaultCustomerFiltering(
            "creditlimit.lessThanOrEqual=" + DEFAULT_CREDITLIMIT,
            "creditlimit.lessThanOrEqual=" + SMALLER_CREDITLIMIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit is less than
        defaultCustomerFiltering("creditlimit.lessThan=" + UPDATED_CREDITLIMIT, "creditlimit.lessThan=" + DEFAULT_CREDITLIMIT);
    }

    @Test
    @Transactional
    void getAllCustomersByCreditlimitIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditlimit is greater than
        defaultCustomerFiltering("creditlimit.greaterThan=" + SMALLER_CREDITLIMIT, "creditlimit.greaterThan=" + DEFAULT_CREDITLIMIT);
    }

    @Test
    @Transactional
    void getAllCustomersByHassecuritydepositIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hassecuritydeposit equals to
        defaultCustomerFiltering(
            "hassecuritydeposit.equals=" + DEFAULT_HASSECURITYDEPOSIT,
            "hassecuritydeposit.equals=" + UPDATED_HASSECURITYDEPOSIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHassecuritydepositIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hassecuritydeposit in
        defaultCustomerFiltering(
            "hassecuritydeposit.in=" + DEFAULT_HASSECURITYDEPOSIT + "," + UPDATED_HASSECURITYDEPOSIT,
            "hassecuritydeposit.in=" + UPDATED_HASSECURITYDEPOSIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHassecuritydepositIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hassecuritydeposit is not null
        defaultCustomerFiltering("hassecuritydeposit.specified=true", "hassecuritydeposit.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount equals to
        defaultCustomerFiltering(
            "securitydepositamount.equals=" + DEFAULT_SECURITYDEPOSITAMOUNT,
            "securitydepositamount.equals=" + UPDATED_SECURITYDEPOSITAMOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount in
        defaultCustomerFiltering(
            "securitydepositamount.in=" + DEFAULT_SECURITYDEPOSITAMOUNT + "," + UPDATED_SECURITYDEPOSITAMOUNT,
            "securitydepositamount.in=" + UPDATED_SECURITYDEPOSITAMOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount is not null
        defaultCustomerFiltering("securitydepositamount.specified=true", "securitydepositamount.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount is greater than or equal to
        defaultCustomerFiltering(
            "securitydepositamount.greaterThanOrEqual=" + DEFAULT_SECURITYDEPOSITAMOUNT,
            "securitydepositamount.greaterThanOrEqual=" + UPDATED_SECURITYDEPOSITAMOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount is less than or equal to
        defaultCustomerFiltering(
            "securitydepositamount.lessThanOrEqual=" + DEFAULT_SECURITYDEPOSITAMOUNT,
            "securitydepositamount.lessThanOrEqual=" + SMALLER_SECURITYDEPOSITAMOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount is less than
        defaultCustomerFiltering(
            "securitydepositamount.lessThan=" + UPDATED_SECURITYDEPOSITAMOUNT,
            "securitydepositamount.lessThan=" + DEFAULT_SECURITYDEPOSITAMOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersBySecuritydepositamountIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where securitydepositamount is greater than
        defaultCustomerFiltering(
            "securitydepositamount.greaterThan=" + SMALLER_SECURITYDEPOSITAMOUNT,
            "securitydepositamount.greaterThan=" + DEFAULT_SECURITYDEPOSITAMOUNT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPaybycashIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where paybycash equals to
        defaultCustomerFiltering("paybycash.equals=" + DEFAULT_PAYBYCASH, "paybycash.equals=" + UPDATED_PAYBYCASH);
    }

    @Test
    @Transactional
    void getAllCustomersByPaybycashIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where paybycash in
        defaultCustomerFiltering("paybycash.in=" + DEFAULT_PAYBYCASH + "," + UPDATED_PAYBYCASH, "paybycash.in=" + UPDATED_PAYBYCASH);
    }

    @Test
    @Transactional
    void getAllCustomersByPaybycashIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where paybycash is not null
        defaultCustomerFiltering("paybycash.specified=true", "paybycash.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCashpaymentbeforeloadIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where cashpaymentbeforeload equals to
        defaultCustomerFiltering(
            "cashpaymentbeforeload.equals=" + DEFAULT_CASHPAYMENTBEFORELOAD,
            "cashpaymentbeforeload.equals=" + UPDATED_CASHPAYMENTBEFORELOAD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCashpaymentbeforeloadIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where cashpaymentbeforeload in
        defaultCustomerFiltering(
            "cashpaymentbeforeload.in=" + DEFAULT_CASHPAYMENTBEFORELOAD + "," + UPDATED_CASHPAYMENTBEFORELOAD,
            "cashpaymentbeforeload.in=" + UPDATED_CASHPAYMENTBEFORELOAD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCashpaymentbeforeloadIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where cashpaymentbeforeload is not null
        defaultCustomerFiltering("cashpaymentbeforeload.specified=true", "cashpaymentbeforeload.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCashlastinvoicebeforeloadIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where cashlastinvoicebeforeload equals to
        defaultCustomerFiltering(
            "cashlastinvoicebeforeload.equals=" + DEFAULT_CASHLASTINVOICEBEFORELOAD,
            "cashlastinvoicebeforeload.equals=" + UPDATED_CASHLASTINVOICEBEFORELOAD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCashlastinvoicebeforeloadIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where cashlastinvoicebeforeload in
        defaultCustomerFiltering(
            "cashlastinvoicebeforeload.in=" + DEFAULT_CASHLASTINVOICEBEFORELOAD + "," + UPDATED_CASHLASTINVOICEBEFORELOAD,
            "cashlastinvoicebeforeload.in=" + UPDATED_CASHLASTINVOICEBEFORELOAD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCashlastinvoicebeforeloadIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where cashlastinvoicebeforeload is not null
        defaultCustomerFiltering("cashlastinvoicebeforeload.specified=true", "cashlastinvoicebeforeload.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPaybycreditIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where paybycredit equals to
        defaultCustomerFiltering("paybycredit.equals=" + DEFAULT_PAYBYCREDIT, "paybycredit.equals=" + UPDATED_PAYBYCREDIT);
    }

    @Test
    @Transactional
    void getAllCustomersByPaybycreditIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where paybycredit in
        defaultCustomerFiltering(
            "paybycredit.in=" + DEFAULT_PAYBYCREDIT + "," + UPDATED_PAYBYCREDIT,
            "paybycredit.in=" + UPDATED_PAYBYCREDIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPaybycreditIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where paybycredit is not null
        defaultCustomerFiltering("paybycredit.specified=true", "paybycredit.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCreditoneweekcheckIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditoneweekcheck equals to
        defaultCustomerFiltering(
            "creditoneweekcheck.equals=" + DEFAULT_CREDITONEWEEKCHECK,
            "creditoneweekcheck.equals=" + UPDATED_CREDITONEWEEKCHECK
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditoneweekcheckIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditoneweekcheck in
        defaultCustomerFiltering(
            "creditoneweekcheck.in=" + DEFAULT_CREDITONEWEEKCHECK + "," + UPDATED_CREDITONEWEEKCHECK,
            "creditoneweekcheck.in=" + UPDATED_CREDITONEWEEKCHECK
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditoneweekcheckIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditoneweekcheck is not null
        defaultCustomerFiltering("creditoneweekcheck.specified=true", "creditoneweekcheck.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays equals to
        defaultCustomerFiltering(
            "creditpaymentbydays.equals=" + DEFAULT_CREDITPAYMENTBYDAYS,
            "creditpaymentbydays.equals=" + UPDATED_CREDITPAYMENTBYDAYS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays in
        defaultCustomerFiltering(
            "creditpaymentbydays.in=" + DEFAULT_CREDITPAYMENTBYDAYS + "," + UPDATED_CREDITPAYMENTBYDAYS,
            "creditpaymentbydays.in=" + UPDATED_CREDITPAYMENTBYDAYS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays is not null
        defaultCustomerFiltering("creditpaymentbydays.specified=true", "creditpaymentbydays.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays is greater than or equal to
        defaultCustomerFiltering(
            "creditpaymentbydays.greaterThanOrEqual=" + DEFAULT_CREDITPAYMENTBYDAYS,
            "creditpaymentbydays.greaterThanOrEqual=" + UPDATED_CREDITPAYMENTBYDAYS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays is less than or equal to
        defaultCustomerFiltering(
            "creditpaymentbydays.lessThanOrEqual=" + DEFAULT_CREDITPAYMENTBYDAYS,
            "creditpaymentbydays.lessThanOrEqual=" + SMALLER_CREDITPAYMENTBYDAYS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays is less than
        defaultCustomerFiltering(
            "creditpaymentbydays.lessThan=" + UPDATED_CREDITPAYMENTBYDAYS,
            "creditpaymentbydays.lessThan=" + DEFAULT_CREDITPAYMENTBYDAYS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditpaymentbydaysIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditpaymentbydays is greater than
        defaultCustomerFiltering(
            "creditpaymentbydays.greaterThan=" + SMALLER_CREDITPAYMENTBYDAYS,
            "creditpaymentbydays.greaterThan=" + DEFAULT_CREDITPAYMENTBYDAYS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHaspurchasingdepositIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where haspurchasingdeposit equals to
        defaultCustomerFiltering(
            "haspurchasingdeposit.equals=" + DEFAULT_HASPURCHASINGDEPOSIT,
            "haspurchasingdeposit.equals=" + UPDATED_HASPURCHASINGDEPOSIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHaspurchasingdepositIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where haspurchasingdeposit in
        defaultCustomerFiltering(
            "haspurchasingdeposit.in=" + DEFAULT_HASPURCHASINGDEPOSIT + "," + UPDATED_HASPURCHASINGDEPOSIT,
            "haspurchasingdeposit.in=" + UPDATED_HASPURCHASINGDEPOSIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHaspurchasingdepositIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where haspurchasingdeposit is not null
        defaultCustomerFiltering("haspurchasingdeposit.specified=true", "haspurchasingdeposit.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByHassecuritydepositbondIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hassecuritydepositbond equals to
        defaultCustomerFiltering(
            "hassecuritydepositbond.equals=" + DEFAULT_HASSECURITYDEPOSITBOND,
            "hassecuritydepositbond.equals=" + UPDATED_HASSECURITYDEPOSITBOND
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHassecuritydepositbondIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hassecuritydepositbond in
        defaultCustomerFiltering(
            "hassecuritydepositbond.in=" + DEFAULT_HASSECURITYDEPOSITBOND + "," + UPDATED_HASSECURITYDEPOSITBOND,
            "hassecuritydepositbond.in=" + UPDATED_HASSECURITYDEPOSITBOND
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHassecuritydepositbondIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hassecuritydepositbond is not null
        defaultCustomerFiltering("hassecuritydepositbond.specified=true", "hassecuritydepositbond.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByHasassestsdepositIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hasassestsdeposit equals to
        defaultCustomerFiltering(
            "hasassestsdeposit.equals=" + DEFAULT_HASASSESTSDEPOSIT,
            "hasassestsdeposit.equals=" + UPDATED_HASASSESTSDEPOSIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHasassestsdepositIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hasassestsdeposit in
        defaultCustomerFiltering(
            "hasassestsdeposit.in=" + DEFAULT_HASASSESTSDEPOSIT + "," + UPDATED_HASASSESTSDEPOSIT,
            "hasassestsdeposit.in=" + UPDATED_HASASSESTSDEPOSIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHasassestsdepositIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hasassestsdeposit is not null
        defaultCustomerFiltering("hasassestsdeposit.specified=true", "hasassestsdeposit.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCustomerrootmappathIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customerrootmappath equals to
        defaultCustomerFiltering(
            "customerrootmappath.equals=" + DEFAULT_CUSTOMERROOTMAPPATH,
            "customerrootmappath.equals=" + UPDATED_CUSTOMERROOTMAPPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCustomerrootmappathIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customerrootmappath in
        defaultCustomerFiltering(
            "customerrootmappath.in=" + DEFAULT_CUSTOMERROOTMAPPATH + "," + UPDATED_CUSTOMERROOTMAPPATH,
            "customerrootmappath.in=" + UPDATED_CUSTOMERROOTMAPPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCustomerrootmappathIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customerrootmappath is not null
        defaultCustomerFiltering("customerrootmappath.specified=true", "customerrootmappath.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCustomerrootmappathContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customerrootmappath contains
        defaultCustomerFiltering(
            "customerrootmappath.contains=" + DEFAULT_CUSTOMERROOTMAPPATH,
            "customerrootmappath.contains=" + UPDATED_CUSTOMERROOTMAPPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCustomerrootmappathNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where customerrootmappath does not contain
        defaultCustomerFiltering(
            "customerrootmappath.doesNotContain=" + UPDATED_CUSTOMERROOTMAPPATH,
            "customerrootmappath.doesNotContain=" + DEFAULT_CUSTOMERROOTMAPPATH
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployernameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employername equals to
        defaultCustomerFiltering("employername.equals=" + DEFAULT_EMPLOYERNAME, "employername.equals=" + UPDATED_EMPLOYERNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByEmployernameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employername in
        defaultCustomerFiltering(
            "employername.in=" + DEFAULT_EMPLOYERNAME + "," + UPDATED_EMPLOYERNAME,
            "employername.in=" + UPDATED_EMPLOYERNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployernameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employername is not null
        defaultCustomerFiltering("employername.specified=true", "employername.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByEmployernameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employername contains
        defaultCustomerFiltering("employername.contains=" + DEFAULT_EMPLOYERNAME, "employername.contains=" + UPDATED_EMPLOYERNAME);
    }

    @Test
    @Transactional
    void getAllCustomersByEmployernameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employername does not contain
        defaultCustomerFiltering(
            "employername.doesNotContain=" + UPDATED_EMPLOYERNAME,
            "employername.doesNotContain=" + DEFAULT_EMPLOYERNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployeraddressIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employeraddress equals to
        defaultCustomerFiltering("employeraddress.equals=" + DEFAULT_EMPLOYERADDRESS, "employeraddress.equals=" + UPDATED_EMPLOYERADDRESS);
    }

    @Test
    @Transactional
    void getAllCustomersByEmployeraddressIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employeraddress in
        defaultCustomerFiltering(
            "employeraddress.in=" + DEFAULT_EMPLOYERADDRESS + "," + UPDATED_EMPLOYERADDRESS,
            "employeraddress.in=" + UPDATED_EMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployeraddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employeraddress is not null
        defaultCustomerFiltering("employeraddress.specified=true", "employeraddress.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByEmployeraddressContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employeraddress contains
        defaultCustomerFiltering(
            "employeraddress.contains=" + DEFAULT_EMPLOYERADDRESS,
            "employeraddress.contains=" + UPDATED_EMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployeraddressNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employeraddress does not contain
        defaultCustomerFiltering(
            "employeraddress.doesNotContain=" + UPDATED_EMPLOYERADDRESS,
            "employeraddress.doesNotContain=" + DEFAULT_EMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerphoneIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerphone equals to
        defaultCustomerFiltering("employerphone.equals=" + DEFAULT_EMPLOYERPHONE, "employerphone.equals=" + UPDATED_EMPLOYERPHONE);
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerphoneIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerphone in
        defaultCustomerFiltering(
            "employerphone.in=" + DEFAULT_EMPLOYERPHONE + "," + UPDATED_EMPLOYERPHONE,
            "employerphone.in=" + UPDATED_EMPLOYERPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerphoneIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerphone is not null
        defaultCustomerFiltering("employerphone.specified=true", "employerphone.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerphoneContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerphone contains
        defaultCustomerFiltering("employerphone.contains=" + DEFAULT_EMPLOYERPHONE, "employerphone.contains=" + UPDATED_EMPLOYERPHONE);
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerphoneNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerphone does not contain
        defaultCustomerFiltering(
            "employerphone.doesNotContain=" + UPDATED_EMPLOYERPHONE,
            "employerphone.doesNotContain=" + DEFAULT_EMPLOYERPHONE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerdesignationIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerdesignation equals to
        defaultCustomerFiltering(
            "employerdesignation.equals=" + DEFAULT_EMPLOYERDESIGNATION,
            "employerdesignation.equals=" + UPDATED_EMPLOYERDESIGNATION
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerdesignationIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerdesignation in
        defaultCustomerFiltering(
            "employerdesignation.in=" + DEFAULT_EMPLOYERDESIGNATION + "," + UPDATED_EMPLOYERDESIGNATION,
            "employerdesignation.in=" + UPDATED_EMPLOYERDESIGNATION
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerdesignationIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerdesignation is not null
        defaultCustomerFiltering("employerdesignation.specified=true", "employerdesignation.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerdesignationContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerdesignation contains
        defaultCustomerFiltering(
            "employerdesignation.contains=" + DEFAULT_EMPLOYERDESIGNATION,
            "employerdesignation.contains=" + UPDATED_EMPLOYERDESIGNATION
        );
    }

    @Test
    @Transactional
    void getAllCustomersByEmployerdesignationNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where employerdesignation does not contain
        defaultCustomerFiltering(
            "employerdesignation.doesNotContain=" + UPDATED_EMPLOYERDESIGNATION,
            "employerdesignation.doesNotContain=" + DEFAULT_EMPLOYERDESIGNATION
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployernameIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployername equals to
        defaultCustomerFiltering(
            "previousemployername.equals=" + DEFAULT_PREVIOUSEMPLOYERNAME,
            "previousemployername.equals=" + UPDATED_PREVIOUSEMPLOYERNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployernameIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployername in
        defaultCustomerFiltering(
            "previousemployername.in=" + DEFAULT_PREVIOUSEMPLOYERNAME + "," + UPDATED_PREVIOUSEMPLOYERNAME,
            "previousemployername.in=" + UPDATED_PREVIOUSEMPLOYERNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployernameIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployername is not null
        defaultCustomerFiltering("previousemployername.specified=true", "previousemployername.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployernameContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployername contains
        defaultCustomerFiltering(
            "previousemployername.contains=" + DEFAULT_PREVIOUSEMPLOYERNAME,
            "previousemployername.contains=" + UPDATED_PREVIOUSEMPLOYERNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployernameNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployername does not contain
        defaultCustomerFiltering(
            "previousemployername.doesNotContain=" + UPDATED_PREVIOUSEMPLOYERNAME,
            "previousemployername.doesNotContain=" + DEFAULT_PREVIOUSEMPLOYERNAME
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployeraddressIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployeraddress equals to
        defaultCustomerFiltering(
            "previousemployeraddress.equals=" + DEFAULT_PREVIOUSEMPLOYERADDRESS,
            "previousemployeraddress.equals=" + UPDATED_PREVIOUSEMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployeraddressIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployeraddress in
        defaultCustomerFiltering(
            "previousemployeraddress.in=" + DEFAULT_PREVIOUSEMPLOYERADDRESS + "," + UPDATED_PREVIOUSEMPLOYERADDRESS,
            "previousemployeraddress.in=" + UPDATED_PREVIOUSEMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployeraddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployeraddress is not null
        defaultCustomerFiltering("previousemployeraddress.specified=true", "previousemployeraddress.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployeraddressContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployeraddress contains
        defaultCustomerFiltering(
            "previousemployeraddress.contains=" + DEFAULT_PREVIOUSEMPLOYERADDRESS,
            "previousemployeraddress.contains=" + UPDATED_PREVIOUSEMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousemployeraddressNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousemployeraddress does not contain
        defaultCustomerFiltering(
            "previousemployeraddress.doesNotContain=" + UPDATED_PREVIOUSEMPLOYERADDRESS,
            "previousemployeraddress.doesNotContain=" + DEFAULT_PREVIOUSEMPLOYERADDRESS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousindustryIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousindustry equals to
        defaultCustomerFiltering(
            "previousindustry.equals=" + DEFAULT_PREVIOUSINDUSTRY,
            "previousindustry.equals=" + UPDATED_PREVIOUSINDUSTRY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousindustryIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousindustry in
        defaultCustomerFiltering(
            "previousindustry.in=" + DEFAULT_PREVIOUSINDUSTRY + "," + UPDATED_PREVIOUSINDUSTRY,
            "previousindustry.in=" + UPDATED_PREVIOUSINDUSTRY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousindustryIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousindustry is not null
        defaultCustomerFiltering("previousindustry.specified=true", "previousindustry.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousindustryContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousindustry contains
        defaultCustomerFiltering(
            "previousindustry.contains=" + DEFAULT_PREVIOUSINDUSTRY,
            "previousindustry.contains=" + UPDATED_PREVIOUSINDUSTRY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousindustryNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousindustry does not contain
        defaultCustomerFiltering(
            "previousindustry.doesNotContain=" + UPDATED_PREVIOUSINDUSTRY,
            "previousindustry.doesNotContain=" + DEFAULT_PREVIOUSINDUSTRY
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousperiodIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousperiod equals to
        defaultCustomerFiltering("previousperiod.equals=" + DEFAULT_PREVIOUSPERIOD, "previousperiod.equals=" + UPDATED_PREVIOUSPERIOD);
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousperiodIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousperiod in
        defaultCustomerFiltering(
            "previousperiod.in=" + DEFAULT_PREVIOUSPERIOD + "," + UPDATED_PREVIOUSPERIOD,
            "previousperiod.in=" + UPDATED_PREVIOUSPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousperiodIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousperiod is not null
        defaultCustomerFiltering("previousperiod.specified=true", "previousperiod.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousperiodContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousperiod contains
        defaultCustomerFiltering("previousperiod.contains=" + DEFAULT_PREVIOUSPERIOD, "previousperiod.contains=" + UPDATED_PREVIOUSPERIOD);
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousperiodNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousperiod does not contain
        defaultCustomerFiltering(
            "previousperiod.doesNotContain=" + UPDATED_PREVIOUSPERIOD,
            "previousperiod.doesNotContain=" + DEFAULT_PREVIOUSPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviouspositionsIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previouspositions equals to
        defaultCustomerFiltering(
            "previouspositions.equals=" + DEFAULT_PREVIOUSPOSITIONS,
            "previouspositions.equals=" + UPDATED_PREVIOUSPOSITIONS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviouspositionsIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previouspositions in
        defaultCustomerFiltering(
            "previouspositions.in=" + DEFAULT_PREVIOUSPOSITIONS + "," + UPDATED_PREVIOUSPOSITIONS,
            "previouspositions.in=" + UPDATED_PREVIOUSPOSITIONS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviouspositionsIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previouspositions is not null
        defaultCustomerFiltering("previouspositions.specified=true", "previouspositions.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPreviouspositionsContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previouspositions contains
        defaultCustomerFiltering(
            "previouspositions.contains=" + DEFAULT_PREVIOUSPOSITIONS,
            "previouspositions.contains=" + UPDATED_PREVIOUSPOSITIONS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviouspositionsNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previouspositions does not contain
        defaultCustomerFiltering(
            "previouspositions.doesNotContain=" + UPDATED_PREVIOUSPOSITIONS,
            "previouspositions.doesNotContain=" + DEFAULT_PREVIOUSPOSITIONS
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousresionforleavingIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousresionforleaving equals to
        defaultCustomerFiltering(
            "previousresionforleaving.equals=" + DEFAULT_PREVIOUSRESIONFORLEAVING,
            "previousresionforleaving.equals=" + UPDATED_PREVIOUSRESIONFORLEAVING
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousresionforleavingIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousresionforleaving in
        defaultCustomerFiltering(
            "previousresionforleaving.in=" + DEFAULT_PREVIOUSRESIONFORLEAVING + "," + UPDATED_PREVIOUSRESIONFORLEAVING,
            "previousresionforleaving.in=" + UPDATED_PREVIOUSRESIONFORLEAVING
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousresionforleavingIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousresionforleaving is not null
        defaultCustomerFiltering("previousresionforleaving.specified=true", "previousresionforleaving.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousresionforleavingContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousresionforleaving contains
        defaultCustomerFiltering(
            "previousresionforleaving.contains=" + DEFAULT_PREVIOUSRESIONFORLEAVING,
            "previousresionforleaving.contains=" + UPDATED_PREVIOUSRESIONFORLEAVING
        );
    }

    @Test
    @Transactional
    void getAllCustomersByPreviousresionforleavingNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where previousresionforleaving does not contain
        defaultCustomerFiltering(
            "previousresionforleaving.doesNotContain=" + UPDATED_PREVIOUSRESIONFORLEAVING,
            "previousresionforleaving.doesNotContain=" + DEFAULT_PREVIOUSRESIONFORLEAVING
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHascreaditlimitIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hascreaditlimit equals to
        defaultCustomerFiltering("hascreaditlimit.equals=" + DEFAULT_HASCREADITLIMIT, "hascreaditlimit.equals=" + UPDATED_HASCREADITLIMIT);
    }

    @Test
    @Transactional
    void getAllCustomersByHascreaditlimitIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hascreaditlimit in
        defaultCustomerFiltering(
            "hascreaditlimit.in=" + DEFAULT_HASCREADITLIMIT + "," + UPDATED_HASCREADITLIMIT,
            "hascreaditlimit.in=" + UPDATED_HASCREADITLIMIT
        );
    }

    @Test
    @Transactional
    void getAllCustomersByHascreaditlimitIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where hascreaditlimit is not null
        defaultCustomerFiltering("hascreaditlimit.specified=true", "hascreaditlimit.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid equals to
        defaultCustomerFiltering("accountid.equals=" + DEFAULT_ACCOUNTID, "accountid.equals=" + UPDATED_ACCOUNTID);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid in
        defaultCustomerFiltering("accountid.in=" + DEFAULT_ACCOUNTID + "," + UPDATED_ACCOUNTID, "accountid.in=" + UPDATED_ACCOUNTID);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid is not null
        defaultCustomerFiltering("accountid.specified=true", "accountid.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid is greater than or equal to
        defaultCustomerFiltering("accountid.greaterThanOrEqual=" + DEFAULT_ACCOUNTID, "accountid.greaterThanOrEqual=" + UPDATED_ACCOUNTID);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid is less than or equal to
        defaultCustomerFiltering("accountid.lessThanOrEqual=" + DEFAULT_ACCOUNTID, "accountid.lessThanOrEqual=" + SMALLER_ACCOUNTID);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid is less than
        defaultCustomerFiltering("accountid.lessThan=" + UPDATED_ACCOUNTID, "accountid.lessThan=" + DEFAULT_ACCOUNTID);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountidIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountid is greater than
        defaultCustomerFiltering("accountid.greaterThan=" + SMALLER_ACCOUNTID, "accountid.greaterThan=" + DEFAULT_ACCOUNTID);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountcodeIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountcode equals to
        defaultCustomerFiltering("accountcode.equals=" + DEFAULT_ACCOUNTCODE, "accountcode.equals=" + UPDATED_ACCOUNTCODE);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountcodeIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountcode in
        defaultCustomerFiltering(
            "accountcode.in=" + DEFAULT_ACCOUNTCODE + "," + UPDATED_ACCOUNTCODE,
            "accountcode.in=" + UPDATED_ACCOUNTCODE
        );
    }

    @Test
    @Transactional
    void getAllCustomersByAccountcodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountcode is not null
        defaultCustomerFiltering("accountcode.specified=true", "accountcode.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByAccountcodeContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountcode contains
        defaultCustomerFiltering("accountcode.contains=" + DEFAULT_ACCOUNTCODE, "accountcode.contains=" + UPDATED_ACCOUNTCODE);
    }

    @Test
    @Transactional
    void getAllCustomersByAccountcodeNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where accountcode does not contain
        defaultCustomerFiltering("accountcode.doesNotContain=" + UPDATED_ACCOUNTCODE, "accountcode.doesNotContain=" + DEFAULT_ACCOUNTCODE);
    }

    @Test
    @Transactional
    void getAllCustomersByIsregisteredIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isregistered equals to
        defaultCustomerFiltering("isregistered.equals=" + DEFAULT_ISREGISTERED, "isregistered.equals=" + UPDATED_ISREGISTERED);
    }

    @Test
    @Transactional
    void getAllCustomersByIsregisteredIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isregistered in
        defaultCustomerFiltering(
            "isregistered.in=" + DEFAULT_ISREGISTERED + "," + UPDATED_ISREGISTERED,
            "isregistered.in=" + UPDATED_ISREGISTERED
        );
    }

    @Test
    @Transactional
    void getAllCustomersByIsregisteredIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where isregistered is not null
        defaultCustomerFiltering("isregistered.specified=true", "isregistered.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByVatregnumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where vatregnumber equals to
        defaultCustomerFiltering("vatregnumber.equals=" + DEFAULT_VATREGNUMBER, "vatregnumber.equals=" + UPDATED_VATREGNUMBER);
    }

    @Test
    @Transactional
    void getAllCustomersByVatregnumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where vatregnumber in
        defaultCustomerFiltering(
            "vatregnumber.in=" + DEFAULT_VATREGNUMBER + "," + UPDATED_VATREGNUMBER,
            "vatregnumber.in=" + UPDATED_VATREGNUMBER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByVatregnumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where vatregnumber is not null
        defaultCustomerFiltering("vatregnumber.specified=true", "vatregnumber.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByVatregnumberContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where vatregnumber contains
        defaultCustomerFiltering("vatregnumber.contains=" + DEFAULT_VATREGNUMBER, "vatregnumber.contains=" + UPDATED_VATREGNUMBER);
    }

    @Test
    @Transactional
    void getAllCustomersByVatregnumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where vatregnumber does not contain
        defaultCustomerFiltering(
            "vatregnumber.doesNotContain=" + UPDATED_VATREGNUMBER,
            "vatregnumber.doesNotContain=" + DEFAULT_VATREGNUMBER
        );
    }

    @Test
    @Transactional
    void getAllCustomersByTinnumberIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where tinnumber equals to
        defaultCustomerFiltering("tinnumber.equals=" + DEFAULT_TINNUMBER, "tinnumber.equals=" + UPDATED_TINNUMBER);
    }

    @Test
    @Transactional
    void getAllCustomersByTinnumberIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where tinnumber in
        defaultCustomerFiltering("tinnumber.in=" + DEFAULT_TINNUMBER + "," + UPDATED_TINNUMBER, "tinnumber.in=" + UPDATED_TINNUMBER);
    }

    @Test
    @Transactional
    void getAllCustomersByTinnumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where tinnumber is not null
        defaultCustomerFiltering("tinnumber.specified=true", "tinnumber.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByTinnumberContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where tinnumber contains
        defaultCustomerFiltering("tinnumber.contains=" + DEFAULT_TINNUMBER, "tinnumber.contains=" + UPDATED_TINNUMBER);
    }

    @Test
    @Transactional
    void getAllCustomersByTinnumberNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where tinnumber does not contain
        defaultCustomerFiltering("tinnumber.doesNotContain=" + UPDATED_TINNUMBER, "tinnumber.doesNotContain=" + DEFAULT_TINNUMBER);
    }

    @Test
    @Transactional
    void getAllCustomersByLatIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lat equals to
        defaultCustomerFiltering("lat.equals=" + DEFAULT_LAT, "lat.equals=" + UPDATED_LAT);
    }

    @Test
    @Transactional
    void getAllCustomersByLatIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lat in
        defaultCustomerFiltering("lat.in=" + DEFAULT_LAT + "," + UPDATED_LAT, "lat.in=" + UPDATED_LAT);
    }

    @Test
    @Transactional
    void getAllCustomersByLatIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lat is not null
        defaultCustomerFiltering("lat.specified=true", "lat.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByLatContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lat contains
        defaultCustomerFiltering("lat.contains=" + DEFAULT_LAT, "lat.contains=" + UPDATED_LAT);
    }

    @Test
    @Transactional
    void getAllCustomersByLatNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lat does not contain
        defaultCustomerFiltering("lat.doesNotContain=" + UPDATED_LAT, "lat.doesNotContain=" + DEFAULT_LAT);
    }

    @Test
    @Transactional
    void getAllCustomersByLonIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lon equals to
        defaultCustomerFiltering("lon.equals=" + DEFAULT_LON, "lon.equals=" + UPDATED_LON);
    }

    @Test
    @Transactional
    void getAllCustomersByLonIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lon in
        defaultCustomerFiltering("lon.in=" + DEFAULT_LON + "," + UPDATED_LON, "lon.in=" + UPDATED_LON);
    }

    @Test
    @Transactional
    void getAllCustomersByLonIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lon is not null
        defaultCustomerFiltering("lon.specified=true", "lon.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByLonContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lon contains
        defaultCustomerFiltering("lon.contains=" + DEFAULT_LON, "lon.contains=" + UPDATED_LON);
    }

    @Test
    @Transactional
    void getAllCustomersByLonNotContainsSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where lon does not contain
        defaultCustomerFiltering("lon.doesNotContain=" + UPDATED_LON, "lon.doesNotContain=" + DEFAULT_LON);
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod equals to
        defaultCustomerFiltering("creditperiod.equals=" + DEFAULT_CREDITPERIOD, "creditperiod.equals=" + UPDATED_CREDITPERIOD);
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsInShouldWork() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod in
        defaultCustomerFiltering(
            "creditperiod.in=" + DEFAULT_CREDITPERIOD + "," + UPDATED_CREDITPERIOD,
            "creditperiod.in=" + UPDATED_CREDITPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsNullOrNotNull() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod is not null
        defaultCustomerFiltering("creditperiod.specified=true", "creditperiod.specified=false");
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod is greater than or equal to
        defaultCustomerFiltering(
            "creditperiod.greaterThanOrEqual=" + DEFAULT_CREDITPERIOD,
            "creditperiod.greaterThanOrEqual=" + UPDATED_CREDITPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod is less than or equal to
        defaultCustomerFiltering(
            "creditperiod.lessThanOrEqual=" + DEFAULT_CREDITPERIOD,
            "creditperiod.lessThanOrEqual=" + SMALLER_CREDITPERIOD
        );
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsLessThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod is less than
        defaultCustomerFiltering("creditperiod.lessThan=" + UPDATED_CREDITPERIOD, "creditperiod.lessThan=" + DEFAULT_CREDITPERIOD);
    }

    @Test
    @Transactional
    void getAllCustomersByCreditperiodIsGreaterThanSomething() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        // Get all the customerList where creditperiod is greater than
        defaultCustomerFiltering("creditperiod.greaterThan=" + SMALLER_CREDITPERIOD, "creditperiod.greaterThan=" + DEFAULT_CREDITPERIOD);
    }

    private void defaultCustomerFiltering(String shouldBeFound, String shouldNotBeFound) throws Exception {
        defaultCustomerShouldBeFound(shouldBeFound);
        defaultCustomerShouldNotBeFound(shouldNotBeFound);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCustomerShouldBeFound(String filter) throws Exception {
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customer.getId().intValue())))
            .andExpect(jsonPath("$.[*].customertype").value(hasItem(DEFAULT_CUSTOMERTYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].namewithinitials").value(hasItem(DEFAULT_NAMEWITHINITIALS)))
            .andExpect(jsonPath("$.[*].fullname").value(hasItem(DEFAULT_FULLNAME)))
            .andExpect(jsonPath("$.[*].callingname").value(hasItem(DEFAULT_CALLINGNAME)))
            .andExpect(jsonPath("$.[*].nicno").value(hasItem(DEFAULT_NICNO)))
            .andExpect(jsonPath("$.[*].nicissueddate").value(hasItem(DEFAULT_NICISSUEDDATE.toString())))
            .andExpect(jsonPath("$.[*].dateofbirth").value(hasItem(DEFAULT_DATEOFBIRTH.toString())))
            .andExpect(jsonPath("$.[*].bloodgroup").value(hasItem(DEFAULT_BLOODGROUP)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].maritalstatus").value(hasItem(DEFAULT_MARITALSTATUS)))
            .andExpect(jsonPath("$.[*].marrieddate").value(hasItem(DEFAULT_MARRIEDDATE.toString())))
            .andExpect(jsonPath("$.[*].nationality").value(hasItem(DEFAULT_NATIONALITY)))
            .andExpect(jsonPath("$.[*].territory").value(hasItem(DEFAULT_TERRITORY)))
            .andExpect(jsonPath("$.[*].religion").value(hasItem(DEFAULT_RELIGION)))
            .andExpect(jsonPath("$.[*].team").value(hasItem(DEFAULT_TEAM)))
            .andExpect(jsonPath("$.[*].businessname").value(hasItem(DEFAULT_BUSINESSNAME)))
            .andExpect(jsonPath("$.[*].businessregdate").value(hasItem(DEFAULT_BUSINESSREGDATE.toString())))
            .andExpect(jsonPath("$.[*].businessregno").value(hasItem(DEFAULT_BUSINESSREGNO)))
            .andExpect(jsonPath("$.[*].profilepicturepath").value(hasItem(DEFAULT_PROFILEPICTUREPATH)))
            .andExpect(jsonPath("$.[*].residencehouseno").value(hasItem(DEFAULT_RESIDENCEHOUSENO)))
            .andExpect(jsonPath("$.[*].residenceaddress").value(hasItem(DEFAULT_RESIDENCEADDRESS)))
            .andExpect(jsonPath("$.[*].residencecity").value(hasItem(DEFAULT_RESIDENCECITY)))
            .andExpect(jsonPath("$.[*].residencephone").value(hasItem(DEFAULT_RESIDENCEPHONE)))
            .andExpect(jsonPath("$.[*].businesslocationno").value(hasItem(DEFAULT_BUSINESSLOCATIONNO)))
            .andExpect(jsonPath("$.[*].businessaddress").value(hasItem(DEFAULT_BUSINESSADDRESS)))
            .andExpect(jsonPath("$.[*].businesscity").value(hasItem(DEFAULT_BUSINESSCITY)))
            .andExpect(jsonPath("$.[*].businessphone1").value(hasItem(DEFAULT_BUSINESSPHONE_1)))
            .andExpect(jsonPath("$.[*].businessphone2").value(hasItem(DEFAULT_BUSINESSPHONE_2)))
            .andExpect(jsonPath("$.[*].businessmobile").value(hasItem(DEFAULT_BUSINESSMOBILE)))
            .andExpect(jsonPath("$.[*].businessfax").value(hasItem(DEFAULT_BUSINESSFAX)))
            .andExpect(jsonPath("$.[*].businessemail").value(hasItem(DEFAULT_BUSINESSEMAIL)))
            .andExpect(jsonPath("$.[*].businessprovinceid").value(hasItem(DEFAULT_BUSINESSPROVINCEID)))
            .andExpect(jsonPath("$.[*].businessdistrictid").value(hasItem(DEFAULT_BUSINESSDISTRICTID)))
            .andExpect(jsonPath("$.[*].contactpersonname").value(hasItem(DEFAULT_CONTACTPERSONNAME)))
            .andExpect(jsonPath("$.[*].contactpersonphone").value(hasItem(DEFAULT_CONTACTPERSONPHONE)))
            .andExpect(jsonPath("$.[*].contactpersonmobile").value(hasItem(DEFAULT_CONTACTPERSONMOBILE)))
            .andExpect(jsonPath("$.[*].contactpersonemail").value(hasItem(DEFAULT_CONTACTPERSONEMAIL)))
            .andExpect(jsonPath("$.[*].rootmappath").value(hasItem(DEFAULT_ROOTMAPPATH)))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE)))
            .andExpect(jsonPath("$.[*].registrationdate").value(hasItem(DEFAULT_REGISTRATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE)))
            .andExpect(jsonPath("$.[*].isinternalcustomer").value(hasItem(DEFAULT_ISINTERNALCUSTOMER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].maximumdiscount").value(hasItem(DEFAULT_MAXIMUMDISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].creditlimit").value(hasItem(DEFAULT_CREDITLIMIT.doubleValue())))
            .andExpect(jsonPath("$.[*].hassecuritydeposit").value(hasItem(DEFAULT_HASSECURITYDEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].securitydepositamount").value(hasItem(DEFAULT_SECURITYDEPOSITAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].paybycash").value(hasItem(DEFAULT_PAYBYCASH.booleanValue())))
            .andExpect(jsonPath("$.[*].cashpaymentbeforeload").value(hasItem(DEFAULT_CASHPAYMENTBEFORELOAD.booleanValue())))
            .andExpect(jsonPath("$.[*].cashlastinvoicebeforeload").value(hasItem(DEFAULT_CASHLASTINVOICEBEFORELOAD.booleanValue())))
            .andExpect(jsonPath("$.[*].paybycredit").value(hasItem(DEFAULT_PAYBYCREDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].creditoneweekcheck").value(hasItem(DEFAULT_CREDITONEWEEKCHECK.booleanValue())))
            .andExpect(jsonPath("$.[*].creditpaymentbydays").value(hasItem(DEFAULT_CREDITPAYMENTBYDAYS)))
            .andExpect(jsonPath("$.[*].haspurchasingdeposit").value(hasItem(DEFAULT_HASPURCHASINGDEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].hassecuritydepositbond").value(hasItem(DEFAULT_HASSECURITYDEPOSITBOND.booleanValue())))
            .andExpect(jsonPath("$.[*].hasassestsdeposit").value(hasItem(DEFAULT_HASASSESTSDEPOSIT.booleanValue())))
            .andExpect(jsonPath("$.[*].customerrootmappath").value(hasItem(DEFAULT_CUSTOMERROOTMAPPATH)))
            .andExpect(jsonPath("$.[*].employername").value(hasItem(DEFAULT_EMPLOYERNAME)))
            .andExpect(jsonPath("$.[*].employeraddress").value(hasItem(DEFAULT_EMPLOYERADDRESS)))
            .andExpect(jsonPath("$.[*].employerphone").value(hasItem(DEFAULT_EMPLOYERPHONE)))
            .andExpect(jsonPath("$.[*].employerdesignation").value(hasItem(DEFAULT_EMPLOYERDESIGNATION)))
            .andExpect(jsonPath("$.[*].previousemployername").value(hasItem(DEFAULT_PREVIOUSEMPLOYERNAME)))
            .andExpect(jsonPath("$.[*].previousemployeraddress").value(hasItem(DEFAULT_PREVIOUSEMPLOYERADDRESS)))
            .andExpect(jsonPath("$.[*].previousindustry").value(hasItem(DEFAULT_PREVIOUSINDUSTRY)))
            .andExpect(jsonPath("$.[*].previousperiod").value(hasItem(DEFAULT_PREVIOUSPERIOD)))
            .andExpect(jsonPath("$.[*].previouspositions").value(hasItem(DEFAULT_PREVIOUSPOSITIONS)))
            .andExpect(jsonPath("$.[*].previousresionforleaving").value(hasItem(DEFAULT_PREVIOUSRESIONFORLEAVING)))
            .andExpect(jsonPath("$.[*].hascreaditlimit").value(hasItem(DEFAULT_HASCREADITLIMIT.booleanValue())))
            .andExpect(jsonPath("$.[*].accountid").value(hasItem(DEFAULT_ACCOUNTID)))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE)))
            .andExpect(jsonPath("$.[*].isregistered").value(hasItem(DEFAULT_ISREGISTERED.booleanValue())))
            .andExpect(jsonPath("$.[*].vatregnumber").value(hasItem(DEFAULT_VATREGNUMBER)))
            .andExpect(jsonPath("$.[*].tinnumber").value(hasItem(DEFAULT_TINNUMBER)))
            .andExpect(jsonPath("$.[*].lat").value(hasItem(DEFAULT_LAT)))
            .andExpect(jsonPath("$.[*].lon").value(hasItem(DEFAULT_LON)))
            .andExpect(jsonPath("$.[*].creditperiod").value(hasItem(DEFAULT_CREDITPERIOD)));

        // Check, that the count call also returns 1
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCustomerShouldNotBeFound(String filter) throws Exception {
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCustomerMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCustomer() throws Exception {
        // Get the customer
        restCustomerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCustomer() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customer
        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCustomer are not directly saved in db
        em.detach(updatedCustomer);
        updatedCustomer
            .customertype(UPDATED_CUSTOMERTYPE)
            .code(UPDATED_CODE)
            .title(UPDATED_TITLE)
            .namewithinitials(UPDATED_NAMEWITHINITIALS)
            .fullname(UPDATED_FULLNAME)
            .callingname(UPDATED_CALLINGNAME)
            .nicno(UPDATED_NICNO)
            .nicissueddate(UPDATED_NICISSUEDDATE)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .bloodgroup(UPDATED_BLOODGROUP)
            .gender(UPDATED_GENDER)
            .maritalstatus(UPDATED_MARITALSTATUS)
            .marrieddate(UPDATED_MARRIEDDATE)
            .nationality(UPDATED_NATIONALITY)
            .territory(UPDATED_TERRITORY)
            .religion(UPDATED_RELIGION)
            .team(UPDATED_TEAM)
            .businessname(UPDATED_BUSINESSNAME)
            .businessregdate(UPDATED_BUSINESSREGDATE)
            .businessregno(UPDATED_BUSINESSREGNO)
            .profilepicturepath(UPDATED_PROFILEPICTUREPATH)
            .residencehouseno(UPDATED_RESIDENCEHOUSENO)
            .residenceaddress(UPDATED_RESIDENCEADDRESS)
            .residencecity(UPDATED_RESIDENCECITY)
            .residencephone(UPDATED_RESIDENCEPHONE)
            .businesslocationno(UPDATED_BUSINESSLOCATIONNO)
            .businessaddress(UPDATED_BUSINESSADDRESS)
            .businesscity(UPDATED_BUSINESSCITY)
            .businessphone1(UPDATED_BUSINESSPHONE_1)
            .businessphone2(UPDATED_BUSINESSPHONE_2)
            .businessmobile(UPDATED_BUSINESSMOBILE)
            .businessfax(UPDATED_BUSINESSFAX)
            .businessemail(UPDATED_BUSINESSEMAIL)
            .businessprovinceid(UPDATED_BUSINESSPROVINCEID)
            .businessdistrictid(UPDATED_BUSINESSDISTRICTID)
            .contactpersonname(UPDATED_CONTACTPERSONNAME)
            .contactpersonphone(UPDATED_CONTACTPERSONPHONE)
            .contactpersonmobile(UPDATED_CONTACTPERSONMOBILE)
            .contactpersonemail(UPDATED_CONTACTPERSONEMAIL)
            .rootmappath(UPDATED_ROOTMAPPATH)
            .website(UPDATED_WEBSITE)
            .registrationdate(UPDATED_REGISTRATIONDATE)
            .isactive(UPDATED_ISACTIVE)
            .isinternalcustomer(UPDATED_ISINTERNALCUSTOMER)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .maximumdiscount(UPDATED_MAXIMUMDISCOUNT)
            .creditlimit(UPDATED_CREDITLIMIT)
            .hassecuritydeposit(UPDATED_HASSECURITYDEPOSIT)
            .securitydepositamount(UPDATED_SECURITYDEPOSITAMOUNT)
            .paybycash(UPDATED_PAYBYCASH)
            .cashpaymentbeforeload(UPDATED_CASHPAYMENTBEFORELOAD)
            .cashlastinvoicebeforeload(UPDATED_CASHLASTINVOICEBEFORELOAD)
            .paybycredit(UPDATED_PAYBYCREDIT)
            .creditoneweekcheck(UPDATED_CREDITONEWEEKCHECK)
            .creditpaymentbydays(UPDATED_CREDITPAYMENTBYDAYS)
            .haspurchasingdeposit(UPDATED_HASPURCHASINGDEPOSIT)
            .hassecuritydepositbond(UPDATED_HASSECURITYDEPOSITBOND)
            .hasassestsdeposit(UPDATED_HASASSESTSDEPOSIT)
            .customerrootmappath(UPDATED_CUSTOMERROOTMAPPATH)
            .employername(UPDATED_EMPLOYERNAME)
            .employeraddress(UPDATED_EMPLOYERADDRESS)
            .employerphone(UPDATED_EMPLOYERPHONE)
            .employerdesignation(UPDATED_EMPLOYERDESIGNATION)
            .previousemployername(UPDATED_PREVIOUSEMPLOYERNAME)
            .previousemployeraddress(UPDATED_PREVIOUSEMPLOYERADDRESS)
            .previousindustry(UPDATED_PREVIOUSINDUSTRY)
            .previousperiod(UPDATED_PREVIOUSPERIOD)
            .previouspositions(UPDATED_PREVIOUSPOSITIONS)
            .previousresionforleaving(UPDATED_PREVIOUSRESIONFORLEAVING)
            .hascreaditlimit(UPDATED_HASCREADITLIMIT)
            .accountid(UPDATED_ACCOUNTID)
            .accountcode(UPDATED_ACCOUNTCODE)
            .isregistered(UPDATED_ISREGISTERED)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .lat(UPDATED_LAT)
            .lon(UPDATED_LON)
            .creditperiod(UPDATED_CREDITPERIOD);

        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCustomer.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCustomerToMatchAllProperties(updatedCustomer);
    }

    @Test
    @Transactional
    void putNonExistingCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, customer.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(customer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .code(UPDATED_CODE)
            .title(UPDATED_TITLE)
            .namewithinitials(UPDATED_NAMEWITHINITIALS)
            .fullname(UPDATED_FULLNAME)
            .nicno(UPDATED_NICNO)
            .bloodgroup(UPDATED_BLOODGROUP)
            .gender(UPDATED_GENDER)
            .marrieddate(UPDATED_MARRIEDDATE)
            .territory(UPDATED_TERRITORY)
            .religion(UPDATED_RELIGION)
            .team(UPDATED_TEAM)
            .businessname(UPDATED_BUSINESSNAME)
            .businessregno(UPDATED_BUSINESSREGNO)
            .residenceaddress(UPDATED_RESIDENCEADDRESS)
            .residencecity(UPDATED_RESIDENCECITY)
            .businesslocationno(UPDATED_BUSINESSLOCATIONNO)
            .businessphone2(UPDATED_BUSINESSPHONE_2)
            .businessemail(UPDATED_BUSINESSEMAIL)
            .contactpersonemail(UPDATED_CONTACTPERSONEMAIL)
            .isinternalcustomer(UPDATED_ISINTERNALCUSTOMER)
            .lmd(UPDATED_LMD)
            .maximumdiscount(UPDATED_MAXIMUMDISCOUNT)
            .creditlimit(UPDATED_CREDITLIMIT)
            .paybycash(UPDATED_PAYBYCASH)
            .cashpaymentbeforeload(UPDATED_CASHPAYMENTBEFORELOAD)
            .creditoneweekcheck(UPDATED_CREDITONEWEEKCHECK)
            .haspurchasingdeposit(UPDATED_HASPURCHASINGDEPOSIT)
            .hasassestsdeposit(UPDATED_HASASSESTSDEPOSIT)
            .customerrootmappath(UPDATED_CUSTOMERROOTMAPPATH)
            .employername(UPDATED_EMPLOYERNAME)
            .employeraddress(UPDATED_EMPLOYERADDRESS)
            .previousemployername(UPDATED_PREVIOUSEMPLOYERNAME)
            .previousemployeraddress(UPDATED_PREVIOUSEMPLOYERADDRESS)
            .previousperiod(UPDATED_PREVIOUSPERIOD)
            .previousresionforleaving(UPDATED_PREVIOUSRESIONFORLEAVING)
            .accountid(UPDATED_ACCOUNTID)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .lon(UPDATED_LON);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCustomer, customer), getPersistedCustomer(customer));
    }

    @Test
    @Transactional
    void fullUpdateCustomerWithPatch() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the customer using partial update
        Customer partialUpdatedCustomer = new Customer();
        partialUpdatedCustomer.setId(customer.getId());

        partialUpdatedCustomer
            .customertype(UPDATED_CUSTOMERTYPE)
            .code(UPDATED_CODE)
            .title(UPDATED_TITLE)
            .namewithinitials(UPDATED_NAMEWITHINITIALS)
            .fullname(UPDATED_FULLNAME)
            .callingname(UPDATED_CALLINGNAME)
            .nicno(UPDATED_NICNO)
            .nicissueddate(UPDATED_NICISSUEDDATE)
            .dateofbirth(UPDATED_DATEOFBIRTH)
            .bloodgroup(UPDATED_BLOODGROUP)
            .gender(UPDATED_GENDER)
            .maritalstatus(UPDATED_MARITALSTATUS)
            .marrieddate(UPDATED_MARRIEDDATE)
            .nationality(UPDATED_NATIONALITY)
            .territory(UPDATED_TERRITORY)
            .religion(UPDATED_RELIGION)
            .team(UPDATED_TEAM)
            .businessname(UPDATED_BUSINESSNAME)
            .businessregdate(UPDATED_BUSINESSREGDATE)
            .businessregno(UPDATED_BUSINESSREGNO)
            .profilepicturepath(UPDATED_PROFILEPICTUREPATH)
            .residencehouseno(UPDATED_RESIDENCEHOUSENO)
            .residenceaddress(UPDATED_RESIDENCEADDRESS)
            .residencecity(UPDATED_RESIDENCECITY)
            .residencephone(UPDATED_RESIDENCEPHONE)
            .businesslocationno(UPDATED_BUSINESSLOCATIONNO)
            .businessaddress(UPDATED_BUSINESSADDRESS)
            .businesscity(UPDATED_BUSINESSCITY)
            .businessphone1(UPDATED_BUSINESSPHONE_1)
            .businessphone2(UPDATED_BUSINESSPHONE_2)
            .businessmobile(UPDATED_BUSINESSMOBILE)
            .businessfax(UPDATED_BUSINESSFAX)
            .businessemail(UPDATED_BUSINESSEMAIL)
            .businessprovinceid(UPDATED_BUSINESSPROVINCEID)
            .businessdistrictid(UPDATED_BUSINESSDISTRICTID)
            .contactpersonname(UPDATED_CONTACTPERSONNAME)
            .contactpersonphone(UPDATED_CONTACTPERSONPHONE)
            .contactpersonmobile(UPDATED_CONTACTPERSONMOBILE)
            .contactpersonemail(UPDATED_CONTACTPERSONEMAIL)
            .rootmappath(UPDATED_ROOTMAPPATH)
            .website(UPDATED_WEBSITE)
            .registrationdate(UPDATED_REGISTRATIONDATE)
            .isactive(UPDATED_ISACTIVE)
            .isinternalcustomer(UPDATED_ISINTERNALCUSTOMER)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .maximumdiscount(UPDATED_MAXIMUMDISCOUNT)
            .creditlimit(UPDATED_CREDITLIMIT)
            .hassecuritydeposit(UPDATED_HASSECURITYDEPOSIT)
            .securitydepositamount(UPDATED_SECURITYDEPOSITAMOUNT)
            .paybycash(UPDATED_PAYBYCASH)
            .cashpaymentbeforeload(UPDATED_CASHPAYMENTBEFORELOAD)
            .cashlastinvoicebeforeload(UPDATED_CASHLASTINVOICEBEFORELOAD)
            .paybycredit(UPDATED_PAYBYCREDIT)
            .creditoneweekcheck(UPDATED_CREDITONEWEEKCHECK)
            .creditpaymentbydays(UPDATED_CREDITPAYMENTBYDAYS)
            .haspurchasingdeposit(UPDATED_HASPURCHASINGDEPOSIT)
            .hassecuritydepositbond(UPDATED_HASSECURITYDEPOSITBOND)
            .hasassestsdeposit(UPDATED_HASASSESTSDEPOSIT)
            .customerrootmappath(UPDATED_CUSTOMERROOTMAPPATH)
            .employername(UPDATED_EMPLOYERNAME)
            .employeraddress(UPDATED_EMPLOYERADDRESS)
            .employerphone(UPDATED_EMPLOYERPHONE)
            .employerdesignation(UPDATED_EMPLOYERDESIGNATION)
            .previousemployername(UPDATED_PREVIOUSEMPLOYERNAME)
            .previousemployeraddress(UPDATED_PREVIOUSEMPLOYERADDRESS)
            .previousindustry(UPDATED_PREVIOUSINDUSTRY)
            .previousperiod(UPDATED_PREVIOUSPERIOD)
            .previouspositions(UPDATED_PREVIOUSPOSITIONS)
            .previousresionforleaving(UPDATED_PREVIOUSRESIONFORLEAVING)
            .hascreaditlimit(UPDATED_HASCREADITLIMIT)
            .accountid(UPDATED_ACCOUNTID)
            .accountcode(UPDATED_ACCOUNTCODE)
            .isregistered(UPDATED_ISREGISTERED)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .lat(UPDATED_LAT)
            .lon(UPDATED_LON)
            .creditperiod(UPDATED_CREDITPERIOD);

        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCustomer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCustomer))
            )
            .andExpect(status().isOk());

        // Validate the Customer in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCustomerUpdatableFieldsEquals(partialUpdatedCustomer, getPersistedCustomer(partialUpdatedCustomer));
    }

    @Test
    @Transactional
    void patchNonExistingCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, customer.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(customer))
            )
            .andExpect(status().isBadRequest());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCustomer() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        customer.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCustomerMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(customer)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Customer in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCustomer() throws Exception {
        // Initialize the database
        insertedCustomer = customerRepository.saveAndFlush(customer);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the customer
        restCustomerMockMvc
            .perform(delete(ENTITY_API_URL_ID, customer.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return customerRepository.count();
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

    protected Customer getPersistedCustomer(Customer customer) {
        return customerRepository.findById(customer.getId()).orElseThrow();
    }

    protected void assertPersistedCustomerToMatchAllProperties(Customer expectedCustomer) {
        assertCustomerAllPropertiesEquals(expectedCustomer, getPersistedCustomer(expectedCustomer));
    }

    protected void assertPersistedCustomerToMatchUpdatableProperties(Customer expectedCustomer) {
        assertCustomerAllUpdatablePropertiesEquals(expectedCustomer, getPersistedCustomer(expectedCustomer));
    }
}
