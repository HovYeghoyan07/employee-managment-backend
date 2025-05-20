package com.example.employeemanagment.repository;

import com.example.employeemanagment.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByName(String name);


}
