package com.bobocode.lesson_8;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureSandbox {
    public static Future<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var future = calculateAsync();
        String result = future.get();
        System.out.println(result);

        CompletableFuture<Void> future2
                = CompletableFuture.supplyAsync(() -> "Hello").thenRun(() -> System.out.println("test"));
        System.out.println(future2.get());


    }


}
