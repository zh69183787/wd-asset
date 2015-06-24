package com.wonders.utils;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

public class ExcelUtil {

    private HSSFCellStyle numbericCss;
    private HSSFCellStyle defaultCss;
    private HSSFCellStyle titleCss;
	/**
	 * 将数据导出Excel
	 * @param list 导出数据的list集合
	 * @param name 导出的表的名字
	 * @param title 第一行标题行
	 * @param response 输出流
	 */
	public void exportExcel(List list, String name,
			String[] title, HttpServletResponse response){
		
		Integer rowNumber = list.size(); 
		  
        try {  
        	response.setContentType("application/x-download");
        	response.setHeader("Content-Disposition", "attachment;filename="+new String(name.getBytes("GBK"), "ISO8859-1"));
            // 创建新的Excel 工作簿  
            HSSFWorkbook workbook = new HSSFWorkbook();  
  
            HSSFSheet sheet = workbook.createSheet();
            sheet.setDefaultColumnWidth(20);
            // 创建第一行并设置标题
            HSSFRow titleRow = sheet.createRow(0);  
            for(int i=0; i<title.length; i++){
                addTitle(titleRow,workbook,title[i],i);
            }
            // 创建单元格并为单元格赋值
            for(int i=0; i<rowNumber; i++){
            	Object[] obj = (Object[]) list.get(i);
            	HSSFRow row = sheet.createRow(i+1);
            	for(int j=0; j<obj.length; j++){
            		HSSFCell cell = row.createCell(j);
            		cell.setCellValue(obj[j]==null ? "" : obj[j].toString());
                    if(obj[j] instanceof Number)
                    cell.setCellStyle(getNumbericCellStyle(workbook));
                    else
                        cell.setCellStyle(getDefaultCellStyle(workbook));
            	}
            }
              
            OutputStream out = response.getOutputStream();
            workbook.write(out);  
  
            out.flush();  
            out.close();  
  
        } catch (Exception e) {  
            System.out.println("Exception：" + e);  
        }  
	}


    private void addTitle(HSSFRow row,HSSFWorkbook workbook, String title,int cellNum) {
        row.createCell(cellNum).setCellValue(title);
        row.getCell(cellNum).setCellStyle(getTitleStyle(workbook));
        row.setHeight((short) 500);
    }

    private HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {

        if (titleCss == null) {
            titleCss = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9);
            font.setFontName("宋体");
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            titleCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            titleCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            titleCss.setFont(font); //调用字体样式对象

            titleCss.setWrapText(true);
        }


        return titleCss;
    }

    private HSSFCellStyle getNumbericCellStyle(HSSFWorkbook workbook) {
        if (numbericCss == null) {
            numbericCss = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9);
            font.setFontName("宋体");
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight((short) 0.8);
            numbericCss.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
            numbericCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            numbericCss.setFont(font); //调用字体样式对象

            numbericCss.setWrapText(true);
            HSSFDataFormat format = workbook.createDataFormat();
            numbericCss.setDataFormat(format.getFormat("#,##0.00"));
        }


        return numbericCss;

    }

    private HSSFCellStyle getDefaultCellStyle(HSSFWorkbook workbook) {

        if (defaultCss == null) {
            defaultCss = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setFontHeightInPoints((short) 9);
            font.setFontName("宋体");
            font.setColor(HSSFColor.BLACK.index);
            font.setBoldweight((short) 0.8);
            defaultCss.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            defaultCss.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
            defaultCss.setFont(font); //调用字体样式对象

            defaultCss.setWrapText(true);
        }


        return defaultCss;
    }

}
