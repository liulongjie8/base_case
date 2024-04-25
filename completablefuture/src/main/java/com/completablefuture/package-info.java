//package org.lls;
//// 使用Future获得异步执行结果时，要么调用阻塞方法get()，要么轮询看isDone()是否为true，这两种方法都不是很好，因为主线程也会被迫等待。
//
//# 从Java 8开始引入了CompletableFuture，它针对Future做了改进，可以传入回调对象，当异步任务完成或者发生异常时，自动调用回调对象的回调方法。
//
//# 我们以获取股票价格为例，看看如何使用CompletableFuture：
//        // 如果执行成功:
//        CompletableFuture.thenAccept((result)->{
//            sxxxxxx
//        })
//        // 如果执行异常:
//        cf.exceptionally((e) -> {
//                e.printStackTrace();
//               return null;
//        });
//
//# #######################################################
//        // 两个CompletableFuture执行异步查询:
//         CompletableFuture<String> cfQueryFromSina = CompletableFuture.supplyAsync(() -> {
//                return queryCode("中国石油", "https://finance.sina.com.cn/code/");
//        });
//        CompletableFuture<String> cfQueryFrom163 = CompletableFuture.supplyAsync(() -> {
//                return queryCode("中国石油", "https://money.163.com/code/");
//        });
//
//        // 用anyOf合并为一个新的CompletableFuture:
//        CompletableFuture<Object> cfQuery = CompletableFuture.anyOf(cfQueryFromSina, cfQueryFrom163);


//- `st      static CompletableFuture<Void> runAsync(Runnable runnable)`：异步执行指定的`Runnable`任务。
//
//        - `static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)`：在指定的`Executor`上异步执行`Runnable`任务。
//
//        - `static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)`：异步执行指定的`Supplier`任务，并返回`CompletableFuture`对象，该对象在计算完成时将提供结果。
//
//        - `static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)`：在指定的`Executor`上异步执行`Supplier`任务，并返回一个`CompletableFuture`对象，该对象在计算完成时将提供结果。
//
//        - `CompletableFuture<T> thenApply(Function<? super T,? extends U> fn)`：当此 `CompletableFuture` 完成时，使用此 `CompletableFuture` 的结果作为参数应用给定的函数。
//
//        - `CompletableFuture<T> thenApplyAsync(Function<? super T,? extends U> fn)`：当此 `CompletableFuture` 完成时，异步地应用给定的函数。
//
//        - `CompletableFuture<T> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)`：当此 CompletableFuture 完成时，使用指定的 Executor 异步地应用给定的函数。
//
//        - `CompletableFuture<Void> thenAccept(Consumer<? super T> action)`：当此 `CompletableFuture` 完成时，对其结果执行给定的操作。
//
//        - `CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)`：当此 `CompletableFuture` 完成时，异步地对其结果执行给定的操作。
package com.completablefuture;