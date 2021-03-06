package com.fis.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fis.employee.bean.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
//	@Query(value = "SELECT e FROM Employee e WHERE e.permanent = 1")
//	@Query(value = "SELECT e FROM Employee e left join e.department d left join e.skillList WHERE e.permanent = 1")
	@Query(value = "SELECT e FROM Employee e left join fetch e.department d left join fetch e.skillList WHERE e.permanent = 1")
	List<Employee> getAllPermanentEmployees();

	@Query(value = "SELECT AVG(e.salary) FROM Employee e")
	double getAverageSalary();

	@Query(value = "SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
	double getAverageSalary(@Param("id") int id);

	@Query(value = "SELECT * FROM employee", nativeQuery = true)
	List<Employee> getAllEmployeesNative();
}
