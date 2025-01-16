package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocarejobcategoryAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocarejobcategory;
import com.heavenscode.rac.repository.AutocarejobcategoryRepository;
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
 * Integration tests for the {@link AutocarejobcategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocarejobcategoryResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_DISPLAYORDER = 1;
    private static final Integer UPDATED_DISPLAYORDER = 2;

    private static final String ENTITY_API_URL = "/api/autocarejobcategories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocarejobcategoryRepository autocarejobcategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocarejobcategoryMockMvc;

    private Autocarejobcategory autocarejobcategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarejobcategory createEntity(EntityManager em) {
        Autocarejobcategory autocarejobcategory = new Autocarejobcategory()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .displayorder(DEFAULT_DISPLAYORDER);
        return autocarejobcategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarejobcategory createUpdatedEntity(EntityManager em) {
        Autocarejobcategory autocarejobcategory = new Autocarejobcategory()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .displayorder(UPDATED_DISPLAYORDER);
        return autocarejobcategory;
    }

    @BeforeEach
    public void initTest() {
        autocarejobcategory = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocarejobcategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocarejobcategory
        var returnedAutocarejobcategory = om.readValue(
            restAutocarejobcategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejobcategory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocarejobcategory.class
        );

        // Validate the Autocarejobcategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocarejobcategoryUpdatableFieldsEquals(
            returnedAutocarejobcategory,
            getPersistedAutocarejobcategory(returnedAutocarejobcategory)
        );
    }

    @Test
    @Transactional
    void createAutocarejobcategoryWithExistingId() throws Exception {
        // Create the Autocarejobcategory with an existing ID
        autocarejobcategory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocarejobcategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejobcategory)))
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocarejobcategories() throws Exception {
        // Initialize the database
        autocarejobcategoryRepository.saveAndFlush(autocarejobcategory);

        // Get all the autocarejobcategoryList
        restAutocarejobcategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocarejobcategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].displayorder").value(hasItem(DEFAULT_DISPLAYORDER)));
    }

    @Test
    @Transactional
    void getAutocarejobcategory() throws Exception {
        // Initialize the database
        autocarejobcategoryRepository.saveAndFlush(autocarejobcategory);

        // Get the autocarejobcategory
        restAutocarejobcategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, autocarejobcategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocarejobcategory.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.displayorder").value(DEFAULT_DISPLAYORDER));
    }

    @Test
    @Transactional
    void getNonExistingAutocarejobcategory() throws Exception {
        // Get the autocarejobcategory
        restAutocarejobcategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocarejobcategory() throws Exception {
        // Initialize the database
        autocarejobcategoryRepository.saveAndFlush(autocarejobcategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejobcategory
        Autocarejobcategory updatedAutocarejobcategory = autocarejobcategoryRepository.findById(autocarejobcategory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocarejobcategory are not directly saved in db
        em.detach(updatedAutocarejobcategory);
        updatedAutocarejobcategory
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .displayorder(UPDATED_DISPLAYORDER);

        restAutocarejobcategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocarejobcategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocarejobcategory))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocarejobcategoryToMatchAllProperties(updatedAutocarejobcategory);
    }

    @Test
    @Transactional
    void putNonExistingAutocarejobcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobcategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarejobcategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocarejobcategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarejobcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocarejobcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobcategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarejobcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocarejobcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobcategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarejobcategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocarejobcategoryWithPatch() throws Exception {
        // Initialize the database
        autocarejobcategoryRepository.saveAndFlush(autocarejobcategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejobcategory using partial update
        Autocarejobcategory partialUpdatedAutocarejobcategory = new Autocarejobcategory();
        partialUpdatedAutocarejobcategory.setId(autocarejobcategory.getId());

        partialUpdatedAutocarejobcategory.code(UPDATED_CODE).description(UPDATED_DESCRIPTION);

        restAutocarejobcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarejobcategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarejobcategory))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejobcategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarejobcategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocarejobcategory, autocarejobcategory),
            getPersistedAutocarejobcategory(autocarejobcategory)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocarejobcategoryWithPatch() throws Exception {
        // Initialize the database
        autocarejobcategoryRepository.saveAndFlush(autocarejobcategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarejobcategory using partial update
        Autocarejobcategory partialUpdatedAutocarejobcategory = new Autocarejobcategory();
        partialUpdatedAutocarejobcategory.setId(autocarejobcategory.getId());

        partialUpdatedAutocarejobcategory
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .displayorder(UPDATED_DISPLAYORDER);

        restAutocarejobcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarejobcategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarejobcategory))
            )
            .andExpect(status().isOk());

        // Validate the Autocarejobcategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarejobcategoryUpdatableFieldsEquals(
            partialUpdatedAutocarejobcategory,
            getPersistedAutocarejobcategory(partialUpdatedAutocarejobcategory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocarejobcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobcategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarejobcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocarejobcategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarejobcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocarejobcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarejobcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocarejobcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarejobcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarejobcategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocarejobcategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarejobcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocarejobcategory() throws Exception {
        // Initialize the database
        autocarejobcategoryRepository.saveAndFlush(autocarejobcategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocarejobcategory
        restAutocarejobcategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocarejobcategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocarejobcategoryRepository.count();
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

    protected Autocarejobcategory getPersistedAutocarejobcategory(Autocarejobcategory autocarejobcategory) {
        return autocarejobcategoryRepository.findById(autocarejobcategory.getId()).orElseThrow();
    }

    protected void assertPersistedAutocarejobcategoryToMatchAllProperties(Autocarejobcategory expectedAutocarejobcategory) {
        assertAutocarejobcategoryAllPropertiesEquals(
            expectedAutocarejobcategory,
            getPersistedAutocarejobcategory(expectedAutocarejobcategory)
        );
    }

    protected void assertPersistedAutocarejobcategoryToMatchUpdatableProperties(Autocarejobcategory expectedAutocarejobcategory) {
        assertAutocarejobcategoryAllUpdatablePropertiesEquals(
            expectedAutocarejobcategory,
            getPersistedAutocarejobcategory(expectedAutocarejobcategory)
        );
    }
}
