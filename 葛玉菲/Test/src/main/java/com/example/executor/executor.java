//package com.example.executor;
//
//import java.util.ArrayDeque;
//import java.util.Queue;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class executor {
//
//    //任务队列
//    final Queue<Runnable> tasks = new ArrayDeque<Runnable>();
//    // 执行器
//    final Executor executor;
//    // 当前正在执行的任务
//    Runnable active;
//
//    // 初始化，指定执行器
//    SerialExecutor(Executor executor) {
//        this.executor = executor;
//    }
//
//    //添加任务到线程池：将任务添加到任务队列，scheduleNext触发执行器去任务队列取任务
//    public synchronized void execute(final Runnable r) {
//        tasks.offer(new Runnable() {
//            public void run() {
//                try {
//                    r.run();
//                } finally {
//                    scheduleNext();
//                }
//            }
//        });
//        if(active == null) {
//            scheduleNext;
//        }
//    }
//
//    protected synchronized void scheduleNext() {
//        if((tasks.poll() == active) != null) {
//            executor.execute(active);
//        }
//    }
//
//    ExecutorService executorService = Executors.newFixedThreadPool();
//    ExecutorService executorService = Executors.newCachedThreadPool();
//}
