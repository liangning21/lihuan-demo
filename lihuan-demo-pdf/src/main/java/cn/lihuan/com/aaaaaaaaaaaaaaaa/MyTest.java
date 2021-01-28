package cn.lihuan.com.aaaaaaaaaaaaaaaa;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author smile
 * @date 2020/9/30
 * @info 调用接口测试类
 */
public class MyTest {
/**
     * 调用接口
     * @param path 远程接口
     * @param obj 发送数据
     * @return 返回结果
     */
    public static String callingInterface(String path,Object obj) throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(path);

        System.out.println("JSON.toJSONString(obj) = " + JSON.toJSONString(obj));
        httpPost.setEntity(new StringEntity(JSON.toJSONString(obj),
                ContentType.create("application/json", "UTF-8")));
        //执行
        CloseableHttpResponse execute = httpClient.execute(httpPost);
        HttpEntity entity = execute.getEntity();
        String str = EntityUtils.toString(entity);
        httpPost.clone();
        httpClient.close();
        System.out.println(str);
        return str;
    }

    public static void main(String[] args) throws Exception {
//        String jsonObject="{\"元素1\":\"2021-01-15\",\"元素2\":\"2345\",\"元素3\":\"3的分割线\",\"元素4\":\"cdzv\",\"元素5\":\"2021-01-15\"}";
//        String filePath = "C:\\Users\\Dell\\Desktop\\做好的模板\\aaa.py";
//        String riu  ="http://192.168.1.113:8081/mars/api/openby/uploadFile";
//        ocrDiscern(jsonObject,filePath,riu);
    }


    /**
     * 调用接口
     * @param filePath 文件本地位置
     * @param riu 远程接口
     */
    public static void ocrDiscern(String jsonObject, String filePath, String riu) {

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        headers.setContentType(type);
        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        //设置发送文件和其它参数
        form.add("multipartFile", fileSystemResource);
        form.add("jsonObject", jsonObject);
        form.add("AppId", "2002100");
        org.springframework.http.HttpEntity<MultiValueMap<String, Object>> files = new org.springframework.http.HttpEntity<>(form, headers);
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.postForObject(riu, files, String.class);
        System.out.println(result);
    }

}
