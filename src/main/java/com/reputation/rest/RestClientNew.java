package com.reputation.rest;

import com.reputation.pojo.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class RestClientNew {
    HttpClient client;

    public RestClientNew() {
        this.client = HttpClient.newHttpClient();

    }

    public HttpResponse send(String url) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Authorization","Token I_am_under_stress_when_I_test")
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }


}
