package com.PathumGunawardhana.dao;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class StudentDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int validateUserAndGetId(String email, String password) {

        String sql = "SELECT student_id FROM students WHERE email=? AND password=?";

        @SuppressWarnings("deprecation")
		List<Integer> list = jdbcTemplate.query(sql,
            new Object[]{email, password},
            (rs, rowNum) -> rs.getInt("student_id")
        );

        return list.isEmpty() ? -1 : list.get(0);
    }
}