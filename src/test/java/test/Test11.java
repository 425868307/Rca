package test;

import com.alibaba.fastjson.JSON;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yaof on 2020/10/15.
 */
public class Test11 {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now.getYear());
        System.out.println(Test08.aaaa);

        int[] ins = {3, 2, 6, 32, 54, 87, 4, 34, 18, 59, 49, 53, 82, 4, 7, 44, 87};
        System.out.println(JSON.toJSONString(ins));
        System.out.println(JSON.toJSONString(mergeSort(ins)));

        aa();
        System.out.println(Test12.name);
    }


    private static int[] mergeSort(int[] ins) {
        if (ins.length <= 1) return ins;
        int[] left = Arrays.copyOfRange(ins, 0, ins.length / 2);
        int[] right = Arrays.copyOfRange(ins, ins.length / 2, ins.length);

        return mergeUtil(mergeSort(left), mergeSort(right));
    }

    private static int[] mergeUtil(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        for (int index = 0, i = 0, j = 0; index < result.length; index++) {
            if (j >= right.length) {
                result[index] = left[i++];
            } else if (i >= left.length) {
                result[index] = right[j++];
            } else if (left[i] < right[j]) {
                result[index] = left[i++];
            } else if (left[i] >= right[j]) {
                result[index] = right[j++];
            }
        }

        return result;
    }


    private static void aa() {
        Inf f = String::length;
        Inf b = a -> a.length();
        System.out.println(f.getLen("123456"));
        System.out.println(b.getLen("23456"));

        HashMap a = new HashMap();
        List<String> xx = new ArrayList<>();
        xx.add("aaaddd");
        xx.add("cccddd");
        xx.stream().forEach(aaa -> System.out.println(aaa));

    }

    interface Inf {
        int getLen(String aa);
    }
}
