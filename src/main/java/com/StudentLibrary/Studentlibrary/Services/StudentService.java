package com.StudentLibrary.Studentlibrary.Services;

import com.StudentLibrary.Studentlibrary.Model.Card;
import com.StudentLibrary.Studentlibrary.Model.Student;
import com.StudentLibrary.Studentlibrary.Repositories.CardRepository;
import com.StudentLibrary.Studentlibrary.Repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {

    Logger logger= LoggerFactory.getLogger(StudentService.class);


    @Autowired
    StudentRepository studentRepository ;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createStudent (Student student){

        Card card=cardService.createCard(student);
        student.setCard(card);  // Set the card in the student object

        studentRepository.save(student);  // Save the student entity to D
        logger.info("The card for the student{} is created with the card details{}",student,card);

    }

    public int updateStudent(Student student) {
        return studentRepository.updateStudentDetails(
                student.getStudentId(),
                student.getEmailId(),
                student.getName(),
                student.getAge(),
                student.getCountry()
        );
    }

//    @Transactional
//    public void deleteStudent(int studentId) {
//        // 1. Get student from DB
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//
//        // 2. Delete the card first (or deactivate it)
//        Card card = student.getCard();
//        if (card != null) {
//            cardRepository.delete(card);  // or cardService.deactivateCard(studentId) if just deactivate
//        }
//
//        // 3. Now delete the student
//        studentRepository.delete(student);
//    }

    @Transactional
    public void deleteStudent(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepository.delete(student); // This will also delete the card
    }



    public void resetStudentAutoIncrement() {
        // This will delete all data and reset auto-increment to 1
        jdbcTemplate.execute("TRUNCATE TABLE student");
    }
}
