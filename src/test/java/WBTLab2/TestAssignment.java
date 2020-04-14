package WBTLab2;

import WBTLab2.domain.Tema;
import WBTLab2.repository.TemaXMLRepository;
import WBTLab2.service.Service;
import WBTLab2.validation.TemaValidator;
import WBTLab2.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import WBTLab2.validation.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestAssignment {

    Service service;

    @Before
    public void setUp() {
        Validator<Tema> temaValidator = new TemaValidator();
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "./src/test/xml/teme.xml");
        service = new Service(null, fileRepository2, null);
    }

    @After
    public void clear() {
        Arrays.asList("1", "2", "-1", "1t").forEach(id -> service.deleteTema(id));
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
        int result = service.saveTema("-1", "", 4, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentDuplicate() {
        int initNo = service.getNumberOfTeme();
        String newId = String.valueOf(initNo + 1);
        service.saveTema(newId, "VVSS2", 10, 5);
        int no = service.getNumberOfTeme();
        service.saveTema(newId, "VVSS2", 10, 5);
        int no2 = service.getNumberOfTeme();
        assertEquals(no, no2);
    }

    @Test
    public void testAddAssignmentNullId() {
        int result = service.saveTema(null, "VVSS", 10, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentEmptyId() {
        int result = service.saveTema("", "VVSS", 10, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentNegativeId() {
        int result = service.saveTema("-1", "VVSS", 10, 5);
        assertEquals(result, 1);
    }


    @Test
    public void testAddAssignmentNonNumericId() {
        int result = service.saveTema("1t", "VVSS", 10, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentNullDescription() {
        int result = service.saveTema("123456", null, 10, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentEmptyDescription() {
        int result = service.saveTema("123456", "", 10, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentSmallerDeadline() {
        int result = service.saveTema("123456", "VVSS", 0, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentGreaterDeadline() {
        int result = service.saveTema("123456", "VVSS", 100, 5);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentSmallerStartline() {
        int result = service.saveTema("123456", "VVSS", 10, 0);
        assertEquals(result, 1);
    }

    @Test
    public void testAddAssignmentGreaterStartline() {
        int result = service.saveTema("123456", "VVSS", 10, 100);
        assertEquals(result, 1);
    }

}
