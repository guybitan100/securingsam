
package com.reputation.concurrency;

import com.google.gson.Gson;
import com.reputation.conf.Configuration;
import com.reputation.models.Metric;
import com.reputation.rest.RestClient;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharSource;
import com.google.common.io.CharStreams;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class ThreadWorker implements Callable {
    public final Configuration conf;
    final static Logger log4j = Logger.getLogger(ThreadWorker.class);
    private List<Metric> metrics;
    private final String uri;
    private final String jsonFileName;
    private final int executions;
    private final Gson gson;

    public ThreadWorker(String uri, String jsonFileName, int executions) {
        conf = new Configuration("api.properties");
        RestClient.disableSslVerification();
        this.uri = uri;
        if (!jsonFileName.endsWith(".json")) {
            jsonFileName = jsonFileName + ".json";
        }
        this.jsonFileName = jsonFileName;
        this.executions = executions;
        metrics = new ArrayList<>();
        gson = new Gson();
    }

    public synchronized RestClient getBaseLogin() {
        RestClient apiRestClient = new RestClient(conf.get("base_url"));
        HttpHeaders headers = apiRestClient.postEntity(conf.get("login_endpoint")).getHeaders();
        String set_cookie = (headers.getFirst(headers.SET_COOKIE)).split(";")[0];
        apiRestClient.addHeader("Cookie", set_cookie);
        return apiRestClient;
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

    @Override
    public List<Metric> call() {
        try {
            execute();
        } catch (Exception e) {
            log4j.debug(e);
        }
        return metrics;
    }

    public void execute() {
        RestClient.disableSslVerification();
        RestClient apiRestClient = getBaseLogin();
        for (int i = 0; i < executions; i++) {
            Metric metric = new Metric();
            metric.setStartTime(System.currentTimeMillis());
            String stringResponse = apiRestClient.post(uri, getJsonAsString(jsonFileName));
//            ClickhouseResponse response = gson.fromJson(stringResponse, ClickhouseResponse.class);
//            metric.setResponse(response);
            //metric.setReportStatus(response.getStatus());
            metric.setEndTime(System.currentTimeMillis());
            metric.setDurationMs(metric.getEndTime() - metric.getStartTime());
            metric.setThreadName(Thread.currentThread().getName() + "-" + (i+1));
            metric.setHttpStatus(apiRestClient.getStatus());
            metrics.add(metric);
        }
    }
}

