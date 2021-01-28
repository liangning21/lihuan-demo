package cn.lihuan.com.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/18 15:35
 * @Created by Dell
 */
public class Jigoudaima {


    public static void main(String[] args) {

        String result ="76156958-4";
        Jigoudaima.acd(result);
    }
    //匹配组织机构信用代码
    public static void acd(String result){
        String regex = "[0-9A-HJ-NPQRTUWXY]{8}-[0-9A-HJ-NPQRTUWXY]";
        Pattern p=Pattern.compile(regex);
        Matcher matcher = p.matcher(result);
        if(matcher.find()){
            System.out.println("matcher.group() = " + matcher.group());
        }
    }
}

