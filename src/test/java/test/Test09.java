package test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.function.Function;
import java.util.function.IntSupplier;

public class Test09 {

    public static void main(String[] args) {

        Function<Integer, String> f1 = String::valueOf;
        f1.apply(23);

        IntSupplier ii = () -> 2;


        User user = new User();
        user.setUserName("yaofang");
        user.setUserAge("24");
        try {
            PropertyDescriptor pd = new PropertyDescriptor("userName", User.class);
            Method readMethod = pd.getReadMethod();

            Method writeMethod = pd.getWriteMethod();
            writeMethod.invoke(user, "yyyyyyy");
            System.out.println(readMethod.invoke(user));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                Method readMethod = propertyDescriptor.getReadMethod();

                System.out.println(propertyDescriptor.getName() + ":" + readMethod.invoke(user));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
