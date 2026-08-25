package in.abnvv.new2Api.controller;

import in.abnvv.new2Api.dto.CreateStudentRequestDto;
import in.abnvv.new2Api.dto.CreateStudentResponseDto;
import in.abnvv.new2Api.dto.UpdateStudentRequestDto;
import in.abnvv.new2Api.dto.UpdateStudentResponseDto;
import in.abnvv.new2Api.entity.Student;
import in.abnvv.new2Api.service.StudentService;
import jakarta.validation.Valid;
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
        CreateStudentResponseDto studentResp = studentService.getStudent(id);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents() {
        List<CreateStudentResponseDto> studentsList = studentService.getAllStudents();
        if(studentsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentsList);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto studentReq) {
        UpdateStudentResponseDto studentResp = studentService.updateStudent(id, studentReq);
        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Boolean isDeleted = studentService.deleteStudent(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("sucessfully deleted");
    }
    @PatchMapping("/delete-softly/{id}")
    public ResponseEntity<String> softDeleteStudent(@PathVariable Long id) {
        Boolean isDeleted = studentService.softDeleteStudent(id);
        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("sucessfully deleted");
    }

}
