package bsu.edu.cs222.model;

import org.asynchttpclient.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReadFromCountryLink {

    public List<String> asyncHttp(List<String> urls) {
        AsyncHttpClient client = Dsl.asyncHttpClient();
        List<String> results = new ArrayList<>();
        List<Future<Response>> futures = new ArrayList<>();
        try {
            for (String url : urls) {
                futures.add(client.prepareGet(url).execute());
            }
            for (Future<Response> responseFuture : futures) {
                results.add(responseFuture.get().getResponseBody());
            }
        } catch (ExecutionException | InterruptedException e) {
            System.err.println("Network Error :(");
            System.exit(3);
        }
        return results;
    }

}
