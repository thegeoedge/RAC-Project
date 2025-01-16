package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.InventorybatchesAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Inventorybatches;
import com.heavenscode.rac.repository.InventorybatchesRepository;
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
 * Integration tests for the {@link InventorybatchesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InventorybatchesResourceIT {

    private static final Integer DEFAULT_ITEMID = 1;
    private static final Integer UPDATED_ITEMID = 2;

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_TXDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_TXDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_COST = 1F;
    private static final Float UPDATED_COST = 2F;

    private static final Float DEFAULT_PRICE = 1F;
    private static final Float UPDATED_PRICE = 2F;

    private static final Float DEFAULT_COSTWITHOUTVAT = 1F;
    private static final Float UPDATED_COSTWITHOUTVAT = 2F;

    private static final Float DEFAULT_PRICEWITHOUTVAT = 1F;
    private static final Float UPDATED_PRICEWITHOUTVAT = 2F;

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_LINEID = 1;
    private static final Integer UPDATED_LINEID = 2;

    private static final Instant DEFAULT_MANUFACTUREDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MANUFACTUREDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_EXPIREDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIREDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_QUANTITY = 1F;
    private static final Float UPDATED_QUANTITY = 2F;

    private static final Instant DEFAULT_ADDEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_COSTTOTAL = 1F;
    private static final Float UPDATED_COSTTOTAL = 2F;

    private static final Float DEFAULT_RETURNPRICE = 1F;
    private static final Float UPDATED_RETURNPRICE = 2F;

    private static final String ENTITY_API_URL = "/api/inventorybatches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InventorybatchesRepository inventorybatchesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInventorybatchesMockMvc;

    private Inventorybatches inventorybatches;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inventorybatches createEntity(EntityManager em) {
        Inventorybatches inventorybatches = new Inventorybatches()
            .itemid(DEFAULT_ITEMID)
            .code(DEFAULT_CODE)
            .txdate(DEFAULT_TXDATE)
            .cost(DEFAULT_COST)
            .price(DEFAULT_PRICE)
            .costwithoutvat(DEFAULT_COSTWITHOUTVAT)
            .pricewithoutvat(DEFAULT_PRICEWITHOUTVAT)
            .notes(DEFAULT_NOTES)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .lineid(DEFAULT_LINEID)
            .manufacturedate(DEFAULT_MANUFACTUREDATE)
            .expiredate(DEFAULT_EXPIREDATE)
            .quantity(DEFAULT_QUANTITY)
            .addeddate(DEFAULT_ADDEDDATE)
            .costtotal(DEFAULT_COSTTOTAL)
            .returnprice(DEFAULT_RETURNPRICE);
        return inventorybatches;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inventorybatches createUpdatedEntity(EntityManager em) {
        Inventorybatches inventorybatches = new Inventorybatches()
            .itemid(UPDATED_ITEMID)
            .code(UPDATED_CODE)
            .txdate(UPDATED_TXDATE)
            .cost(UPDATED_COST)
            .price(UPDATED_PRICE)
            .costwithoutvat(UPDATED_COSTWITHOUTVAT)
            .pricewithoutvat(UPDATED_PRICEWITHOUTVAT)
            .notes(UPDATED_NOTES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .lineid(UPDATED_LINEID)
            .manufacturedate(UPDATED_MANUFACTUREDATE)
            .expiredate(UPDATED_EXPIREDATE)
            .quantity(UPDATED_QUANTITY)
            .addeddate(UPDATED_ADDEDDATE)
            .costtotal(UPDATED_COSTTOTAL)
            .returnprice(UPDATED_RETURNPRICE);
        return inventorybatches;
    }

    @BeforeEach
    public void initTest() {
        inventorybatches = createEntity(em);
    }

    @Test
    @Transactional
    void createInventorybatches() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Inventorybatches
        var returnedInventorybatches = om.readValue(
            restInventorybatchesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventorybatches)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Inventorybatches.class
        );

        // Validate the Inventorybatches in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertInventorybatchesUpdatableFieldsEquals(returnedInventorybatches, getPersistedInventorybatches(returnedInventorybatches));
    }

    @Test
    @Transactional
    void createInventorybatchesWithExistingId() throws Exception {
        // Create the Inventorybatches with an existing ID
        inventorybatches.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventorybatchesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventorybatches)))
            .andExpect(status().isBadRequest());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInventorybatches() throws Exception {
        // Initialize the database
        inventorybatchesRepository.saveAndFlush(inventorybatches);

        // Get all the inventorybatchesList
        restInventorybatchesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventorybatches.getId().intValue())))
            .andExpect(jsonPath("$.[*].itemid").value(hasItem(DEFAULT_ITEMID)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].txdate").value(hasItem(DEFAULT_TXDATE.toString())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.doubleValue())))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].costwithoutvat").value(hasItem(DEFAULT_COSTWITHOUTVAT.doubleValue())))
            .andExpect(jsonPath("$.[*].pricewithoutvat").value(hasItem(DEFAULT_PRICEWITHOUTVAT.doubleValue())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].lineid").value(hasItem(DEFAULT_LINEID)))
            .andExpect(jsonPath("$.[*].manufacturedate").value(hasItem(DEFAULT_MANUFACTUREDATE.toString())))
            .andExpect(jsonPath("$.[*].expiredate").value(hasItem(DEFAULT_EXPIREDATE.toString())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].addeddate").value(hasItem(DEFAULT_ADDEDDATE.toString())))
            .andExpect(jsonPath("$.[*].costtotal").value(hasItem(DEFAULT_COSTTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].returnprice").value(hasItem(DEFAULT_RETURNPRICE.doubleValue())));
    }

    @Test
    @Transactional
    void getInventorybatches() throws Exception {
        // Initialize the database
        inventorybatchesRepository.saveAndFlush(inventorybatches);

        // Get the inventorybatches
        restInventorybatchesMockMvc
            .perform(get(ENTITY_API_URL_ID, inventorybatches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inventorybatches.getId().intValue()))
            .andExpect(jsonPath("$.itemid").value(DEFAULT_ITEMID))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.txdate").value(DEFAULT_TXDATE.toString()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.doubleValue()))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.costwithoutvat").value(DEFAULT_COSTWITHOUTVAT.doubleValue()))
            .andExpect(jsonPath("$.pricewithoutvat").value(DEFAULT_PRICEWITHOUTVAT.doubleValue()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.lineid").value(DEFAULT_LINEID))
            .andExpect(jsonPath("$.manufacturedate").value(DEFAULT_MANUFACTUREDATE.toString()))
            .andExpect(jsonPath("$.expiredate").value(DEFAULT_EXPIREDATE.toString()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY.doubleValue()))
            .andExpect(jsonPath("$.addeddate").value(DEFAULT_ADDEDDATE.toString()))
            .andExpect(jsonPath("$.costtotal").value(DEFAULT_COSTTOTAL.doubleValue()))
            .andExpect(jsonPath("$.returnprice").value(DEFAULT_RETURNPRICE.doubleValue()));
    }

    @Test
    @Transactional
    void getNonExistingInventorybatches() throws Exception {
        // Get the inventorybatches
        restInventorybatchesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInventorybatches() throws Exception {
        // Initialize the database
        inventorybatchesRepository.saveAndFlush(inventorybatches);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventorybatches
        Inventorybatches updatedInventorybatches = inventorybatchesRepository.findById(inventorybatches.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedInventorybatches are not directly saved in db
        em.detach(updatedInventorybatches);
        updatedInventorybatches
            .itemid(UPDATED_ITEMID)
            .code(UPDATED_CODE)
            .txdate(UPDATED_TXDATE)
            .cost(UPDATED_COST)
            .price(UPDATED_PRICE)
            .costwithoutvat(UPDATED_COSTWITHOUTVAT)
            .pricewithoutvat(UPDATED_PRICEWITHOUTVAT)
            .notes(UPDATED_NOTES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .lineid(UPDATED_LINEID)
            .manufacturedate(UPDATED_MANUFACTUREDATE)
            .expiredate(UPDATED_EXPIREDATE)
            .quantity(UPDATED_QUANTITY)
            .addeddate(UPDATED_ADDEDDATE)
            .costtotal(UPDATED_COSTTOTAL)
            .returnprice(UPDATED_RETURNPRICE);

        restInventorybatchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInventorybatches.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedInventorybatches))
            )
            .andExpect(status().isOk());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInventorybatchesToMatchAllProperties(updatedInventorybatches);
    }

    @Test
    @Transactional
    void putNonExistingInventorybatches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventorybatches.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventorybatchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, inventorybatches.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(inventorybatches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInventorybatches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventorybatches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventorybatchesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(inventorybatches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInventorybatches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventorybatches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventorybatchesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventorybatches)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInventorybatchesWithPatch() throws Exception {
        // Initialize the database
        inventorybatchesRepository.saveAndFlush(inventorybatches);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventorybatches using partial update
        Inventorybatches partialUpdatedInventorybatches = new Inventorybatches();
        partialUpdatedInventorybatches.setId(inventorybatches.getId());

        partialUpdatedInventorybatches
            .txdate(UPDATED_TXDATE)
            .price(UPDATED_PRICE)
            .costwithoutvat(UPDATED_COSTWITHOUTVAT)
            .manufacturedate(UPDATED_MANUFACTUREDATE);

        restInventorybatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInventorybatches.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInventorybatches))
            )
            .andExpect(status().isOk());

        // Validate the Inventorybatches in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInventorybatchesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInventorybatches, inventorybatches),
            getPersistedInventorybatches(inventorybatches)
        );
    }

    @Test
    @Transactional
    void fullUpdateInventorybatchesWithPatch() throws Exception {
        // Initialize the database
        inventorybatchesRepository.saveAndFlush(inventorybatches);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventorybatches using partial update
        Inventorybatches partialUpdatedInventorybatches = new Inventorybatches();
        partialUpdatedInventorybatches.setId(inventorybatches.getId());

        partialUpdatedInventorybatches
            .itemid(UPDATED_ITEMID)
            .code(UPDATED_CODE)
            .txdate(UPDATED_TXDATE)
            .cost(UPDATED_COST)
            .price(UPDATED_PRICE)
            .costwithoutvat(UPDATED_COSTWITHOUTVAT)
            .pricewithoutvat(UPDATED_PRICEWITHOUTVAT)
            .notes(UPDATED_NOTES)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .lineid(UPDATED_LINEID)
            .manufacturedate(UPDATED_MANUFACTUREDATE)
            .expiredate(UPDATED_EXPIREDATE)
            .quantity(UPDATED_QUANTITY)
            .addeddate(UPDATED_ADDEDDATE)
            .costtotal(UPDATED_COSTTOTAL)
            .returnprice(UPDATED_RETURNPRICE);

        restInventorybatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInventorybatches.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInventorybatches))
            )
            .andExpect(status().isOk());

        // Validate the Inventorybatches in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInventorybatchesUpdatableFieldsEquals(
            partialUpdatedInventorybatches,
            getPersistedInventorybatches(partialUpdatedInventorybatches)
        );
    }

    @Test
    @Transactional
    void patchNonExistingInventorybatches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventorybatches.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventorybatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, inventorybatches.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(inventorybatches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInventorybatches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventorybatches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventorybatchesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(inventorybatches))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInventorybatches() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventorybatches.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventorybatchesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(inventorybatches)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Inventorybatches in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInventorybatches() throws Exception {
        // Initialize the database
        inventorybatchesRepository.saveAndFlush(inventorybatches);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the inventorybatches
        restInventorybatchesMockMvc
            .perform(delete(ENTITY_API_URL_ID, inventorybatches.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return inventorybatchesRepository.count();
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

    protected Inventorybatches getPersistedInventorybatches(Inventorybatches inventorybatches) {
        return inventorybatchesRepository.findById(inventorybatches.getId()).orElseThrow();
    }

    protected void assertPersistedInventorybatchesToMatchAllProperties(Inventorybatches expectedInventorybatches) {
        assertInventorybatchesAllPropertiesEquals(expectedInventorybatches, getPersistedInventorybatches(expectedInventorybatches));
    }

    protected void assertPersistedInventorybatchesToMatchUpdatableProperties(Inventorybatches expectedInventorybatches) {
        assertInventorybatchesAllUpdatablePropertiesEquals(
            expectedInventorybatches,
            getPersistedInventorybatches(expectedInventorybatches)
        );
    }
}
