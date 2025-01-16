package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.ServicecategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServicecategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Servicecategory.class);
        Servicecategory servicecategory1 = getServicecategorySample1();
        Servicecategory servicecategory2 = new Servicecategory();
        assertThat(servicecategory1).isNotEqualTo(servicecategory2);

        servicecategory2.setId(servicecategory1.getId());
        assertThat(servicecategory1).isEqualTo(servicecategory2);

        servicecategory2 = getServicecategorySample2();
        assertThat(servicecategory1).isNotEqualTo(servicecategory2);
    }
}
