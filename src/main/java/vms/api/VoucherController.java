package vms.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

    record Voucher(UUID id, String organization, int count) {
    }

    @GetMapping
    Collection<Voucher> getAllVouchers() {
        return List.of(
                new Voucher(UUID.randomUUID(), "GE", 15),
                new Voucher(UUID.randomUUID(), "ABC Nova", 8),
                new Voucher(UUID.randomUUID(), "Sewerth", 4)
        );
    }

}
