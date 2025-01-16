package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.CompanybankaccountAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Companybankaccount;
import com.heavenscode.rac.repository.CompanybankaccountRepository;
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
 * Integration tests for the {@link CompanybankaccountResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CompanybankaccountResourceIT {

    private static final Integer DEFAULT_COMPANYID = 1;
    private static final Integer UPDATED_COMPANYID = 2;

    private static final String DEFAULT_ACCOUNTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTNAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_BANKNAME = "AAAAAAAAAA";
    private static final String UPDATED_BANKNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BANKID = 1;
    private static final Integer UPDATED_BANKID = 2;

    private static final String DEFAULT_BRANCHNAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_BRANCHID = 1;
    private static final Integer UPDATED_BRANCHID = 2;

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final String DEFAULT_ACCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ACCOUNTID = 1;
    private static final Integer UPDATED_ACCOUNTID = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LMU = "AAAAAAAAAA";
    private static final String UPDATED_LMU = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Integer DEFAULT_ACCOUNTTYPEID = 1;
    private static final Integer UPDATED_ACCOUNTTYPEID = 2;

    private static final String ENTITY_API_URL = "/api/companybankaccounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CompanybankaccountRepository companybankaccountRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCompanybankaccountMockMvc;

    private Companybankaccount companybankaccount;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Companybankaccount createEntity(EntityManager em) {
        Companybankaccount companybankaccount = new Companybankaccount()
            .companyid(DEFAULT_COMPANYID)
            .accountnumber(DEFAULT_ACCOUNTNUMBER)
            .accountname(DEFAULT_ACCOUNTNAME)
            .bankname(DEFAULT_BANKNAME)
            .bankid(DEFAULT_BANKID)
            .branchname(DEFAULT_BRANCHNAME)
            .branchid(DEFAULT_BRANCHID)
            .amount(DEFAULT_AMOUNT)
            .accountcode(DEFAULT_ACCOUNTCODE)
            .accountid(DEFAULT_ACCOUNTID)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU)
            .isactive(DEFAULT_ISACTIVE)
            .accounttypeid(DEFAULT_ACCOUNTTYPEID);
        return companybankaccount;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Companybankaccount createUpdatedEntity(EntityManager em) {
        Companybankaccount companybankaccount = new Companybankaccount()
            .companyid(UPDATED_COMPANYID)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accountname(UPDATED_ACCOUNTNAME)
            .bankname(UPDATED_BANKNAME)
            .bankid(UPDATED_BANKID)
            .branchname(UPDATED_BRANCHNAME)
            .branchid(UPDATED_BRANCHID)
            .amount(UPDATED_AMOUNT)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .isactive(UPDATED_ISACTIVE)
            .accounttypeid(UPDATED_ACCOUNTTYPEID);
        return companybankaccount;
    }

    @BeforeEach
    public void initTest() {
        companybankaccount = createEntity(em);
    }

    @Test
    @Transactional
    void createCompanybankaccount() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Companybankaccount
        var returnedCompanybankaccount = om.readValue(
            restCompanybankaccountMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(companybankaccount)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Companybankaccount.class
        );

        // Validate the Companybankaccount in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCompanybankaccountUpdatableFieldsEquals(
            returnedCompanybankaccount,
            getPersistedCompanybankaccount(returnedCompanybankaccount)
        );
    }

    @Test
    @Transactional
    void createCompanybankaccountWithExistingId() throws Exception {
        // Create the Companybankaccount with an existing ID
        companybankaccount.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCompanybankaccountMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(companybankaccount)))
            .andExpect(status().isBadRequest());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCompanybankaccounts() throws Exception {
        // Initialize the database
        companybankaccountRepository.saveAndFlush(companybankaccount);

        // Get all the companybankaccountList
        restCompanybankaccountMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(companybankaccount.getId().intValue())))
            .andExpect(jsonPath("$.[*].companyid").value(hasItem(DEFAULT_COMPANYID)))
            .andExpect(jsonPath("$.[*].accountnumber").value(hasItem(DEFAULT_ACCOUNTNUMBER)))
            .andExpect(jsonPath("$.[*].accountname").value(hasItem(DEFAULT_ACCOUNTNAME)))
            .andExpect(jsonPath("$.[*].bankname").value(hasItem(DEFAULT_BANKNAME)))
            .andExpect(jsonPath("$.[*].bankid").value(hasItem(DEFAULT_BANKID)))
            .andExpect(jsonPath("$.[*].branchname").value(hasItem(DEFAULT_BRANCHNAME)))
            .andExpect(jsonPath("$.[*].branchid").value(hasItem(DEFAULT_BRANCHID)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE)))
            .andExpect(jsonPath("$.[*].accountid").value(hasItem(DEFAULT_ACCOUNTID)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].accounttypeid").value(hasItem(DEFAULT_ACCOUNTTYPEID)));
    }

    @Test
    @Transactional
    void getCompanybankaccount() throws Exception {
        // Initialize the database
        companybankaccountRepository.saveAndFlush(companybankaccount);

        // Get the companybankaccount
        restCompanybankaccountMockMvc
            .perform(get(ENTITY_API_URL_ID, companybankaccount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(companybankaccount.getId().intValue()))
            .andExpect(jsonPath("$.companyid").value(DEFAULT_COMPANYID))
            .andExpect(jsonPath("$.accountnumber").value(DEFAULT_ACCOUNTNUMBER))
            .andExpect(jsonPath("$.accountname").value(DEFAULT_ACCOUNTNAME))
            .andExpect(jsonPath("$.bankname").value(DEFAULT_BANKNAME))
            .andExpect(jsonPath("$.bankid").value(DEFAULT_BANKID))
            .andExpect(jsonPath("$.branchname").value(DEFAULT_BRANCHNAME))
            .andExpect(jsonPath("$.branchid").value(DEFAULT_BRANCHID))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.accountcode").value(DEFAULT_ACCOUNTCODE))
            .andExpect(jsonPath("$.accountid").value(DEFAULT_ACCOUNTID))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.accounttypeid").value(DEFAULT_ACCOUNTTYPEID));
    }

    @Test
    @Transactional
    void getNonExistingCompanybankaccount() throws Exception {
        // Get the companybankaccount
        restCompanybankaccountMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCompanybankaccount() throws Exception {
        // Initialize the database
        companybankaccountRepository.saveAndFlush(companybankaccount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the companybankaccount
        Companybankaccount updatedCompanybankaccount = companybankaccountRepository.findById(companybankaccount.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCompanybankaccount are not directly saved in db
        em.detach(updatedCompanybankaccount);
        updatedCompanybankaccount
            .companyid(UPDATED_COMPANYID)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accountname(UPDATED_ACCOUNTNAME)
            .bankname(UPDATED_BANKNAME)
            .bankid(UPDATED_BANKID)
            .branchname(UPDATED_BRANCHNAME)
            .branchid(UPDATED_BRANCHID)
            .amount(UPDATED_AMOUNT)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .isactive(UPDATED_ISACTIVE)
            .accounttypeid(UPDATED_ACCOUNTTYPEID);

        restCompanybankaccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCompanybankaccount.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCompanybankaccount))
            )
            .andExpect(status().isOk());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCompanybankaccountToMatchAllProperties(updatedCompanybankaccount);
    }

    @Test
    @Transactional
    void putNonExistingCompanybankaccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        companybankaccount.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanybankaccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, companybankaccount.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(companybankaccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCompanybankaccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        companybankaccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompanybankaccountMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(companybankaccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCompanybankaccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        companybankaccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompanybankaccountMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(companybankaccount)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCompanybankaccountWithPatch() throws Exception {
        // Initialize the database
        companybankaccountRepository.saveAndFlush(companybankaccount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the companybankaccount using partial update
        Companybankaccount partialUpdatedCompanybankaccount = new Companybankaccount();
        partialUpdatedCompanybankaccount.setId(companybankaccount.getId());

        partialUpdatedCompanybankaccount
            .bankid(UPDATED_BANKID)
            .branchid(UPDATED_BRANCHID)
            .amount(UPDATED_AMOUNT)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .accounttypeid(UPDATED_ACCOUNTTYPEID);

        restCompanybankaccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompanybankaccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompanybankaccount))
            )
            .andExpect(status().isOk());

        // Validate the Companybankaccount in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompanybankaccountUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCompanybankaccount, companybankaccount),
            getPersistedCompanybankaccount(companybankaccount)
        );
    }

    @Test
    @Transactional
    void fullUpdateCompanybankaccountWithPatch() throws Exception {
        // Initialize the database
        companybankaccountRepository.saveAndFlush(companybankaccount);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the companybankaccount using partial update
        Companybankaccount partialUpdatedCompanybankaccount = new Companybankaccount();
        partialUpdatedCompanybankaccount.setId(companybankaccount.getId());

        partialUpdatedCompanybankaccount
            .companyid(UPDATED_COMPANYID)
            .accountnumber(UPDATED_ACCOUNTNUMBER)
            .accountname(UPDATED_ACCOUNTNAME)
            .bankname(UPDATED_BANKNAME)
            .bankid(UPDATED_BANKID)
            .branchname(UPDATED_BRANCHNAME)
            .branchid(UPDATED_BRANCHID)
            .amount(UPDATED_AMOUNT)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .isactive(UPDATED_ISACTIVE)
            .accounttypeid(UPDATED_ACCOUNTTYPEID);

        restCompanybankaccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCompanybankaccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCompanybankaccount))
            )
            .andExpect(status().isOk());

        // Validate the Companybankaccount in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCompanybankaccountUpdatableFieldsEquals(
            partialUpdatedCompanybankaccount,
            getPersistedCompanybankaccount(partialUpdatedCompanybankaccount)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCompanybankaccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        companybankaccount.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCompanybankaccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, companybankaccount.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(companybankaccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCompanybankaccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        companybankaccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompanybankaccountMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(companybankaccount))
            )
            .andExpect(status().isBadRequest());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCompanybankaccount() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        companybankaccount.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCompanybankaccountMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(companybankaccount)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Companybankaccount in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCompanybankaccount() throws Exception {
        // Initialize the database
        companybankaccountRepository.saveAndFlush(companybankaccount);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the companybankaccount
        restCompanybankaccountMockMvc
            .perform(delete(ENTITY_API_URL_ID, companybankaccount.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return companybankaccountRepository.count();
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

    protected Companybankaccount getPersistedCompanybankaccount(Companybankaccount companybankaccount) {
        return companybankaccountRepository.findById(companybankaccount.getId()).orElseThrow();
    }

    protected void assertPersistedCompanybankaccountToMatchAllProperties(Companybankaccount expectedCompanybankaccount) {
        assertCompanybankaccountAllPropertiesEquals(expectedCompanybankaccount, getPersistedCompanybankaccount(expectedCompanybankaccount));
    }

    protected void assertPersistedCompanybankaccountToMatchUpdatableProperties(Companybankaccount expectedCompanybankaccount) {
        assertCompanybankaccountAllUpdatablePropertiesEquals(
            expectedCompanybankaccount,
            getPersistedCompanybankaccount(expectedCompanybankaccount)
        );
    }
}
