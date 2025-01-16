package com.heavenscode.rac.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class VehiclebrandnameAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVehiclebrandnameAllPropertiesEquals(Vehiclebrandname expected, Vehiclebrandname actual) {
        assertVehiclebrandnameAutoGeneratedPropertiesEquals(expected, actual);
        assertVehiclebrandnameAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVehiclebrandnameAllUpdatablePropertiesEquals(Vehiclebrandname expected, Vehiclebrandname actual) {
        assertVehiclebrandnameUpdatableFieldsEquals(expected, actual);
        assertVehiclebrandnameUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVehiclebrandnameAutoGeneratedPropertiesEquals(Vehiclebrandname expected, Vehiclebrandname actual) {
        assertThat(expected)
            .as("Verify Vehiclebrandname auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVehiclebrandnameUpdatableFieldsEquals(Vehiclebrandname expected, Vehiclebrandname actual) {
        assertThat(expected)
            .as("Verify Vehiclebrandname relevant properties")
            .satisfies(e -> assertThat(e.getBrandname()).as("check brandname").isEqualTo(actual.getBrandname()))
            .satisfies(e -> assertThat(e.getDescription()).as("check description").isEqualTo(actual.getDescription()))
            .satisfies(e -> assertThat(e.getLmu()).as("check lmu").isEqualTo(actual.getLmu()))
            .satisfies(e -> assertThat(e.getLmd()).as("check lmd").isEqualTo(actual.getLmd()))
            .satisfies(e -> assertThat(e.getCompanyid()).as("check companyid").isEqualTo(actual.getCompanyid()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertVehiclebrandnameUpdatableRelationshipsEquals(Vehiclebrandname expected, Vehiclebrandname actual) {}
}
