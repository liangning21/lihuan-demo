package cn.lihuan.com.utils;

import cn.lihuan.com.RegExpEnum;

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
    //匹配银行卡
    public static String demo3(String result) {
        StringBuilder stringBuilder = new StringBuilder();
        String regex ="[0-9]\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m=p.matcher(result);
        System.out.println("读取出所有的数字");
        while(m.find()){
            String number = m.group();
            //判断这个数字是否属于银行卡号
            Pattern pattern = Pattern.compile(RegExpEnum.BANK_CARD_RegExp.getValue());
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                stringBuilder.append(matcher.group()+",");
                System.out.println("银行卡号 = " + matcher.group());
            }
        }
        return stringBuilder.toString();
    }

    //匹配身份证
    public static String demo4(String result) {
        StringBuilder stringBuilder = new StringBuilder();
        String regex ="[0-9]\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m=p.matcher(result);
        System.out.println("读取出所有的数字");
        while(m.find()){
            String number = m.group();
            //判断这个数字是否属于身份证号
            Pattern pattern = Pattern.compile(RegExpEnum.IDENTITY_CARD_RegExp.getValue());
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                stringBuilder.append(matcher.group()+",");
                System.out.println("身份证号 = " + matcher.group());
            }
        }
        return stringBuilder.toString();
    }
    //匹配日期  yyyy-MM-dd  yyyy.MM.dd
    public static String demo5(String result){
        StringBuilder stringBuilder = new StringBuilder();
        Pattern compile = Pattern.compile(RegExpEnum.DATA_RegExp.getValue());
        Matcher matcher = compile.matcher(result);
        while (matcher.find()){
            stringBuilder.append(matcher.group()+",");
            System.out.println("日期时间为 = " + matcher.group());
        }
        return stringBuilder.toString();
    }
}
