package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.LastserviceinstructionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LastserviceinstructionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lastserviceinstructions.class);
        Lastserviceinstructions lastserviceinstructions1 = getLastserviceinstructionsSample1();
        Lastserviceinstructions lastserviceinstructions2 = new Lastserviceinstructions();
        assertThat(lastserviceinstructions1).isNotEqualTo(lastserviceinstructions2);

        lastserviceinstructions2.setId(lastserviceinstructions1.getId());
        assertThat(lastserviceinstructions1).isEqualTo(lastserviceinstructions2);

        lastserviceinstructions2 = getLastserviceinstructionsSample2();
        assertThat(lastserviceinstructions1).isNotEqualTo(lastserviceinstructions2);
    }
}
