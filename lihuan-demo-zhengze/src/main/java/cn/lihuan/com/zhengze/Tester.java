package cn.lihuan.com.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {
    public static void main (String[] args) {
        String str = "sdfsdfsd23232323@baidu.com2323sdf@sdfs.comddd打发";
        String reg = "([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})";
        Pattern pattern = Pattern.compile (reg);
        Matcher matcher = pattern.matcher (str);
        while (matcher.find()) {
            System.out.println (matcher.group());
        }
    }
}