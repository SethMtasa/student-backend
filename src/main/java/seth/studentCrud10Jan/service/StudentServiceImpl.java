package seth.studentCrud10Jan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import seth.studentCrud10Jan.dto.StudentDto;
import seth.studentCrud10Jan.dto.StudentRegistrationDto;
import seth.studentCrud10Jan.dto.StudentResponseDto;
import seth.studentCrud10Jan.model.Student;
import seth.studentCrud10Jan.model.User;
import seth.studentCrud10Jan.repo.StudentRepository;
import seth.studentCrud10Jan.repo.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private  final UserRepository userRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
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

    @Override
    public StudentResponseDto registerStudent(StudentRegistrationDto registrationDto) {
        // Validate the user input
        validateRegistrationDto(registrationDto);

        // Check if a student with the given username already exists
        if (studentRepository.existsByUsername(registrationDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Create a new User entity object
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());

        // Save the user to the database
        User savedUser = userRepository.save(user);

        // Map the saved user to a response DTO
        StudentResponseDto responseDto = modelMapper.map(savedUser, StudentResponseDto.class);
        return responseDto;
    }

    private void validateRegistrationDto(StudentRegistrationDto registrationDto) {
        // Implement your validation logic here
        // For example, check if required fields are not null or empty
        // You can throw appropriate exceptions or handle validation errors accordingly
    }


    @Override
    public boolean validateLoginCredentials(String username, String password) {
        // Retrieve the user from the database based on the provided username
        User user = userRepository.findByUsername(username);

        // Check if a user with the provided username exists and if the password matches
        if (user != null && password.equals(user.getPassword())) {
            return true; // Credentials are valid
        }

        return false; // Credentials are invalid
    }


    @Override
    public boolean checkIfUserExists(String username) {
        // Implement the logic to check if the user exists in the database
        // Return true if the user exists, false otherwise

        // Example implementation using a UserRepository assuming you have a User entity and a UserRepository interface:
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
