package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocareappointmenttypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocareappointmenttypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocareappointmenttype.class);
        Autocareappointmenttype autocareappointmenttype1 = getAutocareappointmenttypeSample1();
        Autocareappointmenttype autocareappointmenttype2 = new Autocareappointmenttype();
        assertThat(autocareappointmenttype1).isNotEqualTo(autocareappointmenttype2);

        autocareappointmenttype2.setId(autocareappointmenttype1.getId());
        assertThat(autocareappointmenttype1).isEqualTo(autocareappointmenttype2);

        autocareappointmenttype2 = getAutocareappointmenttypeSample2();
        assertThat(autocareappointmenttype1).isNotEqualTo(autocareappointmenttype2);
    }
}
