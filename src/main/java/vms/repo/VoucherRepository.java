package vms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import vms.domain.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {}
