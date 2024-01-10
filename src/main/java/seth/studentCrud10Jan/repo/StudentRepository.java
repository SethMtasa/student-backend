package seth.studentCrud10Jan.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seth.studentCrud10Jan.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Additional custom query methods if needed

    // ...
}