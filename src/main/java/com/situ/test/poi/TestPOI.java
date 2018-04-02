package com.situ.test.poi;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.situ.crm.bean.SaleChance;

public class TestPOI {

	@Test
	public void test1() throws Exception {
		
		List<SaleChance> listChance=new ArrayList<>();
		SaleChance saleChance = listChance.get(0);
		Class<? extends SaleChance> class1 = saleChance.getClass();
		Field[] fields = class1.getDeclaredFields();
		int length = fields.length;
		for (int i = 0; i < fields.length; i++) {
			
		}
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("HELLO");
		HSSFRow row = sheet.createRow(2);
		HSSFCell cell = row.createCell(2);
		cell.setCellValue("sdfsdfsdfsdfs");
		OutputStream outputStream=new FileOutputStream("E:\\poi.xls");
		workbook.write(outputStream);//将输出流写到workbook中
		
		workbook.close();
		outputStream.close();
	}
}
