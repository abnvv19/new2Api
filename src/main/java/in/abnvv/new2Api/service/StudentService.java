package in.abnvv.new2Api.service;

import in.abnvv.new2Api.dto.CreateStudentRequestDto;
import in.abnvv.new2Api.dto.CreateStudentResponseDto;
import in.abnvv.new2Api.dto.UpdateStudentRequestDto;
import in.abnvv.new2Api.dto.UpdateStudentResponseDto;
import in.abnvv.new2Api.entity.Student;
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

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

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
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()) {
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);
        studentToSave.setUpdatedAt(LocalDateTime.now());


        Student savedStudent = studentRepository.save(studentToSave);
        return  mapToUpdateDto(savedStudent);

    }
    public Boolean deleteStudent(Long id) {
        Boolean isDeleted = studentRepository.existsById(id);
        if(!isDeleted) {
            return false;
        }
        studentRepository.deleteById(id);
        return true;
    }
    public Boolean softDeleteStudent(Long id) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()) {
            return false;
        }
        Student studentToDelete = existingStudent.get();
        studentToDelete.setDeleted(true);
        studentRepository.save(studentToDelete);
        return true;
    }

    private Student mapToEntity(CreateStudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setRollNo(studentRequestDto.getRollNo());
        student.setEmail(studentRequestDto.getEmail());
        student.setSubject(studentRequestDto.getSubject());
        student.setDeleted(false);
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
}
