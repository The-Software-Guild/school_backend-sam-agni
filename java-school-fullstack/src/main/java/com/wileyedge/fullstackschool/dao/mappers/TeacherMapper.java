package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
    	Teacher result = new Teacher();
    	result.setDept(rs.getString("dept"));
    	result.setTeacherFName(rs.getString("tFName"));
    	result.setTeacherId(rs.getInt("tid"));
    	result.setTeacherLName(rs.getString("tLName"));

        return result;

        //YOUR CODE ENDS HERE
    }
}
