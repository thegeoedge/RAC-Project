package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.InventorybatchesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InventorybatchesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inventorybatches.class);
        Inventorybatches inventorybatches1 = getInventorybatchesSample1();
        Inventorybatches inventorybatches2 = new Inventorybatches();
        assertThat(inventorybatches1).isNotEqualTo(inventorybatches2);

        inventorybatches2.setId(inventorybatches1.getId());
        assertThat(inventorybatches1).isEqualTo(inventorybatches2);

        inventorybatches2 = getInventorybatchesSample2();
        assertThat(inventorybatches1).isNotEqualTo(inventorybatches2);
    }
}
