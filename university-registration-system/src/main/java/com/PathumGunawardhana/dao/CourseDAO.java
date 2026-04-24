package com.PathumGunawardhana.dao;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class CourseDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean registerCourse(int studentId, int courseId) {

        try {
            String checkSql =
                "SELECT COUNT(*) FROM registrations WHERE student_id=? AND course_id=?";

            Integer count = jdbcTemplate.queryForObject(
                    checkSql,
                    Integer.class,
                    studentId,
                    courseId
            );

            if (count != null && count > 0) {
                return false;
            }

            String sql =
                "INSERT INTO registrations(student_id, course_id, date) VALUES(?, ?, ?)";

            jdbcTemplate.update(
                sql,
                studentId,
                courseId,
                new java.sql.Date(System.currentTimeMillis())
            );

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Map<String, Object>> getRegisteredCourses(int studentId) {

        String sql =
            "SELECT c.course_id, c.name, c.instructor, c.credits, r.date " +
            "FROM registrations r " +
            "JOIN courses c ON r.course_id = c.course_id " +
            "WHERE r.student_id = ?";

        return jdbcTemplate.queryForList(sql, studentId);
    }
}