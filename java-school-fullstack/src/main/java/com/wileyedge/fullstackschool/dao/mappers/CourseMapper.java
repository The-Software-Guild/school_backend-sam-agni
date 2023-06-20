package com.wileyedge.fullstackschool.dao.mappers;

import com.wileyedge.fullstackschool.model.Course;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        //YOUR CODE STARTS HERE
    	Course result = new Course();
    	result.setCourseDesc(rs.getString("courseDesc"));
    	result.setCourseId(rs.getInt("cid"));
    	result.setCourseName(rs.getString("courseCode"));
    	result.setTeacherId(rs.getInt("teacherId"));

        return result;

        //YOUR CODE ENDS HERE
    }
}
