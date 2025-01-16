package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocaretimetableAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocaretimetable;
import com.heavenscode.rac.repository.AutocaretimetableRepository;
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
 * Integration tests for the {@link AutocaretimetableResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocaretimetableResourceIT {

    private static final Integer DEFAULT_HOISTTYPEID = 1;
    private static final Integer UPDATED_HOISTTYPEID = 2;

    private static final String DEFAULT_HOISTTYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_HOISTTYPENAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_HOISTTIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HOISTTIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/autocaretimetables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocaretimetableRepository autocaretimetableRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocaretimetableMockMvc;

    private Autocaretimetable autocaretimetable;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocaretimetable createEntity(EntityManager em) {
        Autocaretimetable autocaretimetable = new Autocaretimetable()
            .hoisttypeid(DEFAULT_HOISTTYPEID)
            .hoisttypename(DEFAULT_HOISTTYPENAME)
            .hoisttime(DEFAULT_HOISTTIME);
        return autocaretimetable;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocaretimetable createUpdatedEntity(EntityManager em) {
        Autocaretimetable autocaretimetable = new Autocaretimetable()
            .hoisttypeid(UPDATED_HOISTTYPEID)
            .hoisttypename(UPDATED_HOISTTYPENAME)
            .hoisttime(UPDATED_HOISTTIME);
        return autocaretimetable;
    }

    @BeforeEach
    public void initTest() {
        autocaretimetable = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocaretimetable() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocaretimetable
        var returnedAutocaretimetable = om.readValue(
            restAutocaretimetableMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocaretimetable)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocaretimetable.class
        );

        // Validate the Autocaretimetable in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocaretimetableUpdatableFieldsEquals(returnedAutocaretimetable, getPersistedAutocaretimetable(returnedAutocaretimetable));
    }

    @Test
    @Transactional
    void createAutocaretimetableWithExistingId() throws Exception {
        // Create the Autocaretimetable with an existing ID
        autocaretimetable.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocaretimetableMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocaretimetable)))
            .andExpect(status().isBadRequest());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocaretimetables() throws Exception {
        // Initialize the database
        autocaretimetableRepository.saveAndFlush(autocaretimetable);

        // Get all the autocaretimetableList
        restAutocaretimetableMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocaretimetable.getId().intValue())))
            .andExpect(jsonPath("$.[*].hoisttypeid").value(hasItem(DEFAULT_HOISTTYPEID)))
            .andExpect(jsonPath("$.[*].hoisttypename").value(hasItem(DEFAULT_HOISTTYPENAME)))
            .andExpect(jsonPath("$.[*].hoisttime").value(hasItem(DEFAULT_HOISTTIME.toString())));
    }

    @Test
    @Transactional
    void getAutocaretimetable() throws Exception {
        // Initialize the database
        autocaretimetableRepository.saveAndFlush(autocaretimetable);

        // Get the autocaretimetable
        restAutocaretimetableMockMvc
            .perform(get(ENTITY_API_URL_ID, autocaretimetable.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocaretimetable.getId().intValue()))
            .andExpect(jsonPath("$.hoisttypeid").value(DEFAULT_HOISTTYPEID))
            .andExpect(jsonPath("$.hoisttypename").value(DEFAULT_HOISTTYPENAME))
            .andExpect(jsonPath("$.hoisttime").value(DEFAULT_HOISTTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAutocaretimetable() throws Exception {
        // Get the autocaretimetable
        restAutocaretimetableMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocaretimetable() throws Exception {
        // Initialize the database
        autocaretimetableRepository.saveAndFlush(autocaretimetable);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocaretimetable
        Autocaretimetable updatedAutocaretimetable = autocaretimetableRepository.findById(autocaretimetable.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocaretimetable are not directly saved in db
        em.detach(updatedAutocaretimetable);
        updatedAutocaretimetable.hoisttypeid(UPDATED_HOISTTYPEID).hoisttypename(UPDATED_HOISTTYPENAME).hoisttime(UPDATED_HOISTTIME);

        restAutocaretimetableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocaretimetable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocaretimetable))
            )
            .andExpect(status().isOk());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocaretimetableToMatchAllProperties(updatedAutocaretimetable);
    }

    @Test
    @Transactional
    void putNonExistingAutocaretimetable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocaretimetable.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocaretimetableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocaretimetable.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocaretimetable))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocaretimetable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocaretimetable.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocaretimetableMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocaretimetable))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocaretimetable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocaretimetable.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocaretimetableMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocaretimetable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocaretimetableWithPatch() throws Exception {
        // Initialize the database
        autocaretimetableRepository.saveAndFlush(autocaretimetable);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocaretimetable using partial update
        Autocaretimetable partialUpdatedAutocaretimetable = new Autocaretimetable();
        partialUpdatedAutocaretimetable.setId(autocaretimetable.getId());

        restAutocaretimetableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocaretimetable.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocaretimetable))
            )
            .andExpect(status().isOk());

        // Validate the Autocaretimetable in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocaretimetableUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocaretimetable, autocaretimetable),
            getPersistedAutocaretimetable(autocaretimetable)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocaretimetableWithPatch() throws Exception {
        // Initialize the database
        autocaretimetableRepository.saveAndFlush(autocaretimetable);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocaretimetable using partial update
        Autocaretimetable partialUpdatedAutocaretimetable = new Autocaretimetable();
        partialUpdatedAutocaretimetable.setId(autocaretimetable.getId());

        partialUpdatedAutocaretimetable.hoisttypeid(UPDATED_HOISTTYPEID).hoisttypename(UPDATED_HOISTTYPENAME).hoisttime(UPDATED_HOISTTIME);

        restAutocaretimetableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocaretimetable.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocaretimetable))
            )
            .andExpect(status().isOk());

        // Validate the Autocaretimetable in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocaretimetableUpdatableFieldsEquals(
            partialUpdatedAutocaretimetable,
            getPersistedAutocaretimetable(partialUpdatedAutocaretimetable)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocaretimetable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocaretimetable.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocaretimetableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocaretimetable.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocaretimetable))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocaretimetable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocaretimetable.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocaretimetableMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocaretimetable))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocaretimetable() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocaretimetable.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocaretimetableMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocaretimetable)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocaretimetable in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocaretimetable() throws Exception {
        // Initialize the database
        autocaretimetableRepository.saveAndFlush(autocaretimetable);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocaretimetable
        restAutocaretimetableMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocaretimetable.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocaretimetableRepository.count();
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

    protected Autocaretimetable getPersistedAutocaretimetable(Autocaretimetable autocaretimetable) {
        return autocaretimetableRepository.findById(autocaretimetable.getId()).orElseThrow();
    }

    protected void assertPersistedAutocaretimetableToMatchAllProperties(Autocaretimetable expectedAutocaretimetable) {
        assertAutocaretimetableAllPropertiesEquals(expectedAutocaretimetable, getPersistedAutocaretimetable(expectedAutocaretimetable));
    }

    protected void assertPersistedAutocaretimetableToMatchUpdatableProperties(Autocaretimetable expectedAutocaretimetable) {
        assertAutocaretimetableAllUpdatablePropertiesEquals(
            expectedAutocaretimetable,
            getPersistedAutocaretimetable(expectedAutocaretimetable)
        );
    }
}
