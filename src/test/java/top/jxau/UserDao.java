package top.jxau;

import java.util.HashMap;
import java.util.Map;

public class UserDao {


    private static Map<String, String> hashMap = new HashMap<>();

    public void init(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "alibaba");
        hashMap.put("10002", "byte-dance");
        hashMap.put("10003", "tencent");
    }

    public void destroy(){
        System.out.println("执行：destroy-method");
        System.out.println("清除 hashMap size: " + hashMap.size());
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }
}
