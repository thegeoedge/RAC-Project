package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.EmpJobcommissionAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.EmpJobcommission;
import com.heavenscode.rac.repository.EmpJobcommissionRepository;
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
 * Integration tests for the {@link EmpJobcommissionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EmpJobcommissionResourceIT {

    private static final Long DEFAULT_VEHCATID = 1L;
    private static final Long UPDATED_VEHCATID = 2L;

    private static final Long DEFAULT_AUTOJOBCATID = 1L;
    private static final Long UPDATED_AUTOJOBCATID = 2L;

    private static final Float DEFAULT_FIRSTCOM = 1F;
    private static final Float UPDATED_FIRSTCOM = 2F;

    private static final Float DEFAULT_SECONDCOM = 1F;
    private static final Float UPDATED_SECONDCOM = 2F;

    private static final Float DEFAULT_THIRDCOM = 1F;
    private static final Float UPDATED_THIRDCOM = 2F;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final String ENTITY_API_URL = "/api/emp-jobcommissions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private EmpJobcommissionRepository empJobcommissionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmpJobcommissionMockMvc;

    private EmpJobcommission empJobcommission;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmpJobcommission createEntity(EntityManager em) {
        EmpJobcommission empJobcommission = new EmpJobcommission()
            .vehcatid(DEFAULT_VEHCATID)
            .autojobcatid(DEFAULT_AUTOJOBCATID)
            .firstcom(DEFAULT_FIRSTCOM)
            .secondcom(DEFAULT_SECONDCOM)
            .thirdcom(DEFAULT_THIRDCOM)
            .lmd(DEFAULT_LMD)
            .lmu(DEFAULT_LMU);
        return empJobcommission;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EmpJobcommission createUpdatedEntity(EntityManager em) {
        EmpJobcommission empJobcommission = new EmpJobcommission()
            .vehcatid(UPDATED_VEHCATID)
            .autojobcatid(UPDATED_AUTOJOBCATID)
            .firstcom(UPDATED_FIRSTCOM)
            .secondcom(UPDATED_SECONDCOM)
            .thirdcom(UPDATED_THIRDCOM)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);
        return empJobcommission;
    }

    @BeforeEach
    public void initTest() {
        empJobcommission = createEntity(em);
    }

    @Test
    @Transactional
    void createEmpJobcommission() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the EmpJobcommission
        var returnedEmpJobcommission = om.readValue(
            restEmpJobcommissionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empJobcommission)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            EmpJobcommission.class
        );

        // Validate the EmpJobcommission in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertEmpJobcommissionUpdatableFieldsEquals(returnedEmpJobcommission, getPersistedEmpJobcommission(returnedEmpJobcommission));
    }

    @Test
    @Transactional
    void createEmpJobcommissionWithExistingId() throws Exception {
        // Create the EmpJobcommission with an existing ID
        empJobcommission.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmpJobcommissionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empJobcommission)))
            .andExpect(status().isBadRequest());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkVehcatidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        empJobcommission.setVehcatid(null);

        // Create the EmpJobcommission, which fails.

        restEmpJobcommissionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empJobcommission)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkAutojobcatidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        empJobcommission.setAutojobcatid(null);

        // Create the EmpJobcommission, which fails.

        restEmpJobcommissionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empJobcommission)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllEmpJobcommissions() throws Exception {
        // Initialize the database
        empJobcommissionRepository.saveAndFlush(empJobcommission);

        // Get all the empJobcommissionList
        restEmpJobcommissionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(empJobcommission.getId().intValue())))
            .andExpect(jsonPath("$.[*].vehcatid").value(hasItem(DEFAULT_VEHCATID.intValue())))
            .andExpect(jsonPath("$.[*].autojobcatid").value(hasItem(DEFAULT_AUTOJOBCATID.intValue())))
            .andExpect(jsonPath("$.[*].firstcom").value(hasItem(DEFAULT_FIRSTCOM.doubleValue())))
            .andExpect(jsonPath("$.[*].secondcom").value(hasItem(DEFAULT_SECONDCOM.doubleValue())))
            .andExpect(jsonPath("$.[*].thirdcom").value(hasItem(DEFAULT_THIRDCOM.doubleValue())))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)));
    }

    @Test
    @Transactional
    void getEmpJobcommission() throws Exception {
        // Initialize the database
        empJobcommissionRepository.saveAndFlush(empJobcommission);

        // Get the empJobcommission
        restEmpJobcommissionMockMvc
            .perform(get(ENTITY_API_URL_ID, empJobcommission.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(empJobcommission.getId().intValue()))
            .andExpect(jsonPath("$.vehcatid").value(DEFAULT_VEHCATID.intValue()))
            .andExpect(jsonPath("$.autojobcatid").value(DEFAULT_AUTOJOBCATID.intValue()))
            .andExpect(jsonPath("$.firstcom").value(DEFAULT_FIRSTCOM.doubleValue()))
            .andExpect(jsonPath("$.secondcom").value(DEFAULT_SECONDCOM.doubleValue()))
            .andExpect(jsonPath("$.thirdcom").value(DEFAULT_THIRDCOM.doubleValue()))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU));
    }

    @Test
    @Transactional
    void getNonExistingEmpJobcommission() throws Exception {
        // Get the empJobcommission
        restEmpJobcommissionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEmpJobcommission() throws Exception {
        // Initialize the database
        empJobcommissionRepository.saveAndFlush(empJobcommission);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empJobcommission
        EmpJobcommission updatedEmpJobcommission = empJobcommissionRepository.findById(empJobcommission.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedEmpJobcommission are not directly saved in db
        em.detach(updatedEmpJobcommission);
        updatedEmpJobcommission
            .vehcatid(UPDATED_VEHCATID)
            .autojobcatid(UPDATED_AUTOJOBCATID)
            .firstcom(UPDATED_FIRSTCOM)
            .secondcom(UPDATED_SECONDCOM)
            .thirdcom(UPDATED_THIRDCOM)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restEmpJobcommissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedEmpJobcommission.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedEmpJobcommission))
            )
            .andExpect(status().isOk());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedEmpJobcommissionToMatchAllProperties(updatedEmpJobcommission);
    }

    @Test
    @Transactional
    void putNonExistingEmpJobcommission() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empJobcommission.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpJobcommissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, empJobcommission.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empJobcommission))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEmpJobcommission() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empJobcommission.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpJobcommissionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(empJobcommission))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEmpJobcommission() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empJobcommission.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpJobcommissionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(empJobcommission)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEmpJobcommissionWithPatch() throws Exception {
        // Initialize the database
        empJobcommissionRepository.saveAndFlush(empJobcommission);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empJobcommission using partial update
        EmpJobcommission partialUpdatedEmpJobcommission = new EmpJobcommission();
        partialUpdatedEmpJobcommission.setId(empJobcommission.getId());

        restEmpJobcommissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpJobcommission.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEmpJobcommission))
            )
            .andExpect(status().isOk());

        // Validate the EmpJobcommission in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEmpJobcommissionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedEmpJobcommission, empJobcommission),
            getPersistedEmpJobcommission(empJobcommission)
        );
    }

    @Test
    @Transactional
    void fullUpdateEmpJobcommissionWithPatch() throws Exception {
        // Initialize the database
        empJobcommissionRepository.saveAndFlush(empJobcommission);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the empJobcommission using partial update
        EmpJobcommission partialUpdatedEmpJobcommission = new EmpJobcommission();
        partialUpdatedEmpJobcommission.setId(empJobcommission.getId());

        partialUpdatedEmpJobcommission
            .vehcatid(UPDATED_VEHCATID)
            .autojobcatid(UPDATED_AUTOJOBCATID)
            .firstcom(UPDATED_FIRSTCOM)
            .secondcom(UPDATED_SECONDCOM)
            .thirdcom(UPDATED_THIRDCOM)
            .lmd(UPDATED_LMD)
            .lmu(UPDATED_LMU);

        restEmpJobcommissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEmpJobcommission.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedEmpJobcommission))
            )
            .andExpect(status().isOk());

        // Validate the EmpJobcommission in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertEmpJobcommissionUpdatableFieldsEquals(
            partialUpdatedEmpJobcommission,
            getPersistedEmpJobcommission(partialUpdatedEmpJobcommission)
        );
    }

    @Test
    @Transactional
    void patchNonExistingEmpJobcommission() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empJobcommission.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmpJobcommissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, empJobcommission.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(empJobcommission))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEmpJobcommission() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empJobcommission.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpJobcommissionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(empJobcommission))
            )
            .andExpect(status().isBadRequest());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEmpJobcommission() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        empJobcommission.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEmpJobcommissionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(empJobcommission)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EmpJobcommission in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEmpJobcommission() throws Exception {
        // Initialize the database
        empJobcommissionRepository.saveAndFlush(empJobcommission);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the empJobcommission
        restEmpJobcommissionMockMvc
            .perform(delete(ENTITY_API_URL_ID, empJobcommission.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return empJobcommissionRepository.count();
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

    protected EmpJobcommission getPersistedEmpJobcommission(EmpJobcommission empJobcommission) {
        return empJobcommissionRepository.findById(empJobcommission.getId()).orElseThrow();
    }

    protected void assertPersistedEmpJobcommissionToMatchAllProperties(EmpJobcommission expectedEmpJobcommission) {
        assertEmpJobcommissionAllPropertiesEquals(expectedEmpJobcommission, getPersistedEmpJobcommission(expectedEmpJobcommission));
    }

    protected void assertPersistedEmpJobcommissionToMatchUpdatableProperties(EmpJobcommission expectedEmpJobcommission) {
        assertEmpJobcommissionAllUpdatablePropertiesEquals(
            expectedEmpJobcommission,
            getPersistedEmpJobcommission(expectedEmpJobcommission)
        );
    }
}
