import java.util.Comparator;
import java.util.List;

public class EmployeeStream {

    static void main(String[] args) {
        List<Employee> employees = new java.util.ArrayList<>(List.of(new Employee(1, "Kritica", 4000.00),
                new Employee(2, "Sachin", 4000.00),
                new Employee(2, "Laavanya", 9000.00),
                new Employee(2, "abc", 1000.00),
                new Employee(2, "Sai", 5000.00),
                new Employee(2, "monika", 2000.00),
                new Employee(2, "neha", 7000.00)));

        //top 3 highest paid employees
        System.out.println("top 3 highest paid employees");
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3).forEach(employee -> System.out.println(employee.getName()+" "+employee.getSalary()));

        //Get highest paid employee
        System.out.println("Get highest paid employee");
        employees.stream().max(Comparator.comparing(Employee::getSalary)).ifPresent(employee -> System.out.println(employee.getName()+" "+employee.getSalary()));
        //Get names of top 3 highest paid employees.
        System.out.println("Get names of top 3 highest paid employees.");
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3).forEach(employee -> System.out.println(employee.getName()));

        //Get employees whose salary is greater than 4000, sorted by salary descending.
        System.out.println("Get employees whose salary is greater than 4000, sorted by salary descending.");

        //Get the total salary of all employees.
        System.out.println("Get the total salary of all employees.");

        //Get the average salary.
        System.out.println("Get the average salary.");

        //Get the employee with the second highest salary.
        System.out.println("Get the employee with the second highest salary.");

        //Age >30
        //IT department
//        Active employees
//        Unique employees
//        Sorted employees
//        Employee statistics
        //First IT employee
        //First salary >100000
//        Department wise count
//        Department wise average salary
//        Department wise max salary
    }
}


class Employee{
    String name;
    int id;
    Double salary;

    Employee(int id, String name, Double salary){
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Double getSalary() {
        return salary;
    }
}
