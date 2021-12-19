package com.reputation;

public class Main {
    public static void main(String[] args) {
        // arg1 maxDomains arg2 maxTreads arg3 timeOut
        new MainThreadExecutor(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2])).run();

    }
}
