package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.VehiclebrandnameAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Vehiclebrandname;
import com.heavenscode.rac.repository.VehiclebrandnameRepository;
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
 * Integration tests for the {@link VehiclebrandnameResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class VehiclebrandnameResourceIT {

    private static final String DEFAULT_BRANDNAME = "AAAAAAAAAA";
    private static final String UPDATED_BRANDNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_COMPANYID = 1;
    private static final Integer UPDATED_COMPANYID = 2;

    private static final String ENTITY_API_URL = "/api/vehiclebrandnames";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private VehiclebrandnameRepository vehiclebrandnameRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVehiclebrandnameMockMvc;

    private Vehiclebrandname vehiclebrandname;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiclebrandname createEntity(EntityManager em) {
        Vehiclebrandname vehiclebrandname = new Vehiclebrandname()
            .brandname(DEFAULT_BRANDNAME)
            .description(DEFAULT_DESCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .companyid(DEFAULT_COMPANYID);
        return vehiclebrandname;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vehiclebrandname createUpdatedEntity(EntityManager em) {
        Vehiclebrandname vehiclebrandname = new Vehiclebrandname()
            .brandname(UPDATED_BRANDNAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .companyid(UPDATED_COMPANYID);
        return vehiclebrandname;
    }

    @BeforeEach
    public void initTest() {
        vehiclebrandname = createEntity(em);
    }

    @Test
    @Transactional
    void createVehiclebrandname() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Vehiclebrandname
        var returnedVehiclebrandname = om.readValue(
            restVehiclebrandnameMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclebrandname)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Vehiclebrandname.class
        );

        // Validate the Vehiclebrandname in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertVehiclebrandnameUpdatableFieldsEquals(returnedVehiclebrandname, getPersistedVehiclebrandname(returnedVehiclebrandname));
    }

    @Test
    @Transactional
    void createVehiclebrandnameWithExistingId() throws Exception {
        // Create the Vehiclebrandname with an existing ID
        vehiclebrandname.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restVehiclebrandnameMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclebrandname)))
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllVehiclebrandnames() throws Exception {
        // Initialize the database
        vehiclebrandnameRepository.saveAndFlush(vehiclebrandname);

        // Get all the vehiclebrandnameList
        restVehiclebrandnameMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vehiclebrandname.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandname").value(hasItem(DEFAULT_BRANDNAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].companyid").value(hasItem(DEFAULT_COMPANYID)));
    }

    @Test
    @Transactional
    void getVehiclebrandname() throws Exception {
        // Initialize the database
        vehiclebrandnameRepository.saveAndFlush(vehiclebrandname);

        // Get the vehiclebrandname
        restVehiclebrandnameMockMvc
            .perform(get(ENTITY_API_URL_ID, vehiclebrandname.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vehiclebrandname.getId().intValue()))
            .andExpect(jsonPath("$.brandname").value(DEFAULT_BRANDNAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.companyid").value(DEFAULT_COMPANYID));
    }

    @Test
    @Transactional
    void getNonExistingVehiclebrandname() throws Exception {
        // Get the vehiclebrandname
        restVehiclebrandnameMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingVehiclebrandname() throws Exception {
        // Initialize the database
        vehiclebrandnameRepository.saveAndFlush(vehiclebrandname);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclebrandname
        Vehiclebrandname updatedVehiclebrandname = vehiclebrandnameRepository.findById(vehiclebrandname.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedVehiclebrandname are not directly saved in db
        em.detach(updatedVehiclebrandname);
        updatedVehiclebrandname
            .brandname(UPDATED_BRANDNAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .companyid(UPDATED_COMPANYID);

        restVehiclebrandnameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedVehiclebrandname.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedVehiclebrandname))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedVehiclebrandnameToMatchAllProperties(updatedVehiclebrandname);
    }

    @Test
    @Transactional
    void putNonExistingVehiclebrandname() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandname.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiclebrandnameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, vehiclebrandname.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehiclebrandname))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchVehiclebrandname() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandname.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandnameMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(vehiclebrandname))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamVehiclebrandname() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandname.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandnameMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(vehiclebrandname)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateVehiclebrandnameWithPatch() throws Exception {
        // Initialize the database
        vehiclebrandnameRepository.saveAndFlush(vehiclebrandname);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclebrandname using partial update
        Vehiclebrandname partialUpdatedVehiclebrandname = new Vehiclebrandname();
        partialUpdatedVehiclebrandname.setId(vehiclebrandname.getId());

        partialUpdatedVehiclebrandname.lmu(UPDATED_LMU).companyid(UPDATED_COMPANYID);

        restVehiclebrandnameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehiclebrandname.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehiclebrandname))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclebrandname in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehiclebrandnameUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedVehiclebrandname, vehiclebrandname),
            getPersistedVehiclebrandname(vehiclebrandname)
        );
    }

    @Test
    @Transactional
    void fullUpdateVehiclebrandnameWithPatch() throws Exception {
        // Initialize the database
        vehiclebrandnameRepository.saveAndFlush(vehiclebrandname);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the vehiclebrandname using partial update
        Vehiclebrandname partialUpdatedVehiclebrandname = new Vehiclebrandname();
        partialUpdatedVehiclebrandname.setId(vehiclebrandname.getId());

        partialUpdatedVehiclebrandname
            .brandname(UPDATED_BRANDNAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .companyid(UPDATED_COMPANYID);

        restVehiclebrandnameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedVehiclebrandname.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedVehiclebrandname))
            )
            .andExpect(status().isOk());

        // Validate the Vehiclebrandname in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertVehiclebrandnameUpdatableFieldsEquals(
            partialUpdatedVehiclebrandname,
            getPersistedVehiclebrandname(partialUpdatedVehiclebrandname)
        );
    }

    @Test
    @Transactional
    void patchNonExistingVehiclebrandname() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandname.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVehiclebrandnameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, vehiclebrandname.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehiclebrandname))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchVehiclebrandname() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandname.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandnameMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(vehiclebrandname))
            )
            .andExpect(status().isBadRequest());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamVehiclebrandname() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        vehiclebrandname.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restVehiclebrandnameMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(vehiclebrandname)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Vehiclebrandname in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteVehiclebrandname() throws Exception {
        // Initialize the database
        vehiclebrandnameRepository.saveAndFlush(vehiclebrandname);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the vehiclebrandname
        restVehiclebrandnameMockMvc
            .perform(delete(ENTITY_API_URL_ID, vehiclebrandname.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return vehiclebrandnameRepository.count();
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

    protected Vehiclebrandname getPersistedVehiclebrandname(Vehiclebrandname vehiclebrandname) {
        return vehiclebrandnameRepository.findById(vehiclebrandname.getId()).orElseThrow();
    }

    protected void assertPersistedVehiclebrandnameToMatchAllProperties(Vehiclebrandname expectedVehiclebrandname) {
        assertVehiclebrandnameAllPropertiesEquals(expectedVehiclebrandname, getPersistedVehiclebrandname(expectedVehiclebrandname));
    }

    protected void assertPersistedVehiclebrandnameToMatchUpdatableProperties(Vehiclebrandname expectedVehiclebrandname) {
        assertVehiclebrandnameAllUpdatablePropertiesEquals(
            expectedVehiclebrandname,
            getPersistedVehiclebrandname(expectedVehiclebrandname)
        );
    }
}
