package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutojobsinvoiceAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autojobsinvoice;
import com.heavenscode.rac.repository.AutojobsinvoiceRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
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
 * Integration tests for the {@link AutojobsinvoiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutojobsinvoiceResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_INVOICEDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INVOICEDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_JOBID = 1;
    private static final Integer UPDATED_JOBID = 2;

    private static final Integer DEFAULT_QUOTEID = 1;
    private static final Integer UPDATED_QUOTEID = 2;

    private static final Integer DEFAULT_ORDERID = 1;
    private static final Integer UPDATED_ORDERID = 2;

    private static final Instant DEFAULT_DELIEVERYDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIEVERYDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_AUTOJOBSREPID = 1;
    private static final Integer UPDATED_AUTOJOBSREPID = 2;

    private static final String DEFAULT_AUTOJOBSREPNAME = "AAAAAAAAAA";
    private static final String UPDATED_AUTOJOBSREPNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIEVERFROM = "AAAAAAAAAA";
    private static final String UPDATED_DELIEVERFROM = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERYADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERYADDRESS = "BBBBBBBBBB";

    private static final Float DEFAULT_SUBTOTAL = 1F;
    private static final Float UPDATED_SUBTOTAL = 2F;

    private static final Float DEFAULT_TOTALTAX = 1F;
    private static final Float UPDATED_TOTALTAX = 2F;

    private static final Float DEFAULT_TOTALDISCOUNT = 1F;
    private static final Float UPDATED_TOTALDISCOUNT = 2F;

    private static final Float DEFAULT_NETTOTAL = 1F;
    private static final Float UPDATED_NETTOTAL = 2F;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_PAIDAMOUNT = 1F;
    private static final Float UPDATED_PAIDAMOUNT = 2F;

    private static final Float DEFAULT_AMOUNTOWING = 1F;
    private static final Float UPDATED_AMOUNTOWING = 2F;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Integer DEFAULT_LOCATIONID = 1;
    private static final Integer UPDATED_LOCATIONID = 2;

    private static final String DEFAULT_LOCATIONCODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIONCODE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCECODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCECODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREATEDBYID = 1;
    private static final Integer UPDATED_CREATEDBYID = 2;

    private static final String DEFAULT_CREATEDBYNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBYNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AUTOCARECOMPANYID = 1;
    private static final Integer UPDATED_AUTOCARECOMPANYID = 2;

    private static final String ENTITY_API_URL = "/api/autojobsinvoices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutojobsinvoiceRepository autojobsinvoiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutojobsinvoiceMockMvc;

    private Autojobsinvoice autojobsinvoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autojobsinvoice createEntity(EntityManager em) {
        Autojobsinvoice autojobsinvoice = new Autojobsinvoice()
            .code(DEFAULT_CODE)
            .invoicedate(DEFAULT_INVOICEDATE)
            .createddate(DEFAULT_CREATEDDATE)
            .jobid(DEFAULT_JOBID)
            .quoteid(DEFAULT_QUOTEID)
            .orderid(DEFAULT_ORDERID)
            .delieverydate(DEFAULT_DELIEVERYDATE)
            .autojobsrepid(DEFAULT_AUTOJOBSREPID)
            .autojobsrepname(DEFAULT_AUTOJOBSREPNAME)
            .delieverfrom(DEFAULT_DELIEVERFROM)
            .customerid(DEFAULT_CUSTOMERID)
            .customername(DEFAULT_CUSTOMERNAME)
            .customeraddress(DEFAULT_CUSTOMERADDRESS)
            .deliveryaddress(DEFAULT_DELIVERYADDRESS)
            .subtotal(DEFAULT_SUBTOTAL)
            .totaltax(DEFAULT_TOTALTAX)
            .totaldiscount(DEFAULT_TOTALDISCOUNT)
            .nettotal(DEFAULT_NETTOTAL)
            .message(DEFAULT_MESSAGE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .paidamount(DEFAULT_PAIDAMOUNT)
            .amountowing(DEFAULT_AMOUNTOWING)
            .isactive(DEFAULT_ISACTIVE)
            .locationid(DEFAULT_LOCATIONID)
            .locationcode(DEFAULT_LOCATIONCODE)
            .referencecode(DEFAULT_REFERENCECODE)
            .createdbyid(DEFAULT_CREATEDBYID)
            .createdbyname(DEFAULT_CREATEDBYNAME)
            .autocarecompanyid(DEFAULT_AUTOCARECOMPANYID);
        return autojobsinvoice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autojobsinvoice createUpdatedEntity(EntityManager em) {
        Autojobsinvoice autojobsinvoice = new Autojobsinvoice()
            .code(UPDATED_CODE)
            .invoicedate(UPDATED_INVOICEDATE)
            .createddate(UPDATED_CREATEDDATE)
            .jobid(UPDATED_JOBID)
            .quoteid(UPDATED_QUOTEID)
            .orderid(UPDATED_ORDERID)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .autojobsrepid(UPDATED_AUTOJOBSREPID)
            .autojobsrepname(UPDATED_AUTOJOBSREPNAME)
            .delieverfrom(UPDATED_DELIEVERFROM)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .totaltax(UPDATED_TOTALTAX)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .paidamount(UPDATED_PAIDAMOUNT)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID);
        return autojobsinvoice;
    }

    @BeforeEach
    public void initTest() {
        autojobsinvoice = createEntity(em);
    }

    @Test
    @Transactional
    void createAutojobsinvoice() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autojobsinvoice
        var returnedAutojobsinvoice = om.readValue(
            restAutojobsinvoiceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autojobsinvoice)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autojobsinvoice.class
        );

        // Validate the Autojobsinvoice in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutojobsinvoiceUpdatableFieldsEquals(returnedAutojobsinvoice, getPersistedAutojobsinvoice(returnedAutojobsinvoice));
    }

    @Test
    @Transactional
    void createAutojobsinvoiceWithExistingId() throws Exception {
        // Create the Autojobsinvoice with an existing ID
        autojobsinvoice.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutojobsinvoiceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autojobsinvoice)))
            .andExpect(status().isBadRequest());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutojobsinvoices() throws Exception {
        // Initialize the database
        autojobsinvoiceRepository.saveAndFlush(autojobsinvoice);

        // Get all the autojobsinvoiceList
        restAutojobsinvoiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autojobsinvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].invoicedate").value(hasItem(DEFAULT_INVOICEDATE.toString())))
            .andExpect(jsonPath("$.[*].createddate").value(hasItem(DEFAULT_CREATEDDATE.toString())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)))
            .andExpect(jsonPath("$.[*].quoteid").value(hasItem(DEFAULT_QUOTEID)))
            .andExpect(jsonPath("$.[*].orderid").value(hasItem(DEFAULT_ORDERID)))
            .andExpect(jsonPath("$.[*].delieverydate").value(hasItem(DEFAULT_DELIEVERYDATE.toString())))
            .andExpect(jsonPath("$.[*].autojobsrepid").value(hasItem(DEFAULT_AUTOJOBSREPID)))
            .andExpect(jsonPath("$.[*].autojobsrepname").value(hasItem(DEFAULT_AUTOJOBSREPNAME)))
            .andExpect(jsonPath("$.[*].delieverfrom").value(hasItem(DEFAULT_DELIEVERFROM)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].customeraddress").value(hasItem(DEFAULT_CUSTOMERADDRESS)))
            .andExpect(jsonPath("$.[*].deliveryaddress").value(hasItem(DEFAULT_DELIVERYADDRESS)))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].totaltax").value(hasItem(DEFAULT_TOTALTAX.doubleValue())))
            .andExpect(jsonPath("$.[*].totaldiscount").value(hasItem(DEFAULT_TOTALDISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].nettotal").value(hasItem(DEFAULT_NETTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].paidamount").value(hasItem(DEFAULT_PAIDAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].amountowing").value(hasItem(DEFAULT_AMOUNTOWING.doubleValue())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].locationid").value(hasItem(DEFAULT_LOCATIONID)))
            .andExpect(jsonPath("$.[*].locationcode").value(hasItem(DEFAULT_LOCATIONCODE)))
            .andExpect(jsonPath("$.[*].referencecode").value(hasItem(DEFAULT_REFERENCECODE)))
            .andExpect(jsonPath("$.[*].createdbyid").value(hasItem(DEFAULT_CREATEDBYID)))
            .andExpect(jsonPath("$.[*].createdbyname").value(hasItem(DEFAULT_CREATEDBYNAME)))
            .andExpect(jsonPath("$.[*].autocarecompanyid").value(hasItem(DEFAULT_AUTOCARECOMPANYID)));
    }

    @Test
    @Transactional
    void getAutojobsinvoice() throws Exception {
        // Initialize the database
        autojobsinvoiceRepository.saveAndFlush(autojobsinvoice);

        // Get the autojobsinvoice
        restAutojobsinvoiceMockMvc
            .perform(get(ENTITY_API_URL_ID, autojobsinvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autojobsinvoice.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.invoicedate").value(DEFAULT_INVOICEDATE.toString()))
            .andExpect(jsonPath("$.createddate").value(DEFAULT_CREATEDDATE.toString()))
            .andExpect(jsonPath("$.jobid").value(DEFAULT_JOBID))
            .andExpect(jsonPath("$.quoteid").value(DEFAULT_QUOTEID))
            .andExpect(jsonPath("$.orderid").value(DEFAULT_ORDERID))
            .andExpect(jsonPath("$.delieverydate").value(DEFAULT_DELIEVERYDATE.toString()))
            .andExpect(jsonPath("$.autojobsrepid").value(DEFAULT_AUTOJOBSREPID))
            .andExpect(jsonPath("$.autojobsrepname").value(DEFAULT_AUTOJOBSREPNAME))
            .andExpect(jsonPath("$.delieverfrom").value(DEFAULT_DELIEVERFROM))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.customeraddress").value(DEFAULT_CUSTOMERADDRESS))
            .andExpect(jsonPath("$.deliveryaddress").value(DEFAULT_DELIVERYADDRESS))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.doubleValue()))
            .andExpect(jsonPath("$.totaltax").value(DEFAULT_TOTALTAX.doubleValue()))
            .andExpect(jsonPath("$.totaldiscount").value(DEFAULT_TOTALDISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.nettotal").value(DEFAULT_NETTOTAL.doubleValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.paidamount").value(DEFAULT_PAIDAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.amountowing").value(DEFAULT_AMOUNTOWING.doubleValue()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.locationid").value(DEFAULT_LOCATIONID))
            .andExpect(jsonPath("$.locationcode").value(DEFAULT_LOCATIONCODE))
            .andExpect(jsonPath("$.referencecode").value(DEFAULT_REFERENCECODE))
            .andExpect(jsonPath("$.createdbyid").value(DEFAULT_CREATEDBYID))
            .andExpect(jsonPath("$.createdbyname").value(DEFAULT_CREATEDBYNAME))
            .andExpect(jsonPath("$.autocarecompanyid").value(DEFAULT_AUTOCARECOMPANYID));
    }

    @Test
    @Transactional
    void getNonExistingAutojobsinvoice() throws Exception {
        // Get the autojobsinvoice
        restAutojobsinvoiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutojobsinvoice() throws Exception {
        // Initialize the database
        autojobsinvoiceRepository.saveAndFlush(autojobsinvoice);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autojobsinvoice
        Autojobsinvoice updatedAutojobsinvoice = autojobsinvoiceRepository.findById(autojobsinvoice.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutojobsinvoice are not directly saved in db
        em.detach(updatedAutojobsinvoice);
        updatedAutojobsinvoice
            .code(UPDATED_CODE)
            .invoicedate(UPDATED_INVOICEDATE)
            .createddate(UPDATED_CREATEDDATE)
            .jobid(UPDATED_JOBID)
            .quoteid(UPDATED_QUOTEID)
            .orderid(UPDATED_ORDERID)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .autojobsrepid(UPDATED_AUTOJOBSREPID)
            .autojobsrepname(UPDATED_AUTOJOBSREPNAME)
            .delieverfrom(UPDATED_DELIEVERFROM)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .totaltax(UPDATED_TOTALTAX)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .paidamount(UPDATED_PAIDAMOUNT)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID);

        restAutojobsinvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutojobsinvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutojobsinvoice))
            )
            .andExpect(status().isOk());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutojobsinvoiceToMatchAllProperties(updatedAutojobsinvoice);
    }

    @Test
    @Transactional
    void putNonExistingAutojobsinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobsinvoice.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutojobsinvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autojobsinvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autojobsinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutojobsinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobsinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobsinvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autojobsinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutojobsinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobsinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobsinvoiceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autojobsinvoice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutojobsinvoiceWithPatch() throws Exception {
        // Initialize the database
        autojobsinvoiceRepository.saveAndFlush(autojobsinvoice);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autojobsinvoice using partial update
        Autojobsinvoice partialUpdatedAutojobsinvoice = new Autojobsinvoice();
        partialUpdatedAutojobsinvoice.setId(autojobsinvoice.getId());

        partialUpdatedAutojobsinvoice
            .code(UPDATED_CODE)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .autojobsrepid(UPDATED_AUTOJOBSREPID)
            .customerid(UPDATED_CUSTOMERID)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID);

        restAutojobsinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutojobsinvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutojobsinvoice))
            )
            .andExpect(status().isOk());

        // Validate the Autojobsinvoice in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutojobsinvoiceUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutojobsinvoice, autojobsinvoice),
            getPersistedAutojobsinvoice(autojobsinvoice)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutojobsinvoiceWithPatch() throws Exception {
        // Initialize the database
        autojobsinvoiceRepository.saveAndFlush(autojobsinvoice);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autojobsinvoice using partial update
        Autojobsinvoice partialUpdatedAutojobsinvoice = new Autojobsinvoice();
        partialUpdatedAutojobsinvoice.setId(autojobsinvoice.getId());

        partialUpdatedAutojobsinvoice
            .code(UPDATED_CODE)
            .invoicedate(UPDATED_INVOICEDATE)
            .createddate(UPDATED_CREATEDDATE)
            .jobid(UPDATED_JOBID)
            .quoteid(UPDATED_QUOTEID)
            .orderid(UPDATED_ORDERID)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .autojobsrepid(UPDATED_AUTOJOBSREPID)
            .autojobsrepname(UPDATED_AUTOJOBSREPNAME)
            .delieverfrom(UPDATED_DELIEVERFROM)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .totaltax(UPDATED_TOTALTAX)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .paidamount(UPDATED_PAIDAMOUNT)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID);

        restAutojobsinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutojobsinvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutojobsinvoice))
            )
            .andExpect(status().isOk());

        // Validate the Autojobsinvoice in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutojobsinvoiceUpdatableFieldsEquals(
            partialUpdatedAutojobsinvoice,
            getPersistedAutojobsinvoice(partialUpdatedAutojobsinvoice)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutojobsinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobsinvoice.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutojobsinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autojobsinvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autojobsinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutojobsinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobsinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobsinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autojobsinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutojobsinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobsinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobsinvoiceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autojobsinvoice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autojobsinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutojobsinvoice() throws Exception {
        // Initialize the database
        autojobsinvoiceRepository.saveAndFlush(autojobsinvoice);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autojobsinvoice
        restAutojobsinvoiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, autojobsinvoice.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autojobsinvoiceRepository.count();
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

    protected Autojobsinvoice getPersistedAutojobsinvoice(Autojobsinvoice autojobsinvoice) {
        return autojobsinvoiceRepository.findById(autojobsinvoice.getId()).orElseThrow();
    }

    protected void assertPersistedAutojobsinvoiceToMatchAllProperties(Autojobsinvoice expectedAutojobsinvoice) {
        assertAutojobsinvoiceAllPropertiesEquals(expectedAutojobsinvoice, getPersistedAutojobsinvoice(expectedAutojobsinvoice));
    }

    protected void assertPersistedAutojobsinvoiceToMatchUpdatableProperties(Autojobsinvoice expectedAutojobsinvoice) {
        assertAutojobsinvoiceAllUpdatablePropertiesEquals(expectedAutojobsinvoice, getPersistedAutojobsinvoice(expectedAutojobsinvoice));
    }
}
