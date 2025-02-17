package se.lexicon.course_manager.data.service.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.course_manager.data.dao.CourseDao;
import se.lexicon.course_manager.data.dao.StudentDao;
import se.lexicon.course_manager.data.service.converter.Converters;
import se.lexicon.course_manager.dto.forms.CreateStudentForm;
import se.lexicon.course_manager.dto.forms.UpdateStudentForm;
import se.lexicon.course_manager.dto.views.StudentView;
import se.lexicon.course_manager.model.Course;
import se.lexicon.course_manager.model.Student;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StudentManager implements StudentService {

    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final Converters converters;

    @Autowired
    public StudentManager(StudentDao studentDao, CourseDao courseDao, Converters converters) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.converters = converters;
    }

    @Override
    public StudentView create(CreateStudentForm form) {
        Student student = studentDao.createStudent(form.getName(), form.getEmail(), form.getAddress());
        return new StudentView(student.getId(),form.getName(), form.getEmail(), form.getAddress());
    }

    @Override
    public StudentView update(UpdateStudentForm form) {
        Student student = studentDao.findById(form.getId());
        if(student == null) return null;
        student.setName(form.getName());
        student.setAddress(form.getAddress());
        student.setEmail(form.getEmail());
        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView findById(int id) {
        Student student = studentDao.findById(id);

        return converters.studentToStudentView(student);
    }

    @Override
    public StudentView searchByEmail(String email) {
        Student student = studentDao.findByEmailIgnoreCase(email);

        return converters.studentToStudentView(student);
    }

    @Override
    public List<StudentView> searchByName(String name) {
        List<StudentView> studentList = new ArrayList<>();

        for (Student student: studentDao.findByNameContains(name)) {
            if(student.getName().equalsIgnoreCase(name)) {
                studentList.add(converters.studentToStudentView(student));
            }
        }

        return studentList;
    }

    @Override
    public List<StudentView> findAll() {
        List<StudentView> studentList = new ArrayList<>();

        for (Student student: studentDao.findAll()) {
            studentList.add(converters.studentToStudentView(student));
        }

        return studentList;
    }

    @Override
    public boolean deleteStudent(int id) {
        Student studentToDelete = null;
        for (Student student: studentDao.findAll()) {
            if(student.getId() == id) studentToDelete = student;
        }

        if(studentToDelete != null) studentDao.removeStudent(studentToDelete);

        return studentToDelete != null;
    }
}
