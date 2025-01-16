package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocaretimetableTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocaretimetableTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocaretimetable.class);
        Autocaretimetable autocaretimetable1 = getAutocaretimetableSample1();
        Autocaretimetable autocaretimetable2 = new Autocaretimetable();
        assertThat(autocaretimetable1).isNotEqualTo(autocaretimetable2);

        autocaretimetable2.setId(autocaretimetable1.getId());
        assertThat(autocaretimetable1).isEqualTo(autocaretimetable2);

        autocaretimetable2 = getAutocaretimetableSample2();
        assertThat(autocaretimetable1).isNotEqualTo(autocaretimetable2);
    }
}
