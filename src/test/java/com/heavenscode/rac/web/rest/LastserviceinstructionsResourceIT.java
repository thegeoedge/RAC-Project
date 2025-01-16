package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.LastserviceinstructionsAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Lastserviceinstructions;
import com.heavenscode.rac.repository.LastserviceinstructionsRepository;
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
 * Integration tests for the {@link LastserviceinstructionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LastserviceinstructionsResourceIT {

    private static final Integer DEFAULT_JOBID = 1;
    private static final Integer UPDATED_JOBID = 2;

    private static final String DEFAULT_INSTRUCTION = "AAAAAAAAAA";
    private static final String UPDATED_INSTRUCTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IGNORE = false;
    private static final Boolean UPDATED_IGNORE = true;

    private static final Integer DEFAULT_VEHICLEID = 1;
    private static final Integer UPDATED_VEHICLEID = 2;

    private static final String DEFAULT_VEHICLENO = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLENO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/lastserviceinstructions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LastserviceinstructionsRepository lastserviceinstructionsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLastserviceinstructionsMockMvc;

    private Lastserviceinstructions lastserviceinstructions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lastserviceinstructions createEntity(EntityManager em) {
        Lastserviceinstructions lastserviceinstructions = new Lastserviceinstructions()
            .jobid(DEFAULT_JOBID)
            .instruction(DEFAULT_INSTRUCTION)
            .isactive(DEFAULT_ISACTIVE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .ignore(DEFAULT_IGNORE)
            .vehicleid(DEFAULT_VEHICLEID)
            .vehicleno(DEFAULT_VEHICLENO);
        return lastserviceinstructions;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lastserviceinstructions createUpdatedEntity(EntityManager em) {
        Lastserviceinstructions lastserviceinstructions = new Lastserviceinstructions()
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .ignore(UPDATED_IGNORE)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);
        return lastserviceinstructions;
    }

    @BeforeEach
    public void initTest() {
        lastserviceinstructions = createEntity(em);
    }

    @Test
    @Transactional
    void createLastserviceinstructions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Lastserviceinstructions
        var returnedLastserviceinstructions = om.readValue(
            restLastserviceinstructionsMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lastserviceinstructions))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Lastserviceinstructions.class
        );

        // Validate the Lastserviceinstructions in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLastserviceinstructionsUpdatableFieldsEquals(
            returnedLastserviceinstructions,
            getPersistedLastserviceinstructions(returnedLastserviceinstructions)
        );
    }

    @Test
    @Transactional
    void createLastserviceinstructionsWithExistingId() throws Exception {
        // Create the Lastserviceinstructions with an existing ID
        lastserviceinstructions.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLastserviceinstructionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lastserviceinstructions)))
            .andExpect(status().isBadRequest());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLastserviceinstructions() throws Exception {
        // Initialize the database
        lastserviceinstructionsRepository.saveAndFlush(lastserviceinstructions);

        // Get all the lastserviceinstructionsList
        restLastserviceinstructionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lastserviceinstructions.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)))
            .andExpect(jsonPath("$.[*].instruction").value(hasItem(DEFAULT_INSTRUCTION)))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].ignore").value(hasItem(DEFAULT_IGNORE.booleanValue())))
            .andExpect(jsonPath("$.[*].vehicleid").value(hasItem(DEFAULT_VEHICLEID)))
            .andExpect(jsonPath("$.[*].vehicleno").value(hasItem(DEFAULT_VEHICLENO)));
    }

    @Test
    @Transactional
    void getLastserviceinstructions() throws Exception {
        // Initialize the database
        lastserviceinstructionsRepository.saveAndFlush(lastserviceinstructions);

        // Get the lastserviceinstructions
        restLastserviceinstructionsMockMvc
            .perform(get(ENTITY_API_URL_ID, lastserviceinstructions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(lastserviceinstructions.getId().intValue()))
            .andExpect(jsonPath("$.jobid").value(DEFAULT_JOBID))
            .andExpect(jsonPath("$.instruction").value(DEFAULT_INSTRUCTION))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.ignore").value(DEFAULT_IGNORE.booleanValue()))
            .andExpect(jsonPath("$.vehicleid").value(DEFAULT_VEHICLEID))
            .andExpect(jsonPath("$.vehicleno").value(DEFAULT_VEHICLENO));
    }

    @Test
    @Transactional
    void getNonExistingLastserviceinstructions() throws Exception {
        // Get the lastserviceinstructions
        restLastserviceinstructionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLastserviceinstructions() throws Exception {
        // Initialize the database
        lastserviceinstructionsRepository.saveAndFlush(lastserviceinstructions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lastserviceinstructions
        Lastserviceinstructions updatedLastserviceinstructions = lastserviceinstructionsRepository
            .findById(lastserviceinstructions.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedLastserviceinstructions are not directly saved in db
        em.detach(updatedLastserviceinstructions);
        updatedLastserviceinstructions
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .ignore(UPDATED_IGNORE)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);

        restLastserviceinstructionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLastserviceinstructions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLastserviceinstructions))
            )
            .andExpect(status().isOk());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLastserviceinstructionsToMatchAllProperties(updatedLastserviceinstructions);
    }

    @Test
    @Transactional
    void putNonExistingLastserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lastserviceinstructions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLastserviceinstructionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, lastserviceinstructions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(lastserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLastserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lastserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLastserviceinstructionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(lastserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLastserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lastserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLastserviceinstructionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(lastserviceinstructions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLastserviceinstructionsWithPatch() throws Exception {
        // Initialize the database
        lastserviceinstructionsRepository.saveAndFlush(lastserviceinstructions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lastserviceinstructions using partial update
        Lastserviceinstructions partialUpdatedLastserviceinstructions = new Lastserviceinstructions();
        partialUpdatedLastserviceinstructions.setId(lastserviceinstructions.getId());

        partialUpdatedLastserviceinstructions
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .lmu(UPDATED_LMU)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);

        restLastserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLastserviceinstructions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLastserviceinstructions))
            )
            .andExpect(status().isOk());

        // Validate the Lastserviceinstructions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLastserviceinstructionsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLastserviceinstructions, lastserviceinstructions),
            getPersistedLastserviceinstructions(lastserviceinstructions)
        );
    }

    @Test
    @Transactional
    void fullUpdateLastserviceinstructionsWithPatch() throws Exception {
        // Initialize the database
        lastserviceinstructionsRepository.saveAndFlush(lastserviceinstructions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lastserviceinstructions using partial update
        Lastserviceinstructions partialUpdatedLastserviceinstructions = new Lastserviceinstructions();
        partialUpdatedLastserviceinstructions.setId(lastserviceinstructions.getId());

        partialUpdatedLastserviceinstructions
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .ignore(UPDATED_IGNORE)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);

        restLastserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLastserviceinstructions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLastserviceinstructions))
            )
            .andExpect(status().isOk());

        // Validate the Lastserviceinstructions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLastserviceinstructionsUpdatableFieldsEquals(
            partialUpdatedLastserviceinstructions,
            getPersistedLastserviceinstructions(partialUpdatedLastserviceinstructions)
        );
    }

    @Test
    @Transactional
    void patchNonExistingLastserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lastserviceinstructions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLastserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, lastserviceinstructions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(lastserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLastserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lastserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLastserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(lastserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLastserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lastserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLastserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(lastserviceinstructions))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Lastserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLastserviceinstructions() throws Exception {
        // Initialize the database
        lastserviceinstructionsRepository.saveAndFlush(lastserviceinstructions);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the lastserviceinstructions
        restLastserviceinstructionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, lastserviceinstructions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return lastserviceinstructionsRepository.count();
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

    protected Lastserviceinstructions getPersistedLastserviceinstructions(Lastserviceinstructions lastserviceinstructions) {
        return lastserviceinstructionsRepository.findById(lastserviceinstructions.getId()).orElseThrow();
    }

    protected void assertPersistedLastserviceinstructionsToMatchAllProperties(Lastserviceinstructions expectedLastserviceinstructions) {
        assertLastserviceinstructionsAllPropertiesEquals(
            expectedLastserviceinstructions,
            getPersistedLastserviceinstructions(expectedLastserviceinstructions)
        );
    }

    protected void assertPersistedLastserviceinstructionsToMatchUpdatableProperties(
        Lastserviceinstructions expectedLastserviceinstructions
    ) {
        assertLastserviceinstructionsAllUpdatablePropertiesEquals(
            expectedLastserviceinstructions,
            getPersistedLastserviceinstructions(expectedLastserviceinstructions)
        );
    }
}
