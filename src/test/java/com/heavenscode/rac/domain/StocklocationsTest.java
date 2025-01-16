package com.heavenscode.rac.domain;

import static com.heavenscode.rac.domain.StocklocationsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.heavenscode.rac.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class StocklocationsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Stocklocations.class);
        Stocklocations stocklocations1 = getStocklocationsSample1();
        Stocklocations stocklocations2 = new Stocklocations();
        assertThat(stocklocations1).isNotEqualTo(stocklocations2);

        stocklocations2.setId(stocklocations1.getId());
        assertThat(stocklocations1).isEqualTo(stocklocations2);

        stocklocations2 = getStocklocationsSample2();
        assertThat(stocklocations1).isNotEqualTo(stocklocations2);
    }
}
