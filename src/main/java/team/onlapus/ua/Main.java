package team.onlapus.ua;

import java.util.*;

public class Main {

    public static Currency[] currencyCollection =
            {
                    new Currency(1, "USD"),
                    new Currency(40.97, "UAH"),
                    new Currency(4.04, "PLN"),
                    new Currency(0.78, "GBR"),
                    new Currency(0.93, "EUR"),
                    new Currency(10.49, "SEK"),
                    new Currency(157.07, "JPY"),
                    new Currency(18.59, "MXN"),
                    new Currency(32.3, "TRY"),
                    new Currency(1.81, "BGN"),
                    new Currency(4.61, "RON"),
                    new Currency(369.82, "HUF")
                    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Currency Converter v1.1");

        while (true){
            try{
                iteration(scanner, currencyCollection);
            } catch (NoCurrencyFoundException e){
                System.out.println("ERROR: " + e.getMessage());
            } catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("ERROR: Impossible input");
            }

            System.out.print("to continue press [ENTER], to exit press x and then [ENTER]\n> ");

            String userChoice = scanner.nextLine();

            if (userChoice.equals("x")){
                break;
            }
        }

    }

    public static void iteration(Scanner scanner, Currency[] currencyCollection)
            throws NoCurrencyFoundException, InputMismatchException {

        System.out.println();

        System.out.print("convert from\n " + Actions.printArray(currencyCollection) + "\n> ");
        String from = scanner.nextLine().replace(" ", "").toUpperCase();

        double fromAgr = Actions.validateAndSet(from, currencyCollection);

        System.out.print("convert to\n " + Actions.printArray(currencyCollection) + "\n> ");
        String to = scanner.nextLine().replace(" ", "").toUpperCase();

        double toAgr = Actions.validateAndSet(to, currencyCollection);

        System.out.print("amount: ");
        double amount;
        try{
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e){
            throw new InputMismatchException();
        }

        System.out.println(amount + from + " is " + Actions.convert(fromAgr, toAgr, amount) + to);

    }

}