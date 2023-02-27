package com.techelevator.view;

import com.techelevator.CurrencyHelper;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CurrencyHelperTest {
    @Test
    public void currencyHelperConvertsBigDecimalsToMoneyStrings() {
        assertEquals("$1.95", CurrencyHelper.makeItMoney(BigDecimal.valueOf(1.95000000001f)));
        assertEquals("$20.15", CurrencyHelper.makeItMoney(BigDecimal.valueOf(20.1499999999f)));
        assertEquals("$0.00", CurrencyHelper.makeItMoney(BigDecimal.ZERO));
    }
}
