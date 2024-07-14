package team.onlapus.ua;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Request {


    public static ArrayList<CurrencyClass> setCurrencyCollection(String apiKey) throws URISyntaxException, IOException, InterruptedException {

        ObjectMapper mapper = new ObjectMapper();
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.currencyfreaks.com/v2.0/rates/latest?apikey=" + apiKey))
                .GET()
                .build();
        HttpResponse<String> response = client.send(getRequest, HttpResponse.BodyHandlers.ofString());

        JsonNode jsonNode = mapper.readTree(response.body());

        String ListOfCurrencies = jsonNode.get("rates").toString();

        Map<String, String> currencyMap = mapper.readValue(ListOfCurrencies, new TypeReference<>() {});

        ArrayList<CurrencyClass> currencyClassList = new ArrayList<>();

        for (Map.Entry<String, String> entry : currencyMap.entrySet()) {
            currencyClassList.add(new CurrencyClass(Double.parseDouble(entry.getValue()), entry.getKey()));
        }

        return currencyClassList;

    }

}
