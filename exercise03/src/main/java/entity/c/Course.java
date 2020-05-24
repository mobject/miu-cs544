package entity.c;

import entity.c.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String courseNumber;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseNumber='" + courseNumber + '\'' +
                ", name='" + name + '\'' +
                ", students="+students.size() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
