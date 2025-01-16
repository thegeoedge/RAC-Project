package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.ServicesubcategoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ServicesubcategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Servicesubcategory.class);
        Servicesubcategory servicesubcategory1 = getServicesubcategorySample1();
        Servicesubcategory servicesubcategory2 = new Servicesubcategory();
        assertThat(servicesubcategory1).isNotEqualTo(servicesubcategory2);

        servicesubcategory2.setId(servicesubcategory1.getId());
        assertThat(servicesubcategory1).isEqualTo(servicesubcategory2);

        servicesubcategory2 = getServicesubcategorySample2();
        assertThat(servicesubcategory1).isNotEqualTo(servicesubcategory2);
    }
}
