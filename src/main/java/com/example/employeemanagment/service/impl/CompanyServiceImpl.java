package com.example.employeemanagment.service.impl;

import com.example.employeemanagment.dto.CompanyResponseDto;
import com.example.employeemanagment.dto.SaveCompanyRequest;
import com.example.employeemanagment.entity.Company;
import com.example.employeemanagment.mapper.CompanyMapper;
import com.example.employeemanagment.repository.CompanyRepository;
import com.example.employeemanagment.repository.EmployeeRepository;
import com.example.employeemanagment.service.CompanyService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<CompanyResponseDto> findAll() {
        List<Company> companyList = companyRepository.findAll();
        return companyMapper.toDtoList(companyList);
    }

    @Override
    public CompanyResponseDto save(SaveCompanyRequest saveCompanyRequest) {
        Company company = companyRepository.save(companyMapper.toEntity(saveCompanyRequest));
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        if (!companyRepository.existsById(id)) {
            throw new RuntimeException("Company does not exist");
        }
        employeeRepository.deleteEmployeeByCompanyId(id);
        companyRepository.deleteById(id);
    }

    @Override
    public CompanyResponseDto findById(int id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            return null;
        }
        return companyMapper.toDto(company);
    }

    @Override
    public Optional<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

    @Override
    public Company getCompanyById(int id) {
        Optional<Company> companyRepositoryById = companyRepository.findById(id);
        companyRepositoryById.orElseThrow(() -> new RuntimeException("Company does not exist with id " + id));
        return companyRepositoryById.orElse(null);
    }

    @Override
    public Company updateCompany(int id, Company company) {
        Company serviceCompany = getCompanyById(id);
        serviceCompany.setName(company.getName());
        serviceCompany.setAddress(company.getAddress());
        serviceCompany.setPhone(company.getPhone());
        serviceCompany.setEmail(company.getEmail());
        companyRepository.save(serviceCompany);
        return serviceCompany;
    }
}
