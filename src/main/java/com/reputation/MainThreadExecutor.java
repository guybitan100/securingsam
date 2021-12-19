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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public class MainThreadExecutor implements Runnable {
    final static Logger log4j = Logger.getLogger(MainThreadExecutor.class);
    public int maxTreads;
    private final ExecutorService executorService;
    private final int maxDomains;
    private ArrayBlockingQueue<Metric> metrics;

    public MainThreadExecutor(int maxDomains, int maxTreads) {
        this.maxTreads = maxTreads;
        this.maxDomains = maxDomains;
        this.executorService = Executors.newFixedThreadPool(maxTreads);
        this.metrics = new ArrayBlockingQueue<>(500);
    }

    public ArrayBlockingQueue<String> readAllDataAtOnce() throws URISyntaxException {
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<>(maxDomains);
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
                callables.add(new ThreadWorker(metrics, readAllDataAtOnce()));
            }
            executorService.invokeAll(callables);
            executorService.shutdown();

        } catch (Exception e) {
            log4j.debug(e);
        }
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
        new MainThreadExecutor(500, 100).run();
    }
}
