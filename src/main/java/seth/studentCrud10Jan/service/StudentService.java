package seth.studentCrud10Jan.service;

import seth.studentCrud10Jan.dto.StudentDto;
import seth.studentCrud10Jan.dto.StudentRegistrationDto;
import seth.studentCrud10Jan.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {
    StudentResponseDto createStudent(StudentDto studentDto);
    StudentResponseDto getStudentById(Long id);
    StudentResponseDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
    List<StudentResponseDto> getAllStudents();

    StudentResponseDto registerStudent(StudentRegistrationDto registrationDto);
}