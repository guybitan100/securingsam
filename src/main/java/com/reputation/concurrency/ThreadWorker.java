package com.reputation.concurrency;

import com.google.gson.Gson;
import com.reputation.models.Metric;
import com.reputation.rest.RestClient;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import com.reputation.rest.RestClientNew;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class ThreadWorker implements Callable {
    final static Logger log4j = Logger.getLogger(ThreadWorker.class);
    private List<Metric> metrics;
    private final String uri;
    private final Gson gson;

    public ThreadWorker(String uri) {
        RestClient.disableSslVerification();
        this.uri = uri;
        metrics = new ArrayList<>();
        this.gson = new Gson();
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
        RestClientNew restClientNew = new RestClientNew();
        Metric metric = new Metric();
        metric.setStartTime(System.currentTimeMillis());
        restClientNew.send(uri);
        //metric.setResponse(response);
        metric.setEndTime(System.currentTimeMillis());
        metric.setDurationMs(metric.getEndTime() - metric.getStartTime());
        metric.setThreadName(Thread.currentThread().getName());
        // metric.setHttpStatus(apiRestClient.getStatus());
        metrics.add(metric);
    }


    public String getJsonAsString(String fileName) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(fileName);
        String jsonString = "";
        try {
            byte[] buffer = ByteStreams.toByteArray(stream);
            Reader reader = CharSource.wrap(new String(buffer)).openStream();
            jsonString = CharStreams.toString(reader);
            reader.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return jsonString;
    }
}

