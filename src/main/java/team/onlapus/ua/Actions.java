package team.onlapus.ua;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;

public class Actions {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be non-negative");

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double convert(double from, double to, double amount) {
        return round(amount / (from / to), 2);
    }

    public static double validateAndSet(String toFind, ArrayList<CurrencyClass> currencyClassCollection) throws NoCurrencyFoundException {

        boolean result = false;

        LinkedList<String> array = new LinkedList<>();
        for (CurrencyClass currencyClass : currencyClassCollection) {
            array.add(currencyClass.getName());
        }

        for (String elem : array) {
            if (elem.equals(toFind)) {
                result = true;
                break;
            }
        }
        if (result) {
            for (CurrencyClass currencyClass : currencyClassCollection) {
                if (currencyClass.getName().equals(toFind)) {
                    return currencyClass.getValue();
                }
            }
        } else {
            throw new NoCurrencyFoundException("No currency found");
        }

        return -1;
    }


}
