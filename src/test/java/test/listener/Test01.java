package test.listener;

/**
 * @Author yaof
 * @Date 2020-12-17
 */
public class Test01 {

    /**
     * 监听者，事件， 把监听添加到事件中，当事件发生时，触发监听者事件
     *
     * @param args
     */
    public static void main(String[] args) {
        Listener listener = new WorkerListener();
        EatHintEvent event = new EatHintEvent();
        event.addListener(listener);

        event.hintEat();
    }

}
