package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.PaymenttermAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Paymentterm;
import com.heavenscode.rac.repository.PaymenttermRepository;
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
 * Integration tests for the {@link PaymenttermResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PaymenttermResourceIT {

    private static final String DEFAULT_PAYMENTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENTTYPE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_FORVOUCHER = false;
    private static final Boolean UPDATED_FORVOUCHER = true;

    private static final String ENTITY_API_URL = "/api/paymentterms";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PaymenttermRepository paymenttermRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPaymenttermMockMvc;

    private Paymentterm paymentterm;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paymentterm createEntity(EntityManager em) {
        Paymentterm paymentterm = new Paymentterm().paymenttype(DEFAULT_PAYMENTTYPE).forvoucher(DEFAULT_FORVOUCHER);
        return paymentterm;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Paymentterm createUpdatedEntity(EntityManager em) {
        Paymentterm paymentterm = new Paymentterm().paymenttype(UPDATED_PAYMENTTYPE).forvoucher(UPDATED_FORVOUCHER);
        return paymentterm;
    }

    @BeforeEach
    public void initTest() {
        paymentterm = createEntity(em);
    }

    @Test
    @Transactional
    void createPaymentterm() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Paymentterm
        var returnedPaymentterm = om.readValue(
            restPaymenttermMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentterm)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Paymentterm.class
        );

        // Validate the Paymentterm in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPaymenttermUpdatableFieldsEquals(returnedPaymentterm, getPersistedPaymentterm(returnedPaymentterm));
    }

    @Test
    @Transactional
    void createPaymenttermWithExistingId() throws Exception {
        // Create the Paymentterm with an existing ID
        paymentterm.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPaymenttermMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentterm)))
            .andExpect(status().isBadRequest());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPaymentterms() throws Exception {
        // Initialize the database
        paymenttermRepository.saveAndFlush(paymentterm);

        // Get all the paymenttermList
        restPaymenttermMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(paymentterm.getId().intValue())))
            .andExpect(jsonPath("$.[*].paymenttype").value(hasItem(DEFAULT_PAYMENTTYPE)))
            .andExpect(jsonPath("$.[*].forvoucher").value(hasItem(DEFAULT_FORVOUCHER.booleanValue())));
    }

    @Test
    @Transactional
    void getPaymentterm() throws Exception {
        // Initialize the database
        paymenttermRepository.saveAndFlush(paymentterm);

        // Get the paymentterm
        restPaymenttermMockMvc
            .perform(get(ENTITY_API_URL_ID, paymentterm.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(paymentterm.getId().intValue()))
            .andExpect(jsonPath("$.paymenttype").value(DEFAULT_PAYMENTTYPE))
            .andExpect(jsonPath("$.forvoucher").value(DEFAULT_FORVOUCHER.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingPaymentterm() throws Exception {
        // Get the paymentterm
        restPaymenttermMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPaymentterm() throws Exception {
        // Initialize the database
        paymenttermRepository.saveAndFlush(paymentterm);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentterm
        Paymentterm updatedPaymentterm = paymenttermRepository.findById(paymentterm.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPaymentterm are not directly saved in db
        em.detach(updatedPaymentterm);
        updatedPaymentterm.paymenttype(UPDATED_PAYMENTTYPE).forvoucher(UPDATED_FORVOUCHER);

        restPaymenttermMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPaymentterm.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPaymentterm))
            )
            .andExpect(status().isOk());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPaymenttermToMatchAllProperties(updatedPaymentterm);
    }

    @Test
    @Transactional
    void putNonExistingPaymentterm() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentterm.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymenttermMockMvc
            .perform(
                put(ENTITY_API_URL_ID, paymentterm.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentterm))
            )
            .andExpect(status().isBadRequest());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPaymentterm() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentterm.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymenttermMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(paymentterm))
            )
            .andExpect(status().isBadRequest());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPaymentterm() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentterm.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymenttermMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(paymentterm)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePaymenttermWithPatch() throws Exception {
        // Initialize the database
        paymenttermRepository.saveAndFlush(paymentterm);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentterm using partial update
        Paymentterm partialUpdatedPaymentterm = new Paymentterm();
        partialUpdatedPaymentterm.setId(paymentterm.getId());

        partialUpdatedPaymentterm.forvoucher(UPDATED_FORVOUCHER);

        restPaymenttermMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentterm.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentterm))
            )
            .andExpect(status().isOk());

        // Validate the Paymentterm in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymenttermUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPaymentterm, paymentterm),
            getPersistedPaymentterm(paymentterm)
        );
    }

    @Test
    @Transactional
    void fullUpdatePaymenttermWithPatch() throws Exception {
        // Initialize the database
        paymenttermRepository.saveAndFlush(paymentterm);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the paymentterm using partial update
        Paymentterm partialUpdatedPaymentterm = new Paymentterm();
        partialUpdatedPaymentterm.setId(paymentterm.getId());

        partialUpdatedPaymentterm.paymenttype(UPDATED_PAYMENTTYPE).forvoucher(UPDATED_FORVOUCHER);

        restPaymenttermMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPaymentterm.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPaymentterm))
            )
            .andExpect(status().isOk());

        // Validate the Paymentterm in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPaymenttermUpdatableFieldsEquals(partialUpdatedPaymentterm, getPersistedPaymentterm(partialUpdatedPaymentterm));
    }

    @Test
    @Transactional
    void patchNonExistingPaymentterm() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentterm.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPaymenttermMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, paymentterm.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentterm))
            )
            .andExpect(status().isBadRequest());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPaymentterm() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentterm.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymenttermMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(paymentterm))
            )
            .andExpect(status().isBadRequest());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPaymentterm() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        paymentterm.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPaymenttermMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(paymentterm)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Paymentterm in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePaymentterm() throws Exception {
        // Initialize the database
        paymenttermRepository.saveAndFlush(paymentterm);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the paymentterm
        restPaymenttermMockMvc
            .perform(delete(ENTITY_API_URL_ID, paymentterm.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return paymenttermRepository.count();
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

    protected Paymentterm getPersistedPaymentterm(Paymentterm paymentterm) {
        return paymenttermRepository.findById(paymentterm.getId()).orElseThrow();
    }

    protected void assertPersistedPaymenttermToMatchAllProperties(Paymentterm expectedPaymentterm) {
        assertPaymenttermAllPropertiesEquals(expectedPaymentterm, getPersistedPaymentterm(expectedPaymentterm));
    }

    protected void assertPersistedPaymenttermToMatchUpdatableProperties(Paymentterm expectedPaymentterm) {
        assertPaymenttermAllUpdatablePropertiesEquals(expectedPaymentterm, getPersistedPaymentterm(expectedPaymentterm));
    }
}
