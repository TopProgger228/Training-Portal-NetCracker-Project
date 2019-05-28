package com.group3.basic.netcracker.backend.service.impl;

import com.group3.basic.netcracker.backend.dao.ReporterDao;
import com.group3.basic.netcracker.backend.service.ReporterService;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;

import java.util.*;


@Service
public class ReporterServiceImpl implements ReporterService {
    private final ReporterDao reporterDao;

    @Autowired
    public ReporterServiceImpl(ReporterDao reporterDao) {
        this.reporterDao = reporterDao;
    }

    public void createReportByCourse(int[] courses) {
        generateReport(reporterDao.queryReportByCourse(courses));
    }

    public void createReportByTrainer(String username) {
        generateReport(reporterDao.queryReportByTrainer(username));
    }

    public void createReportByStudent(String username) {
        generateReport(reporterDao.queryReportByStudent(username));
    }

    public void createReportByLevel(String level) {
        generateReport(reporterDao.queryReportByLevel(level));
    }

    private void generateReport(List<Map<String, Object>> data) {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Attendance report");
        //Iterate over data and write to sheet
        Set<String> keyset = data.listIterator().next().keySet();
        int rownum = 0;
        int cellnum = 0;
        Row row = sheet.createRow(rownum++);
        for (String key : keyset) {
            System.out.println((key));
            Cell cell = row.createCell(cellnum++);
            cell.setCellValue((String) key);
            sheet.autoSizeColumn(cell.getColumnIndex());
            setFont(workbook, sheet);
        }

        for (Map<String, Object> map : data) {
            Row nexRow = sheet.createRow(rownum++);
            int nexCellnum = 0;
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                Cell cell = nexRow.createCell(nexCellnum++);
                cell.setCellValue(" " + value);
                sheet.autoSizeColumn(cell.getColumnIndex());
                setFont(workbook, sheet);
            }
        }

        String userDirectoryString = System.getProperty("user.home");

        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(userDirectoryString + "\\report.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Report written successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setFont(Workbook wb, XSSFSheet sheet) {
        CellStyle style = wb.createCellStyle();//Create style
        Font font = wb.createFont();//Create font
        font.setColor(HSSFColor.GREEN.index);
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        style.setFont(font);

        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++)
            sheet.getRow(0).getCell(i).setCellStyle(style);
    }
}
