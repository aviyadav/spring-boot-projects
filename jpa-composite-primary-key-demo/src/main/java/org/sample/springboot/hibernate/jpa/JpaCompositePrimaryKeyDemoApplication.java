package org.sample.springboot.hibernate.jpa;

import java.util.Optional;
import org.sample.springboot.hibernate.jpa.model.Employee;
import org.sample.springboot.hibernate.jpa.model.EmployeeIdentity;
import org.sample.springboot.hibernate.jpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaCompositePrimaryKeyDemoApplication implements CommandLineRunner {
    
    @Autowired
    private EmployeeRepository employeeRepository;

    public static void main(String[] args) {
        SpringApplication.run(JpaCompositePrimaryKeyDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.deleteAllInBatch();
        
        Employee employee = new Employee(new EmployeeIdentity("E-123", "C-456"), "Abcdef Ghijk Lmnop", "abcdef.lmnop@someemail.com", "+91-9332442098");
        employeeRepository.save(employee);
        
        Optional<Employee> res = employeeRepository.findById(new EmployeeIdentity("E-123", "C-456"));
        System.out.println("Optional<Employee> --> " + res.get().toString());
        
        
        for (Employee emp : employeeRepository.findByEmployeeIdentityCompanyId("C-456")) {
            System.out.println("Employee --> " + emp.toString());
        }
    }
}
