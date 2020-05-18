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

import static org.junit.Assert.assertEquals;

public class BigBangIntergration {

    protected Validator<Student> studentValidator;
    protected Validator<Tema> temaValidator;
    protected Validator<Nota> notaValidator;

    protected StudentXMLRepository fileRepository1;
    protected TemaXMLRepository fileRepository2;
    protected NotaXMLRepository fileRepository3;

    protected Service service;

    @Before
    public void setUp(){
        studentValidator = new StudentValidator();
        temaValidator = new TemaValidator();
        notaValidator = new NotaValidator();
        fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");
        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testaddGradeBigBang(){
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);
        service.saveStudent(newId, "Ioana", 934);

        int initNoTeme = service.getNumberOfTeme();
        String newIdTema = String.valueOf(initNoTeme + 1);
        service.saveTema(newIdTema, "VVSS", 10, 1);
        assertEquals(service.saveNota(newId, newIdTema, 9.5, 5, "very good!"), 1);
    }

    @Test
    public void testaddStudentBigBang(){
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int actualResult = service.saveStudent(newId, "Ioana", 934);
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testaddAssignmentBigBang(){
        int initNo = service.getNumberOfTeme();
        String newId = String.valueOf(initNo + 1);

        int actualResult = service.saveTema(newId, "VVSS", 10, 1);
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testintegrateBigBang(){
        testaddGradeBigBang();
        testaddAssignmentBigBang();
        testaddStudentBigBang();
    }

    @Test
    public void testAddStudentTopDown(){
        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo + 1);

        int actualResult = service.saveStudent(newId, "Andrei", 934);
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAddAssignmentTopDown(){
        this.testAddStudentTopDown();

        int initNo = service.getNumberOfTeme();
        String newId = String.valueOf(initNo + 1);

        int actualResult = service.saveTema(newId, "VVSS", 10, 1);
        int expectedResult = 0;
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testAddGradeTopDown(){
        this.testAddStudentTopDown();
        this.testAddAssignmentTopDown();

        int initNo = service.getNumberOfStudents();
        String newId = String.valueOf(initNo - 1);

        int initNoTeme = service.getNumberOfTeme();
        String newIdTema = String.valueOf(initNoTeme - 1);
        service.saveTema(newIdTema, "VVSS", 10, 1);
        assertEquals(service.saveNota(newId, newIdTema, 9.5, 5, "very good!"), 1);
    }
}
