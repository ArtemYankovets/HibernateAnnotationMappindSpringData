package domain;

import entity.Address;
import entity.Employee;
import entity.Project;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AddressRepository;
import repository.EmployeeRepository;
import repository.ProjectRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

public class Domain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

        Address address = new Address();
        address.setId(1L);
        address.setCountry("DC");
        address.setCity("Gotham city");
        address.setStreet("Arkham street 1");
        address.setPostCode("12345");

        Project project = new Project();
        project.setTitle("Gotham PD");

        Employee employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName("Freeman");
        LocalDate birthday = LocalDate.of(1980, Month.JANUARY, 12);
        employee.setBirthday(Date.valueOf(birthday));
        employee.setAddress(address);

        // --------------------------------------------------------------------
        addressRepository.save(address);

//        If we used both options we will have duplicates in table "PROJECT"

//        Option 1:
//        Set<Employee> employees = new HashSet<>();
//        employees.add(employee);
//        project.setEmployees(employees);
//
//        projectService.save(project);


//        Option 2:
        Set<Project> projects = new HashSet<>();
        projects.add(project);
        employee.setProjects(projects);

        employeeRepository.save(employee);
// --------------------------------------------------------------------
    }


}
