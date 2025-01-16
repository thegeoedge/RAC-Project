package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.EmpSectiontblTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmpSectiontblTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EmpSectiontbl.class);
        EmpSectiontbl empSectiontbl1 = getEmpSectiontblSample1();
        EmpSectiontbl empSectiontbl2 = new EmpSectiontbl();
        assertThat(empSectiontbl1).isNotEqualTo(empSectiontbl2);

        empSectiontbl2.setId(empSectiontbl1.getId());
        assertThat(empSectiontbl1).isEqualTo(empSectiontbl2);

        empSectiontbl2 = getEmpSectiontblSample2();
        assertThat(empSectiontbl1).isNotEqualTo(empSectiontbl2);
    }
}
