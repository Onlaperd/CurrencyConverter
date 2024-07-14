package team.onlapus.ua;

public class CurrencyClass {

    private final double value;
    private final String name;

    public CurrencyClass(double value, String name) {
        this.value = value;
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
