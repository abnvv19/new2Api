package in.abnvv.new2Api.service;

import in.abnvv.new2Api.entity.Student;
import in.abnvv.new2Api.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student studentReq) {
        studentReq.setDeleted(false);
        Student studentResp = studentRepository.save(studentReq);
        return studentResp;
    }
    public Student getStudent(Long id) {
        Optional<Student> studentResp = studentRepository.findByIdAndDeletedIsFalse(id);
        if(studentResp.isPresent()) {
            return studentResp.get();
        }
        return null;
    }
    public List<Student> getAllStudents() {
        List<Student> studentsList = studentRepository.findByDeletedIsFalse();
        return studentsList;
    }
    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndDeletedIsFalse(id);
        if(existingStudent.isEmpty()) {
            return null;
        }
        Student studentToSave = existingStudent.get();
        studentToSave.setName(studentReq.getName());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);

        studentRepository.save(studentToSave);
        return studentToSave;

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
}
