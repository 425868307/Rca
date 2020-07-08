package com.yaof.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.util.Base64Decoder;

public class Test01 {

	private static XSSFSheet sheet;

	public static void main(String[] args) throws IOException {
		String aa = "fkewpofjkpwoefewp";
		System.out.println("初始："+aa);
		System.out.println("初始bytes长度："+aa.getBytes().length);
		
		Encoder encoder = Base64.getEncoder();
		byte[] encodeAa = encoder.encode(aa.getBytes());
		System.out.println("jdk decode:" + new String(encodeAa));
		
		byte[] decode = Base64Decoder.decode(aa.getBytes(), 0, aa.getBytes().length);
		System.out.println("netty decode:" + new String(decode));
		
		InputStream is = new FileInputStream("C:\\Users\\42586\\Desktop\\新建 Microsoft Excel 工作表.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(is);
		sheet = wb.getSheetAt(0);
		System.out.println(sheet.getRow(6).getCell(0).getStringCellValue());
		
	}

}
