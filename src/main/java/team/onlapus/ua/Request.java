package team.onlapus.ua;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {

    public static ArrayList<CurrencyClass> setCurrencyCollection(String apiKey) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<CurrencyClass> currencyClassList = new ArrayList<>();
        String apiUrl = "https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + apiKey;

        HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            JsonNode jsonNode = mapper.readTree(connection.getInputStream());
            String ListOfCurrencies = jsonNode.get("rates").toString();

            Map<String, String> currencyMap = mapper.readValue(ListOfCurrencies, new TypeReference<Map<String, String>>() {});

            for (Map.Entry<String, String> entry : currencyMap.entrySet()) {
                currencyClassList.add(new CurrencyClass(Double.parseDouble(entry.getValue()), entry.getKey()));
            }
        }

        connection.disconnect();
        return currencyClassList;
    }
}
