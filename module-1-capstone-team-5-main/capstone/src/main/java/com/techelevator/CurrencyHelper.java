package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class CurrencyHelper {
    public static String makeItMoney(BigDecimal money) {
        return NumberFormat.getCurrencyInstance().format(money.setScale(2, RoundingMode.HALF_UP));
    }
}
