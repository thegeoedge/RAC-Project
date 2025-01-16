package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.VehiclecategoryAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Vehiclecategory;
import com.heavenscode.rac.repository.VehiclecategoryRepository;
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
 * Integration tests for the {@link VehiclecategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VehiclecategoryResourceIT {

    private static final String DEFAULT_VEHICLECATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLECATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLECATEGORYDISCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLECATEGORYDISCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/vehiclecategories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VehiclecategoryRepository vehiclecategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVehiclecategoryMockMvc;

    private Vehiclecategory vehiclecategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiclecategory createEntity(EntityManager em) {
        Vehiclecategory vehiclecategory = new Vehiclecategory()
            .vehiclecategory(DEFAULT_VEHICLECATEGORY)
            .vehiclecategorydiscription(DEFAULT_VEHICLECATEGORYDISCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD);
        return vehiclecategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiclecategory createUpdatedEntity(EntityManager em) {
        Vehiclecategory vehiclecategory = new Vehiclecategory()
            .vehiclecategory(UPDATED_VEHICLECATEGORY)
            .vehiclecategorydiscription(UPDATED_VEHICLECATEGORYDISCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);
        return vehiclecategory;
    }

    @BeforeEach
    public void initTest() {
        vehiclecategory = createEntity(em);
    }

    @Test
    @Transactional
    void createVehiclecategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Vehiclecategory
        var returnedVehiclecategory = om.readValue(
            restVehiclecategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclecategory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Vehiclecategory.class
        );

        // Validate the Vehiclecategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVehiclecategoryUpdatableFieldsEquals(returnedVehiclecategory, getPersistedVehiclecategory(returnedVehiclecategory));
    }

    @Test
    @Transactional
    void createVehiclecategoryWithExistingId() throws Exception {
        // Create the Vehiclecategory with an existing ID
        vehiclecategory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehiclecategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclecategory)))
            .andExpect(status().isBadRequest());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVehiclecategories() throws Exception {
        // Initialize the database
        vehiclecategoryRepository.saveAndFlush(vehiclecategory);

        // Get all the vehiclecategoryList
        restVehiclecategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehiclecategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehiclecategory").value(hasItem(DEFAULT_VEHICLECATEGORY)))
            .andExpect(jsonPath("$.[*].vehiclecategorydiscription").value(hasItem(DEFAULT_VEHICLECATEGORYDISCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getVehiclecategory() throws Exception {
        // Initialize the database
        vehiclecategoryRepository.saveAndFlush(vehiclecategory);

        // Get the vehiclecategory
        restVehiclecategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, vehiclecategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehiclecategory.getId().intValue()))
            .andExpect(jsonPath("$.vehiclecategory").value(DEFAULT_VEHICLECATEGORY))
            .andExpect(jsonPath("$.vehiclecategorydiscription").value(DEFAULT_VEHICLECATEGORYDISCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVehiclecategory() throws Exception {
        // Get the vehiclecategory
        restVehiclecategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVehiclecategory() throws Exception {
        // Initialize the database
        vehiclecategoryRepository.saveAndFlush(vehiclecategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclecategory
        Vehiclecategory updatedVehiclecategory = vehiclecategoryRepository.findById(vehiclecategory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVehiclecategory are not directly saved in db
        em.detach(updatedVehiclecategory);
        updatedVehiclecategory
            .vehiclecategory(UPDATED_VEHICLECATEGORY)
            .vehiclecategorydiscription(UPDATED_VEHICLECATEGORYDISCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restVehiclecategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVehiclecategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVehiclecategory))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVehiclecategoryToMatchAllProperties(updatedVehiclecategory);
    }

    @Test
    @Transactional
    void putNonExistingVehiclecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclecategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiclecategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vehiclecategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehiclecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVehiclecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclecategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehiclecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVehiclecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclecategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclecategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVehiclecategoryWithPatch() throws Exception {
        // Initialize the database
        vehiclecategoryRepository.saveAndFlush(vehiclecategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclecategory using partial update
        Vehiclecategory partialUpdatedVehiclecategory = new Vehiclecategory();
        partialUpdatedVehiclecategory.setId(vehiclecategory.getId());

        partialUpdatedVehiclecategory.lmd(UPDATED_LMD);

        restVehiclecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehiclecategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehiclecategory))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclecategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehiclecategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVehiclecategory, vehiclecategory),
            getPersistedVehiclecategory(vehiclecategory)
        );
    }

    @Test
    @Transactional
    void fullUpdateVehiclecategoryWithPatch() throws Exception {
        // Initialize the database
        vehiclecategoryRepository.saveAndFlush(vehiclecategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclecategory using partial update
        Vehiclecategory partialUpdatedVehiclecategory = new Vehiclecategory();
        partialUpdatedVehiclecategory.setId(vehiclecategory.getId());

        partialUpdatedVehiclecategory
            .vehiclecategory(UPDATED_VEHICLECATEGORY)
            .vehiclecategorydiscription(UPDATED_VEHICLECATEGORYDISCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restVehiclecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehiclecategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehiclecategory))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclecategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehiclecategoryUpdatableFieldsEquals(
            partialUpdatedVehiclecategory,
            getPersistedVehiclecategory(partialUpdatedVehiclecategory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingVehiclecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclecategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiclecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vehiclecategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehiclecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVehiclecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehiclecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVehiclecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclecategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(vehiclecategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehiclecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVehiclecategory() throws Exception {
        // Initialize the database
        vehiclecategoryRepository.saveAndFlush(vehiclecategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the vehiclecategory
        restVehiclecategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, vehiclecategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return vehiclecategoryRepository.count();
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

    protected Vehiclecategory getPersistedVehiclecategory(Vehiclecategory vehiclecategory) {
        return vehiclecategoryRepository.findById(vehiclecategory.getId()).orElseThrow();
    }

    protected void assertPersistedVehiclecategoryToMatchAllProperties(Vehiclecategory expectedVehiclecategory) {
        assertVehiclecategoryAllPropertiesEquals(expectedVehiclecategory, getPersistedVehiclecategory(expectedVehiclecategory));
    }

    protected void assertPersistedVehiclecategoryToMatchUpdatableProperties(Vehiclecategory expectedVehiclecategory) {
        assertVehiclecategoryAllUpdatablePropertiesEquals(expectedVehiclecategory, getPersistedVehiclecategory(expectedVehiclecategory));
    }
}
