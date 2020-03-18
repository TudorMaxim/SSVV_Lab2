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

import java.util.Iterator;

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

    @Test
    public void testAddStudent() {
        try {
            service.saveStudent("6", "Tudor", 934);
            int len = 0;
            Iterator iterator = service.findAllStudents().iterator();
            while (iterator.hasNext()) {
                len++;
                iterator.next();
            }
            assertEquals(2, len);
        } catch (ValidationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testStudentAlreadyExist() {
        try {
            service.saveStudent("6", "Tudor", 934);
            service.saveStudent("6", "Maria", 934);
        } catch (ValidationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
