package test;

import org.springframework.util.ObjectUtils;

public class Test05 {
    private static int aa;

    static {
        System.out.println("12345");
    }

    public static void main(String[] args) throws Exception {
        if (ObjectUtils.isEmpty(aa)) {
            System.out.println("aa是空的");
        } else {
            System.out.println("aa不为空");
        }

        System.out.println("ddd".compareTo("ddsda3e32e32"));

        String aaa = "'{abcde}','dsfs'";
        aaa.replace("{abcde}", "xxx");

        System.out.println(aaa);

        String bbb = "abcde";
        bbb = bbb.replace("a", "xx");
        System.out.println(bbb);

        Thread.sleep(3000);
        Test05 t = new Test05();
    }

}
