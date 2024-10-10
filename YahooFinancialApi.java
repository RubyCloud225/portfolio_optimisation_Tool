import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YahooFinancialApi {
    private static final String API_URL = "https://query1.finance.yahoo.com/v7/finance/download";
    private static final String API_KEY = "YOUR_API_KEY";
    private static List<Stock> stockData;
    private static List<Bond> bondData;
    private static List<FX> fxData;

    public static List<Stock> getStockData(String symbol) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(API_URL + "?symbol=" + symbol + "&period1=0&period2=999999999&interval=1d&events=history&includeAdjustedClose=true")
            .header("Authorization", "Bearer" + API_KEY)
            .build();
        
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new IOException("Error calling Yahoo Finance API: " + response.code());
        }

        String responseBody = response.body().string();

        return stockData;
    }

    public static List<Bond> getBondData(String symbol) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(API_URL + "?symbol=" + symbol + "&period1=0&period2=999999999&interval=1d&events=history&includeAdjustedClose=true")
            .header("Authorization", "Bearer" + API_KEY)
            .build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()) {
            throw new IOException("Error calling Yahoo Finance API: " + response.code());
        }

        String responseBody = response.body().string();

        return bondData;
    
    }

    public static List<FX> getFXData(String symbol) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
            .url(API_URL + "?symbol=" + symbol + "&period1=0&period2=999999999&interval=1d&events=history&includeAdjustedClose=true")
            .header("Authorization", "Bearer" + API_KEY)
            .build();

        Response response = client.newCall(request).execute();

        if(!response.isSuccessful()) {
            throw new IOException("Error calling Yahoo Finance API: " + response.code());
        }

        String responseBody = response.body().string();

        return fxData;
    }
}
