package cn.lihuan.com.aaaaaaaaaaaaaaaa;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

public class TestUpload {

    public static void main(String[] args) {
        try {
            String filePath = "C:\\Users\\Dell\\Desktop\\做好的模板\\使用教程.txt";
            InputStream is = new FileInputStream(new File(filePath));
            String fileName ="aaa";
            String uploadURL = "http://localhost:8083/aaa"; //接收文件的服务器地址
            //创建HttpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(uploadURL);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();  //上传文件
            /*这是绑定上传文件接口*/
            builder.addBinaryBody("file", is, ContentType.DEFAULT_BINARY,fileName);
            //还可以设置其他参数
            builder.addTextBody("jsonObject","jsonObject");
            HttpEntity entity = builder.build();
            httpPost.setEntity(entity);
            //执行提交
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null){
                //将响应的内容转换成字符串
                String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
                System.out.println(result);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}