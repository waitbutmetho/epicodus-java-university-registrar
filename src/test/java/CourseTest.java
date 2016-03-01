import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import java.util.Arrays;

public class CourseTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Course.all().size(), 0);
  }
//
  @Test
  public void equals_returnsTrueIfNamesAretheSame() {
    Course firstCourse = new Course("Smoking 101", "Snoop Dogg");
    Course secondCourse = new Course("Smoking 101", "Snoop Dogg");
    assertTrue(firstCourse.equals(secondCourse));
  }
//
  @Test
  public void save_savesIntoDatabase_true() {
    Course myCourse = new Course("Smoking 101", "Snoop Dogg");
    myCourse.save();
    assertTrue(Course.all().get(0).equals(myCourse));
  }

  @Test
  public void find_findCourseInDatabase_true() {
    Course myCourse = new Course("Smoking 101", "Snoop Dogg");
    myCourse.save();
    Course savedCourse = Course.find(myCourse.getId());
    assertTrue(myCourse.equals(savedCourse));
  }

  @Test
  public void updateProfessor_replacesOldValueAndReturnsNewValue() {
    Course newCourse = new Course("Smoking 101", "Snoop Dogg");
    newCourse.save();
    newCourse.updateProfessor("Doug Benson");
    assertEquals("Doug Benson", newCourse.getProfessor());
  }

  @Test
  public void addStudent_addsStudentToCourse() {
    Course myCourse = new Course("Smoking 101", "Snoop Dogg");
    myCourse.save();

    Student myStudent = new Student("Roach", "Herbology");
    myStudent.save();

    myCourse.addStudent(myStudent);
    Student savedStudent = myCourse.getStudents().get(0);
    assertTrue(myStudent.equals(savedStudent));
}

  @Test
  public void getStudents_returnsAllStudents_ArrayList() {
    Course myCourse = new Course("Smoking 101", "Snoop Dogg");
    myCourse.save();

    Student myStudent = new Student("Roach", "Herbology");
    myStudent.save();

    myCourse.addStudent(myStudent);
    List savedStudents = myCourse.getStudents();
    assertEquals(savedStudents.size(), 1);
}
//
//   @Test
//   public void delete_deletesAllTasksAndListsAssoicationes() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//
//     Task myTask = new Task("Mow the lawn", false);
//     myTask.save();
//
//     myCategory.addTask(myTask);
//     myCategory.delete();
//     assertEquals(myTask.getCategories().size(), 0);
// }
}
