package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.StudentDao;
import com.wileyedge.fullstackschool.model.Course;
import com.wileyedge.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    StudentDao studentDao;

    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Autowired
    CourseServiceImpl courseService;



    //YOUR CODE ENDS HERE

    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        return studentDao.getAllStudents();

        //YOUR CODE ENDS HERE
    }

    public Student getStudentById(int id) {
        //YOUR CODE STARTS HERE
    	Student result = null;
    	try {
    		result = studentDao.findStudentById(id);
    	} catch (DataAccessException e) {
    		result = new Student();
    		result.setStudentId(id);
    		result.setStudentFirstName("Student Not Found");
    		result.setStudentLastName("Student Not Found");
    	}

        return result;

        //YOUR CODE ENDS HERE
    }

    public Student addNewStudent(Student student) {
        //YOUR CODE STARTS HERE
    	boolean isValid = true;
    	if (student.getStudentFirstName() == null || student.getStudentFirstName().length() == 0) {
    		student.setStudentFirstName("First Name blank, student NOT added");
    		isValid = false;
    	}
    	if (student.getStudentLastName() == null || student.getStudentLastName().length() == 0) {
    		student.setStudentLastName("Last Name blank, student NOT added");
    		isValid = false;
    	}
    	
    	if (isValid) {
    		studentDao.createNewStudent(student);
    	}

        return student;

        //YOUR CODE ENDS HERE
    }

    public Student updateStudentData(int id, Student student) {
        //YOUR CODE STARTS HERE
    	if (id != student.getStudentId()) {
    		student.setStudentFirstName("IDs do not match, student not updated");
    		student.setStudentLastName("IDs do not match, student not updated");
    	} else {
    		studentDao.updateStudent(student);
    	}

        return student;

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentById(int id) {
        //YOUR CODE STARTS HERE
    	studentDao.deleteStudent(id);

        //YOUR CODE ENDS HERE
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
    	Student s = getStudentById(studentId);
    	Course c = courseService.getCourseById(courseId);
    	if (s.getStudentFirstName().equals("Student Not Found")) {
    		System.out.println("Student not found");
    	} else if (c.getCourseName().equals("Course Not Found")) {
    		System.out.println("Course not found");
    	} else {
    		studentDao.deleteStudentFromCourse(studentId, courseId);
    		System.out.println("Student: " + studentId + " deleted from course: " + courseId);
    	}

        //YOUR CODE ENDS HERE
    }

    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE
    	Student s = getStudentById(studentId);
    	Course c = courseService.getCourseById(courseId);
    	if (s.getStudentFirstName().equals("Student Not Found")) {
    		System.out.println("Student not found");
    	} else if (c.getCourseName().equals("Course Not Found")) {
    		System.out.println("Course not found");
    	} else {
    		try {
	    		studentDao.addStudentToCourse(studentId, courseId);
	    		System.out.println("Student: " + studentId + " added to course: " + courseId);
    		} catch (Exception e) {
    			System.out.println("Student: " + studentId + " already enrolled in course: " + courseId);
    		}
    	}

        //YOUR CODE ENDS HERE
    }
}
