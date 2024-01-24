package seth.studentCrud10Jan.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegistrationDto {

    private String name;
    private int age;
    private String address;
    private String email;
    private String username;
    private String password;

    // Constructors, getters, and setters


}
