package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.TaxesAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static com.heavenscode.rac.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Taxes;
import com.heavenscode.rac.repository.TaxesRepository;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TaxesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TaxesResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EFFECTIVEFROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVEFROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_EFFECTIVETO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EFFECTIVETO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTAGE = new BigDecimal(2);

    private static final Float DEFAULT_FIXEDAMOUNT = 1F;
    private static final Float UPDATED_FIXEDAMOUNT = 2F;

    private static final Boolean DEFAULT_ISMANUAL = false;
    private static final Boolean UPDATED_ISMANUAL = true;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/taxes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaxesRepository taxesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTaxesMockMvc;

    private Taxes taxes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taxes createEntity(EntityManager em) {
        Taxes taxes = new Taxes()
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .effectivefrom(DEFAULT_EFFECTIVEFROM)
            .effectiveto(DEFAULT_EFFECTIVETO)
            .percentage(DEFAULT_PERCENTAGE)
            .fixedamount(DEFAULT_FIXEDAMOUNT)
            .ismanual(DEFAULT_ISMANUAL)
            .isactive(DEFAULT_ISACTIVE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD);
        return taxes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Taxes createUpdatedEntity(EntityManager em) {
        Taxes taxes = new Taxes()
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .effectivefrom(UPDATED_EFFECTIVEFROM)
            .effectiveto(UPDATED_EFFECTIVETO)
            .percentage(UPDATED_PERCENTAGE)
            .fixedamount(UPDATED_FIXEDAMOUNT)
            .ismanual(UPDATED_ISMANUAL)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);
        return taxes;
    }

    @BeforeEach
    public void initTest() {
        taxes = createEntity(em);
    }

    @Test
    @Transactional
    void createTaxes() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Taxes
        var returnedTaxes = om.readValue(
            restTaxesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taxes)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Taxes.class
        );

        // Validate the Taxes in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTaxesUpdatableFieldsEquals(returnedTaxes, getPersistedTaxes(returnedTaxes));
    }

    @Test
    @Transactional
    void createTaxesWithExistingId() throws Exception {
        // Create the Taxes with an existing ID
        taxes.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTaxesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taxes)))
            .andExpect(status().isBadRequest());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTaxes() throws Exception {
        // Initialize the database
        taxesRepository.saveAndFlush(taxes);

        // Get all the taxesList
        restTaxesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(taxes.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].effectivefrom").value(hasItem(DEFAULT_EFFECTIVEFROM.toString())))
            .andExpect(jsonPath("$.[*].effectiveto").value(hasItem(DEFAULT_EFFECTIVETO.toString())))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(sameNumber(DEFAULT_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].fixedamount").value(hasItem(DEFAULT_FIXEDAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].ismanual").value(hasItem(DEFAULT_ISMANUAL.booleanValue())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())));
    }

    @Test
    @Transactional
    void getTaxes() throws Exception {
        // Initialize the database
        taxesRepository.saveAndFlush(taxes);

        // Get the taxes
        restTaxesMockMvc
            .perform(get(ENTITY_API_URL_ID, taxes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(taxes.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.effectivefrom").value(DEFAULT_EFFECTIVEFROM.toString()))
            .andExpect(jsonPath("$.effectiveto").value(DEFAULT_EFFECTIVETO.toString()))
            .andExpect(jsonPath("$.percentage").value(sameNumber(DEFAULT_PERCENTAGE)))
            .andExpect(jsonPath("$.fixedamount").value(DEFAULT_FIXEDAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.ismanual").value(DEFAULT_ISMANUAL.booleanValue()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTaxes() throws Exception {
        // Get the taxes
        restTaxesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTaxes() throws Exception {
        // Initialize the database
        taxesRepository.saveAndFlush(taxes);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taxes
        Taxes updatedTaxes = taxesRepository.findById(taxes.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTaxes are not directly saved in db
        em.detach(updatedTaxes);
        updatedTaxes
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .effectivefrom(UPDATED_EFFECTIVEFROM)
            .effectiveto(UPDATED_EFFECTIVETO)
            .percentage(UPDATED_PERCENTAGE)
            .fixedamount(UPDATED_FIXEDAMOUNT)
            .ismanual(UPDATED_ISMANUAL)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restTaxesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTaxes.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTaxes))
            )
            .andExpect(status().isOk());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTaxesToMatchAllProperties(updatedTaxes);
    }

    @Test
    @Transactional
    void putNonExistingTaxes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taxes.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxesMockMvc
            .perform(put(ENTITY_API_URL_ID, taxes.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taxes)))
            .andExpect(status().isBadRequest());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTaxes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taxes.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(taxes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTaxes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taxes.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(taxes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTaxesWithPatch() throws Exception {
        // Initialize the database
        taxesRepository.saveAndFlush(taxes);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taxes using partial update
        Taxes partialUpdatedTaxes = new Taxes();
        partialUpdatedTaxes.setId(taxes.getId());

        partialUpdatedTaxes
            .code(UPDATED_CODE)
            .effectiveto(UPDATED_EFFECTIVETO)
            .ismanual(UPDATED_ISMANUAL)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU);

        restTaxesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxes.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaxes))
            )
            .andExpect(status().isOk());

        // Validate the Taxes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaxesUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedTaxes, taxes), getPersistedTaxes(taxes));
    }

    @Test
    @Transactional
    void fullUpdateTaxesWithPatch() throws Exception {
        // Initialize the database
        taxesRepository.saveAndFlush(taxes);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the taxes using partial update
        Taxes partialUpdatedTaxes = new Taxes();
        partialUpdatedTaxes.setId(taxes.getId());

        partialUpdatedTaxes
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .effectivefrom(UPDATED_EFFECTIVEFROM)
            .effectiveto(UPDATED_EFFECTIVETO)
            .percentage(UPDATED_PERCENTAGE)
            .fixedamount(UPDATED_FIXEDAMOUNT)
            .ismanual(UPDATED_ISMANUAL)
            .isactive(UPDATED_ISACTIVE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD);

        restTaxesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTaxes.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTaxes))
            )
            .andExpect(status().isOk());

        // Validate the Taxes in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTaxesUpdatableFieldsEquals(partialUpdatedTaxes, getPersistedTaxes(partialUpdatedTaxes));
    }

    @Test
    @Transactional
    void patchNonExistingTaxes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taxes.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTaxesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, taxes.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taxes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTaxes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taxes.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(taxes))
            )
            .andExpect(status().isBadRequest());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTaxes() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        taxes.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTaxesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(taxes)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Taxes in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTaxes() throws Exception {
        // Initialize the database
        taxesRepository.saveAndFlush(taxes);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the taxes
        restTaxesMockMvc
            .perform(delete(ENTITY_API_URL_ID, taxes.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return taxesRepository.count();
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

    protected Taxes getPersistedTaxes(Taxes taxes) {
        return taxesRepository.findById(taxes.getId()).orElseThrow();
    }

    protected void assertPersistedTaxesToMatchAllProperties(Taxes expectedTaxes) {
        assertTaxesAllPropertiesEquals(expectedTaxes, getPersistedTaxes(expectedTaxes));
    }

    protected void assertPersistedTaxesToMatchUpdatableProperties(Taxes expectedTaxes) {
        assertTaxesAllUpdatablePropertiesEquals(expectedTaxes, getPersistedTaxes(expectedTaxes));
    }
}
