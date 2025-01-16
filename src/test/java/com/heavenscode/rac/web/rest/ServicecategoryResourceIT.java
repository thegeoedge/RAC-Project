package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.ServicecategoryAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Servicecategory;
import com.heavenscode.rac.repository.ServicecategoryRepository;
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
 * Integration tests for the {@link ServicecategoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ServicecategoryResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_SHOWSECURITY = false;
    private static final Boolean UPDATED_SHOWSECURITY = true;

    private static final Integer DEFAULT_SORTORDER = 1;
    private static final Integer UPDATED_SORTORDER = 2;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final String ENTITY_API_URL = "/api/servicecategories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ServicecategoryRepository servicecategoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServicecategoryMockMvc;

    private Servicecategory servicecategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicecategory createEntity(EntityManager em) {
        Servicecategory servicecategory = new Servicecategory()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .showsecurity(DEFAULT_SHOWSECURITY)
            .sortorder(DEFAULT_SORTORDER)
            .isactive(DEFAULT_ISACTIVE);
        return servicecategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Servicecategory createUpdatedEntity(EntityManager em) {
        Servicecategory servicecategory = new Servicecategory()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .showsecurity(UPDATED_SHOWSECURITY)
            .sortorder(UPDATED_SORTORDER)
            .isactive(UPDATED_ISACTIVE);
        return servicecategory;
    }

    @BeforeEach
    public void initTest() {
        servicecategory = createEntity(em);
    }

    @Test
    @Transactional
    void createServicecategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Servicecategory
        var returnedServicecategory = om.readValue(
            restServicecategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(servicecategory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Servicecategory.class
        );

        // Validate the Servicecategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertServicecategoryUpdatableFieldsEquals(returnedServicecategory, getPersistedServicecategory(returnedServicecategory));
    }

    @Test
    @Transactional
    void createServicecategoryWithExistingId() throws Exception {
        // Create the Servicecategory with an existing ID
        servicecategory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restServicecategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(servicecategory)))
            .andExpect(status().isBadRequest());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllServicecategories() throws Exception {
        // Initialize the database
        servicecategoryRepository.saveAndFlush(servicecategory);

        // Get all the servicecategoryList
        restServicecategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(servicecategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].showsecurity").value(hasItem(DEFAULT_SHOWSECURITY.booleanValue())))
            .andExpect(jsonPath("$.[*].sortorder").value(hasItem(DEFAULT_SORTORDER)))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())));
    }

    @Test
    @Transactional
    void getServicecategory() throws Exception {
        // Initialize the database
        servicecategoryRepository.saveAndFlush(servicecategory);

        // Get the servicecategory
        restServicecategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, servicecategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(servicecategory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.showsecurity").value(DEFAULT_SHOWSECURITY.booleanValue()))
            .andExpect(jsonPath("$.sortorder").value(DEFAULT_SORTORDER))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingServicecategory() throws Exception {
        // Get the servicecategory
        restServicecategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingServicecategory() throws Exception {
        // Initialize the database
        servicecategoryRepository.saveAndFlush(servicecategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the servicecategory
        Servicecategory updatedServicecategory = servicecategoryRepository.findById(servicecategory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedServicecategory are not directly saved in db
        em.detach(updatedServicecategory);
        updatedServicecategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .showsecurity(UPDATED_SHOWSECURITY)
            .sortorder(UPDATED_SORTORDER)
            .isactive(UPDATED_ISACTIVE);

        restServicecategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedServicecategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedServicecategory))
            )
            .andExpect(status().isOk());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedServicecategoryToMatchAllProperties(updatedServicecategory);
    }

    @Test
    @Transactional
    void putNonExistingServicecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicecategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicecategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, servicecategory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(servicecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchServicecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicecategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(servicecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamServicecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicecategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(servicecategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateServicecategoryWithPatch() throws Exception {
        // Initialize the database
        servicecategoryRepository.saveAndFlush(servicecategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the servicecategory using partial update
        Servicecategory partialUpdatedServicecategory = new Servicecategory();
        partialUpdatedServicecategory.setId(servicecategory.getId());

        partialUpdatedServicecategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmd(UPDATED_LMD)
            .showsecurity(UPDATED_SHOWSECURITY)
            .sortorder(UPDATED_SORTORDER)
            .isactive(UPDATED_ISACTIVE);

        restServicecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicecategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServicecategory))
            )
            .andExpect(status().isOk());

        // Validate the Servicecategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServicecategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedServicecategory, servicecategory),
            getPersistedServicecategory(servicecategory)
        );
    }

    @Test
    @Transactional
    void fullUpdateServicecategoryWithPatch() throws Exception {
        // Initialize the database
        servicecategoryRepository.saveAndFlush(servicecategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the servicecategory using partial update
        Servicecategory partialUpdatedServicecategory = new Servicecategory();
        partialUpdatedServicecategory.setId(servicecategory.getId());

        partialUpdatedServicecategory
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .showsecurity(UPDATED_SHOWSECURITY)
            .sortorder(UPDATED_SORTORDER)
            .isactive(UPDATED_ISACTIVE);

        restServicecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedServicecategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedServicecategory))
            )
            .andExpect(status().isOk());

        // Validate the Servicecategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertServicecategoryUpdatableFieldsEquals(
            partialUpdatedServicecategory,
            getPersistedServicecategory(partialUpdatedServicecategory)
        );
    }

    @Test
    @Transactional
    void patchNonExistingServicecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicecategory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServicecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, servicecategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(servicecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchServicecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicecategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(servicecategory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamServicecategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        servicecategory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restServicecategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(servicecategory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Servicecategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteServicecategory() throws Exception {
        // Initialize the database
        servicecategoryRepository.saveAndFlush(servicecategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the servicecategory
        restServicecategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, servicecategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return servicecategoryRepository.count();
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

    protected Servicecategory getPersistedServicecategory(Servicecategory servicecategory) {
        return servicecategoryRepository.findById(servicecategory.getId()).orElseThrow();
    }

    protected void assertPersistedServicecategoryToMatchAllProperties(Servicecategory expectedServicecategory) {
        assertServicecategoryAllPropertiesEquals(expectedServicecategory, getPersistedServicecategory(expectedServicecategory));
    }

    protected void assertPersistedServicecategoryToMatchUpdatableProperties(Servicecategory expectedServicecategory) {
        assertServicecategoryAllUpdatablePropertiesEquals(expectedServicecategory, getPersistedServicecategory(expectedServicecategory));
    }
}
