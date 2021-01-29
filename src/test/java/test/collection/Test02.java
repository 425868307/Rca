package test.collection;

import com.alibaba.fastjson.JSON;
import test.pojo.Company;
import test.pojo.Consumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/9/27.
 *
 * @author
 */
public class Test02 {

    public static void main(String[] args) {
        List<Consumer> list = new ArrayList<>();
        Consumer csm = null;
        csm = new Consumer();
        csm.setName("张三");
        csm.setEnName("zhangsan");
        csm.setAddress("上海市");
        csm.setPhone("12300908849");
        Company company = new Company();
        company.setName("上海某公司");
        company.setAddress("上海浦东新区");
        company.setPhone("10933329");
        csm.setCompany(company);

        list.add(csm);

        csm = new Consumer();
        csm.setName("李四");
        csm.setEnName("lisi");
        csm.setAddress("北京事");
        csm.setAge("87");
        company = new Company();
        company.setName("北京某公司");
        company.setAddress("北京市朝阳区");
        company.setPhone("12445453");
        csm.setCompany(company);
        list.add(csm);

        System.out.println(JSON.toJSONString(list));
    }
}
