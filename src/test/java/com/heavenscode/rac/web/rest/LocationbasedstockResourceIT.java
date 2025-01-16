package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.LocationbasedstockAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Locationbasedstock;
import com.heavenscode.rac.repository.LocationbasedstockRepository;
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
 * Integration tests for the {@link LocationbasedstockResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LocationbasedstockResourceIT {

    private static final Integer DEFAULT_ITEMID = 1;
    private static final Integer UPDATED_ITEMID = 2;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOCATIONID = 1;
    private static final Integer UPDATED_LOCATIONID = 2;

    private static final String DEFAULT_LOCATIONCODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIONCODE = "BBBBBBBBBB";

    private static final Float DEFAULT_AVAILABLEQUANTITY = 1F;
    private static final Float UPDATED_AVAILABLEQUANTITY = 2F;

    private static final Boolean DEFAULT_HASBATCHES = false;
    private static final Boolean UPDATED_HASBATCHES = true;

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/locationbasedstocks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LocationbasedstockRepository locationbasedstockRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocationbasedstockMockMvc;

    private Locationbasedstock locationbasedstock;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Locationbasedstock createEntity(EntityManager em) {
        Locationbasedstock locationbasedstock = new Locationbasedstock()
            .itemid(DEFAULT_ITEMID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .locationid(DEFAULT_LOCATIONID)
            .locationcode(DEFAULT_LOCATIONCODE)
            .availablequantity(DEFAULT_AVAILABLEQUANTITY)
            .hasbatches(DEFAULT_HASBATCHES)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD);
        return locationbasedstock;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Locationbasedstock createUpdatedEntity(EntityManager em) {
        Locationbasedstock locationbasedstock = new Locationbasedstock()
            .itemid(UPDATED_ITEMID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);
        return locationbasedstock;
    }

    @BeforeEach
    public void initTest() {
        locationbasedstock = createEntity(em);
    }

    @Test
    @Transactional
    void createLocationbasedstock() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Locationbasedstock
        var returnedLocationbasedstock = om.readValue(
            restLocationbasedstockMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(locationbasedstock)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Locationbasedstock.class
        );

        // Validate the Locationbasedstock in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLocationbasedstockUpdatableFieldsEquals(
            returnedLocationbasedstock,
            getPersistedLocationbasedstock(returnedLocationbasedstock)
        );
    }

    @Test
    @Transactional
    void createLocationbasedstockWithExistingId() throws Exception {
        // Create the Locationbasedstock with an existing ID
        locationbasedstock.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocationbasedstockMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(locationbasedstock)))
            .andExpect(status().isBadRequest());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkItemidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        locationbasedstock.setItemid(null);

        // Create the Locationbasedstock, which fails.

        restLocationbasedstockMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(locationbasedstock)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkLocationidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        locationbasedstock.setLocationid(null);

        // Create the Locationbasedstock, which fails.

        restLocationbasedstockMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(locationbasedstock)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllLocationbasedstocks() throws Exception {
        // Initialize the database
        locationbasedstockRepository.saveAndFlush(locationbasedstock);

        // Get all the locationbasedstockList
        restLocationbasedstockMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(locationbasedstock.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemid").value(hasItem(DEFAULT_ITEMID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].locationid").value(hasItem(DEFAULT_LOCATIONID)))
            .andExpect(jsonPath("$.[*].locationcode").value(hasItem(DEFAULT_LOCATIONCODE)))
            .andExpect(jsonPath("$.[*].availablequantity").value(hasItem(DEFAULT_AVAILABLEQUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].hasbatches").value(hasItem(DEFAULT_HASBATCHES.booleanValue())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getLocationbasedstock() throws Exception {
        // Initialize the database
        locationbasedstockRepository.saveAndFlush(locationbasedstock);

        // Get the locationbasedstock
        restLocationbasedstockMockMvc
            .perform(get(ENTITY_API_URL_ID, locationbasedstock.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(locationbasedstock.getId().intValue()))
            .andExpect(jsonPath("$.itemid").value(DEFAULT_ITEMID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.locationid").value(DEFAULT_LOCATIONID))
            .andExpect(jsonPath("$.locationcode").value(DEFAULT_LOCATIONCODE))
            .andExpect(jsonPath("$.availablequantity").value(DEFAULT_AVAILABLEQUANTITY.doubleValue()))
            .andExpect(jsonPath("$.hasbatches").value(DEFAULT_HASBATCHES.booleanValue()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingLocationbasedstock() throws Exception {
        // Get the locationbasedstock
        restLocationbasedstockMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLocationbasedstock() throws Exception {
        // Initialize the database
        locationbasedstockRepository.saveAndFlush(locationbasedstock);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the locationbasedstock
        Locationbasedstock updatedLocationbasedstock = locationbasedstockRepository.findById(locationbasedstock.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLocationbasedstock are not directly saved in db
        em.detach(updatedLocationbasedstock);
        updatedLocationbasedstock
            .itemid(UPDATED_ITEMID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restLocationbasedstockMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLocationbasedstock.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLocationbasedstock))
            )
            .andExpect(status().isOk());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLocationbasedstockToMatchAllProperties(updatedLocationbasedstock);
    }

    @Test
    @Transactional
    void putNonExistingLocationbasedstock() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        locationbasedstock.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationbasedstockMockMvc
            .perform(
                put(ENTITY_API_URL_ID, locationbasedstock.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(locationbasedstock))
            )
            .andExpect(status().isBadRequest());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLocationbasedstock() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        locationbasedstock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocationbasedstockMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(locationbasedstock))
            )
            .andExpect(status().isBadRequest());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLocationbasedstock() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        locationbasedstock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocationbasedstockMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(locationbasedstock)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLocationbasedstockWithPatch() throws Exception {
        // Initialize the database
        locationbasedstockRepository.saveAndFlush(locationbasedstock);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the locationbasedstock using partial update
        Locationbasedstock partialUpdatedLocationbasedstock = new Locationbasedstock();
        partialUpdatedLocationbasedstock.setId(locationbasedstock.getId());

        partialUpdatedLocationbasedstock
            .code(UPDATED_CODE)
            .locationcode(UPDATED_LOCATIONCODE)
            .hasbatches(UPDATED_HASBATCHES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restLocationbasedstockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLocationbasedstock.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLocationbasedstock))
            )
            .andExpect(status().isOk());

        // Validate the Locationbasedstock in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLocationbasedstockUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLocationbasedstock, locationbasedstock),
            getPersistedLocationbasedstock(locationbasedstock)
        );
    }

    @Test
    @Transactional
    void fullUpdateLocationbasedstockWithPatch() throws Exception {
        // Initialize the database
        locationbasedstockRepository.saveAndFlush(locationbasedstock);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the locationbasedstock using partial update
        Locationbasedstock partialUpdatedLocationbasedstock = new Locationbasedstock();
        partialUpdatedLocationbasedstock.setId(locationbasedstock.getId());

        partialUpdatedLocationbasedstock
            .itemid(UPDATED_ITEMID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restLocationbasedstockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLocationbasedstock.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLocationbasedstock))
            )
            .andExpect(status().isOk());

        // Validate the Locationbasedstock in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLocationbasedstockUpdatableFieldsEquals(
            partialUpdatedLocationbasedstock,
            getPersistedLocationbasedstock(partialUpdatedLocationbasedstock)
        );
    }

    @Test
    @Transactional
    void patchNonExistingLocationbasedstock() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        locationbasedstock.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocationbasedstockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, locationbasedstock.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(locationbasedstock))
            )
            .andExpect(status().isBadRequest());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLocationbasedstock() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        locationbasedstock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocationbasedstockMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(locationbasedstock))
            )
            .andExpect(status().isBadRequest());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLocationbasedstock() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        locationbasedstock.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocationbasedstockMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(locationbasedstock)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Locationbasedstock in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLocationbasedstock() throws Exception {
        // Initialize the database
        locationbasedstockRepository.saveAndFlush(locationbasedstock);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the locationbasedstock
        restLocationbasedstockMockMvc
            .perform(delete(ENTITY_API_URL_ID, locationbasedstock.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return locationbasedstockRepository.count();
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

    protected Locationbasedstock getPersistedLocationbasedstock(Locationbasedstock locationbasedstock) {
        return locationbasedstockRepository.findById(locationbasedstock.getId()).orElseThrow();
    }

    protected void assertPersistedLocationbasedstockToMatchAllProperties(Locationbasedstock expectedLocationbasedstock) {
        assertLocationbasedstockAllPropertiesEquals(expectedLocationbasedstock, getPersistedLocationbasedstock(expectedLocationbasedstock));
    }

    protected void assertPersistedLocationbasedstockToMatchUpdatableProperties(Locationbasedstock expectedLocationbasedstock) {
        assertLocationbasedstockAllUpdatablePropertiesEquals(
            expectedLocationbasedstock,
            getPersistedLocationbasedstock(expectedLocationbasedstock)
        );
    }
}
