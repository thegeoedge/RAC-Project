package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocarehoistTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocarehoistTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocarehoist.class);
        Autocarehoist autocarehoist1 = getAutocarehoistSample1();
        Autocarehoist autocarehoist2 = new Autocarehoist();
        assertThat(autocarehoist1).isNotEqualTo(autocarehoist2);

        autocarehoist2.setId(autocarehoist1.getId());
        assertThat(autocarehoist1).isEqualTo(autocarehoist2);

        autocarehoist2 = getAutocarehoistSample2();
        assertThat(autocarehoist1).isNotEqualTo(autocarehoist2);
    }
}
