package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.VehiclebrandnameTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VehiclebrandnameTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehiclebrandname.class);
        Vehiclebrandname vehiclebrandname1 = getVehiclebrandnameSample1();
        Vehiclebrandname vehiclebrandname2 = new Vehiclebrandname();
        assertThat(vehiclebrandname1).isNotEqualTo(vehiclebrandname2);

        vehiclebrandname2.setId(vehiclebrandname1.getId());
        assertThat(vehiclebrandname1).isEqualTo(vehiclebrandname2);

        vehiclebrandname2 = getVehiclebrandnameSample2();
        assertThat(vehiclebrandname1).isNotEqualTo(vehiclebrandname2);
    }
}
