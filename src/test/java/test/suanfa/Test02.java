package test.suanfa;

public class Test02 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[][] inss = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        travMatrix(inss);
    }

    /**
     * 给定一个矩阵，顺时针遍历数据值
     * 1, 2, 3, 4, 5
     * 6, 7, 8, 9,10
     * 11,12,13,14,15
     * 16,17,18,19,20
     */
    private static void travMatrix(int[][] inss) {
        int up = 0, down = inss.length - 1, left = 0, right = inss[0].length - 1;
        String now = "up";
        while (up <= down && left <= right) {
            switch (now) {
                case "up":
                    for (int i = left; i <= right; i++) {
                        System.out.println(inss[up][i]);
                    }
                    up++;
                    now = "right";
                    break;
                case "down":
                    for (int i = right; i >= left; i--) {
                        System.out.println(inss[down][i]);
                    }
                    down--;
                    now = "left";
                    break;
                case "left":
                    for (int i = down; i >= up; i--) {
                        System.out.println(inss[i][left]);
                    }
                    left++;
                    now = "up";
                    break;
                case "right":
                    for (int i = up; i <= down; i++) {
                        System.out.println(inss[i][right]);
                    }
                    right--;
                    now = "down";
                    break;
            }
        }
    }
}
