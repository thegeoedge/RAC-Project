package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.BanksTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BanksTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Banks.class);
        Banks banks1 = getBanksSample1();
        Banks banks2 = new Banks();
        assertThat(banks1).isNotEqualTo(banks2);

        banks2.setId(banks1.getId());
        assertThat(banks1).isEqualTo(banks2);

        banks2 = getBanksSample2();
        assertThat(banks1).isNotEqualTo(banks2);
    }
}
