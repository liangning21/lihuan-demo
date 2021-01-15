package cn.lihuan.com.zhengze;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    public static String checkNum(String num) {
        if (num == null || num.length() == 0) {
            return "";
        }
        Pattern pattern = Pattern.compile("(?<!\\d)\\d{11}");
        Matcher matcher = pattern.matcher(num);
        StringBuffer bf = new StringBuffer(64);
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
            System.out.println(matcher.group());
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }

    public static void main(String args[]) throws IOException {
        TestRegex.getTelnum("aaaaaaaaaaalihuan17600423831111dafaafad");
    }


    public static String getTelnum(String sParam) {
        if (sParam == null || sParam.length() <= 0)
            return null;
        Pattern pattern = Pattern.compile("(1|861)([0-9])\\d{9}");
//        Pattern aaa = Pattern.compile("\\d{11}$*");
//        Pattern pattern = Pattern.compile("[1][3,5,7,8][0-9]\\d{8}$*");

        Matcher matcher = pattern.matcher(sParam);
        StringBuffer bf = new StringBuffer();
        while (matcher.find()) {
            bf.append(matcher.group()).append(",");
            System.out.println("matcher.group() = " + matcher.group());
        }
        int len = bf.length();
        if (len > 0) {
            bf.deleteCharAt(len - 1);
        }
        return bf.toString();
    }
}
