package seth.studentCrud10Jan.service;

import seth.studentCrud10Jan.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
    StudentDto getStudentById(Long id);
    List<StudentDto> getAllStudents();

    // ...
}