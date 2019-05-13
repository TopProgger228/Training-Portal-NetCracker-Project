package com.group3.basic.netcracker.backend.report;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
public class ReportDataSource {
    private static final String driverClassName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://178.128.252.215:5432/training";
    private static final String dbUsername = "deploy";
    private static final String dbPassword = "secret";

    private static DataSource dataSource;

    public static void main(String[] args) throws Exception {

        dataSource = getDataSource();

        // JdbcTemplate template = new JdbcTemplate(dataSource); // constructor

        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);

        System.out.println(dataSource.getClass());

    }

    public static DriverManagerDataSource getDataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driverClassName);

        dataSource.setUrl(url);

        dataSource.setUsername(dbUsername);

        dataSource.setPassword(dbPassword);

        return dataSource;
    }
}
