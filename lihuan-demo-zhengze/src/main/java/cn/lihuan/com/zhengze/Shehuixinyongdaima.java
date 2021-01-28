package cn.lihuan.com.zhengze;

import org.apache.commons.lang.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/18 10:50
 * @Created by Dell
 */
public class Shehuixinyongdaima {

    public static void main(String[] args) {
        String aa  = "91110108351303601C";  //信用代码
        isLicense18(aa);

    }
    /**
     * 营业执照 统一社会信用代码（18位）
     * @param license
     * @return
     */
    public static boolean isLicense18(String license) {
        if(StringUtils.isEmpty(license)) {
            return false;
        }
        if(license.length() != 18) {
            return false;
        }
        String regex = "^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-90-9ABCDEFGHJKLMNPQRTUWXY])$";

        Pattern p=Pattern.compile(regex);
        Matcher matcher = p.matcher(license);

        if(matcher.find()){
            //匹配到了就输出
            System.out.println(matcher.group());
        }
        if (!license.matches(regex)) {
            return false;
        }
        String str = "0123456789ABCDEFGHJKLMNPQRTUWXY";
        int[] ws = { 1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28 };
        String[] codes = new String[2];
        codes[0] = license.substring(0, license.length() - 1);
        codes[1] = license.substring(license.length() - 1, license.length());
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += str.indexOf(codes[0].charAt(i)) * ws[i];
        }
        int c18 = 31 - (sum % 31);
        if (c18 == 31) {
            c18 = 'Y';
        } else if (c18 == 30) {
            c18 = '0';
        }
        if (str.charAt(c18) != codes[1].charAt(0)) {
            return false;
        }
        return true;
    }
}
