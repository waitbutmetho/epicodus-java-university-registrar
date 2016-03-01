import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;

public class StudentTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Student.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Student firstStudent = new Student("Bob", "Herbology");
    Student secondStudent = new Student("Bob", "Herbology");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Student myStudent = new Student("Bob", "Herbology");
    myStudent.save();
    assertTrue(Student.all().get(0).equals(myStudent));
  }
}
