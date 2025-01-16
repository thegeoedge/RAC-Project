package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.WorkshopworklistAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Workshopworklist;
import com.heavenscode.rac.repository.WorkshopworklistRepository;
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
 * Integration tests for the {@link WorkshopworklistResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkshopworklistResourceIT {

    private static final String DEFAULT_WORKSHOPWORK = "AAAAAAAAAA";
    private static final String UPDATED_WORKSHOPWORK = "BBBBBBBBBB";

    private static final String DEFAULT_WORKSHOPWORKDESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_WORKSHOPWORKDESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final String ENTITY_API_URL = "/api/workshopworklists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WorkshopworklistRepository workshopworklistRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkshopworklistMockMvc;

    private Workshopworklist workshopworklist;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workshopworklist createEntity(EntityManager em) {
        Workshopworklist workshopworklist = new Workshopworklist()
            .workshopwork(DEFAULT_WORKSHOPWORK)
            .workshopworkdescription(DEFAULT_WORKSHOPWORKDESCRIPTION)
            .isactive(DEFAULT_ISACTIVE)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU);
        return workshopworklist;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workshopworklist createUpdatedEntity(EntityManager em) {
        Workshopworklist workshopworklist = new Workshopworklist()
            .workshopwork(UPDATED_WORKSHOPWORK)
            .workshopworkdescription(UPDATED_WORKSHOPWORKDESCRIPTION)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);
        return workshopworklist;
    }

    @BeforeEach
    public void initTest() {
        workshopworklist = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkshopworklist() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Workshopworklist
        var returnedWorkshopworklist = om.readValue(
            restWorkshopworklistMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workshopworklist)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Workshopworklist.class
        );

        // Validate the Workshopworklist in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWorkshopworklistUpdatableFieldsEquals(returnedWorkshopworklist, getPersistedWorkshopworklist(returnedWorkshopworklist));
    }

    @Test
    @Transactional
    void createWorkshopworklistWithExistingId() throws Exception {
        // Create the Workshopworklist with an existing ID
        workshopworklist.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkshopworklistMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workshopworklist)))
            .andExpect(status().isBadRequest());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWorkshopworklists() throws Exception {
        // Initialize the database
        workshopworklistRepository.saveAndFlush(workshopworklist);

        // Get all the workshopworklistList
        restWorkshopworklistMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workshopworklist.getId().intValue())))
            .andExpect(jsonPath("$.[*].workshopwork").value(hasItem(DEFAULT_WORKSHOPWORK)))
            .andExpect(jsonPath("$.[*].workshopworkdescription").value(hasItem(DEFAULT_WORKSHOPWORKDESCRIPTION)))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)));
    }

    @Test
    @Transactional
    void getWorkshopworklist() throws Exception {
        // Initialize the database
        workshopworklistRepository.saveAndFlush(workshopworklist);

        // Get the workshopworklist
        restWorkshopworklistMockMvc
            .perform(get(ENTITY_API_URL_ID, workshopworklist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workshopworklist.getId().intValue()))
            .andExpect(jsonPath("$.workshopwork").value(DEFAULT_WORKSHOPWORK))
            .andExpect(jsonPath("$.workshopworkdescription").value(DEFAULT_WORKSHOPWORKDESCRIPTION))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU));
    }

    @Test
    @Transactional
    void getNonExistingWorkshopworklist() throws Exception {
        // Get the workshopworklist
        restWorkshopworklistMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWorkshopworklist() throws Exception {
        // Initialize the database
        workshopworklistRepository.saveAndFlush(workshopworklist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workshopworklist
        Workshopworklist updatedWorkshopworklist = workshopworklistRepository.findById(workshopworklist.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWorkshopworklist are not directly saved in db
        em.detach(updatedWorkshopworklist);
        updatedWorkshopworklist
            .workshopwork(UPDATED_WORKSHOPWORK)
            .workshopworkdescription(UPDATED_WORKSHOPWORKDESCRIPTION)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restWorkshopworklistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWorkshopworklist.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWorkshopworklist))
            )
            .andExpect(status().isOk());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWorkshopworklistToMatchAllProperties(updatedWorkshopworklist);
    }

    @Test
    @Transactional
    void putNonExistingWorkshopworklist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopworklist.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkshopworklistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workshopworklist.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(workshopworklist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkshopworklist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopworklist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopworklistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(workshopworklist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkshopworklist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopworklist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopworklistMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workshopworklist)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkshopworklistWithPatch() throws Exception {
        // Initialize the database
        workshopworklistRepository.saveAndFlush(workshopworklist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workshopworklist using partial update
        Workshopworklist partialUpdatedWorkshopworklist = new Workshopworklist();
        partialUpdatedWorkshopworklist.setId(workshopworklist.getId());

        restWorkshopworklistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkshopworklist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWorkshopworklist))
            )
            .andExpect(status().isOk());

        // Validate the Workshopworklist in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkshopworklistUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedWorkshopworklist, workshopworklist),
            getPersistedWorkshopworklist(workshopworklist)
        );
    }

    @Test
    @Transactional
    void fullUpdateWorkshopworklistWithPatch() throws Exception {
        // Initialize the database
        workshopworklistRepository.saveAndFlush(workshopworklist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workshopworklist using partial update
        Workshopworklist partialUpdatedWorkshopworklist = new Workshopworklist();
        partialUpdatedWorkshopworklist.setId(workshopworklist.getId());

        partialUpdatedWorkshopworklist
            .workshopwork(UPDATED_WORKSHOPWORK)
            .workshopworkdescription(UPDATED_WORKSHOPWORKDESCRIPTION)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restWorkshopworklistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkshopworklist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWorkshopworklist))
            )
            .andExpect(status().isOk());

        // Validate the Workshopworklist in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkshopworklistUpdatableFieldsEquals(
            partialUpdatedWorkshopworklist,
            getPersistedWorkshopworklist(partialUpdatedWorkshopworklist)
        );
    }

    @Test
    @Transactional
    void patchNonExistingWorkshopworklist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopworklist.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkshopworklistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workshopworklist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(workshopworklist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkshopworklist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopworklist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopworklistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(workshopworklist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkshopworklist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopworklist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopworklistMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(workshopworklist)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Workshopworklist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkshopworklist() throws Exception {
        // Initialize the database
        workshopworklistRepository.saveAndFlush(workshopworklist);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the workshopworklist
        restWorkshopworklistMockMvc
            .perform(delete(ENTITY_API_URL_ID, workshopworklist.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return workshopworklistRepository.count();
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

    protected Workshopworklist getPersistedWorkshopworklist(Workshopworklist workshopworklist) {
        return workshopworklistRepository.findById(workshopworklist.getId()).orElseThrow();
    }

    protected void assertPersistedWorkshopworklistToMatchAllProperties(Workshopworklist expectedWorkshopworklist) {
        assertWorkshopworklistAllPropertiesEquals(expectedWorkshopworklist, getPersistedWorkshopworklist(expectedWorkshopworklist));
    }

    protected void assertPersistedWorkshopworklistToMatchUpdatableProperties(Workshopworklist expectedWorkshopworklist) {
        assertWorkshopworklistAllUpdatablePropertiesEquals(
            expectedWorkshopworklist,
            getPersistedWorkshopworklist(expectedWorkshopworklist)
        );
    }
}
