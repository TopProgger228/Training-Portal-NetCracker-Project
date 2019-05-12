package com.group3.basic.netcracker.backend.report;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Transactional
@Repository
@Service
public class Reporter {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> reportByCourse() throws SQLException {
        String sql = "select count(lm.id) as missing, u.username as student, lm.reason as reason \n" +
                "from \"LessonMissing\" lm join \"User\" u on lm.user_id = u.id\n" +
                "group by u.username, lm.reason";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        System.out.println("------Missing students list-------");
        for (Map<String, Object> row : list) {
            System.out.println(row.get("missing") + " " + row.get("student") + " " + row.get("reason"));
       } return list;
    }
    public void generateReport(List<Map<String, Object>> mapList) throws SQLException {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Attendance");
        //This data needs to be written (Object[])
        List<Map<String, Object>> data = mapList;


        //Iterate over data and write to sheet
        while (data.listIterator().hasNext()) {
        Set<String> keyset = data.listIterator().next().keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            int n = 0;
            while (data.listIterator().next().entrySet().iterator().hasNext()) {
                n++;
            }
            Object [] objArr = new Object[n];
            for ( int i = 0; i < n; i++) {
                objArr[n] = data.listIterator().next().entrySet().iterator().next().getValue();
            }
            int cellnum = 0;
            for (Object obj : objArr)
            {
                Cell cell = row.createCell(cellnum++);
                if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("D:\\report.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("Report written successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } }
}
