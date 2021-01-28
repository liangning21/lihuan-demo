package cn.lihuan.com.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/19 16:30
 * @Created by Dell
 */
public class jine {
    public static void main(String[] args) {
//        isNumber("666.00元");
//        isNumberS("出借设备总价： ￥6,500元（大写：人民币陆仟伍佰元整） ");
        isNumberS("乙方应当在向甲方提出借设备前三日内，向甲方支付出借设备押金 2,000 元，甲方向乙方开具相等金额收据");
    }


    //金额验证
    public static boolean isNumber(String str){
        Pattern pattern= Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str);
        if(match.matches()==false){
            return false;
        }else{
            System.out.println("match.group() = " + match.group());
            return true;
        }
    }

    public static void  isNumberS(String str){
        String rest ="(([1-9]{1}\\d*)|([0]{1}))(,(\\d){0,4})?(\\s*)(?=元)";
//        String rest ="(\\d)*(?=元)";
        Pattern compile = Pattern.compile(rest);
        Matcher matcher = compile.matcher(str);
        if(matcher.find()){
            System.out.println("matcher.group() = " + matcher.group());
        }

        String rests ="(?<=大写(\\.\\s)\\*)\\.*";
        Pattern compiles = Pattern.compile(rests);
        Matcher matchers = compiles.matcher(str);

        if(matchers.find()){
            System.out.println("matchers.group() = " + matchers.group());
        }
    }

}
