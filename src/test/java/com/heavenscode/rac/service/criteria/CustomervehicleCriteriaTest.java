package com.heavenscode.rac.service.criteria;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

class CustomervehicleCriteriaTest {

    @Test
    void newCustomervehicleCriteriaHasAllFiltersNullTest() {
        var customervehicleCriteria = new CustomervehicleCriteria();
        assertThat(customervehicleCriteria).is(criteriaFiltersAre(filter -> filter == null));
    }

    @Test
    void customervehicleCriteriaFluentMethodsCreatesFiltersTest() {
        var customervehicleCriteria = new CustomervehicleCriteria();

        setAllFilters(customervehicleCriteria);

        assertThat(customervehicleCriteria).is(criteriaFiltersAre(filter -> filter != null));
    }

    @Test
    void customervehicleCriteriaCopyCreatesNullFilterTest() {
        var customervehicleCriteria = new CustomervehicleCriteria();
        var copy = customervehicleCriteria.copy();

        assertThat(customervehicleCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter == null)),
            criteria -> assertThat(criteria).isEqualTo(customervehicleCriteria)
        );
    }

    @Test
    void customervehicleCriteriaCopyDuplicatesEveryExistingFilterTest() {
        var customervehicleCriteria = new CustomervehicleCriteria();
        setAllFilters(customervehicleCriteria);

        var copy = customervehicleCriteria.copy();

        assertThat(customervehicleCriteria).satisfies(
            criteria ->
                assertThat(criteria).is(
                    copyFiltersAre(copy, (a, b) -> (a == null || a instanceof Boolean) ? a == b : (a != b && a.equals(b)))
                ),
            criteria -> assertThat(criteria).isEqualTo(copy),
            criteria -> assertThat(criteria).hasSameHashCodeAs(copy)
        );

        assertThat(copy).satisfies(
            criteria -> assertThat(criteria).is(criteriaFiltersAre(filter -> filter != null)),
            criteria -> assertThat(criteria).isEqualTo(customervehicleCriteria)
        );
    }

    @Test
    void toStringVerifier() {
        var customervehicleCriteria = new CustomervehicleCriteria();

        assertThat(customervehicleCriteria).hasToString("CustomervehicleCriteria{}");
    }

    private static void setAllFilters(CustomervehicleCriteria customervehicleCriteria) {
        customervehicleCriteria.id();
        customervehicleCriteria.customerid();
        customervehicleCriteria.vehiclenumber();
        customervehicleCriteria.categoryid();
        customervehicleCriteria.categoryname();
        customervehicleCriteria.typeid();
        customervehicleCriteria.typename();
        customervehicleCriteria.makeid();
        customervehicleCriteria.makename();
        customervehicleCriteria.model();
        customervehicleCriteria.yom();
        customervehicleCriteria.customercode();
        customervehicleCriteria.remarks();
        customervehicleCriteria.servicecount();
        customervehicleCriteria.engNo();
        customervehicleCriteria.chaNo();
        customervehicleCriteria.milage();
        customervehicleCriteria.lastservicedate();
        customervehicleCriteria.nextservicedate();
        customervehicleCriteria.lmu();
        customervehicleCriteria.lmd();
        customervehicleCriteria.nextgearoilmilage();
        customervehicleCriteria.nextmilage();
        customervehicleCriteria.serviceperiod();
        customervehicleCriteria.distinct();
    }

    private static Condition<CustomervehicleCriteria> criteriaFiltersAre(Function<Object, Boolean> condition) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId()) &&
                condition.apply(criteria.getCustomerid()) &&
                condition.apply(criteria.getVehiclenumber()) &&
                condition.apply(criteria.getCategoryid()) &&
                condition.apply(criteria.getCategoryname()) &&
                condition.apply(criteria.getTypeid()) &&
                condition.apply(criteria.getTypename()) &&
                condition.apply(criteria.getMakeid()) &&
                condition.apply(criteria.getMakename()) &&
                condition.apply(criteria.getModel()) &&
                condition.apply(criteria.getYom()) &&
                condition.apply(criteria.getCustomercode()) &&
                condition.apply(criteria.getRemarks()) &&
                condition.apply(criteria.getServicecount()) &&
                condition.apply(criteria.getEngNo()) &&
                condition.apply(criteria.getChaNo()) &&
                condition.apply(criteria.getMilage()) &&
                condition.apply(criteria.getLastservicedate()) &&
                condition.apply(criteria.getNextservicedate()) &&
                condition.apply(criteria.getLmu()) &&
                condition.apply(criteria.getLmd()) &&
                condition.apply(criteria.getNextgearoilmilage()) &&
                condition.apply(criteria.getNextmilage()) &&
                condition.apply(criteria.getServiceperiod()) &&
                condition.apply(criteria.getDistinct()),
            "every filter matches"
        );
    }

    private static Condition<CustomervehicleCriteria> copyFiltersAre(
        CustomervehicleCriteria copy,
        BiFunction<Object, Object, Boolean> condition
    ) {
        return new Condition<>(
            criteria ->
                condition.apply(criteria.getId(), copy.getId()) &&
                condition.apply(criteria.getCustomerid(), copy.getCustomerid()) &&
                condition.apply(criteria.getVehiclenumber(), copy.getVehiclenumber()) &&
                condition.apply(criteria.getCategoryid(), copy.getCategoryid()) &&
                condition.apply(criteria.getCategoryname(), copy.getCategoryname()) &&
                condition.apply(criteria.getTypeid(), copy.getTypeid()) &&
                condition.apply(criteria.getTypename(), copy.getTypename()) &&
                condition.apply(criteria.getMakeid(), copy.getMakeid()) &&
                condition.apply(criteria.getMakename(), copy.getMakename()) &&
                condition.apply(criteria.getModel(), copy.getModel()) &&
                condition.apply(criteria.getYom(), copy.getYom()) &&
                condition.apply(criteria.getCustomercode(), copy.getCustomercode()) &&
                condition.apply(criteria.getRemarks(), copy.getRemarks()) &&
                condition.apply(criteria.getServicecount(), copy.getServicecount()) &&
                condition.apply(criteria.getEngNo(), copy.getEngNo()) &&
                condition.apply(criteria.getChaNo(), copy.getChaNo()) &&
                condition.apply(criteria.getMilage(), copy.getMilage()) &&
                condition.apply(criteria.getLastservicedate(), copy.getLastservicedate()) &&
                condition.apply(criteria.getNextservicedate(), copy.getNextservicedate()) &&
                condition.apply(criteria.getLmu(), copy.getLmu()) &&
                condition.apply(criteria.getLmd(), copy.getLmd()) &&
                condition.apply(criteria.getNextgearoilmilage(), copy.getNextgearoilmilage()) &&
                condition.apply(criteria.getNextmilage(), copy.getNextmilage()) &&
                condition.apply(criteria.getServiceperiod(), copy.getServiceperiod()) &&
                condition.apply(criteria.getDistinct(), copy.getDistinct()),
            "every filter matches"
        );
    }
}
