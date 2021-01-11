package cn.lihuan.com;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 李欢
 * @Date: 2021/01/07/13:55
 * @Description:
 */
public class ZhengZeDemo {

    /**
     * 正则表达式读取文件指定内容
     */
    /**
     * 获取日志文件中的时间
     * @throws FileNotFoundException
     */
    public static void main(String[]args) throws FileNotFoundException {
        {
            //匹配次数
            int matchTime = 0;
            //存匹配上的字符串
            List<String> strs = new ArrayList<>();
            try
            {
                //编码格式
                String encoding = "UTF-8";
                //文件路径
//                File file = new File("C:\\Users\\Dell\\Desktop\\aaa.txt");
                File file = new File("C:\\Users\\Dell\\Desktop\\招标内容.txt");
                if (file.isFile() && file.exists()){ // 判断文件是否存在
                    //输入流
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), encoding);// 考虑到编码格
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    //读取一行
                    while ((lineTxt = bufferedReader.readLine()) != null)
                    {
                        //正则表达式
                        matchTime = getMatchTime(matchTime, strs, lineTxt);
                    }
                    read.close();
                }
                else
                {
                    System.out.println("找不到指定的文件");
                }
            }
            catch (Exception e)
            {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }
            List<Integer> nums = getSum(strs);
            double avg = getAvgTime(nums,matchTime);
            System.out.print(avg);
        }
    }

    private static int getMatchTime(int matchTime, List<String> strs, String lineTxt) {
//        Pattern p = Pattern.compile("[0-9]*ms$");
//        Pattern p = Pattern.compile("合同编号");
//        Pattern p = Pattern.compile("(?<=合同编号.\\s)\\d*");
        Pattern p = Pattern.compile("(?<=劳务分包人.\\s)\\S*");
//        Pattern p = Pattern.compile("(\\S*)施工$");
        Matcher m = p.matcher(lineTxt);
        boolean result = m.find();
        String find_result = null;
        if (result)
        {
            matchTime++;
            find_result = m.group(0);
            strs.add(find_result);
        }
        return matchTime;
    }

    private static List<Integer> getSum(List<String> strs) {
        List<Integer> nums = new ArrayList<>();
        for(String str : strs){
            String s = str.replace("ms","");
            Integer a = Integer.valueOf(s);
            nums.add(a);
        }
        return nums;
    }

    private static double getAvgTime(List<Integer> nums, int matchTime) {
        double sum = 0;
        double avg ;
        for(Integer num : nums){
            sum+=num;
        }
        avg = sum/matchTime;
        return avg;
    }


}
