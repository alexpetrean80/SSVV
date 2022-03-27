import console.*;
import domain.*;
import repository.*;
import service.*;
import validation.*;

public class Main {
  public static void main(String[] args) {
    var studentValidator = new StudentValidator();
    var temaValidator = new TemaValidator();
    var notaValidator = new NotaValidator();

    var fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
    var fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
    var fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

    var service = new Service(fileRepository1, fileRepository2, fileRepository3);
    var consola = new UI(service);
    consola.run();

    // PENTRU GUI
    // de avut un check: daca profesorul introduce sau nu saptamana la timp
    // daca se introduce nota la timp, se preia saptamana din sistem
    // altfel, se introduce de la tastatura
  }
}
