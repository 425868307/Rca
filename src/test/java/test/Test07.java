package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.collections.SynchronizedQueue;


public class Test07 {

    public static void main(String[] args) {
        Map<Integer, Object> map = new HashMap<>();

        Long conurrentTime = System.currentTimeMillis();
        System.out.println("111:" + (1 << 4));
        System.out.println(conurrentTime & (1 << 4));
        for (int i = 0; i < 16; i++) {
            map.put(i, "hello" + i);
            Long now = System.currentTimeMillis();
            System.out.println(now - conurrentTime);
            conurrentTime = now;
        }
        System.out.println(map);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        SynchronizedQueue<String> sq = new SynchronizedQueue<>();
        sq.offer("123");
        sq.offer("456");
        System.out.println(sq);
    }


}
