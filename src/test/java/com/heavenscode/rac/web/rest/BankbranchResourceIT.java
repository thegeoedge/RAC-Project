package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.BankbranchAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Bankbranch;
import com.heavenscode.rac.repository.BankbranchRepository;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link BankbranchResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BankbranchResourceIT {

    private static final String DEFAULT_BANKCODE = "AAAAAAAAAA";
    private static final String UPDATED_BANKCODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCHCODE = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHCODE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCHNAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANCHNAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bankbranches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BankbranchRepository bankbranchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBankbranchMockMvc;

    private Bankbranch bankbranch;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bankbranch createEntity(EntityManager em) {
        Bankbranch bankbranch = new Bankbranch().bankcode(DEFAULT_BANKCODE).branchcode(DEFAULT_BRANCHCODE).branchname(DEFAULT_BRANCHNAME);
        return bankbranch;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bankbranch createUpdatedEntity(EntityManager em) {
        Bankbranch bankbranch = new Bankbranch().bankcode(UPDATED_BANKCODE).branchcode(UPDATED_BRANCHCODE).branchname(UPDATED_BRANCHNAME);
        return bankbranch;
    }

    @BeforeEach
    public void initTest() {
        bankbranch = createEntity(em);
    }

    @Test
    @Transactional
    void createBankbranch() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Bankbranch
        var returnedBankbranch = om.readValue(
            restBankbranchMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankbranch)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Bankbranch.class
        );

        // Validate the Bankbranch in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBankbranchUpdatableFieldsEquals(returnedBankbranch, getPersistedBankbranch(returnedBankbranch));
    }

    @Test
    @Transactional
    void createBankbranchWithExistingId() throws Exception {
        // Create the Bankbranch with an existing ID
        bankbranch.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBankbranchMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankbranch)))
            .andExpect(status().isBadRequest());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBankbranches() throws Exception {
        // Initialize the database
        bankbranchRepository.saveAndFlush(bankbranch);

        // Get all the bankbranchList
        restBankbranchMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bankbranch.getId().intValue())))
            .andExpect(jsonPath("$.[*].bankcode").value(hasItem(DEFAULT_BANKCODE)))
            .andExpect(jsonPath("$.[*].branchcode").value(hasItem(DEFAULT_BRANCHCODE)))
            .andExpect(jsonPath("$.[*].branchname").value(hasItem(DEFAULT_BRANCHNAME)));
    }

    @Test
    @Transactional
    void getBankbranch() throws Exception {
        // Initialize the database
        bankbranchRepository.saveAndFlush(bankbranch);

        // Get the bankbranch
        restBankbranchMockMvc
            .perform(get(ENTITY_API_URL_ID, bankbranch.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bankbranch.getId().intValue()))
            .andExpect(jsonPath("$.bankcode").value(DEFAULT_BANKCODE))
            .andExpect(jsonPath("$.branchcode").value(DEFAULT_BRANCHCODE))
            .andExpect(jsonPath("$.branchname").value(DEFAULT_BRANCHNAME));
    }

    @Test
    @Transactional
    void getNonExistingBankbranch() throws Exception {
        // Get the bankbranch
        restBankbranchMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBankbranch() throws Exception {
        // Initialize the database
        bankbranchRepository.saveAndFlush(bankbranch);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bankbranch
        Bankbranch updatedBankbranch = bankbranchRepository.findById(bankbranch.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBankbranch are not directly saved in db
        em.detach(updatedBankbranch);
        updatedBankbranch.bankcode(UPDATED_BANKCODE).branchcode(UPDATED_BRANCHCODE).branchname(UPDATED_BRANCHNAME);

        restBankbranchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBankbranch.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBankbranch))
            )
            .andExpect(status().isOk());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBankbranchToMatchAllProperties(updatedBankbranch);
    }

    @Test
    @Transactional
    void putNonExistingBankbranch() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankbranch.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankbranchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bankbranch.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankbranch))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBankbranch() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankbranch.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankbranchMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(bankbranch))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBankbranch() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankbranch.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankbranchMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(bankbranch)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBankbranchWithPatch() throws Exception {
        // Initialize the database
        bankbranchRepository.saveAndFlush(bankbranch);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bankbranch using partial update
        Bankbranch partialUpdatedBankbranch = new Bankbranch();
        partialUpdatedBankbranch.setId(bankbranch.getId());

        partialUpdatedBankbranch.branchcode(UPDATED_BRANCHCODE).branchname(UPDATED_BRANCHNAME);

        restBankbranchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankbranch.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBankbranch))
            )
            .andExpect(status().isOk());

        // Validate the Bankbranch in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBankbranchUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBankbranch, bankbranch),
            getPersistedBankbranch(bankbranch)
        );
    }

    @Test
    @Transactional
    void fullUpdateBankbranchWithPatch() throws Exception {
        // Initialize the database
        bankbranchRepository.saveAndFlush(bankbranch);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the bankbranch using partial update
        Bankbranch partialUpdatedBankbranch = new Bankbranch();
        partialUpdatedBankbranch.setId(bankbranch.getId());

        partialUpdatedBankbranch.bankcode(UPDATED_BANKCODE).branchcode(UPDATED_BRANCHCODE).branchname(UPDATED_BRANCHNAME);

        restBankbranchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBankbranch.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBankbranch))
            )
            .andExpect(status().isOk());

        // Validate the Bankbranch in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBankbranchUpdatableFieldsEquals(partialUpdatedBankbranch, getPersistedBankbranch(partialUpdatedBankbranch));
    }

    @Test
    @Transactional
    void patchNonExistingBankbranch() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankbranch.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBankbranchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bankbranch.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bankbranch))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBankbranch() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankbranch.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankbranchMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(bankbranch))
            )
            .andExpect(status().isBadRequest());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBankbranch() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        bankbranch.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBankbranchMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(bankbranch)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Bankbranch in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBankbranch() throws Exception {
        // Initialize the database
        bankbranchRepository.saveAndFlush(bankbranch);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the bankbranch
        restBankbranchMockMvc
            .perform(delete(ENTITY_API_URL_ID, bankbranch.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return bankbranchRepository.count();
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

    protected Bankbranch getPersistedBankbranch(Bankbranch bankbranch) {
        return bankbranchRepository.findById(bankbranch.getId()).orElseThrow();
    }

    protected void assertPersistedBankbranchToMatchAllProperties(Bankbranch expectedBankbranch) {
        assertBankbranchAllPropertiesEquals(expectedBankbranch, getPersistedBankbranch(expectedBankbranch));
    }

    protected void assertPersistedBankbranchToMatchUpdatableProperties(Bankbranch expectedBankbranch) {
        assertBankbranchAllUpdatablePropertiesEquals(expectedBankbranch, getPersistedBankbranch(expectedBankbranch));
    }
}
