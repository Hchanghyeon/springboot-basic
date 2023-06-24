package com.programmers.springweekly.domain.voucher;

import java.util.Arrays;

public enum VoucherMenu {
    FIXED("fixed"), PERCENT("percent");

    private String menu;

    VoucherMenu(String voucherMenu) {
        this.menu = voucherMenu;
    }

    public static VoucherMenu findVoucherMenu(String voucherMenu){
        return Arrays.stream(VoucherMenu.values())
                .filter(voucher -> voucher.menu.equals(voucherMenu))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no voucher menu."));
    }
}