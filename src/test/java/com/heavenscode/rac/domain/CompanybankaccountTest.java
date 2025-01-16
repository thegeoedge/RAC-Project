package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.CompanybankaccountTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CompanybankaccountTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Companybankaccount.class);
        Companybankaccount companybankaccount1 = getCompanybankaccountSample1();
        Companybankaccount companybankaccount2 = new Companybankaccount();
        assertThat(companybankaccount1).isNotEqualTo(companybankaccount2);

        companybankaccount2.setId(companybankaccount1.getId());
        assertThat(companybankaccount1).isEqualTo(companybankaccount2);

        companybankaccount2 = getCompanybankaccountSample2();
        assertThat(companybankaccount1).isNotEqualTo(companybankaccount2);
    }
}
