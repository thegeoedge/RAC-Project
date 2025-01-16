package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.HoisttypeAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Hoisttype;
import com.heavenscode.rac.repository.HoisttypeRepository;
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
 * Integration tests for the {@link HoisttypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HoisttypeResourceIT {

    private static final String DEFAULT_HOISTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_HOISTTYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/hoisttypes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HoisttypeRepository hoisttypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHoisttypeMockMvc;

    private Hoisttype hoisttype;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hoisttype createEntity(EntityManager em) {
        Hoisttype hoisttype = new Hoisttype().hoisttype(DEFAULT_HOISTTYPE).lmu(DEFAULT_LMU).lmd(DEFAULT_LMD);
        return hoisttype;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hoisttype createUpdatedEntity(EntityManager em) {
        Hoisttype hoisttype = new Hoisttype().hoisttype(UPDATED_HOISTTYPE).lmu(UPDATED_LMU).lmd(UPDATED_LMD);
        return hoisttype;
    }

    @BeforeEach
    public void initTest() {
        hoisttype = createEntity(em);
    }

    @Test
    @Transactional
    void createHoisttype() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Hoisttype
        var returnedHoisttype = om.readValue(
            restHoisttypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hoisttype)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Hoisttype.class
        );

        // Validate the Hoisttype in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertHoisttypeUpdatableFieldsEquals(returnedHoisttype, getPersistedHoisttype(returnedHoisttype));
    }

    @Test
    @Transactional
    void createHoisttypeWithExistingId() throws Exception {
        // Create the Hoisttype with an existing ID
        hoisttype.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHoisttypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hoisttype)))
            .andExpect(status().isBadRequest());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHoisttypes() throws Exception {
        // Initialize the database
        hoisttypeRepository.saveAndFlush(hoisttype);

        // Get all the hoisttypeList
        restHoisttypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hoisttype.getId().intValue())))
            .andExpect(jsonPath("$.[*].hoisttype").value(hasItem(DEFAULT_HOISTTYPE)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getHoisttype() throws Exception {
        // Initialize the database
        hoisttypeRepository.saveAndFlush(hoisttype);

        // Get the hoisttype
        restHoisttypeMockMvc
            .perform(get(ENTITY_API_URL_ID, hoisttype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hoisttype.getId().intValue()))
            .andExpect(jsonPath("$.hoisttype").value(DEFAULT_HOISTTYPE))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHoisttype() throws Exception {
        // Get the hoisttype
        restHoisttypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHoisttype() throws Exception {
        // Initialize the database
        hoisttypeRepository.saveAndFlush(hoisttype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hoisttype
        Hoisttype updatedHoisttype = hoisttypeRepository.findById(hoisttype.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHoisttype are not directly saved in db
        em.detach(updatedHoisttype);
        updatedHoisttype.hoisttype(UPDATED_HOISTTYPE).lmu(UPDATED_LMU).lmd(UPDATED_LMD);

        restHoisttypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHoisttype.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedHoisttype))
            )
            .andExpect(status().isOk());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHoisttypeToMatchAllProperties(updatedHoisttype);
    }

    @Test
    @Transactional
    void putNonExistingHoisttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hoisttype.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoisttypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hoisttype.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hoisttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHoisttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hoisttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoisttypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hoisttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHoisttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hoisttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoisttypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hoisttype)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHoisttypeWithPatch() throws Exception {
        // Initialize the database
        hoisttypeRepository.saveAndFlush(hoisttype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hoisttype using partial update
        Hoisttype partialUpdatedHoisttype = new Hoisttype();
        partialUpdatedHoisttype.setId(hoisttype.getId());

        partialUpdatedHoisttype.hoisttype(UPDATED_HOISTTYPE).lmu(UPDATED_LMU);

        restHoisttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoisttype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHoisttype))
            )
            .andExpect(status().isOk());

        // Validate the Hoisttype in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHoisttypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHoisttype, hoisttype),
            getPersistedHoisttype(hoisttype)
        );
    }

    @Test
    @Transactional
    void fullUpdateHoisttypeWithPatch() throws Exception {
        // Initialize the database
        hoisttypeRepository.saveAndFlush(hoisttype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hoisttype using partial update
        Hoisttype partialUpdatedHoisttype = new Hoisttype();
        partialUpdatedHoisttype.setId(hoisttype.getId());

        partialUpdatedHoisttype.hoisttype(UPDATED_HOISTTYPE).lmu(UPDATED_LMU).lmd(UPDATED_LMD);

        restHoisttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHoisttype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHoisttype))
            )
            .andExpect(status().isOk());

        // Validate the Hoisttype in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHoisttypeUpdatableFieldsEquals(partialUpdatedHoisttype, getPersistedHoisttype(partialUpdatedHoisttype));
    }

    @Test
    @Transactional
    void patchNonExistingHoisttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hoisttype.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHoisttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hoisttype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hoisttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHoisttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hoisttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoisttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hoisttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHoisttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hoisttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHoisttypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hoisttype)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hoisttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHoisttype() throws Exception {
        // Initialize the database
        hoisttypeRepository.saveAndFlush(hoisttype);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hoisttype
        restHoisttypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, hoisttype.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hoisttypeRepository.count();
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

    protected Hoisttype getPersistedHoisttype(Hoisttype hoisttype) {
        return hoisttypeRepository.findById(hoisttype.getId()).orElseThrow();
    }

    protected void assertPersistedHoisttypeToMatchAllProperties(Hoisttype expectedHoisttype) {
        assertHoisttypeAllPropertiesEquals(expectedHoisttype, getPersistedHoisttype(expectedHoisttype));
    }

    protected void assertPersistedHoisttypeToMatchUpdatableProperties(Hoisttype expectedHoisttype) {
        assertHoisttypeAllUpdatablePropertiesEquals(expectedHoisttype, getPersistedHoisttype(expectedHoisttype));
    }
}
