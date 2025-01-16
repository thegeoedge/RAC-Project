package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.InventoryTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InventoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Inventory.class);
        Inventory inventory1 = getInventorySample1();
        Inventory inventory2 = new Inventory();
        assertThat(inventory1).isNotEqualTo(inventory2);

        inventory2.setId(inventory1.getId());
        assertThat(inventory1).isEqualTo(inventory2);

        inventory2 = getInventorySample2();
        assertThat(inventory1).isNotEqualTo(inventory2);
    }
}
