package com.reputation.concurrency;

import com.google.gson.Gson;
import com.reputation.models.Metric;
import com.reputation.pojo.Response;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingDeque;

import static java.time.temporal.ChronoUnit.SECONDS;


public class ThreadWorker implements Callable {
    final static Logger log4j = Logger.getLogger(ThreadWorker.class);
    private LinkedBlockingDeque<Metric> metrics;
    private final ArrayList<String> domainList;
    private final String baseUrl = "https://candidate-eval.securingsam.com/domain/ranking/";
    private final String token = "Token I_am_under_stress_when_I_test";
    private int maxDomain;

    public ThreadWorker(LinkedBlockingDeque<Metric> metrics, int maxDomain, ArrayList<String> domainList) {
        this.domainList = domainList;
        this.metrics = metrics;
        this.maxDomain = maxDomain;
    }

    @Override
    public LinkedBlockingDeque<Metric> call() {
        try {
            execute();
        } catch (Exception e) {
            log4j.debug(e);
        }
        return metrics;
    }

    public void execute() throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        Metric metric = new Metric();
        metric.setStartTime(System.currentTimeMillis());
        for (int i = 0; i < maxDomain; i++) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(baseUrl + domainList.get(i)))
                    .header("Authorization", token)
                    .timeout(Duration.of(10, SECONDS))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            Response responseJson = gson.fromJson(response.body(), Response.class);
            metric.setResponse(responseJson);
            metric.setEndTime(System.currentTimeMillis());
            metric.setDurationMs(metric.getEndTime() - metric.getStartTime());
            metric.setThreadName(Thread.currentThread().getName());
            metric.setStatusCode(response.statusCode());
            metrics.add(metric);
        }
    }
}

