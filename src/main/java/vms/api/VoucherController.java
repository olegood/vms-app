package vms.api;

import java.util.Collection;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vms.domain.Voucher;
import vms.repo.VoucherRepository;

@AllArgsConstructor
@RestController
@RequestMapping("/api/voucher")
public class VoucherController {

  private final VoucherRepository voucherRepository;

  @GetMapping
  Collection<Voucher> getAllVouchers() {
    return voucherRepository.findAll();
  }
}
