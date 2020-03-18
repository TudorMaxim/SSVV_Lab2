package WBTLab2.repository;


import WBTLab2.domain.Student;
import WBTLab2.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

