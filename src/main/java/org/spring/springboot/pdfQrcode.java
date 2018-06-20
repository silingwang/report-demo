package org.spring.springboot;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by nice on 2018/6/20.
 */
public class pdfQrcode {
    public static void main(String... args) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new
//pdf输出地址
                FileOutputStream("E:\\github\\report-demo\\src\\main\\resources\\scanner.pdf"));

        document.open();
        PdfContentByte cb = writer.getDirectContent();

        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("http://memorynotfound.com", 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);

        document.add(codeQrImage);

        document.close();
    }
}
