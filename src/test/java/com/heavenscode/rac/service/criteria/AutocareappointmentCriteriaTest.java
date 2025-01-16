package com.heavenscode.rac.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class AutocareappointmentCriteriaTest {

    @Test
    void newAutocareappointmentCriteriaHasAllFiltersNullTest() {
        var autocareappointmentCriteria = new AutocareappointmentCriteria();
        assertThat(autocareappointmentCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void autocareappointmentCriteriaFluentMethodsCreatesFiltersTest() {
        var autocareappointmentCriteria = new AutocareappointmentCriteria();

        setAllFilters(autocareappointmentCriteria);

        assertThat(autocareappointmentCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void autocareappointmentCriteriaCopyCreatesNullFilterTest() {
        var autocareappointmentCriteria = new AutocareappointmentCriteria();
        var copy = autocareappointmentCriteria.copy();

        assertThat(autocareappointmentCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(autocareappointmentCriteria)
        );
    }

    @Test
    void autocareappointmentCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var autocareappointmentCriteria = new AutocareappointmentCriteria();
        setAllFilters(autocareappointmentCriteria);

        var copy = autocareappointmentCriteria.copy();

        assertThat(autocareappointmentCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(autocareappointmentCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var autocareappointmentCriteria = new AutocareappointmentCriteria();

        assertThat(autocareappointmentCriteria).hasToString("AutocareappointmentCriteria{}");
    }

    private static void setAllFilters(AutocareappointmentCriteria autocareappointmentCriteria) {
        autocareappointmentCriteria.id();
        autocareappointmentCriteria.appointmenttype();
        autocareappointmentCriteria.appointmentdate();
        autocareappointmentCriteria.addeddate();
        autocareappointmentCriteria.conformdate();
        autocareappointmentCriteria.appointmentnumber();
        autocareappointmentCriteria.vehiclenumber();
        autocareappointmentCriteria.appointmenttime();
        autocareappointmentCriteria.isconformed();
        autocareappointmentCriteria.conformedby();
        autocareappointmentCriteria.lmd();
        autocareappointmentCriteria.lmu();
        autocareappointmentCriteria.customerid();
        autocareappointmentCriteria.contactnumber();
        autocareappointmentCriteria.customername();
        autocareappointmentCriteria.issued();
        autocareappointmentCriteria.hoistid();
        autocareappointmentCriteria.isarrived();
        autocareappointmentCriteria.iscancel();
        autocareappointmentCriteria.isnoanswer();
        autocareappointmentCriteria.missedappointmentcall();
        autocareappointmentCriteria.customermobileid();
        autocareappointmentCriteria.customermobilevehicleid();
        autocareappointmentCriteria.vehicleid();
        autocareappointmentCriteria.ismobileappointment();
        autocareappointmentCriteria.advancepayment();
        autocareappointmentCriteria.jobid();
        autocareappointmentCriteria.distinct();
    }

    private static Condition<AutocareappointmentCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getAppointmenttype()) &&
                condition.apply(criteria.getAppointmentdate()) &&
                condition.apply(criteria.getAddeddate()) &&
                condition.apply(criteria.getConformdate()) &&
                condition.apply(criteria.getAppointmentnumber()) &&
                condition.apply(criteria.getVehiclenumber()) &&
                condition.apply(criteria.getAppointmenttime()) &&
                condition.apply(criteria.getIsconformed()) &&
                condition.apply(criteria.getConformedby()) &&
                condition.apply(criteria.getLmd()) &&
                condition.apply(criteria.getLmu()) &&
                condition.apply(criteria.getCustomerid()) &&
                condition.apply(criteria.getContactnumber()) &&
                condition.apply(criteria.getCustomername()) &&
                condition.apply(criteria.getIssued()) &&
                condition.apply(criteria.getHoistid()) &&
                condition.apply(criteria.getIsarrived()) &&
                condition.apply(criteria.getIscancel()) &&
                condition.apply(criteria.getIsnoanswer()) &&
                condition.apply(criteria.getMissedappointmentcall()) &&
                condition.apply(criteria.getCustomermobileid()) &&
                condition.apply(criteria.getCustomermobilevehicleid()) &&
                condition.apply(criteria.getVehicleid()) &&
                condition.apply(criteria.getIsmobileappointment()) &&
                condition.apply(criteria.getAdvancepayment()) &&
                condition.apply(criteria.getJobid()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<AutocareappointmentCriteria> copyFiltersAre(
        AutocareappointmentCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getAppointmenttype(), copy.getAppointmenttype()) &&
                condition.apply(criteria.getAppointmentdate(), copy.getAppointmentdate()) &&
                condition.apply(criteria.getAddeddate(), copy.getAddeddate()) &&
                condition.apply(criteria.getConformdate(), copy.getConformdate()) &&
                condition.apply(criteria.getAppointmentnumber(), copy.getAppointmentnumber()) &&
                condition.apply(criteria.getVehiclenumber(), copy.getVehiclenumber()) &&
                condition.apply(criteria.getAppointmenttime(), copy.getAppointmenttime()) &&
                condition.apply(criteria.getIsconformed(), copy.getIsconformed()) &&
                condition.apply(criteria.getConformedby(), copy.getConformedby()) &&
                condition.apply(criteria.getLmd(), copy.getLmd()) &&
                condition.apply(criteria.getLmu(), copy.getLmu()) &&
                condition.apply(criteria.getCustomerid(), copy.getCustomerid()) &&
                condition.apply(criteria.getContactnumber(), copy.getContactnumber()) &&
                condition.apply(criteria.getCustomername(), copy.getCustomername()) &&
                condition.apply(criteria.getIssued(), copy.getIssued()) &&
                condition.apply(criteria.getHoistid(), copy.getHoistid()) &&
                condition.apply(criteria.getIsarrived(), copy.getIsarrived()) &&
                condition.apply(criteria.getIscancel(), copy.getIscancel()) &&
                condition.apply(criteria.getIsnoanswer(), copy.getIsnoanswer()) &&
                condition.apply(criteria.getMissedappointmentcall(), copy.getMissedappointmentcall()) &&
                condition.apply(criteria.getCustomermobileid(), copy.getCustomermobileid()) &&
                condition.apply(criteria.getCustomermobilevehicleid(), copy.getCustomermobilevehicleid()) &&
                condition.apply(criteria.getVehicleid(), copy.getVehicleid()) &&
                condition.apply(criteria.getIsmobileappointment(), copy.getIsmobileappointment()) &&
                condition.apply(criteria.getAdvancepayment(), copy.getAdvancepayment()) &&
                condition.apply(criteria.getJobid(), copy.getJobid()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
