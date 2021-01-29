package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallableImpl implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        System.out.println("go");
        return "123456";
    }

    public static void main(String[] args) {
//		ExecutorService
        LinkedBlockingQueue que = new LinkedBlockingQueue<>();
        ThreadPoolExecutor t = new ThreadPoolExecutor(10, 10, 15000, TimeUnit.MILLISECONDS, que);
        Future<String> fu = t.submit(new CallableImpl());
        try {
            System.out.println(fu.get());
            System.out.println("执行完毕");
            t.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
