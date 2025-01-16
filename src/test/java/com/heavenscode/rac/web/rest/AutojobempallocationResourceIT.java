package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutojobempallocationAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autojobempallocation;
import com.heavenscode.rac.repository.AutojobempallocationRepository;
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
 * Integration tests for the {@link AutojobempallocationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutojobempallocationResourceIT {

    private static final Integer DEFAULT_JOBID = 1;
    private static final Integer UPDATED_JOBID = 2;

    private static final Integer DEFAULT_CATEGORYID = 1;
    private static final Integer UPDATED_CATEGORYID = 2;

    private static final Integer DEFAULT_EMPPOSITION = 1;
    private static final Integer UPDATED_EMPPOSITION = 2;

    private static final Integer DEFAULT_EMPID = 1;
    private static final Integer UPDATED_EMPID = 2;

    private static final String DEFAULT_EMPNAME = "AAAAAAAAAA";
    private static final String UPDATED_EMPNAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_ALLOCATETIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ALLOCATETIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_JOBDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOBDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_COMMISSION = 1F;
    private static final Float UPDATED_COMMISSION = 2F;

    private static final Boolean DEFAULT_ISCOMMISSIONPAID = false;
    private static final Boolean UPDATED_ISCOMMISSIONPAID = true;

    private static final Instant DEFAULT_STARTTIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_STARTTIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ENDTIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENDTIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/autojobempallocations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutojobempallocationRepository autojobempallocationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutojobempallocationMockMvc;

    private Autojobempallocation autojobempallocation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autojobempallocation createEntity(EntityManager em) {
        Autojobempallocation autojobempallocation = new Autojobempallocation()
            .jobid(DEFAULT_JOBID)
            .categoryid(DEFAULT_CATEGORYID)
            .empposition(DEFAULT_EMPPOSITION)
            .empid(DEFAULT_EMPID)
            .empname(DEFAULT_EMPNAME)
            .allocatetime(DEFAULT_ALLOCATETIME)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .jobdate(DEFAULT_JOBDATE)
            .commission(DEFAULT_COMMISSION)
            .iscommissionpaid(DEFAULT_ISCOMMISSIONPAID)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return autojobempallocation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autojobempallocation createUpdatedEntity(EntityManager em) {
        Autojobempallocation autojobempallocation = new Autojobempallocation()
            .jobid(UPDATED_JOBID)
            .categoryid(UPDATED_CATEGORYID)
            .empposition(UPDATED_EMPPOSITION)
            .empid(UPDATED_EMPID)
            .empname(UPDATED_EMPNAME)
            .allocatetime(UPDATED_ALLOCATETIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .jobdate(UPDATED_JOBDATE)
            .commission(UPDATED_COMMISSION)
            .iscommissionpaid(UPDATED_ISCOMMISSIONPAID)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return autojobempallocation;
    }

    @BeforeEach
    public void initTest() {
        autojobempallocation = createEntity(em);
    }

    @Test
    @Transactional
    void createAutojobempallocation() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autojobempallocation
        var returnedAutojobempallocation = om.readValue(
            restAutojobempallocationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autojobempallocation)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autojobempallocation.class
        );

        // Validate the Autojobempallocation in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutojobempallocationUpdatableFieldsEquals(
            returnedAutojobempallocation,
            getPersistedAutojobempallocation(returnedAutojobempallocation)
        );
    }

    @Test
    @Transactional
    void createAutojobempallocationWithExistingId() throws Exception {
        // Create the Autojobempallocation with an existing ID
        autojobempallocation.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutojobempallocationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autojobempallocation)))
            .andExpect(status().isBadRequest());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutojobempallocations() throws Exception {
        // Initialize the database
        autojobempallocationRepository.saveAndFlush(autojobempallocation);

        // Get all the autojobempallocationList
        restAutojobempallocationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autojobempallocation.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)))
            .andExpect(jsonPath("$.[*].categoryid").value(hasItem(DEFAULT_CATEGORYID)))
            .andExpect(jsonPath("$.[*].empposition").value(hasItem(DEFAULT_EMPPOSITION)))
            .andExpect(jsonPath("$.[*].empid").value(hasItem(DEFAULT_EMPID)))
            .andExpect(jsonPath("$.[*].empname").value(hasItem(DEFAULT_EMPNAME)))
            .andExpect(jsonPath("$.[*].allocatetime").value(hasItem(DEFAULT_ALLOCATETIME.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].jobdate").value(hasItem(DEFAULT_JOBDATE.toString())))
            .andExpect(jsonPath("$.[*].commission").value(hasItem(DEFAULT_COMMISSION.doubleValue())))
            .andExpect(jsonPath("$.[*].iscommissionpaid").value(hasItem(DEFAULT_ISCOMMISSIONPAID.booleanValue())))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getAutojobempallocation() throws Exception {
        // Initialize the database
        autojobempallocationRepository.saveAndFlush(autojobempallocation);

        // Get the autojobempallocation
        restAutojobempallocationMockMvc
            .perform(get(ENTITY_API_URL_ID, autojobempallocation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autojobempallocation.getId().intValue()))
            .andExpect(jsonPath("$.jobid").value(DEFAULT_JOBID))
            .andExpect(jsonPath("$.categoryid").value(DEFAULT_CATEGORYID))
            .andExpect(jsonPath("$.empposition").value(DEFAULT_EMPPOSITION))
            .andExpect(jsonPath("$.empid").value(DEFAULT_EMPID))
            .andExpect(jsonPath("$.empname").value(DEFAULT_EMPNAME))
            .andExpect(jsonPath("$.allocatetime").value(DEFAULT_ALLOCATETIME.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.jobdate").value(DEFAULT_JOBDATE.toString()))
            .andExpect(jsonPath("$.commission").value(DEFAULT_COMMISSION.doubleValue()))
            .andExpect(jsonPath("$.iscommissionpaid").value(DEFAULT_ISCOMMISSIONPAID.booleanValue()))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAutojobempallocation() throws Exception {
        // Get the autojobempallocation
        restAutojobempallocationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutojobempallocation() throws Exception {
        // Initialize the database
        autojobempallocationRepository.saveAndFlush(autojobempallocation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autojobempallocation
        Autojobempallocation updatedAutojobempallocation = autojobempallocationRepository
            .findById(autojobempallocation.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedAutojobempallocation are not directly saved in db
        em.detach(updatedAutojobempallocation);
        updatedAutojobempallocation
            .jobid(UPDATED_JOBID)
            .categoryid(UPDATED_CATEGORYID)
            .empposition(UPDATED_EMPPOSITION)
            .empid(UPDATED_EMPID)
            .empname(UPDATED_EMPNAME)
            .allocatetime(UPDATED_ALLOCATETIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .jobdate(UPDATED_JOBDATE)
            .commission(UPDATED_COMMISSION)
            .iscommissionpaid(UPDATED_ISCOMMISSIONPAID)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restAutojobempallocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutojobempallocation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutojobempallocation))
            )
            .andExpect(status().isOk());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutojobempallocationToMatchAllProperties(updatedAutojobempallocation);
    }

    @Test
    @Transactional
    void putNonExistingAutojobempallocation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobempallocation.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutojobempallocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autojobempallocation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autojobempallocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutojobempallocation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobempallocation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobempallocationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autojobempallocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutojobempallocation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobempallocation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobempallocationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autojobempallocation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutojobempallocationWithPatch() throws Exception {
        // Initialize the database
        autojobempallocationRepository.saveAndFlush(autojobempallocation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autojobempallocation using partial update
        Autojobempallocation partialUpdatedAutojobempallocation = new Autojobempallocation();
        partialUpdatedAutojobempallocation.setId(autojobempallocation.getId());

        partialUpdatedAutojobempallocation
            .categoryid(UPDATED_CATEGORYID)
            .empid(UPDATED_EMPID)
            .empname(UPDATED_EMPNAME)
            .allocatetime(UPDATED_ALLOCATETIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .jobdate(UPDATED_JOBDATE)
            .commission(UPDATED_COMMISSION)
            .endtime(UPDATED_ENDTIME);

        restAutojobempallocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutojobempallocation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutojobempallocation))
            )
            .andExpect(status().isOk());

        // Validate the Autojobempallocation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutojobempallocationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutojobempallocation, autojobempallocation),
            getPersistedAutojobempallocation(autojobempallocation)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutojobempallocationWithPatch() throws Exception {
        // Initialize the database
        autojobempallocationRepository.saveAndFlush(autojobempallocation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autojobempallocation using partial update
        Autojobempallocation partialUpdatedAutojobempallocation = new Autojobempallocation();
        partialUpdatedAutojobempallocation.setId(autojobempallocation.getId());

        partialUpdatedAutojobempallocation
            .jobid(UPDATED_JOBID)
            .categoryid(UPDATED_CATEGORYID)
            .empposition(UPDATED_EMPPOSITION)
            .empid(UPDATED_EMPID)
            .empname(UPDATED_EMPNAME)
            .allocatetime(UPDATED_ALLOCATETIME)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .jobdate(UPDATED_JOBDATE)
            .commission(UPDATED_COMMISSION)
            .iscommissionpaid(UPDATED_ISCOMMISSIONPAID)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restAutojobempallocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutojobempallocation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutojobempallocation))
            )
            .andExpect(status().isOk());

        // Validate the Autojobempallocation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutojobempallocationUpdatableFieldsEquals(
            partialUpdatedAutojobempallocation,
            getPersistedAutojobempallocation(partialUpdatedAutojobempallocation)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutojobempallocation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobempallocation.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutojobempallocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autojobempallocation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autojobempallocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutojobempallocation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobempallocation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobempallocationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autojobempallocation))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutojobempallocation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autojobempallocation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutojobempallocationMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autojobempallocation)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autojobempallocation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutojobempallocation() throws Exception {
        // Initialize the database
        autojobempallocationRepository.saveAndFlush(autojobempallocation);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autojobempallocation
        restAutojobempallocationMockMvc
            .perform(delete(ENTITY_API_URL_ID, autojobempallocation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autojobempallocationRepository.count();
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

    protected Autojobempallocation getPersistedAutojobempallocation(Autojobempallocation autojobempallocation) {
        return autojobempallocationRepository.findById(autojobempallocation.getId()).orElseThrow();
    }

    protected void assertPersistedAutojobempallocationToMatchAllProperties(Autojobempallocation expectedAutojobempallocation) {
        assertAutojobempallocationAllPropertiesEquals(
            expectedAutojobempallocation,
            getPersistedAutojobempallocation(expectedAutojobempallocation)
        );
    }

    protected void assertPersistedAutojobempallocationToMatchUpdatableProperties(Autojobempallocation expectedAutojobempallocation) {
        assertAutojobempallocationAllUpdatablePropertiesEquals(
            expectedAutojobempallocation,
            getPersistedAutojobempallocation(expectedAutojobempallocation)
        );
    }
}
