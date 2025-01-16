package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.ServicesubcategoryAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Servicesubcategory;
import com.heavenscode.rac.repository.ServicesubcategoryRepository;
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
 * Integration tests for the {@link ServicesubcategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServicesubcategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MAINID = 1;
    private static final Integer UPDATED_MAINID = 2;

    private static final String DEFAULT_MAINNAME = "AAAAAAAAAA";
    private static final String UPDATED_MAINNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final String ENTITY_API_URL = "/api/servicesubcategories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ServicesubcategoryRepository servicesubcategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServicesubcategoryMockMvc;

    private Servicesubcategory servicesubcategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicesubcategory createEntity(EntityManager em) {
        Servicesubcategory servicesubcategory = new Servicesubcategory()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .mainid(DEFAULT_MAINID)
            .mainname(DEFAULT_MAINNAME)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .isactive(DEFAULT_ISACTIVE);
        return servicesubcategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicesubcategory createUpdatedEntity(EntityManager em) {
        Servicesubcategory servicesubcategory = new Servicesubcategory()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .mainid(UPDATED_MAINID)
            .mainname(UPDATED_MAINNAME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isactive(UPDATED_ISACTIVE);
        return servicesubcategory;
    }

    @BeforeEach
    public void initTest() {
        servicesubcategory = createEntity(em);
    }

    @Test
    @Transactional
    void createServicesubcategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Servicesubcategory
        var returnedServicesubcategory = om.readValue(
            restServicesubcategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(servicesubcategory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Servicesubcategory.class
        );

        // Validate the Servicesubcategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertServicesubcategoryUpdatableFieldsEquals(
            returnedServicesubcategory,
            getPersistedServicesubcategory(returnedServicesubcategory)
        );
    }

    @Test
    @Transactional
    void createServicesubcategoryWithExistingId() throws Exception {
        // Create the Servicesubcategory with an existing ID
        servicesubcategory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServicesubcategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(servicesubcategory)))
            .andExpect(status().isBadRequest());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllServicesubcategories() throws Exception {
        // Initialize the database
        servicesubcategoryRepository.saveAndFlush(servicesubcategory);

        // Get all the servicesubcategoryList
        restServicesubcategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servicesubcategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].mainid").value(hasItem(DEFAULT_MAINID)))
            .andExpect(jsonPath("$.[*].mainname").value(hasItem(DEFAULT_MAINNAME)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())));
    }

    @Test
    @Transactional
    void getServicesubcategory() throws Exception {
        // Initialize the database
        servicesubcategoryRepository.saveAndFlush(servicesubcategory);

        // Get the servicesubcategory
        restServicesubcategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, servicesubcategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(servicesubcategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.mainid").value(DEFAULT_MAINID))
            .andExpect(jsonPath("$.mainname").value(DEFAULT_MAINNAME))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingServicesubcategory() throws Exception {
        // Get the servicesubcategory
        restServicesubcategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingServicesubcategory() throws Exception {
        // Initialize the database
        servicesubcategoryRepository.saveAndFlush(servicesubcategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the servicesubcategory
        Servicesubcategory updatedServicesubcategory = servicesubcategoryRepository.findById(servicesubcategory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedServicesubcategory are not directly saved in db
        em.detach(updatedServicesubcategory);
        updatedServicesubcategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .mainid(UPDATED_MAINID)
            .mainname(UPDATED_MAINNAME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isactive(UPDATED_ISACTIVE);

        restServicesubcategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedServicesubcategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedServicesubcategory))
            )
            .andExpect(status().isOk());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedServicesubcategoryToMatchAllProperties(updatedServicesubcategory);
    }

    @Test
    @Transactional
    void putNonExistingServicesubcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicesubcategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicesubcategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, servicesubcategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(servicesubcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServicesubcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicesubcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesubcategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(servicesubcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServicesubcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicesubcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesubcategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(servicesubcategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServicesubcategoryWithPatch() throws Exception {
        // Initialize the database
        servicesubcategoryRepository.saveAndFlush(servicesubcategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the servicesubcategory using partial update
        Servicesubcategory partialUpdatedServicesubcategory = new Servicesubcategory();
        partialUpdatedServicesubcategory.setId(servicesubcategory.getId());

        partialUpdatedServicesubcategory.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).lmd(UPDATED_LMD).isactive(UPDATED_ISACTIVE);

        restServicesubcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicesubcategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServicesubcategory))
            )
            .andExpect(status().isOk());

        // Validate the Servicesubcategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServicesubcategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedServicesubcategory, servicesubcategory),
            getPersistedServicesubcategory(servicesubcategory)
        );
    }

    @Test
    @Transactional
    void fullUpdateServicesubcategoryWithPatch() throws Exception {
        // Initialize the database
        servicesubcategoryRepository.saveAndFlush(servicesubcategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the servicesubcategory using partial update
        Servicesubcategory partialUpdatedServicesubcategory = new Servicesubcategory();
        partialUpdatedServicesubcategory.setId(servicesubcategory.getId());

        partialUpdatedServicesubcategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .mainid(UPDATED_MAINID)
            .mainname(UPDATED_MAINNAME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isactive(UPDATED_ISACTIVE);

        restServicesubcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicesubcategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServicesubcategory))
            )
            .andExpect(status().isOk());

        // Validate the Servicesubcategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServicesubcategoryUpdatableFieldsEquals(
            partialUpdatedServicesubcategory,
            getPersistedServicesubcategory(partialUpdatedServicesubcategory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingServicesubcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicesubcategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicesubcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, servicesubcategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(servicesubcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServicesubcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicesubcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesubcategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(servicesubcategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServicesubcategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicesubcategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicesubcategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(servicesubcategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicesubcategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServicesubcategory() throws Exception {
        // Initialize the database
        servicesubcategoryRepository.saveAndFlush(servicesubcategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the servicesubcategory
        restServicesubcategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, servicesubcategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return servicesubcategoryRepository.count();
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

    protected Servicesubcategory getPersistedServicesubcategory(Servicesubcategory servicesubcategory) {
        return servicesubcategoryRepository.findById(servicesubcategory.getId()).orElseThrow();
    }

    protected void assertPersistedServicesubcategoryToMatchAllProperties(Servicesubcategory expectedServicesubcategory) {
        assertServicesubcategoryAllPropertiesEquals(expectedServicesubcategory, getPersistedServicesubcategory(expectedServicesubcategory));
    }

    protected void assertPersistedServicesubcategoryToMatchUpdatableProperties(Servicesubcategory expectedServicesubcategory) {
        assertServicesubcategoryAllUpdatablePropertiesEquals(
            expectedServicesubcategory,
            getPersistedServicesubcategory(expectedServicesubcategory)
        );
    }
}
