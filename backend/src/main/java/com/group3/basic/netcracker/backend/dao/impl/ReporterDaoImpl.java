package com.group3.basic.netcracker.backend.dao.impl;

import com.group3.basic.netcracker.backend.dao.ReporterDao;
import com.group3.basic.netcracker.backend.util.sql.ReporterDaoQueries;
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
        List<Map<String, Object>> list = template.queryForList(ReporterDaoQueries.queryForReportByTrainer, username);
        list = isResultEmpty(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryReportByCourse(int[] courses) {
        String sql = "select c.name as \"Course\",\n" +
        //Если нету уроков по курсу получаем Any lesson. Подобное для студентов.
        //Так делаем на случай если в выбранном для отчета курсе нету пропусков или студентов
                "                coalesce(cast(l.id as text), 'Any lesson') as \"Lesson\", \n" +
                "                coalesce(u.username,'Anyone missing') as \"Student\",             \n" +
                "                coalesce(lm.reason, '') \"Reason\"\n" +
                "                from \"Course\" c\n" +
        //Соединяем по left с уроками что-бы не потерять выбранные из курсов строки. Так же поступаем с остальными.
        //Если по курсу нету совпадений в следующих таблицах простой join их обрежет, а нам нужны все
                "                left join \"Lesson\" l on l.course_id = c.id\n" +
                "                left join \"LessonMissing\" lm on l.id = lm.lesson_id\n" +
                "                left join \"User\" u on u.id = lm.user_id\n" +
        //Фильтруем по курсам, которые были выбраны
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

        list = isResultEmpty(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryReportByStudent(String username) {
        List<Map<String, Object>> list = template.queryForList(ReporterDaoQueries.queryReportByStudent, username);

        list = isResultEmpty(list);
        return list;
    }

    @Override
    public List<Map<String, Object>> queryReportByLevel(String level) {
        List<Map<String, Object>> list = template.queryForList(ReporterDaoQueries.queryReportByLevel, level);

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
