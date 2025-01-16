package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.VehiclecategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VehiclecategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehiclecategory.class);
        Vehiclecategory vehiclecategory1 = getVehiclecategorySample1();
        Vehiclecategory vehiclecategory2 = new Vehiclecategory();
        assertThat(vehiclecategory1).isNotEqualTo(vehiclecategory2);

        vehiclecategory2.setId(vehiclecategory1.getId());
        assertThat(vehiclecategory1).isEqualTo(vehiclecategory2);

        vehiclecategory2 = getVehiclecategorySample2();
        assertThat(vehiclecategory1).isNotEqualTo(vehiclecategory2);
    }
}
