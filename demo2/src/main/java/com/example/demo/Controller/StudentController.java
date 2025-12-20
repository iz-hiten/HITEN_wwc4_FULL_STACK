package com.example.demo.Controller;
import com.example.demo.Model.Student;
import com.example.demo.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {

        if (student.getName() == null || student.getName().isEmpty()
                || student.getCourse() == null || student.getCourse().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Name and course must not be empty");
        }

        try {
            studentService.registerStudent(student);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Student registered successfully");
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {

        Student student = studentService.getStudentById(id);

        if (student == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + id);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {

        boolean deleted = studentService.deleteStudent(id);

        if (!deleted) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Student not found with ID: " + id);
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Student deleted successfully");
    }
}
