package cn.lihuan.com.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/14/14:48
 * @Description:
 */
public class time {
    public static void main(String[] args) {
        String aa = "aadafa2020-19-20adafad2020-12-2";
        String pattern ="\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}";

        String reg_yyyy_MM_dd = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})" +
                "-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))" +
                "|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(aa);
        while (matcher.find()){
            System.out.println("matcher.group() = " + matcher.group());
        }
    }
}
