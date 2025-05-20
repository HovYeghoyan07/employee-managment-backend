package com.example.employeemanagment.endpoint;

import com.example.employeemanagment.dto.EmployeeDto;
import com.example.employeemanagment.dto.SaveEmployeeRequest;
import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.mapper.EmployeeMapper;
import com.example.employeemanagment.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeEndpoint {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody @Valid SaveEmployeeRequest employeeRequest) {
        if (employeeService.existsByPhone(employeeRequest.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Employee already exists");
        }
        EmployeeDto employeeDto = employeeService.createEmployee(employeeRequest);
        return ResponseEntity.ok(employeeDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable int id) {
        return  ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
    }

    @GetMapping("/companyId/{companyId}")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByCompanyId(@PathVariable int companyId) {
        return ResponseEntity.ok(employeeService.getEmployeesByCompanyId(companyId));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody @Valid SaveEmployeeRequest employeeRequest) {
        return ResponseEntity.ok(employeeService.updateEmployee(id,employeeMapper.toEntity(employeeRequest)));
    }
}
