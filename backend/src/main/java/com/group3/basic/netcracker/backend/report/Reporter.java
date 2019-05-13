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

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.group3.basic.netcracker.backend.report.ReportDataSource.getDataSource;

@Transactional
@Repository
@Service
public class Reporter {
    private static DataSource dataSource;

    public List<Map<String, Object>> queryReportByCourse(int[] courses) throws SQLException {
        dataSource = getDataSource();
        // JdbcTemplate template = new JdbcTemplate(dataSource); // constructor
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
        String g = Arrays.toString(courses).substring(1).replaceFirst("]", "").replace("[", "");
        String sql = "select count(lm.id) as missing, \n" +
                "u.username as student, lm.reason as reason \n" +
                "      from \"LessonMissing\" lm join \"User\" u on lm.user_id = u.id \n" +
                "       left join \"Group\" g on u.id = g.user_id \n" +
                "       join \"Course\" c on c.id = g.course_id \n" +
                "         where c.id in (" + g + ") \n" +
                "         group by u.username, lm.reason, c.name";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println("------Missing students list-------");
        for (Map<String, Object> row : list) {
            System.out.println(row.get("missing") + " " + row.get("student") + " " + row.get("reason"));
       } return list;
    }
    public void createReportByCourse(int[] courses) throws SQLException {
        generateReport(queryReportByCourse(courses));
    }

    public void generateReport(List<Map<String, Object>> mapList) throws SQLException {
        //Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        //Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Attendance report");
        //This data needs to be written (Object[])
        List<Map<String, Object>> data = mapList;

        //Iterate over data and write to sheet
            Set<String> keyset = data.listIterator().next().keySet();
            int rownum = 0;
            for (String key : keyset) {
                Row row = sheet.createRow(rownum++);
                int n = 0;

                Object[] objArr = new Object[n];
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String)
                        cell.setCellValue((String) obj);
                    else if (obj instanceof Integer)
                        cell.setCellValue((Integer) obj);
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
    }
}
