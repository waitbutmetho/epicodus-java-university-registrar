import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Course {
  @Override
  public boolean equals(Object otherCourse) {
    if(!(otherCourse instanceof Course)) {
      return false;
    } else {
      Course newCourse = (Course) otherCourse;
        return this.getCourse().equals(newCourse.getCourse())
              && this.getProfessor().equals(newCourse.getProfessor())
              && this.getId() == newCourse.getId()
              && this.getCompleted() == newCourse.getCompleted();
    }
}

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

public void save() {
  String sql = "INSERT INTO courses (id, course, professor, iscompleted) VALUES (:id, :course, :professor, :iscompleted);";
  try (Connection con = DB.sql2o.open()) {
    this.id = (int) con.createQuery(sql, true)
      .addParameter("id", id)
      .addParameter("course", course)
      .addParameter("professor", professor)
      .addParameter("iscompleted", iscompleted)
      .executeUpdate()
      .getKey();
  }
}

public static Course find(int id) {
  String sql = "SELECT id, course, professor, iscompleted FROM courses where id=:id;";
  try (Connection con = DB.sql2o.open()) {
    Course course = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Course.class);
      return course;
  }
}

public void updateProf(String newProf) {
  this.professor = newProf;
  String sql = "UPDATE courses SET  professor=:professor WHERE id=:id;";
  try (Connection con = DB.sql2o.open()) {
    this.id = (int) con.createQuery(sql, true)
      .addParameter("professor", professor)
      .addParameter("id", id)
      .executeUpdate()
      .getKey();
  }
}
}
