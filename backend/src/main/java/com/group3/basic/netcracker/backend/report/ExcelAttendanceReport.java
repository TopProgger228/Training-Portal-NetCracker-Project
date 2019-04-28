package com.group3.basic.netcracker.backend.report;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.group3.basic.netcracker.backend.entity.Attendance;
 
 
public class ExcelAttendanceReport extends  AbstractXlsView {

		@Override
		protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
		        // get data model which is passed by the Spring container
		        List<Attendance> listAttendance = (List<Attendance>) model.get("listAttendance");
		         
		        // create a new Excel sheet
		        Sheet sheet = workbook.createSheet("Attendance report");
		        sheet.setDefaultColumnWidth(30);
		         
		        // create style for header cells
		        CellStyle style = workbook.createCellStyle();
		        Font font = workbook.createFont();
		        font.setFontName("Arial");
		        style.setFillForegroundColor(HSSFColor.GREEN.index);
		        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		        font.setColor(HSSFColor.WHITE.index);
		        style.setFont(font);
		         
		        // create header row
		        Row header = sheet.createRow(0);
		         
		        header.createCell(0).setCellValue("MissingCount");
		        header.getCell(0).setCellStyle(style);
		         
		        header.createCell(1).setCellValue("MissingUser");
		        header.getCell(1).setCellStyle(style);
		         
		        header.createCell(2).setCellValue("Course");
		        header.getCell(2).setCellStyle(style);
		         
		        header.createCell(3).setCellValue("Reason");
		        header.getCell(3).setCellStyle(style);

		        // create data rows
		        int rowCount = 1;
		         
		        for (Attendance attendance : listAttendance) {
		            Row row = sheet.createRow(rowCount++);
		            row.createCell(0).setCellValue(attendance.getMissingCount());
		            row.createCell(1).setCellValue(attendance.getMissingUser());
		            row.createCell(2).setCellValue(attendance.getCourse());
		            row.createCell(3).setCellValue(attendance.getReason());
		        }
	}
}
