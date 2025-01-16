package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.SalesinvoiceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesinvoiceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Salesinvoice.class);
        Salesinvoice salesinvoice1 = getSalesinvoiceSample1();
        Salesinvoice salesinvoice2 = new Salesinvoice();
        assertThat(salesinvoice1).isNotEqualTo(salesinvoice2);

        salesinvoice2.setId(salesinvoice1.getId());
        assertThat(salesinvoice1).isEqualTo(salesinvoice2);

        salesinvoice2 = getSalesinvoiceSample2();
        assertThat(salesinvoice1).isNotEqualTo(salesinvoice2);
    }
}
