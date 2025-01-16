package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.BillingserviceoptionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class BillingserviceoptionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Billingserviceoption.class);
        Billingserviceoption billingserviceoption1 = getBillingserviceoptionSample1();
        Billingserviceoption billingserviceoption2 = new Billingserviceoption();
        assertThat(billingserviceoption1).isNotEqualTo(billingserviceoption2);

        billingserviceoption2.setId(billingserviceoption1.getId());
        assertThat(billingserviceoption1).isEqualTo(billingserviceoption2);

        billingserviceoption2 = getBillingserviceoptionSample2();
        assertThat(billingserviceoption1).isNotEqualTo(billingserviceoption2);
    }
}
