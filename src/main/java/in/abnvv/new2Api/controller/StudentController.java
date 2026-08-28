package in.abnvv.new2Api.controller;

import in.abnvv.new2Api.dto.CreateStudentRequestDto;
import in.abnvv.new2Api.dto.CreateStudentResponseDto;
import in.abnvv.new2Api.dto.UpdateStudentRequestDto;
import in.abnvv.new2Api.dto.UpdateStudentResponseDto;
import in.abnvv.new2Api.entity.Student;
import in.abnvv.new2Api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateStudentResponseDto> createStudent(@Valid @RequestBody CreateStudentRequestDto studentRequestDto) {
       CreateStudentResponseDto createdStudent = studentService.createStudent(studentRequestDto);
       return ResponseEntity.ok(createdStudent);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents() {
        List<CreateStudentResponseDto> studentsList = studentService.getAllStudents();
        return ResponseEntity.ok(studentsList);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto studentReq) {
        UpdateStudentResponseDto studentResp = studentService.updateStudent(id, studentReq);
        return ResponseEntity.ok(studentResp);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PatchMapping("/delete-softly/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        studentService.softDeleteStudent(id);

        return ResponseEntity.noContent().build() ;
    }

}
