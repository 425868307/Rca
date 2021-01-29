package test.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

public class Test01 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");

        System.out.println(JSON.toJSONString(list.subList(0,2)));

        List<String> collect = list.stream().map(aa -> aa + "yaofag").collect(Collectors.toList());

        collect.stream().forEach(bb -> System.out.println(bb));
        System.out.println(JSON.toJSONString(collect));

    }

}
