package test;

@Test
public class Test06 {

    /**
     * 自定义注解的简单使用
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//		Test06 t = new Test06();
        Test[] test = Test06.class.getAnnotationsByType(Test.class);
        System.out.println(test[0].value());

        Test06 t = new Test06();
        Test06 s = new Test06();
        System.out.println(t == s);
        System.out.println(t.getClass() == s.getClass());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    synchronized (t) {
                        t.notify();
                        System.out.println("unlock");
                        Thread.sleep(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        synchronized (t) {
            System.out.println("wait");
            t.wait();
            System.out.println("xx");
        }
        System.out.println(test.hashCode());
    }

}
