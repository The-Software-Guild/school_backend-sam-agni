package com.wileyedge.fullstackschool.service;

import com.wileyedge.fullstackschool.dao.TeacherDao;
import com.wileyedge.fullstackschool.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherServiceInterface {

    //YOUR CODE STARTS HERE
    @Autowired
    TeacherDao teacherDao;

    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }


    //YOUR CODE ENDS HERE

    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        return teacherDao.getAllTeachers();

        //YOUR CODE ENDS HERE
    }

    public Teacher getTeacherById(int id) {
        //YOUR CODE STARTS HERE
    	Teacher result = null;
    	try {
    		result = teacherDao.findTeacherById(id);
    	} catch (DataAccessException e) {
    		result = new Teacher();
    		result.setTeacherId(id);
    		result.setTeacherFName("Teacher Not Found");
    		result.setTeacherLName("Teacher Not Found");
    	}

        return result;

        //YOUR CODE ENDS HERE
    }

    public Teacher addNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE
    	boolean isValid = true;
    	if (teacher.getTeacherFName() == null || teacher.getTeacherFName().length() == 0) {
    		teacher.setTeacherFName("First Name blank, teacher NOT added");
    		isValid = false;
    	}
    	if (teacher.getTeacherLName() == null || teacher.getTeacherLName().length() == 0) {
    		teacher.setTeacherLName("Last Name blank, teacher NOT added");
    		isValid = false;
    	}
    	
    	if (isValid) {
    		teacherDao.createNewTeacher(teacher);
    	}

        return teacher;

        //YOUR CODE ENDS HERE
    }

    public Teacher updateTeacherData(int id, Teacher teacher) {
        //YOUR CODE STARTS HERE
    	if (id != teacher.getTeacherId()) {
    		teacher.setTeacherFName("IDs do not match, teacher not updated");
    		teacher.setTeacherLName("IDs do not match, teacher not updated");
    	} else {
    		teacherDao.updateTeacher(teacher);
    	}

        return teacher;

        //YOUR CODE ENDS HERE
    }

    public void deleteTeacherById(int id) {
        //YOUR CODE STARTS HERE
    	teacherDao.deleteTeacher(id);

        //YOUR CODE ENDS HERE
    }
}
