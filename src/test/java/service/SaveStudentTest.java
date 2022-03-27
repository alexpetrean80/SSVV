package service;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.CRUDRepository;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;

public class SaveStudentTest {
    CRUDRepository<String, Student> studentRepo;
    CRUDRepository<String, Tema> temaRepo;
    CRUDRepository<Pair<String, String>, Nota> notaRepo;

    Validator<Student> studentValidator = new StudentValidator();
    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();

    Service service;

    @Before
    public void setup() {
        studentRepo = new StudentRepository(studentValidator);
        temaRepo = new TemaRepository(temaValidator);
        notaRepo = new NotaRepository(notaValidator);

        service = new Service(studentRepo, temaRepo, notaRepo);
    }

    @After
    public void teardown() {
        studentRepo = null;
        temaRepo = null;
        notaRepo = null;
        service = null;
    }

    @Test
    public void testSaveStudent_groupNumberBelowLowerBound() {
        var student = new Student("a", "b", 110);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(1, res);
    }

    @Test
    public void testSaveStudent_groupNumberLowerBound() {
        var student = new Student("a", "b", 111);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, res);
    }

    @Test
    public void testSaveStudent_groupNumberAboveLowerBound() {
        var student = new Student("a", "b", 112);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, res);
    }

    @Test
    public void testSaveStudent_groupNumberBelowUpperBound() {
        var student = new Student("a", "b", 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, res);
    }

    @Test
    public void testSaveStudent_groupNumberUpperBound() {
        var student = new Student("a", "b", 937);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, res);
    }

    @Test
    public void testSaveStudent_groupNumberAboveUpperBound() {

        var student = new Student("a", "b", 938);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(1, res);
    }

    @Test
    public void testSaveStudent_nameNotEmpty() {
        var student = new Student("a", "b", 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, res);
    }

    @Test
    public void testSaveStudent_nameEmpty() {
        var student = new Student("a", "", 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(1, res);
    }

    @Test
    public void testSaveStudent_nameIsNull() {
        var student = new Student("a", null, 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(1, res);
    }

    @Test
    public void testSaveStudent_idNotEmptyAndNotDuplicate() {
        var student = new Student("a", "b", 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, res);
    }

    @Test
    public void testSaveStudent_idNotEmptyAndDuplicate() {
        var student = new Student("a", "b", 936);

        var firstSave = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());
        var secondSave = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(0, firstSave);
        assertEquals(1, secondSave);
    }

    @Test
    public void testSaveStudent_idEmpty() {
        var student = new Student("", "b", 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(1, res);
    }

    @Test
    public void testSaveStudent_idIsNull() {
        var student = new Student(null, "b", 936);

        var res = service.saveStudent(student.getID(), student.getNume(), student.getGrupa());

        assertEquals(1, res);
    }
}
