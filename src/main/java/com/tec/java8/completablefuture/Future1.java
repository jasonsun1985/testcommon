package com.tec.java8.completablefuture;

import java.util.concurrent.*;

/**
 * @author biezhi
 * @date 2018/3/25
 */
public class Future1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Integer> submit = executorService.submit(() -> {
            TimeUnit.SECONDS.sleep(3);
            return 100;
        });

//        try {
//            Integer result = submit.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        while (!submit.isDone()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer result = submit.get();
        System.out.println(result);
        System.out.println("--------------------------------------------------------");

        Integer newResult = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int i = 1 / 0;
            return 100;
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 666;
        }).get();

        System.out.println(newResult);
    }
}
