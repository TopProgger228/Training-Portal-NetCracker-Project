package com.group3.basic.netcracker.backend.report;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
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
import java.util.*;

import static com.group3.basic.netcracker.backend.report.ReportDataSource.getDataSource;

@Transactional
@Service
public class Reporter {
    @Autowired
    private DataSource dataSource = getDataSource();
    private JdbcTemplate template = new JdbcTemplate(dataSource);

    public void createReportByCourse(int[] courses) throws SQLException {
        generateReport(queryReportByCourse(courses));
    }

    public void createReportByTrainer(int trainerId) throws SQLException {
        generateReport(queryReportByTrainer(trainerId));
    }

    public void createReportByStudent(String username) throws SQLException {
        generateReport(queryReportByStudent(username));
    }

    public void createReportByLevel(String level) throws SQLException {
        generateReport(queryReportByLevel(level));
    }

    public void generateReport(List<Map<String, Object>> data) throws SQLException {
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
                cell.setCellValue(" " +value);
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

    public List<Map<String, Object>> queryReportByTrainer(int trainerId) throws SQLException {

        String sql = "select count(lm.id) as \"MissingQty\", \n" +
                "                u.username as \"Student\", lm.reason as \"Reason\"\n" +
                "                from \"LessonMissing\" lm join \"User\" u on lm.user_id = u.id \n" +
                "                join \"Group\" g on u.id = g.user_id \n" +
                "                join \"Course\" c on c.id = g.course_id\n" +
                "\t\t\t\tjoin (select id, username from \"User\") as t on t.id = c.trainer_id\n" +
                "                  where t.id in (" + trainerId + ") \n" +
                "                  group by u.username, lm.reason, c.name, t.username,t.id";
        List<Map<String, Object>> list = template.queryForList(sql);
        list = isResultEmpty(list);
        return list;
    }

    public List<Map<String, Object>> queryReportByCourse(int[] courses) throws SQLException {
        String g = Arrays.toString(courses).substring(1).replaceFirst("]", "").replace("[", "");
        String sql = "select c.name as \"Course\",\n" +
                "                coalesce(cast(l.id as text), 'Any lesson') as \"Lesson\", \n" +
                "                coalesce(u.username,'Anyone missing') as \"Student\",             \n" +
                "                coalesce(lm.reason, '') \"Reason\"\n" +
                "                from \"Course\" c\n" +
                "                left join \"Lesson\" l on l.course_id = c.id\n" +
                "                left join \"LessonMissing\" lm on l.id = lm.lesson_id\n" +
                "                left join \"User\" u on u.id = lm.user_id\n" +
                "                where c.id in (select id from \"Course\")\n" +
                "                group by c.name, c.id, l.id, lm.reason, u.username, u.fname\n" +
                "\t\t\t\torder by c.name";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println("------Attendance by courses-------");
        list = isResultEmpty(list);
        return list;
    }

    public List<Map<String, Object>> queryReportByStudent(String username) throws SQLException {
        String sql = "select count(lm.id) as \"MissingQty\", \n" +
                "coalesce(u.fname || ' ' || u.lname, '')\n" +
                "as \"Student\", crs.\"Course\", lm.reason as \"Reason\" \n" +
                "from \"LessonMissing\" lm \n" +
                "join \"User\" u on u.id = lm.user_id\n" +
                "join (select c.id, c.name as \"Course\", g.user_id\n" +
                "\t from \"Course\" c\n" +
                "\t join \"Group\" g on g.course_id = c.id) as crs on crs.user_id = u.id \n" +
                "where u.username like '" + username + "'\n" +
                "group by u.fname, u.lname, crs.\"Course\", lm.reason\n";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println("------Attendance by student-------");
        list = isResultEmpty(list);
        return list;
    }
    public List<Map<String, Object>> queryReportByLevel(String level) throws SQLException {
        String sql = "select count(lm.id) as \"MissingQty\", \n" +
                "coalesce(u.fname || ' ' || u.lname, '')\n" +
                "as \"Student\", crs.\"Course\", lm.reason as \"Reason\" \n" +
                "from \"LessonMissing\" lm \n" +
                "join \"User\" u on u.id = lm.user_id\n" +
                "join (select c.id, c.name as \"Course\",\n" +
                "\t g.user_id, c.skill_level\n" +
                "\t from \"Course\" c\n" +
                "\t join \"Group\" g on g.course_id = c.id) as crs on crs.user_id = u.id \n" +
                "where crs.skill_level like '%" + level + "%'\n" +
                "group by crs.\"Course\", u.fname, u.lname, lm.reason";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println("------Attendance by level-------");
        list = isResultEmpty(list);
        return list;
    }
    public void setFont(Workbook wb, XSSFSheet sheet) {
        CellStyle style = wb.createCellStyle();//Create style
        Font font = wb.createFont();//Create font
        font.setColor(HSSFColor.GREEN.index);
        font.setFontName("Arial");
        font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        style.setFont(font);

        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++)
            sheet.getRow(0).getCell(i).setCellStyle(style);
    }
    // Check if result set empty - write some result
    public List<Map<String, Object>> isResultEmpty(List<Map<String, Object>> list) {
        if(list.isEmpty()) {
            Object obj = "";
            String result = "Result set is empty";
            Map<String, Object> objectMap = new TreeMap<String, Object>();
            objectMap.put(result, obj);
            list.add(objectMap);
        }
        return list;
   }
}
