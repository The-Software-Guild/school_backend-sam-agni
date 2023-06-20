package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Student;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
    	Student result = new Student();
    	result.setStudentFirstName(rs.getString("fName"));
    	result.setStudentId(rs.getInt("sid"));
    	result.setStudentLastName(rs.getString("lName"));

        return result;

        //YOUR CODE ENDS HERE
    }
}
