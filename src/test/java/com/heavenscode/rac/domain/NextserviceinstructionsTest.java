package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.NextserviceinstructionsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NextserviceinstructionsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Nextserviceinstructions.class);
        Nextserviceinstructions nextserviceinstructions1 = getNextserviceinstructionsSample1();
        Nextserviceinstructions nextserviceinstructions2 = new Nextserviceinstructions();
        assertThat(nextserviceinstructions1).isNotEqualTo(nextserviceinstructions2);

        nextserviceinstructions2.setId(nextserviceinstructions1.getId());
        assertThat(nextserviceinstructions1).isEqualTo(nextserviceinstructions2);

        nextserviceinstructions2 = getNextserviceinstructionsSample2();
        assertThat(nextserviceinstructions1).isNotEqualTo(nextserviceinstructions2);
    }
}
