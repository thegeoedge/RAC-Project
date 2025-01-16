package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocarejobinimagesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocarejobinimagesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocarejobinimages.class);
        Autocarejobinimages autocarejobinimages1 = getAutocarejobinimagesSample1();
        Autocarejobinimages autocarejobinimages2 = new Autocarejobinimages();
        assertThat(autocarejobinimages1).isNotEqualTo(autocarejobinimages2);

        autocarejobinimages2.setId(autocarejobinimages1.getId());
        assertThat(autocarejobinimages1).isEqualTo(autocarejobinimages2);

        autocarejobinimages2 = getAutocarejobinimagesSample2();
        assertThat(autocarejobinimages1).isNotEqualTo(autocarejobinimages2);
    }
}
