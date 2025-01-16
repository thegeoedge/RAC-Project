package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.StocklocationsAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Stocklocations;
import com.heavenscode.rac.repository.StocklocationsRepository;
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
 * Integration tests for the {@link StocklocationsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StocklocationsResourceIT {

    private static final String DEFAULT_LOCATIONNAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIONNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCATIONCODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIONCODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/stocklocations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private StocklocationsRepository stocklocationsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStocklocationsMockMvc;

    private Stocklocations stocklocations;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stocklocations createEntity(EntityManager em) {
        Stocklocations stocklocations = new Stocklocations()
            .locationname(DEFAULT_LOCATIONNAME)
            .locationcode(DEFAULT_LOCATIONCODE)
            .description(DEFAULT_DESCRIPTION);
        return stocklocations;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Stocklocations createUpdatedEntity(EntityManager em) {
        Stocklocations stocklocations = new Stocklocations()
            .locationname(UPDATED_LOCATIONNAME)
            .locationcode(UPDATED_LOCATIONCODE)
            .description(UPDATED_DESCRIPTION);
        return stocklocations;
    }

    @BeforeEach
    public void initTest() {
        stocklocations = createEntity(em);
    }

    @Test
    @Transactional
    void createStocklocations() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Stocklocations
        var returnedStocklocations = om.readValue(
            restStocklocationsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(stocklocations)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Stocklocations.class
        );

        // Validate the Stocklocations in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertStocklocationsUpdatableFieldsEquals(returnedStocklocations, getPersistedStocklocations(returnedStocklocations));
    }

    @Test
    @Transactional
    void createStocklocationsWithExistingId() throws Exception {
        // Create the Stocklocations with an existing ID
        stocklocations.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStocklocationsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(stocklocations)))
            .andExpect(status().isBadRequest());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllStocklocations() throws Exception {
        // Initialize the database
        stocklocationsRepository.saveAndFlush(stocklocations);

        // Get all the stocklocationsList
        restStocklocationsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stocklocations.getId().intValue())))
            .andExpect(jsonPath("$.[*].locationname").value(hasItem(DEFAULT_LOCATIONNAME)))
            .andExpect(jsonPath("$.[*].locationcode").value(hasItem(DEFAULT_LOCATIONCODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getStocklocations() throws Exception {
        // Initialize the database
        stocklocationsRepository.saveAndFlush(stocklocations);

        // Get the stocklocations
        restStocklocationsMockMvc
            .perform(get(ENTITY_API_URL_ID, stocklocations.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(stocklocations.getId().intValue()))
            .andExpect(jsonPath("$.locationname").value(DEFAULT_LOCATIONNAME))
            .andExpect(jsonPath("$.locationcode").value(DEFAULT_LOCATIONCODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingStocklocations() throws Exception {
        // Get the stocklocations
        restStocklocationsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStocklocations() throws Exception {
        // Initialize the database
        stocklocationsRepository.saveAndFlush(stocklocations);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the stocklocations
        Stocklocations updatedStocklocations = stocklocationsRepository.findById(stocklocations.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedStocklocations are not directly saved in db
        em.detach(updatedStocklocations);
        updatedStocklocations.locationname(UPDATED_LOCATIONNAME).locationcode(UPDATED_LOCATIONCODE).description(UPDATED_DESCRIPTION);

        restStocklocationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedStocklocations.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedStocklocations))
            )
            .andExpect(status().isOk());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedStocklocationsToMatchAllProperties(updatedStocklocations);
    }

    @Test
    @Transactional
    void putNonExistingStocklocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stocklocations.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStocklocationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, stocklocations.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(stocklocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStocklocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stocklocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStocklocationsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(stocklocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStocklocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stocklocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStocklocationsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(stocklocations)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStocklocationsWithPatch() throws Exception {
        // Initialize the database
        stocklocationsRepository.saveAndFlush(stocklocations);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the stocklocations using partial update
        Stocklocations partialUpdatedStocklocations = new Stocklocations();
        partialUpdatedStocklocations.setId(stocklocations.getId());

        partialUpdatedStocklocations.locationname(UPDATED_LOCATIONNAME).locationcode(UPDATED_LOCATIONCODE).description(UPDATED_DESCRIPTION);

        restStocklocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStocklocations.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedStocklocations))
            )
            .andExpect(status().isOk());

        // Validate the Stocklocations in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertStocklocationsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedStocklocations, stocklocations),
            getPersistedStocklocations(stocklocations)
        );
    }

    @Test
    @Transactional
    void fullUpdateStocklocationsWithPatch() throws Exception {
        // Initialize the database
        stocklocationsRepository.saveAndFlush(stocklocations);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the stocklocations using partial update
        Stocklocations partialUpdatedStocklocations = new Stocklocations();
        partialUpdatedStocklocations.setId(stocklocations.getId());

        partialUpdatedStocklocations.locationname(UPDATED_LOCATIONNAME).locationcode(UPDATED_LOCATIONCODE).description(UPDATED_DESCRIPTION);

        restStocklocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStocklocations.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedStocklocations))
            )
            .andExpect(status().isOk());

        // Validate the Stocklocations in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertStocklocationsUpdatableFieldsEquals(partialUpdatedStocklocations, getPersistedStocklocations(partialUpdatedStocklocations));
    }

    @Test
    @Transactional
    void patchNonExistingStocklocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stocklocations.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStocklocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, stocklocations.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(stocklocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStocklocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stocklocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStocklocationsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(stocklocations))
            )
            .andExpect(status().isBadRequest());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStocklocations() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        stocklocations.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStocklocationsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(stocklocations)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Stocklocations in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStocklocations() throws Exception {
        // Initialize the database
        stocklocationsRepository.saveAndFlush(stocklocations);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the stocklocations
        restStocklocationsMockMvc
            .perform(delete(ENTITY_API_URL_ID, stocklocations.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return stocklocationsRepository.count();
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

    protected Stocklocations getPersistedStocklocations(Stocklocations stocklocations) {
        return stocklocationsRepository.findById(stocklocations.getId()).orElseThrow();
    }

    protected void assertPersistedStocklocationsToMatchAllProperties(Stocklocations expectedStocklocations) {
        assertStocklocationsAllPropertiesEquals(expectedStocklocations, getPersistedStocklocations(expectedStocklocations));
    }

    protected void assertPersistedStocklocationsToMatchUpdatableProperties(Stocklocations expectedStocklocations) {
        assertStocklocationsAllUpdatablePropertiesEquals(expectedStocklocations, getPersistedStocklocations(expectedStocklocations));
    }
}
