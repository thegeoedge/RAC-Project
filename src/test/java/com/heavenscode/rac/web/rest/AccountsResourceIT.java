package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.AccountsAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Accounts;
import com.heavenscode.rac.repository.AccountsRepository;
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
 * Integration tests for the {@link AccountsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AccountsResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_PARENT = 1;
    private static final Integer UPDATED_PARENT = 2;

    private static final Float DEFAULT_BALANCE = 1F;
    private static final Float UPDATED_BALANCE = 2F;

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_HASBATCHES = false;
    private static final Boolean UPDATED_HASBATCHES = true;

    private static final Float DEFAULT_ACCOUNTVALUE = 1F;
    private static final Float UPDATED_ACCOUNTVALUE = 2F;

    private static final Integer DEFAULT_ACCOUNTLEVEL = 1;
    private static final Integer UPDATED_ACCOUNTLEVEL = 2;

    private static final Long DEFAULT_ACCOUNTSNUMBERINGSYSTEM = 1L;
    private static final Long UPDATED_ACCOUNTSNUMBERINGSYSTEM = 2L;

    private static final Integer DEFAULT_SUBPARENTID = 1;
    private static final Integer UPDATED_SUBPARENTID = 2;

    private static final Boolean DEFAULT_CANEDIT = false;
    private static final Boolean UPDATED_CANEDIT = true;

    private static final Float DEFAULT_AMOUNT = 1F;
    private static final Float UPDATED_AMOUNT = 2F;

    private static final Float DEFAULT_CREDITAMOUNT = 1F;
    private static final Float UPDATED_CREDITAMOUNT = 2F;

    private static final Float DEFAULT_DEBITAMOUNT = 1F;
    private static final Float UPDATED_DEBITAMOUNT = 2F;

    private static final String DEFAULT_DEBITORCREDIT = "AAAAAAAAAA";
    private static final String UPDATED_DEBITORCREDIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_REPORTTYPE = 1;
    private static final Integer UPDATED_REPORTTYPE = 2;

    private static final String ENTITY_API_URL = "/api/accounts";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAccountsMockMvc;

    private Accounts accounts;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accounts createEntity(EntityManager em) {
        Accounts accounts = new Accounts()
            .code(DEFAULT_CODE)
            .date(DEFAULT_DATE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .parent(DEFAULT_PARENT)
            .balance(DEFAULT_BALANCE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .hasbatches(DEFAULT_HASBATCHES)
            .accountvalue(DEFAULT_ACCOUNTVALUE)
            .accountlevel(DEFAULT_ACCOUNTLEVEL)
            .accountsnumberingsystem(DEFAULT_ACCOUNTSNUMBERINGSYSTEM)
            .subparentid(DEFAULT_SUBPARENTID)
            .canedit(DEFAULT_CANEDIT)
            .amount(DEFAULT_AMOUNT)
            .creditamount(DEFAULT_CREDITAMOUNT)
            .debitamount(DEFAULT_DEBITAMOUNT)
            .debitorcredit(DEFAULT_DEBITORCREDIT)
            .reporttype(DEFAULT_REPORTTYPE);
        return accounts;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Accounts createUpdatedEntity(EntityManager em) {
        Accounts accounts = new Accounts()
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .parent(UPDATED_PARENT)
            .balance(UPDATED_BALANCE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .hasbatches(UPDATED_HASBATCHES)
            .accountvalue(UPDATED_ACCOUNTVALUE)
            .accountlevel(UPDATED_ACCOUNTLEVEL)
            .accountsnumberingsystem(UPDATED_ACCOUNTSNUMBERINGSYSTEM)
            .subparentid(UPDATED_SUBPARENTID)
            .canedit(UPDATED_CANEDIT)
            .amount(UPDATED_AMOUNT)
            .creditamount(UPDATED_CREDITAMOUNT)
            .debitamount(UPDATED_DEBITAMOUNT)
            .debitorcredit(UPDATED_DEBITORCREDIT)
            .reporttype(UPDATED_REPORTTYPE);
        return accounts;
    }

    @BeforeEach
    public void initTest() {
        accounts = createEntity(em);
    }

    @Test
    @Transactional
    void createAccounts() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Accounts
        var returnedAccounts = om.readValue(
            restAccountsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accounts)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Accounts.class
        );

        // Validate the Accounts in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAccountsUpdatableFieldsEquals(returnedAccounts, getPersistedAccounts(returnedAccounts));
    }

    @Test
    @Transactional
    void createAccountsWithExistingId() throws Exception {
        // Create the Accounts with an existing ID
        accounts.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAccountsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accounts)))
            .andExpect(status().isBadRequest());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAccounts() throws Exception {
        // Initialize the database
        accountsRepository.saveAndFlush(accounts);

        // Get all the accountsList
        restAccountsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(accounts.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].date").value(hasItem(DEFAULT_DATE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].parent").value(hasItem(DEFAULT_PARENT)))
            .andExpect(jsonPath("$.[*].balance").value(hasItem(DEFAULT_BALANCE.doubleValue())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].hasbatches").value(hasItem(DEFAULT_HASBATCHES.booleanValue())))
            .andExpect(jsonPath("$.[*].accountvalue").value(hasItem(DEFAULT_ACCOUNTVALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].accountlevel").value(hasItem(DEFAULT_ACCOUNTLEVEL)))
            .andExpect(jsonPath("$.[*].accountsnumberingsystem").value(hasItem(DEFAULT_ACCOUNTSNUMBERINGSYSTEM.intValue())))
            .andExpect(jsonPath("$.[*].subparentid").value(hasItem(DEFAULT_SUBPARENTID)))
            .andExpect(jsonPath("$.[*].canedit").value(hasItem(DEFAULT_CANEDIT.booleanValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].creditamount").value(hasItem(DEFAULT_CREDITAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].debitamount").value(hasItem(DEFAULT_DEBITAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].debitorcredit").value(hasItem(DEFAULT_DEBITORCREDIT)))
            .andExpect(jsonPath("$.[*].reporttype").value(hasItem(DEFAULT_REPORTTYPE)));
    }

    @Test
    @Transactional
    void getAccounts() throws Exception {
        // Initialize the database
        accountsRepository.saveAndFlush(accounts);

        // Get the accounts
        restAccountsMockMvc
            .perform(get(ENTITY_API_URL_ID, accounts.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(accounts.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.date").value(DEFAULT_DATE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.parent").value(DEFAULT_PARENT))
            .andExpect(jsonPath("$.balance").value(DEFAULT_BALANCE.doubleValue()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.hasbatches").value(DEFAULT_HASBATCHES.booleanValue()))
            .andExpect(jsonPath("$.accountvalue").value(DEFAULT_ACCOUNTVALUE.doubleValue()))
            .andExpect(jsonPath("$.accountlevel").value(DEFAULT_ACCOUNTLEVEL))
            .andExpect(jsonPath("$.accountsnumberingsystem").value(DEFAULT_ACCOUNTSNUMBERINGSYSTEM.intValue()))
            .andExpect(jsonPath("$.subparentid").value(DEFAULT_SUBPARENTID))
            .andExpect(jsonPath("$.canedit").value(DEFAULT_CANEDIT.booleanValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.doubleValue()))
            .andExpect(jsonPath("$.creditamount").value(DEFAULT_CREDITAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.debitamount").value(DEFAULT_DEBITAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.debitorcredit").value(DEFAULT_DEBITORCREDIT))
            .andExpect(jsonPath("$.reporttype").value(DEFAULT_REPORTTYPE));
    }

    @Test
    @Transactional
    void getNonExistingAccounts() throws Exception {
        // Get the accounts
        restAccountsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAccounts() throws Exception {
        // Initialize the database
        accountsRepository.saveAndFlush(accounts);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accounts
        Accounts updatedAccounts = accountsRepository.findById(accounts.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAccounts are not directly saved in db
        em.detach(updatedAccounts);
        updatedAccounts
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .parent(UPDATED_PARENT)
            .balance(UPDATED_BALANCE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .hasbatches(UPDATED_HASBATCHES)
            .accountvalue(UPDATED_ACCOUNTVALUE)
            .accountlevel(UPDATED_ACCOUNTLEVEL)
            .accountsnumberingsystem(UPDATED_ACCOUNTSNUMBERINGSYSTEM)
            .subparentid(UPDATED_SUBPARENTID)
            .canedit(UPDATED_CANEDIT)
            .amount(UPDATED_AMOUNT)
            .creditamount(UPDATED_CREDITAMOUNT)
            .debitamount(UPDATED_DEBITAMOUNT)
            .debitorcredit(UPDATED_DEBITORCREDIT)
            .reporttype(UPDATED_REPORTTYPE);

        restAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAccounts.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAccounts))
            )
            .andExpect(status().isOk());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAccountsToMatchAllProperties(updatedAccounts);
    }

    @Test
    @Transactional
    void putNonExistingAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, accounts.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accounts))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(accounts))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(accounts)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAccountsWithPatch() throws Exception {
        // Initialize the database
        accountsRepository.saveAndFlush(accounts);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accounts using partial update
        Accounts partialUpdatedAccounts = new Accounts();
        partialUpdatedAccounts.setId(accounts.getId());

        partialUpdatedAccounts
            .code(UPDATED_CODE)
            .type(UPDATED_TYPE)
            .parent(UPDATED_PARENT)
            .balance(UPDATED_BALANCE)
            .lmu(UPDATED_LMU)
            .hasbatches(UPDATED_HASBATCHES)
            .accountlevel(UPDATED_ACCOUNTLEVEL)
            .amount(UPDATED_AMOUNT)
            .creditamount(UPDATED_CREDITAMOUNT)
            .debitamount(UPDATED_DEBITAMOUNT)
            .reporttype(UPDATED_REPORTTYPE);

        restAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAccounts))
            )
            .andExpect(status().isOk());

        // Validate the Accounts in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAccountsUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedAccounts, accounts), getPersistedAccounts(accounts));
    }

    @Test
    @Transactional
    void fullUpdateAccountsWithPatch() throws Exception {
        // Initialize the database
        accountsRepository.saveAndFlush(accounts);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the accounts using partial update
        Accounts partialUpdatedAccounts = new Accounts();
        partialUpdatedAccounts.setId(accounts.getId());

        partialUpdatedAccounts
            .code(UPDATED_CODE)
            .date(UPDATED_DATE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .parent(UPDATED_PARENT)
            .balance(UPDATED_BALANCE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .hasbatches(UPDATED_HASBATCHES)
            .accountvalue(UPDATED_ACCOUNTVALUE)
            .accountlevel(UPDATED_ACCOUNTLEVEL)
            .accountsnumberingsystem(UPDATED_ACCOUNTSNUMBERINGSYSTEM)
            .subparentid(UPDATED_SUBPARENTID)
            .canedit(UPDATED_CANEDIT)
            .amount(UPDATED_AMOUNT)
            .creditamount(UPDATED_CREDITAMOUNT)
            .debitamount(UPDATED_DEBITAMOUNT)
            .debitorcredit(UPDATED_DEBITORCREDIT)
            .reporttype(UPDATED_REPORTTYPE);

        restAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAccounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAccounts))
            )
            .andExpect(status().isOk());

        // Validate the Accounts in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAccountsUpdatableFieldsEquals(partialUpdatedAccounts, getPersistedAccounts(partialUpdatedAccounts));
    }

    @Test
    @Transactional
    void patchNonExistingAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, accounts.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(accounts))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(accounts))
            )
            .andExpect(status().isBadRequest());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAccounts() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        accounts.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAccountsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(accounts)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Accounts in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAccounts() throws Exception {
        // Initialize the database
        accountsRepository.saveAndFlush(accounts);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the accounts
        restAccountsMockMvc
            .perform(delete(ENTITY_API_URL_ID, accounts.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return accountsRepository.count();
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

    protected Accounts getPersistedAccounts(Accounts accounts) {
        return accountsRepository.findById(accounts.getId()).orElseThrow();
    }

    protected void assertPersistedAccountsToMatchAllProperties(Accounts expectedAccounts) {
        assertAccountsAllPropertiesEquals(expectedAccounts, getPersistedAccounts(expectedAccounts));
    }

    protected void assertPersistedAccountsToMatchUpdatableProperties(Accounts expectedAccounts) {
        assertAccountsAllUpdatablePropertiesEquals(expectedAccounts, getPersistedAccounts(expectedAccounts));
    }
}
