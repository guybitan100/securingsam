package com.reputation;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.reputation.concurrency.ThreadWorker;
import com.reputation.models.Metric;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public class MainThreadExecutor implements Runnable {
    final static Logger log4j = Logger.getLogger(MainThreadExecutor.class);
    public int maxTreads;
    private LinkedBlockingDeque<Metric> metrics;
    private ExecutorService executorService;
    private int maxDomains;

    public MainThreadExecutor(int maxDomains, int maxTreads) {
        this.maxTreads = maxTreads;
        this.maxDomains = maxDomains;
        this.executorService = Executors.newFixedThreadPool(maxTreads);
        this.metrics = new LinkedBlockingDeque<>();
    }

    public ArrayList<String> readAllDataAtOnce() throws URISyntaxException {
        ArrayList<String> abq = new ArrayList<>();
        URL resource = MainThreadExecutor.class.getClassLoader().getResource("top500Domains.csv");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        File file = Paths.get(resource.toURI()).toFile();
        try {
            FileReader filereader = new FileReader(file.getAbsolutePath());
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allDomains = csvReader.readAll();
            for (int i = 0; i < maxDomains; i++) {
                abq.add(allDomains.get(i)[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return abq;
    }

    private void runAllDomains() throws URISyntaxException {
        Set<Callable<ThreadWorker>> callables = new HashSet<>();
        try {
            for (int i = 0; i < maxTreads; i++) {
                callables.add(new ThreadWorker(metrics, maxDomains, readAllDataAtOnce()));
            }
            executorService.invokeAll(callables);
            executorService.shutdown();

        } catch (InterruptedException interruptedException) {
            System.out.println("Test is over!");
            System.out.println("Reason: keyboard interrupt");
        } catch (Exception e) {
            log4j.debug(e);
        }
        System.out.println("Test is over!");
        System.out.println("Reason: timeout");
        System.out.println("Requests in total: " + metrics.size());

        int metrics_total_time = 0;
        long max_time = 0;
        for (Metric metric : metrics) {
            metrics_total_time += metric.getDurationMs();
            if (metric.getDurationMs() > max_time) {
                max_time = metric.getDurationMs();
            }
        }
        System.out.println("Time in total: " + metrics_total_time / 1000 + " Seconds");
        System.out.println("Max time for one request: " + max_time / 1000 + " Seconds");
        System.out.println("Average time for one request: " + (metrics_total_time / metrics.size()) / 1000 + " Seconds");
    }

    @Override
    public void run() {
        try {
            runAllDomains();
            System.out.println(metrics);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MainThreadExecutor(100, 10).run();
    }
}
