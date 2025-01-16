package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.EmpJobcommissionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmpJobcommissionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpJobcommission.class);
        EmpJobcommission empJobcommission1 = getEmpJobcommissionSample1();
        EmpJobcommission empJobcommission2 = new EmpJobcommission();
        assertThat(empJobcommission1).isNotEqualTo(empJobcommission2);

        empJobcommission2.setId(empJobcommission1.getId());
        assertThat(empJobcommission1).isEqualTo(empJobcommission2);

        empJobcommission2 = getEmpJobcommissionSample2();
        assertThat(empJobcommission1).isNotEqualTo(empJobcommission2);
    }
}
