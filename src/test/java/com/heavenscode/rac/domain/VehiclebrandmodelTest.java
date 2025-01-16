package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.VehiclebrandmodelTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class VehiclebrandmodelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Vehiclebrandmodel.class);
        Vehiclebrandmodel vehiclebrandmodel1 = getVehiclebrandmodelSample1();
        Vehiclebrandmodel vehiclebrandmodel2 = new Vehiclebrandmodel();
        assertThat(vehiclebrandmodel1).isNotEqualTo(vehiclebrandmodel2);

        vehiclebrandmodel2.setId(vehiclebrandmodel1.getId());
        assertThat(vehiclebrandmodel1).isEqualTo(vehiclebrandmodel2);

        vehiclebrandmodel2 = getVehiclebrandmodelSample2();
        assertThat(vehiclebrandmodel1).isNotEqualTo(vehiclebrandmodel2);
    }
}
