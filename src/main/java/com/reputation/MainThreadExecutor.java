package com.reputation;

import com.reputation.concurrency.ThreadWorker;
import com.reputation.conf.Configuration;
import com.reputation.rest.RestClient;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public class MainThreadExecutor implements Runnable {
    final static Logger log4j = Logger.getLogger(MainThreadExecutor.class);
    public int maxTreads;
    private final ExecutorService executorService;
    private final String jsonFileName;
    private final String uri;
    private final int executions;
    private boolean isClickhouse;

    public MainThreadExecutor(String uri, String jsonFileName, int maxTreads, int executions, boolean isClickhouse) {
        this.jsonFileName = jsonFileName;
        this.maxTreads = maxTreads;
        this.executions = executions;
        this.uri = uri;
        executorService = Executors.newFixedThreadPool(maxTreads);
        this.isClickhouse = isClickhouse;
    }

    private void runClickhouse() {
        Set<Callable<ThreadWorker>> callables = new HashSet<>();
        String clickhouseStopAllQueries = new Configuration("api.properties").get("clickhouse_stop_all_queries");
        try {
            new RestClient(clickhouseStopAllQueries).postBase();
            for (int i = 0; i < maxTreads; i++) {
                callables.add(new ThreadWorker(uri));
            }
            List<Future<ThreadWorker>> futures = executorService.invokeAll(callables);
            executorService.shutdown();

        } catch (Exception e) {
            log4j.debug(e);
        }
    }


    @Override
    public void run() {
            runClickhouse();
    }

    public static void main(String[] args) {
        String uri = new Configuration("api.properties").get("clickhouse_reports");
        new MainThreadExecutor(uri, "ClickhousePast2Weeks", 10, 2, true).run();
    }
}
