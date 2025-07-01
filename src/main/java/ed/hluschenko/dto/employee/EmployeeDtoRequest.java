package ed.hluschenko.dto.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record EmployeeDtoRequest(Long id, String firstName, String lastName, String post,
                                 String phone) {
}
