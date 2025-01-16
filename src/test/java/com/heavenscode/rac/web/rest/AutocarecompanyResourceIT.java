package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AutocarecompanyAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Autocarecompany;
import com.heavenscode.rac.repository.AutocarecompanyRepository;
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
 * Integration tests for the {@link AutocarecompanyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AutocarecompanyResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_SPAREPARTPHONE = "AAAAAAAAAA";
    private static final String UPDATED_SPAREPARTPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_BODYPAINT = "AAAAAAAAAA";
    private static final String UPDATED_BODYPAINT = "BBBBBBBBBB";

    private static final String DEFAULT_GENERALPHONE = "AAAAAAAAAA";
    private static final String UPDATED_GENERALPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VATREGNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_VATREGNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TINNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TINNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_ACCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ACCOUNTID = 1;
    private static final Integer UPDATED_ACCOUNTID = 2;

    private static final String ENTITY_API_URL = "/api/autocarecompanies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AutocarecompanyRepository autocarecompanyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAutocarecompanyMockMvc;

    private Autocarecompany autocarecompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarecompany createEntity(EntityManager em) {
        Autocarecompany autocarecompany = new Autocarecompany()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS)
            .servicephone(DEFAULT_SERVICEPHONE)
            .sparepartphone(DEFAULT_SPAREPARTPHONE)
            .bodypaint(DEFAULT_BODYPAINT)
            .generalphone(DEFAULT_GENERALPHONE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .description(DEFAULT_DESCRIPTION)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .vatregnumber(DEFAULT_VATREGNUMBER)
            .tinnumber(DEFAULT_TINNUMBER)
            .accountcode(DEFAULT_ACCOUNTCODE)
            .accountid(DEFAULT_ACCOUNTID);
        return autocarecompany;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Autocarecompany createUpdatedEntity(EntityManager em) {
        Autocarecompany autocarecompany = new Autocarecompany()
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .servicephone(UPDATED_SERVICEPHONE)
            .sparepartphone(UPDATED_SPAREPARTPHONE)
            .bodypaint(UPDATED_BODYPAINT)
            .generalphone(UPDATED_GENERALPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID);
        return autocarecompany;
    }

    @BeforeEach
    public void initTest() {
        autocarecompany = createEntity(em);
    }

    @Test
    @Transactional
    void createAutocarecompany() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Autocarecompany
        var returnedAutocarecompany = om.readValue(
            restAutocarecompanyMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarecompany)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Autocarecompany.class
        );

        // Validate the Autocarecompany in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAutocarecompanyUpdatableFieldsEquals(returnedAutocarecompany, getPersistedAutocarecompany(returnedAutocarecompany));
    }

    @Test
    @Transactional
    void createAutocarecompanyWithExistingId() throws Exception {
        // Create the Autocarecompany with an existing ID
        autocarecompany.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutocarecompanyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarecompany)))
            .andExpect(status().isBadRequest());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAutocarecompanies() throws Exception {
        // Initialize the database
        autocarecompanyRepository.saveAndFlush(autocarecompany);

        // Get all the autocarecompanyList
        restAutocarecompanyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(autocarecompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].servicephone").value(hasItem(DEFAULT_SERVICEPHONE)))
            .andExpect(jsonPath("$.[*].sparepartphone").value(hasItem(DEFAULT_SPAREPARTPHONE)))
            .andExpect(jsonPath("$.[*].bodypaint").value(hasItem(DEFAULT_BODYPAINT)))
            .andExpect(jsonPath("$.[*].generalphone").value(hasItem(DEFAULT_GENERALPHONE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].vatregnumber").value(hasItem(DEFAULT_VATREGNUMBER)))
            .andExpect(jsonPath("$.[*].tinnumber").value(hasItem(DEFAULT_TINNUMBER)))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE)))
            .andExpect(jsonPath("$.[*].accountid").value(hasItem(DEFAULT_ACCOUNTID)));
    }

    @Test
    @Transactional
    void getAutocarecompany() throws Exception {
        // Initialize the database
        autocarecompanyRepository.saveAndFlush(autocarecompany);

        // Get the autocarecompany
        restAutocarecompanyMockMvc
            .perform(get(ENTITY_API_URL_ID, autocarecompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(autocarecompany.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.servicephone").value(DEFAULT_SERVICEPHONE))
            .andExpect(jsonPath("$.sparepartphone").value(DEFAULT_SPAREPARTPHONE))
            .andExpect(jsonPath("$.bodypaint").value(DEFAULT_BODYPAINT))
            .andExpect(jsonPath("$.generalphone").value(DEFAULT_GENERALPHONE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.vatregnumber").value(DEFAULT_VATREGNUMBER))
            .andExpect(jsonPath("$.tinnumber").value(DEFAULT_TINNUMBER))
            .andExpect(jsonPath("$.accountcode").value(DEFAULT_ACCOUNTCODE))
            .andExpect(jsonPath("$.accountid").value(DEFAULT_ACCOUNTID));
    }

    @Test
    @Transactional
    void getNonExistingAutocarecompany() throws Exception {
        // Get the autocarecompany
        restAutocarecompanyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAutocarecompany() throws Exception {
        // Initialize the database
        autocarecompanyRepository.saveAndFlush(autocarecompany);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarecompany
        Autocarecompany updatedAutocarecompany = autocarecompanyRepository.findById(autocarecompany.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAutocarecompany are not directly saved in db
        em.detach(updatedAutocarecompany);
        updatedAutocarecompany
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .servicephone(UPDATED_SERVICEPHONE)
            .sparepartphone(UPDATED_SPAREPARTPHONE)
            .bodypaint(UPDATED_BODYPAINT)
            .generalphone(UPDATED_GENERALPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID);

        restAutocarecompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAutocarecompany.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAutocarecompany))
            )
            .andExpect(status().isOk());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAutocarecompanyToMatchAllProperties(updatedAutocarecompany);
    }

    @Test
    @Transactional
    void putNonExistingAutocarecompany() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecompany.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarecompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, autocarecompany.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarecompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAutocarecompany() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecompany.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecompanyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(autocarecompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAutocarecompany() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecompany.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecompanyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(autocarecompany)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAutocarecompanyWithPatch() throws Exception {
        // Initialize the database
        autocarecompanyRepository.saveAndFlush(autocarecompany);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarecompany using partial update
        Autocarecompany partialUpdatedAutocarecompany = new Autocarecompany();
        partialUpdatedAutocarecompany.setId(autocarecompany.getId());

        partialUpdatedAutocarecompany
            .servicephone(UPDATED_SERVICEPHONE)
            .bodypaint(UPDATED_BODYPAINT)
            .generalphone(UPDATED_GENERALPHONE)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .tinnumber(UPDATED_TINNUMBER);

        restAutocarecompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarecompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarecompany))
            )
            .andExpect(status().isOk());

        // Validate the Autocarecompany in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarecompanyUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAutocarecompany, autocarecompany),
            getPersistedAutocarecompany(autocarecompany)
        );
    }

    @Test
    @Transactional
    void fullUpdateAutocarecompanyWithPatch() throws Exception {
        // Initialize the database
        autocarecompanyRepository.saveAndFlush(autocarecompany);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the autocarecompany using partial update
        Autocarecompany partialUpdatedAutocarecompany = new Autocarecompany();
        partialUpdatedAutocarecompany.setId(autocarecompany.getId());

        partialUpdatedAutocarecompany
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS)
            .servicephone(UPDATED_SERVICEPHONE)
            .sparepartphone(UPDATED_SPAREPARTPHONE)
            .bodypaint(UPDATED_BODYPAINT)
            .generalphone(UPDATED_GENERALPHONE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .description(UPDATED_DESCRIPTION)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .vatregnumber(UPDATED_VATREGNUMBER)
            .tinnumber(UPDATED_TINNUMBER)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID);

        restAutocarecompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAutocarecompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAutocarecompany))
            )
            .andExpect(status().isOk());

        // Validate the Autocarecompany in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAutocarecompanyUpdatableFieldsEquals(
            partialUpdatedAutocarecompany,
            getPersistedAutocarecompany(partialUpdatedAutocarecompany)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAutocarecompany() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecompany.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutocarecompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, autocarecompany.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarecompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAutocarecompany() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecompany.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecompanyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(autocarecompany))
            )
            .andExpect(status().isBadRequest());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAutocarecompany() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        autocarecompany.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAutocarecompanyMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(autocarecompany)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Autocarecompany in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAutocarecompany() throws Exception {
        // Initialize the database
        autocarecompanyRepository.saveAndFlush(autocarecompany);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the autocarecompany
        restAutocarecompanyMockMvc
            .perform(delete(ENTITY_API_URL_ID, autocarecompany.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return autocarecompanyRepository.count();
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

    protected Autocarecompany getPersistedAutocarecompany(Autocarecompany autocarecompany) {
        return autocarecompanyRepository.findById(autocarecompany.getId()).orElseThrow();
    }

    protected void assertPersistedAutocarecompanyToMatchAllProperties(Autocarecompany expectedAutocarecompany) {
        assertAutocarecompanyAllPropertiesEquals(expectedAutocarecompany, getPersistedAutocarecompany(expectedAutocarecompany));
    }

    protected void assertPersistedAutocarecompanyToMatchUpdatableProperties(Autocarecompany expectedAutocarecompany) {
        assertAutocarecompanyAllUpdatablePropertiesEquals(expectedAutocarecompany, getPersistedAutocarecompany(expectedAutocarecompany));
    }
}
