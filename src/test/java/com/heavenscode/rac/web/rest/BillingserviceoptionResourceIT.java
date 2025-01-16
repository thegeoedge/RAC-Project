package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.BillingserviceoptionAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Billingserviceoption;
import com.heavenscode.rac.repository.BillingserviceoptionRepository;
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
 * Integration tests for the {@link BillingserviceoptionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BillingserviceoptionResourceIT {

    private static final String DEFAULT_SERVICENAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICENAME = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICEDISCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_SERVICEDISCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Integer DEFAULT_ORDERBY = 1;
    private static final Integer UPDATED_ORDERBY = 2;

    private static final Boolean DEFAULT_BILLTOCUSTOMER = false;
    private static final Boolean UPDATED_BILLTOCUSTOMER = true;

    private static final String ENTITY_API_URL = "/api/billingserviceoptions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private BillingserviceoptionRepository billingserviceoptionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillingserviceoptionMockMvc;

    private Billingserviceoption billingserviceoption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Billingserviceoption createEntity(EntityManager em) {
        Billingserviceoption billingserviceoption = new Billingserviceoption()
            .servicename(DEFAULT_SERVICENAME)
            .servicediscription(DEFAULT_SERVICEDISCRIPTION)
            .isactive(DEFAULT_ISACTIVE)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU)
            .orderby(DEFAULT_ORDERBY)
            .billtocustomer(DEFAULT_BILLTOCUSTOMER);
        return billingserviceoption;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Billingserviceoption createUpdatedEntity(EntityManager em) {
        Billingserviceoption billingserviceoption = new Billingserviceoption()
            .servicename(UPDATED_SERVICENAME)
            .servicediscription(UPDATED_SERVICEDISCRIPTION)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .orderby(UPDATED_ORDERBY)
            .billtocustomer(UPDATED_BILLTOCUSTOMER);
        return billingserviceoption;
    }

    @BeforeEach
    public void initTest() {
        billingserviceoption = createEntity(em);
    }

    @Test
    @Transactional
    void createBillingserviceoption() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Billingserviceoption
        var returnedBillingserviceoption = om.readValue(
            restBillingserviceoptionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billingserviceoption)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Billingserviceoption.class
        );

        // Validate the Billingserviceoption in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertBillingserviceoptionUpdatableFieldsEquals(
            returnedBillingserviceoption,
            getPersistedBillingserviceoption(returnedBillingserviceoption)
        );
    }

    @Test
    @Transactional
    void createBillingserviceoptionWithExistingId() throws Exception {
        // Create the Billingserviceoption with an existing ID
        billingserviceoption.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillingserviceoptionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billingserviceoption)))
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBillingserviceoptions() throws Exception {
        // Initialize the database
        billingserviceoptionRepository.saveAndFlush(billingserviceoption);

        // Get all the billingserviceoptionList
        restBillingserviceoptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(billingserviceoption.getId().intValue())))
            .andExpect(jsonPath("$.[*].servicename").value(hasItem(DEFAULT_SERVICENAME)))
            .andExpect(jsonPath("$.[*].servicediscription").value(hasItem(DEFAULT_SERVICEDISCRIPTION)))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].orderby").value(hasItem(DEFAULT_ORDERBY)))
            .andExpect(jsonPath("$.[*].billtocustomer").value(hasItem(DEFAULT_BILLTOCUSTOMER.booleanValue())));
    }

    @Test
    @Transactional
    void getBillingserviceoption() throws Exception {
        // Initialize the database
        billingserviceoptionRepository.saveAndFlush(billingserviceoption);

        // Get the billingserviceoption
        restBillingserviceoptionMockMvc
            .perform(get(ENTITY_API_URL_ID, billingserviceoption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(billingserviceoption.getId().intValue()))
            .andExpect(jsonPath("$.servicename").value(DEFAULT_SERVICENAME))
            .andExpect(jsonPath("$.servicediscription").value(DEFAULT_SERVICEDISCRIPTION))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.orderby").value(DEFAULT_ORDERBY))
            .andExpect(jsonPath("$.billtocustomer").value(DEFAULT_BILLTOCUSTOMER.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingBillingserviceoption() throws Exception {
        // Get the billingserviceoption
        restBillingserviceoptionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingBillingserviceoption() throws Exception {
        // Initialize the database
        billingserviceoptionRepository.saveAndFlush(billingserviceoption);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billingserviceoption
        Billingserviceoption updatedBillingserviceoption = billingserviceoptionRepository
            .findById(billingserviceoption.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedBillingserviceoption are not directly saved in db
        em.detach(updatedBillingserviceoption);
        updatedBillingserviceoption
            .servicename(UPDATED_SERVICENAME)
            .servicediscription(UPDATED_SERVICEDISCRIPTION)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .orderby(UPDATED_ORDERBY)
            .billtocustomer(UPDATED_BILLTOCUSTOMER);

        restBillingserviceoptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBillingserviceoption.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedBillingserviceoption))
            )
            .andExpect(status().isOk());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedBillingserviceoptionToMatchAllProperties(updatedBillingserviceoption);
    }

    @Test
    @Transactional
    void putNonExistingBillingserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoption.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingserviceoptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, billingserviceoption.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(billingserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBillingserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(billingserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBillingserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(billingserviceoption)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBillingserviceoptionWithPatch() throws Exception {
        // Initialize the database
        billingserviceoptionRepository.saveAndFlush(billingserviceoption);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billingserviceoption using partial update
        Billingserviceoption partialUpdatedBillingserviceoption = new Billingserviceoption();
        partialUpdatedBillingserviceoption.setId(billingserviceoption.getId());

        partialUpdatedBillingserviceoption
            .servicename(UPDATED_SERVICENAME)
            .servicediscription(UPDATED_SERVICEDISCRIPTION)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restBillingserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBillingserviceoption.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBillingserviceoption))
            )
            .andExpect(status().isOk());

        // Validate the Billingserviceoption in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBillingserviceoptionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedBillingserviceoption, billingserviceoption),
            getPersistedBillingserviceoption(billingserviceoption)
        );
    }

    @Test
    @Transactional
    void fullUpdateBillingserviceoptionWithPatch() throws Exception {
        // Initialize the database
        billingserviceoptionRepository.saveAndFlush(billingserviceoption);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the billingserviceoption using partial update
        Billingserviceoption partialUpdatedBillingserviceoption = new Billingserviceoption();
        partialUpdatedBillingserviceoption.setId(billingserviceoption.getId());

        partialUpdatedBillingserviceoption
            .servicename(UPDATED_SERVICENAME)
            .servicediscription(UPDATED_SERVICEDISCRIPTION)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU)
            .orderby(UPDATED_ORDERBY)
            .billtocustomer(UPDATED_BILLTOCUSTOMER);

        restBillingserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBillingserviceoption.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedBillingserviceoption))
            )
            .andExpect(status().isOk());

        // Validate the Billingserviceoption in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertBillingserviceoptionUpdatableFieldsEquals(
            partialUpdatedBillingserviceoption,
            getPersistedBillingserviceoption(partialUpdatedBillingserviceoption)
        );
    }

    @Test
    @Transactional
    void patchNonExistingBillingserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoption.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillingserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, billingserviceoption.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(billingserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBillingserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(billingserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBillingserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        billingserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBillingserviceoptionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(billingserviceoption)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Billingserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBillingserviceoption() throws Exception {
        // Initialize the database
        billingserviceoptionRepository.saveAndFlush(billingserviceoption);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the billingserviceoption
        restBillingserviceoptionMockMvc
            .perform(delete(ENTITY_API_URL_ID, billingserviceoption.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return billingserviceoptionRepository.count();
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

    protected Billingserviceoption getPersistedBillingserviceoption(Billingserviceoption billingserviceoption) {
        return billingserviceoptionRepository.findById(billingserviceoption.getId()).orElseThrow();
    }

    protected void assertPersistedBillingserviceoptionToMatchAllProperties(Billingserviceoption expectedBillingserviceoption) {
        assertBillingserviceoptionAllPropertiesEquals(
            expectedBillingserviceoption,
            getPersistedBillingserviceoption(expectedBillingserviceoption)
        );
    }

    protected void assertPersistedBillingserviceoptionToMatchUpdatableProperties(Billingserviceoption expectedBillingserviceoption) {
        assertBillingserviceoptionAllUpdatablePropertiesEquals(
            expectedBillingserviceoption,
            getPersistedBillingserviceoption(expectedBillingserviceoption)
        );
    }
}
