import java.util.List;
import org.sql2o.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Student {
  private int id;
  private String name;
  private LocalDateTime date_registered;
  private String department;


public Student(String name, String department) {
  this.id = id;
  this.name = name;
  this.date_registered = LocalDateTime.now();
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

public LocalDateTime getTimeRegistered() {
    return date_registered;
  }

public static List<Student> all() {
  String sql = "SELECT id, name, date_registered, department FROM students;";
  try (Connection con = DB.sql2o.open()) {
    return con.createQuery(sql).executeAndFetch(Student.class);
  }
}
}
