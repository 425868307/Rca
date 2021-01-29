//package test.io;
//
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Field;
//
///**
// * @Author yaof
// * @Date 2020-11-09
// */
//public class Test02 {
//
//	public static void main(String[] args) {
//		HSSFWorkbook hwb = new HSSFWorkbook();
//		HSSFSheet sheet2 = hwb.createSheet();
//
//		Class<CMSProductEntity> clazz = CMSProductEntity.class;
//		Field[] fields = clazz.getDeclaredFields();
//		int row = 1;
//		for (Field field: fields) {
//			HSSFRow nowRow = sheet2.createRow(row++);
//			nowRow.createCell(0).setCellValue(field.getName());
//			nowRow.createCell(1).setCellValue(field.getType().getTypeName());
//
//			Annotation[] annotations = field.getAnnotations();
//			for (Annotation anno : annotations) {
//				if(anno instanceof org.springframework.data.mongodb.core.mapping.Field) {
//					nowRow.createCell(3).setCellValue(((org.springframework.data.mongodb.core.mapping.Field) anno).value());
//				}
//			}
//
//		}
//
//
//		File file = new File("C:\\Users\\Administrator\\Desktop\\mongo-mysql.xls");
//		FileOutputStream fileOut = null;
//		try {
//			fileOut = new FileOutputStream(file);
//			hwb.write(fileOut);
//			fileOut.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
