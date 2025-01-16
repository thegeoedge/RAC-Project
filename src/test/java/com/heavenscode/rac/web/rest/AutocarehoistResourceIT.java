package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocarehoistAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocarehoist;
import com.heavenscode.rac.repository.AutocarehoistRepository;
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
 * Integration tests for the {@link AutocarehoistResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocarehoistResourceIT {

    private static final String DEFAULT_HOISTNAME = "AAAAAAAAAA";
    private static final String UPDATED_HOISTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_HOISTCODE = "AAAAAAAAAA";
    private static final String UPDATED_HOISTCODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOISTTYPEID = 1;
    private static final Integer UPDATED_HOISTTYPEID = 2;

    private static final String DEFAULT_HOISTTYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_HOISTTYPENAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/autocarehoists";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocarehoistRepository autocarehoistRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocarehoistMockMvc;

    private Autocarehoist autocarehoist;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarehoist createEntity(EntityManager em) {
        Autocarehoist autocarehoist = new Autocarehoist()
            .hoistname(DEFAULT_HOISTNAME)
            .hoistcode(DEFAULT_HOISTCODE)
            .hoisttypeid(DEFAULT_HOISTTYPEID)
            .hoisttypename(DEFAULT_HOISTTYPENAME)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD);
        return autocarehoist;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarehoist createUpdatedEntity(EntityManager em) {
        Autocarehoist autocarehoist = new Autocarehoist()
            .hoistname(UPDATED_HOISTNAME)
            .hoistcode(UPDATED_HOISTCODE)
            .hoisttypeid(UPDATED_HOISTTYPEID)
            .hoisttypename(UPDATED_HOISTTYPENAME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);
        return autocarehoist;
    }

    @BeforeEach
    public void initTest() {
        autocarehoist = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocarehoist() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocarehoist
        var returnedAutocarehoist = om.readValue(
            restAutocarehoistMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarehoist)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocarehoist.class
        );

        // Validate the Autocarehoist in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocarehoistUpdatableFieldsEquals(returnedAutocarehoist, getPersistedAutocarehoist(returnedAutocarehoist));
    }

    @Test
    @Transactional
    void createAutocarehoistWithExistingId() throws Exception {
        // Create the Autocarehoist with an existing ID
        autocarehoist.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocarehoistMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarehoist)))
            .andExpect(status().isBadRequest());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocarehoists() throws Exception {
        // Initialize the database
        autocarehoistRepository.saveAndFlush(autocarehoist);

        // Get all the autocarehoistList
        restAutocarehoistMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocarehoist.getId().intValue())))
            .andExpect(jsonPath("$.[*].hoistname").value(hasItem(DEFAULT_HOISTNAME)))
            .andExpect(jsonPath("$.[*].hoistcode").value(hasItem(DEFAULT_HOISTCODE)))
            .andExpect(jsonPath("$.[*].hoisttypeid").value(hasItem(DEFAULT_HOISTTYPEID)))
            .andExpect(jsonPath("$.[*].hoisttypename").value(hasItem(DEFAULT_HOISTTYPENAME)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getAutocarehoist() throws Exception {
        // Initialize the database
        autocarehoistRepository.saveAndFlush(autocarehoist);

        // Get the autocarehoist
        restAutocarehoistMockMvc
            .perform(get(ENTITY_API_URL_ID, autocarehoist.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocarehoist.getId().intValue()))
            .andExpect(jsonPath("$.hoistname").value(DEFAULT_HOISTNAME))
            .andExpect(jsonPath("$.hoistcode").value(DEFAULT_HOISTCODE))
            .andExpect(jsonPath("$.hoisttypeid").value(DEFAULT_HOISTTYPEID))
            .andExpect(jsonPath("$.hoisttypename").value(DEFAULT_HOISTTYPENAME))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAutocarehoist() throws Exception {
        // Get the autocarehoist
        restAutocarehoistMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocarehoist() throws Exception {
        // Initialize the database
        autocarehoistRepository.saveAndFlush(autocarehoist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarehoist
        Autocarehoist updatedAutocarehoist = autocarehoistRepository.findById(autocarehoist.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocarehoist are not directly saved in db
        em.detach(updatedAutocarehoist);
        updatedAutocarehoist
            .hoistname(UPDATED_HOISTNAME)
            .hoistcode(UPDATED_HOISTCODE)
            .hoisttypeid(UPDATED_HOISTTYPEID)
            .hoisttypename(UPDATED_HOISTTYPENAME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restAutocarehoistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocarehoist.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocarehoist))
            )
            .andExpect(status().isOk());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocarehoistToMatchAllProperties(updatedAutocarehoist);
    }

    @Test
    @Transactional
    void putNonExistingAutocarehoist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarehoist.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarehoistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocarehoist.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarehoist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocarehoist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarehoist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarehoistMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarehoist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocarehoist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarehoist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarehoistMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarehoist)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocarehoistWithPatch() throws Exception {
        // Initialize the database
        autocarehoistRepository.saveAndFlush(autocarehoist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarehoist using partial update
        Autocarehoist partialUpdatedAutocarehoist = new Autocarehoist();
        partialUpdatedAutocarehoist.setId(autocarehoist.getId());

        partialUpdatedAutocarehoist.hoistcode(UPDATED_HOISTCODE).hoisttypename(UPDATED_HOISTTYPENAME).lmd(UPDATED_LMD);

        restAutocarehoistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarehoist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarehoist))
            )
            .andExpect(status().isOk());

        // Validate the Autocarehoist in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarehoistUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocarehoist, autocarehoist),
            getPersistedAutocarehoist(autocarehoist)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocarehoistWithPatch() throws Exception {
        // Initialize the database
        autocarehoistRepository.saveAndFlush(autocarehoist);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarehoist using partial update
        Autocarehoist partialUpdatedAutocarehoist = new Autocarehoist();
        partialUpdatedAutocarehoist.setId(autocarehoist.getId());

        partialUpdatedAutocarehoist
            .hoistname(UPDATED_HOISTNAME)
            .hoistcode(UPDATED_HOISTCODE)
            .hoisttypeid(UPDATED_HOISTTYPEID)
            .hoisttypename(UPDATED_HOISTTYPENAME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restAutocarehoistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarehoist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarehoist))
            )
            .andExpect(status().isOk());

        // Validate the Autocarehoist in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarehoistUpdatableFieldsEquals(partialUpdatedAutocarehoist, getPersistedAutocarehoist(partialUpdatedAutocarehoist));
    }

    @Test
    @Transactional
    void patchNonExistingAutocarehoist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarehoist.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarehoistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocarehoist.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarehoist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocarehoist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarehoist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarehoistMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarehoist))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocarehoist() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarehoist.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarehoistMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocarehoist)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarehoist in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocarehoist() throws Exception {
        // Initialize the database
        autocarehoistRepository.saveAndFlush(autocarehoist);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocarehoist
        restAutocarehoistMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocarehoist.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocarehoistRepository.count();
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

    protected Autocarehoist getPersistedAutocarehoist(Autocarehoist autocarehoist) {
        return autocarehoistRepository.findById(autocarehoist.getId()).orElseThrow();
    }

    protected void assertPersistedAutocarehoistToMatchAllProperties(Autocarehoist expectedAutocarehoist) {
        assertAutocarehoistAllPropertiesEquals(expectedAutocarehoist, getPersistedAutocarehoist(expectedAutocarehoist));
    }

    protected void assertPersistedAutocarehoistToMatchUpdatableProperties(Autocarehoist expectedAutocarehoist) {
        assertAutocarehoistAllUpdatablePropertiesEquals(expectedAutocarehoist, getPersistedAutocarehoist(expectedAutocarehoist));
    }
}
