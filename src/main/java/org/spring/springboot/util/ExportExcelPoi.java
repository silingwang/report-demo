package org.spring.springboot.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExportExcelPoi {
    /*
     * 参数：
     * title：sheet页名称
     * headers：标题栏数组，支持双行标题
     * dataset：具体要在Excel中显示的数据链表
     * out：输出路径
     */
    public void exportExcel(String title, String[] headers, List<List<String>> dataset, OutputStream out) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
//        // 生成并设置另一个样式
//        HSSFCellStyle style2 = workbook.createCellStyle();
//        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
//        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        // 生成另一个字体
//        HSSFFont font2 = workbook.createFont();
//        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
//        // 把字体应用到当前的样式
//        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
                0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("这里是设置作者");
//         headers= new String[]{"分类", "初期金额", "本期入库:入库汇总,其他汇总", "本期出库:出库汇总,其他出库", "期末总金额"};
        // 产生表格标题行
        CellRangeAddress topTitleRegion = new CellRangeAddress(0, 0, 0, 10);
        int i1 = sheet.addMergedRegion(topTitleRegion);
        //创建标题
        HSSFCellStyle startStyle = workbook.createCellStyle();
        startStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCell cell3 = sheet.createRow(0).createCell(0);
        cell3.setCellValue("武汉大学口腔医院结存表");
        cell3.setCellStyle(startStyle);
        //副标题
        startStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFCell cell4 = sheet.createRow(1).createCell(0);
        cell4.setCellValue("会计月:2018年01月,库房: 保健科室");
        cell4.setCellStyle(startStyle);

//        sheet.createRow(0).createCell(0).setCellValue("武汉大学口腔医院结存表");
        HSSFRow row = sheet.createRow(2);
        HSSFRow row2 = sheet.createRow(3);
        for (short i = 0, n = 0; i < headers.length; i++) {//i是headers的索引，n是Excel的索引
            HSSFCell cell1 = row.createCell(n);
            cell1.setCellStyle(style);
            HSSFRichTextString text = null;
            if (headers[i].contains(":")) {//双标题
                String[] temp = headers[i].split(":");
                text = new HSSFRichTextString(temp[0]);
                String[] tempSec = temp[1].split(",");
                sheet.addMergedRegion(new Region(0, n, 0, (short) (n + tempSec.length - 1)));
                short tempI = n;
                for (int j = 0; j < tempSec.length - 1; j++) {
                    HSSFCell cellT = row.createCell(++tempI);
                    cellT.setCellStyle(style);
                }
                for (int j = 0; j < tempSec.length; j++) {
                    HSSFCell cell2 = row2.createCell(n++);
                    cell2.setCellStyle(style);
                    cell2.setCellValue(new HSSFRichTextString(tempSec[j]));
                }
            } else {//单标题
                HSSFCell cell2 = row2.createCell(n);
                cell2.setCellStyle(style);
                text = new HSSFRichTextString(headers[i]);
                sheet.addMergedRegion(new Region(0, n, 1, n));
                n++;
            }
            cell1.setCellValue(text);
        }
        // 遍历集合数据，产生数据行
        for (int i = 0, index = 4; i < dataset.size(); i++, index++) {
            row = sheet.createRow(index);
            for (int j = 0; j < ((ArrayList) dataset.get(i)).size(); j++) {
                HSSFCell cell = row.createCell((short) j);
                cell.setCellStyle(style);
                HSSFRichTextString richString = new HSSFRichTextString(dataset.get(i).get(j));
                cell.setCellValue(richString);
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ExportExcelPoi eep = new ExportExcelPoi();
//        String[] titles = {"标题一", "标题二", "标题三:3.1,3.2", "标题四:4.1,4.2", "标题五", "标题六", "标题七", "标题八"};
////        String[] titles = {"分类", "初期金额", "本期入库:入库汇总,其他汇总", "本期出库:出库汇总,其他出库", "期末总金额"};
//        String[] titles2 = {"分类", "初期金额", "本期入库:入库汇总,其他汇总", "本期出库:出库汇总,其他出库", "期末总金额"};
//        List<List<String>> dataset = new ArrayList<List<String>>();//excel要显示的数据，从数据库中查出。
//        List<String> content = new ArrayList<String>();
//        content.add("我是要显示的文字");
//        content.add("我是要显示的文字");
//        content.add("我是要显示的文字");
//        content.add("我是要显示的文字");
//        content.add("我是要显示的文字");
//        content.add("555");
//        content.add("555");
//        content.add("555");
//        content.add("555");
//        content.add("555");
//        dataset.add(content);
//        dataset.add(content);
//        dataset.add(content);
//        dataset.add(content);
//        try {
//            OutputStream out = new FileOutputStream("D://outExcel.xls");
//            eep.exportExcel("第一个sheet页", titles, dataset, out);
//            OutputStream out2 = new FileOutputStream("D://结存.xls");
//            eep.exportExcel("第一个sheet页", titles2, dataset, out2);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}