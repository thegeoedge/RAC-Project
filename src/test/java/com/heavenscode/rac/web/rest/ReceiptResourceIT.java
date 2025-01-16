package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.ReceiptAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Receipt;
import com.heavenscode.rac.repository.ReceiptRepository;
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
 * Integration tests for the {@link ReceiptResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ReceiptResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_RECEIPTDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_RECEIPTDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERADDRESS = "BBBBBBBBBB";

    private static final Float DEFAULT_TOTALAMOUNT = 1F;
    private static final Float UPDATED_TOTALAMOUNT = 2F;

    private static final String DEFAULT_TOTALAMOUNTINWORD = "AAAAAAAAAA";
    private static final String UPDATED_TOTALAMOUNTINWORD = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_COMMENTS = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_TERMID = 1;
    private static final Integer UPDATED_TERMID = 2;

    private static final String DEFAULT_TERM = "AAAAAAAAAA";
    private static final String UPDATED_TERM = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final Instant DEFAULT_CHECKDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CHECKDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CHECKNO = "AAAAAAAAAA";
    private static final String UPDATED_CHECKNO = "BBBBBBBBBB";

    private static final String DEFAULT_BANK = "AAAAAAAAAA";
    private static final String UPDATED_BANK = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Boolean DEFAULT_DEPOSITED = false;
    private static final Boolean UPDATED_DEPOSITED = true;

    private static final Integer DEFAULT_CREATEDBY = 1;
    private static final Integer UPDATED_CREATEDBY = 2;

    private static final String ENTITY_API_URL = "/api/receipts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restReceiptMockMvc;

    private Receipt receipt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Receipt createEntity(EntityManager em) {
        Receipt receipt = new Receipt()
            .code(DEFAULT_CODE)
            .receiptdate(DEFAULT_RECEIPTDATE)
            .customername(DEFAULT_CUSTOMERNAME)
            .customeraddress(DEFAULT_CUSTOMERADDRESS)
            .totalamount(DEFAULT_TOTALAMOUNT)
            .totalamountinword(DEFAULT_TOTALAMOUNTINWORD)
            .comments(DEFAULT_COMMENTS)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .termid(DEFAULT_TERMID)
            .term(DEFAULT_TERM)
            .date(DEFAULT_DATE)
            .amount(DEFAULT_AMOUNT)
            .checkdate(DEFAULT_CHECKDATE)
            .checkno(DEFAULT_CHECKNO)
            .bank(DEFAULT_BANK)
            .customerid(DEFAULT_CUSTOMERID)
            .isactive(DEFAULT_ISACTIVE)
            .deposited(DEFAULT_DEPOSITED)
            .createdby(DEFAULT_CREATEDBY);
        return receipt;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Receipt createUpdatedEntity(EntityManager em) {
        Receipt receipt = new Receipt()
            .code(UPDATED_CODE)
            .receiptdate(UPDATED_RECEIPTDATE)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .totalamount(UPDATED_TOTALAMOUNT)
            .totalamountinword(UPDATED_TOTALAMOUNTINWORD)
            .comments(UPDATED_COMMENTS)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .termid(UPDATED_TERMID)
            .term(UPDATED_TERM)
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT)
            .checkdate(UPDATED_CHECKDATE)
            .checkno(UPDATED_CHECKNO)
            .bank(UPDATED_BANK)
            .customerid(UPDATED_CUSTOMERID)
            .isactive(UPDATED_ISACTIVE)
            .deposited(UPDATED_DEPOSITED)
            .createdby(UPDATED_CREATEDBY);
        return receipt;
    }

    @BeforeEach
    public void initTest() {
        receipt = createEntity(em);
    }

    @Test
    @Transactional
    void createReceipt() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Receipt
        var returnedReceipt = om.readValue(
            restReceiptMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(receipt)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Receipt.class
        );

        // Validate the Receipt in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertReceiptUpdatableFieldsEquals(returnedReceipt, getPersistedReceipt(returnedReceipt));
    }

    @Test
    @Transactional
    void createReceiptWithExistingId() throws Exception {
        // Create the Receipt with an existing ID
        receipt.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceiptMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(receipt)))
            .andExpect(status().isBadRequest());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllReceipts() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        // Get all the receiptList
        restReceiptMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(receipt.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].receiptdate").value(hasItem(DEFAULT_RECEIPTDATE.toString())))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].customeraddress").value(hasItem(DEFAULT_CUSTOMERADDRESS)))
            .andExpect(jsonPath("$.[*].totalamount").value(hasItem(DEFAULT_TOTALAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].totalamountinword").value(hasItem(DEFAULT_TOTALAMOUNTINWORD)))
            .andExpect(jsonPath("$.[*].comments").value(hasItem(DEFAULT_COMMENTS)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].termid").value(hasItem(DEFAULT_TERMID)))
            .andExpect(jsonPath("$.[*].term").value(hasItem(DEFAULT_TERM)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].checkdate").value(hasItem(DEFAULT_CHECKDATE.toString())))
            .andExpect(jsonPath("$.[*].checkno").value(hasItem(DEFAULT_CHECKNO)))
            .andExpect(jsonPath("$.[*].bank").value(hasItem(DEFAULT_BANK)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].deposited").value(hasItem(DEFAULT_DEPOSITED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdby").value(hasItem(DEFAULT_CREATEDBY)));
    }

    @Test
    @Transactional
    void getReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        // Get the receipt
        restReceiptMockMvc
            .perform(get(ENTITY_API_URL_ID, receipt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(receipt.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.receiptdate").value(DEFAULT_RECEIPTDATE.toString()))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.customeraddress").value(DEFAULT_CUSTOMERADDRESS))
            .andExpect(jsonPath("$.totalamount").value(DEFAULT_TOTALAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.totalamountinword").value(DEFAULT_TOTALAMOUNTINWORD))
            .andExpect(jsonPath("$.comments").value(DEFAULT_COMMENTS))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.termid").value(DEFAULT_TERMID))
            .andExpect(jsonPath("$.term").value(DEFAULT_TERM))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.checkdate").value(DEFAULT_CHECKDATE.toString()))
            .andExpect(jsonPath("$.checkno").value(DEFAULT_CHECKNO))
            .andExpect(jsonPath("$.bank").value(DEFAULT_BANK))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.deposited").value(DEFAULT_DEPOSITED.booleanValue()))
            .andExpect(jsonPath("$.createdby").value(DEFAULT_CREATEDBY));
    }

    @Test
    @Transactional
    void getNonExistingReceipt() throws Exception {
        // Get the receipt
        restReceiptMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the receipt
        Receipt updatedReceipt = receiptRepository.findById(receipt.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedReceipt are not directly saved in db
        em.detach(updatedReceipt);
        updatedReceipt
            .code(UPDATED_CODE)
            .receiptdate(UPDATED_RECEIPTDATE)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .totalamount(UPDATED_TOTALAMOUNT)
            .totalamountinword(UPDATED_TOTALAMOUNTINWORD)
            .comments(UPDATED_COMMENTS)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .termid(UPDATED_TERMID)
            .term(UPDATED_TERM)
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT)
            .checkdate(UPDATED_CHECKDATE)
            .checkno(UPDATED_CHECKNO)
            .bank(UPDATED_BANK)
            .customerid(UPDATED_CUSTOMERID)
            .isactive(UPDATED_ISACTIVE)
            .deposited(UPDATED_DEPOSITED)
            .createdby(UPDATED_CREATEDBY);

        restReceiptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedReceipt.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedReceipt))
            )
            .andExpect(status().isOk());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedReceiptToMatchAllProperties(updatedReceipt);
    }

    @Test
    @Transactional
    void putNonExistingReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        receipt.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceiptMockMvc
            .perform(put(ENTITY_API_URL_ID, receipt.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(receipt)))
            .andExpect(status().isBadRequest());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        receipt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(receipt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        receipt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(receipt)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateReceiptWithPatch() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the receipt using partial update
        Receipt partialUpdatedReceipt = new Receipt();
        partialUpdatedReceipt.setId(receipt.getId());

        partialUpdatedReceipt
            .code(UPDATED_CODE)
            .totalamount(UPDATED_TOTALAMOUNT)
            .totalamountinword(UPDATED_TOTALAMOUNTINWORD)
            .comments(UPDATED_COMMENTS)
            .amount(UPDATED_AMOUNT)
            .checkno(UPDATED_CHECKNO)
            .bank(UPDATED_BANK)
            .isactive(UPDATED_ISACTIVE)
            .deposited(UPDATED_DEPOSITED)
            .createdby(UPDATED_CREATEDBY);

        restReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReceipt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReceipt))
            )
            .andExpect(status().isOk());

        // Validate the Receipt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReceiptUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedReceipt, receipt), getPersistedReceipt(receipt));
    }

    @Test
    @Transactional
    void fullUpdateReceiptWithPatch() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the receipt using partial update
        Receipt partialUpdatedReceipt = new Receipt();
        partialUpdatedReceipt.setId(receipt.getId());

        partialUpdatedReceipt
            .code(UPDATED_CODE)
            .receiptdate(UPDATED_RECEIPTDATE)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .totalamount(UPDATED_TOTALAMOUNT)
            .totalamountinword(UPDATED_TOTALAMOUNTINWORD)
            .comments(UPDATED_COMMENTS)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .termid(UPDATED_TERMID)
            .term(UPDATED_TERM)
            .date(UPDATED_DATE)
            .amount(UPDATED_AMOUNT)
            .checkdate(UPDATED_CHECKDATE)
            .checkno(UPDATED_CHECKNO)
            .bank(UPDATED_BANK)
            .customerid(UPDATED_CUSTOMERID)
            .isactive(UPDATED_ISACTIVE)
            .deposited(UPDATED_DEPOSITED)
            .createdby(UPDATED_CREATEDBY);

        restReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedReceipt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedReceipt))
            )
            .andExpect(status().isOk());

        // Validate the Receipt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertReceiptUpdatableFieldsEquals(partialUpdatedReceipt, getPersistedReceipt(partialUpdatedReceipt));
    }

    @Test
    @Transactional
    void patchNonExistingReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        receipt.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, receipt.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(receipt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        receipt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(receipt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamReceipt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        receipt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restReceiptMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(receipt)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Receipt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteReceipt() throws Exception {
        // Initialize the database
        receiptRepository.saveAndFlush(receipt);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the receipt
        restReceiptMockMvc
            .perform(delete(ENTITY_API_URL_ID, receipt.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return receiptRepository.count();
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

    protected Receipt getPersistedReceipt(Receipt receipt) {
        return receiptRepository.findById(receipt.getId()).orElseThrow();
    }

    protected void assertPersistedReceiptToMatchAllProperties(Receipt expectedReceipt) {
        assertReceiptAllPropertiesEquals(expectedReceipt, getPersistedReceipt(expectedReceipt));
    }

    protected void assertPersistedReceiptToMatchUpdatableProperties(Receipt expectedReceipt) {
        assertReceiptAllUpdatablePropertiesEquals(expectedReceipt, getPersistedReceipt(expectedReceipt));
    }
}
