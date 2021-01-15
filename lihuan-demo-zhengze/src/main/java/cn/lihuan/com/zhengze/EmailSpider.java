package cn.lihuan.com.zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 抓取网页中的email地址
public class EmailSpider {

	public static void main(String[] args) {

		String result  = "这是什么乱七八糟79108174@qq.comdafa这是一个测试afdafafa695050698@qq.cndsdfa";
//		parse(result);
		parseEmil(result);
	}
	private static void parseEmil (String lin){
		Pattern p=Pattern.compile("[\\w]+@[\\w]+\\.[com|bainet|cn]*");
		Matcher m=p.matcher(lin);
		while(m.find()){
			System.out.println(m.group());//打印所du有zhi邮箱
		}
	}

	//匹配邮箱
	private static void parse(String lin) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(lin);
		while(m.find()) {
			// 如果是存到数据库的话就改这里
			System.out.println(m.group());
		}
	}



}
