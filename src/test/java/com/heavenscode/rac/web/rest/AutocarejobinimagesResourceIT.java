package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocarejobinimagesAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocarejobinimages;
import com.heavenscode.rac.repository.AutocarejobinimagesRepository;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link AutocarejobinimagesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocarejobinimagesResourceIT {

    private static final Integer DEFAULT_JOBID = 1;
    private static final Integer UPDATED_JOBID = 2;

    private static final String DEFAULT_IMAGEFOLDER = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEFOLDER = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGENAME = "AAAAAAAAAA";
    private static final String UPDATED_IMAGENAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/autocarejobinimages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocarejobinimagesRepository autocarejobinimagesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocarejobinimagesMockMvc;

    private Autocarejobinimages autocarejobinimages;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarejobinimages createEntity(EntityManager em) {
        Autocarejobinimages autocarejobinimages = new Autocarejobinimages()
            .jobid(DEFAULT_JOBID)
            .imagefolder(DEFAULT_IMAGEFOLDER)
            .imagename(DEFAULT_IMAGENAME);
        return autocarejobinimages;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarejobinimages createUpdatedEntity(EntityManager em) {
        Autocarejobinimages autocarejobinimages = new Autocarejobinimages()
            .jobid(UPDATED_JOBID)
            .imagefolder(UPDATED_IMAGEFOLDER)
            .imagename(UPDATED_IMAGENAME);
        return autocarejobinimages;
    }

    @BeforeEach
    public void initTest() {
        autocarejobinimages = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocarejobinimages() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocarejobinimages
        var returnedAutocarejobinimages = om.readValue(
            restAutocarejobinimagesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejobinimages)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocarejobinimages.class
        );

        // Validate the Autocarejobinimages in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocarejobinimagesUpdatableFieldsEquals(
            returnedAutocarejobinimages,
            getPersistedAutocarejobinimages(returnedAutocarejobinimages)
        );
    }

    @Test
    @Transactional
    void createAutocarejobinimagesWithExistingId() throws Exception {
        // Create the Autocarejobinimages with an existing ID
        autocarejobinimages.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocarejobinimagesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejobinimages)))
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocarejobinimages() throws Exception {
        // Initialize the database
        autocarejobinimagesRepository.saveAndFlush(autocarejobinimages);

        // Get all the autocarejobinimagesList
        restAutocarejobinimagesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocarejobinimages.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)))
            .andExpect(jsonPath("$.[*].imagefolder").value(hasItem(DEFAULT_IMAGEFOLDER)))
            .andExpect(jsonPath("$.[*].imagename").value(hasItem(DEFAULT_IMAGENAME)));
    }

    @Test
    @Transactional
    void getAutocarejobinimages() throws Exception {
        // Initialize the database
        autocarejobinimagesRepository.saveAndFlush(autocarejobinimages);

        // Get the autocarejobinimages
        restAutocarejobinimagesMockMvc
            .perform(get(ENTITY_API_URL_ID, autocarejobinimages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocarejobinimages.getId().intValue()))
            .andExpect(jsonPath("$.jobid").value(DEFAULT_JOBID))
            .andExpect(jsonPath("$.imagefolder").value(DEFAULT_IMAGEFOLDER))
            .andExpect(jsonPath("$.imagename").value(DEFAULT_IMAGENAME));
    }

    @Test
    @Transactional
    void getNonExistingAutocarejobinimages() throws Exception {
        // Get the autocarejobinimages
        restAutocarejobinimagesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocarejobinimages() throws Exception {
        // Initialize the database
        autocarejobinimagesRepository.saveAndFlush(autocarejobinimages);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejobinimages
        Autocarejobinimages updatedAutocarejobinimages = autocarejobinimagesRepository.findById(autocarejobinimages.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocarejobinimages are not directly saved in db
        em.detach(updatedAutocarejobinimages);
        updatedAutocarejobinimages.jobid(UPDATED_JOBID).imagefolder(UPDATED_IMAGEFOLDER).imagename(UPDATED_IMAGENAME);

        restAutocarejobinimagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocarejobinimages.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocarejobinimages))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocarejobinimagesToMatchAllProperties(updatedAutocarejobinimages);
    }

    @Test
    @Transactional
    void putNonExistingAutocarejobinimages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobinimages.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarejobinimagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocarejobinimages.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarejobinimages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocarejobinimages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobinimages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobinimagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarejobinimages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocarejobinimages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobinimages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobinimagesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejobinimages)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocarejobinimagesWithPatch() throws Exception {
        // Initialize the database
        autocarejobinimagesRepository.saveAndFlush(autocarejobinimages);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejobinimages using partial update
        Autocarejobinimages partialUpdatedAutocarejobinimages = new Autocarejobinimages();
        partialUpdatedAutocarejobinimages.setId(autocarejobinimages.getId());

        partialUpdatedAutocarejobinimages.imagename(UPDATED_IMAGENAME);

        restAutocarejobinimagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarejobinimages.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarejobinimages))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejobinimages in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarejobinimagesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocarejobinimages, autocarejobinimages),
            getPersistedAutocarejobinimages(autocarejobinimages)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocarejobinimagesWithPatch() throws Exception {
        // Initialize the database
        autocarejobinimagesRepository.saveAndFlush(autocarejobinimages);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejobinimages using partial update
        Autocarejobinimages partialUpdatedAutocarejobinimages = new Autocarejobinimages();
        partialUpdatedAutocarejobinimages.setId(autocarejobinimages.getId());

        partialUpdatedAutocarejobinimages.jobid(UPDATED_JOBID).imagefolder(UPDATED_IMAGEFOLDER).imagename(UPDATED_IMAGENAME);

        restAutocarejobinimagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarejobinimages.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarejobinimages))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejobinimages in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarejobinimagesUpdatableFieldsEquals(
            partialUpdatedAutocarejobinimages,
            getPersistedAutocarejobinimages(partialUpdatedAutocarejobinimages)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocarejobinimages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobinimages.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarejobinimagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocarejobinimages.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarejobinimages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocarejobinimages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobinimages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobinimagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarejobinimages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocarejobinimages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobinimages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobinimagesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocarejobinimages)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarejobinimages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocarejobinimages() throws Exception {
        // Initialize the database
        autocarejobinimagesRepository.saveAndFlush(autocarejobinimages);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocarejobinimages
        restAutocarejobinimagesMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocarejobinimages.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocarejobinimagesRepository.count();
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

    protected Autocarejobinimages getPersistedAutocarejobinimages(Autocarejobinimages autocarejobinimages) {
        return autocarejobinimagesRepository.findById(autocarejobinimages.getId()).orElseThrow();
    }

    protected void assertPersistedAutocarejobinimagesToMatchAllProperties(Autocarejobinimages expectedAutocarejobinimages) {
        assertAutocarejobinimagesAllPropertiesEquals(
            expectedAutocarejobinimages,
            getPersistedAutocarejobinimages(expectedAutocarejobinimages)
        );
    }

    protected void assertPersistedAutocarejobinimagesToMatchUpdatableProperties(Autocarejobinimages expectedAutocarejobinimages) {
        assertAutocarejobinimagesAllUpdatablePropertiesEquals(
            expectedAutocarejobinimages,
            getPersistedAutocarejobinimages(expectedAutocarejobinimages)
        );
    }
}
