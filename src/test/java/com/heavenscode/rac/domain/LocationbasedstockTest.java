package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.LocationbasedstockTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LocationbasedstockTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Locationbasedstock.class);
        Locationbasedstock locationbasedstock1 = getLocationbasedstockSample1();
        Locationbasedstock locationbasedstock2 = new Locationbasedstock();
        assertThat(locationbasedstock1).isNotEqualTo(locationbasedstock2);

        locationbasedstock2.setId(locationbasedstock1.getId());
        assertThat(locationbasedstock1).isEqualTo(locationbasedstock2);

        locationbasedstock2 = getLocationbasedstockSample2();
        assertThat(locationbasedstock1).isNotEqualTo(locationbasedstock2);
    }
}
