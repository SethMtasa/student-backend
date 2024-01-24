package seth.studentCrud10Jan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seth.studentCrud10Jan.dto.StudentDto;
import seth.studentCrud10Jan.dto.StudentResponseDto;
import seth.studentCrud10Jan.model.Student;
import seth.studentCrud10Jan.repo.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public StudentResponseDto createStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto, Student.class);
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentResponseDto.class);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        existingStudent.setName(studentDto.getName());
        existingStudent.setAge(studentDto.getAge());
        existingStudent.setAddress(studentDto.getAddress());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setPassword(studentDto.getPassword());

        Student updatedStudent = studentRepository.save(existingStudent);
        return modelMapper.map(updatedStudent, StudentResponseDto.class);
    }
    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return modelMapper.map(student, StudentResponseDto.class);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentResponseDto.class))
                .collect(Collectors.toList());
    }
}
