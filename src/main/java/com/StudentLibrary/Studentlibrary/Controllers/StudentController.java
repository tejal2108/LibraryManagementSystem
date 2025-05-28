package com.StudentLibrary.Studentlibrary.Controllers;

import com.StudentLibrary.Studentlibrary.Model.Student;
import com.StudentLibrary.Studentlibrary.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Test route is working!", HttpStatus.OK);
    }

    @PostMapping("/createStudent")
    public ResponseEntity createStudent(@RequestBody Student student){
        studentService.createStudent(student);
        return new ResponseEntity("Student Successfully added to the system", HttpStatus.CREATED);

    }

//    @PutMapping("/updateStudent")
//    public ResponseEntity updateStudent(@RequestBody Student student){
//        int lines=studentService.updateStudent(student);
//        return new ResponseEntity("Student updated",HttpStatus.OK);
//    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody Student student) {
        try {
            student.setStudentId(id);
            int rowsUpdated = studentService.updateStudent(student);
            if (rowsUpdated > 0) {
                return ResponseEntity.ok("Student updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print stacktrace to console/logs
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating student: " + e.getMessage());
        }
    }


    @DeleteMapping("/deleteStudent")
    public ResponseEntity deleteStudent(@RequestParam("id")int id){
        studentService.deleteStudent(id);
        return new ResponseEntity("student successfully deleted!!",HttpStatus.OK);
    }


    @PostMapping("/reset-student-table")
    public String resetStudentTable() {
        studentService.resetStudentAutoIncrement();
        return "Student table reset successful!";
    }










}
