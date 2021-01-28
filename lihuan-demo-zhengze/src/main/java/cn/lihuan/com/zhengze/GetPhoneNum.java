package cn.lihuan.com.zhengze;

import cn.lihuan.com.utils.RegExpEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetPhoneNum {

    public static void main(String[] args) {
//            demo2();
//        validateChineseName("李欢打发");
        demo3();
    }
    //这个用于匹配符合手机号
    public static void demo2() {
        String s="125这个不是手机号189888888882222,曾经用过15633333333";
        String regex ="1[0-9]\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m=p.matcher(s);
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
                    System.out.println("符合手机号的正则表达式" + group);
                }
            }
        }
    }
    //匹配银行卡
    public static void demo3() {
        String s="125这个不是银行号6217855000052295094,曾经用过15633333333";
        String regex ="[0-9]\\d*";
        Pattern p = Pattern.compile(regex);
        Matcher m=p.matcher(s);
        System.out.println("读取出所有的数字");
        while(m.find()){
            String number = m.group();
            //判断这个数字是否属于银行卡号
            Pattern pattern = Pattern.compile(RegExpEnum.BANK_CARD_RegExp.getValue());
            Matcher matcher = pattern.matcher(number);
            if (matcher.find()) {
                System.out.println("matcher.group() = " + matcher.group());
            }
        }
    }

    public static void validateChineseName(String name) {
        String regEx = "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*";

        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(name);
        boolean isMatch = matcher.matches();
        System.out.println(matcher.group());;
    }

}
