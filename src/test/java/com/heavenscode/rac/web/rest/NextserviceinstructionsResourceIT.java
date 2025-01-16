package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.NextserviceinstructionsAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Nextserviceinstructions;
import com.heavenscode.rac.repository.NextserviceinstructionsRepository;
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
 * Integration tests for the {@link NextserviceinstructionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NextserviceinstructionsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/nextserviceinstructions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private NextserviceinstructionsRepository nextserviceinstructionsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNextserviceinstructionsMockMvc;

    private Nextserviceinstructions nextserviceinstructions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nextserviceinstructions createEntity(EntityManager em) {
        Nextserviceinstructions nextserviceinstructions = new Nextserviceinstructions()
            .jobid(DEFAULT_JOBID)
            .instruction(DEFAULT_INSTRUCTION)
            .isactive(DEFAULT_ISACTIVE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .ignore(DEFAULT_IGNORE)
            .vehicleid(DEFAULT_VEHICLEID)
            .vehicleno(DEFAULT_VEHICLENO);
        return nextserviceinstructions;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Nextserviceinstructions createUpdatedEntity(EntityManager em) {
        Nextserviceinstructions nextserviceinstructions = new Nextserviceinstructions()
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .ignore(UPDATED_IGNORE)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);
        return nextserviceinstructions;
    }

    @BeforeEach
    public void initTest() {
        nextserviceinstructions = createEntity(em);
    }

    @Test
    @Transactional
    void createNextserviceinstructions() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Nextserviceinstructions
        var returnedNextserviceinstructions = om.readValue(
            restNextserviceinstructionsMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nextserviceinstructions))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Nextserviceinstructions.class
        );

        // Validate the Nextserviceinstructions in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertNextserviceinstructionsUpdatableFieldsEquals(
            returnedNextserviceinstructions,
            getPersistedNextserviceinstructions(returnedNextserviceinstructions)
        );
    }

    @Test
    @Transactional
    void createNextserviceinstructionsWithExistingId() throws Exception {
        // Create the Nextserviceinstructions with an existing ID
        nextserviceinstructions.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNextserviceinstructionsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nextserviceinstructions)))
            .andExpect(status().isBadRequest());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNextserviceinstructions() throws Exception {
        // Initialize the database
        nextserviceinstructionsRepository.saveAndFlush(nextserviceinstructions);

        // Get all the nextserviceinstructionsList
        restNextserviceinstructionsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nextserviceinstructions.getId().intValue())))
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
    void getNextserviceinstructions() throws Exception {
        // Initialize the database
        nextserviceinstructionsRepository.saveAndFlush(nextserviceinstructions);

        // Get the nextserviceinstructions
        restNextserviceinstructionsMockMvc
            .perform(get(ENTITY_API_URL_ID, nextserviceinstructions.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nextserviceinstructions.getId().intValue()))
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
    void getNonExistingNextserviceinstructions() throws Exception {
        // Get the nextserviceinstructions
        restNextserviceinstructionsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNextserviceinstructions() throws Exception {
        // Initialize the database
        nextserviceinstructionsRepository.saveAndFlush(nextserviceinstructions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nextserviceinstructions
        Nextserviceinstructions updatedNextserviceinstructions = nextserviceinstructionsRepository
            .findById(nextserviceinstructions.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedNextserviceinstructions are not directly saved in db
        em.detach(updatedNextserviceinstructions);
        updatedNextserviceinstructions
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .ignore(UPDATED_IGNORE)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);

        restNextserviceinstructionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedNextserviceinstructions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedNextserviceinstructions))
            )
            .andExpect(status().isOk());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedNextserviceinstructionsToMatchAllProperties(updatedNextserviceinstructions);
    }

    @Test
    @Transactional
    void putNonExistingNextserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nextserviceinstructions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNextserviceinstructionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nextserviceinstructions.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nextserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNextserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nextserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextserviceinstructionsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(nextserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNextserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nextserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextserviceinstructionsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(nextserviceinstructions)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNextserviceinstructionsWithPatch() throws Exception {
        // Initialize the database
        nextserviceinstructionsRepository.saveAndFlush(nextserviceinstructions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nextserviceinstructions using partial update
        Nextserviceinstructions partialUpdatedNextserviceinstructions = new Nextserviceinstructions();
        partialUpdatedNextserviceinstructions.setId(nextserviceinstructions.getId());

        partialUpdatedNextserviceinstructions.instruction(UPDATED_INSTRUCTION).lmd(UPDATED_LMD).vehicleno(UPDATED_VEHICLENO);

        restNextserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNextserviceinstructions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNextserviceinstructions))
            )
            .andExpect(status().isOk());

        // Validate the Nextserviceinstructions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNextserviceinstructionsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedNextserviceinstructions, nextserviceinstructions),
            getPersistedNextserviceinstructions(nextserviceinstructions)
        );
    }

    @Test
    @Transactional
    void fullUpdateNextserviceinstructionsWithPatch() throws Exception {
        // Initialize the database
        nextserviceinstructionsRepository.saveAndFlush(nextserviceinstructions);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the nextserviceinstructions using partial update
        Nextserviceinstructions partialUpdatedNextserviceinstructions = new Nextserviceinstructions();
        partialUpdatedNextserviceinstructions.setId(nextserviceinstructions.getId());

        partialUpdatedNextserviceinstructions
            .jobid(UPDATED_JOBID)
            .instruction(UPDATED_INSTRUCTION)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .ignore(UPDATED_IGNORE)
            .vehicleid(UPDATED_VEHICLEID)
            .vehicleno(UPDATED_VEHICLENO);

        restNextserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNextserviceinstructions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedNextserviceinstructions))
            )
            .andExpect(status().isOk());

        // Validate the Nextserviceinstructions in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertNextserviceinstructionsUpdatableFieldsEquals(
            partialUpdatedNextserviceinstructions,
            getPersistedNextserviceinstructions(partialUpdatedNextserviceinstructions)
        );
    }

    @Test
    @Transactional
    void patchNonExistingNextserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nextserviceinstructions.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNextserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nextserviceinstructions.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nextserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNextserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nextserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(nextserviceinstructions))
            )
            .andExpect(status().isBadRequest());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNextserviceinstructions() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        nextserviceinstructions.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNextserviceinstructionsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(nextserviceinstructions))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Nextserviceinstructions in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNextserviceinstructions() throws Exception {
        // Initialize the database
        nextserviceinstructionsRepository.saveAndFlush(nextserviceinstructions);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the nextserviceinstructions
        restNextserviceinstructionsMockMvc
            .perform(delete(ENTITY_API_URL_ID, nextserviceinstructions.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return nextserviceinstructionsRepository.count();
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

    protected Nextserviceinstructions getPersistedNextserviceinstructions(Nextserviceinstructions nextserviceinstructions) {
        return nextserviceinstructionsRepository.findById(nextserviceinstructions.getId()).orElseThrow();
    }

    protected void assertPersistedNextserviceinstructionsToMatchAllProperties(Nextserviceinstructions expectedNextserviceinstructions) {
        assertNextserviceinstructionsAllPropertiesEquals(
            expectedNextserviceinstructions,
            getPersistedNextserviceinstructions(expectedNextserviceinstructions)
        );
    }

    protected void assertPersistedNextserviceinstructionsToMatchUpdatableProperties(
        Nextserviceinstructions expectedNextserviceinstructions
    ) {
        assertNextserviceinstructionsAllUpdatablePropertiesEquals(
            expectedNextserviceinstructions,
            getPersistedNextserviceinstructions(expectedNextserviceinstructions)
        );
    }
}
