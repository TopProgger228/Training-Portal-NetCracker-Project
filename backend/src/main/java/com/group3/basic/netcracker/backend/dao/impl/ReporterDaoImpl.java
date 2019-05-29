package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.ReporterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Transactional
@Repository
public class ReporterDaoImpl implements ReporterDao {
    private final JdbcTemplate template;

    @Autowired
    public ReporterDaoImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Map<String, Object>> queryReportByTrainer(String username) {
        String sql = "select count(lm.id) as \"MissingQty\", \n" +
                "       u.username as \"Student\", c.name as \"Course\",\n" +
                "\t   lm.reason as \"Reason\", t.username as \"Trainer\"\n" +
                "       from \"LessonMissing\" lm join \"User\" u on lm.user_id = u.id \n" +
                "       join \"Group\" g on u.id = g.user_id \n" +
                "       join \"Course\" c on c.id = g.course_id\n" +
                "       join (select id, username from \"User\") as t on t.id = c.trainer_id\n" +
                "              where t.username = '" + username + "' \n" +
                "              group by u.username, lm.reason, c.name, t.username,t.id";
        List<Map<String, Object>> list = template.queryForList(sql);
        list = isResultEmpty(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryReportByCourse(int[] courses) {
        String sql = "select c.name as \"Course\",\n" +
                "                coalesce(cast(l.id as text), 'Any lesson') as \"Lesson\", \n" +
                "                coalesce(u.username,'Anyone missing') as \"Student\",             \n" +
                "                coalesce(lm.reason, '') \"Reason\"\n" +
                "                from \"Course\" c\n" +
                "                left join \"Lesson\" l on l.course_id = c.id\n" +
                "                left join \"LessonMissing\" lm on l.id = lm.lesson_id\n" +
                "                left join \"User\" u on u.id = lm.user_id\n" +
                "                where c.id in (select id from \"Course\" where id in (";
        sql += courses[0];
        if(courses.length > 1)
        for (int i  = 1; i < courses.length - 1; i++) {
            sql += ", " + courses[i];
        }
        sql += "))\n" +
                "                group by c.name, c.id, l.id, lm.reason, u.username, u.fname\n" +
                "\t\t\t\torder by c.name";
        List<Map<String, Object>> list = template.queryForList(sql);
        System.out.println("------Attendance by courses-------");
        list = isResultEmpty(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryReportByStudent(String username) {
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

        list = isResultEmpty(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryReportByLevel(String level) {
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

        list = isResultEmpty(list);
        return list;
    }

    private List<Map<String, Object>> isResultEmpty(List<Map<String, Object>> list) {
        if (list.isEmpty()) {
            Object obj = "";
            String result = "Result set is empty";
            Map<String, Object> objectMap = new TreeMap<String, Object>();
            objectMap.put(result, obj);
            list.add(objectMap);
        }
        return list;
    }
}
