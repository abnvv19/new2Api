package in.abnvv.new2Api.service;

import in.abnvv.new2Api.dto.CreateStudentRequestDto;
import in.abnvv.new2Api.dto.CreateStudentResponseDto;
import in.abnvv.new2Api.dto.UpdateStudentRequestDto;
import in.abnvv.new2Api.dto.UpdateStudentResponseDto;
import in.abnvv.new2Api.entity.Student;
import in.abnvv.new2Api.exception.DuplicateResourceException;
import in.abnvv.new2Api.exception.ResourceNotFoundException;
import in.abnvv.new2Api.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentReqDto) {
        Student student = mapToEntity(studentReqDto);
        if(emailExist(student)){
            throw new DuplicateResourceException("Email already exists");
        }
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);
    }
    public CreateStudentResponseDto getStudent(Long id) {
        Student studentResp = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id " + id + " not found"));
        return mapToDto(studentResp);

    }
    public List<CreateStudentResponseDto> getAllStudents() {
        List<Student> studentsList = studentRepository.findByDeletedIsFalse();
        return studentsList.stream().map(this::mapToDto).toList();
    }
    public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentReq) {
        Student existingStudent = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id" + id + " not found"));


        existingStudent.setName(studentReq.getName());
        existingStudent.setAge(studentReq.getAge());
        existingStudent.setRollNo(studentReq.getRollNo());
        existingStudent.setSubject(studentReq.getSubject());
        existingStudent.setDeleted(false);
        existingStudent.setUpdatedAt(LocalDateTime.now());


        Student savedStudent = studentRepository.save(existingStudent);
        return  mapToUpdateDto(savedStudent);

    }
    public void deleteStudent(Long id) {
        Student studentToBeDeleted = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id" + id + " not found"));
        studentRepository.delete(studentToBeDeleted);
    }
    public void softDeleteStudent(Long id) {
        Student studentToDelete = studentRepository
                .findByIdAndDeletedIsFalse(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student with id" + id + " not found"));
        studentToDelete.setDeleted(true);
        studentRepository.save(studentToDelete);
    }

    private Student mapToEntity(CreateStudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setRollNo(studentRequestDto.getRollNo());
        student.setEmail(studentRequestDto.getEmail());
        student.setSubject(studentRequestDto.getSubject());
        student.setDeleted(false);
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());
        return student;
    }
    private CreateStudentResponseDto mapToDto(Student student) {
        CreateStudentResponseDto studentResponseDto = new CreateStudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setMessage("student saved successfully");
        studentResponseDto.setCreatedAt(student.getCreatedAt());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());


        return studentResponseDto;

    }
    private UpdateStudentResponseDto mapToUpdateDto(Student student){
        UpdateStudentResponseDto studentResponseDto = new UpdateStudentResponseDto();
        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setAge(student.getAge());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setSubject(student.getSubject());
        studentResponseDto.setMessage("student saved successfully");
        studentResponseDto.setCreatedAt(student.getCreatedAt());
        studentResponseDto.setUpdatedAt(student.getUpdatedAt());

        return studentResponseDto;
    }
    private boolean emailExist(Student student){
       return studentRepository.existsByEmail(student.getEmail());
    }
}
