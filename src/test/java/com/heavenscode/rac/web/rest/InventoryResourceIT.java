package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.InventoryAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Inventory;
import com.heavenscode.rac.repository.InventoryRepository;
import jakarta.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
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
 * Integration tests for the {@link InventoryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InventoryResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTNUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PARTNUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final String DEFAULT_CLASSIFICATION_1 = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSIFICATION_2 = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSIFICATION_3 = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATION_3 = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSIFICATION_4 = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATION_4 = "BBBBBBBBBB";

    private static final String DEFAULT_CLASSIFICATION_5 = "AAAAAAAAAA";
    private static final String UPDATED_CLASSIFICATION_5 = "BBBBBBBBBB";

    private static final String DEFAULT_UNITOFMEASUREMENT = "AAAAAAAAAA";
    private static final String UPDATED_UNITOFMEASUREMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_DECIMALPLACES = 1;
    private static final Integer UPDATED_DECIMALPLACES = 2;

    private static final Boolean DEFAULT_ISASSEMBLYUNIT = false;
    private static final Boolean UPDATED_ISASSEMBLYUNIT = true;

    private static final Integer DEFAULT_ASSEMBLYUNITOF = 1;
    private static final Integer UPDATED_ASSEMBLYUNITOF = 2;

    private static final Float DEFAULT_REORDERLEVEL = 1F;
    private static final Float UPDATED_REORDERLEVEL = 2F;

    private static final Float DEFAULT_LASTCOST = 1F;
    private static final Float UPDATED_LASTCOST = 2F;

    private static final Float DEFAULT_LASTSELLINGPRICE = 1F;
    private static final Float UPDATED_LASTSELLINGPRICE = 2F;

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_AVAILABLEQUANTITY = 1F;
    private static final Float UPDATED_AVAILABLEQUANTITY = 2F;

    private static final Boolean DEFAULT_HASBATCHES = false;
    private static final Boolean UPDATED_HASBATCHES = true;

    private static final String DEFAULT_ITEMSPECFILEPATH = "AAAAAAAAAA";
    private static final String UPDATED_ITEMSPECFILEPATH = "BBBBBBBBBB";

    private static final String DEFAULT_ITEMIMAGEPATH = "AAAAAAAAAA";
    private static final String UPDATED_ITEMIMAGEPATH = "BBBBBBBBBB";

    private static final Float DEFAULT_RETURNPRICE = 1F;
    private static final Float UPDATED_RETURNPRICE = 2F;

    private static final Boolean DEFAULT_ACTIVEITEM = false;
    private static final Boolean UPDATED_ACTIVEITEM = true;

    private static final Float DEFAULT_MINSTOCK = 1F;
    private static final Float UPDATED_MINSTOCK = 2F;

    private static final Float DEFAULT_MAXSTOCK = 1F;
    private static final Float UPDATED_MAXSTOCK = 2F;

    private static final Float DEFAULT_DAILYAVERAGE = 1F;
    private static final Float UPDATED_DAILYAVERAGE = 2F;

    private static final Float DEFAULT_BUFFERLEVEL = 1F;
    private static final Float UPDATED_BUFFERLEVEL = 2F;

    private static final Float DEFAULT_LEADTIME = 1F;
    private static final Float UPDATED_LEADTIME = 2F;

    private static final Float DEFAULT_BUFFERTIME = 1F;
    private static final Float UPDATED_BUFFERTIME = 2F;

    private static final Float DEFAULT_SAFTYDAYS = 1F;
    private static final Float UPDATED_SAFTYDAYS = 2F;

    private static final String DEFAULT_ACCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNTCODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ACCOUNTID = 1;
    private static final Integer UPDATED_ACCOUNTID = 2;

    private static final Integer DEFAULT_CASEPACKQTY = 1;
    private static final Integer UPDATED_CASEPACKQTY = 2;

    private static final Boolean DEFAULT_ISREGISTERED = false;
    private static final Boolean UPDATED_ISREGISTERED = true;

    private static final Integer DEFAULT_DEFAULTSTOCKLOCATIONID = 1;
    private static final Integer UPDATED_DEFAULTSTOCKLOCATIONID = 2;

    private static final String DEFAULT_RACKNO = "AAAAAAAAAA";
    private static final String UPDATED_RACKNO = "BBBBBBBBBB";

    private static final byte[] DEFAULT_BARCODEIMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_BARCODEIMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_BARCODEIMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_BARCODEIMAGE_CONTENT_TYPE = "image/png";

    private static final Integer DEFAULT_COMMISSIONEMPID = 1;
    private static final Integer UPDATED_COMMISSIONEMPID = 2;

    private static final Integer DEFAULT_CHECKTYPEID = 1;
    private static final Integer UPDATED_CHECKTYPEID = 2;

    private static final String DEFAULT_CHECKTYPE = "AAAAAAAAAA";
    private static final String UPDATED_CHECKTYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_REORDERQTY = 1F;
    private static final Float UPDATED_REORDERQTY = 2F;

    private static final Boolean DEFAULT_NOTININVOICE = false;
    private static final Boolean UPDATED_NOTININVOICE = true;

    private static final String ENTITY_API_URL = "/api/inventories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInventoryMockMvc;

    private Inventory inventory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inventory createEntity(EntityManager em) {
        Inventory inventory = new Inventory()
            .code(DEFAULT_CODE)
            .partnumber(DEFAULT_PARTNUMBER)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .type(DEFAULT_TYPE)
            .classification1(DEFAULT_CLASSIFICATION_1)
            .classification2(DEFAULT_CLASSIFICATION_2)
            .classification3(DEFAULT_CLASSIFICATION_3)
            .classification4(DEFAULT_CLASSIFICATION_4)
            .classification5(DEFAULT_CLASSIFICATION_5)
            .unitofmeasurement(DEFAULT_UNITOFMEASUREMENT)
            .decimalplaces(DEFAULT_DECIMALPLACES)
            .isassemblyunit(DEFAULT_ISASSEMBLYUNIT)
            .assemblyunitof(DEFAULT_ASSEMBLYUNITOF)
            .reorderlevel(DEFAULT_REORDERLEVEL)
            .lastcost(DEFAULT_LASTCOST)
            .lastsellingprice(DEFAULT_LASTSELLINGPRICE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .availablequantity(DEFAULT_AVAILABLEQUANTITY)
            .hasbatches(DEFAULT_HASBATCHES)
            .itemspecfilepath(DEFAULT_ITEMSPECFILEPATH)
            .itemimagepath(DEFAULT_ITEMIMAGEPATH)
            .returnprice(DEFAULT_RETURNPRICE)
            .activeitem(DEFAULT_ACTIVEITEM)
            .minstock(DEFAULT_MINSTOCK)
            .maxstock(DEFAULT_MAXSTOCK)
            .dailyaverage(DEFAULT_DAILYAVERAGE)
            .bufferlevel(DEFAULT_BUFFERLEVEL)
            .leadtime(DEFAULT_LEADTIME)
            .buffertime(DEFAULT_BUFFERTIME)
            .saftydays(DEFAULT_SAFTYDAYS)
            .accountcode(DEFAULT_ACCOUNTCODE)
            .accountid(DEFAULT_ACCOUNTID)
            .casepackqty(DEFAULT_CASEPACKQTY)
            .isregistered(DEFAULT_ISREGISTERED)
            .defaultstocklocationid(DEFAULT_DEFAULTSTOCKLOCATIONID)
            .rackno(DEFAULT_RACKNO)
            .barcodeimage(DEFAULT_BARCODEIMAGE)
            .barcodeimageContentType(DEFAULT_BARCODEIMAGE_CONTENT_TYPE)
            .commissionempid(DEFAULT_COMMISSIONEMPID)
            .checktypeid(DEFAULT_CHECKTYPEID)
            .checktype(DEFAULT_CHECKTYPE)
            .reorderqty(DEFAULT_REORDERQTY)
            .notininvoice(DEFAULT_NOTININVOICE);
        return inventory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Inventory createUpdatedEntity(EntityManager em) {
        Inventory inventory = new Inventory()
            .code(UPDATED_CODE)
            .partnumber(UPDATED_PARTNUMBER)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .classification1(UPDATED_CLASSIFICATION_1)
            .classification2(UPDATED_CLASSIFICATION_2)
            .classification3(UPDATED_CLASSIFICATION_3)
            .classification4(UPDATED_CLASSIFICATION_4)
            .classification5(UPDATED_CLASSIFICATION_5)
            .unitofmeasurement(UPDATED_UNITOFMEASUREMENT)
            .decimalplaces(UPDATED_DECIMALPLACES)
            .isassemblyunit(UPDATED_ISASSEMBLYUNIT)
            .assemblyunitof(UPDATED_ASSEMBLYUNITOF)
            .reorderlevel(UPDATED_REORDERLEVEL)
            .lastcost(UPDATED_LASTCOST)
            .lastsellingprice(UPDATED_LASTSELLINGPRICE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .itemspecfilepath(UPDATED_ITEMSPECFILEPATH)
            .itemimagepath(UPDATED_ITEMIMAGEPATH)
            .returnprice(UPDATED_RETURNPRICE)
            .activeitem(UPDATED_ACTIVEITEM)
            .minstock(UPDATED_MINSTOCK)
            .maxstock(UPDATED_MAXSTOCK)
            .dailyaverage(UPDATED_DAILYAVERAGE)
            .bufferlevel(UPDATED_BUFFERLEVEL)
            .leadtime(UPDATED_LEADTIME)
            .buffertime(UPDATED_BUFFERTIME)
            .saftydays(UPDATED_SAFTYDAYS)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID)
            .casepackqty(UPDATED_CASEPACKQTY)
            .isregistered(UPDATED_ISREGISTERED)
            .defaultstocklocationid(UPDATED_DEFAULTSTOCKLOCATIONID)
            .rackno(UPDATED_RACKNO)
            .barcodeimage(UPDATED_BARCODEIMAGE)
            .barcodeimageContentType(UPDATED_BARCODEIMAGE_CONTENT_TYPE)
            .commissionempid(UPDATED_COMMISSIONEMPID)
            .checktypeid(UPDATED_CHECKTYPEID)
            .checktype(UPDATED_CHECKTYPE)
            .reorderqty(UPDATED_REORDERQTY)
            .notininvoice(UPDATED_NOTININVOICE);
        return inventory;
    }

    @BeforeEach
    public void initTest() {
        inventory = createEntity(em);
    }

    @Test
    @Transactional
    void createInventory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Inventory
        var returnedInventory = om.readValue(
            restInventoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventory)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Inventory.class
        );

        // Validate the Inventory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertInventoryUpdatableFieldsEquals(returnedInventory, getPersistedInventory(returnedInventory));
    }

    @Test
    @Transactional
    void createInventoryWithExistingId() throws Exception {
        // Create the Inventory with an existing ID
        inventory.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInventoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventory)))
            .andExpect(status().isBadRequest());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInventories() throws Exception {
        // Initialize the database
        inventoryRepository.saveAndFlush(inventory);

        // Get all the inventoryList
        restInventoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(inventory.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].partnumber").value(hasItem(DEFAULT_PARTNUMBER)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].classification1").value(hasItem(DEFAULT_CLASSIFICATION_1)))
            .andExpect(jsonPath("$.[*].classification2").value(hasItem(DEFAULT_CLASSIFICATION_2)))
            .andExpect(jsonPath("$.[*].classification3").value(hasItem(DEFAULT_CLASSIFICATION_3)))
            .andExpect(jsonPath("$.[*].classification4").value(hasItem(DEFAULT_CLASSIFICATION_4)))
            .andExpect(jsonPath("$.[*].classification5").value(hasItem(DEFAULT_CLASSIFICATION_5)))
            .andExpect(jsonPath("$.[*].unitofmeasurement").value(hasItem(DEFAULT_UNITOFMEASUREMENT)))
            .andExpect(jsonPath("$.[*].decimalplaces").value(hasItem(DEFAULT_DECIMALPLACES)))
            .andExpect(jsonPath("$.[*].isassemblyunit").value(hasItem(DEFAULT_ISASSEMBLYUNIT.booleanValue())))
            .andExpect(jsonPath("$.[*].assemblyunitof").value(hasItem(DEFAULT_ASSEMBLYUNITOF)))
            .andExpect(jsonPath("$.[*].reorderlevel").value(hasItem(DEFAULT_REORDERLEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].lastcost").value(hasItem(DEFAULT_LASTCOST.doubleValue())))
            .andExpect(jsonPath("$.[*].lastsellingprice").value(hasItem(DEFAULT_LASTSELLINGPRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].availablequantity").value(hasItem(DEFAULT_AVAILABLEQUANTITY.doubleValue())))
            .andExpect(jsonPath("$.[*].hasbatches").value(hasItem(DEFAULT_HASBATCHES.booleanValue())))
            .andExpect(jsonPath("$.[*].itemspecfilepath").value(hasItem(DEFAULT_ITEMSPECFILEPATH)))
            .andExpect(jsonPath("$.[*].itemimagepath").value(hasItem(DEFAULT_ITEMIMAGEPATH)))
            .andExpect(jsonPath("$.[*].returnprice").value(hasItem(DEFAULT_RETURNPRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].activeitem").value(hasItem(DEFAULT_ACTIVEITEM.booleanValue())))
            .andExpect(jsonPath("$.[*].minstock").value(hasItem(DEFAULT_MINSTOCK.doubleValue())))
            .andExpect(jsonPath("$.[*].maxstock").value(hasItem(DEFAULT_MAXSTOCK.doubleValue())))
            .andExpect(jsonPath("$.[*].dailyaverage").value(hasItem(DEFAULT_DAILYAVERAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].bufferlevel").value(hasItem(DEFAULT_BUFFERLEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].leadtime").value(hasItem(DEFAULT_LEADTIME.doubleValue())))
            .andExpect(jsonPath("$.[*].buffertime").value(hasItem(DEFAULT_BUFFERTIME.doubleValue())))
            .andExpect(jsonPath("$.[*].saftydays").value(hasItem(DEFAULT_SAFTYDAYS.doubleValue())))
            .andExpect(jsonPath("$.[*].accountcode").value(hasItem(DEFAULT_ACCOUNTCODE)))
            .andExpect(jsonPath("$.[*].accountid").value(hasItem(DEFAULT_ACCOUNTID)))
            .andExpect(jsonPath("$.[*].casepackqty").value(hasItem(DEFAULT_CASEPACKQTY)))
            .andExpect(jsonPath("$.[*].isregistered").value(hasItem(DEFAULT_ISREGISTERED.booleanValue())))
            .andExpect(jsonPath("$.[*].defaultstocklocationid").value(hasItem(DEFAULT_DEFAULTSTOCKLOCATIONID)))
            .andExpect(jsonPath("$.[*].rackno").value(hasItem(DEFAULT_RACKNO)))
            .andExpect(jsonPath("$.[*].barcodeimageContentType").value(hasItem(DEFAULT_BARCODEIMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].barcodeimage").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_BARCODEIMAGE))))
            .andExpect(jsonPath("$.[*].commissionempid").value(hasItem(DEFAULT_COMMISSIONEMPID)))
            .andExpect(jsonPath("$.[*].checktypeid").value(hasItem(DEFAULT_CHECKTYPEID)))
            .andExpect(jsonPath("$.[*].checktype").value(hasItem(DEFAULT_CHECKTYPE)))
            .andExpect(jsonPath("$.[*].reorderqty").value(hasItem(DEFAULT_REORDERQTY.doubleValue())))
            .andExpect(jsonPath("$.[*].notininvoice").value(hasItem(DEFAULT_NOTININVOICE.booleanValue())));
    }

    @Test
    @Transactional
    void getInventory() throws Exception {
        // Initialize the database
        inventoryRepository.saveAndFlush(inventory);

        // Get the inventory
        restInventoryMockMvc
            .perform(get(ENTITY_API_URL_ID, inventory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(inventory.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.partnumber").value(DEFAULT_PARTNUMBER))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.classification1").value(DEFAULT_CLASSIFICATION_1))
            .andExpect(jsonPath("$.classification2").value(DEFAULT_CLASSIFICATION_2))
            .andExpect(jsonPath("$.classification3").value(DEFAULT_CLASSIFICATION_3))
            .andExpect(jsonPath("$.classification4").value(DEFAULT_CLASSIFICATION_4))
            .andExpect(jsonPath("$.classification5").value(DEFAULT_CLASSIFICATION_5))
            .andExpect(jsonPath("$.unitofmeasurement").value(DEFAULT_UNITOFMEASUREMENT))
            .andExpect(jsonPath("$.decimalplaces").value(DEFAULT_DECIMALPLACES))
            .andExpect(jsonPath("$.isassemblyunit").value(DEFAULT_ISASSEMBLYUNIT.booleanValue()))
            .andExpect(jsonPath("$.assemblyunitof").value(DEFAULT_ASSEMBLYUNITOF))
            .andExpect(jsonPath("$.reorderlevel").value(DEFAULT_REORDERLEVEL.doubleValue()))
            .andExpect(jsonPath("$.lastcost").value(DEFAULT_LASTCOST.doubleValue()))
            .andExpect(jsonPath("$.lastsellingprice").value(DEFAULT_LASTSELLINGPRICE.doubleValue()))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.availablequantity").value(DEFAULT_AVAILABLEQUANTITY.doubleValue()))
            .andExpect(jsonPath("$.hasbatches").value(DEFAULT_HASBATCHES.booleanValue()))
            .andExpect(jsonPath("$.itemspecfilepath").value(DEFAULT_ITEMSPECFILEPATH))
            .andExpect(jsonPath("$.itemimagepath").value(DEFAULT_ITEMIMAGEPATH))
            .andExpect(jsonPath("$.returnprice").value(DEFAULT_RETURNPRICE.doubleValue()))
            .andExpect(jsonPath("$.activeitem").value(DEFAULT_ACTIVEITEM.booleanValue()))
            .andExpect(jsonPath("$.minstock").value(DEFAULT_MINSTOCK.doubleValue()))
            .andExpect(jsonPath("$.maxstock").value(DEFAULT_MAXSTOCK.doubleValue()))
            .andExpect(jsonPath("$.dailyaverage").value(DEFAULT_DAILYAVERAGE.doubleValue()))
            .andExpect(jsonPath("$.bufferlevel").value(DEFAULT_BUFFERLEVEL.doubleValue()))
            .andExpect(jsonPath("$.leadtime").value(DEFAULT_LEADTIME.doubleValue()))
            .andExpect(jsonPath("$.buffertime").value(DEFAULT_BUFFERTIME.doubleValue()))
            .andExpect(jsonPath("$.saftydays").value(DEFAULT_SAFTYDAYS.doubleValue()))
            .andExpect(jsonPath("$.accountcode").value(DEFAULT_ACCOUNTCODE))
            .andExpect(jsonPath("$.accountid").value(DEFAULT_ACCOUNTID))
            .andExpect(jsonPath("$.casepackqty").value(DEFAULT_CASEPACKQTY))
            .andExpect(jsonPath("$.isregistered").value(DEFAULT_ISREGISTERED.booleanValue()))
            .andExpect(jsonPath("$.defaultstocklocationid").value(DEFAULT_DEFAULTSTOCKLOCATIONID))
            .andExpect(jsonPath("$.rackno").value(DEFAULT_RACKNO))
            .andExpect(jsonPath("$.barcodeimageContentType").value(DEFAULT_BARCODEIMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.barcodeimage").value(Base64.getEncoder().encodeToString(DEFAULT_BARCODEIMAGE)))
            .andExpect(jsonPath("$.commissionempid").value(DEFAULT_COMMISSIONEMPID))
            .andExpect(jsonPath("$.checktypeid").value(DEFAULT_CHECKTYPEID))
            .andExpect(jsonPath("$.checktype").value(DEFAULT_CHECKTYPE))
            .andExpect(jsonPath("$.reorderqty").value(DEFAULT_REORDERQTY.doubleValue()))
            .andExpect(jsonPath("$.notininvoice").value(DEFAULT_NOTININVOICE.booleanValue()));
    }

    @Test
    @Transactional
    void getNonExistingInventory() throws Exception {
        // Get the inventory
        restInventoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInventory() throws Exception {
        // Initialize the database
        inventoryRepository.saveAndFlush(inventory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventory
        Inventory updatedInventory = inventoryRepository.findById(inventory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedInventory are not directly saved in db
        em.detach(updatedInventory);
        updatedInventory
            .code(UPDATED_CODE)
            .partnumber(UPDATED_PARTNUMBER)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .classification1(UPDATED_CLASSIFICATION_1)
            .classification2(UPDATED_CLASSIFICATION_2)
            .classification3(UPDATED_CLASSIFICATION_3)
            .classification4(UPDATED_CLASSIFICATION_4)
            .classification5(UPDATED_CLASSIFICATION_5)
            .unitofmeasurement(UPDATED_UNITOFMEASUREMENT)
            .decimalplaces(UPDATED_DECIMALPLACES)
            .isassemblyunit(UPDATED_ISASSEMBLYUNIT)
            .assemblyunitof(UPDATED_ASSEMBLYUNITOF)
            .reorderlevel(UPDATED_REORDERLEVEL)
            .lastcost(UPDATED_LASTCOST)
            .lastsellingprice(UPDATED_LASTSELLINGPRICE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .itemspecfilepath(UPDATED_ITEMSPECFILEPATH)
            .itemimagepath(UPDATED_ITEMIMAGEPATH)
            .returnprice(UPDATED_RETURNPRICE)
            .activeitem(UPDATED_ACTIVEITEM)
            .minstock(UPDATED_MINSTOCK)
            .maxstock(UPDATED_MAXSTOCK)
            .dailyaverage(UPDATED_DAILYAVERAGE)
            .bufferlevel(UPDATED_BUFFERLEVEL)
            .leadtime(UPDATED_LEADTIME)
            .buffertime(UPDATED_BUFFERTIME)
            .saftydays(UPDATED_SAFTYDAYS)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID)
            .casepackqty(UPDATED_CASEPACKQTY)
            .isregistered(UPDATED_ISREGISTERED)
            .defaultstocklocationid(UPDATED_DEFAULTSTOCKLOCATIONID)
            .rackno(UPDATED_RACKNO)
            .barcodeimage(UPDATED_BARCODEIMAGE)
            .barcodeimageContentType(UPDATED_BARCODEIMAGE_CONTENT_TYPE)
            .commissionempid(UPDATED_COMMISSIONEMPID)
            .checktypeid(UPDATED_CHECKTYPEID)
            .checktype(UPDATED_CHECKTYPE)
            .reorderqty(UPDATED_REORDERQTY)
            .notininvoice(UPDATED_NOTININVOICE);

        restInventoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedInventory.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedInventory))
            )
            .andExpect(status().isOk());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedInventoryToMatchAllProperties(updatedInventory);
    }

    @Test
    @Transactional
    void putNonExistingInventory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, inventory.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInventory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(inventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInventory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(inventory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInventoryWithPatch() throws Exception {
        // Initialize the database
        inventoryRepository.saveAndFlush(inventory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventory using partial update
        Inventory partialUpdatedInventory = new Inventory();
        partialUpdatedInventory.setId(inventory.getId());

        partialUpdatedInventory
            .code(UPDATED_CODE)
            .partnumber(UPDATED_PARTNUMBER)
            .description(UPDATED_DESCRIPTION)
            .classification1(UPDATED_CLASSIFICATION_1)
            .classification5(UPDATED_CLASSIFICATION_5)
            .assemblyunitof(UPDATED_ASSEMBLYUNITOF)
            .lmu(UPDATED_LMU)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .itemspecfilepath(UPDATED_ITEMSPECFILEPATH)
            .itemimagepath(UPDATED_ITEMIMAGEPATH)
            .returnprice(UPDATED_RETURNPRICE)
            .activeitem(UPDATED_ACTIVEITEM)
            .minstock(UPDATED_MINSTOCK)
            .bufferlevel(UPDATED_BUFFERLEVEL)
            .leadtime(UPDATED_LEADTIME)
            .buffertime(UPDATED_BUFFERTIME)
            .saftydays(UPDATED_SAFTYDAYS)
            .accountid(UPDATED_ACCOUNTID)
            .casepackqty(UPDATED_CASEPACKQTY)
            .isregistered(UPDATED_ISREGISTERED)
            .defaultstocklocationid(UPDATED_DEFAULTSTOCKLOCATIONID)
            .rackno(UPDATED_RACKNO)
            .checktypeid(UPDATED_CHECKTYPEID)
            .notininvoice(UPDATED_NOTININVOICE);

        restInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInventory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInventory))
            )
            .andExpect(status().isOk());

        // Validate the Inventory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInventoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedInventory, inventory),
            getPersistedInventory(inventory)
        );
    }

    @Test
    @Transactional
    void fullUpdateInventoryWithPatch() throws Exception {
        // Initialize the database
        inventoryRepository.saveAndFlush(inventory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the inventory using partial update
        Inventory partialUpdatedInventory = new Inventory();
        partialUpdatedInventory.setId(inventory.getId());

        partialUpdatedInventory
            .code(UPDATED_CODE)
            .partnumber(UPDATED_PARTNUMBER)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .classification1(UPDATED_CLASSIFICATION_1)
            .classification2(UPDATED_CLASSIFICATION_2)
            .classification3(UPDATED_CLASSIFICATION_3)
            .classification4(UPDATED_CLASSIFICATION_4)
            .classification5(UPDATED_CLASSIFICATION_5)
            .unitofmeasurement(UPDATED_UNITOFMEASUREMENT)
            .decimalplaces(UPDATED_DECIMALPLACES)
            .isassemblyunit(UPDATED_ISASSEMBLYUNIT)
            .assemblyunitof(UPDATED_ASSEMBLYUNITOF)
            .reorderlevel(UPDATED_REORDERLEVEL)
            .lastcost(UPDATED_LASTCOST)
            .lastsellingprice(UPDATED_LASTSELLINGPRICE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .availablequantity(UPDATED_AVAILABLEQUANTITY)
            .hasbatches(UPDATED_HASBATCHES)
            .itemspecfilepath(UPDATED_ITEMSPECFILEPATH)
            .itemimagepath(UPDATED_ITEMIMAGEPATH)
            .returnprice(UPDATED_RETURNPRICE)
            .activeitem(UPDATED_ACTIVEITEM)
            .minstock(UPDATED_MINSTOCK)
            .maxstock(UPDATED_MAXSTOCK)
            .dailyaverage(UPDATED_DAILYAVERAGE)
            .bufferlevel(UPDATED_BUFFERLEVEL)
            .leadtime(UPDATED_LEADTIME)
            .buffertime(UPDATED_BUFFERTIME)
            .saftydays(UPDATED_SAFTYDAYS)
            .accountcode(UPDATED_ACCOUNTCODE)
            .accountid(UPDATED_ACCOUNTID)
            .casepackqty(UPDATED_CASEPACKQTY)
            .isregistered(UPDATED_ISREGISTERED)
            .defaultstocklocationid(UPDATED_DEFAULTSTOCKLOCATIONID)
            .rackno(UPDATED_RACKNO)
            .barcodeimage(UPDATED_BARCODEIMAGE)
            .barcodeimageContentType(UPDATED_BARCODEIMAGE_CONTENT_TYPE)
            .commissionempid(UPDATED_COMMISSIONEMPID)
            .checktypeid(UPDATED_CHECKTYPEID)
            .checktype(UPDATED_CHECKTYPE)
            .reorderqty(UPDATED_REORDERQTY)
            .notininvoice(UPDATED_NOTININVOICE);

        restInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInventory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedInventory))
            )
            .andExpect(status().isOk());

        // Validate the Inventory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertInventoryUpdatableFieldsEquals(partialUpdatedInventory, getPersistedInventory(partialUpdatedInventory));
    }

    @Test
    @Transactional
    void patchNonExistingInventory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventory.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, inventory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(inventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInventory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(inventory))
            )
            .andExpect(status().isBadRequest());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInventory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        inventory.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInventoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(inventory)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Inventory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInventory() throws Exception {
        // Initialize the database
        inventoryRepository.saveAndFlush(inventory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the inventory
        restInventoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, inventory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return inventoryRepository.count();
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

    protected Inventory getPersistedInventory(Inventory inventory) {
        return inventoryRepository.findById(inventory.getId()).orElseThrow();
    }

    protected void assertPersistedInventoryToMatchAllProperties(Inventory expectedInventory) {
        assertInventoryAllPropertiesEquals(expectedInventory, getPersistedInventory(expectedInventory));
    }

    protected void assertPersistedInventoryToMatchUpdatableProperties(Inventory expectedInventory) {
        assertInventoryAllUpdatablePropertiesEquals(expectedInventory, getPersistedInventory(expectedInventory));
    }
}
