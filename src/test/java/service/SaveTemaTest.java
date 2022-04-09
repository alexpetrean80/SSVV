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
import validation.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    public void testSaveTema_validInputAndUniqueId(){

        var result = service.saveTema("id", "desc", 6, 4);
        assertEquals(0, result);
    }

    @Test
    public void testSaveTema_invalidId(){
        assertThrows(ValidationException.class, () -> {
            service.saveTema("", "desc", 6, 4);
        });
    }

    @Test
    public void testSaveTema_invalidDesc(){
       assertThrows(ValidationException.class, () -> {
           service.saveTema("id", "", 6, 4);
       });
    }

    @Test
    public void testSaveTema_invalidDeadline(){
        assertThrows(ValidationException.class, () -> {
            service.saveTema("id", "test", 3, 4);
        });
    }

    @Test
    public void testSaveTema_invalidStartline(){
        assertThrows(ValidationException.class, () -> {
            service.saveTema("id", "test", 6, 8);
        });
    }

    @Test
    public void testSaveTema_validInputDuplicateId(){
        var result = service.saveTema("id", "desc", 6, 4);
        assertEquals(0, result);

        result = service.saveTema("id", "desc", 6, 4);
        assertEquals(1, result);
    }


}
