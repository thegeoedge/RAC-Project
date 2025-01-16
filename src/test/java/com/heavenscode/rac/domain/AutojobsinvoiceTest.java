package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutojobsinvoiceTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutojobsinvoiceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autojobsinvoice.class);
        Autojobsinvoice autojobsinvoice1 = getAutojobsinvoiceSample1();
        Autojobsinvoice autojobsinvoice2 = new Autojobsinvoice();
        assertThat(autojobsinvoice1).isNotEqualTo(autojobsinvoice2);

        autojobsinvoice2.setId(autojobsinvoice1.getId());
        assertThat(autojobsinvoice1).isEqualTo(autojobsinvoice2);

        autojobsinvoice2 = getAutojobsinvoiceSample2();
        assertThat(autojobsinvoice1).isNotEqualTo(autojobsinvoice2);
    }
}
