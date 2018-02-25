package util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Evgeniy Golubtsov on 25.02.2018.
 */
public class CurrencyFormat {
    private static NumberFormat numberFormatter = NumberFormat.getCurrencyInstance(Locale.US);

    public static String format(BigDecimal number) {
        return numberFormatter.format(number);
    }

    public static String getCurrency(){
        return numberFormatter.format(0)
                .replace("0","")
                .replace(".", "")
                .replace(",", "");
    }
}
