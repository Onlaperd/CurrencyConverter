package team.onlapus.ua;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Actions {

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be non-negative");

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    public static double convert(double from, double to, double amount){
        return round(amount/(from/to), 2);
    }
    public static String printArray(Currency[] array){
        StringBuilder result = new StringBuilder();

        for (Currency s : array){
            result.append(s.getName()).append("/");
        }

        return result.toString();
    }
    public static double validateAndSet(String toFind, Currency[] currencyCollection) throws NoCurrencyFoundException {

        boolean result = false;
        ArrayList<String> array = new ArrayList<>();

        for (Currency currency : currencyCollection){
            array.add(currency.getName());
        }

        for (String elem : array){
            if (elem.equals(toFind)) {
                result = true;
                break;
            }
        } if (result){
            for(Currency currency : currencyCollection){
                if (currency.getName().equals(toFind)){
                    return currency.getValue();
                }
            }
        } else {
            throw new NoCurrencyFoundException("No currency found");
        }

        return -1;
    }


}
