package com.programmers.springweekly.repository.voucher;

import com.programmers.springweekly.domain.voucher.FixedAmountVoucher;
import com.programmers.springweekly.domain.voucher.PercentDiscountVoucher;
import com.programmers.springweekly.domain.voucher.Voucher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(classes = {FileVoucherRepository.class})
public class FileVoucherRepositoryTest {

    @Autowired
    private VoucherRepository voucherRepository;

    @Test
    @DisplayName("파일 저장소에 고정 할인 바우처가 정상적으로 등록된다.")
    void saveFixedVoucherToFileRepository() {
        // given
        Voucher voucher = new FixedAmountVoucher(UUID.randomUUID(), 1000);

        // when
        voucherRepository.save(voucher);

        // then
        assertThat(voucherRepository.getList().get(voucher.getVoucherId()))
                .usingRecursiveComparison()
                .isEqualTo(voucher);
    }

    @Test
    @DisplayName("파일 저장소에 퍼센트 할인 바우처가 정상적으로 등록된다.")
    void savePercentVoucherToFileRepository() {
        // given
        Voucher voucher = new PercentDiscountVoucher(UUID.randomUUID(), 100);

        // when
        voucherRepository.save(voucher);

        // then
        assertThat(voucherRepository.getList().get(voucher.getVoucherId()))
                .usingRecursiveComparison()
                .isEqualTo(voucher);
    }
}
