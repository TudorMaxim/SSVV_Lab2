package WBTLab2;

import WBTLab2.domain.Nota;
import WBTLab2.domain.Student;
import WBTLab2.domain.Tema;
import WBTLab2.repository.NotaXMLRepository;
import WBTLab2.repository.StudentXMLRepository;
import WBTLab2.repository.TemaXMLRepository;
import WBTLab2.service.Service;
import WBTLab2.validation.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestStudent {

    Service service;

    @Before
    public void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        service = new Service(fileRepository1, null, null);
    }

    @Test
    public void testAddStudentSuccess() {
        try {
            service.saveStudent("6", "Tudor", 934);
            int len = service.getNumberOfStudents();
            assertEquals(2, len);
        } catch (ValidationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void testStudentFailure() {
        try {
            service.saveStudent("6", "Tudor", 934);
            service.saveStudent("6", "Maria", 934);
        } catch (ValidationException e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

}
