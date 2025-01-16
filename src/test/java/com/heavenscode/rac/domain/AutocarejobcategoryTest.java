package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.AutocarejobcategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AutocarejobcategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Autocarejobcategory.class);
        Autocarejobcategory autocarejobcategory1 = getAutocarejobcategorySample1();
        Autocarejobcategory autocarejobcategory2 = new Autocarejobcategory();
        assertThat(autocarejobcategory1).isNotEqualTo(autocarejobcategory2);

        autocarejobcategory2.setId(autocarejobcategory1.getId());
        assertThat(autocarejobcategory1).isEqualTo(autocarejobcategory2);

        autocarejobcategory2 = getAutocarejobcategorySample2();
        assertThat(autocarejobcategory1).isNotEqualTo(autocarejobcategory2);
    }
}
