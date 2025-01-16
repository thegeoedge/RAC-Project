package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.BankbranchTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BankbranchTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bankbranch.class);
        Bankbranch bankbranch1 = getBankbranchSample1();
        Bankbranch bankbranch2 = new Bankbranch();
        assertThat(bankbranch1).isNotEqualTo(bankbranch2);

        bankbranch2.setId(bankbranch1.getId());
        assertThat(bankbranch1).isEqualTo(bankbranch2);

        bankbranch2 = getBankbranchSample2();
        assertThat(bankbranch1).isNotEqualTo(bankbranch2);
    }
}
