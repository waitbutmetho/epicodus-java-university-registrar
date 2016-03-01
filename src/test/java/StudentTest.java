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
    Student firstStudent = new Student("Roach", "Herbology");
    Student secondStudent = new Student("Roach", "Herbology");
    assertTrue(firstStudent.equals(secondStudent));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Student myStudent = new Student("Roach", "Herbology");
    myStudent.save();
    assertTrue(Student.all().get(0).equals(myStudent));
  }

  @Test
  public void find_findStudentInDatabase_true() {
    Student myStudent = new Student("Roach", "Herbology");
    myStudent.save();
    Student savedStudent = Student.find(myStudent.getId());
    assertTrue(myStudent.equals(savedStudent));
  }

  @Test
  public void updateDepartment_replacesOldValueAndReturnsNewValue() {
    Student newStudent = new Student("Roach", "Herbology");
    newStudent.save();
    newStudent.updateDepartment("industrial agriculture");
    assertEquals("industrial agriculture", newStudent.getDepartment());
  }

  @Test
  public void addCourse_addsCourseToStudent() {
    Student myStudent = new Student("Roach", "Herbology");
    myStudent.save();

    Course myCourse = new Course("Smoking 101", "Snoop Dogg");
    myCourse.save();

    myStudent.addCourse(myCourse);
    Course savedCourse = myStudent.getCourses().get(0);
    assertTrue(myCourse.equals(savedCourse));
}

  @Test
  public void getCourses_returnsAllCourses_ArrayList() {
    Student myStudent = new Student("Roach", "Herbology");
    myStudent.save();

    Course myCourse = new Course("Smoking 101", "Snoop Dogg");
    myCourse.save();

    myStudent.addCourse(myCourse);
    List savedCourses = myStudent.getCourses();
    assertEquals(savedCourses.size(), 1);
  }
}
