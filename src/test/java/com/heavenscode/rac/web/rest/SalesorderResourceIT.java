package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.SalesorderAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Salesorder;
import com.heavenscode.rac.repository.SalesorderRepository;
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
 * Integration tests for the {@link SalesorderResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesorderResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_ORDERDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ORDERDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_QUOTEID = 1;
    private static final Integer UPDATED_QUOTEID = 2;

    private static final Integer DEFAULT_SALESREPID = 1;
    private static final Integer UPDATED_SALESREPID = 2;

    private static final String DEFAULT_SALESREPNAME = "AAAAAAAAAA";
    private static final String UPDATED_SALESREPNAME = "BBBBBBBBBB";

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

    private static final Boolean DEFAULT_ISINVOICED = false;
    private static final Boolean UPDATED_ISINVOICED = true;

    private static final Integer DEFAULT_REFINVOICEID = 1;
    private static final Integer UPDATED_REFINVOICEID = 2;

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISFIXED = false;
    private static final Boolean UPDATED_ISFIXED = true;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Float DEFAULT_ADVANCEPAYMENT = 1F;
    private static final Float UPDATED_ADVANCEPAYMENT = 2F;

    private static final Float DEFAULT_ADVANCEPAYMENTRETURNAMOUNT = 1F;
    private static final Float UPDATED_ADVANCEPAYMENTRETURNAMOUNT = 2F;

    private static final Instant DEFAULT_ADVANCEPAYMENTRETURNDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADVANCEPAYMENTRETURNDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_ADVANCEPAYMENTBY = 1;
    private static final Integer UPDATED_ADVANCEPAYMENTBY = 2;

    private static final String ENTITY_API_URL = "/api/salesorders";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SalesorderRepository salesorderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesorderMockMvc;

    private Salesorder salesorder;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salesorder createEntity(EntityManager em) {
        Salesorder salesorder = new Salesorder()
            .code(DEFAULT_CODE)
            .orderdate(DEFAULT_ORDERDATE)
            .createddate(DEFAULT_CREATEDDATE)
            .quoteid(DEFAULT_QUOTEID)
            .salesrepid(DEFAULT_SALESREPID)
            .salesrepname(DEFAULT_SALESREPNAME)
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
            .isinvoiced(DEFAULT_ISINVOICED)
            .refinvoiceid(DEFAULT_REFINVOICEID)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .isfixed(DEFAULT_ISFIXED)
            .isactive(DEFAULT_ISACTIVE)
            .advancepayment(DEFAULT_ADVANCEPAYMENT)
            .advancepaymentreturnamount(DEFAULT_ADVANCEPAYMENTRETURNAMOUNT)
            .advancepaymentreturndate(DEFAULT_ADVANCEPAYMENTRETURNDATE)
            .advancepaymentby(DEFAULT_ADVANCEPAYMENTBY);
        return salesorder;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salesorder createUpdatedEntity(EntityManager em) {
        Salesorder salesorder = new Salesorder()
            .code(UPDATED_CODE)
            .orderdate(UPDATED_ORDERDATE)
            .createddate(UPDATED_CREATEDDATE)
            .quoteid(UPDATED_QUOTEID)
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
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
            .isinvoiced(UPDATED_ISINVOICED)
            .refinvoiceid(UPDATED_REFINVOICEID)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isfixed(UPDATED_ISFIXED)
            .isactive(UPDATED_ISACTIVE)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .advancepaymentreturnamount(UPDATED_ADVANCEPAYMENTRETURNAMOUNT)
            .advancepaymentreturndate(UPDATED_ADVANCEPAYMENTRETURNDATE)
            .advancepaymentby(UPDATED_ADVANCEPAYMENTBY);
        return salesorder;
    }

    @BeforeEach
    public void initTest() {
        salesorder = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesorder() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Salesorder
        var returnedSalesorder = om.readValue(
            restSalesorderMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesorder)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Salesorder.class
        );

        // Validate the Salesorder in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSalesorderUpdatableFieldsEquals(returnedSalesorder, getPersistedSalesorder(returnedSalesorder));
    }

    @Test
    @Transactional
    void createSalesorderWithExistingId() throws Exception {
        // Create the Salesorder with an existing ID
        salesorder.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesorderMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesorder)))
            .andExpect(status().isBadRequest());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesorders() throws Exception {
        // Initialize the database
        salesorderRepository.saveAndFlush(salesorder);

        // Get all the salesorderList
        restSalesorderMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesorder.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].orderdate").value(hasItem(DEFAULT_ORDERDATE.toString())))
            .andExpect(jsonPath("$.[*].createddate").value(hasItem(DEFAULT_CREATEDDATE.toString())))
            .andExpect(jsonPath("$.[*].quoteid").value(hasItem(DEFAULT_QUOTEID)))
            .andExpect(jsonPath("$.[*].salesrepid").value(hasItem(DEFAULT_SALESREPID)))
            .andExpect(jsonPath("$.[*].salesrepname").value(hasItem(DEFAULT_SALESREPNAME)))
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
            .andExpect(jsonPath("$.[*].isinvoiced").value(hasItem(DEFAULT_ISINVOICED.booleanValue())))
            .andExpect(jsonPath("$.[*].refinvoiceid").value(hasItem(DEFAULT_REFINVOICEID)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].isfixed").value(hasItem(DEFAULT_ISFIXED.booleanValue())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].advancepayment").value(hasItem(DEFAULT_ADVANCEPAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].advancepaymentreturnamount").value(hasItem(DEFAULT_ADVANCEPAYMENTRETURNAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].advancepaymentreturndate").value(hasItem(DEFAULT_ADVANCEPAYMENTRETURNDATE.toString())))
            .andExpect(jsonPath("$.[*].advancepaymentby").value(hasItem(DEFAULT_ADVANCEPAYMENTBY)));
    }

    @Test
    @Transactional
    void getSalesorder() throws Exception {
        // Initialize the database
        salesorderRepository.saveAndFlush(salesorder);

        // Get the salesorder
        restSalesorderMockMvc
            .perform(get(ENTITY_API_URL_ID, salesorder.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesorder.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.orderdate").value(DEFAULT_ORDERDATE.toString()))
            .andExpect(jsonPath("$.createddate").value(DEFAULT_CREATEDDATE.toString()))
            .andExpect(jsonPath("$.quoteid").value(DEFAULT_QUOTEID))
            .andExpect(jsonPath("$.salesrepid").value(DEFAULT_SALESREPID))
            .andExpect(jsonPath("$.salesrepname").value(DEFAULT_SALESREPNAME))
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
            .andExpect(jsonPath("$.isinvoiced").value(DEFAULT_ISINVOICED.booleanValue()))
            .andExpect(jsonPath("$.refinvoiceid").value(DEFAULT_REFINVOICEID))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.isfixed").value(DEFAULT_ISFIXED.booleanValue()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.advancepayment").value(DEFAULT_ADVANCEPAYMENT.doubleValue()))
            .andExpect(jsonPath("$.advancepaymentreturnamount").value(DEFAULT_ADVANCEPAYMENTRETURNAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.advancepaymentreturndate").value(DEFAULT_ADVANCEPAYMENTRETURNDATE.toString()))
            .andExpect(jsonPath("$.advancepaymentby").value(DEFAULT_ADVANCEPAYMENTBY));
    }

    @Test
    @Transactional
    void getNonExistingSalesorder() throws Exception {
        // Get the salesorder
        restSalesorderMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesorder() throws Exception {
        // Initialize the database
        salesorderRepository.saveAndFlush(salesorder);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the salesorder
        Salesorder updatedSalesorder = salesorderRepository.findById(salesorder.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSalesorder are not directly saved in db
        em.detach(updatedSalesorder);
        updatedSalesorder
            .code(UPDATED_CODE)
            .orderdate(UPDATED_ORDERDATE)
            .createddate(UPDATED_CREATEDDATE)
            .quoteid(UPDATED_QUOTEID)
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
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
            .isinvoiced(UPDATED_ISINVOICED)
            .refinvoiceid(UPDATED_REFINVOICEID)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isfixed(UPDATED_ISFIXED)
            .isactive(UPDATED_ISACTIVE)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .advancepaymentreturnamount(UPDATED_ADVANCEPAYMENTRETURNAMOUNT)
            .advancepaymentreturndate(UPDATED_ADVANCEPAYMENTRETURNDATE)
            .advancepaymentby(UPDATED_ADVANCEPAYMENTBY);

        restSalesorderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSalesorder.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSalesorder))
            )
            .andExpect(status().isOk());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSalesorderToMatchAllProperties(updatedSalesorder);
    }

    @Test
    @Transactional
    void putNonExistingSalesorder() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesorder.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesorderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesorder.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesorder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesorder() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesorder.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesorderMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(salesorder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesorder() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesorder.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesorderMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesorder)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesorderWithPatch() throws Exception {
        // Initialize the database
        salesorderRepository.saveAndFlush(salesorder);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the salesorder using partial update
        Salesorder partialUpdatedSalesorder = new Salesorder();
        partialUpdatedSalesorder.setId(salesorder.getId());

        partialUpdatedSalesorder
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
            .customerid(UPDATED_CUSTOMERID)
            .subtotal(UPDATED_SUBTOTAL)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .isinvoiced(UPDATED_ISINVOICED)
            .refinvoiceid(UPDATED_REFINVOICEID)
            .lmd(UPDATED_LMD)
            .isactive(UPDATED_ISACTIVE)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .advancepaymentreturndate(UPDATED_ADVANCEPAYMENTRETURNDATE);

        restSalesorderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesorder.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSalesorder))
            )
            .andExpect(status().isOk());

        // Validate the Salesorder in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSalesorderUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSalesorder, salesorder),
            getPersistedSalesorder(salesorder)
        );
    }

    @Test
    @Transactional
    void fullUpdateSalesorderWithPatch() throws Exception {
        // Initialize the database
        salesorderRepository.saveAndFlush(salesorder);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the salesorder using partial update
        Salesorder partialUpdatedSalesorder = new Salesorder();
        partialUpdatedSalesorder.setId(salesorder.getId());

        partialUpdatedSalesorder
            .code(UPDATED_CODE)
            .orderdate(UPDATED_ORDERDATE)
            .createddate(UPDATED_CREATEDDATE)
            .quoteid(UPDATED_QUOTEID)
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
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
            .isinvoiced(UPDATED_ISINVOICED)
            .refinvoiceid(UPDATED_REFINVOICEID)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isfixed(UPDATED_ISFIXED)
            .isactive(UPDATED_ISACTIVE)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .advancepaymentreturnamount(UPDATED_ADVANCEPAYMENTRETURNAMOUNT)
            .advancepaymentreturndate(UPDATED_ADVANCEPAYMENTRETURNDATE)
            .advancepaymentby(UPDATED_ADVANCEPAYMENTBY);

        restSalesorderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesorder.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSalesorder))
            )
            .andExpect(status().isOk());

        // Validate the Salesorder in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSalesorderUpdatableFieldsEquals(partialUpdatedSalesorder, getPersistedSalesorder(partialUpdatedSalesorder));
    }

    @Test
    @Transactional
    void patchNonExistingSalesorder() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesorder.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesorderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesorder.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(salesorder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesorder() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesorder.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesorderMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(salesorder))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesorder() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesorder.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesorderMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(salesorder)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Salesorder in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesorder() throws Exception {
        // Initialize the database
        salesorderRepository.saveAndFlush(salesorder);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the salesorder
        restSalesorderMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesorder.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return salesorderRepository.count();
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

    protected Salesorder getPersistedSalesorder(Salesorder salesorder) {
        return salesorderRepository.findById(salesorder.getId()).orElseThrow();
    }

    protected void assertPersistedSalesorderToMatchAllProperties(Salesorder expectedSalesorder) {
        assertSalesorderAllPropertiesEquals(expectedSalesorder, getPersistedSalesorder(expectedSalesorder));
    }

    protected void assertPersistedSalesorderToMatchUpdatableProperties(Salesorder expectedSalesorder) {
        assertSalesorderAllUpdatablePropertiesEquals(expectedSalesorder, getPersistedSalesorder(expectedSalesorder));
    }
}
