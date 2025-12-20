package com.example.demo.Services;
import com.example.demo.Model.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private Map<Integer, Student> studentMap = new HashMap<>();

    public Student registerStudent(Student student) {
        if (studentMap.containsKey(student.getId())) {
            throw new IllegalStateException("Student with that ID already exists");
        }
        studentMap.put(student.getId(), student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }
    public Student getStudentById(int id) {
        return studentMap.get(id);
    }
    public boolean deleteStudent(int id) {
        return studentMap.remove(id) != null;
    }
}
