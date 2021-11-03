package org.sample.springboot.hibernate.jpa.repository;

import java.util.List;
import org.sample.springboot.hibernate.jpa.model.Employee;
import org.sample.springboot.hibernate.jpa.model.EmployeeIdentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeeIdentity> {
    List<Employee> findByEmployeeIdentityCompanyId(String companyId);
}
