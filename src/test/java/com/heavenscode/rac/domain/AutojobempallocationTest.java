package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutojobempallocationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutojobempallocationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autojobempallocation.class);
        Autojobempallocation autojobempallocation1 = getAutojobempallocationSample1();
        Autojobempallocation autojobempallocation2 = new Autojobempallocation();
        assertThat(autojobempallocation1).isNotEqualTo(autojobempallocation2);

        autojobempallocation2.setId(autojobempallocation1.getId());
        assertThat(autojobempallocation1).isEqualTo(autojobempallocation2);

        autojobempallocation2 = getAutojobempallocationSample2();
        assertThat(autojobempallocation1).isNotEqualTo(autojobempallocation2);
    }
}
