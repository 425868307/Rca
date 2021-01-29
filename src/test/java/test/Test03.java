package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test03 {

    private int m;

    public int getName() {
        return m + 1;
    }

    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            byte[] b = new byte[1024];
            fis = new FileInputStream(new File("D:\\doc\\layDate-v5.0.9.zip"));
            fos = new FileOutputStream(new File("D:\\doc\\ll.123.zip"));
            int x = 0;
            int i = 0;
            while ((x = fis.read(b)) > 0) {
                fos.write(b);
                i++;
            }
            System.out.println(x + "--" + i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

}
