package com.reputation;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.reputation.concurrency.ThreadWorker;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public class MainThreadExecutor implements Runnable {
    final static Logger log4j = Logger.getLogger(MainThreadExecutor.class);
    public int maxTreads;
    private final ExecutorService executorService;
    private final int maxDomains;
    private ArrayBlockingQueue<String> abq;

    public MainThreadExecutor(int maxDomains, int maxTreads) {
        this.maxTreads = maxTreads;
        this.maxDomains = maxDomains;
        this.executorService = Executors.newFixedThreadPool(maxTreads);
        this.abq = new ArrayBlockingQueue<>(maxDomains);
    }

    public void readAllDataAtOnce() {
        URL resource = MainThreadExecutor.class.getClassLoader().getResource("top500Domains.csv");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        try {
            FileReader filereader = new FileReader(String.valueOf(resource.toURI()));
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            List<String[]> allData = csvReader.readAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void runDomains() {
        readAllDataAtOnce();
        Set<Callable<ThreadWorker>> callables = new HashSet<>();
        try {
            for (int i = 0; i < maxTreads; i++) {
                callables.add(new ThreadWorker("uri"));
            }
            executorService.invokeAll(callables);
            executorService.shutdown();

        } catch (Exception e) {
            log4j.debug(e);
        }
    }


    @Override
    public void run() {
        runDomains();
    }

    public static void main(String[] args) {
        new MainThreadExecutor(5, 100).run();
    }
}
