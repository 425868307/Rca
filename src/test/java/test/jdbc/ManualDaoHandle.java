package test.jdbc;


import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author wangx
 * @Date: Nov 25, 2016
 * @func: 手工(非spring整合)的方式使用mybatis
 * @Copyright: 2016 wangx. All rights reserved.
 */
public class ManualDaoHandle {
    private final Logger log = LoggerFactory.getLogger(ManualDaoHandle.class);

    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            //第一种方式 使用sqlsesiontemplate
            sqlSession = new SqlSessionTemplate(new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("test/jdbc/mybatis-config.xml")));
            //第二种方式 直接使用session
            //sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("com/test/mybatis/mybatis-config.xml")).openSession();
            List<?> list = sqlSession.selectList("getPassport", null);
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }
}
