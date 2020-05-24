package entity.f;

import javax.persistence.*;

@Entity
@Table(name = "FEmployee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String employeeNumber;
    private String name;
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;

    @ManyToOne(cascade = CascadeType.ALL)
    private Office office;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", office=" + office +
                '}';
    }
}
