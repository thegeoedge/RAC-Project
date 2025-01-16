package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocareappointmentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocareappointmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocareappointment.class);
        Autocareappointment autocareappointment1 = getAutocareappointmentSample1();
        Autocareappointment autocareappointment2 = new Autocareappointment();
        assertThat(autocareappointment1).isNotEqualTo(autocareappointment2);

        autocareappointment2.setId(autocareappointment1.getId());
        assertThat(autocareappointment1).isEqualTo(autocareappointment2);

        autocareappointment2 = getAutocareappointmentSample2();
        assertThat(autocareappointment1).isNotEqualTo(autocareappointment2);
    }
}
