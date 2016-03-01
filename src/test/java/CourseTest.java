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
  public void updateProf_replacesOldValueAndReturnsNewValue() {
    Course newCourse = new Course("Smoking 101", "Snoop Dogg");
    newCourse.save();
    newCourse.updateProf("Doug Benson");
    assertEquals("Doug Benson", newCourse.getProfessor());
  }
//   @Test
//   public void addTask_addsTaskToCategory() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//
//     Task myTask = new Task("Mow the lawn", false);
//     myTask.save();
//
//     myCategory.addTask(myTask);
//     Task savedTask = myCategory.getTasks().get(0);
//     assertTrue(myTask.equals(savedTask));
// }
//
//   @Test
//   public void getTasks_returnsAllTasks_ArrayList() {
//     Category myCategory = new Category("Household chores");
//     myCategory.save();
//
//     Task myTask = new Task("Mow the lawn", false);
//     myTask.save();
//
//     myCategory.addTask(myTask);
//     List savedTasks = myCategory.getTasks();
//     assertEquals(savedTasks.size(), 1);
// }
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
