package cn.lihuan.com;

import org.apache.commons.lang.StringUtils;

/**
 * @Auther: 李欢
 * @Date: 2021/01/12/17:14
 * @Description:
 * 常用正则表达式
 */
public enum RegExpEnum {

    //身份证号
    IDENTITY_CARD_RegExp("身份证号","(^[1-9]\\\\d{5}(18|19|20)\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}[0-9Xx]$)|\" +\n" +
            "                \"(^[1-9]\\\\d{5}\\\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\\\d{3}$)"),
    //银行卡
    BANK_CARD_RegExp("银行卡","^([1-9]{1})(\\d{14}|\\d{18})$"),

    //电话号码
    PHONE_NUMBER_RegExp("手机号","[1][3,5,7,8][0-9]\\d{8}$*"),

    //日期  yyyy-MM-dd  yyyy.MM.dd
    DATA_RegExp("日期","\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}"),

    //邮箱
    EMIAL_RegExp("邮箱","[\\w]+@[\\w]+\\.[com|bainet|cn]*");

    private String name;
    private String value;

    RegExpEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public static RegExpEnum getEnum(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        for (RegExpEnum val : values()) {
            if (val.getName().equals(name)) {
                return val;
            }
        }
        return null;
    }
}
