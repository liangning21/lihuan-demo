package cn.lihuan.com.zhengze;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/14/10:30
 * @Description:
 */
public class Tests {

    public static void main(String[] args) throws IOException {

        String aaa="dSD6217855000052295094";


        System.out.println(Tests.isMobileNO(aaa));
    }
    public static boolean isMobileNO(String mobiles){
//        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
//        Pattern p = Pattern.compile(RegExpEnum.IDENTITY_CARD_RegExp.getValue());
        Pattern p = Pattern.compile("(?<=[.])^([1-9]{1})(\\d{14}|\\d{18})$");
//        Pattern p = Pattern.compile("/62\\d{11,17}/g");
//        Pattern p = Pattern.compile("(?<=合同编号.\\s)\\d*");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches()+"---");

        if (m.matches()) {
            String group = m.group();
            System.out.println("group = " + group);
        }
        return m.matches();
    }

    @Test
    public void test2(){

        String aa  = "adafa13753293141";

        //先定义一个字符串代码
        Pattern p = Pattern.compile("/\\d{11}/g");
        Matcher matcher = p.matcher(aa);
        if (matcher.matches()) {
            System.out.println("matcher.group() = " + matcher.group());
        }
    }
}
