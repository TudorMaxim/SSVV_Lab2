package WBTLab2.validation;

import WBTLab2.domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().equals("") || !isId(student)|| Long.parseLong(student.getID()) < 0) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().equals("") || !isName(student)) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() <= 110 || student.getGrupa() >= 938) {
            throw new ValidationException("Grupa invalida! \n");
        }
    }

    public boolean isName(Student student) {
        String name = student.getNume();
        return name.matches("[a-zA-Z]+");
    }

    public boolean isId(Student student) {
        String id = student.getID();
        return id.matches("[0-9]+");
    }
}

