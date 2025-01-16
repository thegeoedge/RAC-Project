package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.EmpSectiontblAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.EmpSectiontbl;
import com.heavenscode.rac.repository.EmpSectiontblRepository;
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
 * Integration tests for the {@link EmpSectiontblResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmpSectiontblResourceIT {

    private static final Long DEFAULT_EMPID = 1L;
    private static final Long UPDATED_EMPID = 2L;

    private static final Integer DEFAULT_SECTIONID = 1;
    private static final Integer UPDATED_SECTIONID = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final String ENTITY_API_URL = "/api/emp-sectiontbls";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EmpSectiontblRepository empSectiontblRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmpSectiontblMockMvc;

    private EmpSectiontbl empSectiontbl;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmpSectiontbl createEntity(EntityManager em) {
        EmpSectiontbl empSectiontbl = new EmpSectiontbl()
            .empid(DEFAULT_EMPID)
            .sectionid(DEFAULT_SECTIONID)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU);
        return empSectiontbl;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmpSectiontbl createUpdatedEntity(EntityManager em) {
        EmpSectiontbl empSectiontbl = new EmpSectiontbl()
            .empid(UPDATED_EMPID)
            .sectionid(UPDATED_SECTIONID)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);
        return empSectiontbl;
    }

    @BeforeEach
    public void initTest() {
        empSectiontbl = createEntity(em);
    }

    @Test
    @Transactional
    void createEmpSectiontbl() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EmpSectiontbl
        var returnedEmpSectiontbl = om.readValue(
            restEmpSectiontblMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empSectiontbl)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EmpSectiontbl.class
        );

        // Validate the EmpSectiontbl in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEmpSectiontblUpdatableFieldsEquals(returnedEmpSectiontbl, getPersistedEmpSectiontbl(returnedEmpSectiontbl));
    }

    @Test
    @Transactional
    void createEmpSectiontblWithExistingId() throws Exception {
        // Create the EmpSectiontbl with an existing ID
        empSectiontbl.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpSectiontblMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empSectiontbl)))
            .andExpect(status().isBadRequest());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEmpSectiontbls() throws Exception {
        // Initialize the database
        empSectiontblRepository.saveAndFlush(empSectiontbl);

        // Get all the empSectiontblList
        restEmpSectiontblMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empSectiontbl.getId().intValue())))
            .andExpect(jsonPath("$.[*].empid").value(hasItem(DEFAULT_EMPID.intValue())))
            .andExpect(jsonPath("$.[*].sectionid").value(hasItem(DEFAULT_SECTIONID)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)));
    }

    @Test
    @Transactional
    void getEmpSectiontbl() throws Exception {
        // Initialize the database
        empSectiontblRepository.saveAndFlush(empSectiontbl);

        // Get the empSectiontbl
        restEmpSectiontblMockMvc
            .perform(get(ENTITY_API_URL_ID, empSectiontbl.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(empSectiontbl.getId().intValue()))
            .andExpect(jsonPath("$.empid").value(DEFAULT_EMPID.intValue()))
            .andExpect(jsonPath("$.sectionid").value(DEFAULT_SECTIONID))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU));
    }

    @Test
    @Transactional
    void getNonExistingEmpSectiontbl() throws Exception {
        // Get the empSectiontbl
        restEmpSectiontblMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEmpSectiontbl() throws Exception {
        // Initialize the database
        empSectiontblRepository.saveAndFlush(empSectiontbl);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empSectiontbl
        EmpSectiontbl updatedEmpSectiontbl = empSectiontblRepository.findById(empSectiontbl.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEmpSectiontbl are not directly saved in db
        em.detach(updatedEmpSectiontbl);
        updatedEmpSectiontbl.empid(UPDATED_EMPID).sectionid(UPDATED_SECTIONID).lmd(UPDATED_LMD).lmu(UPDATED_LMU);

        restEmpSectiontblMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEmpSectiontbl.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEmpSectiontbl))
            )
            .andExpect(status().isOk());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEmpSectiontblToMatchAllProperties(updatedEmpSectiontbl);
    }

    @Test
    @Transactional
    void putNonExistingEmpSectiontbl() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empSectiontbl.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpSectiontblMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empSectiontbl.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empSectiontbl))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmpSectiontbl() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empSectiontbl.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpSectiontblMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empSectiontbl))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmpSectiontbl() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empSectiontbl.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpSectiontblMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empSectiontbl)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmpSectiontblWithPatch() throws Exception {
        // Initialize the database
        empSectiontblRepository.saveAndFlush(empSectiontbl);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empSectiontbl using partial update
        EmpSectiontbl partialUpdatedEmpSectiontbl = new EmpSectiontbl();
        partialUpdatedEmpSectiontbl.setId(empSectiontbl.getId());

        partialUpdatedEmpSectiontbl.sectionid(UPDATED_SECTIONID).lmd(UPDATED_LMD);

        restEmpSectiontblMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpSectiontbl.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEmpSectiontbl))
            )
            .andExpect(status().isOk());

        // Validate the EmpSectiontbl in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEmpSectiontblUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEmpSectiontbl, empSectiontbl),
            getPersistedEmpSectiontbl(empSectiontbl)
        );
    }

    @Test
    @Transactional
    void fullUpdateEmpSectiontblWithPatch() throws Exception {
        // Initialize the database
        empSectiontblRepository.saveAndFlush(empSectiontbl);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empSectiontbl using partial update
        EmpSectiontbl partialUpdatedEmpSectiontbl = new EmpSectiontbl();
        partialUpdatedEmpSectiontbl.setId(empSectiontbl.getId());

        partialUpdatedEmpSectiontbl.empid(UPDATED_EMPID).sectionid(UPDATED_SECTIONID).lmd(UPDATED_LMD).lmu(UPDATED_LMU);

        restEmpSectiontblMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpSectiontbl.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEmpSectiontbl))
            )
            .andExpect(status().isOk());

        // Validate the EmpSectiontbl in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEmpSectiontblUpdatableFieldsEquals(partialUpdatedEmpSectiontbl, getPersistedEmpSectiontbl(partialUpdatedEmpSectiontbl));
    }

    @Test
    @Transactional
    void patchNonExistingEmpSectiontbl() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empSectiontbl.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpSectiontblMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, empSectiontbl.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(empSectiontbl))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmpSectiontbl() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empSectiontbl.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpSectiontblMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(empSectiontbl))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmpSectiontbl() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empSectiontbl.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpSectiontblMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(empSectiontbl)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmpSectiontbl in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmpSectiontbl() throws Exception {
        // Initialize the database
        empSectiontblRepository.saveAndFlush(empSectiontbl);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the empSectiontbl
        restEmpSectiontblMockMvc
            .perform(delete(ENTITY_API_URL_ID, empSectiontbl.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return empSectiontblRepository.count();
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

    protected EmpSectiontbl getPersistedEmpSectiontbl(EmpSectiontbl empSectiontbl) {
        return empSectiontblRepository.findById(empSectiontbl.getId()).orElseThrow();
    }

    protected void assertPersistedEmpSectiontblToMatchAllProperties(EmpSectiontbl expectedEmpSectiontbl) {
        assertEmpSectiontblAllPropertiesEquals(expectedEmpSectiontbl, getPersistedEmpSectiontbl(expectedEmpSectiontbl));
    }

    protected void assertPersistedEmpSectiontblToMatchUpdatableProperties(EmpSectiontbl expectedEmpSectiontbl) {
        assertEmpSectiontblAllUpdatablePropertiesEquals(expectedEmpSectiontbl, getPersistedEmpSectiontbl(expectedEmpSectiontbl));
    }
}
