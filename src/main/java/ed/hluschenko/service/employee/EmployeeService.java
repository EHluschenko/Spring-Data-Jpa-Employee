package ed.hluschenko.service.employee;

import ed.hluschenko.dto.employee.EmployeeDtoRequest;
import ed.hluschenko.entity.Employee;
import ed.hluschenko.service.BaseService;

import java.util.List;
import java.util.Optional;

public interface EmployeeService extends BaseService<Employee, EmployeeDtoRequest> {
    Employee create(EmployeeDtoRequest request);
    Optional<List<Employee>> getAll();
    Employee getById(Long id);
    Employee updateById(Long id, EmployeeDtoRequest request);
    boolean deleteById(Long id);
}
