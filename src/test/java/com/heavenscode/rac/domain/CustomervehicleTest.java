package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.CustomervehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CustomervehicleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Customervehicle.class);
        Customervehicle customervehicle1 = getCustomervehicleSample1();
        Customervehicle customervehicle2 = new Customervehicle();
        assertThat(customervehicle1).isNotEqualTo(customervehicle2);

        customervehicle2.setId(customervehicle1.getId());
        assertThat(customervehicle1).isEqualTo(customervehicle2);

        customervehicle2 = getCustomervehicleSample2();
        assertThat(customervehicle1).isNotEqualTo(customervehicle2);
    }
}
