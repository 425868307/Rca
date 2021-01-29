package test;

public class FinalizeEscaeGC {

    public static FinalizeEscaeGC sava_hook = null;

    public void isAlive() {
        System.out.println("yes,i am still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscaeGC.sava_hook = this;
    }

    public static void main(String[] args) throws Throwable {
        sava_hook = new FinalizeEscaeGC();
        sava_hook = null;
        System.gc();
//		Thread.sleep(500);
        if (sava_hook != null) {
            sava_hook.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }

        sava_hook = null;
        System.gc();
        Thread.sleep(500);
        if (sava_hook != null) {
            sava_hook.isAlive();
        } else {
            System.out.println("no, i am dead :(");
        }
    }

}
