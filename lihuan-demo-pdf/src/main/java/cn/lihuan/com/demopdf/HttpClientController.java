package cn.lihuan.com.demopdf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/26 15:15
 * @Created by Dell
 */
public class HttpClientController {

    private String sys_url ="http://192.168.1.113:8081/mars/api/openby/uploadFile";

    public static void main(String[] args) {


    }


    @RequestMapping("/upload")
    @ResponseBody
    @SuppressWarnings("unchecked")
    public void startVideoAnalyze(HttpServletResponse response,
                                  @RequestParam(value = "image") MultipartFile image)throws IOException {
        InputStream fileStream = image.getInputStream();
        String fileName = image.getOriginalFilename();
        String fileType = image.getContentType();
        // 发送请求：sys_url为自己的远程接口地址
        String jsonString = singleFileUploadWithParameters(sys_url, "img", fileStream, fileName, fileType, null);

    }
    /**
     * 集上传单个文件与传递参数于一体的方法
     *
     * @param actionURL  上传文件的URL地址包括URL
     * @param name       文件标识，用于服务器解析（相当于表单名）
     * @param fileStream 文件流
     * @param fileName   文件名
     * @param fileType   文件类型
     * @param parameters 跟文件一起传输的参数
     * @return
     */
    public static String singleFileUploadWithParameters(String actionURL, String name,  InputStream fileStream,
                                                        String fileName, String fileType, HashMap<String, String> parameters) {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "----WebKitFormBoundary851PD6JXXxfIPFk9";
        String response = "";
        try {
            URL url = new URL(actionURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // 发送post请求需要下面两行
            connection.setDoInput(true);
            connection.setDoOutput(true);
            // 设置请求参数
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 获取请求内容输出流
            DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
            // 开始写表单格式内容
            // 写参数
            if (parameters != null) {
                Set<String> keys = parameters.keySet();
                for (String key : keys) {
                    ds.writeBytes(twoHyphens + boundary + end);
                    ds.writeBytes("Content-Disposition: form-data; name=\"");
                    ds.write(key.getBytes());
                    ds.writeBytes("\"" + end);
                    ds.writeBytes(end);
                    ds.write(parameters.get(key).getBytes());
                    ds.writeBytes(end);
                }
            }
            // 写文件
            ds.writeBytes(twoHyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; " + "name=\"" + name + "\"; " + "filename=\"");
            // 防止中文乱码
            ds.write(fileName.getBytes());
            ds.writeBytes("\"" + end);
            ds.writeBytes("Content-Type: " + fileType + end);
            ds.writeBytes(end);
            // 根据路径读取文件
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fileStream.read(buffer)) != -1) {
                ds.write(buffer, 0, length);
            }
            ds.writeBytes(end);
            fileStream.close();
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            ds.writeBytes(end);
            ds.flush();
            try {
                // 获取URL的响应
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String s = "";
                String temp = "";
                while ((temp = reader.readLine()) != null) {
                    s += temp;
                }
                response = s;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No response get!!!");
            }
            ds.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Request failed!");
        }
        return response;
    }





}
