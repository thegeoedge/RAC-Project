package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocareservicemillagesAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocareservicemillages;
import com.heavenscode.rac.repository.AutocareservicemillagesRepository;
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
 * Integration tests for the {@link AutocareservicemillagesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocareservicemillagesResourceIT {

    private static final Integer DEFAULT_MILLAGE = 1;
    private static final Integer UPDATED_MILLAGE = 2;

    private static final String ENTITY_API_URL = "/api/autocareservicemillages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocareservicemillagesRepository autocareservicemillagesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocareservicemillagesMockMvc;

    private Autocareservicemillages autocareservicemillages;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocareservicemillages createEntity(EntityManager em) {
        Autocareservicemillages autocareservicemillages = new Autocareservicemillages().millage(DEFAULT_MILLAGE);
        return autocareservicemillages;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocareservicemillages createUpdatedEntity(EntityManager em) {
        Autocareservicemillages autocareservicemillages = new Autocareservicemillages().millage(UPDATED_MILLAGE);
        return autocareservicemillages;
    }

    @BeforeEach
    public void initTest() {
        autocareservicemillages = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocareservicemillages() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocareservicemillages
        var returnedAutocareservicemillages = om.readValue(
            restAutocareservicemillagesMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareservicemillages))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocareservicemillages.class
        );

        // Validate the Autocareservicemillages in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocareservicemillagesUpdatableFieldsEquals(
            returnedAutocareservicemillages,
            getPersistedAutocareservicemillages(returnedAutocareservicemillages)
        );
    }

    @Test
    @Transactional
    void createAutocareservicemillagesWithExistingId() throws Exception {
        // Create the Autocareservicemillages with an existing ID
        autocareservicemillages.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocareservicemillagesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareservicemillages)))
            .andExpect(status().isBadRequest());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocareservicemillages() throws Exception {
        // Initialize the database
        autocareservicemillagesRepository.saveAndFlush(autocareservicemillages);

        // Get all the autocareservicemillagesList
        restAutocareservicemillagesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocareservicemillages.getId().intValue())))
            .andExpect(jsonPath("$.[*].millage").value(hasItem(DEFAULT_MILLAGE)));
    }

    @Test
    @Transactional
    void getAutocareservicemillages() throws Exception {
        // Initialize the database
        autocareservicemillagesRepository.saveAndFlush(autocareservicemillages);

        // Get the autocareservicemillages
        restAutocareservicemillagesMockMvc
            .perform(get(ENTITY_API_URL_ID, autocareservicemillages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocareservicemillages.getId().intValue()))
            .andExpect(jsonPath("$.millage").value(DEFAULT_MILLAGE));
    }

    @Test
    @Transactional
    void getNonExistingAutocareservicemillages() throws Exception {
        // Get the autocareservicemillages
        restAutocareservicemillagesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocareservicemillages() throws Exception {
        // Initialize the database
        autocareservicemillagesRepository.saveAndFlush(autocareservicemillages);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareservicemillages
        Autocareservicemillages updatedAutocareservicemillages = autocareservicemillagesRepository
            .findById(autocareservicemillages.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedAutocareservicemillages are not directly saved in db
        em.detach(updatedAutocareservicemillages);
        updatedAutocareservicemillages.millage(UPDATED_MILLAGE);

        restAutocareservicemillagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocareservicemillages.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocareservicemillages))
            )
            .andExpect(status().isOk());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocareservicemillagesToMatchAllProperties(updatedAutocareservicemillages);
    }

    @Test
    @Transactional
    void putNonExistingAutocareservicemillages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareservicemillages.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocareservicemillagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocareservicemillages.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocareservicemillages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocareservicemillages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareservicemillages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareservicemillagesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocareservicemillages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocareservicemillages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareservicemillages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareservicemillagesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareservicemillages)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocareservicemillagesWithPatch() throws Exception {
        // Initialize the database
        autocareservicemillagesRepository.saveAndFlush(autocareservicemillages);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareservicemillages using partial update
        Autocareservicemillages partialUpdatedAutocareservicemillages = new Autocareservicemillages();
        partialUpdatedAutocareservicemillages.setId(autocareservicemillages.getId());

        partialUpdatedAutocareservicemillages.millage(UPDATED_MILLAGE);

        restAutocareservicemillagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocareservicemillages.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocareservicemillages))
            )
            .andExpect(status().isOk());

        // Validate the Autocareservicemillages in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocareservicemillagesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocareservicemillages, autocareservicemillages),
            getPersistedAutocareservicemillages(autocareservicemillages)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocareservicemillagesWithPatch() throws Exception {
        // Initialize the database
        autocareservicemillagesRepository.saveAndFlush(autocareservicemillages);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareservicemillages using partial update
        Autocareservicemillages partialUpdatedAutocareservicemillages = new Autocareservicemillages();
        partialUpdatedAutocareservicemillages.setId(autocareservicemillages.getId());

        partialUpdatedAutocareservicemillages.millage(UPDATED_MILLAGE);

        restAutocareservicemillagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocareservicemillages.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocareservicemillages))
            )
            .andExpect(status().isOk());

        // Validate the Autocareservicemillages in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocareservicemillagesUpdatableFieldsEquals(
            partialUpdatedAutocareservicemillages,
            getPersistedAutocareservicemillages(partialUpdatedAutocareservicemillages)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocareservicemillages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareservicemillages.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocareservicemillagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocareservicemillages.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocareservicemillages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocareservicemillages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareservicemillages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareservicemillagesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocareservicemillages))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocareservicemillages() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareservicemillages.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareservicemillagesMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocareservicemillages))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocareservicemillages in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocareservicemillages() throws Exception {
        // Initialize the database
        autocareservicemillagesRepository.saveAndFlush(autocareservicemillages);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocareservicemillages
        restAutocareservicemillagesMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocareservicemillages.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocareservicemillagesRepository.count();
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

    protected Autocareservicemillages getPersistedAutocareservicemillages(Autocareservicemillages autocareservicemillages) {
        return autocareservicemillagesRepository.findById(autocareservicemillages.getId()).orElseThrow();
    }

    protected void assertPersistedAutocareservicemillagesToMatchAllProperties(Autocareservicemillages expectedAutocareservicemillages) {
        assertAutocareservicemillagesAllPropertiesEquals(
            expectedAutocareservicemillages,
            getPersistedAutocareservicemillages(expectedAutocareservicemillages)
        );
    }

    protected void assertPersistedAutocareservicemillagesToMatchUpdatableProperties(
        Autocareservicemillages expectedAutocareservicemillages
    ) {
        assertAutocareservicemillagesAllUpdatablePropertiesEquals(
            expectedAutocareservicemillages,
            getPersistedAutocareservicemillages(expectedAutocareservicemillages)
        );
    }
}
