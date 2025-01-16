package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.SalesorderTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesorderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Salesorder.class);
        Salesorder salesorder1 = getSalesorderSample1();
        Salesorder salesorder2 = new Salesorder();
        assertThat(salesorder1).isNotEqualTo(salesorder2);

        salesorder2.setId(salesorder1.getId());
        assertThat(salesorder1).isEqualTo(salesorder2);

        salesorder2 = getSalesorderSample2();
        assertThat(salesorder1).isNotEqualTo(salesorder2);
    }
}
