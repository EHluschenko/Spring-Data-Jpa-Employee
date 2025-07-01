package ed.hluschenko.controller;

import ed.hluschenko.dto.employee.*;
import ed.hluschenko.entity.Employee;
import ed.hluschenko.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController( EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDtoCreateResponse> createEmployee(
            @RequestBody EmployeeDtoRequest request) {
        Employee employee = employeeService.create(request);
        return (employee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoCreateResponse.of(true,
                                employee)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoCreateResponse.of(false,
                                null));
    }

    @GetMapping
    public ResponseEntity<EmployeeDtoListResponse> getAllEmployees() {
        Optional<List<Employee>> optional = employeeService.getAll();
        if (optional.isPresent()) {
            List<Employee> list = optional.get();
            return (!list.isEmpty()) ?
                    ResponseEntity.status(HttpStatus.OK)
                            .body(EmployeeDtoListResponse.of(false,
                                    list)) :
                    ResponseEntity.status(HttpStatus.OK)
                            .body(EmployeeDtoListResponse.of(true,
                                    Collections.emptyList()));
        } else
            return ResponseEntity.status(HttpStatus.OK)
                    .body(EmployeeDtoListResponse.of(true,
                            Collections.emptyList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDtoGetByIdResponse> getEmployeeById(
            @PathVariable("id") Long id) {
        Employee employee = employeeService.getById(id);
        return (employee != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoGetByIdResponse.of(id, true,
                                employee)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoGetByIdResponse.of(id, false,
                                null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDtoUpdateResponse> updateEmployeeById(
            @PathVariable("id") Long id,
            @RequestBody EmployeeDtoRequest request) {
        return (employeeService.getById(id) != null) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoUpdateResponse.of(id, true,
                                employeeService.updateById(id, request))) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoUpdateResponse.of(id, false,
                                null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDtoDeleteResponse> deleteEmployeeById(
            @PathVariable(value = "id") Long id) {
        return (employeeService.deleteById(id)) ?
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoDeleteResponse.of(id, true)) :
                ResponseEntity.status(HttpStatus.OK)
                        .body(EmployeeDtoDeleteResponse.of(id, false));
    }

}
