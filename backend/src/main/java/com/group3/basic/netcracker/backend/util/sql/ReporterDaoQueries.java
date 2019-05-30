package com.group3.basic.netcracker.backend.util.sql;

public interface ReporterDaoQueries {

    String queryForReportByTrainer = "select count(lm.id) as \"MissingQty\", \n" +
    //Получаем в отчет кол-во пропущенных уроков, юзернейм студента и причину пропуска
            "       u.username as \"Student\", c.name as \"Course\",\n" +
            "\t   lm.reason as \"Reason\"," +
    //Тренера нужно брать из подзапроса, потому что с User уже берем связь для студентов
            " t.username as \"Trainer\"\n" +
            "       from \"LessonMissing\" lm " +
    //Соединяем пропуски со студентом
            "join \"User\" u on lm.user_id = u.id \n" +
    //Соединяем с таблицей групп и курсов
            "       join \"Group\" g on u.id = g.user_id \n" +
            "       join \"Course\" c on c.id = g.course_id\n" +
    //Делаем маленький подзапрос что-бы выбрать тренеров
            "       join (select id, username from \"User\") as t on t.id = c.trainer_id\n" +
    //Фильтруем по юзернейму тренера из подзапроса
            "              where t.username = ? \n" +
    //И группируем по выбранным строкам, нужно так сделать потому что используем статистическую функцию count
            "              group by u.username, lm.reason, c.name, t.username,t.id";

    String queryReportByStudent = "select count(lm.id) as \"MissingQty\", \n" +
   //Получаем в отчет кол-во пропущенных уроков студентом,
   //Выводим fname и lname в одну колонку, через coalesce указываем пустой символ если не нашли lname
            "            coalesce(u.fname || ' ' || u.lname, '')\n" +
            "            as \"Student\",\n" +
     //Добавляем в вывод название курса и причину пропуска
            "            c.name as \"Course\", lm.reason as \"Reason\"\n" +
            "            from \"LessonMissing\" lm          \n" +
     //Соединяем с юзером по группе что-бы выбрать именно студента
            "            join \"User\" u on u.id = lm.user_id\n" +
            "\t\t\t      join \"Group\" g on g.user_id = u.id\n" +
     //Соединяем курс по группе
            "            join \"Course\" c on c.id = g.course_id       \n" +
     //Фильтруем по юзернейму студента и группируем
            "            where u.username = ? \n" +
            "            group by u.fname, u.lname, c.name, lm.reason;";


    String queryReportByLevel = "select count(lm.id) as \"MissingQty\", \n" +
    //Получаем в отчет кол-во пропущенных уроков студентом,
    //Выводим fname и lname в одну колонку, через coalesce указываем пустой символ если не нашли lname
            "            coalesce(u.fname || ' ' || u.lname, '')\n"+
            "            as \"Student\",\n"+
    //Добавляем в вывод курс и причину пропуска из LessonMissing
            "            c.name as \"Coruse\", lm.reason as \"Reason\"\n"+
            "            from \"LessonMissing\" lm \n"+
    //Соединяем с User по пропущенному уроку, с группой по юзеру
            "            join \"User\" u on u.id = lm.user_id\n"+
            "            join \"Group\" g on g.user_id = u.id\n"+
    //С курсами соединим по группе и добавляем фильтр по уровню сложности затем группируем
            "\t\t\t      join \"Course\" c on g.course_id = c.id \n"+
            "            where c.skill_level = ? \n"+
            "            group by u.fname, u.lname, c.id, lm.reason";
}
