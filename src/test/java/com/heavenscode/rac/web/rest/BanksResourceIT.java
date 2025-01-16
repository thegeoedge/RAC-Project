package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.BanksAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Banks;
import com.heavenscode.rac.repository.BanksRepository;
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
 * Integration tests for the {@link BanksResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BanksResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/banks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BanksRepository banksRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBanksMockMvc;

    private Banks banks;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banks createEntity(EntityManager em) {
        Banks banks = new Banks().code(DEFAULT_CODE).name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION).lmu(DEFAULT_LMU).lmd(DEFAULT_LMD);
        return banks;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Banks createUpdatedEntity(EntityManager em) {
        Banks banks = new Banks().code(UPDATED_CODE).name(UPDATED_NAME).description(UPDATED_DESCRIPTION).lmu(UPDATED_LMU).lmd(UPDATED_LMD);
        return banks;
    }

    @BeforeEach
    public void initTest() {
        banks = createEntity(em);
    }

    @Test
    @Transactional
    void createBanks() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Banks
        var returnedBanks = om.readValue(
            restBanksMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(banks)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Banks.class
        );

        // Validate the Banks in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBanksUpdatableFieldsEquals(returnedBanks, getPersistedBanks(returnedBanks));
    }

    @Test
    @Transactional
    void createBanksWithExistingId() throws Exception {
        // Create the Banks with an existing ID
        banks.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBanksMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(banks)))
            .andExpect(status().isBadRequest());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBanks() throws Exception {
        // Initialize the database
        banksRepository.saveAndFlush(banks);

        // Get all the banksList
        restBanksMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(banks.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getBanks() throws Exception {
        // Initialize the database
        banksRepository.saveAndFlush(banks);

        // Get the banks
        restBanksMockMvc
            .perform(get(ENTITY_API_URL_ID, banks.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(banks.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingBanks() throws Exception {
        // Get the banks
        restBanksMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBanks() throws Exception {
        // Initialize the database
        banksRepository.saveAndFlush(banks);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the banks
        Banks updatedBanks = banksRepository.findById(banks.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedBanks are not directly saved in db
        em.detach(updatedBanks);
        updatedBanks.code(UPDATED_CODE).name(UPDATED_NAME).description(UPDATED_DESCRIPTION).lmu(UPDATED_LMU).lmd(UPDATED_LMD);

        restBanksMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBanks.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBanks))
            )
            .andExpect(status().isOk());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBanksToMatchAllProperties(updatedBanks);
    }

    @Test
    @Transactional
    void putNonExistingBanks() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        banks.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBanksMockMvc
            .perform(put(ENTITY_API_URL_ID, banks.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(banks)))
            .andExpect(status().isBadRequest());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBanks() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        banks.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBanksMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(banks))
            )
            .andExpect(status().isBadRequest());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBanks() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        banks.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBanksMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(banks)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBanksWithPatch() throws Exception {
        // Initialize the database
        banksRepository.saveAndFlush(banks);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the banks using partial update
        Banks partialUpdatedBanks = new Banks();
        partialUpdatedBanks.setId(banks.getId());

        partialUpdatedBanks.description(UPDATED_DESCRIPTION).lmu(UPDATED_LMU).lmd(UPDATED_LMD);

        restBanksMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBanks.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBanks))
            )
            .andExpect(status().isOk());

        // Validate the Banks in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBanksUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedBanks, banks), getPersistedBanks(banks));
    }

    @Test
    @Transactional
    void fullUpdateBanksWithPatch() throws Exception {
        // Initialize the database
        banksRepository.saveAndFlush(banks);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the banks using partial update
        Banks partialUpdatedBanks = new Banks();
        partialUpdatedBanks.setId(banks.getId());

        partialUpdatedBanks.code(UPDATED_CODE).name(UPDATED_NAME).description(UPDATED_DESCRIPTION).lmu(UPDATED_LMU).lmd(UPDATED_LMD);

        restBanksMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBanks.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBanks))
            )
            .andExpect(status().isOk());

        // Validate the Banks in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBanksUpdatableFieldsEquals(partialUpdatedBanks, getPersistedBanks(partialUpdatedBanks));
    }

    @Test
    @Transactional
    void patchNonExistingBanks() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        banks.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBanksMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, banks.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(banks))
            )
            .andExpect(status().isBadRequest());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBanks() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        banks.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBanksMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(banks))
            )
            .andExpect(status().isBadRequest());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBanks() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        banks.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBanksMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(banks)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Banks in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBanks() throws Exception {
        // Initialize the database
        banksRepository.saveAndFlush(banks);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the banks
        restBanksMockMvc
            .perform(delete(ENTITY_API_URL_ID, banks.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return banksRepository.count();
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

    protected Banks getPersistedBanks(Banks banks) {
        return banksRepository.findById(banks.getId()).orElseThrow();
    }

    protected void assertPersistedBanksToMatchAllProperties(Banks expectedBanks) {
        assertBanksAllPropertiesEquals(expectedBanks, getPersistedBanks(expectedBanks));
    }

    protected void assertPersistedBanksToMatchUpdatableProperties(Banks expectedBanks) {
        assertBanksAllUpdatablePropertiesEquals(expectedBanks, getPersistedBanks(expectedBanks));
    }
}
