package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.CourseDao;
import com.wileyedge.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }


    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
    	Course result = null;
    	try {
    		result = courseDao.findCourseById(id);
    	} catch (DataAccessException e) {
    		result = new Course();
    		result.setCourseId(id);
    		result.setCourseDesc("Course Not Found");
    		result.setCourseName("Course Not Found");
    	}

        return result;

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE
    	boolean isValid = true;
    	if (course.getCourseName() == null || course.getCourseName().length() == 0) {
    		course.setCourseName("Name blank, course NOT added");
    		isValid = false;
    	}
    	if (course.getCourseDesc() == null || course.getCourseDesc().length() == 0) {
    		course.setCourseDesc("Description blank, course NOT added");
    		isValid = false;
    	}
    	
    	if (isValid) {
    		courseDao.createNewCourse(course);
    	}

        return course;

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
    	if (id != course.getCourseId()) {
    		course.setCourseDesc("IDs do not match, course not updated");
    		course.setCourseName("IDs do not match, course not updated");
    	} else {
    		courseDao.updateCourse(course);
    	}

        return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
    	courseDao.deleteCourse(id);
    	System.out.println("Course ID: " + id + " deleted");

        //YOUR CODE ENDS HERE
    }
}
