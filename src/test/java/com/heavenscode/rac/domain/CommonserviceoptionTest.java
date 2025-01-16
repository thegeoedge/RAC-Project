package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.CommonserviceoptionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommonserviceoptionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Commonserviceoption.class);
        Commonserviceoption commonserviceoption1 = getCommonserviceoptionSample1();
        Commonserviceoption commonserviceoption2 = new Commonserviceoption();
        assertThat(commonserviceoption1).isNotEqualTo(commonserviceoption2);

        commonserviceoption2.setId(commonserviceoption1.getId());
        assertThat(commonserviceoption1).isEqualTo(commonserviceoption2);

        commonserviceoption2 = getCommonserviceoptionSample2();
        assertThat(commonserviceoption1).isNotEqualTo(commonserviceoption2);
    }
}
