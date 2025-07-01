package ed.hluschenko.service.employee;

import ed.hluschenko.dto.employee.EmployeeDtoRequest;
import ed.hluschenko.entity.Employee;
import ed.hluschenko.entity.EmployeeMapper;
import ed.hluschenko.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper mapper;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(EmployeeDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        Employee employee = mapper.dtoCreateToEntity(request);
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<List<Employee>> getAll() {
        Iterable<Employee> iterable = employeeRepository.findAll();
        // Конвертуємо Iterable в List,
        // оскільки interface CrudRepository<T, ID>
        // має саме метод Iterable<T> findAll();
        List<Employee> list =
                StreamSupport.stream(iterable.spliterator(), false)
                        .toList();
        // Запаковуємо List в Optional та повертаємо
        return Optional.of(list);
    }

    @Override
    public Employee getById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        return employeeRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Employee updateById(Long id, EmployeeDtoRequest request) {
        Objects.requireNonNull(request,
                "Parameter [request] must not be null!");
        if (id == null) {
            throw new IllegalArgumentException("Id must be provided!");
        }
        Optional<Employee> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            Employee employeeToUpdate =
                    mapper.dtoUpdateToEntity(id, request,
                            optional.get());
            employeeRepository.save(employeeToUpdate);
        }
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Long id) {
        Objects.requireNonNull(id,
                "Parameter [id] must not be null!");
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
