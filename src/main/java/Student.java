import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Student {
  @Override
  public boolean equals(Object otherStudent) {
    if(!(otherStudent instanceof Student)) {
      return false;
    } else {
      Student newStudent = (Student) otherStudent;
        return this.getName().equals(newStudent.getName())
              && this.getDepartment().equals(newStudent.getDepartment())
              && this.getId() == newStudent.getId();
        }
  }

  private int id;
  private String name;
  private String date_registered;
  private String department;


public Student(String name, String department) {
  this.id = id;
  this.name = name;
  this.date_registered = LocalDateTime.now().toString();
  this.department = department;
}

public int getId() {
  return id;
}

public String getName() {
  return name;
}

public String getDepartment() {
  return department;
}

public String getTimeRegistered() {
    return date_registered;
  }

public static List<Student> all() {
  String sql = "SELECT id, name, date_registered, department FROM students;";
  try (Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Student.class);
  }
}

public void save() {
  String sql = "INSERT INTO students (id, name, date_registered, department) VALUES (:id, :name, :date_registered, :department);";
  try (Connection con = DB.sql2o.open()) {
    this.id = (int) con.createQuery(sql, true)
      .addParameter("id", id)
      .addParameter("name", name)
      .addParameter("date_registered", date_registered)
      .addParameter("department", department)
      .executeUpdate()
      .getKey();
  }
}
}
