package team.onlapus.ua;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        final String APIKEY = getAPI();
        ArrayList<CurrencyClass> currencyCollection = null;
        Scanner scanner = new Scanner(System.in);

        try {
            currencyCollection = Request.setCurrencyCollection(APIKEY);
        } catch (InterruptedException e) {
            System.out.println("Error! try to update program\n" +
                    "if it didn't helped then server is down or changed its domain");
        } catch (URISyntaxException | NullPointerException e) {
            System.out.println("Error! your API KEY is invalid");
        } catch (IOException e) {
            System.out.println("Error! there is problems with Internet connection or program failed to connect to the server");
        }

        while (true) {
            try {
                iteration(scanner, currencyCollection);
            } catch (NoCurrencyFoundException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("ERROR: Impossible input");
            }

            System.out.print("to continue press [ENTER], to exit press x and then [ENTER]\n> ");

            String userChoice = scanner.nextLine();

            if (userChoice.equalsIgnoreCase("x") || userChoice.equalsIgnoreCase("exit")) {
                break;
            }
        }

    }

    public static void iteration(Scanner scanner, ArrayList<CurrencyClass> currencyClassCollection)
            throws NoCurrencyFoundException, InputMismatchException {

        System.out.println();

        System.out.print("convert from\n(write here currency code such as USD or EUR) " + "\n> ");
        String from = scanner.nextLine().replace(" ", "").toUpperCase();

        double fromAgr = Actions.validateAndSet(from, currencyClassCollection);

        System.out.print("convert to\n(write here currency code such as USD or EUR) " + "\n> ");
        String to = scanner.nextLine().replace(" ", "").toUpperCase();

        double toAgr = Actions.validateAndSet(to, currencyClassCollection);

        System.out.print("amount: ");
        double amount;
        try {
            amount = scanner.nextDouble();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            throw new InputMismatchException();
        }

        System.out.println(amount + from + " is " + Actions.convert(fromAgr, toAgr, amount) + to);

    }

    public static String getAPI() {

        String filePath = "src/main/java/team/onlapus/ua/API_KEY";

        try (Scanner inputStream = new Scanner(new FileReader(filePath))) {
            return inputStream.nextLine();
        } catch (FileNotFoundException e) {

            String result;

            System.out.print("hello! This program requires API key specifically from the website " +
                    "https://api.currencyfreaks.com , if you haven't one you can create it for free! " +
                    "thanks.\n");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter your api key: ");
                result = scanner.nextLine();

                if (validateAPI(result)) {
                    break;
                }

                System.out.println("Error! invalid API key or server is inaccessible now");

            }


            try (FileWriter outputStream = new FileWriter(filePath)) {
                outputStream.write(result);
            } catch (IOException ex) {
                System.out.println("Oops.. Unexpected error! error message:\n" + ex.getMessage());
            }

            return result;

        }

    }

    public static boolean validateAPI(String apiKey) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + apiKey))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() != 401;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return false;
        }
    }

}