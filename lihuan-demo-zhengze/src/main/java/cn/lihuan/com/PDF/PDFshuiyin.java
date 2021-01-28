package cn.lihuan.com.PDF;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Classname lihuan
 * @Description TODO
 * @Date 2021/1/21 18:41
 * @Created by Dell
 */
public class PDFshuiyin {

    public static void main(String[] args) throws Exception {
        aaa();
//        float opacity = 0.2f;
//        float rotation = 45f;
//        int waterMarkFontSize = 60 ;
//        int zzb = 320;
//        int hzb = 320;
//        int xAmout = 3;
//        int yAmout = 3;
//
//        FileInputStream input = new FileInputStream(new File("C:\\Users\\Dell\\Desktop\\1611222019651.pdf"));
//
//        String outputStream = "D:\\jeecg\\upload\\pdf\\WaterMark"+System.currentTimeMillis()+".pdf";
//
//        PdfReader reader = new PdfReader(input);
//        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(outputStream));
//        PdfStamper stamper = new PdfStamper(reader, output);
//        BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//        String waterMarkString = "内部使用"; //水印内容
//
//        int total = reader.getNumberOfPages() + 1;
//        PdfContentByte over;
//        // 给每一页加水印
//        for (int i = 1; i < total; i++) {
//            Rectangle pageRect = stamper.getReader().getPageSizeWithRotation(i);
//            // 计算水印每个单位步长X,Y
//            float x = pageRect.getWidth() / xAmout;
//            float y = pageRect.getHeight() / yAmout;
//
//            over = stamper.getOverContent(i);
//            PdfGState gs = new PdfGState();
//            gs.setFillOpacity(0.2f);
//            over = stamper.getOverContent(i);
//            over.setGState(gs);
//            over.saveState();
//            over.beginText();
//            over.setColorFill(BaseColor.BLUE);
//            over.setFontAndSize(baseFont, waterMarkFontSize);
//            //平铺
//            String layout = "1";  //布局模式
//            if("1".equals(layout)){
//                for (int n = 0; n < xAmout+1; n++) {
//                    for (int m = 0; m < yAmout+1; m++) {
//                        over.showTextAligned(Element.ALIGN_CENTER, waterMarkString, x * n, y * m, rotation);
//                    }
//                }
//            }
//            over.endText();
//        }
//        stamper.close();
//        reader.close();
//        output.close();
    }



    public static void aaa () throws IOException, DocumentException {

        FileInputStream inputFile = new FileInputStream(new File("C:\\Users\\Dell\\Desktop\\询比选拔文件-封面-1.pdf"));


        String outputStream = "D:\\jeecg\\upload\\pdf\\WaterMark"+System.currentTimeMillis()+".pdf";
        PdfReader reader = new PdfReader(inputFile);

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(
                outputStream));

//        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",   BaseFont.EMBEDDED);
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        Rectangle pageRect = null;
        PdfGState gs = new PdfGState();
        gs.setFillOpacity(0.3f);
        gs.setStrokeOpacity(0.4f);
        int total = reader.getNumberOfPages() + 1;

        JLabel label = new JLabel();
        FontMetrics metrics;
        int textH = 0;
        int textW = 0;
        label.setText("aaaaaaaaaaaaaaaaaa");
        metrics = label.getFontMetrics(label.getFont());
        textH = metrics.getHeight();
        textW = metrics.stringWidth(label.getText());

        PdfContentByte under;
        for (int i = 1; i < total; i++) {
            under = stamper.getOverContent(i);// 在内容上方加水印
            //content = stamper.getUnderContent(i);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            // content.setGState(gs);
            under.beginText();
            under.setFontAndSize(base, 50);
            under.setTextMatrix(70, 200);
            under.showTextAligned(Element.ALIGN_CENTER, "测试水印！", 300,350, 55);
            under.endText();
        }

        //一定不要忘记关闭流
        stamper.close();
        reader.close();
    }


}
