package WBTLab2;

import WBTLab2.domain.Nota;
import WBTLab2.domain.Student;
import WBTLab2.domain.Tema;
import WBTLab2.repository.NotaXMLRepository;
import WBTLab2.repository.StudentXMLRepository;
import WBTLab2.repository.TemaXMLRepository;
import WBTLab2.service.Service;
import WBTLab2.validation.NotaValidator;
import WBTLab2.validation.StudentValidator;
import WBTLab2.validation.TemaValidator;
import WBTLab2.validation.Validator;
import org.junit.Test;
import WBTLab2.validation.*;

import static org.junit.Assert.*;

public class TestAssignment {
    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    Service service = new Service(null, fileRepository2, null);

    @Test
    public void testAddAssignmentValid() {
        int initNo = service.getNumberOfTeme();
        String newId = String.valueOf(initNo + 1);
        service.saveTema(newId, "VVSS", 10, 5);
        int no = service.getNumberOfTeme();
        assertEquals(no, initNo + 1);
    }

    @Test
    public void testAddAssignmentInvalidId() {
        try {
            service.saveTema("-1", "", 4, 5);
        } catch(ValidationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
