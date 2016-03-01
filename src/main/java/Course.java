import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Course {
  private int id;
  private String course;
  private String professor;
  private boolean iscompleted;


public Course(String course, String professor) {
  this.id = id;
  this.course = course;
  this.professor = professor;
  this.iscompleted = false;
}

public int getId() {
  return id;
}

public String getCourse() {
  return course;
}

public String getProfessor() {
  return professor;
}

public boolean getCompleted() {
  return iscompleted;
}

public static List<Course> all() {
  String sql = "SELECT id, course, professor FROM courses;";
  try (Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Course.class);
  }
}
}
