package com.yaof.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提供序列化和反序列化的方法
 *
 * @author Administrator
 */
public class SerializeUtils {

    private static final Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

    public static void main(String[] args) {
    }

    /**
     * 对实体对象进行序列化
     *
     * @param obj
     * @return
     */
    public static String getSerializeString(Serializable obj) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        String res = "";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            res = bos.toString("ISO-8859-1");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return res;
    }

    /**
     * 对字符串进行反序列化，返回实体对象
     *
     * @param serStr
     * @return
     */
    public static Object getDeserializeString(String serStr) {
        Object res = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(serStr.getBytes("ISO-8859-1"));
            ObjectInputStream ois = new ObjectInputStream(bis);
            res = ois.readObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return res;
    }
}
