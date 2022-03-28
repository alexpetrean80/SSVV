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

public class SaveTemaTest {

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
    public void testSaveTema_ValidInput(){

        var result = service.saveTema("id", "desc", 10, 8);
        assertEquals(0, result);
    }

    @Test
    public void testSaveTema_InvalidInput(){
        var result = service.saveTema(null, "", 9, 12);
        assertEquals(1, result);
    }

}
