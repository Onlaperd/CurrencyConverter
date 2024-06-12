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
    public static double getCurrencyValue(String currency) throws NoCurrencyFoundException{
        switch (currency) {
            case "UAH":
                return Main.UAH;
            case "PLN":
                return Main.PLN;
            case "GBR":
                return Main.GBR;
            case "EUR":
                return Main.EUR;
            case "SEK":
                return Main.SEK;
            case "USD":
                return Main.USD;
            default:
                throw new NoCurrencyFoundException("no currency found");
        }
    }


}
