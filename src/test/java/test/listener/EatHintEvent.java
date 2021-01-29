package test.listener;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaof
 * @Date 2020-12-17
 */
public class EatHintEvent {
    /**
     * 事件监听，需要的成员：监听器(监听类)，事件源，
     * <p>
     * 例子：
     * 1.现在事件源发布一个事件：集体吃饭时间到提醒
     * 2.worker如果需要一起吃饭的，需要监听(订阅)此提醒；如果不需要吃饭的，不监听此次提醒
     * 3.事件发生：
     * 4.所有订阅了此次提醒的worker，前往集合
     */

    private List<Listener> listeners = new ArrayList<>();

    void addListener(Listener listener) {
        listeners.add(listener);
    }

    void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public void hintEat() {
        System.out.println("event:集体吃饭时间到提醒！！！");
        hintWorker();
    }

    private void hintWorker() {
        if (!CollectionUtils.isEmpty(listeners)) {
            for (Listener listener : listeners) {
                listener.doSomeThing();
            }
        }
    }

}
