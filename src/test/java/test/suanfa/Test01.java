package test.suanfa;

public class Test01 {

    public static void main(String[] args) {
//		int[] ins = {12,23,34,12,5,7,5,23,34,7,34,23,23,12,34};
//		System.out.println(getTheSingle(ins));
//		System.out.println(getSingle(ins));
//		System.out.println(singleNumber(ins));

        System.out.println(getCount(1, 10));
    }


    private static int getCount(int rest, int day) {
        if (day == 1) return rest;
        return getCount((rest + 1) * 2, day - 1);
    }


    public static int getTheSingle(int[] ins) {
        int max = ins[0];
        int min = ins[0];
        for (int i = 1; i < ins.length; i++) {
            if (max < ins[i]) max = ins[i];
            if (min > ins[i]) min = ins[i];
        }
        boolean[] boo = new boolean[max - min + 1];
        for (int i : ins) {
            boo[i - min] = !boo[i - min];
        }
        for (int i = 0; i < boo.length; i++) {
            if (boo[i]) {
                return i + min;
            }
        }

        return -1;
    }


    public static int getSingle(int[] ins) {
        int result = 0;
        for (int i = 0; i < ins.length; i++) {
            result ^= ins[i];
        }
        return result;
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
