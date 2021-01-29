package test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yaof.pojo.User;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;


/**
 * Created by yaofang on 2019/3/15.
 */
public class Test04 {


    public static void main(String[] args) {
        try {
            checkd(new User());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> void checkd(T obj) throws Exception {
        Method[] ms = obj.getClass().getMethods();        //通过反射取得此类所有方法
        Field[] fs = obj.getClass().getDeclaredFields();    //通过反射取得所有的字段
        for (Field f : fs) {
            if (!String.class.equals(f.getType())) {
                continue;
            }
            System.out.println(f.getName());
            String fname = f.getName();
            String getMethod = "";
            String setMethod = "";
            for (Method m : ms) {
                String mname = m.getName();
                if (lowerCase(mname).equals(lowerCase("get" + fname))) {
                    getMethod = mname;
                    System.out.println(getMethod);
                }
                if (lowerCase(mname).equals(lowerCase("set" + fname))) {
                    setMethod = mname;
                    System.out.println(mname);
                }
            }
            if (!StringUtils.isEmpty(getMethod) && !StringUtils.isEmpty(setMethod)) {
                Method gm = obj.getClass().getMethod(getMethod);
                Method sm = obj.getClass().getMethod(setMethod, f.getType());
                String prop = HtmlUtils.htmlUnescape(String.valueOf(gm.invoke(obj)));
                sm.invoke(obj, prop);
            }
        }
    }

    public static String lowerCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();

    }
}
