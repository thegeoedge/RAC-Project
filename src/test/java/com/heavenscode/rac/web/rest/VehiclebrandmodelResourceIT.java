package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.VehiclebrandmodelAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Vehiclebrandmodel;
import com.heavenscode.rac.repository.VehiclebrandmodelRepository;
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
 * Integration tests for the {@link VehiclebrandmodelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VehiclebrandmodelResourceIT {

    private static final Integer DEFAULT_BRANDID = 1;
    private static final Integer UPDATED_BRANDID = 2;

    private static final String DEFAULT_BRANDNAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANDNAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/vehiclebrandmodels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VehiclebrandmodelRepository vehiclebrandmodelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVehiclebrandmodelMockMvc;

    private Vehiclebrandmodel vehiclebrandmodel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiclebrandmodel createEntity(EntityManager em) {
        Vehiclebrandmodel vehiclebrandmodel = new Vehiclebrandmodel()
            .brandid(DEFAULT_BRANDID)
            .brandname(DEFAULT_BRANDNAME)
            .model(DEFAULT_MODEL)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD);
        return vehiclebrandmodel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiclebrandmodel createUpdatedEntity(EntityManager em) {
        Vehiclebrandmodel vehiclebrandmodel = new Vehiclebrandmodel()
            .brandid(UPDATED_BRANDID)
            .brandname(UPDATED_BRANDNAME)
            .model(UPDATED_MODEL)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);
        return vehiclebrandmodel;
    }

    @BeforeEach
    public void initTest() {
        vehiclebrandmodel = createEntity(em);
    }

    @Test
    @Transactional
    void createVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Vehiclebrandmodel
        var returnedVehiclebrandmodel = om.readValue(
            restVehiclebrandmodelMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclebrandmodel)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Vehiclebrandmodel.class
        );

        // Validate the Vehiclebrandmodel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVehiclebrandmodelUpdatableFieldsEquals(returnedVehiclebrandmodel, getPersistedVehiclebrandmodel(returnedVehiclebrandmodel));
    }

    @Test
    @Transactional
    void createVehiclebrandmodelWithExistingId() throws Exception {
        // Create the Vehiclebrandmodel with an existing ID
        vehiclebrandmodel.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehiclebrandmodelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclebrandmodel)))
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVehiclebrandmodels() throws Exception {
        // Initialize the database
        vehiclebrandmodelRepository.saveAndFlush(vehiclebrandmodel);

        // Get all the vehiclebrandmodelList
        restVehiclebrandmodelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehiclebrandmodel.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandid").value(hasItem(DEFAULT_BRANDID)))
            .andExpect(jsonPath("$.[*].brandname").value(hasItem(DEFAULT_BRANDNAME)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getVehiclebrandmodel() throws Exception {
        // Initialize the database
        vehiclebrandmodelRepository.saveAndFlush(vehiclebrandmodel);

        // Get the vehiclebrandmodel
        restVehiclebrandmodelMockMvc
            .perform(get(ENTITY_API_URL_ID, vehiclebrandmodel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehiclebrandmodel.getId().intValue()))
            .andExpect(jsonPath("$.brandid").value(DEFAULT_BRANDID))
            .andExpect(jsonPath("$.brandname").value(DEFAULT_BRANDNAME))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingVehiclebrandmodel() throws Exception {
        // Get the vehiclebrandmodel
        restVehiclebrandmodelMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVehiclebrandmodel() throws Exception {
        // Initialize the database
        vehiclebrandmodelRepository.saveAndFlush(vehiclebrandmodel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclebrandmodel
        Vehiclebrandmodel updatedVehiclebrandmodel = vehiclebrandmodelRepository.findById(vehiclebrandmodel.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVehiclebrandmodel are not directly saved in db
        em.detach(updatedVehiclebrandmodel);
        updatedVehiclebrandmodel
            .brandid(UPDATED_BRANDID)
            .brandname(UPDATED_BRANDNAME)
            .model(UPDATED_MODEL)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restVehiclebrandmodelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVehiclebrandmodel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVehiclebrandmodel))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVehiclebrandmodelToMatchAllProperties(updatedVehiclebrandmodel);
    }

    @Test
    @Transactional
    void putNonExistingVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandmodel.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiclebrandmodelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vehiclebrandmodel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehiclebrandmodel))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandmodel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandmodelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehiclebrandmodel))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandmodel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandmodelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclebrandmodel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVehiclebrandmodelWithPatch() throws Exception {
        // Initialize the database
        vehiclebrandmodelRepository.saveAndFlush(vehiclebrandmodel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclebrandmodel using partial update
        Vehiclebrandmodel partialUpdatedVehiclebrandmodel = new Vehiclebrandmodel();
        partialUpdatedVehiclebrandmodel.setId(vehiclebrandmodel.getId());

        partialUpdatedVehiclebrandmodel.brandid(UPDATED_BRANDID).lmd(UPDATED_LMD);

        restVehiclebrandmodelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehiclebrandmodel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehiclebrandmodel))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclebrandmodel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehiclebrandmodelUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVehiclebrandmodel, vehiclebrandmodel),
            getPersistedVehiclebrandmodel(vehiclebrandmodel)
        );
    }

    @Test
    @Transactional
    void fullUpdateVehiclebrandmodelWithPatch() throws Exception {
        // Initialize the database
        vehiclebrandmodelRepository.saveAndFlush(vehiclebrandmodel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclebrandmodel using partial update
        Vehiclebrandmodel partialUpdatedVehiclebrandmodel = new Vehiclebrandmodel();
        partialUpdatedVehiclebrandmodel.setId(vehiclebrandmodel.getId());

        partialUpdatedVehiclebrandmodel
            .brandid(UPDATED_BRANDID)
            .brandname(UPDATED_BRANDNAME)
            .model(UPDATED_MODEL)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restVehiclebrandmodelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehiclebrandmodel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehiclebrandmodel))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclebrandmodel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehiclebrandmodelUpdatableFieldsEquals(
            partialUpdatedVehiclebrandmodel,
            getPersistedVehiclebrandmodel(partialUpdatedVehiclebrandmodel)
        );
    }

    @Test
    @Transactional
    void patchNonExistingVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandmodel.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiclebrandmodelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vehiclebrandmodel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehiclebrandmodel))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandmodel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandmodelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehiclebrandmodel))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVehiclebrandmodel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandmodel.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandmodelMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(vehiclebrandmodel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehiclebrandmodel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVehiclebrandmodel() throws Exception {
        // Initialize the database
        vehiclebrandmodelRepository.saveAndFlush(vehiclebrandmodel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the vehiclebrandmodel
        restVehiclebrandmodelMockMvc
            .perform(delete(ENTITY_API_URL_ID, vehiclebrandmodel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return vehiclebrandmodelRepository.count();
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

    protected Vehiclebrandmodel getPersistedVehiclebrandmodel(Vehiclebrandmodel vehiclebrandmodel) {
        return vehiclebrandmodelRepository.findById(vehiclebrandmodel.getId()).orElseThrow();
    }

    protected void assertPersistedVehiclebrandmodelToMatchAllProperties(Vehiclebrandmodel expectedVehiclebrandmodel) {
        assertVehiclebrandmodelAllPropertiesEquals(expectedVehiclebrandmodel, getPersistedVehiclebrandmodel(expectedVehiclebrandmodel));
    }

    protected void assertPersistedVehiclebrandmodelToMatchUpdatableProperties(Vehiclebrandmodel expectedVehiclebrandmodel) {
        assertVehiclebrandmodelAllUpdatablePropertiesEquals(
            expectedVehiclebrandmodel,
            getPersistedVehiclebrandmodel(expectedVehiclebrandmodel)
        );
    }
}
