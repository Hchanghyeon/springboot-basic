package com.programmers.springweekly.domain.voucher;

import java.util.UUID;

public class VoucherFactory {

    public Voucher createVoucher(VoucherMenu voucherMenu, long discount){
        if(voucherMenu.equals(VoucherMenu.FIXED)){
            return new FixedAmountVoucher(UUID.randomUUID(), discount, "fixed");
        }

        if(voucherMenu.equals(VoucherMenu.PERCENT)){
            return new PercentDiscountVoucher(UUID.randomUUID(), discount, "percent");
        }

        throw new IllegalArgumentException("There is no voucher menu.");
    }
}
