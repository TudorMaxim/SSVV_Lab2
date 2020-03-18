package WBTLab2;

import WBTLab2.domain.Nota;
import WBTLab2.domain.Student;
import WBTLab2.domain.Tema;
import WBTLab2.repository.NotaXMLRepository;
import WBTLab2.repository.StudentXMLRepository;
import WBTLab2.repository.TemaXMLRepository;
import WBTLab2.service.Service;
import WBTLab2.validation.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStudent {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(fileRepository1, fileRepository2, fileRepository3);

//    @Test
//    public void testAddStudent() {
//        String[] student = {"1", "nume", "123", "asdfd", "asdfds"};
//        try {
//            service.saveStudent("5", "Teo", 934);
//            assertEquals(1, service.findAllStudents().iterator());
//        } catch (ValidatorException e) {
//            e.printStackTrace();
//            assertTrue(false);
//        }
//    }

//    @Test
//    public void testStudentAlreadyExist() {
//        String[] student = {"2", "nume", "123", "asdfd", "asdfds"};
//        String[] student2 = {"2", "gigolo", "123", "asdfd", "asdfds"};
//        try {
//        } catch (ValidatorException e) {
//            e.printStackTrace();
//            assertTrue(false);
//        }
//    }

    @Test(expected = ValidationException.class)
    public void testStudentId() throws ValidationException {
        String[] student = {"", "nume", "123", "asdfd", "asdfds"};
        service.saveStudent("", "jdj", 434);
    }
}
