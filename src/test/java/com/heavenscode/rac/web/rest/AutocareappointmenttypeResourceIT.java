package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocareappointmenttypeAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocareappointmenttype;
import com.heavenscode.rac.repository.AutocareappointmenttypeRepository;
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
 * Integration tests for the {@link AutocareappointmenttypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocareappointmenttypeResourceIT {

    private static final String DEFAULT_TYPENAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPENAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/autocareappointmenttypes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocareappointmenttypeRepository autocareappointmenttypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocareappointmenttypeMockMvc;

    private Autocareappointmenttype autocareappointmenttype;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocareappointmenttype createEntity(EntityManager em) {
        Autocareappointmenttype autocareappointmenttype = new Autocareappointmenttype().typename(DEFAULT_TYPENAME);
        return autocareappointmenttype;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocareappointmenttype createUpdatedEntity(EntityManager em) {
        Autocareappointmenttype autocareappointmenttype = new Autocareappointmenttype().typename(UPDATED_TYPENAME);
        return autocareappointmenttype;
    }

    @BeforeEach
    public void initTest() {
        autocareappointmenttype = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocareappointmenttype
        var returnedAutocareappointmenttype = om.readValue(
            restAutocareappointmenttypeMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareappointmenttype))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocareappointmenttype.class
        );

        // Validate the Autocareappointmenttype in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocareappointmenttypeUpdatableFieldsEquals(
            returnedAutocareappointmenttype,
            getPersistedAutocareappointmenttype(returnedAutocareappointmenttype)
        );
    }

    @Test
    @Transactional
    void createAutocareappointmenttypeWithExistingId() throws Exception {
        // Create the Autocareappointmenttype with an existing ID
        autocareappointmenttype.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocareappointmenttypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareappointmenttype)))
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocareappointmenttypes() throws Exception {
        // Initialize the database
        autocareappointmenttypeRepository.saveAndFlush(autocareappointmenttype);

        // Get all the autocareappointmenttypeList
        restAutocareappointmenttypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocareappointmenttype.getId().intValue())))
            .andExpect(jsonPath("$.[*].typename").value(hasItem(DEFAULT_TYPENAME)));
    }

    @Test
    @Transactional
    void getAutocareappointmenttype() throws Exception {
        // Initialize the database
        autocareappointmenttypeRepository.saveAndFlush(autocareappointmenttype);

        // Get the autocareappointmenttype
        restAutocareappointmenttypeMockMvc
            .perform(get(ENTITY_API_URL_ID, autocareappointmenttype.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocareappointmenttype.getId().intValue()))
            .andExpect(jsonPath("$.typename").value(DEFAULT_TYPENAME));
    }

    @Test
    @Transactional
    void getNonExistingAutocareappointmenttype() throws Exception {
        // Get the autocareappointmenttype
        restAutocareappointmenttypeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocareappointmenttype() throws Exception {
        // Initialize the database
        autocareappointmenttypeRepository.saveAndFlush(autocareappointmenttype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareappointmenttype
        Autocareappointmenttype updatedAutocareappointmenttype = autocareappointmenttypeRepository
            .findById(autocareappointmenttype.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedAutocareappointmenttype are not directly saved in db
        em.detach(updatedAutocareappointmenttype);
        updatedAutocareappointmenttype.typename(UPDATED_TYPENAME);

        restAutocareappointmenttypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocareappointmenttype.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocareappointmenttype))
            )
            .andExpect(status().isOk());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocareappointmenttypeToMatchAllProperties(updatedAutocareappointmenttype);
    }

    @Test
    @Transactional
    void putNonExistingAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointmenttype.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocareappointmenttypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocareappointmenttype.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocareappointmenttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointmenttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmenttypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocareappointmenttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointmenttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmenttypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocareappointmenttype)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocareappointmenttypeWithPatch() throws Exception {
        // Initialize the database
        autocareappointmenttypeRepository.saveAndFlush(autocareappointmenttype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareappointmenttype using partial update
        Autocareappointmenttype partialUpdatedAutocareappointmenttype = new Autocareappointmenttype();
        partialUpdatedAutocareappointmenttype.setId(autocareappointmenttype.getId());

        partialUpdatedAutocareappointmenttype.typename(UPDATED_TYPENAME);

        restAutocareappointmenttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocareappointmenttype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocareappointmenttype))
            )
            .andExpect(status().isOk());

        // Validate the Autocareappointmenttype in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocareappointmenttypeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocareappointmenttype, autocareappointmenttype),
            getPersistedAutocareappointmenttype(autocareappointmenttype)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocareappointmenttypeWithPatch() throws Exception {
        // Initialize the database
        autocareappointmenttypeRepository.saveAndFlush(autocareappointmenttype);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocareappointmenttype using partial update
        Autocareappointmenttype partialUpdatedAutocareappointmenttype = new Autocareappointmenttype();
        partialUpdatedAutocareappointmenttype.setId(autocareappointmenttype.getId());

        partialUpdatedAutocareappointmenttype.typename(UPDATED_TYPENAME);

        restAutocareappointmenttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocareappointmenttype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocareappointmenttype))
            )
            .andExpect(status().isOk());

        // Validate the Autocareappointmenttype in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocareappointmenttypeUpdatableFieldsEquals(
            partialUpdatedAutocareappointmenttype,
            getPersistedAutocareappointmenttype(partialUpdatedAutocareappointmenttype)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointmenttype.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocareappointmenttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocareappointmenttype.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocareappointmenttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointmenttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmenttypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocareappointmenttype))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocareappointmenttype() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocareappointmenttype.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocareappointmenttypeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocareappointmenttype))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocareappointmenttype in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocareappointmenttype() throws Exception {
        // Initialize the database
        autocareappointmenttypeRepository.saveAndFlush(autocareappointmenttype);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocareappointmenttype
        restAutocareappointmenttypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocareappointmenttype.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocareappointmenttypeRepository.count();
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

    protected Autocareappointmenttype getPersistedAutocareappointmenttype(Autocareappointmenttype autocareappointmenttype) {
        return autocareappointmenttypeRepository.findById(autocareappointmenttype.getId()).orElseThrow();
    }

    protected void assertPersistedAutocareappointmenttypeToMatchAllProperties(Autocareappointmenttype expectedAutocareappointmenttype) {
        assertAutocareappointmenttypeAllPropertiesEquals(
            expectedAutocareappointmenttype,
            getPersistedAutocareappointmenttype(expectedAutocareappointmenttype)
        );
    }

    protected void assertPersistedAutocareappointmenttypeToMatchUpdatableProperties(
        Autocareappointmenttype expectedAutocareappointmenttype
    ) {
        assertAutocareappointmenttypeAllUpdatablePropertiesEquals(
            expectedAutocareappointmenttype,
            getPersistedAutocareappointmenttype(expectedAutocareappointmenttype)
        );
    }
}
