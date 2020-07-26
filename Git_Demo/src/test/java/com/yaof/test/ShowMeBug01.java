package com.yaof.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShowMeBug01 {

	public static void main(String[] args) {
        System.out.println("阅读getCondition方法，找出其中的错误点并修复");
        System.out.println("要求不能使用第三方包，只能用JDK中的方法");
        System.out.println("要求对修复之后的方法进行测试，并打印结果");
        
        Map<String, Object> searchParams = new LinkedHashMap<>();
        searchParams.put("startTime", System.currentTimeMillis());
        searchParams.put("DIC_TYPE", "1,2,4");
        searchParams.put("ITEM_ID", "itemId123456");
        searchParams.put("ORG_ID", "orgId123456");
        searchParams.put("COMPANY_ID", "companyId123456");
        
        List<Object> params = new ArrayList<>();
        String condition = getCondition(searchParams, params);
        System.out.println("condition:"+condition);
        System.out.print("params: ");
        for (int i = 0; i < params.size(); i++) {
        	System.out.print(params.get(i)+", ");
		}
    }

	public static String getCondition(Map<String, Object> searchParams, List<Object> values) {
        StringBuilder sb = new StringBuilder();
        for (String s : searchParams.keySet()) {
            if ("startTime".equals(s)) {
                if (getString(searchParams.get("startTime")).length() != 13) {
                    throw new RuntimeException("请使用时间戳格式的时间进行查询！");
                } else {
                    sb.append("UPDATE_TIME >= ? AND ");
                    values.add(paseDateFromLongStr(getString(searchParams.get(s))));
                }
            } else if ("DIC_TYPE".equals(s)) {
                String[] split = getString(searchParams.get(s)).split(",");
                if (split.length == 3) {
                	sb.append(" DIC_TYPE IN (?,?,?) AND ");
                    values.addAll(Arrays.asList(split));
                } else if (split.length != 3) {
                    // TODO 处理非3的情况,根据业务，如果参数总是3个，当不是3个的时候，不要这个条件；如果这个不确定，可以遍历进行拼接
                }
            } else if ("ITEM_ID".equals(s)) {
                sb.append("ITEM_ID = ? AND ");
                values.add(searchParams.get(s));
            } else if ("ORG_ID".equals(s) || "COMPANY_ID".equals(s)) {
                sb.append(s + " = ? AND ");
                values.add(searchParams.get(s));
            }

        }
        if (!searchParams.keySet().contains("DIC_CODE")) {
            sb.append(" DIC_CODE IN ('JOB_DUTY','GROUP_JOB_DUTY','USER_POST') AND ");
        } else {
        	String[] split = getString(searchParams.get("DIC_CODE")).split(",");
        	sb.append(" DIC_CODE IN (?");
        	for (int i = 1; i < split.length; i++) {
        		sb.append(",?");
        		values.add(split[i]);
			}
        	sb.append(") and");
        }
        return sb.substring(0, sb.length()-4).toString();

    }
	
	private static String getString(Object obj){
		if(obj != null) {
			return obj.toString();
		}
		return "";
	}
	
	private static Date paseDateFromLongStr(String dateStr) {
        Date date = null;
        try {
            Long timestamp = Long.parseLong(dateStr);
            date = new Timestamp(timestamp);
        } catch (Exception e) {
            throw new RuntimeException("请检查日期格式(需使用时间戳格式的时间)");
        }
        return date;
    }
}
