package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocarecancelitemoptAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocarecancelitemopt;
import com.heavenscode.rac.repository.AutocarecancelitemoptRepository;
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
 * Integration tests for the {@link AutocarecancelitemoptResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocarecancelitemoptResourceIT {

    private static final String DEFAULT_CANCELOPTION = "AAAAAAAAAA";
    private static final String UPDATED_CANCELOPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/autocarecancelitemopts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocarecancelitemoptRepository autocarecancelitemoptRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocarecancelitemoptMockMvc;

    private Autocarecancelitemopt autocarecancelitemopt;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarecancelitemopt createEntity(EntityManager em) {
        Autocarecancelitemopt autocarecancelitemopt = new Autocarecancelitemopt().canceloption(DEFAULT_CANCELOPTION);
        return autocarecancelitemopt;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarecancelitemopt createUpdatedEntity(EntityManager em) {
        Autocarecancelitemopt autocarecancelitemopt = new Autocarecancelitemopt().canceloption(UPDATED_CANCELOPTION);
        return autocarecancelitemopt;
    }

    @BeforeEach
    public void initTest() {
        autocarecancelitemopt = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocarecancelitemopt
        var returnedAutocarecancelitemopt = om.readValue(
            restAutocarecancelitemoptMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarecancelitemopt)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocarecancelitemopt.class
        );

        // Validate the Autocarecancelitemopt in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocarecancelitemoptUpdatableFieldsEquals(
            returnedAutocarecancelitemopt,
            getPersistedAutocarecancelitemopt(returnedAutocarecancelitemopt)
        );
    }

    @Test
    @Transactional
    void createAutocarecancelitemoptWithExistingId() throws Exception {
        // Create the Autocarecancelitemopt with an existing ID
        autocarecancelitemopt.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocarecancelitemoptMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarecancelitemopt)))
            .andExpect(status().isBadRequest());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocarecancelitemopts() throws Exception {
        // Initialize the database
        autocarecancelitemoptRepository.saveAndFlush(autocarecancelitemopt);

        // Get all the autocarecancelitemoptList
        restAutocarecancelitemoptMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocarecancelitemopt.getId().intValue())))
            .andExpect(jsonPath("$.[*].canceloption").value(hasItem(DEFAULT_CANCELOPTION)));
    }

    @Test
    @Transactional
    void getAutocarecancelitemopt() throws Exception {
        // Initialize the database
        autocarecancelitemoptRepository.saveAndFlush(autocarecancelitemopt);

        // Get the autocarecancelitemopt
        restAutocarecancelitemoptMockMvc
            .perform(get(ENTITY_API_URL_ID, autocarecancelitemopt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocarecancelitemopt.getId().intValue()))
            .andExpect(jsonPath("$.canceloption").value(DEFAULT_CANCELOPTION));
    }

    @Test
    @Transactional
    void getNonExistingAutocarecancelitemopt() throws Exception {
        // Get the autocarecancelitemopt
        restAutocarecancelitemoptMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocarecancelitemopt() throws Exception {
        // Initialize the database
        autocarecancelitemoptRepository.saveAndFlush(autocarecancelitemopt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarecancelitemopt
        Autocarecancelitemopt updatedAutocarecancelitemopt = autocarecancelitemoptRepository
            .findById(autocarecancelitemopt.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedAutocarecancelitemopt are not directly saved in db
        em.detach(updatedAutocarecancelitemopt);
        updatedAutocarecancelitemopt.canceloption(UPDATED_CANCELOPTION);

        restAutocarecancelitemoptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocarecancelitemopt.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocarecancelitemopt))
            )
            .andExpect(status().isOk());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocarecancelitemoptToMatchAllProperties(updatedAutocarecancelitemopt);
    }

    @Test
    @Transactional
    void putNonExistingAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecancelitemopt.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarecancelitemoptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocarecancelitemopt.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarecancelitemopt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecancelitemopt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecancelitemoptMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarecancelitemopt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecancelitemopt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecancelitemoptMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarecancelitemopt)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocarecancelitemoptWithPatch() throws Exception {
        // Initialize the database
        autocarecancelitemoptRepository.saveAndFlush(autocarecancelitemopt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarecancelitemopt using partial update
        Autocarecancelitemopt partialUpdatedAutocarecancelitemopt = new Autocarecancelitemopt();
        partialUpdatedAutocarecancelitemopt.setId(autocarecancelitemopt.getId());

        partialUpdatedAutocarecancelitemopt.canceloption(UPDATED_CANCELOPTION);

        restAutocarecancelitemoptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarecancelitemopt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarecancelitemopt))
            )
            .andExpect(status().isOk());

        // Validate the Autocarecancelitemopt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarecancelitemoptUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocarecancelitemopt, autocarecancelitemopt),
            getPersistedAutocarecancelitemopt(autocarecancelitemopt)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocarecancelitemoptWithPatch() throws Exception {
        // Initialize the database
        autocarecancelitemoptRepository.saveAndFlush(autocarecancelitemopt);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarecancelitemopt using partial update
        Autocarecancelitemopt partialUpdatedAutocarecancelitemopt = new Autocarecancelitemopt();
        partialUpdatedAutocarecancelitemopt.setId(autocarecancelitemopt.getId());

        partialUpdatedAutocarecancelitemopt.canceloption(UPDATED_CANCELOPTION);

        restAutocarecancelitemoptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarecancelitemopt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarecancelitemopt))
            )
            .andExpect(status().isOk());

        // Validate the Autocarecancelitemopt in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarecancelitemoptUpdatableFieldsEquals(
            partialUpdatedAutocarecancelitemopt,
            getPersistedAutocarecancelitemopt(partialUpdatedAutocarecancelitemopt)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecancelitemopt.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarecancelitemoptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocarecancelitemopt.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarecancelitemopt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecancelitemopt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecancelitemoptMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarecancelitemopt))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocarecancelitemopt() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecancelitemopt.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecancelitemoptMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocarecancelitemopt)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarecancelitemopt in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocarecancelitemopt() throws Exception {
        // Initialize the database
        autocarecancelitemoptRepository.saveAndFlush(autocarecancelitemopt);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocarecancelitemopt
        restAutocarecancelitemoptMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocarecancelitemopt.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocarecancelitemoptRepository.count();
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

    protected Autocarecancelitemopt getPersistedAutocarecancelitemopt(Autocarecancelitemopt autocarecancelitemopt) {
        return autocarecancelitemoptRepository.findById(autocarecancelitemopt.getId()).orElseThrow();
    }

    protected void assertPersistedAutocarecancelitemoptToMatchAllProperties(Autocarecancelitemopt expectedAutocarecancelitemopt) {
        assertAutocarecancelitemoptAllPropertiesEquals(
            expectedAutocarecancelitemopt,
            getPersistedAutocarecancelitemopt(expectedAutocarecancelitemopt)
        );
    }

    protected void assertPersistedAutocarecancelitemoptToMatchUpdatableProperties(Autocarecancelitemopt expectedAutocarecancelitemopt) {
        assertAutocarecancelitemoptAllUpdatablePropertiesEquals(
            expectedAutocarecancelitemopt,
            getPersistedAutocarecancelitemopt(expectedAutocarecancelitemopt)
        );
    }
}
