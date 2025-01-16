package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.WorkshopvehicleworkTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkshopvehicleworkTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Workshopvehiclework.class);
        Workshopvehiclework workshopvehiclework1 = getWorkshopvehicleworkSample1();
        Workshopvehiclework workshopvehiclework2 = new Workshopvehiclework();
        assertThat(workshopvehiclework1).isNotEqualTo(workshopvehiclework2);

        workshopvehiclework2.setId(workshopvehiclework1.getId());
        assertThat(workshopvehiclework1).isEqualTo(workshopvehiclework2);

        workshopvehiclework2 = getWorkshopvehicleworkSample2();
        assertThat(workshopvehiclework1).isNotEqualTo(workshopvehiclework2);
    }
}
