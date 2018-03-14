package util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

/**
 * Created by Evgeniy Golubtsov on 25.02.2018.
 */
public abstract class CurrencyFormat {
    private static final Locale locale = Locale.US;
    private static final NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(locale);

    public static String format(BigDecimal number) {
        return numberFormatter.format(number);
    }

    public static String getCurrency(){
        return Currency.getInstance(locale).getSymbol(locale);
    }
}
