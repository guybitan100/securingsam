package com.reputation;

import com.reputation.concurrency.ThreadWorker;
import com.reputation.rest.RestClientNew;
import org.apache.log4j.Logger;

import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;


public class MainThreadExecutor implements Runnable {
    final static Logger log4j = Logger.getLogger(MainThreadExecutor.class);
    public int maxTreads;
    private final ExecutorService executorService;
    private final String uri;

    public MainThreadExecutor(String uri, int maxTreads) {
        this.maxTreads = maxTreads;
        this.uri = uri;
        executorService = Executors.newFixedThreadPool(maxTreads);
    }

    private void runDomains() {
        Set<Callable<ThreadWorker>> callables = new HashSet<>();
        try {
            HttpResponse response = new RestClientNew().send(uri);
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
        runDomains();
    }

    public static void main(String[] args) {
        new MainThreadExecutor("https://candidate-eval.securingsam.com/domain/ranking/google.com",1).run();
    }
}
