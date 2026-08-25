package in.abnvv.new2Api.dto;

import jakarta.validation.constraints.*;

public class CreateStudentRequestDto {
    @NotBlank(message = "Name cannot be null or empty")
    @Size(min = 2, max = 50, message = "student name must be within 2-50 character ")
    private String name;

    @Email(message = "email must be valid")
    private String email;

    @Min(value = 18, message = "student must be 18 years old")
    private int age;

    @NotNull(message = "roll no is required")
    private int rollNo;

    @NotBlank(message = "subject is required")
    private String subject;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
