package service;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import repository.CRUDRepository;
import repository.NotaRepository;
import repository.StudentRepository;
import repository.TemaRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BigBangIntegrationTest {
    private Service service;

    @Mock
    CRUDRepository<String, Student> studentRepo;
    @Mock
    CRUDRepository<String, Tema> temaRepo;
    @Mock
    CRUDRepository<Pair<String, String>, Nota> notaRepo;
    @Mock
    Validator<Student> studentValidator;
    @Mock
    Validator<Tema> temaValidator;
    @Mock
    Validator<Nota> notaValidator;

    @Before
    public void setup() {
        studentValidator = mock(StudentValidator.class);
        temaValidator = mock(TemaValidator.class);
        notaValidator = mock(NotaValidator.class);

        studentRepo = mock(StudentRepository.class);
        temaRepo = mock(TemaRepository.class);
        notaRepo = mock(NotaRepository.class);

        service = new Service(studentRepo, temaRepo, notaRepo);
    }

    @Test
    public void addStudent_test() {
        when(studentRepo.save(any(Student.class))).thenReturn(null);

        var result = service.saveStudent("id", "nume", 936);

        assertEquals(0, result);
    }

    @Test
    public void addAssignment_test() {
        when(temaRepo.save(any(Tema.class))).thenReturn(null);
        var res = service.saveTema("temaId", "temaDescriere", 30, 15);
        assertEquals(0, res);
    }

    @Test
    public void addGrade_test() {
        when(studentRepo.findOne("id")).thenReturn(new Student("id", "nume", 936));
        when(temaRepo.findOne("temaId")).thenReturn(new Tema("temaId", "descriere", 1, 2));
        when(notaRepo.save(any(Nota.class))).thenReturn(new Nota(new Pair<>("id", "temaId"), 10, 5, "bine boss"));

        var res = service.saveNota("id", "temaId", 10, 5, "bine boss");

        assertEquals(0, res);
    }

    @Test
    public void addAll_test() {
        when(studentRepo.save(any(Student.class))).thenReturn(null);
        when(temaRepo.save(any(Tema.class))).thenReturn(null);
        when(studentRepo.findOne("id")).thenReturn(new Student("id", "nume", 936));
        when(temaRepo.findOne("temaId")).thenReturn(new Tema("temaId", "descriere", 1, 2));
        when(notaRepo.save(any(Nota.class))).thenReturn(new Nota(new Pair<>("id", "temaId"), 10, 5, "bine boss"));

        var studentRes = service.saveStudent("id", "nume", 936);
        var temaRes = service.saveTema("temaId", "temaDescriere", 30, 15);
        var notaRes = service.saveNota("id", "temaId", 10, 5, "bine boss");

        assertEquals(0, studentRes);
        assertEquals(0, temaRes);
        assertEquals(0, notaRes);
    }
}
