package com.heavenscode.rac.web.rest;

import static com.heavenscode.rac.domain.SalesinvoiceAsserts.*;
import static com.heavenscode.rac.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenscode.rac.IntegrationTest;
import com.heavenscode.rac.domain.Salesinvoice;
import com.heavenscode.rac.repository.SalesinvoiceRepository;
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
 * Integration tests for the {@link SalesinvoiceResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesinvoiceResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_INVOICEDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INVOICEDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CREATEDDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATEDDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_QUOTEID = 1;
    private static final Integer UPDATED_QUOTEID = 2;

    private static final Integer DEFAULT_ORDERID = 1;
    private static final Integer UPDATED_ORDERID = 2;

    private static final Instant DEFAULT_DELIEVERYDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DELIEVERYDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_SALESREPID = 1;
    private static final Integer UPDATED_SALESREPID = 2;

    private static final String DEFAULT_SALESREPNAME = "AAAAAAAAAA";
    private static final String UPDATED_SALESREPNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DELIEVERFROM = "AAAAAAAAAA";
    private static final String UPDATED_DELIEVERFROM = "BBBBBBBBBB";

    private static final Integer DEFAULT_CUSTOMERID = 1;
    private static final Integer UPDATED_CUSTOMERID = 2;

    private static final String DEFAULT_CUSTOMERNAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMERADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMERADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERYADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERYADDRESS = "BBBBBBBBBB";

    private static final Float DEFAULT_SUBTOTAL = 1F;
    private static final Float UPDATED_SUBTOTAL = 2F;

    private static final Float DEFAULT_TOTALTAX = 1F;
    private static final Float UPDATED_TOTALTAX = 2F;

    private static final Float DEFAULT_TOTALDISCOUNT = 1F;
    private static final Float UPDATED_TOTALDISCOUNT = 2F;

    private static final Float DEFAULT_NETTOTAL = 1F;
    private static final Float UPDATED_NETTOTAL = 2F;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_LMU = 1;
    private static final Integer UPDATED_LMU = 2;

    private static final Instant DEFAULT_LMD = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_LMD = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Float DEFAULT_PAIDAMOUNT = 1F;
    private static final Float UPDATED_PAIDAMOUNT = 2F;

    private static final Float DEFAULT_AMOUNTOWING = 1F;
    private static final Float UPDATED_AMOUNTOWING = 2F;

    private static final Boolean DEFAULT_ISACTIVE = false;
    private static final Boolean UPDATED_ISACTIVE = true;

    private static final Integer DEFAULT_LOCATIONID = 1;
    private static final Integer UPDATED_LOCATIONID = 2;

    private static final String DEFAULT_LOCATIONCODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCATIONCODE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCECODE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCECODE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREATEDBYID = 1;
    private static final Integer UPDATED_CREATEDBYID = 2;

    private static final String DEFAULT_CREATEDBYNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATEDBYNAME = "BBBBBBBBBB";

    private static final Float DEFAULT_AUTOCARECHARGES = 1F;
    private static final Float UPDATED_AUTOCARECHARGES = 2F;

    private static final Integer DEFAULT_AUTOCAREJOBID = 1;
    private static final Integer UPDATED_AUTOCAREJOBID = 2;

    private static final String DEFAULT_VEHICLENO = "AAAAAAAAAA";
    private static final String UPDATED_VEHICLENO = "BBBBBBBBBB";

    private static final String DEFAULT_NEXTMETER = "AAAAAAAAAA";
    private static final String UPDATED_NEXTMETER = "BBBBBBBBBB";

    private static final String DEFAULT_CURRENTMETER = "AAAAAAAAAA";
    private static final String UPDATED_CURRENTMETER = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_HASDUMMYBILL = false;
    private static final Boolean UPDATED_HASDUMMYBILL = true;

    private static final Integer DEFAULT_DUMMYBILLID = 1;
    private static final Integer UPDATED_DUMMYBILLID = 2;

    private static final Float DEFAULT_DUMMYBILLAMOUNT = 1F;
    private static final Float UPDATED_DUMMYBILLAMOUNT = 2F;

    private static final Float DEFAULT_DUMMYCOMMISION = 1F;
    private static final Float UPDATED_DUMMYCOMMISION = 2F;

    private static final Boolean DEFAULT_ISSERVICEINVOICE = false;
    private static final Boolean UPDATED_ISSERVICEINVOICE = true;

    private static final Float DEFAULT_NBTAMOUNT = 1F;
    private static final Float UPDATED_NBTAMOUNT = 2F;

    private static final Float DEFAULT_VATAMOUNT = 1F;
    private static final Float UPDATED_VATAMOUNT = 2F;

    private static final Integer DEFAULT_AUTOCARECOMPANYID = 1;
    private static final Integer UPDATED_AUTOCARECOMPANYID = 2;

    private static final Boolean DEFAULT_ISCOMPANYINVOICE = false;
    private static final Boolean UPDATED_ISCOMPANYINVOICE = true;

    private static final Instant DEFAULT_INVCANCELDATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_INVCANCELDATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Integer DEFAULT_INVCANCELBY = 1;
    private static final Integer UPDATED_INVCANCELBY = 2;

    private static final Boolean DEFAULT_ISVATINVOICE = false;
    private static final Boolean UPDATED_ISVATINVOICE = true;

    private static final String DEFAULT_PAYMENTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PAYMENTTYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_PENDINGAMOUNT = 1F;
    private static final Float UPDATED_PENDINGAMOUNT = 2F;

    private static final Float DEFAULT_ADVANCEPAYMENT = 1F;
    private static final Float UPDATED_ADVANCEPAYMENT = 2F;

    private static final String DEFAULT_DISCOUNTCODE = "AAAAAAAAAA";
    private static final String UPDATED_DISCOUNTCODE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/salesinvoices";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SalesinvoiceRepository salesinvoiceRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesinvoiceMockMvc;

    private Salesinvoice salesinvoice;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salesinvoice createEntity(EntityManager em) {
        Salesinvoice salesinvoice = new Salesinvoice()
            .code(DEFAULT_CODE)
            .invoicedate(DEFAULT_INVOICEDATE)
            .createddate(DEFAULT_CREATEDDATE)
            .quoteid(DEFAULT_QUOTEID)
            .orderid(DEFAULT_ORDERID)
            .delieverydate(DEFAULT_DELIEVERYDATE)
            .salesrepid(DEFAULT_SALESREPID)
            .salesrepname(DEFAULT_SALESREPNAME)
            .delieverfrom(DEFAULT_DELIEVERFROM)
            .customerid(DEFAULT_CUSTOMERID)
            .customername(DEFAULT_CUSTOMERNAME)
            .customeraddress(DEFAULT_CUSTOMERADDRESS)
            .deliveryaddress(DEFAULT_DELIVERYADDRESS)
            .subtotal(DEFAULT_SUBTOTAL)
            .totaltax(DEFAULT_TOTALTAX)
            .totaldiscount(DEFAULT_TOTALDISCOUNT)
            .nettotal(DEFAULT_NETTOTAL)
            .message(DEFAULT_MESSAGE)
            .lmu(DEFAULT_LMU)
            .lmd(DEFAULT_LMD)
            .paidamount(DEFAULT_PAIDAMOUNT)
            .amountowing(DEFAULT_AMOUNTOWING)
            .isactive(DEFAULT_ISACTIVE)
            .locationid(DEFAULT_LOCATIONID)
            .locationcode(DEFAULT_LOCATIONCODE)
            .referencecode(DEFAULT_REFERENCECODE)
            .createdbyid(DEFAULT_CREATEDBYID)
            .createdbyname(DEFAULT_CREATEDBYNAME)
            .autocarecharges(DEFAULT_AUTOCARECHARGES)
            .autocarejobid(DEFAULT_AUTOCAREJOBID)
            .vehicleno(DEFAULT_VEHICLENO)
            .nextmeter(DEFAULT_NEXTMETER)
            .currentmeter(DEFAULT_CURRENTMETER)
            .remarks(DEFAULT_REMARKS)
            .hasdummybill(DEFAULT_HASDUMMYBILL)
            .dummybillid(DEFAULT_DUMMYBILLID)
            .dummybillamount(DEFAULT_DUMMYBILLAMOUNT)
            .dummycommision(DEFAULT_DUMMYCOMMISION)
            .isserviceinvoice(DEFAULT_ISSERVICEINVOICE)
            .nbtamount(DEFAULT_NBTAMOUNT)
            .vatamount(DEFAULT_VATAMOUNT)
            .autocarecompanyid(DEFAULT_AUTOCARECOMPANYID)
            .iscompanyinvoice(DEFAULT_ISCOMPANYINVOICE)
            .invcanceldate(DEFAULT_INVCANCELDATE)
            .invcancelby(DEFAULT_INVCANCELBY)
            .isvatinvoice(DEFAULT_ISVATINVOICE)
            .paymenttype(DEFAULT_PAYMENTTYPE)
            .pendingamount(DEFAULT_PENDINGAMOUNT)
            .advancepayment(DEFAULT_ADVANCEPAYMENT)
            .discountcode(DEFAULT_DISCOUNTCODE);
        return salesinvoice;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Salesinvoice createUpdatedEntity(EntityManager em) {
        Salesinvoice salesinvoice = new Salesinvoice()
            .code(UPDATED_CODE)
            .invoicedate(UPDATED_INVOICEDATE)
            .createddate(UPDATED_CREATEDDATE)
            .quoteid(UPDATED_QUOTEID)
            .orderid(UPDATED_ORDERID)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
            .delieverfrom(UPDATED_DELIEVERFROM)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .totaltax(UPDATED_TOTALTAX)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .paidamount(UPDATED_PAIDAMOUNT)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecharges(UPDATED_AUTOCARECHARGES)
            .autocarejobid(UPDATED_AUTOCAREJOBID)
            .vehicleno(UPDATED_VEHICLENO)
            .nextmeter(UPDATED_NEXTMETER)
            .currentmeter(UPDATED_CURRENTMETER)
            .remarks(UPDATED_REMARKS)
            .hasdummybill(UPDATED_HASDUMMYBILL)
            .dummybillid(UPDATED_DUMMYBILLID)
            .dummybillamount(UPDATED_DUMMYBILLAMOUNT)
            .dummycommision(UPDATED_DUMMYCOMMISION)
            .isserviceinvoice(UPDATED_ISSERVICEINVOICE)
            .nbtamount(UPDATED_NBTAMOUNT)
            .vatamount(UPDATED_VATAMOUNT)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID)
            .iscompanyinvoice(UPDATED_ISCOMPANYINVOICE)
            .invcanceldate(UPDATED_INVCANCELDATE)
            .invcancelby(UPDATED_INVCANCELBY)
            .isvatinvoice(UPDATED_ISVATINVOICE)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .pendingamount(UPDATED_PENDINGAMOUNT)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .discountcode(UPDATED_DISCOUNTCODE);
        return salesinvoice;
    }

    @BeforeEach
    public void initTest() {
        salesinvoice = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesinvoice() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Salesinvoice
        var returnedSalesinvoice = om.readValue(
            restSalesinvoiceMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesinvoice)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Salesinvoice.class
        );

        // Validate the Salesinvoice in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSalesinvoiceUpdatableFieldsEquals(returnedSalesinvoice, getPersistedSalesinvoice(returnedSalesinvoice));
    }

    @Test
    @Transactional
    void createSalesinvoiceWithExistingId() throws Exception {
        // Create the Salesinvoice with an existing ID
        salesinvoice.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesinvoiceMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesinvoice)))
            .andExpect(status().isBadRequest());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesinvoices() throws Exception {
        // Initialize the database
        salesinvoiceRepository.saveAndFlush(salesinvoice);

        // Get all the salesinvoiceList
        restSalesinvoiceMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesinvoice.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].invoicedate").value(hasItem(DEFAULT_INVOICEDATE.toString())))
            .andExpect(jsonPath("$.[*].createddate").value(hasItem(DEFAULT_CREATEDDATE.toString())))
            .andExpect(jsonPath("$.[*].quoteid").value(hasItem(DEFAULT_QUOTEID)))
            .andExpect(jsonPath("$.[*].orderid").value(hasItem(DEFAULT_ORDERID)))
            .andExpect(jsonPath("$.[*].delieverydate").value(hasItem(DEFAULT_DELIEVERYDATE.toString())))
            .andExpect(jsonPath("$.[*].salesrepid").value(hasItem(DEFAULT_SALESREPID)))
            .andExpect(jsonPath("$.[*].salesrepname").value(hasItem(DEFAULT_SALESREPNAME)))
            .andExpect(jsonPath("$.[*].delieverfrom").value(hasItem(DEFAULT_DELIEVERFROM)))
            .andExpect(jsonPath("$.[*].customerid").value(hasItem(DEFAULT_CUSTOMERID)))
            .andExpect(jsonPath("$.[*].customername").value(hasItem(DEFAULT_CUSTOMERNAME)))
            .andExpect(jsonPath("$.[*].customeraddress").value(hasItem(DEFAULT_CUSTOMERADDRESS)))
            .andExpect(jsonPath("$.[*].deliveryaddress").value(hasItem(DEFAULT_DELIVERYADDRESS)))
            .andExpect(jsonPath("$.[*].subtotal").value(hasItem(DEFAULT_SUBTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].totaltax").value(hasItem(DEFAULT_TOTALTAX.doubleValue())))
            .andExpect(jsonPath("$.[*].totaldiscount").value(hasItem(DEFAULT_TOTALDISCOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].nettotal").value(hasItem(DEFAULT_NETTOTAL.doubleValue())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].lmu").value(hasItem(DEFAULT_LMU)))
            .andExpect(jsonPath("$.[*].lmd").value(hasItem(DEFAULT_LMD.toString())))
            .andExpect(jsonPath("$.[*].paidamount").value(hasItem(DEFAULT_PAIDAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].amountowing").value(hasItem(DEFAULT_AMOUNTOWING.doubleValue())))
            .andExpect(jsonPath("$.[*].isactive").value(hasItem(DEFAULT_ISACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].locationid").value(hasItem(DEFAULT_LOCATIONID)))
            .andExpect(jsonPath("$.[*].locationcode").value(hasItem(DEFAULT_LOCATIONCODE)))
            .andExpect(jsonPath("$.[*].referencecode").value(hasItem(DEFAULT_REFERENCECODE)))
            .andExpect(jsonPath("$.[*].createdbyid").value(hasItem(DEFAULT_CREATEDBYID)))
            .andExpect(jsonPath("$.[*].createdbyname").value(hasItem(DEFAULT_CREATEDBYNAME)))
            .andExpect(jsonPath("$.[*].autocarecharges").value(hasItem(DEFAULT_AUTOCARECHARGES.doubleValue())))
            .andExpect(jsonPath("$.[*].autocarejobid").value(hasItem(DEFAULT_AUTOCAREJOBID)))
            .andExpect(jsonPath("$.[*].vehicleno").value(hasItem(DEFAULT_VEHICLENO)))
            .andExpect(jsonPath("$.[*].nextmeter").value(hasItem(DEFAULT_NEXTMETER)))
            .andExpect(jsonPath("$.[*].currentmeter").value(hasItem(DEFAULT_CURRENTMETER)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].hasdummybill").value(hasItem(DEFAULT_HASDUMMYBILL.booleanValue())))
            .andExpect(jsonPath("$.[*].dummybillid").value(hasItem(DEFAULT_DUMMYBILLID)))
            .andExpect(jsonPath("$.[*].dummybillamount").value(hasItem(DEFAULT_DUMMYBILLAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].dummycommision").value(hasItem(DEFAULT_DUMMYCOMMISION.doubleValue())))
            .andExpect(jsonPath("$.[*].isserviceinvoice").value(hasItem(DEFAULT_ISSERVICEINVOICE.booleanValue())))
            .andExpect(jsonPath("$.[*].nbtamount").value(hasItem(DEFAULT_NBTAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].vatamount").value(hasItem(DEFAULT_VATAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].autocarecompanyid").value(hasItem(DEFAULT_AUTOCARECOMPANYID)))
            .andExpect(jsonPath("$.[*].iscompanyinvoice").value(hasItem(DEFAULT_ISCOMPANYINVOICE.booleanValue())))
            .andExpect(jsonPath("$.[*].invcanceldate").value(hasItem(DEFAULT_INVCANCELDATE.toString())))
            .andExpect(jsonPath("$.[*].invcancelby").value(hasItem(DEFAULT_INVCANCELBY)))
            .andExpect(jsonPath("$.[*].isvatinvoice").value(hasItem(DEFAULT_ISVATINVOICE.booleanValue())))
            .andExpect(jsonPath("$.[*].paymenttype").value(hasItem(DEFAULT_PAYMENTTYPE)))
            .andExpect(jsonPath("$.[*].pendingamount").value(hasItem(DEFAULT_PENDINGAMOUNT.doubleValue())))
            .andExpect(jsonPath("$.[*].advancepayment").value(hasItem(DEFAULT_ADVANCEPAYMENT.doubleValue())))
            .andExpect(jsonPath("$.[*].discountcode").value(hasItem(DEFAULT_DISCOUNTCODE)));
    }

    @Test
    @Transactional
    void getSalesinvoice() throws Exception {
        // Initialize the database
        salesinvoiceRepository.saveAndFlush(salesinvoice);

        // Get the salesinvoice
        restSalesinvoiceMockMvc
            .perform(get(ENTITY_API_URL_ID, salesinvoice.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesinvoice.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.invoicedate").value(DEFAULT_INVOICEDATE.toString()))
            .andExpect(jsonPath("$.createddate").value(DEFAULT_CREATEDDATE.toString()))
            .andExpect(jsonPath("$.quoteid").value(DEFAULT_QUOTEID))
            .andExpect(jsonPath("$.orderid").value(DEFAULT_ORDERID))
            .andExpect(jsonPath("$.delieverydate").value(DEFAULT_DELIEVERYDATE.toString()))
            .andExpect(jsonPath("$.salesrepid").value(DEFAULT_SALESREPID))
            .andExpect(jsonPath("$.salesrepname").value(DEFAULT_SALESREPNAME))
            .andExpect(jsonPath("$.delieverfrom").value(DEFAULT_DELIEVERFROM))
            .andExpect(jsonPath("$.customerid").value(DEFAULT_CUSTOMERID))
            .andExpect(jsonPath("$.customername").value(DEFAULT_CUSTOMERNAME))
            .andExpect(jsonPath("$.customeraddress").value(DEFAULT_CUSTOMERADDRESS))
            .andExpect(jsonPath("$.deliveryaddress").value(DEFAULT_DELIVERYADDRESS))
            .andExpect(jsonPath("$.subtotal").value(DEFAULT_SUBTOTAL.doubleValue()))
            .andExpect(jsonPath("$.totaltax").value(DEFAULT_TOTALTAX.doubleValue()))
            .andExpect(jsonPath("$.totaldiscount").value(DEFAULT_TOTALDISCOUNT.doubleValue()))
            .andExpect(jsonPath("$.nettotal").value(DEFAULT_NETTOTAL.doubleValue()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.lmu").value(DEFAULT_LMU))
            .andExpect(jsonPath("$.lmd").value(DEFAULT_LMD.toString()))
            .andExpect(jsonPath("$.paidamount").value(DEFAULT_PAIDAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.amountowing").value(DEFAULT_AMOUNTOWING.doubleValue()))
            .andExpect(jsonPath("$.isactive").value(DEFAULT_ISACTIVE.booleanValue()))
            .andExpect(jsonPath("$.locationid").value(DEFAULT_LOCATIONID))
            .andExpect(jsonPath("$.locationcode").value(DEFAULT_LOCATIONCODE))
            .andExpect(jsonPath("$.referencecode").value(DEFAULT_REFERENCECODE))
            .andExpect(jsonPath("$.createdbyid").value(DEFAULT_CREATEDBYID))
            .andExpect(jsonPath("$.createdbyname").value(DEFAULT_CREATEDBYNAME))
            .andExpect(jsonPath("$.autocarecharges").value(DEFAULT_AUTOCARECHARGES.doubleValue()))
            .andExpect(jsonPath("$.autocarejobid").value(DEFAULT_AUTOCAREJOBID))
            .andExpect(jsonPath("$.vehicleno").value(DEFAULT_VEHICLENO))
            .andExpect(jsonPath("$.nextmeter").value(DEFAULT_NEXTMETER))
            .andExpect(jsonPath("$.currentmeter").value(DEFAULT_CURRENTMETER))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.hasdummybill").value(DEFAULT_HASDUMMYBILL.booleanValue()))
            .andExpect(jsonPath("$.dummybillid").value(DEFAULT_DUMMYBILLID))
            .andExpect(jsonPath("$.dummybillamount").value(DEFAULT_DUMMYBILLAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.dummycommision").value(DEFAULT_DUMMYCOMMISION.doubleValue()))
            .andExpect(jsonPath("$.isserviceinvoice").value(DEFAULT_ISSERVICEINVOICE.booleanValue()))
            .andExpect(jsonPath("$.nbtamount").value(DEFAULT_NBTAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.vatamount").value(DEFAULT_VATAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.autocarecompanyid").value(DEFAULT_AUTOCARECOMPANYID))
            .andExpect(jsonPath("$.iscompanyinvoice").value(DEFAULT_ISCOMPANYINVOICE.booleanValue()))
            .andExpect(jsonPath("$.invcanceldate").value(DEFAULT_INVCANCELDATE.toString()))
            .andExpect(jsonPath("$.invcancelby").value(DEFAULT_INVCANCELBY))
            .andExpect(jsonPath("$.isvatinvoice").value(DEFAULT_ISVATINVOICE.booleanValue()))
            .andExpect(jsonPath("$.paymenttype").value(DEFAULT_PAYMENTTYPE))
            .andExpect(jsonPath("$.pendingamount").value(DEFAULT_PENDINGAMOUNT.doubleValue()))
            .andExpect(jsonPath("$.advancepayment").value(DEFAULT_ADVANCEPAYMENT.doubleValue()))
            .andExpect(jsonPath("$.discountcode").value(DEFAULT_DISCOUNTCODE));
    }

    @Test
    @Transactional
    void getNonExistingSalesinvoice() throws Exception {
        // Get the salesinvoice
        restSalesinvoiceMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesinvoice() throws Exception {
        // Initialize the database
        salesinvoiceRepository.saveAndFlush(salesinvoice);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the salesinvoice
        Salesinvoice updatedSalesinvoice = salesinvoiceRepository.findById(salesinvoice.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSalesinvoice are not directly saved in db
        em.detach(updatedSalesinvoice);
        updatedSalesinvoice
            .code(UPDATED_CODE)
            .invoicedate(UPDATED_INVOICEDATE)
            .createddate(UPDATED_CREATEDDATE)
            .quoteid(UPDATED_QUOTEID)
            .orderid(UPDATED_ORDERID)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
            .delieverfrom(UPDATED_DELIEVERFROM)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .totaltax(UPDATED_TOTALTAX)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .paidamount(UPDATED_PAIDAMOUNT)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecharges(UPDATED_AUTOCARECHARGES)
            .autocarejobid(UPDATED_AUTOCAREJOBID)
            .vehicleno(UPDATED_VEHICLENO)
            .nextmeter(UPDATED_NEXTMETER)
            .currentmeter(UPDATED_CURRENTMETER)
            .remarks(UPDATED_REMARKS)
            .hasdummybill(UPDATED_HASDUMMYBILL)
            .dummybillid(UPDATED_DUMMYBILLID)
            .dummybillamount(UPDATED_DUMMYBILLAMOUNT)
            .dummycommision(UPDATED_DUMMYCOMMISION)
            .isserviceinvoice(UPDATED_ISSERVICEINVOICE)
            .nbtamount(UPDATED_NBTAMOUNT)
            .vatamount(UPDATED_VATAMOUNT)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID)
            .iscompanyinvoice(UPDATED_ISCOMPANYINVOICE)
            .invcanceldate(UPDATED_INVCANCELDATE)
            .invcancelby(UPDATED_INVCANCELBY)
            .isvatinvoice(UPDATED_ISVATINVOICE)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .pendingamount(UPDATED_PENDINGAMOUNT)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .discountcode(UPDATED_DISCOUNTCODE);

        restSalesinvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSalesinvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSalesinvoice))
            )
            .andExpect(status().isOk());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSalesinvoiceToMatchAllProperties(updatedSalesinvoice);
    }

    @Test
    @Transactional
    void putNonExistingSalesinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesinvoice.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesinvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesinvoice.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(salesinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesinvoiceMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(salesinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesinvoiceMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(salesinvoice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesinvoiceWithPatch() throws Exception {
        // Initialize the database
        salesinvoiceRepository.saveAndFlush(salesinvoice);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the salesinvoice using partial update
        Salesinvoice partialUpdatedSalesinvoice = new Salesinvoice();
        partialUpdatedSalesinvoice.setId(salesinvoice.getId());

        partialUpdatedSalesinvoice
            .code(UPDATED_CODE)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .salesrepname(UPDATED_SALESREPNAME)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .autocarecharges(UPDATED_AUTOCARECHARGES)
            .autocarejobid(UPDATED_AUTOCAREJOBID)
            .hasdummybill(UPDATED_HASDUMMYBILL)
            .dummybillid(UPDATED_DUMMYBILLID)
            .nbtamount(UPDATED_NBTAMOUNT)
            .vatamount(UPDATED_VATAMOUNT)
            .iscompanyinvoice(UPDATED_ISCOMPANYINVOICE)
            .invcanceldate(UPDATED_INVCANCELDATE)
            .invcancelby(UPDATED_INVCANCELBY)
            .isvatinvoice(UPDATED_ISVATINVOICE)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .pendingamount(UPDATED_PENDINGAMOUNT)
            .advancepayment(UPDATED_ADVANCEPAYMENT);

        restSalesinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesinvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSalesinvoice))
            )
            .andExpect(status().isOk());

        // Validate the Salesinvoice in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSalesinvoiceUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSalesinvoice, salesinvoice),
            getPersistedSalesinvoice(salesinvoice)
        );
    }

    @Test
    @Transactional
    void fullUpdateSalesinvoiceWithPatch() throws Exception {
        // Initialize the database
        salesinvoiceRepository.saveAndFlush(salesinvoice);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the salesinvoice using partial update
        Salesinvoice partialUpdatedSalesinvoice = new Salesinvoice();
        partialUpdatedSalesinvoice.setId(salesinvoice.getId());

        partialUpdatedSalesinvoice
            .code(UPDATED_CODE)
            .invoicedate(UPDATED_INVOICEDATE)
            .createddate(UPDATED_CREATEDDATE)
            .quoteid(UPDATED_QUOTEID)
            .orderid(UPDATED_ORDERID)
            .delieverydate(UPDATED_DELIEVERYDATE)
            .salesrepid(UPDATED_SALESREPID)
            .salesrepname(UPDATED_SALESREPNAME)
            .delieverfrom(UPDATED_DELIEVERFROM)
            .customerid(UPDATED_CUSTOMERID)
            .customername(UPDATED_CUSTOMERNAME)
            .customeraddress(UPDATED_CUSTOMERADDRESS)
            .deliveryaddress(UPDATED_DELIVERYADDRESS)
            .subtotal(UPDATED_SUBTOTAL)
            .totaltax(UPDATED_TOTALTAX)
            .totaldiscount(UPDATED_TOTALDISCOUNT)
            .nettotal(UPDATED_NETTOTAL)
            .message(UPDATED_MESSAGE)
            .lmu(UPDATED_LMU)
            .lmd(UPDATED_LMD)
            .paidamount(UPDATED_PAIDAMOUNT)
            .amountowing(UPDATED_AMOUNTOWING)
            .isactive(UPDATED_ISACTIVE)
            .locationid(UPDATED_LOCATIONID)
            .locationcode(UPDATED_LOCATIONCODE)
            .referencecode(UPDATED_REFERENCECODE)
            .createdbyid(UPDATED_CREATEDBYID)
            .createdbyname(UPDATED_CREATEDBYNAME)
            .autocarecharges(UPDATED_AUTOCARECHARGES)
            .autocarejobid(UPDATED_AUTOCAREJOBID)
            .vehicleno(UPDATED_VEHICLENO)
            .nextmeter(UPDATED_NEXTMETER)
            .currentmeter(UPDATED_CURRENTMETER)
            .remarks(UPDATED_REMARKS)
            .hasdummybill(UPDATED_HASDUMMYBILL)
            .dummybillid(UPDATED_DUMMYBILLID)
            .dummybillamount(UPDATED_DUMMYBILLAMOUNT)
            .dummycommision(UPDATED_DUMMYCOMMISION)
            .isserviceinvoice(UPDATED_ISSERVICEINVOICE)
            .nbtamount(UPDATED_NBTAMOUNT)
            .vatamount(UPDATED_VATAMOUNT)
            .autocarecompanyid(UPDATED_AUTOCARECOMPANYID)
            .iscompanyinvoice(UPDATED_ISCOMPANYINVOICE)
            .invcanceldate(UPDATED_INVCANCELDATE)
            .invcancelby(UPDATED_INVCANCELBY)
            .isvatinvoice(UPDATED_ISVATINVOICE)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .pendingamount(UPDATED_PENDINGAMOUNT)
            .advancepayment(UPDATED_ADVANCEPAYMENT)
            .discountcode(UPDATED_DISCOUNTCODE);

        restSalesinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesinvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSalesinvoice))
            )
            .andExpect(status().isOk());

        // Validate the Salesinvoice in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSalesinvoiceUpdatableFieldsEquals(partialUpdatedSalesinvoice, getPersistedSalesinvoice(partialUpdatedSalesinvoice));
    }

    @Test
    @Transactional
    void patchNonExistingSalesinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesinvoice.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesinvoice.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(salesinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesinvoiceMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(salesinvoice))
            )
            .andExpect(status().isBadRequest());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesinvoice() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        salesinvoice.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesinvoiceMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(salesinvoice)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Salesinvoice in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesinvoice() throws Exception {
        // Initialize the database
        salesinvoiceRepository.saveAndFlush(salesinvoice);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the salesinvoice
        restSalesinvoiceMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesinvoice.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return salesinvoiceRepository.count();
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

    protected Salesinvoice getPersistedSalesinvoice(Salesinvoice salesinvoice) {
        return salesinvoiceRepository.findById(salesinvoice.getId()).orElseThrow();
    }

    protected void assertPersistedSalesinvoiceToMatchAllProperties(Salesinvoice expectedSalesinvoice) {
        assertSalesinvoiceAllPropertiesEquals(expectedSalesinvoice, getPersistedSalesinvoice(expectedSalesinvoice));
    }

    protected void assertPersistedSalesinvoiceToMatchUpdatableProperties(Salesinvoice expectedSalesinvoice) {
        assertSalesinvoiceAllUpdatablePropertiesEquals(expectedSalesinvoice, getPersistedSalesinvoice(expectedSalesinvoice));
    }
}
