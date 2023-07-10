package com.programmers.springweekly.repository.voucher;

import com.programmers.springweekly.domain.voucher.Voucher;
import java.util.List;
import java.util.UUID;

public interface VoucherRepository {

    Voucher save(Voucher voucher);

    void update(Voucher voucher);

    Voucher findById(UUID voucherId);

    List<Voucher> findAll();

    void deleteById(UUID voucherId);

    void deleteAll();
    
}
