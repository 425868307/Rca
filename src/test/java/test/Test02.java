package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象磁盘化到本地
 *
 * @author yaofang
 * @Description
 * @date 2019年1月21日
 */

public class Test02 implements Serializable {

    private String name;

    public Test02(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        Test02 t = new Test02("猫猫毛");
        try {
            FileOutputStream fos = new FileOutputStream("D:\\EBTshare\\12345");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(t);
            os.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("D:\\EBTshare\\12345");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Test02 tt = (Test02) ois.readObject();
            ois.close();
            fis.close();
            System.out.println(tt.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
