package cn.lihuan.com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/14/14:22
 * @Description:
 */
public class RegExpUtils {
    //匹配手机号
    public static String demo2(String result) {
        StringBuilder stringBuilder = new StringBuilder();
        String regex ="1[0-9]\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m=p.matcher(result);
        System.out.println("读取出所有的数字");
        while(m.find()){
            String number = m.group();
            if(number.length() ==11){
                //判断是不是符合手机号的要求
                String regs="[1][3,5,7,8][0-9]\\d{8}$*";
                Pattern pattern = Pattern.compile(regs);
                Matcher matcher = pattern.matcher(number);
                if(matcher.find()){
                    String group = matcher.group();
                    stringBuilder.append(group+",");
                    System.out.println("符合手机号的正则表达式" + group);
                }
            }
        }
        return stringBuilder.toString();
    }


}
