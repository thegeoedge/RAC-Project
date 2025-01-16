package com.heavenscode.rac.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CustomerCriteriaTest {

    @Test
    void newCustomerCriteriaHasAllFiltersNullTest() {
        var customerCriteria = new CustomerCriteria();
        assertThat(customerCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void customerCriteriaFluentMethodsCreatesFiltersTest() {
        var customerCriteria = new CustomerCriteria();

        setAllFilters(customerCriteria);

        assertThat(customerCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void customerCriteriaCopyCreatesNullFilterTest() {
        var customerCriteria = new CustomerCriteria();
        var copy = customerCriteria.copy();

        assertThat(customerCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(customerCriteria)
        );
    }

    @Test
    void customerCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var customerCriteria = new CustomerCriteria();
        setAllFilters(customerCriteria);

        var copy = customerCriteria.copy();

        assertThat(customerCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(customerCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var customerCriteria = new CustomerCriteria();

        assertThat(customerCriteria).hasToString("CustomerCriteria{}");
    }

    private static void setAllFilters(CustomerCriteria customerCriteria) {
        customerCriteria.id();
        customerCriteria.customertype();
        customerCriteria.code();
        customerCriteria.title();
        customerCriteria.namewithinitials();
        customerCriteria.fullname();
        customerCriteria.callingname();
        customerCriteria.nicno();
        customerCriteria.nicissueddate();
        customerCriteria.dateofbirth();
        customerCriteria.bloodgroup();
        customerCriteria.gender();
        customerCriteria.maritalstatus();
        customerCriteria.marrieddate();
        customerCriteria.nationality();
        customerCriteria.territory();
        customerCriteria.religion();
        customerCriteria.team();
        customerCriteria.businessname();
        customerCriteria.businessregdate();
        customerCriteria.businessregno();
        customerCriteria.profilepicturepath();
        customerCriteria.residencehouseno();
        customerCriteria.residenceaddress();
        customerCriteria.residencecity();
        customerCriteria.residencephone();
        customerCriteria.businesslocationno();
        customerCriteria.businessaddress();
        customerCriteria.businesscity();
        customerCriteria.businessphone1();
        customerCriteria.businessphone2();
        customerCriteria.businessmobile();
        customerCriteria.businessfax();
        customerCriteria.businessemail();
        customerCriteria.businessprovinceid();
        customerCriteria.businessdistrictid();
        customerCriteria.contactpersonname();
        customerCriteria.contactpersonphone();
        customerCriteria.contactpersonmobile();
        customerCriteria.contactpersonemail();
        customerCriteria.rootmappath();
        customerCriteria.website();
        customerCriteria.registrationdate();
        customerCriteria.isactive();
        customerCriteria.isinternalcustomer();
        customerCriteria.description();
        customerCriteria.lmu();
        customerCriteria.lmd();
        customerCriteria.maximumdiscount();
        customerCriteria.creditlimit();
        customerCriteria.hassecuritydeposit();
        customerCriteria.securitydepositamount();
        customerCriteria.paybycash();
        customerCriteria.cashpaymentbeforeload();
        customerCriteria.cashlastinvoicebeforeload();
        customerCriteria.paybycredit();
        customerCriteria.creditoneweekcheck();
        customerCriteria.creditpaymentbydays();
        customerCriteria.haspurchasingdeposit();
        customerCriteria.hassecuritydepositbond();
        customerCriteria.hasassestsdeposit();
        customerCriteria.customerrootmappath();
        customerCriteria.employername();
        customerCriteria.employeraddress();
        customerCriteria.employerphone();
        customerCriteria.employerdesignation();
        customerCriteria.previousemployername();
        customerCriteria.previousemployeraddress();
        customerCriteria.previousindustry();
        customerCriteria.previousperiod();
        customerCriteria.previouspositions();
        customerCriteria.previousresionforleaving();
        customerCriteria.hascreaditlimit();
        customerCriteria.accountid();
        customerCriteria.accountcode();
        customerCriteria.isregistered();
        customerCriteria.vatregnumber();
        customerCriteria.tinnumber();
        customerCriteria.lat();
        customerCriteria.lon();
        customerCriteria.creditperiod();
        customerCriteria.distinct();
    }

    private static Condition<CustomerCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getCustomertype()) &&
                condition.apply(criteria.getCode()) &&
                condition.apply(criteria.getTitle()) &&
                condition.apply(criteria.getNamewithinitials()) &&
                condition.apply(criteria.getFullname()) &&
                condition.apply(criteria.getCallingname()) &&
                condition.apply(criteria.getNicno()) &&
                condition.apply(criteria.getNicissueddate()) &&
                condition.apply(criteria.getDateofbirth()) &&
                condition.apply(criteria.getBloodgroup()) &&
                condition.apply(criteria.getGender()) &&
                condition.apply(criteria.getMaritalstatus()) &&
                condition.apply(criteria.getMarrieddate()) &&
                condition.apply(criteria.getNationality()) &&
                condition.apply(criteria.getTerritory()) &&
                condition.apply(criteria.getReligion()) &&
                condition.apply(criteria.getTeam()) &&
                condition.apply(criteria.getBusinessname()) &&
                condition.apply(criteria.getBusinessregdate()) &&
                condition.apply(criteria.getBusinessregno()) &&
                condition.apply(criteria.getProfilepicturepath()) &&
                condition.apply(criteria.getResidencehouseno()) &&
                condition.apply(criteria.getResidenceaddress()) &&
                condition.apply(criteria.getResidencecity()) &&
                condition.apply(criteria.getResidencephone()) &&
                condition.apply(criteria.getBusinesslocationno()) &&
                condition.apply(criteria.getBusinessaddress()) &&
                condition.apply(criteria.getBusinesscity()) &&
                condition.apply(criteria.getBusinessphone1()) &&
                condition.apply(criteria.getBusinessphone2()) &&
                condition.apply(criteria.getBusinessmobile()) &&
                condition.apply(criteria.getBusinessfax()) &&
                condition.apply(criteria.getBusinessemail()) &&
                condition.apply(criteria.getBusinessprovinceid()) &&
                condition.apply(criteria.getBusinessdistrictid()) &&
                condition.apply(criteria.getContactpersonname()) &&
                condition.apply(criteria.getContactpersonphone()) &&
                condition.apply(criteria.getContactpersonmobile()) &&
                condition.apply(criteria.getContactpersonemail()) &&
                condition.apply(criteria.getRootmappath()) &&
                condition.apply(criteria.getWebsite()) &&
                condition.apply(criteria.getRegistrationdate()) &&
                condition.apply(criteria.getIsactive()) &&
                condition.apply(criteria.getIsinternalcustomer()) &&
                condition.apply(criteria.getDescription()) &&
                condition.apply(criteria.getLmu()) &&
                condition.apply(criteria.getLmd()) &&
                condition.apply(criteria.getMaximumdiscount()) &&
                condition.apply(criteria.getCreditlimit()) &&
                condition.apply(criteria.getHassecuritydeposit()) &&
                condition.apply(criteria.getSecuritydepositamount()) &&
                condition.apply(criteria.getPaybycash()) &&
                condition.apply(criteria.getCashpaymentbeforeload()) &&
                condition.apply(criteria.getCashlastinvoicebeforeload()) &&
                condition.apply(criteria.getPaybycredit()) &&
                condition.apply(criteria.getCreditoneweekcheck()) &&
                condition.apply(criteria.getCreditpaymentbydays()) &&
                condition.apply(criteria.getHaspurchasingdeposit()) &&
                condition.apply(criteria.getHassecuritydepositbond()) &&
                condition.apply(criteria.getHasassestsdeposit()) &&
                condition.apply(criteria.getCustomerrootmappath()) &&
                condition.apply(criteria.getEmployername()) &&
                condition.apply(criteria.getEmployeraddress()) &&
                condition.apply(criteria.getEmployerphone()) &&
                condition.apply(criteria.getEmployerdesignation()) &&
                condition.apply(criteria.getPreviousemployername()) &&
                condition.apply(criteria.getPreviousemployeraddress()) &&
                condition.apply(criteria.getPreviousindustry()) &&
                condition.apply(criteria.getPreviousperiod()) &&
                condition.apply(criteria.getPreviouspositions()) &&
                condition.apply(criteria.getPreviousresionforleaving()) &&
                condition.apply(criteria.getHascreaditlimit()) &&
                condition.apply(criteria.getAccountid()) &&
                condition.apply(criteria.getAccountcode()) &&
                condition.apply(criteria.getIsregistered()) &&
                condition.apply(criteria.getVatregnumber()) &&
                condition.apply(criteria.getTinnumber()) &&
                condition.apply(criteria.getLat()) &&
                condition.apply(criteria.getLon()) &&
                condition.apply(criteria.getCreditperiod()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CustomerCriteria> copyFiltersAre(CustomerCriteria copy, BiFunction<Object, Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getCustomertype(), copy.getCustomertype()) &&
                condition.apply(criteria.getCode(), copy.getCode()) &&
                condition.apply(criteria.getTitle(), copy.getTitle()) &&
                condition.apply(criteria.getNamewithinitials(), copy.getNamewithinitials()) &&
                condition.apply(criteria.getFullname(), copy.getFullname()) &&
                condition.apply(criteria.getCallingname(), copy.getCallingname()) &&
                condition.apply(criteria.getNicno(), copy.getNicno()) &&
                condition.apply(criteria.getNicissueddate(), copy.getNicissueddate()) &&
                condition.apply(criteria.getDateofbirth(), copy.getDateofbirth()) &&
                condition.apply(criteria.getBloodgroup(), copy.getBloodgroup()) &&
                condition.apply(criteria.getGender(), copy.getGender()) &&
                condition.apply(criteria.getMaritalstatus(), copy.getMaritalstatus()) &&
                condition.apply(criteria.getMarrieddate(), copy.getMarrieddate()) &&
                condition.apply(criteria.getNationality(), copy.getNationality()) &&
                condition.apply(criteria.getTerritory(), copy.getTerritory()) &&
                condition.apply(criteria.getReligion(), copy.getReligion()) &&
                condition.apply(criteria.getTeam(), copy.getTeam()) &&
                condition.apply(criteria.getBusinessname(), copy.getBusinessname()) &&
                condition.apply(criteria.getBusinessregdate(), copy.getBusinessregdate()) &&
                condition.apply(criteria.getBusinessregno(), copy.getBusinessregno()) &&
                condition.apply(criteria.getProfilepicturepath(), copy.getProfilepicturepath()) &&
                condition.apply(criteria.getResidencehouseno(), copy.getResidencehouseno()) &&
                condition.apply(criteria.getResidenceaddress(), copy.getResidenceaddress()) &&
                condition.apply(criteria.getResidencecity(), copy.getResidencecity()) &&
                condition.apply(criteria.getResidencephone(), copy.getResidencephone()) &&
                condition.apply(criteria.getBusinesslocationno(), copy.getBusinesslocationno()) &&
                condition.apply(criteria.getBusinessaddress(), copy.getBusinessaddress()) &&
                condition.apply(criteria.getBusinesscity(), copy.getBusinesscity()) &&
                condition.apply(criteria.getBusinessphone1(), copy.getBusinessphone1()) &&
                condition.apply(criteria.getBusinessphone2(), copy.getBusinessphone2()) &&
                condition.apply(criteria.getBusinessmobile(), copy.getBusinessmobile()) &&
                condition.apply(criteria.getBusinessfax(), copy.getBusinessfax()) &&
                condition.apply(criteria.getBusinessemail(), copy.getBusinessemail()) &&
                condition.apply(criteria.getBusinessprovinceid(), copy.getBusinessprovinceid()) &&
                condition.apply(criteria.getBusinessdistrictid(), copy.getBusinessdistrictid()) &&
                condition.apply(criteria.getContactpersonname(), copy.getContactpersonname()) &&
                condition.apply(criteria.getContactpersonphone(), copy.getContactpersonphone()) &&
                condition.apply(criteria.getContactpersonmobile(), copy.getContactpersonmobile()) &&
                condition.apply(criteria.getContactpersonemail(), copy.getContactpersonemail()) &&
                condition.apply(criteria.getRootmappath(), copy.getRootmappath()) &&
                condition.apply(criteria.getWebsite(), copy.getWebsite()) &&
                condition.apply(criteria.getRegistrationdate(), copy.getRegistrationdate()) &&
                condition.apply(criteria.getIsactive(), copy.getIsactive()) &&
                condition.apply(criteria.getIsinternalcustomer(), copy.getIsinternalcustomer()) &&
                condition.apply(criteria.getDescription(), copy.getDescription()) &&
                condition.apply(criteria.getLmu(), copy.getLmu()) &&
                condition.apply(criteria.getLmd(), copy.getLmd()) &&
                condition.apply(criteria.getMaximumdiscount(), copy.getMaximumdiscount()) &&
                condition.apply(criteria.getCreditlimit(), copy.getCreditlimit()) &&
                condition.apply(criteria.getHassecuritydeposit(), copy.getHassecuritydeposit()) &&
                condition.apply(criteria.getSecuritydepositamount(), copy.getSecuritydepositamount()) &&
                condition.apply(criteria.getPaybycash(), copy.getPaybycash()) &&
                condition.apply(criteria.getCashpaymentbeforeload(), copy.getCashpaymentbeforeload()) &&
                condition.apply(criteria.getCashlastinvoicebeforeload(), copy.getCashlastinvoicebeforeload()) &&
                condition.apply(criteria.getPaybycredit(), copy.getPaybycredit()) &&
                condition.apply(criteria.getCreditoneweekcheck(), copy.getCreditoneweekcheck()) &&
                condition.apply(criteria.getCreditpaymentbydays(), copy.getCreditpaymentbydays()) &&
                condition.apply(criteria.getHaspurchasingdeposit(), copy.getHaspurchasingdeposit()) &&
                condition.apply(criteria.getHassecuritydepositbond(), copy.getHassecuritydepositbond()) &&
                condition.apply(criteria.getHasassestsdeposit(), copy.getHasassestsdeposit()) &&
                condition.apply(criteria.getCustomerrootmappath(), copy.getCustomerrootmappath()) &&
                condition.apply(criteria.getEmployername(), copy.getEmployername()) &&
                condition.apply(criteria.getEmployeraddress(), copy.getEmployeraddress()) &&
                condition.apply(criteria.getEmployerphone(), copy.getEmployerphone()) &&
                condition.apply(criteria.getEmployerdesignation(), copy.getEmployerdesignation()) &&
                condition.apply(criteria.getPreviousemployername(), copy.getPreviousemployername()) &&
                condition.apply(criteria.getPreviousemployeraddress(), copy.getPreviousemployeraddress()) &&
                condition.apply(criteria.getPreviousindustry(), copy.getPreviousindustry()) &&
                condition.apply(criteria.getPreviousperiod(), copy.getPreviousperiod()) &&
                condition.apply(criteria.getPreviouspositions(), copy.getPreviouspositions()) &&
                condition.apply(criteria.getPreviousresionforleaving(), copy.getPreviousresionforleaving()) &&
                condition.apply(criteria.getHascreaditlimit(), copy.getHascreaditlimit()) &&
                condition.apply(criteria.getAccountid(), copy.getAccountid()) &&
                condition.apply(criteria.getAccountcode(), copy.getAccountcode()) &&
                condition.apply(criteria.getIsregistered(), copy.getIsregistered()) &&
                condition.apply(criteria.getVatregnumber(), copy.getVatregnumber()) &&
                condition.apply(criteria.getTinnumber(), copy.getTinnumber()) &&
                condition.apply(criteria.getLat(), copy.getLat()) &&
                condition.apply(criteria.getLon(), copy.getLon()) &&
                condition.apply(criteria.getCreditperiod(), copy.getCreditperiod()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
