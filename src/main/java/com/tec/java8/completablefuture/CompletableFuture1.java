package com.tec.java8.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 创建 CompletableFuture
 *
 * @author biezhi
 * @date 2018/3/25
 */
public class CompletableFuture1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<Void> helloFuture = CompletableFuture.runAsync(() -> System.out.println("hello future"));
        System.out.println(helloFuture.get());
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 2333);
        System.out.println(integerCompletableFuture.get());

    }
}
