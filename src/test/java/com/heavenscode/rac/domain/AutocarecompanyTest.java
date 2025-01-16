package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocarecompanyTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocarecompanyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocarecompany.class);
        Autocarecompany autocarecompany1 = getAutocarecompanySample1();
        Autocarecompany autocarecompany2 = new Autocarecompany();
        assertThat(autocarecompany1).isNotEqualTo(autocarecompany2);

        autocarecompany2.setId(autocarecompany1.getId());
        assertThat(autocarecompany1).isEqualTo(autocarecompany2);

        autocarecompany2 = getAutocarecompanySample2();
        assertThat(autocarecompany1).isNotEqualTo(autocarecompany2);
    }
}
