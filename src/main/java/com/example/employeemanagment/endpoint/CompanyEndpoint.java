package com.example.employeemanagment.endpoint;

import com.example.employeemanagment.dto.CompanyResponseDto;
import com.example.employeemanagment.dto.SaveCompanyRequest;
import com.example.employeemanagment.entity.Company;
import com.example.employeemanagment.mapper.CompanyMapper;
import com.example.employeemanagment.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyEndpoint {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create( @RequestBody @Valid SaveCompanyRequest companyRequest){
        if (companyService.findByName(companyRequest.getName()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Company already exists");
        }
        CompanyResponseDto companyResponseDto = companyService.save(companyRequest);

        return ResponseEntity.ok(companyResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable int id){
        return ResponseEntity.ok(companyService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        companyService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable("id") int id, @RequestBody @Valid SaveCompanyRequest companyRequest) {
       return ResponseEntity.ok(companyService.updateCompany(id, companyMapper.toEntity(companyRequest)));
    }
}
