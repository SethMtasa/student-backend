package seth.studentCrud10Jan.controller;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import seth.studentCrud10Jan.dto.*;
import seth.studentCrud10Jan.service.StudentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentResponseDto createdStudent = studentService.createStudent(studentDto);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

        // ...

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
            // Validate the login credentials
            boolean isValidCredentials = studentService.validateLoginCredentials(loginRequest.getUsername(), loginRequest.getPassword());

            if (isValidCredentials) {
                // Authentication granted
                log.info("ssssssssssssssss Successful login for username: {}", loginRequest.getUsername());
                return ResponseEntity.ok().build();
            } else {
                // Authentication failed
                boolean userExists = studentService.checkIfUserExists(loginRequest.getUsername());

                if (userExists) {
                    log.warn("fffffffffffffff Failed login for username: {}", loginRequest.getUsername());
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
                } else {
                    log.warn("uuuuuuuuuuuuuuuu User not found for username: {}", loginRequest.getUsername());
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                }
            }
        }

    @PostMapping("/register")
    public ResponseEntity<StudentResponseDto> registerStudent(@RequestBody StudentRegistrationDto registrationDto) {
        // Delegate the registration logic to the StudentService
        StudentResponseDto responseDto = studentService.registerStudent(registrationDto);

        // Return a ResponseEntity with the response DTO and a status code
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

}
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentResponseDto updatedStudent = studentService.updateStudent(id, studentDto);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable Long id) {
        StudentResponseDto student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().
        build();
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        List<StudentResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
}