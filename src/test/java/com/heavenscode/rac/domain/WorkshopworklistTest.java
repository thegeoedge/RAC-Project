package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.WorkshopworklistTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WorkshopworklistTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Workshopworklist.class);
        Workshopworklist workshopworklist1 = getWorkshopworklistSample1();
        Workshopworklist workshopworklist2 = new Workshopworklist();
        assertThat(workshopworklist1).isNotEqualTo(workshopworklist2);

        workshopworklist2.setId(workshopworklist1.getId());
        assertThat(workshopworklist1).isEqualTo(workshopworklist2);

        workshopworklist2 = getWorkshopworklistSample2();
        assertThat(workshopworklist1).isNotEqualTo(workshopworklist2);
    }
}
