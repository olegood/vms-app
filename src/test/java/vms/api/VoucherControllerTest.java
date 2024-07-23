package vms.api;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VoucherControllerTest {

    @Test
    void shouldReturnVouchers() {
        // expect
        assertThat(new VoucherController().getAllVouchers()).hasSize(3);
    }

}