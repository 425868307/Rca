package test.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {

    /**
     * 定义一个线程集群有6个人，当所有人都到了就一起执行；最后来的人可以做一件事情(请大家吃雪糕)
     */
    private static CyclicBarrier barrier = new CyclicBarrier(6, new Runnable() {
        /**
         * 最后到达的线程执行的方法，哪个线程最终到达，就由他执行这个方法
         */
        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName() + "我是最后一个来到的，迟到了，所以我请大家吃雪糕。。。");

        }
    });

    static class TaskThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(getName() + "我已经到达饭店啦！！！");
                barrier.await();
                System.out.println(getName() + "吃完饭啦拉拉，好饱啊！");

                Thread.sleep(2000);
                System.out.println(getName() + "我到了KTV了，大家快点勒");
                barrier.await();
                System.out.println(getName() + "今天很高兴，我要回家了，大家拜拜。。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int threadNum = 6;

        for (int i = 0; i < threadNum; i++) {
            if (i % 2 == 0) {
                new TaskThread().start();
            } else {
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            System.out.println(Thread.currentThread().getName() + "我到了，大家久等了！");
                            barrier.await();
                            System.out.println(Thread.currentThread().getName() + "吃完啦，现在去哪里玩呢？");
                            Thread.sleep(2000);
                            System.out.println(Thread.currentThread().getName() + "我也到KTV了啊，你们人呢？");
                            barrier.await();
                            Thread.sleep(1250);
                            System.out.println(Thread.currentThread().getName() + "今天好高兴啊，我们下次再聚哦。");
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

}
