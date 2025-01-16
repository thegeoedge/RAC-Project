package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.CommonserviceoptionAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Commonserviceoption;
import com.heavenscode.rac.repository.CommonserviceoptionRepository;
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
 * Integration tests for the {@link CommonserviceoptionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommonserviceoptionResourceIT {

    private static final Integer DEFAULT_MAINID = 1;
    private static final Integer UPDATED_MAINID = 2;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final String ENTITY_API_URL = "/api/commonserviceoptions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CommonserviceoptionRepository commonserviceoptionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommonserviceoptionMockMvc;

    private Commonserviceoption commonserviceoption;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commonserviceoption createEntity(EntityManager em) {
        Commonserviceoption commonserviceoption = new Commonserviceoption()
            .mainid(DEFAULT_MAINID)
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .value(DEFAULT_VALUE)
            .isactive(DEFAULT_ISACTIVE)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU);
        return commonserviceoption;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Commonserviceoption createUpdatedEntity(EntityManager em) {
        Commonserviceoption commonserviceoption = new Commonserviceoption()
            .mainid(UPDATED_MAINID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .value(UPDATED_VALUE)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);
        return commonserviceoption;
    }

    @BeforeEach
    public void initTest() {
        commonserviceoption = createEntity(em);
    }

    @Test
    @Transactional
    void createCommonserviceoption() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Commonserviceoption
        var returnedCommonserviceoption = om.readValue(
            restCommonserviceoptionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(commonserviceoption)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Commonserviceoption.class
        );

        // Validate the Commonserviceoption in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCommonserviceoptionUpdatableFieldsEquals(
            returnedCommonserviceoption,
            getPersistedCommonserviceoption(returnedCommonserviceoption)
        );
    }

    @Test
    @Transactional
    void createCommonserviceoptionWithExistingId() throws Exception {
        // Create the Commonserviceoption with an existing ID
        commonserviceoption.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommonserviceoptionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(commonserviceoption)))
            .andExpect(status().isBadRequest());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommonserviceoptions() throws Exception {
        // Initialize the database
        commonserviceoptionRepository.saveAndFlush(commonserviceoption);

        // Get all the commonserviceoptionList
        restCommonserviceoptionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(commonserviceoption.getId().intValue())))
            .andExpect(jsonPath("$.[*].mainid").value(hasItem(DEFAULT_MAINID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)));
    }

    @Test
    @Transactional
    void getCommonserviceoption() throws Exception {
        // Initialize the database
        commonserviceoptionRepository.saveAndFlush(commonserviceoption);

        // Get the commonserviceoption
        restCommonserviceoptionMockMvc
            .perform(get(ENTITY_API_URL_ID, commonserviceoption.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(commonserviceoption.getId().intValue()))
            .andExpect(jsonPath("$.mainid").value(DEFAULT_MAINID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU));
    }

    @Test
    @Transactional
    void getNonExistingCommonserviceoption() throws Exception {
        // Get the commonserviceoption
        restCommonserviceoptionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommonserviceoption() throws Exception {
        // Initialize the database
        commonserviceoptionRepository.saveAndFlush(commonserviceoption);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the commonserviceoption
        Commonserviceoption updatedCommonserviceoption = commonserviceoptionRepository.findById(commonserviceoption.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCommonserviceoption are not directly saved in db
        em.detach(updatedCommonserviceoption);
        updatedCommonserviceoption
            .mainid(UPDATED_MAINID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .value(UPDATED_VALUE)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restCommonserviceoptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCommonserviceoption.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCommonserviceoption))
            )
            .andExpect(status().isOk());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCommonserviceoptionToMatchAllProperties(updatedCommonserviceoption);
    }

    @Test
    @Transactional
    void putNonExistingCommonserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        commonserviceoption.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommonserviceoptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, commonserviceoption.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(commonserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommonserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        commonserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommonserviceoptionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(commonserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommonserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        commonserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommonserviceoptionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(commonserviceoption)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommonserviceoptionWithPatch() throws Exception {
        // Initialize the database
        commonserviceoptionRepository.saveAndFlush(commonserviceoption);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the commonserviceoption using partial update
        Commonserviceoption partialUpdatedCommonserviceoption = new Commonserviceoption();
        partialUpdatedCommonserviceoption.setId(commonserviceoption.getId());

        partialUpdatedCommonserviceoption.description(UPDATED_DESCRIPTION).isactive(UPDATED_ISACTIVE);

        restCommonserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommonserviceoption.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommonserviceoption))
            )
            .andExpect(status().isOk());

        // Validate the Commonserviceoption in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommonserviceoptionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCommonserviceoption, commonserviceoption),
            getPersistedCommonserviceoption(commonserviceoption)
        );
    }

    @Test
    @Transactional
    void fullUpdateCommonserviceoptionWithPatch() throws Exception {
        // Initialize the database
        commonserviceoptionRepository.saveAndFlush(commonserviceoption);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the commonserviceoption using partial update
        Commonserviceoption partialUpdatedCommonserviceoption = new Commonserviceoption();
        partialUpdatedCommonserviceoption.setId(commonserviceoption.getId());

        partialUpdatedCommonserviceoption
            .mainid(UPDATED_MAINID)
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .value(UPDATED_VALUE)
            .isactive(UPDATED_ISACTIVE)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restCommonserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommonserviceoption.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommonserviceoption))
            )
            .andExpect(status().isOk());

        // Validate the Commonserviceoption in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommonserviceoptionUpdatableFieldsEquals(
            partialUpdatedCommonserviceoption,
            getPersistedCommonserviceoption(partialUpdatedCommonserviceoption)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCommonserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        commonserviceoption.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommonserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, commonserviceoption.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(commonserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommonserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        commonserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommonserviceoptionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(commonserviceoption))
            )
            .andExpect(status().isBadRequest());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommonserviceoption() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        commonserviceoption.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommonserviceoptionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(commonserviceoption)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Commonserviceoption in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommonserviceoption() throws Exception {
        // Initialize the database
        commonserviceoptionRepository.saveAndFlush(commonserviceoption);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the commonserviceoption
        restCommonserviceoptionMockMvc
            .perform(delete(ENTITY_API_URL_ID, commonserviceoption.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return commonserviceoptionRepository.count();
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

    protected Commonserviceoption getPersistedCommonserviceoption(Commonserviceoption commonserviceoption) {
        return commonserviceoptionRepository.findById(commonserviceoption.getId()).orElseThrow();
    }

    protected void assertPersistedCommonserviceoptionToMatchAllProperties(Commonserviceoption expectedCommonserviceoption) {
        assertCommonserviceoptionAllPropertiesEquals(
            expectedCommonserviceoption,
            getPersistedCommonserviceoption(expectedCommonserviceoption)
        );
    }

    protected void assertPersistedCommonserviceoptionToMatchUpdatableProperties(Commonserviceoption expectedCommonserviceoption) {
        assertCommonserviceoptionAllUpdatablePropertiesEquals(
            expectedCommonserviceoption,
            getPersistedCommonserviceoption(expectedCommonserviceoption)
        );
    }
}
