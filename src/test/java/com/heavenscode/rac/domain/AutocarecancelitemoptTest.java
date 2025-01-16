package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocarecancelitemoptTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocarecancelitemoptTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocarecancelitemopt.class);
        Autocarecancelitemopt autocarecancelitemopt1 = getAutocarecancelitemoptSample1();
        Autocarecancelitemopt autocarecancelitemopt2 = new Autocarecancelitemopt();
        assertThat(autocarecancelitemopt1).isNotEqualTo(autocarecancelitemopt2);

        autocarecancelitemopt2.setId(autocarecancelitemopt1.getId());
        assertThat(autocarecancelitemopt1).isEqualTo(autocarecancelitemopt2);

        autocarecancelitemopt2 = getAutocarecancelitemoptSample2();
        assertThat(autocarecancelitemopt1).isNotEqualTo(autocarecancelitemopt2);
    }
}
