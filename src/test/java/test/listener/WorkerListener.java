package test.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author yaof
 * @Date 2020-12-17
 */
public class WorkerListener implements Listener {


    @Override
    public void doSomeThing() {
        System.out.println("worker(listener):我要去集合吃饭啦！！！");
    }
}
