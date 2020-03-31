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
import org.junit.Before;
import org.junit.Test;
import WBTLab2.validation.*;

import static org.junit.Assert.*;

public class TestAssignment {

    Service service;

    @Before
    public void setUp() {
        Validator<Tema> temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        service = new Service(null, fileRepository2, null);
    }

    @Test
    public void testAddAssignmentSuccess() {
        int initNo = service.getNumberOfTeme();
        String newId = String.valueOf(initNo + 1);
        service.saveTema(newId, "VVSS", 10, 5);
        int no = service.getNumberOfTeme();
        assertEquals(no, initNo + 1);
    }

    @Test
    public void testAddAssignmentFailure() {
        try {
            service.saveTema("-1", "", 4, 5);
        } catch(ValidationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }
}
