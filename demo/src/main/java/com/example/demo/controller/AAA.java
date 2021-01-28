package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/26 16:13
 * @Created by Dell
 */
@RestController
public class AAA {


    @RequestMapping("/aaa")
    public void updal(@RequestParam("file") MultipartFile file,
                      String jsonObject, String appid) throws IOException {

        if (file.isEmpty()) {
            System.out.println("为空");
        }
        InputStream inputStream = file.getInputStream();
        byte[] b = new byte[1024 * 10];
//       　　　　　　 定义长度为0；
        int len = 0;
        while ((len = inputStream.read(b)) != -1) {
            System.out.println(new String(b,"UTF-8"));
        }
    }

}
