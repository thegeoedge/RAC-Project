package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.PaymenttermTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymenttermTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Paymentterm.class);
        Paymentterm paymentterm1 = getPaymenttermSample1();
        Paymentterm paymentterm2 = new Paymentterm();
        assertThat(paymentterm1).isNotEqualTo(paymentterm2);

        paymentterm2.setId(paymentterm1.getId());
        assertThat(paymentterm1).isEqualTo(paymentterm2);

        paymentterm2 = getPaymenttermSample2();
        assertThat(paymentterm1).isNotEqualTo(paymentterm2);
    }
}
