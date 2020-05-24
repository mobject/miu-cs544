package entity.f;

import javax.persistence.*;
import java.util.List;

@Entity
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String building;
    @OneToMany
    private List<Employee> employees;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Office{" +
                "id=" + id +
                ", building='" + building + '\'' +
                ", employees=" + employees.size() +
                '}';
    }
}
