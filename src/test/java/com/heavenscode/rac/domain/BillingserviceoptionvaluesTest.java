package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.BillingserviceoptionvaluesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BillingserviceoptionvaluesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Billingserviceoptionvalues.class);
        Billingserviceoptionvalues billingserviceoptionvalues1 = getBillingserviceoptionvaluesSample1();
        Billingserviceoptionvalues billingserviceoptionvalues2 = new Billingserviceoptionvalues();
        assertThat(billingserviceoptionvalues1).isNotEqualTo(billingserviceoptionvalues2);

        billingserviceoptionvalues2.setId(billingserviceoptionvalues1.getId());
        assertThat(billingserviceoptionvalues1).isEqualTo(billingserviceoptionvalues2);

        billingserviceoptionvalues2 = getBillingserviceoptionvaluesSample2();
        assertThat(billingserviceoptionvalues1).isNotEqualTo(billingserviceoptionvalues2);
    }
}
