package team.onlapus.ua;

import java.util.*;

public class Main {

    public static String[] currencyNameCollection = {"USD", "UAH", "PLN", "GBR", "EUR", "SEK"};

    public static void main(String[] args) {

        Map<String, Double> currencyCollection = new HashMap<>();
        currencyCollection.put("USD", 1.0);
        currencyCollection.put("UAH", 40.74);
        currencyCollection.put("PLN", 4.04);
        currencyCollection.put("GBR", 0.78);
        currencyCollection.put("EUR", 0.93);
        currencyCollection.put("SEK", 10.49);
        currencyCollection.put("JPY", 157.07);
        currencyCollection.put("MXN", 18.59);
        currencyCollection.put("TRY", 32.3);
        currencyCollection.put("BGN", 1.81);
        currencyCollection.put("RON", 4.61);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Currency Converter v1.1");

        while (true){
            try{
                iteration(scanner, currencyCollection);
            } catch (NoCurrencyFoundException e){
                System.out.println("ERROR: " + e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("ERROR: Impossible input");
            }

            System.out.print("to continue press [ENTER], to exit press x and then [ENTER]\n> ");
            String userChoice = scanner.nextLine();

            if (userChoice.equals("x")){
                break;
            }
        }

    }

    public static void iteration(Scanner scanner, Map<String, Double> currencyCollection)
            throws NoCurrencyFoundException, InputMismatchException {

        System.out.println();

        System.out.print("convert from\n " + Actions.printArray(currencyNameCollection) + "\n> ");
        String from = scanner.nextLine().replace(" ", "").toUpperCase();

        Actions.validate(from, currencyNameCollection);
        Double fromAgr = currencyCollection.get(from);

        System.out.print("convert to\n " + Actions.printArray(currencyNameCollection) + "\n> ");
        String to = scanner.nextLine().replace(" ", "").toUpperCase();

        Actions.validate(to, currencyNameCollection);
        Double toAgr = currencyCollection.get(to);

        System.out.print("amount: ");
        double amount;
        try{
            amount = scanner.nextDouble();
        } catch (InputMismatchException e){
            throw new InputMismatchException();
        }


        System.out.println(amount + from + " is " + Actions.convert(fromAgr, toAgr, amount) + to);

    }

}