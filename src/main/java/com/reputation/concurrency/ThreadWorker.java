package com.reputation.concurrency;

import com.reputation.models.Metric;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;

import static java.time.temporal.ChronoUnit.SECONDS;


public class ThreadWorker implements Callable {
    final static Logger log4j = Logger.getLogger(ThreadWorker.class);
    private List<Metric> metrics;
    private final ArrayBlockingQueue abq;
    private final String baseUrl = "https://candidate-eval.securingsam.com/domain/ranking/";
    private final String token = "Token I_am_under_stress_when_I_test";

    public ThreadWorker(ArrayBlockingQueue abq) {
        this.abq = abq;
        metrics = new ArrayList<>();
    }

    @Override
    public List<Metric> call() {
        try {
            execute();
        } catch (Exception e) {
            log4j.debug(e);
        }
        return metrics;
    }

    public void execute() throws URISyntaxException, IOException, InterruptedException {
        Metric metric = new Metric();
        metric.setStartTime(System.currentTimeMillis());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(baseUrl + abq.poll().toString()))
                .header("Authorization", token)
                .timeout(Duration.of(10, SECONDS))
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        metric.setResponse(response.body());
        metric.setEndTime(System.currentTimeMillis());
        metric.setDurationMs(metric.getEndTime() - metric.getStartTime());
        metric.setThreadName(Thread.currentThread().getName());
        metric.setStatusCode(response.statusCode());
        System.out.println(response.body());
        metrics.add(metric);
    }
}

