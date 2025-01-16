package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.BillingserviceoptionvaluesAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Billingserviceoptionvalues;
import com.heavenscode.rac.repository.BillingserviceoptionvaluesRepository;
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
 * Integration tests for the {@link BillingserviceoptionvaluesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BillingserviceoptionvaluesResourceIT {

    private static final Long DEFAULT_VEHICLETYPEID = 1L;
    private static final Long UPDATED_VEHICLETYPEID = 2L;

    private static final Long DEFAULT_BILLINGSERVICEOPTIONID = 1L;
    private static final Long UPDATED_BILLINGSERVICEOPTIONID = 2L;

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final String ENTITY_API_URL = "/api/billingserviceoptionvalues";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BillingserviceoptionvaluesRepository billingserviceoptionvaluesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingserviceoptionvaluesMockMvc;

    private Billingserviceoptionvalues billingserviceoptionvalues;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Billingserviceoptionvalues createEntity(EntityManager em) {
        Billingserviceoptionvalues billingserviceoptionvalues = new Billingserviceoptionvalues()
            .vehicletypeid(DEFAULT_VEHICLETYPEID)
            .billingserviceoptionid(DEFAULT_BILLINGSERVICEOPTIONID)
            .value(DEFAULT_VALUE)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU);
        return billingserviceoptionvalues;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Billingserviceoptionvalues createUpdatedEntity(EntityManager em) {
        Billingserviceoptionvalues billingserviceoptionvalues = new Billingserviceoptionvalues()
            .vehicletypeid(UPDATED_VEHICLETYPEID)
            .billingserviceoptionid(UPDATED_BILLINGSERVICEOPTIONID)
            .value(UPDATED_VALUE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);
        return billingserviceoptionvalues;
    }

    @BeforeEach
    public void initTest() {
        billingserviceoptionvalues = createEntity(em);
    }

    @Test
    @Transactional
    void createBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Billingserviceoptionvalues
        var returnedBillingserviceoptionvalues = om.readValue(
            restBillingserviceoptionvaluesMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billingserviceoptionvalues))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Billingserviceoptionvalues.class
        );

        // Validate the Billingserviceoptionvalues in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBillingserviceoptionvaluesUpdatableFieldsEquals(
            returnedBillingserviceoptionvalues,
            getPersistedBillingserviceoptionvalues(returnedBillingserviceoptionvalues)
        );
    }

    @Test
    @Transactional
    void createBillingserviceoptionvaluesWithExistingId() throws Exception {
        // Create the Billingserviceoptionvalues with an existing ID
        billingserviceoptionvalues.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingserviceoptionvaluesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billingserviceoptionvalues)))
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBillingserviceoptionvalues() throws Exception {
        // Initialize the database
        billingserviceoptionvaluesRepository.saveAndFlush(billingserviceoptionvalues);

        // Get all the billingserviceoptionvaluesList
        restBillingserviceoptionvaluesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingserviceoptionvalues.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicletypeid").value(hasItem(DEFAULT_VEHICLETYPEID.intValue())))
            .andExpect(jsonPath("$.[*].billingserviceoptionid").value(hasItem(DEFAULT_BILLINGSERVICEOPTIONID.intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)));
    }

    @Test
    @Transactional
    void getBillingserviceoptionvalues() throws Exception {
        // Initialize the database
        billingserviceoptionvaluesRepository.saveAndFlush(billingserviceoptionvalues);

        // Get the billingserviceoptionvalues
        restBillingserviceoptionvaluesMockMvc
            .perform(get(ENTITY_API_URL_ID, billingserviceoptionvalues.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingserviceoptionvalues.getId().intValue()))
            .andExpect(jsonPath("$.vehicletypeid").value(DEFAULT_VEHICLETYPEID.intValue()))
            .andExpect(jsonPath("$.billingserviceoptionid").value(DEFAULT_BILLINGSERVICEOPTIONID.intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU));
    }

    @Test
    @Transactional
    void getNonExistingBillingserviceoptionvalues() throws Exception {
        // Get the billingserviceoptionvalues
        restBillingserviceoptionvaluesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBillingserviceoptionvalues() throws Exception {
        // Initialize the database
        billingserviceoptionvaluesRepository.saveAndFlush(billingserviceoptionvalues);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billingserviceoptionvalues
        Billingserviceoptionvalues updatedBillingserviceoptionvalues = billingserviceoptionvaluesRepository
            .findById(billingserviceoptionvalues.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedBillingserviceoptionvalues are not directly saved in db
        em.detach(updatedBillingserviceoptionvalues);
        updatedBillingserviceoptionvalues
            .vehicletypeid(UPDATED_VEHICLETYPEID)
            .billingserviceoptionid(UPDATED_BILLINGSERVICEOPTIONID)
            .value(UPDATED_VALUE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restBillingserviceoptionvaluesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBillingserviceoptionvalues.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBillingserviceoptionvalues))
            )
            .andExpect(status().isOk());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBillingserviceoptionvaluesToMatchAllProperties(updatedBillingserviceoptionvalues);
    }

    @Test
    @Transactional
    void putNonExistingBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoptionvalues.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingserviceoptionvaluesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, billingserviceoptionvalues.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(billingserviceoptionvalues))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoptionvalues.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionvaluesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(billingserviceoptionvalues))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoptionvalues.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionvaluesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billingserviceoptionvalues)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBillingserviceoptionvaluesWithPatch() throws Exception {
        // Initialize the database
        billingserviceoptionvaluesRepository.saveAndFlush(billingserviceoptionvalues);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billingserviceoptionvalues using partial update
        Billingserviceoptionvalues partialUpdatedBillingserviceoptionvalues = new Billingserviceoptionvalues();
        partialUpdatedBillingserviceoptionvalues.setId(billingserviceoptionvalues.getId());

        partialUpdatedBillingserviceoptionvalues.lmd(UPDATED_LMD).lmu(UPDATED_LMU);

        restBillingserviceoptionvaluesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBillingserviceoptionvalues.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBillingserviceoptionvalues))
            )
            .andExpect(status().isOk());

        // Validate the Billingserviceoptionvalues in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBillingserviceoptionvaluesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBillingserviceoptionvalues, billingserviceoptionvalues),
            getPersistedBillingserviceoptionvalues(billingserviceoptionvalues)
        );
    }

    @Test
    @Transactional
    void fullUpdateBillingserviceoptionvaluesWithPatch() throws Exception {
        // Initialize the database
        billingserviceoptionvaluesRepository.saveAndFlush(billingserviceoptionvalues);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billingserviceoptionvalues using partial update
        Billingserviceoptionvalues partialUpdatedBillingserviceoptionvalues = new Billingserviceoptionvalues();
        partialUpdatedBillingserviceoptionvalues.setId(billingserviceoptionvalues.getId());

        partialUpdatedBillingserviceoptionvalues
            .vehicletypeid(UPDATED_VEHICLETYPEID)
            .billingserviceoptionid(UPDATED_BILLINGSERVICEOPTIONID)
            .value(UPDATED_VALUE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restBillingserviceoptionvaluesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBillingserviceoptionvalues.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBillingserviceoptionvalues))
            )
            .andExpect(status().isOk());

        // Validate the Billingserviceoptionvalues in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBillingserviceoptionvaluesUpdatableFieldsEquals(
            partialUpdatedBillingserviceoptionvalues,
            getPersistedBillingserviceoptionvalues(partialUpdatedBillingserviceoptionvalues)
        );
    }

    @Test
    @Transactional
    void patchNonExistingBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoptionvalues.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingserviceoptionvaluesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, billingserviceoptionvalues.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(billingserviceoptionvalues))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoptionvalues.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionvaluesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(billingserviceoptionvalues))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBillingserviceoptionvalues() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoptionvalues.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionvaluesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(billingserviceoptionvalues))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Billingserviceoptionvalues in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBillingserviceoptionvalues() throws Exception {
        // Initialize the database
        billingserviceoptionvaluesRepository.saveAndFlush(billingserviceoptionvalues);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the billingserviceoptionvalues
        restBillingserviceoptionvaluesMockMvc
            .perform(delete(ENTITY_API_URL_ID, billingserviceoptionvalues.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return billingserviceoptionvaluesRepository.count();
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

    protected Billingserviceoptionvalues getPersistedBillingserviceoptionvalues(Billingserviceoptionvalues billingserviceoptionvalues) {
        return billingserviceoptionvaluesRepository.findById(billingserviceoptionvalues.getId()).orElseThrow();
    }

    protected void assertPersistedBillingserviceoptionvaluesToMatchAllProperties(
        Billingserviceoptionvalues expectedBillingserviceoptionvalues
    ) {
        assertBillingserviceoptionvaluesAllPropertiesEquals(
            expectedBillingserviceoptionvalues,
            getPersistedBillingserviceoptionvalues(expectedBillingserviceoptionvalues)
        );
    }

    protected void assertPersistedBillingserviceoptionvaluesToMatchUpdatableProperties(
        Billingserviceoptionvalues expectedBillingserviceoptionvalues
    ) {
        assertBillingserviceoptionvaluesAllUpdatablePropertiesEquals(
            expectedBillingserviceoptionvalues,
            getPersistedBillingserviceoptionvalues(expectedBillingserviceoptionvalues)
        );
    }
}
