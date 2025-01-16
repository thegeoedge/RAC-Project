package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.TaxesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TaxesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Taxes.class);
        Taxes taxes1 = getTaxesSample1();
        Taxes taxes2 = new Taxes();
        assertThat(taxes1).isNotEqualTo(taxes2);

        taxes2.setId(taxes1.getId());
        assertThat(taxes1).isEqualTo(taxes2);

        taxes2 = getTaxesSample2();
        assertThat(taxes1).isNotEqualTo(taxes2);
    }
}
