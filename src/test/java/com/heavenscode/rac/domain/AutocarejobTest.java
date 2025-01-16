package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocarejobTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocarejobTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocarejob.class);
        Autocarejob autocarejob1 = getAutocarejobSample1();
        Autocarejob autocarejob2 = new Autocarejob();
        assertThat(autocarejob1).isNotEqualTo(autocarejob2);

        autocarejob2.setId(autocarejob1.getId());
        assertThat(autocarejob1).isEqualTo(autocarejob2);

        autocarejob2 = getAutocarejobSample2();
        assertThat(autocarejob1).isNotEqualTo(autocarejob2);
    }
}
