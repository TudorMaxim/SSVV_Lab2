package WBTLab2;

import WBTLab2.domain.Nota;
import WBTLab2.domain.Student;
import WBTLab2.domain.Tema;
import WBTLab2.repository.NotaXMLRepository;
import WBTLab2.repository.StudentXMLRepository;
import WBTLab2.repository.TemaXMLRepository;
import WBTLab2.service.Service;
import WBTLab2.validation.*;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class TestStudent {

    Service service;

    @Before
    public void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        service = new Service(fileRepository1, null, null);
    }


    @Test
    /**
     * TestCase no. 1
     */
    public void testAddStudentSuccess() {

        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Tudor", 934);
        System.out.println(result);
        int actualResult = 0;
        assertEquals(result, actualResult);
    }

    @Test
    /**
     * TestCase no. 2
     */
    public void testStudentFailure() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result1 = service.saveStudent(newId, "Tudor", 934);
        int studentsNo1 = service.getNumberOfStudents();
        int result2 = service.saveStudent(newId, "Maria", 934);
        int studentsNo2 = service.getNumberOfStudents();

        //the number of the students remains the same, hence we cannot add a student with the same id twice
        assertEquals(studentsNo1, studentsNo2);
    }

    @Test
    /**
     * TestCase no. 3
     * */
    public void testStudentFailure_IDNegative() {
        int result = service.saveStudent("-1", "Tudor", 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    /**
     * TestCase no. 4
     * */
    public void testStudentFailure_IDNull() {
        int result = service.saveStudent(null, "Tudor", 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    /**
     * TestCase no. 5
     * */
    public void testStudentFailure_IDEmpty() {
        int result = service.saveStudent("", "Tudor", 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    /**
     * TestCase no. 6
     * */
    public void testStudentFailure_IDNotValid() {
        int result = service.saveStudent("123abc", "Tudor", 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

//    @Test
//    public void testStudentSuccess_Name() {
//        int initNo = service.getNumberOfStudents();
//        String newId = String.valueOf(initNo + 1);
//
//        int result = service.saveStudent(newId, "Ioana", 934);
//        int actualResult = 0;
//        assertEquals(result, actualResult);
//    }

    @Test
    /**
     * TestCase no. 7
     * */
    public void testStudentFailure_NameNull() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, null, 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    /**
     * TestCase no. 8
     * */
    public void testStudentFailure_NameEmpty() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "", 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    /**
     * TestCase no. 9
     * */
    public void testStudentFailure_NameisNotValid() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Teo123!", 934);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

//    @Test
//    public void testStudentSuccess_Group() {
//        int initNo = service.getNumberOfStudents();
//        String newId = String.valueOf(initNo + 1);
//
//        int result = service.saveStudent(newId, "Ioana", 934);
//        int actualResult = 0;
//        assertEquals(result, actualResult);
//    }

    @Test
    public void testStudentFailure_GroupLessMIN() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Ioana", 109);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    public void testStudentFailure_GroupMIN() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Ioana", 110);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    public void testStudentSuccess_GroupMIN() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Ioana", 111);
        int actualResult = 0;
        assertEquals(result, actualResult);
    }


    @Test
    public void testStudentSuccess_GroupMAX() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Ioana", 937);
        int actualResult = 0;
        assertEquals(result, actualResult);
    }

    @Test
    public void testStudentFailure_GroupMAX() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Ioana", 938);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }

    @Test
    public void testStudentFailure_GroupGreaterMAX() {
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int result = service.saveStudent(newId, "Ioana", 939);
        int actualResult = 1;
        assertEquals(result, actualResult);
    }
}
