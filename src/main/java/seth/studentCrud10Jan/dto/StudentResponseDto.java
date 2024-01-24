package seth.studentCrud10Jan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private int age;
    private String address;


    // Constructors, getters, and setters

    // ...
}