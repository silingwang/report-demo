package org.spring.springboot.web;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.springboot.reflect.BeanUtils;
import org.spring.springboot.scheduled.BeanUtilTest;
import org.spring.springboot.util.BaseResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;
import java.util.Optional;

/**
 * Created by nice on 2017/10/27.
 */
@RestController
@Slf4j
@Scope("prototype")
@AllArgsConstructor
@RequestMapping("/cache")
public class CacheController implements Serializable {

    private BeanUtilTest beanUtilTest;


    /**
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/test")
    @ResponseBody
    public String aop(String request) {
        return beanUtilTest.simulateCache();
    }

    /**
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/put")
    @ResponseBody
    public BaseResponse put(@RequestParam("put") String put) {
        return beanUtilTest.putString(put);
    }
    /**
     * 用程序模拟缓存 demo
     */
    @RequestMapping(value = "/getMap")
    @ResponseBody
    public Map getMap(String put) {
        return beanUtilTest.getMap();
    }
    @RequestMapping(value = "/writeImage")
    @ResponseBody
    public void writeImage(HttpServletRequest request,
                           HttpServletResponse response) throws BadElementException, IOException {
        BarcodeQRCode barcodeQRCode = new BarcodeQRCode("http://memorynotfound.com", 1000, 1000, null);
        Image codeQrImage = barcodeQRCode.getImage();
        codeQrImage.scaleAbsolute(100, 100);
        byte[] bytes = ObjectToByte(codeQrImage);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }

    public  byte[] objectToBytes(Object obj){
        byte[] bytes = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream sOut;
        try {
            sOut = new ObjectOutputStream(out);
            sOut.writeObject(obj);
            sOut.flush();
            OutputStream outputStream=sOut;
            bytes= out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    public byte[] ObjectToByte(Object obj) {
        byte[] bytes=new byte[10240];
        try {
            // object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);
            bytes = bo.toByteArray();
            bo.close();
            oo.close();
        } catch (Exception e) {
            System.out.println("translation" + e.getMessage());
            e.printStackTrace();
        }
        return (bytes);
    }
    @RequestMapping(value = "/getMethod")
    public Map<String, Object> test(){
        Map<String, Object> method = BeanUtils.getMethod();
        return method;
    }
}
