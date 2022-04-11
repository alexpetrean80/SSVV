package service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import repository.CRUDRepository;

public class IntegrationTest {
  private Service service;

  @Mock
  private CRUDRepository<String, Student> studentRepo;
  @Mock
  private CRUDRepository<String, Tema> temaRepo;
  @Mock
  private CRUDRepository<Pair<String, String>, Nota> notaRepo;


  @Before
  public void setup() {
    service = new Service(studentRepo, temaRepo, notaRepo);
  }

  @After
  public void teardown() {
    service = null;
  }

  @Test
  public void addStudent_test() {
    Mockito.when(studentRepo.save(Mockito.any(Student.class))).thenReturn(null);

    var result = service.saveStudent("id", "Odobasian", 113);

    assertEquals(0, result);
  }

  @Test
  public void addAssignment_test() {

  }

  @Test
  public void addGrade_test() {

  }

  @Test
  public void addAll_test() {

  }
}
