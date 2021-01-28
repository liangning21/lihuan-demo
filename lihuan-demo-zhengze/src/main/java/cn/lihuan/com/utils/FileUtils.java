package cn.lihuan.com.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/18 10:16
 * @Created by Dell
 */
public class FileUtils {

    private String filePath ="C:\\Users\\Dell\\Desktop\\请求参数.txt";

    public static void main(String[] args) throws IOException {
        FileUtils fileUtils =new FileUtils();
        fileUtils.readFile();
    }
    //读取所有的内容
    public void readFile() throws IOException {
        List<String> list = org.apache.commons.io.FileUtils.readLines(new File(filePath), "UTF-8");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            //正则匹配需要的内容
            System.out.println("s = " + s);
        }
    }



}
