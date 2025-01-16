package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.VehicletypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VehicletypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehicletype.class);
        Vehicletype vehicletype1 = getVehicletypeSample1();
        Vehicletype vehicletype2 = new Vehicletype();
        assertThat(vehicletype1).isNotEqualTo(vehicletype2);

        vehicletype2.setId(vehicletype1.getId());
        assertThat(vehicletype1).isEqualTo(vehicletype2);

        vehicletype2 = getVehicletypeSample2();
        assertThat(vehicletype1).isNotEqualTo(vehicletype2);
    }
}
