package team.onlapus.ua;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Actions {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be non-negative");

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static double convert(double from, double to, double amount){return round(amount/(from/to), 2);}

    public static String printArray(String[] array){
        StringBuilder result = new StringBuilder();

        for (String s : array){
            result.append(s).append("/");
        }

        return result.toString();
    }

    public static boolean validate(String toFind, String[] array) throws NoCurrencyFoundException {

        for (String elem : array){
            if (elem.equals(toFind)) {
                return true;
            }
        }

        throw new NoCurrencyFoundException("No currency found");

    }


}
