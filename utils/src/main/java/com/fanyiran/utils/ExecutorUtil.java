package com.fanyiran.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by fanqiang on 2018/12/7.
 */
public class ExecutorUtil {
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int corePoolSize = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int maximumPoolSize = CPU_COUNT * 2 + 1;
    private static int keepAliveTime = 30;
    private static Executor executor;
    private static BlockingQueue<Runnable> blockingQueue = new SynchronousQueue();
    private static ThreadFactory threadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r);
        }
    };

    private static RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    static public Executor getExecutor(){
        if(executor == null){
            executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                    keepAliveTime, TimeUnit.SECONDS,blockingQueue,threadFactory,rejectedExecutionHandler);
        }
        return executor;
    }
}
