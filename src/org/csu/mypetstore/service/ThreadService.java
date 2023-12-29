package org.csu.mypetstore.service;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class ThreadService {
    private static ExecutorService pool;
    static{
       pool = newFixedThreadPool(3);
    }
    public static void submitATask(Runnable task){
        pool.submit(task);
    }
}
