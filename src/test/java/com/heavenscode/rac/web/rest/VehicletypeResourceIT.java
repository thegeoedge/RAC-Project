package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.VehicletypeAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Vehicletype;
import com.heavenscode.rac.repository.VehicletypeRepository;
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
 * Integration tests for the {@link VehicletypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VehicletypeResourceIT {

    private static final String DEFAULT_VEHICLETYPE = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLETYPEDISCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLETYPEDISCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_CATID = 1;
    private static final Integer UPDATED_CATID = 2;

    private static final String DEFAULT_CATNAME = "AAAAAAAAAA";
    private static final String UPDATED_CATNAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/vehicletypes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VehicletypeRepository vehicletypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVehicletypeMockMvc;

    private Vehicletype vehicletype;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehicletype createEntity(EntityManager em) {
        Vehicletype vehicletype = new Vehicletype()
            .vehicletype(DEFAULT_VEHICLETYPE)
            .vehicletypediscription(DEFAULT_VEHICLETYPEDISCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .catid(DEFAULT_CATID)
            .catname(DEFAULT_CATNAME);
        return vehicletype;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehicletype createUpdatedEntity(EntityManager em) {
        Vehicletype vehicletype = new Vehicletype()
            .vehicletype(UPDATED_VEHICLETYPE)
            .vehicletypediscription(UPDATED_VEHICLETYPEDISCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .catid(UPDATED_CATID)
            .catname(UPDATED_CATNAME);
        return vehicletype;
    }

    @BeforeEach
    public void initTest() {
        vehicletype = createEntity(em);
    }

    @Test
    @Transactional
    void createVehicletype() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Vehicletype
        var returnedVehicletype = om.readValue(
            restVehicletypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehicletype)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Vehicletype.class
        );

        // Validate the Vehicletype in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVehicletypeUpdatableFieldsEquals(returnedVehicletype, getPersistedVehicletype(returnedVehicletype));
    }

    @Test
    @Transactional
    void createVehicletypeWithExistingId() throws Exception {
        // Create the Vehicletype with an existing ID
        vehicletype.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehicletypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehicletype)))
            .andExpect(status().isBadRequest());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVehicletypes() throws Exception {
        // Initialize the database
        vehicletypeRepository.saveAndFlush(vehicletype);

        // Get all the vehicletypeList
        restVehicletypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehicletype.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehicletype").value(hasItem(DEFAULT_VEHICLETYPE)))
            .andExpect(jsonPath("$.[*].vehicletypediscription").value(hasItem(DEFAULT_VEHICLETYPEDISCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].catid").value(hasItem(DEFAULT_CATID)))
            .andExpect(jsonPath("$.[*].catname").value(hasItem(DEFAULT_CATNAME)));
    }

    @Test
    @Transactional
    void getVehicletype() throws Exception {
        // Initialize the database
        vehicletypeRepository.saveAndFlush(vehicletype);

        // Get the vehicletype
        restVehicletypeMockMvc
            .perform(get(ENTITY_API_URL_ID, vehicletype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehicletype.getId().intValue()))
            .andExpect(jsonPath("$.vehicletype").value(DEFAULT_VEHICLETYPE))
            .andExpect(jsonPath("$.vehicletypediscription").value(DEFAULT_VEHICLETYPEDISCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.catid").value(DEFAULT_CATID))
            .andExpect(jsonPath("$.catname").value(DEFAULT_CATNAME));
    }

    @Test
    @Transactional
    void getNonExistingVehicletype() throws Exception {
        // Get the vehicletype
        restVehicletypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVehicletype() throws Exception {
        // Initialize the database
        vehicletypeRepository.saveAndFlush(vehicletype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehicletype
        Vehicletype updatedVehicletype = vehicletypeRepository.findById(vehicletype.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVehicletype are not directly saved in db
        em.detach(updatedVehicletype);
        updatedVehicletype
            .vehicletype(UPDATED_VEHICLETYPE)
            .vehicletypediscription(UPDATED_VEHICLETYPEDISCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .catid(UPDATED_CATID)
            .catname(UPDATED_CATNAME);

        restVehicletypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVehicletype.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVehicletype))
            )
            .andExpect(status().isOk());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVehicletypeToMatchAllProperties(updatedVehicletype);
    }

    @Test
    @Transactional
    void putNonExistingVehicletype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehicletype.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicletypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vehicletype.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehicletype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVehicletype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehicletype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehicletypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehicletype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVehicletype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehicletype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehicletypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehicletype)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVehicletypeWithPatch() throws Exception {
        // Initialize the database
        vehicletypeRepository.saveAndFlush(vehicletype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehicletype using partial update
        Vehicletype partialUpdatedVehicletype = new Vehicletype();
        partialUpdatedVehicletype.setId(vehicletype.getId());

        partialUpdatedVehicletype.lmu(UPDATED_LMU);

        restVehicletypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehicletype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehicletype))
            )
            .andExpect(status().isOk());

        // Validate the Vehicletype in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehicletypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVehicletype, vehicletype),
            getPersistedVehicletype(vehicletype)
        );
    }

    @Test
    @Transactional
    void fullUpdateVehicletypeWithPatch() throws Exception {
        // Initialize the database
        vehicletypeRepository.saveAndFlush(vehicletype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehicletype using partial update
        Vehicletype partialUpdatedVehicletype = new Vehicletype();
        partialUpdatedVehicletype.setId(vehicletype.getId());

        partialUpdatedVehicletype
            .vehicletype(UPDATED_VEHICLETYPE)
            .vehicletypediscription(UPDATED_VEHICLETYPEDISCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .catid(UPDATED_CATID)
            .catname(UPDATED_CATNAME);

        restVehicletypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehicletype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehicletype))
            )
            .andExpect(status().isOk());

        // Validate the Vehicletype in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehicletypeUpdatableFieldsEquals(partialUpdatedVehicletype, getPersistedVehicletype(partialUpdatedVehicletype));
    }

    @Test
    @Transactional
    void patchNonExistingVehicletype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehicletype.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehicletypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vehicletype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehicletype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVehicletype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehicletype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehicletypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehicletype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVehicletype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehicletype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehicletypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(vehicletype)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehicletype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVehicletype() throws Exception {
        // Initialize the database
        vehicletypeRepository.saveAndFlush(vehicletype);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the vehicletype
        restVehicletypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, vehicletype.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return vehicletypeRepository.count();
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

    protected Vehicletype getPersistedVehicletype(Vehicletype vehicletype) {
        return vehicletypeRepository.findById(vehicletype.getId()).orElseThrow();
    }

    protected void assertPersistedVehicletypeToMatchAllProperties(Vehicletype expectedVehicletype) {
        assertVehicletypeAllPropertiesEquals(expectedVehicletype, getPersistedVehicletype(expectedVehicletype));
    }

    protected void assertPersistedVehicletypeToMatchUpdatableProperties(Vehicletype expectedVehicletype) {
        assertVehicletypeAllUpdatablePropertiesEquals(expectedVehicletype, getPersistedVehicletype(expectedVehicletype));
    }
}
