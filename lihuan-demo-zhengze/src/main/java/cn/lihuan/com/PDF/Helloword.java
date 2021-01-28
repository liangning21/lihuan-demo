package cn.lihuan.com.PDF;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/21 9:54
 * @Created by Dell
 */
public class Helloword {

    public static void main(String[] args) throws Exception {
        InputStream filePath =null;
        FileInputStream fileInputStream =null;
        try {
            File file1 = new File("C:\\Users\\Dell\\Desktop\\询比选拔文件-封面-1.pdf");
            fileInputStream = new FileInputStream(file1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        filePath=fileInputStream;

        String savePath  ="C:\\Users\\Dell\\Desktop\\"+System.currentTimeMillis()+".pdf";
        PdfReader reader = new PdfReader(filePath);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(savePath));
        int a = PdfWriter.ALLOW_PRINTING +PdfWriter.ALLOW_COPY+PdfWriter.ALLOW_FILL_IN;
        a +=PdfWriter.ALLOW_MODIFY_ANNOTATIONS+PdfWriter.ALLOW_SCREENREADERS;
        a+= PdfWriter.ALLOW_MODIFY_CONTENTS+PdfWriter.ALLOW_ASSEMBLY+2;
        //访问者密码，拥有者密码(权限密码让pdf文件无法被修改)，访问者权限，加密方式。
//        stamper.setEncryption("123".getBytes(),null,
//                PdfWriter.ALLOW_PRINTING|PdfWriter.ALLOW_COPY|PdfWriter.ALLOW_FILL_IN|
//                PdfWriter.ALLOW_MODIFY_ANNOTATIONS|PdfWriter.ALLOW_SCREENREADERS|
//                        PdfWriter.ALLOW_MODIFY_CONTENTS+PdfWriter.ALLOW_ASSEMBLY,
//                PdfWriter.STANDARD_ENCRYPTION_128);

//        PdfWriter.ALLOW_PRINTING 打印   PdfWriter.ALLOW_COPY 复 制   PdfWriter.ALLOW_MODIFY_ANNOTATIONS 注释 表单
//v       PdfWriter.ALLOW_ASSEMBLY 插入和删除(文档组合)
        stamper.setEncryption(null,"123".getBytes(),PdfWriter.ALLOW_MODIFY_ANNOTATIONS,
                PdfWriter.STANDARD_ENCRYPTION_128);
        stamper.close();
        reader.close();
    }



}
