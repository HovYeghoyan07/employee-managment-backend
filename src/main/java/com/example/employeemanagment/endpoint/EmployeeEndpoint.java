package com.example.employeemanagment.endpoint;

import com.example.employeemanagment.dto.EmployeeDto;
import com.example.employeemanagment.dto.SaveEmployeeRequest;
import com.example.employeemanagment.entity.Employee;
import com.example.employeemanagment.mapper.EmployeeMapper;
import com.example.employeemanagment.service.EmployeeService;
import com.example.employeemanagment.util.FileUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeEndpoint {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;
    private final FileUtil fileUtil;

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createEmployee(@RequestPart("employee") @Valid SaveEmployeeRequest employeeRequest, @RequestPart(value = "picture", required = false) MultipartFile picture) {
        if (employeeService.existsByPhone(employeeRequest.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Employee already exists");
        }
        EmployeeDto employeeDto = employeeService.createEmployee(employeeRequest,picture);
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

    @GetMapping("/employee/picture/{imageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageId) {
        byte[] picture = fileUtil.getPicture(imageId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture);
    }
}
