package com.hdu.stash;

import akka.Done;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/20
 * @Time 下午4:51
 */
public class DBIml implements StashDemo.DB {
    @Override
    public CompletionStage<Done> save(String id, String value) {
        System.out.println(String.format("id %s,value %s", id, value));
        CompletableFuture future = new CompletableFuture();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3232);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                future.complete("the db handle is over");
            }
        });
        thread.start();
        return future;
    }

    @Override
    public CompletionStage<String> load(String id) {

        System.out.println(String.format("db is loading,%s", id));
        CompletableFuture future = new CompletableFuture();
        future.completeExceptionally(new InterruptedException(""));
        return future;
    }
}
