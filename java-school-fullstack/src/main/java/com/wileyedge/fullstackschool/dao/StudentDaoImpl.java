package com.wileyedge.fullstackschool.dao;

import com.wileyedge.fullstackschool.dao.mappers.StudentMapper;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE
    	String sql = "INSERT INTO student (sid, fName, lName) VALUES (?,?,?)";
    	jdbcTemplate.update(sql, student.getStudentId(), student.getStudentFirstName(), student.getStudentLastName());

        return student;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE
    	String sql = "SELECT * FROM student";
    	List<Student> result = jdbcTemplate.query(sql, new StudentMapper());

        return result;

        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE
    	String sql = "SELECT * FROM student WHERE sid=?";
    	Student result = jdbcTemplate.queryForObject(sql, new StudentMapper(), id);

        return result;

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateStudent(Student student) {
        //YOUR CODE STARTS HERE
    	String sql = "UPDATE student SET fName=?, lName=? WHERE sid=?";
    	jdbcTemplate.update(sql, student.getStudentFirstName(), student.getStudentLastName(), student.getStudentId());

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE
    	String sql = "DELETE FROM student WHERE sid=?";
    	jdbcTemplate.update(sql, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
    	String sql = "INSERT INTO course_student (student_id, course_id) VALUES (?,?)";
    	jdbcTemplate.update(sql, studentId, courseId);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
    	String sql = "DELETE FROM course_student WHERE course_id=? AND student_id=?";
    	jdbcTemplate.update(sql, courseId, studentId);

        //YOUR CODE ENDS HERE
    }
}
