package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.HoisttypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HoisttypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hoisttype.class);
        Hoisttype hoisttype1 = getHoisttypeSample1();
        Hoisttype hoisttype2 = new Hoisttype();
        assertThat(hoisttype1).isNotEqualTo(hoisttype2);

        hoisttype2.setId(hoisttype1.getId());
        assertThat(hoisttype1).isEqualTo(hoisttype2);

        hoisttype2 = getHoisttypeSample2();
        assertThat(hoisttype1).isNotEqualTo(hoisttype2);
    }
}
