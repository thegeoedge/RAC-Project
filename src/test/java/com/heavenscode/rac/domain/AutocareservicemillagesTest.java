package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocareservicemillagesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocareservicemillagesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocareservicemillages.class);
        Autocareservicemillages autocareservicemillages1 = getAutocareservicemillagesSample1();
        Autocareservicemillages autocareservicemillages2 = new Autocareservicemillages();
        assertThat(autocareservicemillages1).isNotEqualTo(autocareservicemillages2);

        autocareservicemillages2.setId(autocareservicemillages1.getId());
        assertThat(autocareservicemillages1).isEqualTo(autocareservicemillages2);

        autocareservicemillages2 = getAutocareservicemillagesSample2();
        assertThat(autocareservicemillages1).isNotEqualTo(autocareservicemillages2);
    }
}
