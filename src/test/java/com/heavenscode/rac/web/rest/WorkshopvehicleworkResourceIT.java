package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.WorkshopvehicleworkAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Workshopvehiclework;
import com.heavenscode.rac.repository.WorkshopvehicleworkRepository;
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
 * Integration tests for the {@link WorkshopvehicleworkResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkshopvehicleworkResourceIT {

    private static final Integer DEFAULT_JOBID = 1;
    private static final Integer UPDATED_JOBID = 2;

    private static final Integer DEFAULT_VEHICLEID = 1;
    private static final Integer UPDATED_VEHICLEID = 2;

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTNO = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTNO = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLENO = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLENO = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLEBRAND = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLEBRAND = "BBBBBBBBBB";

    private static final String DEFAULT_VEHICLEMODEL = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLEMODEL = "BBBBBBBBBB";

    private static final String DEFAULT_MILEAGE = "AAAAAAAAAA";
    private static final String UPDATED_MILEAGE = "BBBBBBBBBB";

    private static final Instant DEFAULT_ADDEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_ISCALLTOCUSTOMER = false;
    private static final Boolean UPDATED_ISCALLTOCUSTOMER = true;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CALLDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CALLDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/workshopvehicleworks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WorkshopvehicleworkRepository workshopvehicleworkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkshopvehicleworkMockMvc;

    private Workshopvehiclework workshopvehiclework;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workshopvehiclework createEntity(EntityManager em) {
        Workshopvehiclework workshopvehiclework = new Workshopvehiclework()
            .jobid(DEFAULT_JOBID)
            .vehicleid(DEFAULT_VEHICLEID)
            .customerid(DEFAULT_CUSTOMERID)
            .customername(DEFAULT_CUSTOMERNAME)
            .contactno(DEFAULT_CONTACTNO)
            .vehicleno(DEFAULT_VEHICLENO)
            .vehiclebrand(DEFAULT_VEHICLEBRAND)
            .vehiclemodel(DEFAULT_VEHICLEMODEL)
            .mileage(DEFAULT_MILEAGE)
            .addeddate(DEFAULT_ADDEDDATE)
            .iscalltocustomer(DEFAULT_ISCALLTOCUSTOMER)
            .remarks(DEFAULT_REMARKS)
            .calldate(DEFAULT_CALLDATE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD);
        return workshopvehiclework;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Workshopvehiclework createUpdatedEntity(EntityManager em) {
        Workshopvehiclework workshopvehiclework = new Workshopvehiclework()
            .jobid(UPDATED_JOBID)
            .vehicleid(UPDATED_VEHICLEID)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .contactno(UPDATED_CONTACTNO)
            .vehicleno(UPDATED_VEHICLENO)
            .vehiclebrand(UPDATED_VEHICLEBRAND)
            .vehiclemodel(UPDATED_VEHICLEMODEL)
            .mileage(UPDATED_MILEAGE)
            .addeddate(UPDATED_ADDEDDATE)
            .iscalltocustomer(UPDATED_ISCALLTOCUSTOMER)
            .remarks(UPDATED_REMARKS)
            .calldate(UPDATED_CALLDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);
        return workshopvehiclework;
    }

    @BeforeEach
    public void initTest() {
        workshopvehiclework = createEntity(em);
    }

    @Test
    @Transactional
    void createWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Workshopvehiclework
        var returnedWorkshopvehiclework = om.readValue(
            restWorkshopvehicleworkMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workshopvehiclework)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Workshopvehiclework.class
        );

        // Validate the Workshopvehiclework in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWorkshopvehicleworkUpdatableFieldsEquals(
            returnedWorkshopvehiclework,
            getPersistedWorkshopvehiclework(returnedWorkshopvehiclework)
        );
    }

    @Test
    @Transactional
    void createWorkshopvehicleworkWithExistingId() throws Exception {
        // Create the Workshopvehiclework with an existing ID
        workshopvehiclework.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkshopvehicleworkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workshopvehiclework)))
            .andExpect(status().isBadRequest());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWorkshopvehicleworks() throws Exception {
        // Initialize the database
        workshopvehicleworkRepository.saveAndFlush(workshopvehiclework);

        // Get all the workshopvehicleworkList
        restWorkshopvehicleworkMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(workshopvehiclework.getId().intValue())))
            .andExpect(jsonPath("$.[*].jobid").value(hasItem(DEFAULT_JOBID)))
            .andExpect(jsonPath("$.[*].vehicleid").value(hasItem(DEFAULT_VEHICLEID)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].contactno").value(hasItem(DEFAULT_CONTACTNO)))
            .andExpect(jsonPath("$.[*].vehicleno").value(hasItem(DEFAULT_VEHICLENO)))
            .andExpect(jsonPath("$.[*].vehiclebrand").value(hasItem(DEFAULT_VEHICLEBRAND)))
            .andExpect(jsonPath("$.[*].vehiclemodel").value(hasItem(DEFAULT_VEHICLEMODEL)))
            .andExpect(jsonPath("$.[*].mileage").value(hasItem(DEFAULT_MILEAGE)))
            .andExpect(jsonPath("$.[*].addeddate").value(hasItem(DEFAULT_ADDEDDATE.toString())))
            .andExpect(jsonPath("$.[*].iscalltocustomer").value(hasItem(DEFAULT_ISCALLTOCUSTOMER.booleanValue())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].calldate").value(hasItem(DEFAULT_CALLDATE.toString())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getWorkshopvehiclework() throws Exception {
        // Initialize the database
        workshopvehicleworkRepository.saveAndFlush(workshopvehiclework);

        // Get the workshopvehiclework
        restWorkshopvehicleworkMockMvc
            .perform(get(ENTITY_API_URL_ID, workshopvehiclework.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(workshopvehiclework.getId().intValue()))
            .andExpect(jsonPath("$.jobid").value(DEFAULT_JOBID))
            .andExpect(jsonPath("$.vehicleid").value(DEFAULT_VEHICLEID))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.contactno").value(DEFAULT_CONTACTNO))
            .andExpect(jsonPath("$.vehicleno").value(DEFAULT_VEHICLENO))
            .andExpect(jsonPath("$.vehiclebrand").value(DEFAULT_VEHICLEBRAND))
            .andExpect(jsonPath("$.vehiclemodel").value(DEFAULT_VEHICLEMODEL))
            .andExpect(jsonPath("$.mileage").value(DEFAULT_MILEAGE))
            .andExpect(jsonPath("$.addeddate").value(DEFAULT_ADDEDDATE.toString()))
            .andExpect(jsonPath("$.iscalltocustomer").value(DEFAULT_ISCALLTOCUSTOMER.booleanValue()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.calldate").value(DEFAULT_CALLDATE.toString()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWorkshopvehiclework() throws Exception {
        // Get the workshopvehiclework
        restWorkshopvehicleworkMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWorkshopvehiclework() throws Exception {
        // Initialize the database
        workshopvehicleworkRepository.saveAndFlush(workshopvehiclework);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workshopvehiclework
        Workshopvehiclework updatedWorkshopvehiclework = workshopvehicleworkRepository.findById(workshopvehiclework.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWorkshopvehiclework are not directly saved in db
        em.detach(updatedWorkshopvehiclework);
        updatedWorkshopvehiclework
            .jobid(UPDATED_JOBID)
            .vehicleid(UPDATED_VEHICLEID)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .contactno(UPDATED_CONTACTNO)
            .vehicleno(UPDATED_VEHICLENO)
            .vehiclebrand(UPDATED_VEHICLEBRAND)
            .vehiclemodel(UPDATED_VEHICLEMODEL)
            .mileage(UPDATED_MILEAGE)
            .addeddate(UPDATED_ADDEDDATE)
            .iscalltocustomer(UPDATED_ISCALLTOCUSTOMER)
            .remarks(UPDATED_REMARKS)
            .calldate(UPDATED_CALLDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restWorkshopvehicleworkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWorkshopvehiclework.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWorkshopvehiclework))
            )
            .andExpect(status().isOk());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWorkshopvehicleworkToMatchAllProperties(updatedWorkshopvehiclework);
    }

    @Test
    @Transactional
    void putNonExistingWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopvehiclework.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkshopvehicleworkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, workshopvehiclework.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(workshopvehiclework))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopvehiclework.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopvehicleworkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(workshopvehiclework))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopvehiclework.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopvehicleworkMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(workshopvehiclework)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkshopvehicleworkWithPatch() throws Exception {
        // Initialize the database
        workshopvehicleworkRepository.saveAndFlush(workshopvehiclework);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workshopvehiclework using partial update
        Workshopvehiclework partialUpdatedWorkshopvehiclework = new Workshopvehiclework();
        partialUpdatedWorkshopvehiclework.setId(workshopvehiclework.getId());

        partialUpdatedWorkshopvehiclework
            .jobid(UPDATED_JOBID)
            .customername(UPDATED_CUSTOMERNAME)
            .iscalltocustomer(UPDATED_ISCALLTOCUSTOMER)
            .calldate(UPDATED_CALLDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restWorkshopvehicleworkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkshopvehiclework.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWorkshopvehiclework))
            )
            .andExpect(status().isOk());

        // Validate the Workshopvehiclework in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkshopvehicleworkUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedWorkshopvehiclework, workshopvehiclework),
            getPersistedWorkshopvehiclework(workshopvehiclework)
        );
    }

    @Test
    @Transactional
    void fullUpdateWorkshopvehicleworkWithPatch() throws Exception {
        // Initialize the database
        workshopvehicleworkRepository.saveAndFlush(workshopvehiclework);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the workshopvehiclework using partial update
        Workshopvehiclework partialUpdatedWorkshopvehiclework = new Workshopvehiclework();
        partialUpdatedWorkshopvehiclework.setId(workshopvehiclework.getId());

        partialUpdatedWorkshopvehiclework
            .jobid(UPDATED_JOBID)
            .vehicleid(UPDATED_VEHICLEID)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .contactno(UPDATED_CONTACTNO)
            .vehicleno(UPDATED_VEHICLENO)
            .vehiclebrand(UPDATED_VEHICLEBRAND)
            .vehiclemodel(UPDATED_VEHICLEMODEL)
            .mileage(UPDATED_MILEAGE)
            .addeddate(UPDATED_ADDEDDATE)
            .iscalltocustomer(UPDATED_ISCALLTOCUSTOMER)
            .remarks(UPDATED_REMARKS)
            .calldate(UPDATED_CALLDATE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restWorkshopvehicleworkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWorkshopvehiclework.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWorkshopvehiclework))
            )
            .andExpect(status().isOk());

        // Validate the Workshopvehiclework in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkshopvehicleworkUpdatableFieldsEquals(
            partialUpdatedWorkshopvehiclework,
            getPersistedWorkshopvehiclework(partialUpdatedWorkshopvehiclework)
        );
    }

    @Test
    @Transactional
    void patchNonExistingWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopvehiclework.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkshopvehicleworkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, workshopvehiclework.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(workshopvehiclework))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopvehiclework.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopvehicleworkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(workshopvehiclework))
            )
            .andExpect(status().isBadRequest());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWorkshopvehiclework() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        workshopvehiclework.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkshopvehicleworkMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(workshopvehiclework)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Workshopvehiclework in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWorkshopvehiclework() throws Exception {
        // Initialize the database
        workshopvehicleworkRepository.saveAndFlush(workshopvehiclework);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the workshopvehiclework
        restWorkshopvehicleworkMockMvc
            .perform(delete(ENTITY_API_URL_ID, workshopvehiclework.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return workshopvehicleworkRepository.count();
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

    protected Workshopvehiclework getPersistedWorkshopvehiclework(Workshopvehiclework workshopvehiclework) {
        return workshopvehicleworkRepository.findById(workshopvehiclework.getId()).orElseThrow();
    }

    protected void assertPersistedWorkshopvehicleworkToMatchAllProperties(Workshopvehiclework expectedWorkshopvehiclework) {
        assertWorkshopvehicleworkAllPropertiesEquals(
            expectedWorkshopvehiclework,
            getPersistedWorkshopvehiclework(expectedWorkshopvehiclework)
        );
    }

    protected void assertPersistedWorkshopvehicleworkToMatchUpdatableProperties(Workshopvehiclework expectedWorkshopvehiclework) {
        assertWorkshopvehicleworkAllUpdatablePropertiesEquals(
            expectedWorkshopvehiclework,
            getPersistedWorkshopvehiclework(expectedWorkshopvehiclework)
        );
    }
}
