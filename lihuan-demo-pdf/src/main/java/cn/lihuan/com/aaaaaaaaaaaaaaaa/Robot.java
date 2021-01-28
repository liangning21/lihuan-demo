package cn.lihuan.com.aaaaaaaaaaaaaaaa;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class Robot {

    public static void main(String[] args) throws Exception {
        String path = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=8e606595-c530-4be9-9e2b-6ab567ce79d2";
        Robot.demo(path, Robot.jsonMap());
    }
    public static void demo(String url, String data) throws ClientProtocolException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建POST请求对象
        HttpPost httpPost = new HttpPost(url);
        //添加请求头信息
        httpPost.addHeader("Content-Type", "application/json");
        //设置请求参数
        StringEntity stringEntity = new StringEntity(data, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);//设置请求主体

        HttpResponse httpResponse = httpClient.execute(httpPost);//发送请求
        HttpEntity httpEntity = httpResponse.getEntity();//获取请求返回体
        String backResult = EntityUtils.toString(httpEntity, "UTF-8");//请求返回结果
        System.out.print("收到的返回数据》》》》》》》"+backResult);
        //释放资源
        if (httpResponse != null) {
            try {
                EntityUtils.consume(httpResponse.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String jsonMap(){
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("msgtype", "text");
        Map<String, Object> map1 = new LinkedHashMap<String, Object>();
        map1.put("content", "测试数据");
        map.put("text", map1);
        String jsonMap = JSON.toJSON(map).toString();
        return jsonMap;
    }
}


