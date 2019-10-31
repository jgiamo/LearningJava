package concurrency;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SyncTest {

    public static void forkAndJoinTest(){
        ForkJoinPool forkJoinPool = new ForkJoinPool();



    }

    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(16));

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world");
            }
        });

        CompletableFuture<Integer> completableFutureV2 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("supplier");
                return 123;
            }
        }).thenApply(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("first step");
                return null;
            }
        }).thenApply(new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println("second step");
                return null;
            }
        }).exceptionally(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) {
                System.out.println("third step");
                return null;
            }
        }).whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                System.out.println("end");
            }
        });

        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPoolExecutor);

        completionService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("first step");
                Thread.sleep(1000);
                return 1;
            }
        });

        completionService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("second step");
                return 2;
            }
        });

        completionService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("third step");
                return 3;
            }
        });

        AtomicReference<Integer> atomicReference = new AtomicReference<>(Integer.MAX_VALUE);

        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.submit(new Runnable() {
                public void run() {
                    try {
                        Integer expect = atomicReference.get();
                        Integer newValue = completionService.take().get();
                        atomicReference.compareAndSet(expect, Integer.min(expect, newValue));
                    } catch (Exception e) {

                    }
                    return;
                }
            });
        }

        System.out.println(atomicReference.get());


    }
}
