package team.onlapus.ua;

import java.util.Scanner;

public class Main {

    public static double UAH = 40.47;
    public static double PLN = 4.04;
    public static double GBR = 0.78;
    public static double EUR = 0.93;
    public static double SEK = 10.49;
    public static double USD = 1;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Currency Converter v1.0");

        while (true){
            try{
                iteration();
            } catch (NoCurrencyFoundException e){
                System.out.println("ERROR: " + e.getMessage());
            }

            System.out.print("to continue press [ENTER], to exit press x and then [ENTER]\n> ");
            String userChoice = scanner.nextLine();

            if (userChoice.equals("x")){
                break;
            }
        }

    }

    public static void iteration() throws NoCurrencyFoundException {

        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.print("convert from UAH/PLN/GBR/EUR/SEK/USD: ");
        String from = scanner.nextLine().replace(" ", "").toUpperCase();

        double fromAgr = Actions.getCurrencyValue(from);

        System.out.print("convert to UAH/PLN/GBR/EUR/SEK/USD: ");
        String to = scanner.nextLine().replace(" ", "").toUpperCase();

        double toAgr = Actions.getCurrencyValue(to);

        System.out.print("amount: ");
        double amount = scanner.nextDouble();


        System.out.println(amount + from + " is " + Actions.convert(fromAgr, toAgr, amount) + to);

    }

}